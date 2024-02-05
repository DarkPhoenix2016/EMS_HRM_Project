package main.customeComponents;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class ModernTitleBar extends JComponent {
	// Generated serialVersionUID to suppress the warning
    private static final long serialVersionUID = 1L;
    
    private JFrame frame;
    /*private int mouseX, mouseY;*/
    private ComponentResizer resizer;
    private JPanel panel;
    private boolean register = true;
    private int x;
    private int y;

    public ModernTitleBar(JFrame frame, String frameName) {
        this.frame = frame;
        init();
        initJFrame();
        createControlButtons();
        createFrameNameLabel(frameName);
    }

    private void init() {
        setLayout(new MigLayout("inset 3, fill", "[fill]", "[fill]"));
        panel = new JPanel();
        panel.setOpaque(false);
        add(panel);
        panel.setLayout(new MigLayout("inset 3"));

        createControlButton("", "/main/assets/icons/close.png", 1400, new Color(255, 255, 255), e -> System.exit(0));

        createControlButton("", "/main/assets/icons/minimize.png", 1320, new Color(255, 255, 255), e ->
                frame.setState(JFrame.ICONIFIED));

        createControlButton("", "/main/assets/icons/maximize.png", 1360, Color.WHITE, e -> toggleMaximize());

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (frame.getExtendedState() == JFrame.NORMAL && SwingUtilities.isLeftMouseButton(e)) {
                    x = e.getX() + 3;
                    y = e.getY() + 3;
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (frame.getExtendedState() == JFrame.NORMAL) {
                        frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
                    }
                }
            }
        });
    }

    private void initJFrame() {
        resizer = new ComponentResizer();
        resizer.setSnapSize(new Dimension(10, 10));
        resizer.setMinimumSize(new Dimension(800, 600));
        resizer.registerComponent(frame);

        frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == JFrame.MAXIMIZED_BOTH) {
                    resizer.deregisterComponent(frame);
                    register = false;
                } else if (e.getNewState() == JFrame.NORMAL) {
                    if (!register) {
                        resizer.registerComponent(frame);
                        register = true;
                    }
                }
            }
        });
    }

    private void createControlButtons() {
        
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
                button.setBackground(new Color(200, 200, 200));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(defaultColor);
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
