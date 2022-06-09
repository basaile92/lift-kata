package fr.choibajil.lift.model.lift;

import fr.choibajil.lift.exception.LiftCommandsException;
import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.common.command.LiftCommand;
import fr.choibajil.lift.model.common.command.LiftCommandComparator;
import fr.choibajil.lift.model.common.command.LiftCommandFilter;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.UP;

@Data
public
class Lift {

    @NonNull
    @Setter
    private FloorIdentifier currentFloor;

    @Setter
    private Direction currentDirection;

    private final Set<FloorIdentifier> availableFloors;

    private List<LiftCommand> liftCommands;

    public List<LiftCommand> getLiftCommands() {
        return liftCommands;
    }

    public Lift(final FloorIdentifier currentFloor, final Set<FloorIdentifier> floors) {
        this.currentFloor = currentFloor;
        this.currentDirection = UP;
        this.availableFloors = floors;
        this.liftCommands = new ArrayList<>();
    }

    public void addLiftCommand(final LiftCommand liftCommand) {
        liftCommands.add(liftCommand);
        sortCommands();
        filterCommands();
    }

    protected void filterCommands() {
        liftCommands = LiftCommandFilter.filter(liftCommands);
    }

    protected void sortCommands() {
        liftCommands.sort(new LiftCommandComparator(currentFloor, currentDirection, this.availableFloors));
    }

    public void executeAllCommands() {
        while (!liftCommands.isEmpty())
            executeNextCommand();
    }

    @SneakyThrows
    public void executeNextCommand() {
        if (liftCommands.isEmpty()) {
            throw new LiftCommandsException("No commands to execute.");
        }
        final LiftCommand liftCommand = liftCommands.remove(0);
        applyNextCommandAndDingAndOpenAndCloseDoor(liftCommand);
    }

    private void applyNextCommandAndDingAndOpenAndCloseDoor(final LiftCommand liftCommand) {
        liftCommand.apply(this);
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
