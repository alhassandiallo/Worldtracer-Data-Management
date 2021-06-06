package com.litige.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.litige.business.AgentBusiness;
import com.litige.dao.Agent;
import com.litige.dao.Claim;

public class AgentPanel extends JPanel {
	private JTable table = new JTable();
	private AgentTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Agent> listAgent = new ArrayList<>();
	
	private AgentManagement agentManagement;
	private AgentBusiness agentBusiness;
	
	public AgentPanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}
	
	public void setMainWindow(AgentManagement agentManagement) {
		this.agentManagement = agentManagement;
	}	
	
	public void setListAgent(List<Agent> listAgent) {
		tableModel.setListAgent(listAgent);
		tableModel.fireTableDataChanged();
	}
	
	private void initComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 5.0;		
		constraints.weighty = 1.0;
		constraints.insets = new Insets(10, 10, 10, 10);
				
		tableModel = new AgentTableModel();
		
		table.setModel(tableModel);
		add(new JScrollPane(table), constraints);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
		panelButtons.setPreferredSize(new Dimension(150, 100));
		
		buttonNew.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		buttonEdit.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
		buttonDelete.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
		buttonRefresh.setIcon(new ImageIcon(getClass().getResource("/icons/refresh.png")));
		
		panelButtons.add(buttonNew);
		panelButtons.add(buttonEdit);
		panelButtons.add(buttonDelete);
		panelButtons.add(buttonRefresh);
		
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.weightx = 0.0;		
		constraints.weighty = 1.0;		

		add(panelButtons, constraints);
	}
	
	private void registerEventHandlers() {
		buttonNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewAgent();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editAgent();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAgent();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listAgents();				
			}
		});		
	}
	
	protected void createNewAgent() {
		AgentForm agentForm = new AgentForm(agentManagement);
		agentForm.setVisible(true);
		
		Agent agent = agentForm.getAgent();
		
		if (agent != null) {
			try {
				agentBusiness.save(agent);
				listAgents();
			} catch (Exception ex) {
				String message = "Could not save new agent. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editAgent(Agent agent) {
		AgentForm agentForm = new AgentForm(agentManagement);
		
		agentForm.setAgent(agent);
		agentForm.setVisible(true);
		
		Agent editedAgent = agentForm.getAgent();
		
		if (editedAgent != null) {
			try {
				agentBusiness.save(editedAgent);
				listAgents();
			} catch (Exception ex) {
				String message = "Could not update agent. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listAgents() {
		try {
			List<Agent> listAgents = agentBusiness.listAll();
			AgentPanel agentPanel = new AgentPanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			agentPanel.setListAgent(listAgents);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load agent list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void deleteAgent(Agent agent) {
		try {
			agentBusiness.delete(agent);
			listAgents();
		} catch (Exception ex) {
			String message = "Could not delete agent. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void editAgent() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Agent agentToEdit = tableModel.getAgentAt(selectedRow);
			
			if (agentToEdit != null) {
				editAgent(agentToEdit);
			}
		}		
	}
	
	protected void deleteAgent() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Agent agentToDelete = tableModel.getAgentAt(selectedRow);
			
			if (agentToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", agentToDelete.getId()); 
				int answer = JOptionPane.showConfirmDialog(agentManagement, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deleteAgent(agentToDelete);
				}				
			}
		}
	}

}
