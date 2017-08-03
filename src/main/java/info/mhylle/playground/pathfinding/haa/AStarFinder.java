package info.mhylle.playground.pathfinding.haa;

import java.util.*;

// https://github.com/qiao/PathFinding.js/blob/master/src/finders/AStarFinder.js
public class AStarFinder
{
  
  public List findPath(int startX, int startY, int endX, int endY, Level level) {
    List<Tile> openList = new ArrayList();
  
    Tile startNode = level.getTiles()[startX][startY];
    Tile endNode= level.getTiles()[endX][endY];
    int g = 0;
    int f = 0;
    openList.add(startNode);
    
    while (!openList.isEmpty()) {
      Tile node = openList.remove(openList.size());
      
      if (node.equals(endNode)) {
        // return the path..
      }
  
      List<Tile> neighbours = level.getNeighbours(node, true);
      for (Tile neighbour : neighbours) {
      
      }
    }
  
    return null;
  }
}
