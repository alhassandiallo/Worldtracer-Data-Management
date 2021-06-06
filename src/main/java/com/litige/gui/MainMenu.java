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

public class MainMenu extends JFrame{
	private JPanel menuPane = new JPanel();
	private JPanel titlePane;
	private JLabel title_lab;
	private JLabel title_icon;
	private JPanel rec_pane;
	private JLabel rec_lab;
	private JPanel claim_pane;
	private JLabel claim_lab;
	private JPanel mc_pane;
	private JLabel mc_lab;
	private JPanel rb_pane;
	private JLabel rb_lab;
	private JPanel rv_pane;
	private JLabel rv_lab;
	private JPanel lgout_pane;
	private JLabel lgout_lab;
	

	/**
	 * Create the application.
	 */
	public MainMenu() {
		setBounds(100, 100, 697, 710);
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
		titlePane.setBackground(new Color(0, 153, 51));
		titlePane.setBounds(0, 0, 697, 158);
		titlePane.setLayout(null);
		
		title_lab = new JLabel("MAIN MENU");
		title_lab.setBackground(new Color(0, 153, 153));
		title_lab.setFont(new Font("Lucida Grande", 1, 36)); 
		title_lab.setForeground(new Color(255, 255, 255));
		title_lab.setBounds(339, 47, 239, 39);
		titlePane.add(title_lab);
		
		title_icon = new JLabel("icon");
		title_icon.setBounds(18, 6, 221, 139);
		titlePane.add(title_icon);
		title_icon.setIcon(new ImageIcon(getClass().getResource("/icons/iconeSogeac.jpg")));
		menuPane.setBorder(BorderFactory.createTitledBorder(null, "Operations Management", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(255, 255, 255)));
		menuPane.setBackground(new Color(160, 160, 160));
		menuPane.setBounds(33, 188, 632, 473);
		
		menuPane.setLayout(null);
		
		rec_pane = new JPanel();
		rec_pane.setBounds(27, 81, 179, 141);
		rec_pane.setBackground(new Color(255, 255, 255));
		rec_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		menuPane.add(rec_pane);
		rec_pane.setLayout(null);
		
		rec_lab = new JLabel("Record Bag");
		rec_lab.setBounds(20, 47, 139, 39);
		rec_lab.setHorizontalAlignment(SwingConstants.CENTER);
		rec_lab.setFont(new Font("Lucida Grande", 1, 18)); 
		rec_lab.setForeground(new Color(0, 153, 51));
		rec_pane.add(rec_lab);
		
		claim_pane = new JPanel();
		claim_pane.setLayout(null);
		claim_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		claim_pane.setBackground(Color.WHITE);
		claim_pane.setBounds(231, 81, 179, 141);
		menuPane.add(claim_pane);
		
		claim_lab = new JLabel("Claim Report");
		claim_lab.setHorizontalAlignment(SwingConstants.CENTER);
		claim_lab.setForeground(new Color(0, 153, 51));
		claim_lab.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		claim_lab.setBounds(26, 56, 127, 22);
		claim_pane.add(claim_lab);
		
		mc_pane = new JPanel();
		mc_pane.setLayout(null);
		mc_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		mc_pane.setBackground(Color.WHITE);
		mc_pane.setBounds(429, 81, 179, 141);
		menuPane.add(mc_pane);
		
		mc_lab = new JLabel("Pilferage Report");
		mc_lab.setHorizontalAlignment(SwingConstants.CENTER);
		mc_lab.setForeground(new Color(0, 153, 51));
		mc_lab.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		mc_lab.setBounds(16, 40, 157, 50);
		mc_pane.add(mc_lab);

		rb_pane = new JPanel();
		rb_pane.setLayout(null);
		rb_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		rb_pane.setBackground(Color.WHITE);
		rb_pane.setBounds(27, 272, 179, 141);
		menuPane.add(rb_pane);
		
		rb_lab = new JLabel("Flight Report");
		rb_lab.setHorizontalAlignment(SwingConstants.CENTER);
		rb_lab.setForeground(new Color(0, 153, 51));
		rb_lab.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		rb_lab.setBounds(6, 54, 167, 22);
		rb_pane.add(rb_lab);
		
		rv_pane = new JPanel();
		rv_pane.setLayout(null);
		rv_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		rv_pane.setBackground(Color.WHITE);
		rv_pane.setBounds(231, 272, 179, 141);
		menuPane.add(rv_pane);
		
		rv_lab = new JLabel("Luggage Return");
		rv_lab.setHorizontalAlignment(SwingConstants.CENTER);
		rv_lab.setForeground(new Color(0, 153, 51));
		rv_lab.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		rv_lab.setBounds(6, 55, 167, 22);
		rv_pane.add(rv_lab);
		
		lgout_pane = new JPanel();
		lgout_pane.setLayout(null);
		lgout_pane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		lgout_pane.setBackground(Color.WHITE);
		lgout_pane.setBounds(429, 272, 179, 141);
		menuPane.add(lgout_pane);
		
		lgout_lab = new JLabel("Log Out");
		lgout_lab.setHorizontalAlignment(SwingConstants.CENTER);
		lgout_lab.setForeground(new Color(0, 153, 51));
		lgout_lab.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lgout_lab.setBounds(45, 57, 89, 22);
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
    	rec_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(rec_pane, rec_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(rec_pane, rec_lab);
            }
            public void mousePressed(MouseEvent evt) {
            	//new AdminLogin();
                dispose();
            }
        });
    	
    	claim_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(claim_pane, claim_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(claim_pane, claim_lab);
            }
            public void mousePressed(MouseEvent evt) {
                
                //new AgentLogin();
                dispose();
            }
        });
    	
    	mc_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(mc_pane, mc_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(mc_pane, mc_lab);
            }
            public void mousePressed(MouseEvent evt) {
            	//new AdminLogin();
                dispose();
            }
        });
    	
    	rb_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(rb_pane, rb_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(rb_pane, rb_lab);
            }
            public void mousePressed(MouseEvent evt) {
                
                //new AgentLogin();
                dispose();
            }
        });
    	
    	rv_pane.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
            	setColor(rv_pane, rv_lab);
            }
            public void mouseExited(MouseEvent evt) {
            	resetColor(rv_pane, rv_lab);
            }
            public void mousePressed(MouseEvent evt) {
                
                //new AgentLogin();
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
                if(JOptionPane.showConfirmDialog(frame, "Do you really want to log out ?", " Main Menu ", 
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){new WelcomePage();dispose();}
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
