package fr.choibajil.lift.model.building;

import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BuildingTest {

    @Test
    void givenABuildingWithTwoFloors_whenMonitorOfIsCalledOfExistingFloor_thenItShouldReturnFloorMonitor() {
        // GIVEN
        final Set<FloorIdentifier> floorIdentifiers = Set.of(new FloorIdentifier("0"), new FloorIdentifier("1"));
        final Building building = new Building(floorIdentifiers, 1);

        // WHEN
        final Optional<Monitor> monitor = building.monitorOf(new FloorIdentifier("0"));

        // THEN
        assertThat(monitor).isNotEmpty();
        assertThat(monitor).hasValue(Monitor.of(new FloorIdentifier("0")));
    }

    @Test
    void givenABuildingWithTwoFloors_whenMonitorOfIsCalledOfNotExistingFloor_thenItShouldReturnEmptyValue() {
        // GIVEN
        final Set<FloorIdentifier> floorIdentifiers = Set.of(new FloorIdentifier("0"), new FloorIdentifier("1"));
        final Building building = new Building(floorIdentifiers, 1);

        // WHEN
        final Optional<Monitor> monitor = building.monitorOf(new FloorIdentifier("empty"));

        // THEN
        assertThat(monitor).isEmpty();
    }
}
