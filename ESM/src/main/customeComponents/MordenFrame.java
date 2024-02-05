package main.customeComponents;

import javax.swing.*;
import java.awt.*;

public class MordenFrame extends JFrame {

	// Generated serialVersionUID to suppress the warning
    private static final long serialVersionUID = 1L;
    
    public MordenFrame(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the frame on the screen

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setResizable(true);
    }
}
