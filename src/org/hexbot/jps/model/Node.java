package org.hexbot.jps.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/7/13
 * Time: 6:35 PM
 */
public class Node implements Comparable {
    public static final int SIZE = 20;
    public static final int NORMAL = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int END = 3;
    public static final int TRAVERSED = 4;
    private final int x;
    private final int y;
    private boolean touched;
    private int type = 0;
    private double g;
    private double f;
    private Node parent;

    public Node(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x * SIZE, y * SIZE, SIZE, SIZE);
    }

    public void draw(final Graphics g) {
        g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x * SIZE, y * SIZE, SIZE, SIZE);
    }

    public int getType() {
        return type;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public double getG() {
        return g;
    }

    public void setG(final double g) {
        this.g = g;
    }

    public int getHeuristic(Node target) {
        return 10 * ((Math.abs(x - target.getX())) + (Math.abs(y - target.getY())));
    }

    public double getF() {
        return f;
    }

    public void setF(final double f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(final Node parent) {
        this.parent = parent;
    }

    public void unTouch() {
        touched = false;
    }

    public void touch() {
        touched = true;
    }

    public boolean hasTouched() {
        return touched;
    }

    @Override
    public int compareTo(final Object o) {
        if (!(o instanceof Node)) {
            throw new RuntimeException("Node not being compared to Node");
        }

        final Node n = (Node) o;
        return (int) (this.f - n.f);
    }
}
