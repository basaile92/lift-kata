package fr.choibajil.lift.utils;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import lombok.SneakyThrows;

import java.util.Iterator;
import java.util.Set;

public class FloorUtils {

    public static Direction getDirectionToGoFromFloorToFloor(final Set<FloorIdentifier> floors, final FloorIdentifier sourceFloor, final FloorIdentifier directionFloor) {
        final int floorBetweenSourceAndDirection = floors.stream().toList().indexOf(directionFloor) - floors.stream().toList().indexOf(sourceFloor);
        return floorBetweenSourceAndDirection >= 0 ? Direction.UP : Direction.DOWN;
    }

    @SneakyThrows
    public static int getFloorIdentifierPositionInFloorsSet(final FloorIdentifier floor, final Set<FloorIdentifier> floors) {
        int id = 0;
        final Iterator<FloorIdentifier> iterator = floors.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(floor))
                return id;
            id++;
        }
        throw new Exception("Floor identifiers are not linked to the elevator");
    }

}
