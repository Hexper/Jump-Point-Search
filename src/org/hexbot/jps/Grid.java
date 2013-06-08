package org.hexbot.jps;

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
        setResizable(false);

        final JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        final GridPanel grid = new GridPanel();
        final Sidebox box = new Sidebox(grid);

        panel.add(grid);
        panel.add(box);

        setVisible(true);
        setSize(600, 600);
        setTitle("Jump Point Search");
    }

    public static void main(String[] args) {
       new Grid();
    }
}
