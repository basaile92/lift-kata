package fr.choibajil.lift.model.common.command;

import fr.choibajil.lift.model.lift.Lift;

public interface LiftCommand extends Comparable {


    int getPriorityLevel();

    void apply(Lift lift);
}
