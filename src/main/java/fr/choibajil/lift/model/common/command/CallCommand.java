package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.lift.Lift;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.DOWN;
import static fr.choibajil.lift.model.common.Direction.UP;
import static fr.choibajil.lift.utils.FloorUtils.getFloorIdentifierPositionInFloorsSet;

@AllArgsConstructor(staticName = "of")
public class CallCommand implements LiftCommand {

    @Getter
    private FloorIdentifier sourceFloor;

    @Getter
    private Direction direction;

    @Override
    public void apply(final Lift lift) {
        lift.setCurrentDirection(direction);
        lift.setCurrentFloor(sourceFloor);

    }

    @Override
    public int compareTo(
            final CallCommand callCommand, final FloorIdentifier liftFloor,
            final Direction liftDirection, final Set<FloorIdentifier> floorsOrder) {

        return compareFloorIdentifierWithDirection(liftFloor, liftDirection, sourceFloor, direction, callCommand.getSourceFloor(), callCommand.getDirection(), floorsOrder);
    }

    @SneakyThrows
    private int compareFloorIdentifierWithDirection(
            final FloorIdentifier liftFloor, final Direction liftDirection,
            final FloorIdentifier floor1, final Direction direction1, final FloorIdentifier floor2,
            final Direction direction2, final Set<FloorIdentifier> floorsOrder) {

        if (floor1.equals(floor2) && direction1 == direction2) return 0;

        final int currentFloorId = getFloorIdentifierPositionInFloorsSet(liftFloor, floorsOrder);
        final int floor1Id = getFloorIdentifierPositionInFloorsSet(floor1, floorsOrder);
        final int floor2Id = getFloorIdentifierPositionInFloorsSet(floor2, floorsOrder);

        final int floor1Diff = floor1Id - currentFloorId;
        final int floor2Diff = floor2Id - currentFloorId;

        if (liftDirection == UP) {
            return compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsUp(floor1Diff, direction1, floor2Diff, direction2);
        } else {
            return compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsDown(floor1Diff, direction1, floor2Diff, direction2);
        }
    }

    private int compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsUp(
            final int floor1Diff, final Direction directionCalled1,
            final int floor2Diff, final Direction directionCalled2) {

        if (floorsCalledAreBothUpperThanCurrentFloor(floor1Diff, floor2Diff)) {
            if (directionsCalledAreBothUp(directionCalled1, directionCalled2)) {
                return closerFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
            } else if (firstDirectionCalledIsUpAndSecondIsDown(directionCalled1, directionCalled2)) {
                return firstFloorCalledNeedToBeFirst();
            } else if (firstDirectionCalledIsDownAndSecondIsUp(directionCalled1, directionCalled2)) {
                return secondFloorCalledNeedToBeFirst();
            } else {
                return fartherFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
            }
        } else if (onlyFirstFloorCalledIsUpperThanCurrentFloor(floor1Diff, floor2Diff)) {

            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsUpperThanCurrentFloor(floor1Diff, floor2Diff)) {

            return secondFloorCalledNeedToBeFirst();
        } else {

            if (directionsCalledAreBothUp(directionCalled1, directionCalled2)) {
                return fartherFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
            } else if (firstDirectionCalledIsUpAndSecondIsDown(directionCalled1, directionCalled2)) {
                return secondFloorCalledNeedToBeFirst();
            } else if (firstDirectionCalledIsDownAndSecondIsUp(directionCalled1, directionCalled2)) {
                return firstFloorCalledNeedToBeFirst();
            } else
                return closerFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
        }
    }

    private int compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsDown(
            final int floor1Diff, final Direction directionCalled1,
            final int floor2Diff, final Direction directionCalled2) {

        if (floorsCalledAreBothDownerThanCurrentFloor(floor1Diff, floor2Diff)) {
            if (directionCalledAreBothDown(directionCalled1, directionCalled2)) {
                return closerFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
            } else if (firstDirectionCalledIsDownAndSecondIsUp(directionCalled1, directionCalled2)) {
                return firstFloorCalledNeedToBeFirst();
            } else if (firstDirectionCalledIsUpAndSecondIsDown(directionCalled1, directionCalled2)) {
                return secondFloorCalledNeedToBeFirst();
            } else
                return fartherFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
        } else if (onlyFirstFloorCalledIsDownerThanCurrentFloor(floor1Diff, floor2Diff)) {

            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsDownerThanCurrentFloor(floor1Diff, floor2Diff)) {

            return secondFloorCalledNeedToBeFirst();
        } else {

            if (directionCalledAreBothDown(directionCalled1, directionCalled2)) {
                return fartherFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
            } else if (firstDirectionCalledIsDownAndSecondIsUp(directionCalled1, directionCalled2)) {
                return secondFloorCalledNeedToBeFirst();
            } else if (firstDirectionCalledIsUpAndSecondIsDown(directionCalled1, directionCalled2)) {
                return firstFloorCalledNeedToBeFirst();
            } else
                return closerFloorCalledNeedToBeFirst(floor1Diff, floor2Diff);
        }
    }

    @Override
    public int compareTo(
            final GoCommand goCommand, final FloorIdentifier liftFloor,
            final Direction liftDirection, final Set<FloorIdentifier> floorsOrder) {

        return compareFloorIdentifierWithDirection(liftFloor, liftDirection, sourceFloor, direction, goCommand.getDirectionFloor(), floorsOrder);
    }

    @Override
    public FloorIdentifier getFloor() {
        return sourceFloor;
    }

