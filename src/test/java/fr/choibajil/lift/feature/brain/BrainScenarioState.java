package fr.choibajil.lift.feature.brain;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.lift.Lift;
import lombok.Data;

@Data
class BrainScenarioState {
    private Building building;

    private Lift lift;
}
