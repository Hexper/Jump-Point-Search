package org.hexbot.jps;

import org.hexbot.jps.model.Node;
import org.hexbot.jps.ui.GridPanel;
import org.hexbot.jps.ui.Sidebox;

import javax.swing.*;
import java.awt.*;


/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/7/13
 * Time: 6:34 PM
 */
public class Grid extends JFrame {


    public Grid() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        GridPanel grid = new GridPanel();
        Sidebox box = new Sidebox();

        panel.add(grid);
        panel.add(box);

        setSize(276, 25 * 25);
        setVisible(true);
    }

    public static void main(String[] args) {
       new Grid();
    }
}
