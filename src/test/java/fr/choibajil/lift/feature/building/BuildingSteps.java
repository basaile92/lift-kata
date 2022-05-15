package fr.choibajil.lift.feature.building;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Étantdonné;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class BuildingSteps {

    @Autowired
    private BuildingScenarioState buildingScenarioState;

    @Étantdonné("un immeuble de cinq étages")
    public void un_immeuble_de_cinq_etages() {
        buildingScenarioState.setBuilding(Building
                .builder()
                .monitors(Set.of(
                        Monitor.of(new FloorIdentifier("0")),
                        Monitor.of(new FloorIdentifier("1")),
                        Monitor.of(new FloorIdentifier("2")),
                        Monitor.of(new FloorIdentifier("3")),
                        Monitor.of(new FloorIdentifier("4"))
                ))
                .build());
    }

    @Étantdonné("un ascenseur situé au premier étage")
    public void un_ascenseur_situé_au_premier_étage() {
        buildingScenarioState.getBuilding().setLifts(singletonList(Lift.of((new FloorIdentifier("1")), emptyList())));
    }

    @Étantdonné("deux ascenseurs situé au premier étage")
    public void deuxAscenseursSituéAuPremierÉtage() {
        buildingScenarioState.getBuilding().setLifts(asList(Lift.of((new FloorIdentifier("1")), emptyList()), Lift.of((new FloorIdentifier("1")), emptyList())));
    }

    @Quand("je cherche des ascenseurs")
    public void jeChercheDesAscenseurs() {
        buildingScenarioState.setNumberOfLift(buildingScenarioState.getBuilding().getLifts().size());
    }

    @Alors("je trouve l'ascenseur")
    public void jeTrouveLAscenseur() {
        assertThat(buildingScenarioState.getNumberOfLift()).isEqualTo(1);
    }

    @Alors("je trouve les deux ascenseurs")
    public void jeTrouveLesDeuxAscenseurs() {
        assertThat(buildingScenarioState.getNumberOfLift()).isEqualTo(2);
    }
}
