package fr.choibajil.lift.service;

import fr.choibajil.lift.model.common.command.CallCommand;
import fr.choibajil.lift.model.common.command.GoCommand;
import fr.choibajil.lift.model.floor.FloorButton;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.model.lift.LiftButton;
import org.springframework.stereotype.Service;


@Service
public class LiftService {

    public void call(final Lift lift, final Monitor monitorSourceFloor, final FloorButton floorButton) {
        lift.addLiftCommand(CallCommand.of(monitorSourceFloor.floorIdentifier, floorButton.direction()));
    }

    public void go(final Lift lift, final LiftButton monitorDirectionFloor) {
        lift.addLiftCommand(GoCommand.of(monitorDirectionFloor.floorIdentifier()));
    }

    public void executeNextCommand(Lift lift) {
        lift.executeNextCommand();
    }
}
