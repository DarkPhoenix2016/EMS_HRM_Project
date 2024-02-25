package main.customeComponents.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Menu extends JComponent {

    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }

    private MenuEvent event;
    private MigLayout layout;
    private String[][] menuItems = new String[][]{
        {"Dashboard"},
        {"Employee", "Register","Designation"},
        {"Salary", "Create Slip","Allowance","Deduction"},
        {"Leaves"},
        {"Settings", "Company","My Profile"}
    };

    public Menu() {
        init();
    }

    private void init() {
       
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);
        //  Init MenuItem
        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);
        }

    }

    private Icon getIcon(int index) {
        URL url = getClass().getResource("/main/customeComponents/menu/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    private void addMenu(String menuName, int index) {
        int length = menuItems[index].length;
        MenuItem item = new MenuItem(menuName, index, length > 1);

        // Set font for menu item
        Font font = new Font("Poppins", Font.BOLD, 12);
        item.setFont(font);
        
        Icon icon = getIcon(index);
        if (icon != null) {
            item.setIcon(icon);
        }
        item.addActionListener((ActionEvent ae) -> {
            if (length > 1) {
                if (!item.isSelected()) {
                    item.setSelected(true);
                    item.setForeground(new Color(255, 255, 255));
                    addSubMenu(item, index, length, getComponentZOrder(item));
                } else {
                    //  Hide menu
                    hideMenu(item, index);
                    item.setSelected(false);
                }
            } else {
                if (event != null) {
                    event.selected(index, 0);
                    item.setForeground(new Color(255, 255, 255));
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addSubMenu(MenuItem item, int index, int length, int indexZorder) {
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setBackground(new Color(1,38,119));
        
        // Set font for sub-menu items
        Font font = new Font("Poppins", Font.BOLD, 12);
        item.setForeground(new Color(255, 255, 255));
        for (int i = 1; i < length; i++) {
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.setFont(font); // Set font for sub-menu item
            subItem.addActionListener((ActionEvent ae) -> {
                if (event != null) {
                    event.selected(index, subItem.getIndex());
                }
            });
            subItem.initSubMenu(i, length);
            panel.add(subItem);
        }
        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel, item, layout, true);
    }

    private void hideMenu(MenuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                item.setForeground(new Color(255, 255, 255));
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(0,20,73));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }

}
