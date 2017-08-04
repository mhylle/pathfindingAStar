package cokeandcode;

public class UnitMover implements Mover {

    private GameMap.TerrainType type;

    public UnitMover(GameMap.TerrainType type) {
        this.type = type;
    }

    public GameMap.TerrainType getType() {
        return type;
    }
}
