package cokeandcode;

public interface TileBasedMap {
    int getWidthInTiles();

    int getHeightInTiles();

    boolean blocked(Mover mover, int x, int y);

    float getCost(Mover mover, int sx, int sy, int tx, int ty);

    void pathFinderVisited(int x, int y);
}
