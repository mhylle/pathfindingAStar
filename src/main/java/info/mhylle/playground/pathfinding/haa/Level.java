package info.mhylle.playground.pathfinding.haa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {

  private int width;
  private int height;

  private Tile[][] tiles;

  public Level(
      int width,
      int height) {
    this.width = width;
    this.height = height;
  }

  public void createBaseLevel() {
    tiles = new Tile[width / 10][];

    for (int i = 0; i < width / 10; i++) {
      tiles[i] = new Tile[height / 10];
      for (int j = 0; j < height / 10; j++) {
        Tile t = new Tile();
        t.x = i * 10;
        t.y = j * 10;
        t.width = 10;
        t.height = 10;
        Random rnd = new Random();
        t.depth = rnd.nextInt(5);
        tiles[i][j] = t;
        double chanceOfBlocked = rnd.nextDouble();
        if (i == 0 && j == 0) {
          continue;
        }
        if (chanceOfBlocked < 0.2) {
          t.blocked = true;
        }
      }
    }
  }


  // https://github.com/qiao/PathFinding.js/blob/master/src/core/Grid.js
  public List<Tile> getNeighbours(
      Tile tile,
      boolean useDiagonal) {
    int x = tile.x / 10;
    int y = tile.y / 10;
    List<Tile> neighbours = new ArrayList<>();
    boolean s0, s1, s2, s3 = false;
    boolean d0, d1, d2, d3 = true;

    if (y != 0 && !tiles[x][y - 1].blocked) {
      neighbours.add(tiles[x][y - 1]);
      s0 = true;
    }
    if (x < width &&!tiles[x + 1][y].blocked) {
      neighbours.add(tiles[x + 1][y]);
      s1 = true;
    }
    if (y < height && !tiles[x][y + 1].blocked) {
      neighbours.add(tiles[x][y + 1]);
      s2 = true;
    }
    if (x != 0 && !tiles[x - 1][y].blocked) {
      neighbours.add(tiles[x - 1][y]);
      s3 = true;
    }

    if (useDiagonal) {
      if (x != 0 && y != 0 && !tiles[x - 1][y - 1].blocked) {
        neighbours.add(tiles[x - 1][y - 1]);
      }
      if (x < width && y != 0 && !tiles[x + 1][y - 1].blocked) {
        neighbours.add(tiles[x + 1][y - 1]);
      }
      if (x < width && y < height && !tiles[x + 1][y + 1].blocked) {
        neighbours.add(tiles[x + 1][y + 1]);
      }
      if (x != 0 && y < height && !tiles[x - 1][y + 1].blocked) {
        neighbours.add(tiles[x - 1][y + 1]);
      }
    }

    return neighbours;
  }

  public Tile[][] getTiles() {
    return tiles;
  }
}
