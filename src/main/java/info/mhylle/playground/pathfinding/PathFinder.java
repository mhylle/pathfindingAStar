package info.mhylle.playground.pathfinding;

import java.util.*;

import info.mhylle.playground.pathfinding.haa.*;

/**
 * Test implementations of different pathfinding algorithms:
 * Sites that help:
 * http://theory.stanford.edu/~amitp/GameProgramming/ImplementationNotes.html#source-code-and-demos
 * https://github.com/qiao/PathFinding.js
 */
public class PathFinder
{
  private List<Tile> openList;
  private List<Tile> closedList;
  private Level level;
  
  private Tile startTile;
  private Tile targetTile;
  private Visualizer visualizer;
  
  public PathFinder()
  {
    openList = new ArrayList<>();
    closedList = new ArrayList<>();
  }
  
  public static void main(String[] args)
  {
    PathFinder p = new PathFinder();
    p.start();
    
  }
  
  private void start()
  {
    level = new Level(400,400);
    level.createBaseLevel();
    Tile[][] tiles = level.getTiles();
    startTile = tiles[0][0];
    Tile[] endRow = tiles[tiles.length - 1];
    targetTile = endRow[endRow.length-1];
    visualizer = new Visualizer(level);
    visualizer.show();
    generatePath();
  }
  
  private void generatePath()
  {
    AStarFinder aStarFinder = new AStarFinder();
    List<Tile> path = aStarFinder.findPath(1, 1, 20, 20, level);
    visualizer.showPath(path);
  }
}
