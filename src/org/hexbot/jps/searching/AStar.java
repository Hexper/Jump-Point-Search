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
public class AStar implements Searchable {

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

    private Node[] reconstuct(Node end) {
        final ArrayList<Node> path = new ArrayList<Node>();
        Node current = end.getParent();
        while ((current.getType() != Node.START)) {
            path.add(current);
            current = current.getParent();
        }
        return path.toArray(new Node[path.size()]);
    }

    @Override
    public Node[] findPath() {
        final LinkedList<Node> open = new LinkedList<Node>();
        final LinkedList<Node> closed = new LinkedList<Node>();

        open.add(start);

        start.setG(0);
        start.setF(start.getG() + start.getHeuristic(end));

        while (!open.isEmpty()) {
            Node current = getBest(open);

            if (current.equals(end)) {
                return reconstuct(current);
            }

            open.remove(current);
            closed.add(current);

            for (Node node : getAdjacent(current)) {
                node.setG(distance(current, node));

                double tent_g = current.getG() + node.getG();

                if (closed.contains(node) && tent_g > node.getG() || node.getType() == Node.WALL) {
                    continue;
                }

                if (!open.contains(node) || tent_g < node.getG()) {
                    node.setParent(current);
                    node.setG(tent_g);
                    node.setF(node.getG() + node.getHeuristic(end));

                    if (!open.contains(node)) {
                        open.add(node);
                    }
                }
            }
        }

        return new Node[]{start};
    }

    private double distance(final Node from, final Node to) {
        double distX = from.getX() - to.getX();
        double distY = from.getY() - to.getY();

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

        if (node.getX() > 0) {
            adjacent.add(map[node.getX() - 1][node.getY()]);
            left = true;
        }

        if (node.getX() < map.length - 1) {
            adjacent.add(map[node.getX() + 1][node.getY()]);
            right = true;
        }

        if (node.getY() > 0) {
            adjacent.add(map[node.getX()][node.getY() - 1]);
            top = true;
        }

        if (node.getY() < map.length - 1) {
            adjacent.add(map[node.getX()][node.getY() + 1]);
            bottom = true;
        }
        try {
            final boolean wall1 = map[node.getX() + 1][node.getY()].getType() == Node.WALL;
            final boolean wall2 = map[node.getX()][node.getY() - 1].getType() == Node.WALL;
            final boolean wall3 = map[node.getX() - 1][node.getY()].getType() == Node.WALL;
            final boolean wall4 = map[node.getX()][node.getY() + 1].getType() == Node.WALL;

            if (top) {
                if (right) {
                    if (!(wall1 && wall2))
                        adjacent.add(map[node.getX() + 1][node.getY() - 1]);
                } else if (left) {
                    if (!(wall3 && wall2))
                        adjacent.add(map[node.getX() - 1][node.getY() - 1]);
                }
            }

            if (bottom) {
                if (right) {
                    if (!(wall1 && wall4))
                        adjacent.add(map[node.getX() + 1][node.getY() + 1]);
                } else if (left) {
                    if (!(wall3 && wall4))
                        adjacent.add(map[node.getX() - 1][node.getY() + 1]);
                }
            }
        } catch (Exception e) {
        }

        return adjacent.toArray(new Node[adjacent.size()]);
    }
}
