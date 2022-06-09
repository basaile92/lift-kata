package fr.choibajil.lift.model.common.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LiftCommandFilter {

    public static List<LiftCommand> filter(final List<LiftCommand> liftCommands) {
        final ArrayList<LiftCommand> liftCommandListFiltered = new ArrayList<>();
        liftCommandListFiltered.add(liftCommands.get(0));
        IntStream.range(1, liftCommands.size())
                .forEach(i -> {
                    final LiftCommand oldCommand = liftCommandListFiltered.get(liftCommandListFiltered.size() - 1);
                    final LiftCommand command = liftCommands.get(i);
                    if (!command.getFloor().equals(oldCommand.getFloor())) {
                        liftCommandListFiltered.add(command);
                    } else if (oldCommand instanceof GoCommand && command instanceof CallCommand) {
                        liftCommandListFiltered.remove(liftCommandListFiltered.size() - 1);
                        liftCommandListFiltered.add(command);
                    }
                });
        return liftCommandListFiltered;
    }
}
