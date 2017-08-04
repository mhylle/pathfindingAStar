package cokeandcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStarPathFinder implements PathFinder {

    private List<Node> closed = new ArrayList<>();
    private SortedList open = new SortedList();

    private TileBasedMap map;
    private int maxSearchDistance;
    private Node[][] nodes;

    private boolean allowDiagonalMovement;
    private AStarHeuristic heuristic;


    AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagonalMovement) {
        this(map, maxSearchDistance, allowDiagonalMovement, new ClosestHeuristic());
    }

    private AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagonalMovement, AStarHeuristic heuristic) {
        this.heuristic = heuristic;
        this.map = map;
        this.maxSearchDistance = maxSearchDistance;
        this.allowDiagonalMovement = allowDiagonalMovement;

        nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
        for (int i = 0; i < map.getWidthInTiles(); i++) {
            for (int j = 0; j < map.getHeightInTiles(); j++) {
                nodes[i][j] = new Node(i, j);
            }

        }
    }

    @Override
    public Path findPath(Mover mover, int sx, int sy, int tx, int ty) {
        if (map.blocked(mover, tx, ty)) {
            return null;
        }

        nodes[sx][sy].cost = 0;
        nodes[sx][sy].depth = 0;
        closed.clear();
        open.clear();
        open.add(nodes[sx][sy]);
        nodes[tx][ty].parent = null;

        int maxDepth = 0;

        while ((maxDepth < maxSearchDistance) && open.size() != 0) {
            Node currentNode = getFirstInOpen();
            if (currentNode == nodes[tx][ty]) {
                break;
            }

            removeFromOpen(currentNode);
            addToClosed(currentNode);

            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    if ((x == 0) && (y == 0)) {
                        // ourself
                        continue;
                    }

                    if (!allowDiagonalMovement) {
                        if ((x != 0) && (y != 0)) {
                            continue;
                        }
                    }

                    int xp = x + currentNode.x;
                    int yp = y + currentNode.y;

                    if (isValidLocation(mover, sx, sy, xp, yp)) {
                        float nextStepCost = currentNode.cost + getMovementCost(mover, currentNode.x, currentNode.y, xp, yp);
                        Node neighbour = nodes[xp][yp];
                        map.pathFinderVisited(xp, yp);

                        if (nextStepCost < neighbour.cost) {
                            if (inOpenList(neighbour)) {
                                removeFromOpen(neighbour);
                            }
                            if (inClosedList(neighbour)) {
                                removeFromClosed(neighbour);
                            }
                        }

                        if (!inOpenList(neighbour) && !inClosedList(neighbour)) {
                            neighbour.cost = nextStepCost;
                            neighbour.heuristic = getHeuristicCose(mover, xp, yp, tx, ty);
                            maxDepth = Math.max(maxDepth, neighbour.setParent(currentNode));
                            addToOpen(neighbour);
                        }
                    }
                }
            }
        }
        if (nodes[tx][ty].parent == null) {
            return null;
        }

        Path path = new Path();
        Node target = nodes[tx][ty];
        while (target != nodes[sx][sy]) {
            path.prependStep(target.x, target.y);
            target = target.parent;
        }
        path.prependStep(sx, sy);
        return path;
    }

    private Node getFirstInOpen() {
        return open.first();
    }

    private void addToOpen(Node node) {
        open.add(node);
    }

    private boolean inOpenList(Node node) {
        return open.contains(node);
    }

    private void removeFromOpen(Node node) {
        open.remove(node);
    }

    private void addToClosed(Node node) {
        closed.add(node);
    }


    private boolean inClosedList(Node node) {
        return closed.contains(node);
    }

    private void removeFromClosed(Node node) {
        closed.remove(node);
    }

    private boolean isValidLocation(Mover mover, int sx, int sy, int x, int y) {
        boolean invalid = (x < 0) || (y < 0) || (x > map.getWidthInTiles()) || (y > map.getHeightInTiles());
        if ((!invalid) && ((sx != x) || (sy != y))) {
            invalid = map.blocked(mover, x, y);
        }
        return !invalid;
    }

    private float getMovementCost(Mover mover, int sx, int sy, int tx, int ty) {
        return map.getCost(mover, sx, sy, tx, ty);
    }

    private float getHeuristicCose(Mover mover, int x, int y, int tx, int ty) {
        return heuristic.getCost(map, mover, x, y, tx, ty);
    }


    private class SortedList {
        private List<Node> list = new ArrayList<>();

        Node first() {
            return list.get(0);
        }

        void clear() {
            list.clear();
        }

        void add(Node o) {
            list.add(o);
            Collections.sort(list);
        }

        void remove(Node o) {
            list.remove(o);
        }

        int size() {
            return list.size();
        }

        boolean contains(Node o) {
            return list.contains(o);
        }
    }

    private class Node implements Comparable {
        private int x;
        private int y;
        private float cost;
        private Node parent;
        private float heuristic;
        private int depth;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int setParent(Node parent) {
            depth = parent.depth + 1;
            this.parent = parent;
            return depth;
        }

        @Override
        public int compareTo(Object other) {
            Node o = (Node) other;

            float f = heuristic + cost;
            float of = o.heuristic + o.cost;

            return Float.compare(f, of);
        }
    }
}
