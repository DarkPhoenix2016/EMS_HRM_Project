package main.customeComponents;
import javax.swing.*;
import java.awt.*;

public class ImageLabel extends javax.swing.JLabel {
	// Generated serialVersionUID to suppress the warning
    private static final long serialVersionUID = 1L;
    
    
    public ImageLabel(String imagePath, int iconWidth, int iconHeight) {
        loadImage(imagePath, iconWidth, iconHeight);
    }

    private void loadImage(String imagePath, int iconWidth, int iconHeight) {
        // Load the image using resource-relative path
        ImageIcon imgIcon = new ImageIcon(getClass().getResource(imagePath));
        Image img = imgIcon.getImage();

        // Scale the image to a fixed size with smoother scaling
        Image newImg = img.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the scaled image
        ImageIcon scaledImageIcon = new ImageIcon(newImg);

        // Set the scaled image as the icon for the JLabel
        setIcon(scaledImageIcon);
    }
}
