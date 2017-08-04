package info.mhylle.playground.pathfinding.haa;

public class Tile
{
  int x;
  int y;
  int width;
  int height;
  int depth;
  boolean blocked = false;
  
  boolean opened;
  double g;
  double f;
  Tile parent;
  
  boolean allowDiagonals = true;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tile tile = (Tile) o;

    if (x != tile.x) return false;
    return y == tile.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}
