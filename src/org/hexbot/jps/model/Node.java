package org.hexbot.jps.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/7/13
 * Time: 6:35 PM
 */
public class Node {

    public static final int SIZE = 20;

    // types
    public static final int NORMAL = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int END = 3;
    public static final int TRAVERSED = 4;


    private final int x;
    private final int y;

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

    public void draw(Graphics g, int height, int width) {

        g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x * SIZE, y * SIZE, SIZE, SIZE);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public int getHeuristic(Node target) {
        return 10 * ( (Math.abs(getX() - target.getX())) + (Math.abs(getY() - target.getY())) );
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
