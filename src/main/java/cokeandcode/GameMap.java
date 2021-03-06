package cokeandcode;

public class GameMap implements TileBasedMap {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    enum TerrainType {
        GRASS, WATER, TREES, PLANE, BOAT, TANK
    }

    private TerrainType[][] terrain = new TerrainType[WIDTH][HEIGHT];
    private TerrainType[][] units = new TerrainType[WIDTH][HEIGHT];

    private boolean[][] visited = new boolean[WIDTH][HEIGHT];

    GameMap() {

        fillArea(0,0,5,20,TerrainType.GRASS);
        fillArea(5,5,5,5,TerrainType.GRASS);
        fillArea(10,10,5,5,TerrainType.GRASS);
        fillArea(15,10,5,5,TerrainType.GRASS);
        fillArea(20,10,5,5,TerrainType.GRASS);

        fillArea(21,7,3,3,TerrainType.TREES);
//        fillArea(21,5,2,2,TerrainType.GRASS);
        fillArea(17,5,6,2,TerrainType.GRASS);
        fillArea(17,7,2,3,TerrainType.GRASS);

//        fillArea(20,5,5,5,TerrainType.GRASS);
//        fillArea(0,5,3,10,TerrainType.WATER);
//        fillArea(0,15,7,15,TerrainType.WATER);
//        fillArea(7,26,22,4,TerrainType.WATER);
//
//        fillArea(17,5,10,3,TerrainType.TREES);
//        fillArea(20,8,5,3,TerrainType.TREES);
//        fillArea(8,2,7,3,TerrainType.TREES);
//        fillArea(10,5,3,3,TerrainType.TREES);
//        fillArea(10,5,3,3,TerrainType.TREES);

        units[23][7] = TerrainType.PLANE;
//        units[2][7] = TerrainType.BOAT;
//        units[20][25] = TerrainType.PLANE;
    }

    private void fillArea(int x, int y, int width, int height, TerrainType type) {
        for (int xp = x; xp<x + width; xp++) {
            for (int yp = y; yp < y+height;yp++) {
                terrain[xp][yp] = type;
            }
        }
    }

    void clearVisited() {
        for  (int x = 0; x <getWidthInTiles();x++) {
            for (int y = 0; y < getHeightInTiles(); y++) {
                visited[x][y] = false;
            }
        }
    }

    public boolean visited(int x, int y) {
        return visited[x][y];
    }

    TerrainType getTerrain(int x, int y){
        return terrain[x][y];
    }

    TerrainType getUnit(int x, int y) {
        return units[x][y];
    }

    void setUnit(int x, int y, TerrainType unit) {
        units[x][y] = unit;
    }

    public boolean blocked(Mover mover, int x, int y) {
        if (getUnit(x, y) != null) {
            return true;
        }

        TerrainType unit = ((UnitMover) mover).getType();

        if (unit == TerrainType.PLANE) {
            return false;
        }

        if (unit == TerrainType.TANK) {
            return terrain[x][y] != TerrainType.GRASS;
        }

        return unit != TerrainType.BOAT || terrain[x][y] != TerrainType.WATER;

    }

    public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
        if (terrain[sx] != null) {
            TerrainType terrainType = terrain[sx][sy];
            if (terrainType == null) {
                return 10000;
            }
            if (terrainType.equals(TerrainType.GRASS)) {
                return 5;
            }

            if (terrainType.equals(TerrainType.TREES)) {
                return 6;
            }
            if (terrainType.equals(TerrainType.WATER)) {
                return 8;
            }

        }
        return 2;
    }

    public int getHeightInTiles() {
        return HEIGHT;
    }

    public int getWidthInTiles() {
        return WIDTH;
    }

    public void pathFinderVisited(int x, int y) {
        visited[x][y] = true;
    }
}