    @SneakyThrows
    private int compareFloorIdentifierWithDirection(
            final FloorIdentifier liftFloor, final Direction liftDirection,
            final FloorIdentifier callingCommandFloor, final Direction direction,
            final FloorIdentifier goingCommandFloor, final Set<FloorIdentifier> floorsOrder) {

        if (callingCommandFloor.equals(goingCommandFloor)) return -1;

        final int currentFloorId = getFloorIdentifierPositionInFloorsSet(liftFloor, floorsOrder);
        final int callingCommandFloorId = getFloorIdentifierPositionInFloorsSet(callingCommandFloor, floorsOrder);
        final int goingCommandFloorId = getFloorIdentifierPositionInFloorsSet(goingCommandFloor, floorsOrder);

        final int callingCommandFloorIdDiff = callingCommandFloorId - currentFloorId;
        final int goingCommandFloorIdDiff = goingCommandFloorId - currentFloorId;

        if (liftDirection == UP) {
            return compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsUp(callingCommandFloorIdDiff, direction, goingCommandFloorIdDiff);
        } else {
            return compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsDown(callingCommandFloorIdDiff, direction, goingCommandFloorIdDiff);
        }
    }

    private int compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsUp(final int callingCommandFloorIdDiff, final Direction direction, final int goingCommandFloorIdDiff) {
        if (floorsCalledAreBothUpperThanCurrentFloor(callingCommandFloorIdDiff, goingCommandFloorIdDiff)) {
            if (direction == UP) {
                return closerFloorCalledNeedToBeFirst(callingCommandFloorIdDiff, goingCommandFloorIdDiff);
            } else {
                return secondFloorCalledNeedToBeFirst();
            }
        } else if (onlyFirstFloorCalledIsUpperThanCurrentFloor(callingCommandFloorIdDiff, goingCommandFloorIdDiff)) {
            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsUpperThanCurrentFloor(callingCommandFloorIdDiff, goingCommandFloorIdDiff)) {
            return secondFloorCalledNeedToBeFirst();
        } else {

            if (direction == UP) {
                return secondFloorCalledNeedToBeFirst();
            } else
                return closerFloorCalledNeedToBeFirst(callingCommandFloorIdDiff, goingCommandFloorIdDiff);
        }
    }

    private int compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsDown(final int callingCommandFloorIdDiff, final Direction direction, final int goingCommandFloorIdDiff) {
        if (floorsCalledAreBothDownerThanCurrentFloor(callingCommandFloorIdDiff, goingCommandFloorIdDiff)) {
            if (direction == DOWN) {
                return closerFloorCalledNeedToBeFirst(callingCommandFloorIdDiff, goingCommandFloorIdDiff);
            } else {
                return secondFloorCalledNeedToBeFirst();
            }
        } else if (onlyFirstFloorCalledIsDownerThanCurrentFloor(callingCommandFloorIdDiff, goingCommandFloorIdDiff)) {
            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsDownerThanCurrentFloor(callingCommandFloorIdDiff, goingCommandFloorIdDiff)) {
            return secondFloorCalledNeedToBeFirst();
        } else {
            if (direction == DOWN) {
                return secondFloorCalledNeedToBeFirst();
            } else
                return closerFloorCalledNeedToBeFirst(callingCommandFloorIdDiff, goingCommandFloorIdDiff);
        }
    }

    private boolean floorsCalledAreBothUpperThanCurrentFloor(final int floor1Diff, final int floor2Diff) {
        return floor1Diff >= 0 && floor2Diff >= 0;
    }

    private boolean floorsCalledAreBothDownerThanCurrentFloor(final int floor1Diff, final int floor2Diff) {
        return floor1Diff <= 0 && floor2Diff <= 0;
    }

    private boolean onlyFirstFloorCalledIsUpperThanCurrentFloor(final int floor1Diff, final int floor2Diff) {
        return floor1Diff >= 0 && floor2Diff < 0;
    }

    private boolean onlySecondFloorCalledIsUpperThanCurrentFloor(final int floor1Diff, final int floor2Diff) {
        return floor1Diff < 0 && floor2Diff >= 0;
    }

    private boolean onlyFirstFloorCalledIsDownerThanCurrentFloor(final int floor1Diff, final int floor2Diff) {
        return floor1Diff <= 0 && floor2Diff > 0;
    }

    private boolean onlySecondFloorCalledIsDownerThanCurrentFloor(final int floor1Diff, final int floor2Diff) {
        return floor1Diff > 0 && floor2Diff <= 0;
    }

    private boolean firstDirectionCalledIsUpAndSecondIsDown(final Direction directionCalled1, final Direction directionCalled2) {
        return directionCalled1 == Direction.UP && directionCalled2 == Direction.DOWN;
    }

    private boolean firstDirectionCalledIsDownAndSecondIsUp(final Direction directionCalled1, final Direction directionCalled2) {
        return directionCalled1 == Direction.DOWN && directionCalled2 == Direction.UP;
    }

    private boolean directionsCalledAreBothUp(final Direction directionCalled1, final Direction directionCalled2) {
        return directionCalled1 == Direction.UP && directionCalled2 == Direction.UP;
    }

    private boolean directionCalledAreBothDown(final Direction directionCalled1, final Direction directionCalled2) {
        return directionCalled1 == Direction.DOWN && directionCalled2 == Direction.DOWN;
    }


    private int closerFloorCalledNeedToBeFirst(final int floor1Diff, final int floor2Diff) {
        return Math.abs(floor1Diff) - Math.abs(floor2Diff);
    }

    private int fartherFloorCalledNeedToBeFirst(final int floor1Diff, final int floor2Diff) {
        return Math.abs(floor2Diff) - Math.abs(floor1Diff);
    }

    private int firstFloorCalledNeedToBeFirst() {
        return -1;
    }

    private int secondFloorCalledNeedToBeFirst() {
        return 1;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CallCommand that = (CallCommand) o;
        return Objects.equals(sourceFloor, that.sourceFloor) && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceFloor, direction);
    }
}
