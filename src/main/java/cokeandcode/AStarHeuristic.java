package cokeandcode;

public interface AStarHeuristic {

    float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty);
}
