package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LiftCommandFilterTest {

    @Test
    void whenFilterIsCalledThenItShouldFilter() {
        // GIVEN
        final List<LiftCommand> filterCommandList = new ArrayList<>();
        filterCommandList.add(GoCommand.of(new FloorIdentifier("3")));
        filterCommandList.add(CallCommand.of(new FloorIdentifier("3"), Direction.UP));
        filterCommandList.add(GoCommand.of(new FloorIdentifier("3")));
        filterCommandList.add(GoCommand.of(new FloorIdentifier("4")));
        filterCommandList.add(GoCommand.of(new FloorIdentifier("4")));
        filterCommandList.add(GoCommand.of(new FloorIdentifier("6")));
        filterCommandList.add(GoCommand.of(new FloorIdentifier("6")));
        filterCommandList.add(CallCommand.of(new FloorIdentifier("6"), Direction.DOWN));
        filterCommandList.add(GoCommand.of(new FloorIdentifier("6")));
        filterCommandList.add(GoCommand.of(new FloorIdentifier("3")));

        final List<LiftCommand> expectedResult = new ArrayList<>();
        expectedResult.add(CallCommand.of(new FloorIdentifier("3"), Direction.UP));
        expectedResult.add(GoCommand.of(new FloorIdentifier("4")));
        expectedResult.add(CallCommand.of(new FloorIdentifier("6"), Direction.DOWN));
        expectedResult.add(GoCommand.of(new FloorIdentifier("3")));

        // WHEN
        final List<LiftCommand> result = LiftCommandFilter.filter(filterCommandList);

        // THEN
        assertThat(result).containsExactlyElementsOf(expectedResult);
    }
}
