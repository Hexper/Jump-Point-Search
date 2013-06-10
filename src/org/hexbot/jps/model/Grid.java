package org.hexbot.jps.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Kyle Stevenson
 *         Date: 6/9/13
 *         Time: 11:15 PM
 */
public class Grid {
    private final Node[][] nodes2d;
    private final int size;

    private final Node start;
    private final Node end;

    public Grid(final int size, final Node start, final Node end) {
        this.size = size;

        nodes2d = new Node[size][size];

        for (int i = 0; i < nodes2d.length; i++) {
            for (int j = 0; j < nodes2d[i].length; j++) {
                nodes2d[i][j] = new Node(i, j);
            }
        }

        start.setType(Node.START);
        this.start = start;

        end.setType(Node.END);
        this.end = end;
    }

    public Node[] getNeighbors(final Node node) {
        final ArrayList<Node> nodes = new ArrayList<>();

        final int x = node.getX();
        final int y = node.getY();
        final boolean[] flags = new boolean[8];
        final byte NW = 0, N = 0, NE = 1, E = 1, SE = 2, S = 2, SW = 3, W = 3;

        if (isWalkable(x, y - 1)) {
            nodes.add(get(x, y - 1));
            flags[N] = true;
        }

        if (isWalkable(x + 1, y - 1)) {
            nodes.add(get(x + 1, y - 1));
            flags[NE] = true;
        }

        if (isWalkable(x + 1, y)) {
            nodes.add(get(x + 1, y));
            flags[E] = true;
        }

        if (isWalkable(x + 1, y + 1)) {
            nodes.add(get(x + 1, y + 1));
            flags[SE] = true;
        }

        if (isWalkable(x, y + 1)) {
            nodes.add(get(x, y + 1));
            flags[S] = true;
        }

        if (isWalkable(x - 1, y + 1)) {
            nodes.add(get(x - 1, y + 1));
            flags[SW] = true;
        }

        if (isWalkable(x - 1, y)) {
            nodes.add(get(x - 1, y));
            flags[W] = true;
        }

        return nodes.toArray(new Node[nodes.size()]);
    }

    public Node get(final int x, final int y) {
        return inGrid(x, y) ? nodes2d[x][y] : null;
    }

    public Node getStart() {
        return start;
    }

    public boolean isStart(final int x, final int y) {
        return inGrid(x, y) && isStart(nodes2d[x][y]);
    }

    public boolean isStart(final Node node) {
        return node.getType() == Node.START;
    }

    public Node getEnd() {
        return end;
    }

    public boolean isEnd(final int x, final int y) {
        return inGrid(x, y) && isEnd(nodes2d[x][y]);
    }

    public boolean isEnd(final Node node) {
        return node.getType() == Node.END;
    }

    public boolean shouldWalk(final Node node) {
        return node.getType() != Node.WALL && !node.hasTouched();
    }

    public boolean isWalkable(final int x, final int y) {
        return inGrid(x, y) && nodes2d[x][y].getType() != Node.WALL;
    }

    public boolean inGrid(final int x, final int y) {
        return (x >= size || y >= size || x < 0 || y < 0);
    }
}
