package main.customeComponents;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldModern extends JTextField {

    private static final long serialVersionUID = 1L;
    private Color borderColor = new Color(0, 0, 0); // Default border color
    private Color focusColor = new Color(0, 128, 255); // Border color when focused

    public TextFieldModern() {
        setOpaque(false);
        setBorder(null);

        // Add focus listener to change border color when focused
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                borderColor = focusColor;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                borderColor = new Color(0, 0, 0); // Reset to default color when focus is lost
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable antialiasing
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw bottom border
        g2d.setColor(borderColor);
        g2d.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        g2d.dispose();
    }
}
