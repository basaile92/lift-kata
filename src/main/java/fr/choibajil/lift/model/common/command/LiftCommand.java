package fr.choibajil.lift.model.common.command;

public interface LiftCommand extends Comparable {

    void applyNextCommand();

    int getPriorityLevel();
}
