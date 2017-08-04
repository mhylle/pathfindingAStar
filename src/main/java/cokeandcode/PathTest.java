package cokeandcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class PathTest extends JFrame {

    private GameMap map = new GameMap();
    private PathFinder finder;
    private Path path;

    private int selectedX = -1;
    private int selectedY = -1;
    private int lastFindX = -1;
    private int lastFindY = -1;


    private String[] tiles = new String[6];
    /** The offscreen buffer used for rendering in the wonder world of Java 2D */
    private Image buffer;

    private PathTest() {
        super(("Path finding Example"));

        // create tiles..
        tiles[GameMap.TerrainType.TREES.ordinal()] = "T";
        tiles[GameMap.TerrainType.GRASS.ordinal()] = "G";
        tiles[GameMap.TerrainType.WATER.ordinal()] = "W";
        tiles[GameMap.TerrainType.TANK.ordinal()] = "K";
        tiles[GameMap.TerrainType.PLANE.ordinal()] = "P";
        tiles[GameMap.TerrainType.BOAT.ordinal()] = "B";

        finder = new AStarPathFinder(map, 500, true);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMoved(e.getX(), e.getY());
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(600,600);
        setResizable(false);
        setVisible(true);
    }

    private void handleMouseMoved(int x, int y) {
        x-=50;
        y -= 50;
        x/= 16;
        y/= 16;

        if ((x <0) || (y<0) ||(x> map.getWidthInTiles()) || (y >map.getHeightInTiles())) {
            return;
        }

        if (selectedX != -1) {
            if ((lastFindX != x) ||(lastFindY != y)) {
                lastFindX = x;
                lastFindY = y;
                path = finder.findPath(new UnitMover(map.getUnit(selectedX, selectedY)), selectedX, selectedY, x,y );
                repaint(0);
            }
        }
    }

    private void handleMousePressed(int x, int y) {
        System.out.println("Mouse Pressed at " + x + ", " + y);
        x -= 50;
        y -= 50;
        x /= 16;
        y /= 16;
        System.out.println("Converted Mouse Pressed at " + x + ", " + y);

        if ((x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles())) {
            System.out.println("Outside map at at " + x + ", " + y);
            return;
        }

        if (map.getUnit(x, y) != null) {
            System.out.println("Found unit at " + x + ", " + y);
            selectedX = x;
            selectedY = y;
            lastFindX = - 1;
        } else {
            if (selectedX != -1) {
                map.clearVisited();
                path = finder.findPath(new UnitMover(map.getUnit(selectedX, selectedY)),
                        selectedX, selectedY, x, y);

                if (path != null) {
                    path = null;
                    GameMap.TerrainType unit = map.getUnit(selectedX, selectedY);
                    map.setUnit(selectedX, selectedY, null);
                    map.setUnit(x,y,unit);
                    selectedX = x;
                    selectedY = y;
                    lastFindX = - 1;
                }
            }
        }

        repaint(0);
    }

    public void paint(Graphics graphics){
        if (buffer == null) {
            buffer = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        }
        Graphics g = buffer.getGraphics();

        g.clearRect(0,0,600,600);
        g.translate(50, 50);

        for (int x=0;x<map.getWidthInTiles();x++) {
            for (int y=0;y<map.getHeightInTiles();y++) {
                GameMap.TerrainType terrain = map.getTerrain(x, y);
                if (terrain != null) {
                    String tile = tiles[terrain.ordinal()];
                    g.drawString(tile, x * 16, y * 16);
                }
                if (map.getUnit(x, y) != null) {
                    g.drawString(tiles[map.getUnit(x, y).ordinal()],x*16,y*16);
                } else {
                    if (path != null) {
                        if (path.contains(x, y)) {
                            g.setColor(Color.blue);
                            g.fillRect((x*16)+4, (y*16)+4,7,7);
                        }
                    }
                }
            }
        }

        if (selectedX != -1) {
            g.setColor(Color.black);
            g.drawRect(selectedX *16, selectedY *16, 15, 15);
            g.drawRect((selectedX *16)-2, (selectedY *16)-2, 19, 19);
            g.setColor(Color.white);
            g.drawRect((selectedX *16)-1, (selectedY *16)-1, 17, 17);
        }
        graphics.drawImage(buffer, 0, 0, null);
    }

    public static void main(String[] args) {
        new PathTest();
    }
}
