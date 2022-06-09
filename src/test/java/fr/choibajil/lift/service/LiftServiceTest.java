package fr.choibajil.lift.service;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.common.command.CallCommand;
import fr.choibajil.lift.model.common.command.GoCommand;
import fr.choibajil.lift.model.floor.FloorButton;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.model.lift.LiftButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LiftServiceTest {

    LiftService liftService;

    @BeforeEach
    void init() {
        liftService = new LiftService();
    }

    @Test
    void whenCallIsCalledThenItShouldAddCallCommandToLiftCommands() {
        // GIVEN
        final Lift lift = mock(Lift.class);

        // WHEN
        liftService.call(lift, Monitor.of(new FloorIdentifier("5")), new FloorButton(Direction.UP));

        // THEN
        verify(lift).addLiftCommand(CallCommand.of(new FloorIdentifier("5"), Direction.UP));
    }

    @Test
    void whenGoIsCalledThenItShouldAddGoCommandToLiftCommands() {
        // GIVEN
        final Lift lift = mock(Lift.class);

        // WHEN
        liftService.go(lift, new LiftButton(new FloorIdentifier("3")));

        // THEN
        verify(lift).addLiftCommand(GoCommand.of(new FloorIdentifier("3")));

    }

    @Test
    void executeNextCommand() {
    }
}
