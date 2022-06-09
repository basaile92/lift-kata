package fr.choibajil.lift.utils;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.DOWN;
import static fr.choibajil.lift.model.common.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

class FloorUtilsTest {

    private static Set<FloorIdentifier> floors;

    @BeforeAll
    static void init() {
        floors = new HashSet<>();
        floors.add(new FloorIdentifier("RDC"));
        floors.add(new FloorIdentifier("1"));
        floors.add(new FloorIdentifier("2"));
        floors.add(new FloorIdentifier("3"));
        floors.add(new FloorIdentifier("4"));
        floors.add(new FloorIdentifier("5"));
    }

    @Nested
    class WhenGetDirectionToGoFromFloorToFloorIsCalled {

        @Test
        public void andSecondFloorIsUpperThanFirstFloorThenItShouldReturnUp() {
            // WHEN
            final Direction result = FloorUtils.getDirectionToGoFromFloorToFloor(floors, new FloorIdentifier("3"), new FloorIdentifier("5"));
            // THEN
            assertThat(result).isEqualTo(UP);
        }

        @Test
        public void andSecondFloorIsEqualToFirstFloorThenItShouldReturnUp() {
            // WHEN
            final Direction result = FloorUtils.getDirectionToGoFromFloorToFloor(floors, new FloorIdentifier("3"), new FloorIdentifier("3"));
            // THEN
            assertThat(result).isEqualTo(UP);
        }

        @Test
        public void andSecondFloorIsDownerThanFirstFloorThenItShouldReturnDown() {
            // WHEN
            final Direction result = FloorUtils.getDirectionToGoFromFloorToFloor(floors, new FloorIdentifier("4"), new FloorIdentifier("1"));
            // THEN
            assertThat(result).isEqualTo(DOWN);
        }
    }

    @Test
    void whenGetFloorIdentifierPositionInFloorsSetThenItShouldReturnFloorPositionInSet() {
        assertThat(FloorUtils.getFloorIdentifierPositionInFloorsSet(new FloorIdentifier("RDC"), floors)).isEqualTo(0);
        assertThat(FloorUtils.getFloorIdentifierPositionInFloorsSet(new FloorIdentifier("1"), floors)).isEqualTo(1);
        assertThat(FloorUtils.getFloorIdentifierPositionInFloorsSet(new FloorIdentifier("2"), floors)).isEqualTo(2);
        assertThat(FloorUtils.getFloorIdentifierPositionInFloorsSet(new FloorIdentifier("3"), floors)).isEqualTo(3);
        assertThat(FloorUtils.getFloorIdentifierPositionInFloorsSet(new FloorIdentifier("4"), floors)).isEqualTo(4);
        assertThat(FloorUtils.getFloorIdentifierPositionInFloorsSet(new FloorIdentifier("5"), floors)).isEqualTo(5);
    }
}
