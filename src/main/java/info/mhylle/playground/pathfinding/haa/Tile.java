package info.mhylle.playground.pathfinding.haa;

public class Tile
{
  int x;
  int y;
  int width;
  int height;
  int depth;
  boolean blocked = false;
  
  boolean allowDiagonals = true;
  int[][] neighbours = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
  
  private void calculateNeighbours()
  {
    for (int i = 0; i < (allowDiagonals ? 8 : 4); ++i) {
      if (x + neighbours[i][0] < 0 || x + neighbours[i][0] > 19) {
        continue;
      }
      if (y + neighbours[i][1] < 0 || y + neighbours[i][1] > 19) {
        continue;
      }
      
      
    }
  }
}
