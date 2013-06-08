package org.hexbot.jps.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jasper
 * Date: 6/7/13
 * Time: 7:04 PM
 */
public class Sidebox extends JPanel {

    public Sidebox() {
        setLayout(null);
        setPreferredSize(new Dimension(100, 25 * 25));


        JComboBox<String> box = new JComboBox<String>();
        box.setBounds(25, 25, 80, 20);
        add(box);

    }
}
