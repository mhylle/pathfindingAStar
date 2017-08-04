package cokeandcode;

class UnitMover implements Mover {

    private GameMap.TerrainType type;

    UnitMover(GameMap.TerrainType type) {
        this.type = type;
    }

    GameMap.TerrainType getType() {
        return type;
    }
}
