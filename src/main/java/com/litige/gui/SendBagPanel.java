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

import com.litige.business.SendBagBusiness;
import com.litige.dao.Pilferage;
import com.litige.dao.SendBag;

public class SendBagPanel extends JPanel {
	private JTable table = new JTable();
	private SendBagTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<SendBag> listSendBag = new ArrayList<>();
	
	private MainWindow mainWindow;
	private SendBagBusiness sendBagBusiness;
	
	public SendBagPanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListSendBag(List<SendBag> listSendBag) {
		tableModel.setlistSendBag(listSendBag);
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
				
		tableModel = new SendBagTableModel();
		
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
				createNewSendBag();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editSendBag();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSendBag();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listSendBags();				
			}
		});		
	}
	
	protected void createNewSendBag() {
		SendBagForm sendBagForm = new SendBagForm(mainWindow);
		sendBagForm.setVisible(true);
		
		SendBag sendBag = sendBagForm.getSendBag();
		
		if (sendBag != null) {
			try {
				sendBagBusiness.save(sendBag);
				listSendBags();
			} catch (Exception ex) {
				String message = "Could not save new sendBag. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editSendBag(SendBag sendBag) {
		SendBagForm sendBagForm = new SendBagForm(mainWindow);
		
		sendBagForm.setSendBag(sendBag);
		sendBagForm.setVisible(true);
		
		SendBag editedSendBag = sendBagForm.getSendBag();
		
		if (editedSendBag != null) {
			try {
				sendBagBusiness.save(editedSendBag);
				listSendBags();
			} catch (Exception ex) {
				String message = "Could not update sendBag. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listSendBags() {
		try {
			List<SendBag> listSendBags = sendBagBusiness.listAll();
			SendBagPanel sendBagPanel = new SendBagPanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			sendBagPanel.setListSendBag(listSendBags);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load sendbag list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void deleteSendBag(SendBag sendBag) {
		try {
			sendBagBusiness.delete(sendBag);
			listSendBags();
		} catch (Exception ex) {
			String message = "Could not delete sendBag. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void editSendBag() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			SendBag sendBagToEdit = tableModel.getSendBagAt(selectedRow);
			
			if (sendBagToEdit != null) {
				editSendBag(sendBagToEdit);
			}
		}		
	}
	
	protected void deleteSendBag() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			SendBag sendBagToDelete = tableModel.getSendBagAt(selectedRow);
			
			if (sendBagToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", sendBagToDelete.getCoSendBag()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deleteSendBag(sendBagToDelete);
				}				
			}
		}
	}

}
