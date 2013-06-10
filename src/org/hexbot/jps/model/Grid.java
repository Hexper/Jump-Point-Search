package org.hexbot.jps.model;

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

    public Node getStart() {
        return start;
    }

    public boolean isStart(final int x, final int y) {
        if (x >= size || y >= size || x < 0 || y < 0) {
            return false;
        }

        return isStart(nodes2d[x][y]);
    }

    public boolean isStart(final Node node) {
        return shouldWalk(node);
    }

    public Node getEnd() {
        return end;
    }

    public boolean isEnd(final int x, final int y) {
        if (x >= size || y >= size || x < 0 || y < 0) {
            return false;
        }

        return isEnd(nodes2d[x][y]);
    }

    public boolean isEnd(final Node node) {
        return shouldWalk(node);
    }

    public boolean shouldWalk(final Node node) {
        return node.getType() != Node.WALL && !node.hasTouched();
    }

    public boolean isWalkable(final int x, final int y) {
        if (x >= size || y >= size || x < 0 || y < 0) {
            return false;
        }

        return nodes2d[x][y].getType() != Node.WALL;
    }
}
