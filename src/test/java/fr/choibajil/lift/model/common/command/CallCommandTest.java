package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.common.Sign;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.lift.Lift;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static fr.choibajil.lift.model.common.Direction.DOWN;
import static fr.choibajil.lift.model.common.Direction.UP;
import static fr.choibajil.lift.model.common.Sign.NEGATIVE;
import static fr.choibajil.lift.model.common.Sign.POSITIVE;
import static org.assertj.core.api.Assertions.assertThat;

class CallCommandTest {


    @Test
    void whenApplyThenItShouldChangeCurrentDirectionAndCurrentFloor() {
        // GIVEN
        final CallCommand callCommand = CallCommand.of(new FloorIdentifier("5"), UP);
        final Lift lift = new Lift(new FloorIdentifier("1"),
                Set.of(new FloorIdentifier("1"), new FloorIdentifier("5")));


        // WHEN
        callCommand.apply(lift);
        // THEN
        assertThat(lift.getCurrentFloor()).isEqualTo(new FloorIdentifier("5"));
        assertThat(lift.getCurrentDirection()).isEqualTo(UP);
    }


    @ParameterizedTest
    @MethodSource("provideArgumentsForCompareToCallCommand")
    void whenCompareToCallCommandIsCalled(
            final Direction liftDirection, final String liftFloor,
            final Direction directionCall1, final String floorCall1,
            final Direction directionCall2, final String floorCall2,
            final Sign resultSign) {
        // GIVEN
        final Set<FloorIdentifier> floorIdentifiers = new HashSet<>();
        floorIdentifiers.add(new FloorIdentifier("0"));
        floorIdentifiers.add(new FloorIdentifier("1"));
        floorIdentifiers.add(new FloorIdentifier("2"));
        floorIdentifiers.add(new FloorIdentifier("3"));
        floorIdentifiers.add(new FloorIdentifier("4"));
        floorIdentifiers.add(new FloorIdentifier("5"));

        final CallCommand firstCallCommand = CallCommand.of(new FloorIdentifier(floorCall1), directionCall1);
        final CallCommand secondCallCommand = CallCommand.of(new FloorIdentifier(floorCall2), directionCall2);

        // WHEN
        final int result = firstCallCommand.compareTo(secondCallCommand, new FloorIdentifier(liftFloor), liftDirection, floorIdentifiers);

        // THEN
        if (resultSign == POSITIVE) {
            assertThat(result).isPositive();
        } else if (resultSign == NEGATIVE) {
            assertThat(result).isNegative();
        }
    }

