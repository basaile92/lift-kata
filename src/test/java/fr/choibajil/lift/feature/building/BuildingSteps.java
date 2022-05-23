package fr.choibajil.lift.feature.building;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Étantdonné;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class BuildingSteps {

    @Autowired
    private BuildingScenarioState buildingScenarioState;

    @Étantdonné("un immeuble de cinq étages avec un ascenseur")
    public void un_immeuble_de_cinq_etages_avec_un_ascenseur() {
        buildingScenarioState.setBuilding(
                new Building(new HashSet<>(asList(
                        new FloorIdentifier("0"),
                        new FloorIdentifier("1"),
                        new FloorIdentifier("2"),
                        new FloorIdentifier("3"),
                        new FloorIdentifier("4"))),
                        1));
    }

    @Étantdonné("un immeuble de cinq étages avec deux ascenseurs")
    public void un_immeuble_de_cinq_etages_avec_deux_ascenseurs() {
        buildingScenarioState.setBuilding(
                new Building(new HashSet<>(asList(
                        new FloorIdentifier("0"),
                        new FloorIdentifier("1"),
                        new FloorIdentifier("2"),
                        new FloorIdentifier("3"),
                        new FloorIdentifier("4"))),
                        2));
    }

    @Quand("je cherche des ascenseurs")
    public void jeChercheDesAscenseurs() {
    }

    @Alors("je trouve l'ascenseur")
    public void jeTrouveLAscenseur() {
        assertThat(buildingScenarioState.getBuilding().getLifts()).hasSize(1);
    }

    @Alors("je trouve les deux ascenseurs")
    public void jeTrouveLesDeuxAscenseurs() {
        assertThat(buildingScenarioState.getBuilding().getLifts()).hasSize(2);
    }
}
