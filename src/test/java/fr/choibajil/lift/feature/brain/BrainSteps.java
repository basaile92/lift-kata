package fr.choibajil.lift.feature.brain;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.common.Direction;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.model.lift.LiftButton;
import fr.choibajil.lift.service.LiftService;
import io.cucumber.java.ParameterType;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static fr.choibajil.lift.model.common.Direction.DOWN;
import static fr.choibajil.lift.model.common.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

public class BrainSteps {
    @Autowired
    private BrainScenarioState brainScenarioState;

    @Autowired
    private LiftService liftService;

    @Etantdonné("un building avec les étages ordonnés:{string},{string},{string},{string},{string},{string}")
    public void unBuildingAvecLesÉtagesOrdonnés(final String etage1, final String etage2, final String etage3, final String etage4, final String etage5, final String etage6) {
        final LinkedHashSet<FloorIdentifier> floorSet = new LinkedHashSet<>();
        floorSet.add(new FloorIdentifier(etage1));
        floorSet.add(new FloorIdentifier(etage2));
        floorSet.add(new FloorIdentifier(etage3));
        floorSet.add(new FloorIdentifier(etage4));
        floorSet.add(new FloorIdentifier(etage5));
        floorSet.add(new FloorIdentifier(etage6));

        brainScenarioState.setBuilding(new Building(floorSet, 1));
        brainScenarioState.setLift(brainScenarioState.getBuilding().getLifts().get(0));
    }

    @Etantdonné("un ascenseur situé à l'étage {string} qui monte avec un cerveau n'ayant pas de commandes en mémoire")
    public void unAscenseurSituéÀLÉtageQuiMonteAvecUnCerveauNayantPasDeCommandesEnMemoire(final String etage) {
        brainScenarioState.getLift().setCurrentFloor(new FloorIdentifier(etage));
        brainScenarioState.getLift().setCurrentDirection(UP);
    }

    @Etantdonné("un ascenseur situé à l'étage {string} qui descend avec un cerveau n'ayant pas de commandes en mémoire")
    public void unAscenseurSituéÀLÉtageQuiDescendAvecUnCerveauNayantPasDeCommandesEnMemoire(final String etage) {
        brainScenarioState.getLift().setCurrentFloor(new FloorIdentifier(etage));
        brainScenarioState.getLift().setCurrentDirection(DOWN);
    }

    @Quand("une commande appel:{direction} de l'étage {string} est envoyé au cerveau")
    public void uneCommandeAppelDeLÉtageEstEnvoyeAuCerveau(final Direction directionSouhaite, final String etage) {
        final Optional<Monitor> monitorOfCaller = brainScenarioState
                .getBuilding().getMonitors()
                .stream().filter(
                        monitor -> monitor.floorIdentifier.label().equals(etage))
                .findFirst();

        assertThat(monitorOfCaller).isNotEmpty();
        monitorOfCaller.ifPresent(
                monitor ->
                        liftService.call(brainScenarioState.getLift(),
                                monitor,
                                directionSouhaite == UP ? monitor.upFloorButton : monitor.downFloorButton)
        );
    }

    @Quand("une commande aller:à l'étage {string} est envoyé au cerveau")
    public void uneCommandeAllerDeLÉtageEstEnvoyeAuCerveau(final String etage) {
        final Optional<FloorIdentifier> floorWished = brainScenarioState
                .getLift().getAvailableFloors()
                .stream().filter(
                        floorIdentifier -> floorIdentifier.label().equals(etage))
                .findFirst();

        assertThat(floorWished).isNotEmpty();
        floorWished.ifPresent(
                floor ->
                        liftService.go(brainScenarioState.getLift(), new LiftButton(floor))
        );
    }

    @Alors("le cerveau va ordonner d'aller à l'étage {string}")
    public void leCerveauVaOrdonnerDAllerÀLÉtage(final String etageVoulu) {
        final Lift lift = brainScenarioState.getLift();
        liftService.executeNextCommand(lift);
        assertThat(lift.getCurrentFloor().label()).isEqualTo(etageVoulu);
    }

    @ParameterType(value = "monter|descendre")
    public Direction direction(final String value) {
        if (value.equals("monter")) {
            return UP;
        }
        return Direction.DOWN;
    }


    @Alors("le cerveau n'a plus rien à faire")
    public void leCerveauNAPlusRienÀFaire() {
        assertThat(brainScenarioState.getLift().getLiftCommands()).isEmpty();
    }
}
