package fr.choibajil.lift.feature.monitor;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.common.command.CallCommand;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.service.LiftService;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Étantdonné;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.UP;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class MonitorSteps {

    @Autowired
    private LiftService liftService;
    @Autowired
    private MonitorScenarioState monitorScenarioState;

    @Étantdonné("un immeuble de cinq étages")
    public void un_immeuble_de_cinq_etages() {
        monitorScenarioState.setCurrentMonitor(Monitor.of(new FloorIdentifier("1")));
        monitorScenarioState.setBuilding(Building
                .builder()
                .monitors(Set.of(
                        Monitor.of(new FloorIdentifier("0")),
                        monitorScenarioState.getCurrentMonitor(),
                        Monitor.of(new FloorIdentifier("2")),
                        Monitor.of(new FloorIdentifier("3")),
                        Monitor.of(new FloorIdentifier("4"))
                ))
                .build());
    }

    @Étantdonné("un ascenseur situé au premier étage")
    public void un_ascenseur_situé_au_premier_étage() {
        monitorScenarioState.getBuilding().setLifts(singletonList(Lift.of((new FloorIdentifier("1")), emptyList())));
    }

    @Quand("j'appuie sur le bouton monter du moniteur du premier étage")
    public void j_appuie_sur_le_bouton_monter_du_moniteur_du_premier_étage() throws Exception {
        final Monitor firstFloorMonitor = monitorScenarioState.getBuilding().monitorOf(new FloorIdentifier("1")).orElseThrow(() -> new Exception("First floor not exists"));


        liftService.call(monitorScenarioState.getBuilding().getLifts().get(0), firstFloorMonitor, firstFloorMonitor.upFloorButton);
    }

    @Alors("l'ascenseur reçoit une demande pour monter provenant du premier étage")
    public void l_ascenseur_reçoit_une_demande_pour_monter_provenant_du_premier_étage() {
        assertThat(monitorScenarioState.getBuilding().getLifts().get(0).getLiftCommands()).containsExactly(CallCommand.of(new FloorIdentifier("1"), UP));
    }

}
