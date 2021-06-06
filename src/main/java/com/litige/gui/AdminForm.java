package com.litige.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class AdminForm extends JFrame{
	private final JPanel menuPane = new JPanel();
	private JPanel titlePane;
	private JLabel title_lab;
	private JLabel title_icon;
	private JPanel mg_pane;
	private JLabel mg_lab;
	private JPanel mm_pane;
	private JLabel mm_lab;
	private JPanel lgout_pane;
	private JLabel lgout_lab;

	

	/**
	 * Create the application.
	 */
	public AdminForm() {
		setBounds(100, 100, 697, 513);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		initialize();
		centerView();
		creatEvent();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		titlePane = new JPanel();
		titlePane.setBounds(0, 0, 697, 158);
		titlePane.setLayout(null);
		
		title_lab = new JLabel("Admin Page");
		title_lab.setBackground(new Color(0, 153, 153));
		title_lab.setFont(new Font("Tahoma", 1, 36));
		title_lab.setForeground(new Color(0, 153, 51));
		title_lab.setBounds(379, 50, 236, 44);
		titlePane.add(title_lab);
		
		title_icon = new JLabel("icon");
		title_icon.setBounds(18, 6, 221, 139);
		titlePane.add(title_icon);
		title_icon.setIcon(new ImageIcon(getClass().getResource("/icons/iconeSogeac.jpg")));
		menuPane.setBorder(BorderFactory.createTitledBorder(null, "Admin", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(255, 255, 255)));
		menuPane.setBackground(new Color(160, 160, 160));
		menuPane.setBounds(33, 188, 632, 273);
		
		menuPane.setLayout(null);
		
		mg_pane = new JPanel();
		mg_pane.setBounds(27, 81, 179, 141);
		mg_pane.setBackground(new Color(255, 255, 255));
		mg_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		menuPane.add(mg_pane);
		mg_pane.setLayout(null);
		
		mg_lab = new JLabel("Manage");
		mg_lab.setBounds(32, 55, 112, 22);
		mg_lab.setHorizontalAlignment(SwingConstants.CENTER);
		mg_lab.setFont(new Font("Tahoma", 1, 18)); 
		mg_lab.setForeground(new Color(0, 153, 51));
		mg_pane.add(mg_lab);
		
		mm_pane = new JPanel();
		mm_pane.setLayout(null);
		mm_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		mm_pane.setBackground(Color.WHITE);
		mm_pane.setBounds(231, 81, 179, 141);
		menuPane.add(mm_pane);
		
		mm_lab = new JLabel("Main Window");
		mm_lab.setHorizontalAlignment(SwingConstants.CENTER);
		mm_lab.setForeground(new Color(0, 153, 51));
		mm_lab.setFont(new Font("Tahoma", Font.BOLD, 18));
		mm_lab.setBounds(36, 54, 106, 22);
		mm_pane.add(mm_lab);
		
		lgout_pane = new JPanel();
		lgout_pane.setLayout(null);
		lgout_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		lgout_pane.setBackground(Color.WHITE);
		lgout_pane.setBounds(429, 81, 179, 141);
		menuPane.add(lgout_pane);
		
		lgout_lab = new JLabel("Log Out");
		lgout_lab.setHorizontalAlignment(SwingConstants.CENTER);
		lgout_lab.setForeground(new Color(0, 153, 51));
		lgout_lab.setFont(new Font("Tahoma", Font.BOLD, 18));
		lgout_lab.setBounds(53, 55, 90, 22);
		lgout_pane.add(lgout_lab);
		
		getContentPane().add(titlePane);
		getContentPane().add(menuPane);
	}
	
	private void centerView() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        this.setLocation((screen.width - frameSize.width) >> 1,
                          (screen.height - frameSize.height) >> 1);
    }
    
    private void creatEvent() {
    	mg_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(mg_pane, mg_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(mg_pane, mg_lab);
            }
            public void mousePressed(MouseEvent evt) {
            	new AgentManagement();
                dispose();
            }
        });
    	
    	mm_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(mm_pane, mm_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(mm_pane, mm_lab);
            }
            public void mousePressed(MouseEvent evt) {
                
                new MainWindow();
                dispose();
            }
        });
    	
    	lgout_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(lgout_pane, lgout_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(lgout_pane, lgout_lab);
            }
            public void mousePressed(MouseEvent evt) {
            	JFrame frame = new JFrame();
                if(JOptionPane.showConfirmDialog(frame, "Do you want to exit the application ?", " Page d'accueil ", 
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){System.exit(0);}
            }
        });
    	
    	
    }
    
    private void setColor(JPanel pane, JLabel lab){
        lab.setForeground(new Color(255, 255, 255));
        pane.setBackground(new Color(0,153,51));
        
    }
    
    private void resetColor(JPanel pane, JLabel lab){
        pane.setBackground(new Color(240, 240, 240));
        lab.setForeground(new Color(0,153,51));
    }

}
