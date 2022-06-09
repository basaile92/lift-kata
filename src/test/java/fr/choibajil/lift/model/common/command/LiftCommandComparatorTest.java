package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LiftCommandComparatorTest {

    private Set<FloorIdentifier> floors;

    private FloorIdentifier floorIdentifier;

    private Direction direction;

    GoCommand liftCommand;

    @BeforeEach
    void init() {
        floors = new HashSet<>();
        floors.add(new FloorIdentifier("0"));
        floors.add(new FloorIdentifier("1"));
        floors.add(new FloorIdentifier("2"));
        floors.add(new FloorIdentifier("3"));
        floors.add(new FloorIdentifier("4"));
        floors.add(new FloorIdentifier("5"));
        floorIdentifier = new FloorIdentifier("1");
        direction = Direction.UP;
        liftCommand = mock(GoCommand.class);
    }

    @Test
    void whenCompareIsCalledWithGoCommandThenItShouldCallCompareToGoCommand() {
        // GIVEN
        final GoCommand goCommand = mock(GoCommand.class);
        final LiftCommandComparator liftCommandComparator = new LiftCommandComparator(floorIdentifier, direction, floors);
        when(liftCommand.compareTo(goCommand, floorIdentifier, direction, floors)).thenReturn(1);

        // WHEN
        final int result = liftCommandComparator.compare(liftCommand, goCommand);

        // THEN
        assertThat(result).isEqualTo(1);
        verify(liftCommand).compareTo(goCommand, floorIdentifier, direction, floors);
    }

    @Test
    void whenCompareIsCalledWithCallCommandThenItShouldCallCompareToCallCommand() {
        // GIVEN
        final CallCommand callCommand = mock(CallCommand.class);
        final LiftCommandComparator liftCommandComparator = new LiftCommandComparator(floorIdentifier, direction, floors);
        when(liftCommand.compareTo(callCommand, floorIdentifier, direction, floors)).thenReturn(1);

        // WHEN
        final int result = liftCommandComparator.compare(liftCommand, callCommand);

        // THEN
        assertThat(result).isEqualTo(1);
        verify(liftCommand).compareTo(callCommand, floorIdentifier, direction, floors);

    }
}
