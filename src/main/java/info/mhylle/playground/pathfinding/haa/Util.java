package info.mhylle.playground.pathfinding.haa;

import java.util.*;

public class Util
{
  
  public static List<Tile> backtrace(Tile node) {
    List<Tile> result = new ArrayList<>();
    while (node.parent != null) {
      node = node.parent;
      result.add(node);
    }
    Collections.reverse(result);
    return result;
  }
}
