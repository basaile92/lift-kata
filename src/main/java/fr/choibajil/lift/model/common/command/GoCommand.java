package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.floor.FloorIdentifier;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor(staticName = "of")
public class GoCommand implements LiftCommand {

    private FloorIdentifier directionFloor;

    @Override
    public void applyNextCommand() {

    }

    @Override
    public int getPriorityLevel() {
        return 0;
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
