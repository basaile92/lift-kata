package fr.choibajil.lift.model.building;

import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BuildingTest {

    @Test
    void givenABuildingWithTwoFloors_whenMonitorOfIsCalledOfExistingFloor_thenItShouldReturnFloorMonitor() {
        // GIVEN
        final LinkedHashSet<FloorIdentifier> floors = new LinkedHashSet<>();
        floors.add(new FloorIdentifier("0"));
        floors.add(new FloorIdentifier("1"));
        final Building building = new Building(floors, 1);

        // WHEN
        final Optional<Monitor> monitor = building.monitorOf(new FloorIdentifier("0"));

        // THEN
        assertThat(monitor).isNotEmpty();
        assertThat(monitor).hasValue(Monitor.of(new FloorIdentifier("0")));
    }

    @Test
    void givenABuildingWithTwoFloors_whenMonitorOfIsCalledOfNotExistingFloor_thenItShouldReturnEmptyValue() {
        // GIVEN
        final LinkedHashSet<FloorIdentifier> floors = new LinkedHashSet<>();
        floors.add(new FloorIdentifier("0"));
        floors.add(new FloorIdentifier("1"));
        final Building building = new Building(floors, 1);

        // WHEN
        final Optional<Monitor> monitor = building.monitorOf(new FloorIdentifier("empty"));

        // THEN
        assertThat(monitor).isEmpty();
    }
}
