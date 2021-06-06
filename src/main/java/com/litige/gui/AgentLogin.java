package com.litige.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AgentLogin extends JFrame {

	private JPanel contentPane;
	private JLabel con_lab;
	private JLabel id_lab;
	private JLabel pass_lab;
	private JTextField idField;
	private JPasswordField passField;
	private JButton con_button;


	/**
	 * Create the frame.
	 */
	public AgentLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 272);
		setVisible(true);
		setResizable(false);
		initialize();
		creatEvent();
		centerView();
	}
	private void initialize() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);
		
		con_lab = new JLabel("Agent Login");
		con_lab.setFont(new Font("Lucida Grande", 1, 24));
		con_lab.setBounds(84, 18, 232, 31);
		contentPane.add(con_lab);
		
		id_lab = new JLabel("Username");
		id_lab.setFont(new Font("Lucida Grande", 1, 18));
		id_lab.setBounds(26, 79, 96, 41);
		contentPane.add(id_lab);
		
		idField = new JTextField();
		idField.setBounds(146, 80, 204, 39);
		contentPane.add(idField);
		idField.setColumns(10);
		
		pass_lab = new JLabel("Password");
		pass_lab.setFont(new Font("Lucida Grande", 1, 18));
		pass_lab.setBounds(26, 146, 96, 16);
		contentPane.add(pass_lab);
		
		passField = new JPasswordField();
		passField.setBounds(146, 134, 204, 41);
		contentPane.add(passField);
		
		con_button = new JButton("Connect");
		con_button.setFont(new Font("Lucida Grande", 1, 18));
		con_button.setBounds(124, 187, 149, 50);
		contentPane.add(con_button);
		
		getContentPane().add(contentPane);
	}
	
	private void creatEvent() {
		con_button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	//setColor(con_button);
            }
            public void mouseExited(MouseEvent evt) {
            	//resetColor(con_button);
            }
            public void mousePressed(MouseEvent evt) {
            	String identifiant = idField.getText();
                String password = String.valueOf(passField.getPassword());
          
                int attempt = 0;
				if(identifiant.equals("banna") && password.equals("test1234")){
	                new MainWindow();
	                dispose();
	            }else{
                    JOptionPane.showMessageDialog(AgentLogin.this, "Invalid login details","Login Error!", 
                    		JOptionPane.ERROR_MESSAGE);
                    idField.setText(null);
    				passField.setText(null);
                                    attempt+=1;
                }
                if(attempt == 3){
                    System.exit(0);
                }
            }
        });
	}
	
	public void setColor(JButton button){
        button.setForeground(new Color(255, 255, 255));
        button.setBackground(new Color(0,153,51));
        
    }
    
    public void resetColor(JButton button){
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(new Color(0,153,51));
    }
    
    private void centerView() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        this.setLocation((screen.width - frameSize.width) >> 1,
                          (screen.height - frameSize.height) >> 1);
    }

}
