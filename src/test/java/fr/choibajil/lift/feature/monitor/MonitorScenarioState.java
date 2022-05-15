package fr.choibajil.lift.feature.monitor;

import fr.choibajil.lift.model.building.Building;
import fr.choibajil.lift.model.floor.Monitor;
import lombok.Data;

@Data
class MonitorScenarioState {

    private Building building;

    private Monitor currentMonitor;
}
