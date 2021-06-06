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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class WelcomePage extends JFrame{

	private final JPanel menuPane = new JPanel();
	private JPanel titlePane;
	private JLabel title_lab;
	private JLabel title_icon;
	private JPanel adm_pane;
	private JLabel adm_lab;
	private JPanel ag_pane;
	private JLabel ag_lab;
	private JPanel exit_pane;
	private JLabel exit_lab;


	/**
	 * Create the application.
	 */
	public WelcomePage() {
		
		setBounds(100, 100, 697, 513);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		//titlePane.setBackground(new Color(0, 153, 51));
		
		title_lab = new JLabel("Welcome !");
		title_lab.setBounds(378, 50, 207, 33);
		title_lab.setBackground(new Color(0, 153, 153));
		title_lab.setFont(new Font("Lucida Grande", 1, 36)); // NOI18N
		//title_lab.setForeground(new Color(255, 255, 255));
		title_lab.setForeground(new Color(0, 153, 51));
		
		title_icon = new JLabel("icon");
		title_icon.setBounds(18, 6, 221, 139);
		title_icon.setIcon(new ImageIcon(getClass().getResource("/icons/iconeSogeac.jpg")));
		menuPane.setBounds(33, 188, 632, 273);
		menuPane.setBorder(BorderFactory.createTitledBorder(null, "Login", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(255, 255, 255)));
		menuPane.setBackground(new Color(160, 160, 160));
		
		menuPane.setLayout(null);
		
		adm_pane = new JPanel();
		adm_pane.setBounds(27, 81, 179, 141);
		adm_pane.setBackground(new Color(255, 255, 255));
		adm_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		menuPane.add(adm_pane);
		adm_pane.setLayout(null);
		
		adm_lab = new JLabel("ADMIN");
		adm_lab.setBounds(53, 55, 65, 22);
		adm_lab.setHorizontalAlignment(SwingConstants.CENTER);
		adm_lab.setFont(new Font("Lucida Grande", 1, 18)); 
		adm_lab.setForeground(new Color(0, 153, 51));
		adm_pane.add(adm_lab);
		
		ag_pane = new JPanel();
		ag_pane.setLayout(null);
		ag_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		ag_pane.setBackground(Color.WHITE);
		ag_pane.setBounds(231, 81, 179, 141);
		menuPane.add(ag_pane);
		
		ag_lab = new JLabel("AGENT");
		ag_lab.setHorizontalAlignment(SwingConstants.CENTER);
		ag_lab.setForeground(new Color(0, 153, 51));
		ag_lab.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		ag_lab.setBounds(53, 55, 65, 22);
		ag_pane.add(ag_lab);
		
		exit_pane = new JPanel();
		exit_pane.setLayout(null);
		exit_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		exit_pane.setBackground(Color.WHITE);
		exit_pane.setBounds(429, 81, 179, 141);
		menuPane.add(exit_pane);
		
		exit_lab = new JLabel("EXIT");
		exit_lab.setHorizontalAlignment(SwingConstants.CENTER);
		exit_lab.setForeground(new Color(0, 153, 51));
		exit_lab.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		exit_lab.setBounds(53, 55, 65, 22);
		exit_pane.add(exit_lab);
		setLayout(null);
		titlePane.setLayout(null);
		titlePane.add(title_icon);
		titlePane.add(title_lab);
		add(titlePane);
		add(menuPane);
		
	}
	
	private void centerView() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        this.setLocation((screen.width - frameSize.width) >> 1,
                          (screen.height - frameSize.height) >> 1);
    }
    
    private void creatEvent() {
    	adm_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(adm_pane, adm_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(adm_pane, adm_lab);
            }
            public void mousePressed(MouseEvent evt) {
            	new AdminLogin();
                dispose();
            }
        });
    	
    	ag_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(ag_pane, ag_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(ag_pane, ag_lab);
            }
            public void mousePressed(MouseEvent evt) {
                dispose();
                new AgentLogin();

            }
        });
    	
    	exit_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(exit_pane, exit_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(exit_pane, exit_lab);
            }
            public void mousePressed(MouseEvent evt) {
            	JFrame frame = new JFrame();
                if(JOptionPane.showConfirmDialog(frame, "Do you want to exit the application ?", " Welcome Page ", 
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
