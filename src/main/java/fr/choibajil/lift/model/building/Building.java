package fr.choibajil.lift.model.building;

import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Builder
@Data
public class Building {

    @NonNull
    private Set<Monitor> monitors;

    private List<Lift> lifts;

    public Optional<Monitor> monitorOf(final FloorIdentifier floorIdentifier) {
        return monitors.stream()
                .filter(monitor -> monitor.floorIdentifier.equals(floorIdentifier))
                .findFirst();
    }
}
