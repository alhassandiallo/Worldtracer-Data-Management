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

import com.litige.business.DestinationBusiness;
import com.litige.dao.Delivery;
import com.litige.dao.Destination;

public class DestinationPanel extends JPanel {
	private JTable table = new JTable();
	private DestinationTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Destination> listDest = new ArrayList<>();
	
	private MainWindow mainWindow;
	private DestinationBusiness destBusiness;
	
	public DestinationPanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListDest(List<Destination> listDests) {
		tableModel.setListDest(listDests);
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
				
		tableModel = new DestinationTableModel();
		
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
				createNewDest();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editDest();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteDest();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listDest();				
			}
		});		
	}
	
	protected void createNewDest() {
		DestinationForm destForm = new DestinationForm(mainWindow);
		destForm.setVisible(true);
		
		Destination dest = destForm.getDest();
		
		if (dest != null) {
			try {
				destBusiness.save(dest);
				listDest();
			} catch (Exception ex) {
				String message = "Could not save new destination. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editDest(Destination dest) {
		DestinationForm destForm = new DestinationForm(mainWindow);
		
		destForm.setDest(dest);
		destForm.setVisible(true);
		
		Destination editedDest = destForm.getDest();
		
		if (editedDest != null) {
			try {
				destBusiness.save(editedDest);
				listDest();
			} catch (Exception ex) {
				String message = "Could not update destination. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listDest() {
		try {
			List<Destination> listDest = destBusiness.listAll();
			DestinationPanel destPanel = new DestinationPanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			destPanel.setListDest(listDest);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load destination list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void deleteDest(Destination dest) {
		try {
			destBusiness.delete(dest);
			listDest();
		} catch (Exception ex) {
			String message = "Could not delete destination. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void editDest() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Destination destToEdit = tableModel.getDestAt(selectedRow);
			
			if (destToEdit != null) {
				editDest(destToEdit);
			}
		}		
	}
	
	protected void deleteDest() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Destination destToDelete = tableModel.getDestAt(selectedRow);
			
			if (destToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", destToDelete.getId()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deleteDest(destToDelete);
				}				
			}
		}
	}

}
