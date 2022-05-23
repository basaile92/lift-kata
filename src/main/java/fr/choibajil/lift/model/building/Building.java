package fr.choibajil.lift.model.building;

import fr.choibajil.lift.model.floor.FloorIdentifier;
import fr.choibajil.lift.model.floor.Monitor;
import fr.choibajil.lift.model.lift.Lift;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Getter
public class Building {

    @NonNull
    private final Set<Monitor> monitors;

    @Setter
    private List<Lift> lifts;

    public Building(final Set<FloorIdentifier> floors, final int numberOfLifts) {
        this.monitors = floors.stream().map(Monitor::of).collect(Collectors.toSet());
        this.lifts = IntStream.range(0, numberOfLifts).mapToObj(i -> new Lift(floors.stream().toList().get(0), floors)).collect(toList());
    }

    public Optional<Monitor> monitorOf(final FloorIdentifier floorIdentifier) {
        return monitors.stream()
                .filter(monitor -> monitor.floorIdentifier.equals(floorIdentifier))
                .findFirst();
    }

}
