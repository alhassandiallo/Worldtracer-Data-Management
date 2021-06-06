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

import com.litige.business.ClaimBusiness;
import com.litige.dao.Claim;
import com.litige.dao.Luggage;

public class ClaimPanel extends JPanel {
	private JTable table = new JTable();
	private ClaimTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Claim> listClaims = new ArrayList<>();
	
	private MainWindow mainWindow;
	private ClaimBusiness claimBusiness;
	
	public ClaimPanel() {
		setVisible(true);
		initComponents();
		registerEventHandlers();
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListClaim(List<Claim> listClaims) {
		tableModel.setListClaim(listClaims);
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
				
		tableModel = new ClaimTableModel();
		
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
				createNewClaim();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editClaim();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteClaim();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listClaims();				
			}
		});		
	}
	
	protected void createNewClaim() {
		ClaimForm claimForm = new ClaimForm(mainWindow);
		claimForm.setVisible(true);
		
		Claim claim = claimForm.getClaim();
		
		if (claim != null) {
			try {
				claimBusiness.save(claim);
				listClaims();
			} catch (Exception ex) {
				String message = "Could not save new claim. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editClaim(Claim claim) {
		ClaimForm claimForm = new ClaimForm(mainWindow);
		
		claimForm.setClaim(claim);
		claimForm.setVisible(true);
		
		Claim editedClaim = claimForm.getClaim();
		
		if (editedClaim != null) {
			try {
				claimBusiness.save(editedClaim);
				listClaims();
			} catch (Exception ex) {
				String message = "Could not update claim. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}
	
	protected void listClaims() {
		try {
			List<Claim> listClaims = claimBusiness.listAll();
			ClaimPanel claimPanel = new ClaimPanel();
			JTabbedPane tabbedPane = new JTabbedPane();
			claimPanel.setListClaim(listClaims);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load claim list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}
	
	protected void deleteClaim(Claim claim) {
		try {
			claimBusiness.delete(claim);
			listClaims();
		} catch (Exception ex) {
			String message = "Could not delete claim. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void editClaim() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Claim claimToEdit = tableModel.getClaimAt(selectedRow);
			
			if (claimToEdit != null) {
				editClaim(claimToEdit);
			}
		}		
	}
	
	protected void deleteClaim() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Claim claimToDelete = tableModel.getClaimAt(selectedRow);
			
			if (claimToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", claimToDelete.getClaimId()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					deleteClaim(claimToDelete);
				}				
			}
		}
	}
	
}
