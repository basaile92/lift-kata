package fr.choibajil.lift.model.lift;

import fr.choibajil.lift.model.common.command.GoCommand;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.DOWN;
import static fr.choibajil.lift.model.common.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class LiftTest {

    Lift lift;

    @BeforeEach
    void init() {
        final Set<FloorIdentifier> floors = new HashSet<>();
        floors.add(new FloorIdentifier("0"));
        floors.add(new FloorIdentifier("1"));
        floors.add(new FloorIdentifier("2"));
        floors.add(new FloorIdentifier("3"));
        floors.add(new FloorIdentifier("4"));
        floors.add(new FloorIdentifier("5"));
        lift = spy(new Lift(new FloorIdentifier("1"), floors));
    }

    @Test
    void whenAddLiftCommandThenItShouldAddNewLiftCommandsAndSortCommandsAndFilterCommands() {
        // WHEN
        lift.addLiftCommand(GoCommand.of(new FloorIdentifier("5")));

        // THEN
        assertThat(lift.getLiftCommands()).containsExactly(GoCommand.of(new FloorIdentifier("5")));
        verify(lift).sortCommands();
        verify(lift).filterCommands();
    }

    @Test
    void whenExecuteAllCommandsIsCalledThenItShouldExecuteAllCommands() {
        // GIVEN
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("3")));
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("5")));
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("3")));
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("0")));

        // WHEN
        lift.executeAllCommands();

        // THEN
        assertThat(lift.getCurrentFloor()).isEqualTo(new FloorIdentifier("0"));
        assertThat(lift.getCurrentDirection()).isEqualTo(DOWN);
    }

    @Test
    void whenExecuteNextCommandIsCalledThenItShouldExecuteOnlyNextCommand() {
        // GIVEN
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("3")));
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("5")));
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("3")));
        lift.getLiftCommands().add(GoCommand.of(new FloorIdentifier("0")));

        // WHEN
        lift.executeNextCommand();

        // THEN
        assertThat(lift.getCurrentFloor()).isEqualTo(new FloorIdentifier("3"));
        assertThat(lift.getCurrentDirection()).isEqualTo(UP);

    }
}
