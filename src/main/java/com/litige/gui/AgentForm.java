package com.litige.gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.litige.dao.Agent;

public class AgentForm extends JDialog {
	private JLabel labelAgentId = new JLabel("Agent ID:");
	private JLabel labelAgentName = new JLabel("Agent Name:");
	private JLabel labelPhone = new JLabel("Phone Number:");
	private JLabel labelAddress = new JLabel("Agent Address:");
	private JLabel labelPassword = new JLabel("Password:");
	
	private JTextField fieldAgentId = new JTextField(20);
	private JTextField fieldAgentName = new JTextField(20);
	private JTextField fieldPhone = new JTextField(20);
	private JTextField fieldAddress = new JTextField(20);
	private JTextField fieldPassword = new JTextField(20);
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private Agent agent;
	
	private JPanel contentPane;
	
	private AgentManagement agentManagement;
	
	public AgentForm(AgentManagement agentManagement) {
		super(agentManagement, true);
		setTitle("Create Claim");
		
		initComponents();
		registerEventHandlers();
		
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private void registerEventHandlers() {
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (validateFormInput()) {
						dispose();					
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
	}
	
	protected boolean validateFormInput() throws ParseException {	
	
		int agentId = Integer.parseInt(fieldAgentId.getText());
		String agentName = fieldAgentName.getText();
		String phone = fieldPhone.getText();
		String address = fieldAddress.getText();
		String password = fieldPassword.getText();
		
		
		if (agentId == 0) {
			JOptionPane.showMessageDialog(this, "Please enter agent ID!");
			fieldAgentId.requestFocus();
			return false;
		} else if (agentName == null) {
			JOptionPane.showMessageDialog(this, "Please enter agent name!");
			fieldAgentName.requestFocus();
			return false;
		} else if (phone == null) {
			JOptionPane.showMessageDialog(this, "Please enter the agent's phone number!");
			fieldPhone.requestFocus();
			return false;
		}else if (address == null) {
			JOptionPane.showMessageDialog(this, "Please enter the address of the agent!");
			fieldAddress.requestFocus();
			return false;
		} else if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the password of the agent!");
			fieldPassword.requestFocus();
			return false;
		}
		
		if (agent == null) {		
			agent = new Agent();
		}
		
		agent.setId(agentId);
		agent.setName(agentName);
		agent.setPhone(phone);
		agent.setAddress(address);
		agent.setPassword(password);
		
		return true;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		setTitle("Edit Agent");
		fieldAgentId.setText(String.valueOf(agent.getId()));
		fieldAgentName.setText(agent.getName());
		fieldPhone.setText(agent.getPhone());
		fieldAddress.setText(agent.getAddress());
		fieldPassword.setText(agent.getPassword());
		
		this.agent = agent;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initComponents() {
		setBounds(100, 100, 636, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		labelAgentId.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelAgentId.setBounds(57, 45, 128, 34);
		contentPane.add(labelAgentId);
		
		fieldAgentId.setColumns(10);
		fieldAgentId.setBounds(243, 40, 190, 39);
		contentPane.add(fieldAgentId);
		//
		labelAgentName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelAgentName.setBounds(57, 96, 178, 35);
		contentPane.add(labelAgentName);
		
		fieldAgentName.setBounds(243, 91, 190, 40);
		contentPane.add(fieldAgentName);
		fieldAgentName.setColumns(10);
		//
		labelPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelPhone.setBounds(57, 148, 178, 34);
		contentPane.add(labelPhone);
		
		fieldPhone.setBounds(243, 143, 190, 39);
		contentPane.add(fieldPhone);
		fieldPhone.setColumns(10);
		//
		labelAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelAddress.setBounds(57, 205, 178, 39);
		contentPane.add(labelAddress);
		
		fieldAddress.setBounds(243, 200, 190, 39);
		contentPane.add(fieldAddress);
		fieldAddress.setColumns(10);
		//
		labelPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelPassword.setBounds(57, 259, 169, 34);
		contentPane.add(labelPassword);
		
		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(243, 253, 190, 39);
		contentPane.add(fieldPassword);
		
		btnSave.setBounds(476, 57, 128, 70);
		contentPane.add(btnSave);
		
		btnCancel.setBounds(476, 177, 128, 67);
		contentPane.add(btnCancel);
		
		add(contentPane);
	}

}
