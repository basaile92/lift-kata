package fr.choibajil.lift.feature.building;

import fr.choibajil.lift.model.building.Building;
import lombok.Data;

@Data
class BuildingScenarioState {

    private Building building;

    private int numberOfLift;
}
