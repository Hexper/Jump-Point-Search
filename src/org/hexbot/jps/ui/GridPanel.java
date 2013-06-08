package org.hexbot.jps.ui;

import org.hexbot.jps.model.Node;

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
        for(int i = 0; i < nodes.length; i++) {
            for(int i2 = 0; i2 < nodes[i].length; i2++) {
                nodes[i][i2] = new Node(i, i2);
            }
        }

        nodes[0][0].setType(Node.START);
        nodes[24][24].setType(Node.END);

        setPreferredSize(new Dimension(Node.SIZE * 25, Node.SIZE * 25));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        for(Node[] noders : nodes) {
            for(Node node : noders) {

                switch(node.getType()) {
                    case Node.NORMAL:
                        g.setColor(Color.WHITE);
                        node.draw(g, getWidth(), getHeight());
                        break;
                    case Node.START:
                        g.setColor(Color.GREEN);
                        node.draw(g, getWidth(), getHeight());
                         break;
                    case Node.END:
                        g.setColor(Color.RED);
                        node.draw(g, getWidth(), getHeight());

                }
            }
        }
    }
}
