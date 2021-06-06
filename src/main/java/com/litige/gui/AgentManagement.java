package com.litige.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.litige.business.AgentBusiness;
import com.litige.dao.GeneralDao;

public class AgentManagement extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuHelp;
	private JMenu menuAgent;
	
	private JMenuItem menuItemExit;
	
	private JMenuItem menuItemNewAgent;
	private JMenuItem menuItemListAgents;
	private JMenuItem menuItemHelpContent;
	private JMenuItem menuItemHelpAbout;
	
	private AgentPanel agentPanel;
	private JPanel panel;
	
	private JTabbedPane tabbedPane;
	
	private AgentBusiness agentBusiness;
	
	private GeneralDao dao;
	
	public AgentManagement() {
		super("Agent Management");
		
		setVisible(true);
		setLayout(new GridBagLayout());
		
		createMenus();
		createPanel();
		registerEventHandlers();
		
		intializeBusiness();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 700);
		setLocationRelativeTo(null);
	}
	
	private void createMenus() {
		menuBar = new JMenuBar();
		
		menuFile = new JMenu("File");
		menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);
		
		menuAgent = new JMenu("Agent");
		menuItemNewAgent = new JMenuItem("New Agent");
		menuItemListAgents = new JMenuItem("List Agents");
		
		menuAgent.add(menuItemNewAgent);
		menuAgent.add(menuItemListAgents);
		
		
		menuHelp = new JMenu("Help");
		menuItemHelpContent = new JMenuItem("Help Content");
		menuItemHelpAbout = new JMenuItem("About");
		
		menuHelp.add(menuItemHelpContent);
		menuHelp.add(menuItemHelpAbout);		
		
		menuBar.add(menuFile);
		menuBar.add(menuAgent);
		menuBar.add(menuHelp);
		
		setJMenuBar(menuBar);
	}
	
	private void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;		
		constraints.weighty = 1.0;
		
		tabbedPane = new JTabbedPane();
		
		agentPanel = new AgentPanel();
		agentPanel.setMainWindow(this);
		
		tabbedPane.add("Agent", agentPanel);
		
		panel.add(tabbedPane, BorderLayout.CENTER);
		add(panel, constraints);
	}
	
	private void registerEventHandlers() {
		
		menuItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		
		menuItemListAgents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				agentPanel.listAgents();
			}
		});		
		
		menuItemNewAgent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				agentPanel.createNewAgent();
			}
		});
		
		menuItemHelpContent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});		
		
		menuItemHelpAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});	
	}
	
	protected void showAbout() {
		String aboutInfo = "Luggage Claim Management\n"
				+ "Version 1.0.0\n"
				+ "Copyright (C) 2018 by Diallo Alhassan Boner\n"
				+ "All rights reserved.";
		JOptionPane.showMessageDialog(this, aboutInfo, "About", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void showHelp() {
		URI helpWebpage;
		try {
			helpWebpage = new URI("#");
			Desktop.getDesktop().browse(helpWebpage);
		} catch (URISyntaxException | IOException e) {
			JOptionPane.showMessageDialog(this, "Could not load Help content!");
		}
	}
	
	private void intializeBusiness() {
		dao = new GeneralDao();
		
		try {
			dao.connect();
			agentBusiness = new AgentBusiness(dao);
		} catch (Exception ex) {
			String message = "Could not connect to the database!";
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
	}

}
