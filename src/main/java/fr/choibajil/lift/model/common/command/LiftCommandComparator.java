package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.Set;

@AllArgsConstructor
public class LiftCommandComparator implements Comparator<LiftCommand> {

    private FloorIdentifier currentFloorIdentifier;

    private Direction currentDirection;

    private Set<FloorIdentifier> availableFloors;

    @Override
    public int compare(final LiftCommand o1, final LiftCommand o2) {
        if (o2 instanceof GoCommand goCommand) {
            return o1.compareTo(goCommand, this.currentFloorIdentifier, currentDirection, availableFloors);
        }

        if (o2 instanceof CallCommand callCommand) {
            return o1.compareTo(callCommand, this.currentFloorIdentifier, currentDirection, availableFloors);
        }

        return 0;
    }
}
