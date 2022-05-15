package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor(staticName = "of")
public class CallCommand implements LiftCommand {

    private FloorIdentifier sourceFloor;

    private Direction direction;


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
        final CallCommand that = (CallCommand) o;
        return Objects.equals(sourceFloor, that.sourceFloor) && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceFloor, direction);
    }
}
