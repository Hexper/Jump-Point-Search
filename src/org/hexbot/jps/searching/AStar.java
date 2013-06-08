package org.hexbot.jps.searching;

import org.hexbot.jps.model.Node;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/8/13
 * Time: 3:38 AM
 */
public class AStar {

    private final Node[][] map;
    private Node start;
    private Node end;

    public AStar(final Node[][] map) {
        this.map = map;

        for (Node[] noder : map) {
            for (Node node : noder) {
                if (node.getType() == Node.START) {
                    start = node;
                } else if (node.getType() == Node.END) {
                    end = node;
                }
            }
        }
    }

    public Node[] findPath() {
        final LinkedList<Node> open = new LinkedList<Node>();
        final LinkedList<Node> closed = new LinkedList<Node>();

        open.add(start);

        start.setG(0);
        start.setF(start.getG() + start.getHeuristic(end));

        while (!open.isEmpty()) {
            Node current = getBest(open);

            if (current.equals(end)) {
                closed.add(current);

                return closed.toArray(new Node[closed.size()]);
            }

            open.remove(current);
            closed.add(current);

            for (Node node : getAdjacent(current)) {
                node.setG(distance(current, node));

                double tent_g = current.getG() + node.getG();

                if (closed.contains(node) && tent_g > node.getG() || node.getType() == Node.WALL) {
                    continue;
                }

                if (!open.contains(node) || tent_g  < node.getG()) {
                    node.setParent(current);
                    node.setG(tent_g);
                    node.setF(node.getG() + node.getHeuristic(end));

                    if (!open.contains(node)) {
                        open.add(node);
                    }
                }
            }
        }

        return null;
    }

    private double distance(final Node from, final Node to) {
        double distX = from.getX() - to.getX();
        double distY = from .getY() - to.getY();

        return Math.sqrt(((distX * distX) + (distY * distY)));
    }

    private Node getBest(final LinkedList<Node> open) {
        Node best = open.get(0);

        for (Node node : open) {
            if (node.getF() < best.getF()) {
                best = node;
            }
        }
        return best;
    }

    public Node[] getAdjacent(final Node node) {
        boolean top = false;
        boolean left = false;
        boolean right = false;
        boolean bottom = false;

        final ArrayList<Node> adjacent = new ArrayList<Node>();

        //LEFT
        if(node.getX() > 0) {
            adjacent.add(map[node.getX() - 1][node.getY()]);
            left = true;
        }

        //RIGHT
        if(node.getX() < map.length - 1) {
            adjacent.add(map[node.getX() + 1][node.getY()]);
            right = true;
        }

        //TOP
        if(node.getY() > 0) {
            adjacent.add(map[node.getX()][node.getY() - 1]);
            top = true;
        }

        //BOTTOM
        if(node.getY() < map.length - 1) {
            adjacent.add(map[node.getX()][node.getY() + 1]);
            bottom = true;
        }

        if (top) {
            if (right) {
                adjacent.add(map[node.getX() + 1][node.getY() - 1]);
            } else if (left) {
                adjacent.add(map[node.getX() - 1][node.getY() - 1]);
            }
        }

        if (bottom) {
            if (right) {
                adjacent.add(map[node.getX() + 1][node.getY() + 1]);
            } else if (left) {
                adjacent.add(map[node.getX() - 1][node.getY() + 1]);
            }
        }

        return adjacent.toArray(new Node[adjacent.size()]);
    }
}
