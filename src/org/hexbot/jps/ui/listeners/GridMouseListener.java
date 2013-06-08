package org.hexbot.jps.ui.listeners;

import org.hexbot.jps.model.Node;
import org.hexbot.jps.ui.GridPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/8/13
 * Time: 3:26 PM
 */
public class GridMouseListener implements MouseListener {

    private final Node[][] nodes;

    public GridMouseListener(final Node[][] nodes) {
        this.nodes = nodes;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        for (final Node[] noders : nodes) {
            for (final Node node : noders) {
                if (node.getRectangle().contains(e.getPoint())) {
                    if (node.getType() == Node.START || node.getType() == Node.END) {
                        return;
                    }

                    node.setType(node.getType() == Node.NORMAL ? Node.WALL : Node.NORMAL);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
