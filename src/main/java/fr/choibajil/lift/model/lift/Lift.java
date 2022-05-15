package fr.choibajil.lift.model.lift;

import fr.choibajil.lift.model.common.command.LiftCommand;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.TreeSet;

@RequiredArgsConstructor(staticName = "of")
@Data
public
class Lift {

    @NonNull
    private final FloorIdentifier currentFloor;

    private final List<LiftButton> buttons;

    private final TreeSet<LiftCommand> liftCommands = new TreeSet<>();

    public TreeSet<LiftCommand> getLiftCommands() {
        return liftCommands;
    }

    public void addLiftCommand(final LiftCommand liftCommand) {
        liftCommands.add(liftCommand);
    }

    public void executeScheduledCommands() {
        liftCommands.forEach(this::applyNextCommandAndDingAndOpenAndCloseDoor);
    }

    private void applyNextCommandAndDingAndOpenAndCloseDoor(final LiftCommand liftCommand) {
        liftCommand.applyNextCommand();
        ding();
        openDoor();
        closeDoor();
    }

    public void ding() {
        System.out.println("DING!");
    }

    public void openDoor() {
        System.out.println("DOOR OPENED!");
    }

    public void closeDoor() {
        System.out.println("DOOR CLOSED!");
    }
}
