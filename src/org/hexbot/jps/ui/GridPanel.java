package org.hexbot.jps.ui;

import org.hexbot.jps.model.Node;
import org.hexbot.jps.searching.AStar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/7/13
 * Time: 7:04 PM
 */
public class GridPanel extends JPanel implements MouseListener {

    private final Node[][] nodes = new Node[25][25];

    public GridPanel() {
        addMouseListener(this);
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
        g.setColor(Color.BLACK);

        for(Node[] noders : nodes) {
            for(Node node : noders) {

                switch(node.getType()) {
                    case Node.NORMAL:
                        g.setColor(Color.WHITE);
                        break;

                    case Node.START:
                        g.setColor(Color.GREEN);
                         break;

                    case Node.END:
                        g.setColor(Color.RED);
                         break;

                    case Node.WALL:
                        g.setColor(Color.GRAY);
                        break;

                    case Node.TRAVERSED:
                        g.setColor(Color.BLUE);
                        break;
                }
                node.draw(g, getWidth(), getHeight());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        for(Node[] noders : nodes) {
            for(Node node : noders) {
                if(node.getRectangle().contains(e.getPoint())) {

                    if(node.getType() == Node.START || node.getType() == Node.END)
                        return;

                    node.setType(node.getType() == Node.NORMAL ? Node.WALL : Node.NORMAL);
                    super.repaint();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Node[][] getMap() {
        return nodes;
    }
}
