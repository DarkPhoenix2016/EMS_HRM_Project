package main.customeComponents;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundedPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private int shadowSize = 20; // Adjust the shadow size as needed

    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = 40;

        // Enable antialiasing
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded rectangle
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, radius, radius);
        g2d.dispose();

        // Draw drop shadow
        for (int i = 0; i < shadowSize; i++) {
        	g.setColor(new Color(0, 0, 0, Math.max(0, 20 - i * 4))); // Adjust alpha for fading effect
            g.drawRoundRect(i, i, getWidth() - i - 1, getHeight() - i - 1, radius, radius);
        }
    }
}
