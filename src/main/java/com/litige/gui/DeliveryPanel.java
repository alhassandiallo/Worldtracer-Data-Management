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

import com.litige.business.DeliveryBusiness;
import com.litige.dao.Claim;
import com.litige.dao.Delivery;

public class DeliveryPanel extends JPanel{
	private JTable table = new JTable();
	private DeliveryTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Delivery> listDeliveries = new ArrayList<>();
	
	private MainWindow mainWindow;
	private DeliveryBusiness deliveryBusiness;
	
	public DeliveryPanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListDelivery(List<Delivery> listDeliveries) {
		tableModel.setListDelivery(listDeliveries);
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
				
		tableModel = new DeliveryTableModel();
		
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
				createNewDelivery();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editDelivery();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteDelivery();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listDeliveries();				
			}
		});		
	}
	
	protected void createNewDelivery() {
		DeliveryForm deliveryForm = new DeliveryForm(mainWindow);
		deliveryForm.setVisible(true);
		
		Delivery delivery = deliveryForm.getDelivery();
		
		if (delivery != null) {
			try {
				deliveryBusiness.save(delivery);
				listDeliveries();
			} catch (Exception ex) {
				String message = "Could not save new delivery. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editDelivery(Delivery delivery) {
		DeliveryForm deliveryForm = new DeliveryForm(mainWindow);
		
		deliveryForm.setDelivery(delivery);
		deliveryForm.setVisible(true);
		
		Delivery editedDelivery = deliveryForm.getDelivery();
		
		if (editedDelivery != null) {
			try {
				deliveryBusiness.save(editedDelivery);
				listDeliveries();
			} catch (Exception ex) {
				String message = "Could not update delivery. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listDeliveries() {
		try {
			List<Delivery> listDeliveries = deliveryBusiness.listAll();
			DeliveryPanel deliveryPanel = new DeliveryPanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			deliveryPanel.setListDelivery(listDeliveries);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load delivery list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void deleteDelivery(Delivery delivery) {
		try {
			deliveryBusiness.delete(delivery);
			listDeliveries();
		} catch (Exception ex) {
			String message = "Could not delete delivery. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void editDelivery() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Delivery deliveryToEdit = tableModel.getDeliveryAt(selectedRow);
			
			if (deliveryToEdit != null) {
				editDelivery(deliveryToEdit);
			}
		}		
	}
	
	protected void deleteDelivery() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Delivery deliveryToDelete = tableModel.getDeliveryAt(selectedRow);
			
			if (deliveryToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", deliveryToDelete.getDeliveryId()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deleteDelivery(deliveryToDelete);
				}				
			}
		}
	}

}
