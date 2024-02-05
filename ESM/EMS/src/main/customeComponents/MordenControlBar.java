package main.customeComponents;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MordenControlBar extends JComponent {
	// Generated serialVersionUID to suppress the warning
    private static final long serialVersionUID = 1L;
    
    private int mouseX, mouseY;
    private final JFrame frame;
    private JFrame fram;
    
    private ComponentResizer resizer;
    public MordenControlBar(JFrame frame, String frameName) {
        this.frame = frame;
        resizer = new ComponentResizer();
        resizer.setSnapSize(new Dimension(10, 10));
        resizer.setMinimumSize(new Dimension(800, 600));
        resizer.registerComponent(fram);
        
        setLayout(null);
        
        setPreferredSize(new Dimension(1440, 40));
        setBackground(new Color(217, 217, 217));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - mouseX;
                int deltaY = e.getYOnScreen() - mouseY;

                frame.setLocation(frame.getLocation().x + deltaX, frame.getLocation().y + deltaY);

                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();
            }
        });

        createControlButtons();
        createFrameNameLabel(frameName);
    }

    private void createControlButtons() {
        // Close button
        createControlButton("", "/main/assets/icons/close.png", 1400, new Color(255, 255, 255), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Minimize button
        createControlButton("", "/main/assets/icons/minimize.png", 1320, new Color(255, 255, 255), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }
        });

        // Maximize button
        createControlButton("", "/main/assets/icons/maximize.png", 1360, Color.WHITE, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleMaximize();
            }
        });
    }

    private void createControlButton(String text, String iconPath, int x, Color defaultColor, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setBorder(null);
        button.setBackground(defaultColor);
        button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        button.setBounds(x, 0, 40, 40);
        add(button);

        button.addActionListener(actionListener);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(200, 200, 200)); // Set hover color
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(defaultColor); // Reset to default color
            }
        });
    }

    private void createFrameNameLabel(String frameName) {
        JLabel nameLabel = new JLabel(frameName);
        nameLabel.setFont(new Font("Poppins SemiBold", Font.PLAIN, 18));
        nameLabel.setBounds(25, 0, 454, 40);
        add(nameLabel);
    }

    private void toggleMaximize() {
        if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            frame.setExtendedState(JFrame.NORMAL);
        } else {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }
}
