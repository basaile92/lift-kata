package fr.choibajil.lift.model.floor;

import fr.choibajil.lift.model.common.Direction;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
@ToString
public
class Monitor {

    public final FloorIdentifier floorIdentifier;
    public final FloorButton upFloorButton = new FloorButton(Direction.UP);
    public final FloorButton downFloorButton = new FloorButton(Direction.DOWN);
}
