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

import com.litige.business.LuggageBusiness;
import com.litige.dao.Luggage;

public class BagPanel extends JPanel {
	private JTable table = new JTable();
	private BagTableModel tableModel;
	private LuggageBusiness bagBusiness;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Luggage> listBags = new ArrayList<>();
	
	private MainWindow mainWindow;
	public BagPanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListBag(List<Luggage> listBags) {
		tableModel.setListBag(listBags);
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
				
		tableModel = new BagTableModel();
		
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
				//mainWindow.createNewBag();
				createNewBag();
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editBag();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteBag();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//mainWindow.listBags();	
				listBags();
			}
		});		
	}
	
	protected void createNewBag() {
		BagForm bagForm = new BagForm(mainWindow);
		bagForm.setVisible(true);
		
		Luggage bag = bagForm.getBag();
		
		if (bag != null) {
			try {
				bagBusiness.save(bag);
				listBags();
			} catch (Exception ex) {
				String message = "Could not save new bag. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editBag() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Luggage bagToEdit = tableModel.getBagAt(selectedRow);
			
			if (bagToEdit != null) {
				editBag(bagToEdit);
			}
		}		
	}
	
	protected void editBag(Luggage bag) {
		BagForm bagForm = new BagForm(mainWindow);
		
		bagForm.setBag(bag);
		bagForm.setVisible(true);
		
		Luggage editedBag = bagForm.getBag();
		
		if (editedBag != null) {
			try {
				bagBusiness.save(editedBag);
				listBags();
			} catch (Exception ex) {
				String message = "Could not update bag. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listBags() {
		try {
			List<Luggage> listBags = bagBusiness.listAll();
			BagPanel bagPanel = new BagPanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			bagPanel.setListBag(listBags);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load bag list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}

	protected void deleteBag() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Luggage bagToDelete = tableModel.getBagAt(selectedRow);
			
			if (bagToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", bagToDelete.getTagNumber()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deleteBag(bagToDelete);
				}				
			}
		}
	}
	
	protected void deleteBag(Luggage bag) {
		try {
			bagBusiness.delete(bag);
			listBags();
		} catch (Exception ex) {
			String message = "Could not delete bag. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}

}



