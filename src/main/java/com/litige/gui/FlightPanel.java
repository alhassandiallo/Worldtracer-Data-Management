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

import com.litige.business.FlightBusiness;
import com.litige.dao.Flight;

public class FlightPanel extends JPanel {
	private JTable table = new JTable();
	private FlightTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Flight> listFlights = new ArrayList<>();
	
	private MainWindow mainWindow;
	private FlightBusiness flightBusiness;
	
	public FlightPanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListFlight(List<Flight> listFlights) {
		tableModel.setListFlight(listFlights);
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
				
		tableModel = new FlightTableModel();
		
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
				createNewFlight();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editFlight();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteFlight();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listFlights();				
			}
		});		
	}
	
	protected void createNewFlight() {
		FlightForm flightForm = new FlightForm(mainWindow);
		flightForm.setVisible(true);
		
		Flight flight = flightForm.getFlight();
		
		if (flight != null) {
			try {
				flightBusiness.save(flight);
				listFlights();
			} catch (Exception ex) {
				String message = "Could not save new flight. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editFlight(Flight flight) {
		FlightForm flightForm = new FlightForm(mainWindow);
		
		flightForm.setFlight(flight);
		flightForm.setVisible(true);
		
		Flight editedFlight = flightForm.getFlight();
		
		if (editedFlight != null) {
			try {
				flightBusiness.save(editedFlight);
				listFlights();
			} catch (Exception ex) {
				String message = "Could not update flight. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listFlights() {
		try {
			List<Flight> listFlights = flightBusiness.listAll();
			FlightPanel flightPanel = new FlightPanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			flightPanel.setListFlight(listFlights);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load flight list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void deleteFlight(Flight flight) {
		try {
			flightBusiness.delete(flight);
			listFlights();
		} catch (Exception ex) {
			String message = "Could not delete flight. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void editFlight() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Flight flightToEdit = tableModel.getFlightAt(selectedRow);
			
			if (flightToEdit != null) {
				editFlight(flightToEdit);
			}
		}		
	}
	
	protected void deleteFlight() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Flight flightToDelete = tableModel.getFlightAt(selectedRow);
			
			if (flightToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", flightToDelete.getCodeFlight()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deleteFlight(flightToDelete);
				}				
			}
		}
	}

}
