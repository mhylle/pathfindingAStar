package info.mhylle.playground.pathfinding.haa;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

public class Visualizer
{
  
  private Level level;
  private JPanel mainPanel;
  
  public Visualizer(Level level)
  {
    this.level = level;
  }
  
  public void show()
  {
    JFrame frame = new JFrame();
    frame.setSize(100, 100);
    mainPanel = new JPanel(null);
    frame.add(mainPanel);
    frame.addWindowListener(new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    });
    
    Tile[][] tiles = level.getTiles();
    for (Tile[] row : tiles) {
      for (Tile tile : row) {
        JPanel tilePanel = new JPanel();
        tilePanel.setLocation(tile.x, tile.y);
        tilePanel.setSize(tile.width, tile.height);
        tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        if (tile.blocked) {
          tilePanel.setBackground(Color.BLACK);
        } else {
          tilePanel.setBackground(Color.blue);
        }
        mainPanel.add(tilePanel);
      }
    }
    frame.setVisible(true);
  }
  
  public void showPath(List<Tile> path)
  {
    if (path == null) {
      return;
    }
    for (Tile tile : path) {
      JPanel tilePanel = new JPanel();
      tilePanel.setLocation(tile.x, tile.y);
      tilePanel.setSize(tile.width, tile.height);
      tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
      tilePanel.setBackground(Color.red);
      mainPanel.add(tilePanel);
    }
  }
}
