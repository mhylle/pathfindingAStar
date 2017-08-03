package info.mhylle.playground.pathfinding;

import java.util.*;

import info.mhylle.playground.pathfinding.haa.*;

public class PathFinder
{
  private List<Tile> openList;
  private List<Tile> closedList;
  private Level level;
  
  private Tile startTile;
  private Tile targetTile;
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
    Visualizer v = new Visualizer(level);
    v.show();
    generatePath();
  }
  
  private void generatePath()
  {
    Tile[][] tiles = level.getTiles();
    openList.add(tiles[0][0]);
  }
}
