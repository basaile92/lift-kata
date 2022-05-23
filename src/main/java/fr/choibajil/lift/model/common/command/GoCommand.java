package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.model.lift.LiftButton;
import fr.choibajil.lift.utils.FloorUtils;
import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(staticName = "of")
public class GoCommand implements LiftCommand {

    private FloorIdentifier directionFloor;

    @Override
    public int getPriorityLevel() {
        return 0;
    }

    @Override
    public void apply(Lift lift) {
        lift.setCurrentDirection(
                Optional.of(
                        FloorUtils.getDirectionToGoFromFloorToFloor(
                                lift.getButtons().stream().map(LiftButton::floorIdentifier).collect(Collectors.toSet()),
                                lift.getCurrentFloor(), directionFloor)));
        lift.setCurrentFloor(directionFloor);
    }

    @Override
    public int compareTo(final Object o) {
        return 0;
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
