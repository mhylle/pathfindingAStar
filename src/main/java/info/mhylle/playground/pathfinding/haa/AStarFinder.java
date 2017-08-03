package info.mhylle.playground.pathfinding.haa;

import java.util.*;

// https://github.com/qiao/PathFinding.js/blob/master/src/finders/AStarFinder.js
public class AStarFinder
{
  
  static double SQRT2 = Math.sqrt(2);
  
  public List findPath(int startX, int startY, int endX, int endY, Level level) {
    List<Tile> openList = new ArrayList();
  
    Tile startNode = level.getTiles()[startX][startY];
    Tile endNode= level.getTiles()[endX][endY];
    startNode.g = 0;
    startNode.f = 0;
    startNode.opened = true;
    openList.add(startNode);
    
    while (!openList.isEmpty()) {
      Tile node = openList.remove(openList.size()-1);
      node.opened = false;
      if (node.equals(endNode)) {
        return Util.backtrace(node);
      }
  
      List<Tile> neighbours = level.getNeighbours(node, true);
      for (Tile neighbour : neighbours) {
        if (neighbour.opened) {
          continue;
        }
        
        double ng = node.g + ((neighbour.x - node.x == 0 || neighbour.y - node.y == 0) ? 1:SQRT2);
        
        if (!neighbour.opened || ng < neighbour.g) {
          neighbour.g = ng;
          neighbour.f = neighbour.g +1;
          neighbour.parent = node;
          
          if (!neighbour.opened) {
            openList.add(neighbour);
            neighbour.opened = true;
          }
        }
      }
    }
  
    return null;
  }
}