    private static Stream<? extends Arguments> provideArgumentsForCompareToCallCommand() {
        return Stream.of(
                Arguments.of(UP, "0", UP, "2", UP, "3", NEGATIVE),
                Arguments.of(UP, "0", UP, "3", UP, "2", POSITIVE),
                Arguments.of(UP, "0", DOWN, "2", DOWN, "3", POSITIVE),
                Arguments.of(UP, "0", DOWN, "3", DOWN, "2", NEGATIVE),
                Arguments.of(UP, "0", UP, "2", DOWN, "3", NEGATIVE),
                Arguments.of(UP, "0", DOWN, "2", UP, "3", POSITIVE),
                Arguments.of(UP, "0", UP, "3", DOWN, "2", NEGATIVE),
                Arguments.of(UP, "0", DOWN, "3", UP, "2", POSITIVE),
                Arguments.of(UP, "4", UP, "2", UP, "3", NEGATIVE),
                Arguments.of(UP, "4", UP, "3", UP, "2", POSITIVE),
                Arguments.of(UP, "4", DOWN, "2", DOWN, "3", POSITIVE),
                Arguments.of(UP, "4", DOWN, "3", DOWN, "2", NEGATIVE),
                Arguments.of(UP, "4", UP, "2", DOWN, "3", POSITIVE),
                Arguments.of(UP, "4", DOWN, "2", UP, "3", NEGATIVE),
                Arguments.of(UP, "4", UP, "3", DOWN, "2", POSITIVE),
                Arguments.of(UP, "4", DOWN, "3", UP, "2", NEGATIVE),
                Arguments.of(UP, "2", UP, "0", UP, "4", POSITIVE),
                Arguments.of(UP, "2", DOWN, "0", DOWN, "4", POSITIVE),
                Arguments.of(UP, "2", UP, "0", DOWN, "4", POSITIVE),
                Arguments.of(UP, "2", DOWN, "0", UP, "4", POSITIVE),
                Arguments.of(UP, "2", UP, "4", UP, "0", NEGATIVE),
                Arguments.of(UP, "2", DOWN, "4", DOWN, "0", NEGATIVE),
                Arguments.of(UP, "2", UP, "4", DOWN, "0", NEGATIVE),
                Arguments.of(UP, "2", DOWN, "4", UP, "0", NEGATIVE),
                Arguments.of(DOWN, "0", UP, "2", UP, "3", NEGATIVE),
                Arguments.of(DOWN, "0", UP, "3", UP, "2", POSITIVE),
                Arguments.of(DOWN, "0", DOWN, "2", DOWN, "3", POSITIVE),
                Arguments.of(DOWN, "0", DOWN, "3", DOWN, "2", NEGATIVE),
                Arguments.of(DOWN, "0", UP, "2", DOWN, "3", NEGATIVE),
                Arguments.of(DOWN, "0", DOWN, "2", UP, "3", POSITIVE),
                Arguments.of(DOWN, "0", UP, "3", DOWN, "2", NEGATIVE),
                Arguments.of(DOWN, "0", DOWN, "3", UP, "2", POSITIVE),
                Arguments.of(DOWN, "4", UP, "2", UP, "3", NEGATIVE),
                Arguments.of(DOWN, "4", UP, "3", UP, "2", POSITIVE),
                Arguments.of(DOWN, "4", DOWN, "2", DOWN, "3", POSITIVE),
                Arguments.of(DOWN, "4", DOWN, "3", DOWN, "2", NEGATIVE),
                Arguments.of(DOWN, "4", UP, "2", DOWN, "3", POSITIVE),
                Arguments.of(DOWN, "4", DOWN, "2", UP, "3", NEGATIVE),
                Arguments.of(DOWN, "4", UP, "3", DOWN, "2", POSITIVE),
                Arguments.of(DOWN, "4", DOWN, "3", UP, "2", NEGATIVE),
                Arguments.of(DOWN, "2", UP, "0", UP, "4", NEGATIVE),
                Arguments.of(DOWN, "2", DOWN, "0", DOWN, "4", NEGATIVE),
                Arguments.of(DOWN, "2", UP, "0", DOWN, "4", NEGATIVE),
                Arguments.of(DOWN, "2", DOWN, "0", UP, "4", NEGATIVE),
                Arguments.of(DOWN, "2", UP, "4", UP, "0", POSITIVE),
                Arguments.of(DOWN, "2", DOWN, "4", DOWN, "0", POSITIVE),
                Arguments.of(DOWN, "2", UP, "4", DOWN, "0", POSITIVE),
                Arguments.of(DOWN, "2", DOWN, "4", UP, "0", POSITIVE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCompareToGoCommand")
    void whenCompareToGoCommandIsCalled(
            final Direction liftDirection, final String liftFloor,
            final Direction directionCall, final String floorCall,
            final String floorToGo,
            final Sign resultSign) {
        // GIVEN
        final Set<FloorIdentifier> floorIdentifiers = new HashSet<>();
        floorIdentifiers.add(new FloorIdentifier("0"));
        floorIdentifiers.add(new FloorIdentifier("1"));
        floorIdentifiers.add(new FloorIdentifier("2"));
        floorIdentifiers.add(new FloorIdentifier("3"));
        floorIdentifiers.add(new FloorIdentifier("4"));
        floorIdentifiers.add(new FloorIdentifier("5"));

        final CallCommand callCommand = CallCommand.of(new FloorIdentifier(floorCall), directionCall);
        final GoCommand goCommand = GoCommand.of(new FloorIdentifier(floorToGo));

        // WHEN
        final int result = callCommand.compareTo(goCommand, new FloorIdentifier(liftFloor), liftDirection, floorIdentifiers);

        // THEN
        if (resultSign == POSITIVE) {
            assertThat(result).isPositive();
        } else if (resultSign == NEGATIVE) {
            assertThat(result).isNegative();
        }
    }

    private static Stream<? extends Arguments> provideArgumentsForCompareToGoCommand() {
        return Stream.of(
                Arguments.of(UP, "0", UP, "2", "3", NEGATIVE),
                Arguments.of(UP, "0", UP, "3", "2", POSITIVE),
                Arguments.of(UP, "0", DOWN, "2", "3", POSITIVE),
                Arguments.of(UP, "0", DOWN, "3", "2", POSITIVE),
                Arguments.of(UP, "4", UP, "2", "3", POSITIVE),
                Arguments.of(UP, "4", UP, "3", "2", POSITIVE),
                Arguments.of(UP, "4", DOWN, "2", "3", POSITIVE),
                Arguments.of(UP, "4", DOWN, "3", "2", NEGATIVE),
                Arguments.of(UP, "2", UP, "0", "4", POSITIVE),
                Arguments.of(UP, "2", UP, "4", "0", NEGATIVE),
                Arguments.of(UP, "2", DOWN, "0", "4", POSITIVE),
                Arguments.of(UP, "2", DOWN, "4", "0", NEGATIVE),
                Arguments.of(DOWN, "0", UP, "2", "3", NEGATIVE),
                Arguments.of(DOWN, "0", UP, "3", "2", POSITIVE),
                Arguments.of(DOWN, "0", DOWN, "2", "3", POSITIVE),
                Arguments.of(DOWN, "0", DOWN, "3", "2", POSITIVE),
                Arguments.of(DOWN, "4", UP, "2", "3", POSITIVE),
                Arguments.of(DOWN, "4", UP, "3", "2", POSITIVE),
                Arguments.of(DOWN, "4", DOWN, "2", "3", POSITIVE),
                Arguments.of(DOWN, "4", DOWN, "3", "2", NEGATIVE),
                Arguments.of(DOWN, "2", UP, "0", "4", NEGATIVE),
                Arguments.of(DOWN, "2", UP, "4", "0", POSITIVE),
                Arguments.of(DOWN, "2", DOWN, "0", "4", NEGATIVE),
                Arguments.of(DOWN, "2", DOWN, "4", "0", POSITIVE)

        );

    }

}
