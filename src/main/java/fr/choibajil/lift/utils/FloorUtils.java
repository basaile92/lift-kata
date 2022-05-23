package fr.choibajil.lift.utils;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;

import java.util.Set;
import java.util.stream.Collectors;

public class FloorUtils {

    public static Direction getDirectionToGoFromFloorToFloor(Set<FloorIdentifier> floors, FloorIdentifier sourceFloor, FloorIdentifier directionFloor) {
        int floorBetweenSourceAndDirection = floors.stream().toList().indexOf(directionFloor) - floors.stream().toList().indexOf(sourceFloor);
        return floorBetweenSourceAndDirection >= 0 ? Direction.UP: Direction.DOWN;
    }
}
