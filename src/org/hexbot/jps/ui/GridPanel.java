package org.hexbot.jps.ui;

import org.hexbot.jps.model.Node;
import org.hexbot.jps.ui.listeners.GridMouseListener;
import org.hexbot.jps.ui.listeners.GridMouseMotionListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/7/13
 * Time: 7:04 PM
 */
public class GridPanel extends JPanel {
    private final Node[][] nodes = new Node[25][25];

    public GridPanel() {
        addMouseListener(new GridMouseListener(nodes));
        addMouseMotionListener(new GridMouseMotionListener(nodes));

        final Thread painter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isVisible()) {
                    repaint();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        });

        painter.setName("Panel repaint");
        painter.setPriority(Thread.MIN_PRIORITY);
        painter.start();

        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }

        nodes[2][2].setType(Node.START);
        nodes[22][22].setType(Node.END);

        setPreferredSize(new Dimension(Node.SIZE * 25, Node.SIZE * 25));
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        final Graphics2D g = (Graphics2D) graphics;

        g.setColor(Color.BLACK);

        for (final Node[] noders : nodes) {
            for (final Node node : noders) {
                switch (node.getType()) {
                    case Node.NORMAL:
                        g.setColor(Color.WHITE);
                        break;

                    case Node.START:
                        g.setColor(Color.GREEN);
                        break;

                    case Node.END:
                        Stroke stroke = g.getStroke();
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.YELLOW);
                        Node parent = node.getParent();
                        if (parent != null) {
                            g.drawLine((int) parent.getRectangle().getCenterX(), (int) parent.getRectangle().getCenterY(),
                                    (int) node.getRectangle().getCenterX(), (int) node.getRectangle().getCenterY());
                        }
                        g.setColor(Color.RED);
                        g.setStroke(stroke);
                        break;

                    case Node.WALL:
                        g.setColor(Color.GRAY);
                        break;

                    case Node.TRAVERSED:
                        stroke = g.getStroke();
                        parent = node.getParent();
                        g.setStroke(new BasicStroke(2));
                        g.setColor(Color.YELLOW);
                        if (parent != null)
                            g.drawLine((int) parent.getRectangle().getCenterX(), (int) parent.getRectangle().getCenterY(),
                                    (int) node.getRectangle().getCenterX(), (int) node.getRectangle().getCenterY());

                        g.setStroke(stroke);
                        break;
                }
                if (node.getType() != Node.TRAVERSED)
                    node.draw(g);
            }
        }
    }

    public Node[][] getMap() {
        return nodes;
    }
}
