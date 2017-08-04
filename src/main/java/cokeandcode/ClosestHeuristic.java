package cokeandcode;

public class ClosestHeuristic implements AStarHeuristic {
    @Override
    public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty) {
        float dx = tx - x;
        float dy = ty - y;
        return (float) ((0.05) * (Math.sqrt((dx * dx) + (dy * dy))));
    }
}
