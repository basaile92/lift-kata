package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.utils.FloorUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.DOWN;
import static fr.choibajil.lift.model.common.Direction.UP;
import static fr.choibajil.lift.utils.FloorUtils.getFloorIdentifierPositionInFloorsSet;

@AllArgsConstructor(staticName = "of")
public class GoCommand implements LiftCommand {

    @Getter
    private FloorIdentifier directionFloor;

    @Override
    public void apply(final Lift lift) {
        lift.setCurrentDirection(
                FloorUtils.getDirectionToGoFromFloorToFloor(
                        lift.getAvailableFloors(),
                        lift.getCurrentFloor(), directionFloor));
        lift.setCurrentFloor(directionFloor);
    }

    @Override
    public int compareTo(final CallCommand callCommand, final FloorIdentifier liftFloor, final Direction liftDirection, final Set<FloorIdentifier> floorsOrder) {
        return compareFloorIdentifierWithDirection(liftFloor, liftDirection, callCommand.getSourceFloor(), callCommand.getDirection(), directionFloor, floorsOrder);
    }

    @SneakyThrows
    private int compareFloorIdentifierWithDirection(
            final FloorIdentifier liftFloor, final Direction liftDirection,
            final FloorIdentifier callingCommandFloor, final Direction direction,
            final FloorIdentifier goingCommandFloor, final Set<FloorIdentifier> floorsOrder) {

        if (callingCommandFloor.equals(goingCommandFloor)) return 1;

        final int currentFloorId = getFloorIdentifierPositionInFloorsSet(liftFloor, floorsOrder);
        final int callingCommandFloorId = getFloorIdentifierPositionInFloorsSet(callingCommandFloor, floorsOrder);
        final int goingCommandFloorId = getFloorIdentifierPositionInFloorsSet(goingCommandFloor, floorsOrder);

        final int callingCommandFloorIdDiff = callingCommandFloorId - currentFloorId;
        final int goingCommandFloorIdDiff = goingCommandFloorId - currentFloorId;

        if (liftDirection == UP) {
            return compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsUp(goingCommandFloorIdDiff, callingCommandFloorIdDiff, direction);
        } else {
            return compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsDown(goingCommandFloorIdDiff, callingCommandFloorIdDiff, direction);
        }
    }

    private int compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsUp(final int goingCommandFloorIdDiff, final int callingCommandFloorIdDiff, final Direction direction) {
        if (floorsCalledAreBothUpperThanCurrentFloor(goingCommandFloorIdDiff, callingCommandFloorIdDiff)) {
            if (direction == UP) {
                return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff, callingCommandFloorIdDiff);
            } else {
                return firstFloorCalledNeedToBeFirst();
            }
        } else if (onlyFirstFloorCalledIsUpperThanCurrentFloor(goingCommandFloorIdDiff, callingCommandFloorIdDiff)) {
            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsUpperThanCurrentFloor(goingCommandFloorIdDiff, callingCommandFloorIdDiff)) {
            return secondFloorCalledNeedToBeFirst();
        } else {

            if (direction == UP) {
                return firstFloorCalledNeedToBeFirst();
            } else
                return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff, callingCommandFloorIdDiff);
        }
    }

    private int compareFloorDiffAndDirectionToOptimizeElevatorWayWhenElevatorDirectionIsDown(final int goingCommandFloorIdDiff, final int callingCommandFloorIdDiff, final Direction direction) {
        if (floorsCalledAreBothDownerThanCurrentFloor(goingCommandFloorIdDiff, callingCommandFloorIdDiff)) {
            if (direction == DOWN) {
                return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff, callingCommandFloorIdDiff);
            } else {
                return firstFloorCalledNeedToBeFirst();
            }
        } else if (onlyFirstFloorCalledIsDownerThanCurrentFloor(goingCommandFloorIdDiff, callingCommandFloorIdDiff)) {
            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsDownerThanCurrentFloor(goingCommandFloorIdDiff, callingCommandFloorIdDiff)) {
            return secondFloorCalledNeedToBeFirst();
        } else {
            if (direction == DOWN) {
                return firstFloorCalledNeedToBeFirst();
            } else
                return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff, callingCommandFloorIdDiff);
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

    private int closerFloorCalledNeedToBeFirst(final int floor1Diff, final int floor2Diff) {
        return Math.abs(floor1Diff) - Math.abs(floor2Diff);
    }

    private int firstFloorCalledNeedToBeFirst() {
        return -1;
    }

    private int secondFloorCalledNeedToBeFirst() {
        return 1;
    }

    @Override
    public int compareTo(final GoCommand goCommand, final FloorIdentifier liftFloor, final Direction liftDirection, final Set<FloorIdentifier> floorsOrder) {
        return compareFloorIdentifierWithDirection(liftFloor, liftDirection, directionFloor, goCommand.getDirectionFloor(), floorsOrder);
    }

    @Override
    public FloorIdentifier getFloor() {
        return directionFloor;
    }

    private int compareFloorIdentifierWithDirection(
            final FloorIdentifier liftFloor, final Direction liftDirection,
            final FloorIdentifier goCommandFloor1, final FloorIdentifier goCommandFloor2,
            final Set<FloorIdentifier> floorsOrder) {

        if (goCommandFloor1.equals(goCommandFloor2)) return 0;

        final int currentFloorId = getFloorIdentifierPositionInFloorsSet(liftFloor, floorsOrder);
        final int goingCommandFloorId1 = getFloorIdentifierPositionInFloorsSet(goCommandFloor1, floorsOrder);
        final int goingCommandFloorId2 = getFloorIdentifierPositionInFloorsSet(goCommandFloor2, floorsOrder);

        final int goingCommandFloorIdDiff1 = goingCommandFloorId1 - currentFloorId;
        final int goingCommandFloorIdDiff2 = goingCommandFloorId2 - currentFloorId;

        if (liftDirection == UP) {
            return compareFloorDiffToOptimizeElevatorWayWhenElevatorDirectionIsUp(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2);
        } else {
            return compareFloorDiffToOptimizeElevatorWayWhenElevatorDirectionIsDown(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2);
        }
    }


    private int compareFloorDiffToOptimizeElevatorWayWhenElevatorDirectionIsUp(final int goingCommandFloorIdDiff1, final int goingCommandFloorIdDiff2) {
        if (floorsCalledAreBothUpperThanCurrentFloor(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2)) {
            return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2);
        } else if (onlyFirstFloorCalledIsUpperThanCurrentFloor(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2)) {
            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsUpperThanCurrentFloor(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2)) {
            return secondFloorCalledNeedToBeFirst();
        } else {
            return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2);
        }
    }

    private int compareFloorDiffToOptimizeElevatorWayWhenElevatorDirectionIsDown(final int goingCommandFloorIdDiff1, final int goingCommandFloorIdDiff2) {
        if (floorsCalledAreBothDownerThanCurrentFloor(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2)) {
            return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2);
        } else if (onlyFirstFloorCalledIsDownerThanCurrentFloor(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2)) {
            return firstFloorCalledNeedToBeFirst();
        } else if (onlySecondFloorCalledIsDownerThanCurrentFloor(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2)) {
            return secondFloorCalledNeedToBeFirst();
        } else {
            return closerFloorCalledNeedToBeFirst(goingCommandFloorIdDiff1, goingCommandFloorIdDiff2);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GoCommand goCommand = (GoCommand) o;
        return Objects.equals(directionFloor, goCommand.directionFloor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directionFloor);
    }
}
