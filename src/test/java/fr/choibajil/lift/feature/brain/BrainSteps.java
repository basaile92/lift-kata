package fr.choibajil.lift.feature.brain;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.model.lift.LiftButton;
import fr.choibajil.lift.service.LiftService;
import io.cucumber.java.ParameterType;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;

import java.util.Optional;
import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

public class BrainSteps {

    @Autowired
    private BrainScenarioState brainScenarioState;

    @Autowired
    private LiftService liftService;

    @Etantdonné("un building avec les étages ordonnés:{string},{string},{string},{string},{string},{string}")
    public void unBuildingAvecLesÉtagesOrdonnés(String etage1, String etage2, String etage3, String etage4, String etage5, String etage6) {
        brainScenarioState.setBuilding(
                new Building(Set.of(
                        new FloorIdentifier(etage1),
                        new FloorIdentifier(etage2),
                        new FloorIdentifier(etage3),
                        new FloorIdentifier(etage4),
                        new FloorIdentifier(etage5),
                        new FloorIdentifier(etage6)), 1));
        brainScenarioState.setLift(brainScenarioState.getLift());
    }

    @Et("un ascenseur situé à l'étage {string} avec un cerveau n'ayant pas de commandes en mémoire")
    public void unAscenseurSituéÀLÉtageAvecUnCerveauNayantPasDeCommandesEnMemoire(String etage) {
        brainScenarioState.getLift().setCurrentFloor(new FloorIdentifier(etage));
    }

    @Quand("une commande appel:{direction} de l'étage {string} est envoyé au cerveau")
    public void uneCommandeAppelDeLÉtageEstEnvoyeAuCerveau(Direction directionSouhaite ,String etage) {
       Optional<Monitor> monitorOfCaller = brainScenarioState
                .getBuilding().getMonitors()
                .stream().filter(
                        monitor -> monitor.floorIdentifier.label().equals(etage))
                .findFirst();

       assertThat(monitorOfCaller).isNotEmpty();
       monitorOfCaller.ifPresent(
               monitor ->
                       liftService.call(brainScenarioState.getLift(),
                               monitor,
                               directionSouhaite == UP? monitor.upFloorButton: monitor.downFloorButton)
       );
    }

    @Alors("le cerveau va ordonner d'aller à l'étage {string}")
    public void leCerveauVaOrdonnerDAllerÀLÉtage(String etageVoulu) {
        Lift lift = brainScenarioState.getLift();
        liftService.executeNextCommand(lift);
        assertThat(lift.getCurrentFloor().label()).isEqualTo(etageVoulu);
    }

    @ParameterType(value = "monter|descendre")
    public Direction direction(String value) {
        if (value.equals("monter")) {
            return UP;
        }
        return Direction.DOWN;
    }


}
