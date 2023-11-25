package main.customeComponents;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.RenderingHints;

public class RoundedButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(new Color(200, 200, 200)); // Set pressed color
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }
}

