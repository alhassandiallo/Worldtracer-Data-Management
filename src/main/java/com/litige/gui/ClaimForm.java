package com.litige.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.litige.dao.Agent;
import com.litige.dao.Claim;
import com.litige.dao.Delivery;

public class ClaimForm extends JDialog {
	private JLabel labelclaimId = new JLabel("Claim ID:");
	private JLabel labelpassengerName = new JLabel("Passenger Name:");
	private JLabel labeldateClaim = new JLabel("Date of Claim:");
	private JLabel labelnumberOfBags = new JLabel("Number of Bags:");
	private JLabel labelpassengerAddress = new JLabel("Passenger Address:");
	private JLabel labelphone = new JLabel("Phone:");
	private JLabel labelagent = new JLabel("Agent:");
	private JLabel labeldelivery = new JLabel("Delivery:");
	
	private JTextField fieldclaimId = new JTextField(20);
	private JTextField fieldpassengerName = new JTextField(20);
	private JTextField fielddateClaim = new JTextField(20);
	private JTextField fieldnumberOfBags = new JTextField(20);
	private JTextField fieldpassengerAddress = new JTextField(20);
	private JTextField fieldphone = new JTextField(20);
	
	private JComboBox<Agent> comboAgent = new JComboBox<>();
	private JComboBox<Delivery> comboDelivery = new JComboBox<>();
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private Claim claim;
	
	private JPanel contentPane;
	
	public ClaimForm(MainWindow owner) {
		super(owner, true);
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
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);	
	
		int claimId = Integer.parseInt(fieldclaimId.getText());
		String passengerName = fieldpassengerName.getText();
		Date dateClaim = format.parse(fielddateClaim.getText());
		int numberOfBags = Integer.parseInt(fieldnumberOfBags.getText());
		String passengerAddress = fieldpassengerAddress.getText();
		String phone = fieldphone.getText();
		
		Object selectedAgent = comboAgent.getSelectedItem();
		Object selectedDelivery = comboDelivery.getSelectedItem();
		
		
		if (claimId == 0) {
			JOptionPane.showMessageDialog(this, "Please enter claim number!");
			fieldclaimId.requestFocus();
			return false;
		} else if (passengerName == null) {
			JOptionPane.showMessageDialog(this, "Please enter passenger name!");
			fieldpassengerName.requestFocus();
			return false;
		} else if (dateClaim == null) {
			JOptionPane.showMessageDialog(this, "Please enter the date of the claim!");
			fielddateClaim.requestFocus();
			return false;
		}else if (numberOfBags == 0) {
			JOptionPane.showMessageDialog(this, "Please enter the number of bags!");
			fieldnumberOfBags.requestFocus();
			return false;
		} else if (passengerAddress.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the address of the passenger!");
			fieldpassengerAddress.requestFocus();
			return false;
		}else if (selectedAgent == null) {
			JOptionPane.showMessageDialog(this, "Please enter the ID number of the agent!");
			comboAgent.requestFocus();
			return false;
		}
		
		if (claim == null) {		
			claim = new Claim();
		}
		
		claim.setClaimId(claimId);
		claim.setPassengerName(passengerName);
		claim.setDateClaim(dateClaim);
		claim.setNumberOfBags(numberOfBags);
		claim.setPassengerAddress(passengerAddress);
		claim.setPhone(phone);
		claim.setAgent((Agent) selectedAgent);
		claim.setDelivery((Delivery) selectedDelivery);
		
		return true;
	}
	
	public Claim getClaim() {
		return claim;
	}
	
	public void setClaim(Claim claim) {
		setTitle("Edit Claim");
		fieldclaimId.setText(String.valueOf(claim.getClaimId()));
		fieldpassengerName.setText(claim.getPassengerName());
		fielddateClaim.setText(String.valueOf(claim.getDateClaim()));
		fieldnumberOfBags.setText(String.valueOf(claim.getNumberOfBags()));
		fieldpassengerAddress.setText(claim.getPassengerAddress());
		fieldphone.setText(claim.getPhone());
		
		comboAgent.setSelectedItem(claim.getAgent());
		comboDelivery.setSelectedItem(claim.getDelivery());
		this.claim = claim;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents() {
		setBounds(100, 100, 683, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		labelclaimId.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelclaimId.setBounds(57, 45, 105, 21);
		contentPane.add(labelclaimId);
		
		fieldclaimId.setColumns(10);
		fieldclaimId.setBounds(243, 40, 190, 39);
		contentPane.add(fieldclaimId);
		
		labelpassengerName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelpassengerName.setBounds(57, 96, 178, 35);
		contentPane.add(labelpassengerName);
		
		fieldpassengerName.setBounds(243, 91, 190, 40);
		contentPane.add(fieldpassengerName);
		fieldpassengerName.setColumns(10);
		
		labeldateClaim.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labeldateClaim.setBounds(57, 148, 146, 34);
		contentPane.add(labeldateClaim);
		
		fielddateClaim.setBounds(243, 143, 190, 39);
		contentPane.add(fielddateClaim);
		fielddateClaim.setColumns(10);
		
		labelnumberOfBags.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelnumberOfBags.setBounds(57, 205, 178, 39);
		contentPane.add(labelnumberOfBags);
		
		fieldnumberOfBags.setBounds(243, 200, 190, 39);
		contentPane.add(fieldnumberOfBags);
		fieldnumberOfBags.setColumns(10);
		
		labelpassengerAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelpassengerAddress.setBounds(57, 259, 198, 34);
		contentPane.add(labelpassengerAddress);
		
		fieldpassengerAddress.setBounds(243, 254, 190, 39);
		contentPane.add(fieldpassengerAddress);
		fieldpassengerAddress.setColumns(10);
		
		labelphone.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelphone.setBounds(57, 313, 94, 34);
		contentPane.add(labelphone);
		
		fieldphone.setBounds(243, 308, 190, 39);
		contentPane.add(fieldphone);
		fieldphone.setColumns(10);
		
		labelagent.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelagent.setBounds(57, 359, 94, 39);
		contentPane.add(labelagent);
		
		comboAgent.setBounds(243, 369, 190, 27);
		contentPane.add(comboAgent);
	
		
		labeldelivery.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labeldelivery.setBounds(57, 410, 94, 42);
		contentPane.add(labeldelivery);
		
		comboDelivery.setBounds(243, 420, 190, 39);
		contentPane.add(comboDelivery);
		
		btnSave.setBounds(476, 112, 128, 70);
		contentPane.add(btnSave);
		
		btnCancel.setBounds(476, 240, 128, 67);
		contentPane.add(btnCancel);
		
		add(contentPane);		
		
	}

}
