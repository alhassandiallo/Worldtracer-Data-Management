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

import com.litige.business.PilferageBusiness;
import com.litige.dao.Pilferage;

public class PilferagePanel extends JPanel {
	private JTable table = new JTable();
	private PilferageTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Pilferage> listPilfs = new ArrayList<>();
	
	private MainWindow mainWindow;
	private PilferageBusiness pilfBusiness;
	
	public PilferagePanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListPilf(List<Pilferage> listPilfs) {
		tableModel.setListPilf(listPilfs);
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
				
		tableModel = new PilferageTableModel();
		
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
				createNewPilf();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editPilf();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deletePilf();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPilfs();				
			}
		});		
	}
	
	protected void createNewPilf() {
		PilferageForm pilfForm = new PilferageForm(mainWindow);
		pilfForm.setVisible(true);
		
		Pilferage pilf = pilfForm.getPilferage();
		
		if (pilf != null) {
			try {
				pilfBusiness.save(pilf);
				listPilfs();
			} catch (Exception ex) {
				String message = "Could not save new pilferage. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editPilf(Pilferage pilf) {
		PilferageForm pilfForm = new PilferageForm(mainWindow);
		
		pilfForm.setPilferage(pilf);
		pilfForm.setVisible(true);
		
		Pilferage editedPilf = pilfForm.getPilferage();
		
		if (editedPilf != null) {
			try {
				pilfBusiness.save(editedPilf);
				listPilfs();
			} catch (Exception ex) {
				String message = "Could not update pilferage. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listPilfs() {
		try {
			List<Pilferage> listPilfs = pilfBusiness.listAll();
			PilferagePanel pilfPanel = new PilferagePanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			pilfPanel.setListPilf(listPilfs);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load pilferage list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void deletePilf(Pilferage pilf) {
		try {
			pilfBusiness.delete(pilf);
			listPilfs();
		} catch (Exception ex) {
			String message = "Could not delete pilferage. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void editPilf() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Pilferage pilfToEdit = tableModel.getPilfAt(selectedRow);
			
			if (pilfToEdit != null) {
				editPilf(pilfToEdit);
			}
		}		
	}
	
	protected void deletePilf() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Pilferage pilfToDelete = tableModel.getPilfAt(selectedRow);
			
			if (pilfToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", pilfToDelete.getCodePilf()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deletePilf(pilfToDelete);
				}				
			}
		}
	}

}
