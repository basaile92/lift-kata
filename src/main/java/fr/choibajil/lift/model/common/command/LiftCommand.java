package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.lift.Lift;

import java.util.Set;

public interface LiftCommand {

    void apply(Lift lift);

    int compareTo(CallCommand callCommand, FloorIdentifier floorIdentifier, Direction liftDirection, Set<FloorIdentifier> floorsOrder);

    int compareTo(GoCommand goCommand, FloorIdentifier floorIdentifier, Direction liftDirection, Set<FloorIdentifier> floorsOrder);

    FloorIdentifier getFloor();
}
