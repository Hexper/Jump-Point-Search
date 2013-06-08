package org.hexbot.jps.ui;

import org.hexbot.jps.model.Node;
import org.hexbot.jps.searching.AStar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/7/13
 * Time: 7:04 PM
 */
public class Sidebox extends JPanel {

    public Sidebox(final GridPanel panel) {
        setLayout(null);
        setPreferredSize(new Dimension(100, 20));

        JButton find = new JButton("Find");
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AStar aStar = new AStar(panel.getMap());
                for(Node node : aStar.findPath()) {
                    if(node.getType() != Node.START && node.getType() != Node.END)
                        node.setType(Node.TRAVERSED);
                }
                panel.repaint();
            }

        });
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Node[] nodes : panel.getMap()) {
                    for(Node node : nodes) {
                        if(node.getType() != Node.START && node.getType() != Node.END && node.getType() != Node.WALL)
                            node.setType(Node.NORMAL);
                    }
                }
                panel.repaint();
            }
        });

        find.setBounds(10, 10, 80, 20);
        clear.setBounds(100, 10, 80, 20);

        add(find);
        add(clear);

    }
}
