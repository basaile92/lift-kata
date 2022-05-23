package fr.choibajil.lift.model.lift;

import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.common.command.LiftCommand;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public
class Lift {

    @NonNull
    @Setter
    private FloorIdentifier currentFloor;

    @Setter
    private Optional<Direction> currentDirection;

    private final Set<LiftButton> buttons;

    private final TreeSet<LiftCommand> liftCommands;

    public TreeSet<LiftCommand> getLiftCommands() {
        return liftCommands;
    }

    public Lift(FloorIdentifier currentFloor, Set<FloorIdentifier> floors) {
        this.currentFloor = currentFloor;
        this.currentDirection = Optional.empty();
        this.buttons = floors.stream().map(LiftButton::new).collect(Collectors.toSet());
        this.liftCommands = new TreeSet<>();
    }

    public void addLiftCommand(final LiftCommand liftCommand) {
        liftCommands.add(liftCommand);
    }

    public void executeAllCommands() {
        IntStream.of(liftCommands.size()).forEach(
                i -> executeNextCommand()
        );
    }

    public void executeNextCommand() {
        LiftCommand liftCommand = liftCommands.pollFirst();
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
