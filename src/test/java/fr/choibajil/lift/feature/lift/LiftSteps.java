package fr.choibajil.lift.feature.lift;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.common.command.GoCommand;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.lift.Lift;
import fr.choibajil.lift.model.lift.LiftButton;
import fr.choibajil.lift.service.LiftService;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Étantdonné;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class LiftSteps {

    @Autowired
    private LiftScenarioState liftScenarioState;

    @Autowired
    private LiftService liftService;

    @Étantdonné("un immeuble de cinq étages")
    public void un_immeuble_de_cinq_etages() {
        LinkedHashSet<FloorIdentifier> floors = new LinkedHashSet<>();
        floors.add(new FloorIdentifier("0"));
        floors.add(new FloorIdentifier("1"));
        floors.add(new FloorIdentifier("2"));
        floors.add(new FloorIdentifier("3"));
        floors.add(new FloorIdentifier("4"));
        liftScenarioState.setBuilding(
                new Building(floors,
                        1));
    }

    @Étantdonné("un ascenseur situé au premier étage")
    public void un_ascenseur_situé_au_premier_étage() {
        liftScenarioState.getBuilding().getLifts().get(0).setCurrentFloor(new FloorIdentifier("1"));
    }

    @Étantdonné("un ascenseur situé au deuxième étage")
    public void unAscenseurSituéAuDeuxièmeÉtage() {
        liftScenarioState.getBuilding().getLifts().get(0).setCurrentFloor(new FloorIdentifier("1"));
    }

    @Étantdonné("un ascenseur se déplaçant au premier étage")
    public void unAscenseurSeDéplaçantAuPremierÉtage() {
        LinkedHashSet<FloorIdentifier> floors = new LinkedHashSet<>();
        floors.add(new FloorIdentifier("0"));
        floors.add(new FloorIdentifier("1"));
        floors.add(new FloorIdentifier("2"));
        floors.add(new FloorIdentifier("3"));
        floors.add(new FloorIdentifier("4"));

        liftScenarioState.getBuilding().setLifts(singletonList(
                spy(new Lift(new FloorIdentifier("1"), floors))));
        liftScenarioState.getBuilding().getLifts().get(0).addLiftCommand(GoCommand.of(new FloorIdentifier("1")));

    }


    @Quand("j'appuie sur le bouton 'premier étage' de l'ascenseur")
    public void jAppuieSurLeBoutonPremierEtageDeLAscenseur() {
        liftService.go(liftScenarioState.getBuilding().getLifts().get(0), new LiftButton(new FloorIdentifier("1")));
    }

    @Quand("j'appuie sur le bouton 'deuxième étage' de l'ascenseur")
    public void jAppuieSurLeBoutonDeuxiemeEtageDeLAscenseur() {
        liftService.go(liftScenarioState.getBuilding().getLifts().get(0), new LiftButton(new FloorIdentifier("2")));
    }

    @Quand("je regarde l'étage de l'ascenseur sur l'écran")
    public void jeRegardeLÉtageDeLAscenseurSurLÉcran() {
    }

    @Quand("l'ascenseur arrive au premier étage")
    public void lAscenseurArriveAuPremierÉtage() {
        liftScenarioState.getBuilding().getLifts().get(0).executeAllCommands();
    }

    @Alors("l'ascenseur reçoit une demande pour aller au deuxième étage")
    public void lAscenseurReçoitUneDemandePourAllerAuDeuxièmeÉtage() {
        assertThat(liftScenarioState.getBuilding().getLifts().get(0).getLiftCommands()).containsExactly(GoCommand.of(new FloorIdentifier("2")));
    }

    @Alors("l'ascenseur reçoit une demande pour aller au premier étage")
    public void lAscenseurReçoitUneDemandePourAllerAuPremierÉtage() {
        assertThat(liftScenarioState.getBuilding().getLifts().get(0).getLiftCommands()).containsExactly(GoCommand.of(new FloorIdentifier("1")));
    }

    @Alors("l'écran indique {string}")
    public void lÉcranMIndique(final String floorNumber) {
        assertThat(liftScenarioState.getBuilding().getLifts().get(0).getCurrentFloor()).isEqualTo(new FloorIdentifier(floorNumber));
    }

    @Alors("l'ascenseur fait: DING!")
    public void lAscenseurFaitDING() {
        verify(liftScenarioState.getBuilding().getLifts().get(0)).ding();
    }

    @Et("les portes de l'ascenseur s'ouvre et se referme")
    public void lesPortesDeLAscenseurSOuvreEtSeReferme() {
        verify(liftScenarioState.getBuilding().getLifts().get(0)).openDoor();
        verify(liftScenarioState.getBuilding().getLifts().get(0)).closeDoor();

    }
}
