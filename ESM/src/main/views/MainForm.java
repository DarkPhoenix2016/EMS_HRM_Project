package main.views;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import com.formdev.flatlaf.FlatLightLaf;
import main.customeComponents.ButtonModern;
import main.customeComponents.ImageLabel;
import main.customeComponents.PasswordFieldModern;
import main.customeComponents.RoundedButton;
import main.customeComponents.RoundedPanel;
import main.customeComponents.TextFieldModern;
import main.models.LoginAuthenticator;

public class MainForm extends JFrame {
	// Generated serialVersionUID to suppress the warning
    private static final long serialVersionUID = 1L;
    
    
	private JPanel contentPane;
	private int mouseX, mouseY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		    
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setTitle("Project EMS");
		
		setUndecorated(true);
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		
		// Calculate the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set the location to the center of the screen
        setLocation(centerX, centerY);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(255, 255, 255));
		controlPanel.setBounds(0, 0, 1000, 40);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		
		
		controlPanel.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        mouseX = e.getXOnScreen();
		        mouseY = e.getYOnScreen();
		    }
		});

		controlPanel.addMouseMotionListener(new MouseAdapter() {
		    public void mouseDragged(MouseEvent e) {
		        int deltaX = e.getXOnScreen() - mouseX;
		        int deltaY = e.getYOnScreen() - mouseY;

		        setLocation(getLocation().x + deltaX, getLocation().y + deltaY);

		        mouseX = e.getXOnScreen();
		        mouseY = e.getYOnScreen();
		    }
		});
        				
		//Close button
		JButton closeButton = new JButton("");
		closeButton.setFocusable(false);
		closeButton.setBorder(null);
		closeButton.setBackground(new Color(255, 255, 255));
		closeButton.setIcon(new ImageIcon(MainForm.class.getResource("/main/assets/icons/close-square-black.png")));
		closeButton.setBounds(960, 0, 40, 40);
		controlPanel.add(closeButton);
		
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	closeButton.setBackground(new Color(255, 0, 0)); // Set hover color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	closeButton.setBackground(new Color(255, 255, 255)); // Reset to default color
            }
        });
		
		//Minimize button
		JButton minimizeButton = new JButton("");
		minimizeButton.setFocusable(false);
		minimizeButton.setBorder(null);
		minimizeButton.setBackground(new Color(255, 255, 255));
		minimizeButton.setIcon(new ImageIcon(MainForm.class.getResource("/main/assets/icons/minus-square-black.png")));
		minimizeButton.setBounds(920, 0, 40, 40);
		controlPanel.add(minimizeButton);
		
		
		minimizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeButton.setBackground(new Color(200, 200, 200)); // Set hover color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeButton.setBackground(new Color(255, 255, 255)); // Reset to default color
            }
        });
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 38, 1000, 462);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(36, 87, 416, 284);
		lblNewLabel.setIcon(new ImageIcon(MainForm.class.getResource("/main/assets/artworks/openartwork.png")));
		panel_2.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("HR HUB");
		lblNewLabel_1.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(10, 11, 156, 46);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Manage Your Employees Smartly");
		lblNewLabel_1_1.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 60, 381, 30);
		panel_3.add(lblNewLabel_1_1);
		
		
		
		
		JPanel loginCardPanel = new JPanel();
		loginCardPanel.setVisible(true);
		loginCardPanel.setBackground(new Color(255, 255, 255));
		loginCardPanel.setBounds(10, 113, 441, 299);
		panel_3.add(loginCardPanel);
		loginCardPanel.setLayout(new GridLayout(0, 2, 20, 0));
		
		RoundedPanel roundedPanel = new RoundedPanel();
		roundedPanel.setBackground(new Color(255, 255, 255));
		loginCardPanel.add(roundedPanel);
        roundedPanel.setLayout(null);

        JButton rndbtnAdmin = new RoundedButton("Click me");
        rndbtnAdmin.setBackground(new Color(183, 224, 246));
        rndbtnAdmin.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 20));
        rndbtnAdmin.setText("Admin");
        rndbtnAdmin.setBounds(10, 244, 190, 44);
        roundedPanel.add(rndbtnAdmin);
        
        ImageLabel imageLabel = new ImageLabel("/main/assets/icons/admin.png", 150, 150);
        imageLabel.setBounds(28, 27, 150, 150);
        roundedPanel.add(imageLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Login As");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 18));
        lblNewLabel_2.setBackground(new Color(255, 255, 255));
        lblNewLabel_2.setFocusable(false);
        lblNewLabel_2.setBounds(28, 212, 150, 28);
        roundedPanel.add(lblNewLabel_2);
        
        RoundedPanel roundedPanel_1 = new RoundedPanel();
        roundedPanel_1.setLayout(null);
        roundedPanel_1.setBackground(Color.WHITE);
        loginCardPanel.add(roundedPanel_1);
        
        RoundedButton rndbtnEmployee = new RoundedButton("Click me");
        
        rndbtnEmployee.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 20));
        rndbtnEmployee.setText("Employee");
        rndbtnEmployee.setBackground(new Color(183, 224, 246));
        rndbtnEmployee.setBounds(10, 248, 190, 40);
        roundedPanel_1.add(rndbtnEmployee);
        
        ImageLabel imageLabel_1 = new ImageLabel("/main/assets/icons/users.png", 150, 150);
        imageLabel_1.setBounds(28, 27, 150, 150);
        roundedPanel_1.add(imageLabel_1);
        
        JLabel lblNewLabel_2_1 = new JLabel("Login As");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Poppins ExtraLight", Font.PLAIN, 18));
        lblNewLabel_2_1.setFocusable(false);
        lblNewLabel_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_1.setBounds(28, 209, 150, 28);
        roundedPanel_1.add(lblNewLabel_2_1);
        
       
       
        //Admin Login Form
        
        RoundedPanel adminLoginForm = new RoundedPanel();
        adminLoginForm.setVisible(false);
        adminLoginForm.setBounds(10, 113, 441, 299);
        adminLoginForm.setLayout(null);
        adminLoginForm.setBackground(Color.WHITE);
        panel_3.add(adminLoginForm);
        
        
        
        JPanel adminForm = new JPanel();
        adminForm.setBackground(new Color(255, 255, 255));
        adminForm.setBounds(100, 58, 304, 218);
        adminLoginForm.add(adminForm);
        
        ImageLabel imageLabel_2 = new ImageLabel("/main/assets/icons/admin.png", 80, 80);
        imageLabel_2.setBounds(10, 11, 80, 80);
        adminLoginForm.add(imageLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Login as");
        lblNewLabel_3.setFont(new Font("Poppins Light", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(100, 11, 80, 36);
        adminLoginForm.add(lblNewLabel_3);
        
        JLabel lblNewLabel_2_2 = new JLabel("Admin");
        lblNewLabel_2_2.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel_2_2.setBounds(181, 11, 86, 36);
        adminLoginForm.add(lblNewLabel_2_2);
        SpringLayout sl_adminForm = new SpringLayout();
        adminForm.setLayout(sl_adminForm);
        
        JLabel lblNewLabel_4 = new JLabel("Username");
        sl_adminForm.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 1, SpringLayout.NORTH, adminForm);
        sl_adminForm.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, adminForm);
        sl_adminForm.putConstraint(SpringLayout.SOUTH, lblNewLabel_4, 31, SpringLayout.NORTH, adminForm);
        sl_adminForm.putConstraint(SpringLayout.EAST, lblNewLabel_4, 304, SpringLayout.WEST, adminForm);
        lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel_4.setFont(new Font("Poppins Light", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(100, 58, 150, 36);
        adminForm.add(lblNewLabel_4);
        
        TextFieldModern adminUsername = new TextFieldModern();
        sl_adminForm.putConstraint(SpringLayout.NORTH, adminUsername, 0, SpringLayout.SOUTH, lblNewLabel_4);
        sl_adminForm.putConstraint(SpringLayout.WEST, adminUsername, 0, SpringLayout.WEST, lblNewLabel_4);
        sl_adminForm.putConstraint(SpringLayout.SOUTH, adminUsername, -157, SpringLayout.SOUTH, adminForm);
        sl_adminForm.putConstraint(SpringLayout.EAST, adminUsername, 0, SpringLayout.EAST, adminForm);
        adminUsername.setFont(new Font("Poppins SemiBold", Font.PLAIN, 18));
        adminUsername.setLocation(100, 106);
        adminUsername.setSize(new Dimension(254, 36));
        adminForm.add(adminUsername);
        
        JLabel lblNewLabel_9 = new JLabel("Password");
        sl_adminForm.putConstraint(SpringLayout.NORTH, lblNewLabel_9, 0, SpringLayout.SOUTH, adminUsername);
        sl_adminForm.putConstraint(SpringLayout.WEST, lblNewLabel_9, 0, SpringLayout.WEST, lblNewLabel_4);
        sl_adminForm.putConstraint(SpringLayout.SOUTH, lblNewLabel_9, -127, SpringLayout.SOUTH, adminForm);
        sl_adminForm.putConstraint(SpringLayout.EAST, lblNewLabel_9, 0, SpringLayout.EAST, adminForm);
        lblNewLabel_9.setBackground(new Color(255, 255, 255));
        lblNewLabel_9.setFont(new Font("Poppins Light", Font.PLAIN, 18));
        lblNewLabel_9.setBounds(100, 150, 150, 36);
        adminForm.add(lblNewLabel_9);
        
        PasswordFieldModern adminPassword = new PasswordFieldModern();
        sl_adminForm.putConstraint(SpringLayout.NORTH, adminPassword, 6, SpringLayout.SOUTH, lblNewLabel_9);
        sl_adminForm.putConstraint(SpringLayout.WEST, adminPassword, 0, SpringLayout.WEST, adminForm);
        sl_adminForm.putConstraint(SpringLayout.EAST, adminPassword, 0, SpringLayout.EAST, lblNewLabel_4);
        adminPassword.setFont(new Font("Poppins SemiBold", Font.PLAIN, 18));
        adminPassword.setLocation(100, 188);
        adminPassword.setSize(new Dimension(254, 36));
        adminForm.add(adminPassword);
        
        
        ImageIcon normalIcon = new ImageIcon(getClass().getResource("/main/assets/icons/arrow-left-black.png"));
        ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/main/assets/icons/arrow-left-black.png"));
        
        RoundedButton rndbtnAdminLogin = new RoundedButton("Click me");
        sl_adminForm.putConstraint(SpringLayout.SOUTH, adminPassword, -12, SpringLayout.NORTH, rndbtnAdminLogin);
        sl_adminForm.putConstraint(SpringLayout.EAST, rndbtnAdminLogin, 0, SpringLayout.EAST, lblNewLabel_4);
        sl_adminForm.putConstraint(SpringLayout.WEST, rndbtnAdminLogin, 0, SpringLayout.WEST, lblNewLabel_4);
        sl_adminForm.putConstraint(SpringLayout.NORTH, rndbtnAdminLogin, 139, SpringLayout.NORTH, adminForm);
        
        rndbtnAdminLogin.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 20));
        rndbtnAdminLogin.setText("Login");
        rndbtnAdminLogin.setBackground(new Color(183, 224, 246));
        adminForm.add(rndbtnAdminLogin);
        
        ButtonModern adminBackButton = new ButtonModern("  Not an Admin, Go Back", normalIcon, hoverIcon);
        sl_adminForm.putConstraint(SpringLayout.SOUTH, rndbtnAdminLogin, -6, SpringLayout.NORTH, adminBackButton);
        sl_adminForm.putConstraint(SpringLayout.NORTH, adminBackButton, 181, SpringLayout.NORTH, adminForm);
        sl_adminForm.putConstraint(SpringLayout.WEST, adminBackButton, 0, SpringLayout.WEST, adminForm);
        sl_adminForm.putConstraint(SpringLayout.SOUTH, adminBackButton, 217, SpringLayout.NORTH, adminForm);
        sl_adminForm.putConstraint(SpringLayout.EAST, adminBackButton, 304, SpringLayout.WEST, adminForm);
        adminBackButton.setHorizontalTextPosition(SwingConstants.CENTER);
        adminBackButton.setIconTextGap(20);
        adminBackButton.setIcon(new ImageIcon(MainForm.class.getResource("/main/assets/icons/arrow-left-black.png")));
        adminBackButton.setForeground(new Color(0, 0, 0));
        adminBackButton.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        adminBackButton.setBackground(new Color(255, 255, 255));
        adminBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminForm.add(adminBackButton);
        
        
        //Employee Login Form
        
        RoundedPanel employeeLoginForm = new RoundedPanel();
        employeeLoginForm.setVisible(false);
        employeeLoginForm.setBounds(10, 113, 441, 299);
        employeeLoginForm.setLayout(null);
        employeeLoginForm.setBackground(Color.WHITE);
        panel_3.add(employeeLoginForm);
        
        JPanel employeeForm = new JPanel();
        employeeForm.setBackground(new Color(255, 255, 255));
        employeeForm.setBounds(100, 58, 311, 230);
        employeeLoginForm.add(employeeForm);
        
        ImageLabel imageLabel_3 = new ImageLabel("/main/assets/icons/users.png", 80, 80);
        imageLabel_3.setBounds(10, 11, 80, 80);
        employeeLoginForm.add(imageLabel_3);
        
        JLabel lblNewLabel_5 = new JLabel("Login as");
        lblNewLabel_5.setFont(new Font("Poppins Light", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(100, 11, 80, 36);
        employeeLoginForm.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Employee");
        lblNewLabel_6.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel_6.setBounds(181, 11, 117, 36);
        employeeLoginForm.add(lblNewLabel_6);
        SpringLayout sl_employeeForm = new SpringLayout();
        employeeForm.setLayout(sl_employeeForm);
        
        JLabel lblNewLabel_7 = new JLabel("Username");
        lblNewLabel_7.setAlignmentX(Component.CENTER_ALIGNMENT);
        sl_employeeForm.putConstraint(SpringLayout.NORTH, lblNewLabel_7, 1, SpringLayout.NORTH, employeeForm);
        sl_employeeForm.putConstraint(SpringLayout.WEST, lblNewLabel_7, 0, SpringLayout.WEST, employeeForm);
        sl_employeeForm.putConstraint(SpringLayout.SOUTH, lblNewLabel_7, 32, SpringLayout.NORTH, employeeForm);
        sl_employeeForm.putConstraint(SpringLayout.EAST, lblNewLabel_7, 304, SpringLayout.WEST, employeeForm);
        lblNewLabel_7.setFont(new Font("Poppins Light", Font.PLAIN, 18));
        lblNewLabel_7.setBounds(100, 58, 150, 36);
        employeeForm.add(lblNewLabel_7);
        
        TextFieldModern employeeUsername = new TextFieldModern();
        sl_employeeForm.putConstraint(SpringLayout.NORTH, employeeUsername, 6, SpringLayout.SOUTH, lblNewLabel_7);
        sl_employeeForm.putConstraint(SpringLayout.WEST, employeeUsername, 0, SpringLayout.WEST, lblNewLabel_7);
        sl_employeeForm.putConstraint(SpringLayout.SOUTH, employeeUsername, -161, SpringLayout.SOUTH, employeeForm);
        sl_employeeForm.putConstraint(SpringLayout.EAST, employeeUsername, 0, SpringLayout.EAST, lblNewLabel_7);
        employeeUsername.setFont(new Font("Poppins SemiBold", Font.PLAIN, 18));
        employeeUsername.setLocation(100, 106);
        employeeUsername.setSize(new Dimension(254, 36));
        employeeForm.add(employeeUsername);
        
        JLabel lblNewLabel_8 = new JLabel("Password");
        sl_employeeForm.putConstraint(SpringLayout.NORTH, lblNewLabel_8, 6, SpringLayout.SOUTH, employeeUsername);
        sl_employeeForm.putConstraint(SpringLayout.WEST, lblNewLabel_8, 0, SpringLayout.WEST, lblNewLabel_7);
        sl_employeeForm.putConstraint(SpringLayout.EAST, lblNewLabel_8, 304, SpringLayout.WEST, employeeForm);
        lblNewLabel_8.setFont(new Font("Poppins Light", Font.PLAIN, 18));
        lblNewLabel_8.setBounds(100, 150, 150, 36);
        employeeForm.add(lblNewLabel_8);
        
        PasswordFieldModern employeePassword = new PasswordFieldModern();
        sl_employeeForm.putConstraint(SpringLayout.NORTH, employeePassword, 6, SpringLayout.SOUTH, lblNewLabel_8);
        sl_employeeForm.putConstraint(SpringLayout.WEST, employeePassword, 0, SpringLayout.WEST, lblNewLabel_7);
        sl_employeeForm.putConstraint(SpringLayout.EAST, employeePassword, 304, SpringLayout.WEST, employeeForm);
        employeePassword.setFont(new Font("Poppins SemiBold", Font.PLAIN, 18));
        employeePassword.setLocation(100, 188);
        employeePassword.setSize(new Dimension(254, 36));
        employeeForm.add(employeePassword);
        
        RoundedButton rndbtnEmployeeLogin = new RoundedButton("Click me");
        sl_employeeForm.putConstraint(SpringLayout.SOUTH, employeePassword, -12, SpringLayout.NORTH, rndbtnEmployeeLogin);
        sl_employeeForm.putConstraint(SpringLayout.EAST, rndbtnEmployeeLogin, 0, SpringLayout.EAST, lblNewLabel_7);
        sl_employeeForm.putConstraint(SpringLayout.WEST, rndbtnEmployeeLogin, 0, SpringLayout.WEST, lblNewLabel_7);
        sl_employeeForm.putConstraint(SpringLayout.SOUTH, employeePassword, -12, SpringLayout.NORTH, rndbtnEmployeeLogin);
        
        rndbtnEmployeeLogin.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 20));
        rndbtnEmployeeLogin.setText("Login");
        rndbtnEmployeeLogin.setBackground(new Color(183, 224, 246));
        employeeForm.add(rndbtnEmployeeLogin);
        
        
        ImageIcon normalIcon_1 = new ImageIcon(getClass().getResource("/main/assets/icons/arrow-left-black.png"));
        ImageIcon hoverIcon_1 = new ImageIcon(getClass().getResource("/main/assets/icons/arrow-left-black.png"));
        
        ButtonModern employeeBackButton = new ButtonModern("  Not an Admin, Go Back", normalIcon_1, hoverIcon_1);
        sl_employeeForm.putConstraint(SpringLayout.SOUTH, rndbtnEmployeeLogin, -6, SpringLayout.NORTH, employeeBackButton);
        sl_employeeForm.putConstraint(SpringLayout.SOUTH, lblNewLabel_8, -92, SpringLayout.NORTH, employeeBackButton);
        employeeBackButton.setHorizontalTextPosition(SwingConstants.CENTER);
        employeeBackButton.setIconTextGap(20);
        employeeBackButton.setIcon(new ImageIcon(MainForm.class.getResource("/main/assets/icons/arrow-left-black.png")));
        employeeBackButton.setForeground(new Color(0, 0, 0));
        employeeBackButton.setFont(new Font("Poppins Light", Font.PLAIN, 16));
        employeeBackButton.setBackground(new Color(255, 255, 255));
        
        sl_employeeForm.putConstraint(SpringLayout.NORTH, employeeBackButton, -32, SpringLayout.SOUTH, employeeForm);
        sl_employeeForm.putConstraint(SpringLayout.WEST, employeeBackButton, 0, SpringLayout.WEST, lblNewLabel_7);
        sl_employeeForm.putConstraint(SpringLayout.SOUTH, employeeBackButton, 0, SpringLayout.SOUTH, employeeForm);
        sl_employeeForm.putConstraint(SpringLayout.EAST, employeeBackButton, 0, SpringLayout.EAST, lblNewLabel_7);
        employeeBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeeForm.add(employeeBackButton);
        
        
        
     
        rndbtnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Admin button clicked");
                loginCardPanel.setVisible(false);
                adminLoginForm.setVisible(true);
            }
        });

        adminBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Admin back button clicked");
                loginCardPanel.setVisible(true);
                adminLoginForm.setVisible(false);
            }
        });

        rndbtnEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Employee button clicked");
                loginCardPanel.setVisible(false);
                employeeLoginForm.setVisible(true);
            }
        });

        employeeBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Employee back button clicked");
                loginCardPanel.setVisible(true);
                employeeLoginForm.setVisible(false);
            }
        });  
        
        
        rndbtnAdminLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String username = adminUsername.getText();
            	char[] passwordChars = adminPassword.getPassword();
                String password = new String(passwordChars);
            	String result = LoginAuthenticator.authenticate(username, password, "Admin");
            	JOptionPane.showMessageDialog(null, result);
            	adminUsername.setText("");
            	adminPassword.setText("");
            }
        });
        
        //Employee Login data submit
        rndbtnEmployeeLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String username = employeeUsername.getText();
            	char[] passwordChars = employeePassword.getPassword();
                String password = new String(passwordChars);
            	String result = LoginAuthenticator.authenticate(username, password, "Employee");
            	JOptionPane.showMessageDialog(null, result);
            	employeeUsername.setText("");
            	employeePassword.setText("");
            }
        });
        
        
        
	}
}
