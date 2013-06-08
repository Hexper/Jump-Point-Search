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


    private final int x;
    private final int y;
    private int type = 0;

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

    public void draw(Graphics g, int height, int width) {
        g.drawLine(x * SIZE, 0, x * SIZE, height);
        g.drawLine(0, y * SIZE, width, y * SIZE);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
