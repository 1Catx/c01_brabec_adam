package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame { //vytvoří instanci *Panelu*, do kterého se poté kreslí

    private final Panel panel;

    public Window(int width, int heigth) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("c01_brabec_adam");
        setVisible(true);

        panel = new Panel(width, heigth);
        add(panel);
        pack();

        panel.setFocusable(true);
        panel.grabFocus();
    }

    public Panel getPanel() {
        return panel;
    }
}
