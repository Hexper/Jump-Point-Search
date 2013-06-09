package org.hexbot.jps.ui.listeners;

import org.hexbot.jps.model.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/8/13
 * Time: 3:52 PM
 */
public class GridMouseMotionListener implements MouseMotionListener {

    private final Node[][] nodes;
    private Rectangle last = null;

    public GridMouseMotionListener(final Node[][] nodes) {
        this.nodes = nodes;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (last != null && last.contains(e.getPoint())) {
            return;
        }
        for (final Node[] noders : nodes) {
            for (final Node node : noders) {
                if (node.getRectangle().contains(e.getPoint())) {
                    if (node.getType() == Node.START || node.getType() == Node.END)
                        return;
                    node.setType(node.getType() == Node.NORMAL ? Node.WALL : Node.NORMAL);
                    last = node.getRectangle();
                }
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
