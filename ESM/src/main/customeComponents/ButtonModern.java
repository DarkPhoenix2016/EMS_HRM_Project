package main.customeComponents;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonModern extends JButton {

    private static final long serialVersionUID = 1L;
    private final Color textColor = new Color(0, 0, 0); // Default text color
    private final Color hoverTextColor = new Color(0, 5, 70);// Text color on hover
    private final ImageIcon icon;
    private final ImageIcon hoverIcon;
    
    public ButtonModern(String text, ImageIcon icon, ImageIcon hoverIcon) {
        super(text);
        this.icon = icon;
        this.hoverIcon = hoverIcon;
        initButton();
    }

    private void initButton() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(textColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(hoverTextColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(textColor);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setIcon(icon);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Enable antialiasing
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw icon
        if (getIcon() != null) {
            getIcon().paintIcon(this, g2d, 5, (getHeight() - getIcon().getIconHeight()) / 2);
        }

        // Draw text
        g2d.setColor(getForeground());
        g2d.drawString(getText(), 30, getHeight() / 2 + g2d.getFontMetrics().getAscent() / 2);

        g2d.dispose();
    }
}
