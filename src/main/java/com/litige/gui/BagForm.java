package com.litige.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.litige.dao.Flight;
import com.litige.dao.Luggage;
import com.litige.dao.Pilferage;
import com.litige.dao.SendBag;

public class BagForm extends JDialog {
	private JLabel labelnumBag = new JLabel("Bag Number:");
	private JLabel labeltagNumber = new JLabel("Tag Number:");
	private JLabel labelnameOnTag = new JLabel("Name on Tag:");
	private JLabel labelreceivedWeight = new JLabel("Weight:");
	private JLabel labeltype = new JLabel("Type of Bag:");
	private JLabel labelcolor = new JLabel("Color of Bag:");
	private JLabel labelstate = new JLabel("State of Bag:");
	private JLabel labelclaimid = new JLabel("Claim ID:");
	private JLabel labelcoFlight = new JLabel("Flight:");
	private JLabel labelcodePilf = new JLabel("Code Pilferage:");
	private JLabel labelagentId = new JLabel("Agent ID:");
	private JLabel labelDelivery = new JLabel("Delivery ID:");
	private JLabel labelSendBag = new JLabel("SendBag:");
	
	private JTextField fieldnumBag = new JTextField(20);
	private JTextField fieldtagNumber = new JTextField(20);
	private JTextField fieldnameOnTag = new JTextField(20);
	private JTextField fieldreceivedWeight = new JTextField(20);
	private JTextField fieldtype = new JTextField(20);
	private JTextField fieldcolor = new JTextField(20);
	private JTextField fieldstate = new JTextField(20);
	
	private JComboBox<Claim> comboClaim = new JComboBox<>();
	private JComboBox<Flight> comboFlight = new JComboBox<>();
	private JComboBox<Pilferage> comboPilf = new JComboBox<>();
	private JComboBox<Agent> comboAgent = new JComboBox<>();
	private JComboBox<Delivery> comboDelivery = new JComboBox<>();
	private JComboBox<SendBag> comboSendBag = new JComboBox<>();
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private Luggage bag;

	private JPanel contentPane;


	/**
	 * Create the dialog.
	 */
	public BagForm(MainWindow owner) {
		super(owner, true);
		setTitle("Create Bag");
		initComponents();
		registerEventHandlers();
		setResizable(false);
		setLocationRelativeTo(null);
	}


	private void registerEventHandlers() {
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFormInput()) {
					dispose();					
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

	protected boolean validateFormInput() {
		
		int numBag = Integer.parseInt(fieldnumBag.getText());
		String tagNumber = fieldtagNumber.getText();
		String nameOnTag = fieldnameOnTag.getText();
		double receivedWeight = Double.parseDouble(fieldreceivedWeight.getText());
		String type = fieldtype.getText();
		String color = fieldcolor.getText();
		String state = fieldstate.getText();
		Object selectedClaim = comboClaim.getSelectedItem();
		Object selectedFlight = comboFlight.getSelectedItem();
		Object selectedPilf = comboPilf.getSelectedItem();
		Object selectedAgent = comboAgent.getSelectedItem();
		Object selectedDelivery = comboDelivery.getSelectedItem();
		Object selectedSendBag = comboSendBag.getSelectedItem();
		
		if (numBag == 0) {
			JOptionPane.showMessageDialog(this, "Please enter product name!");
			fieldnumBag.requestFocus();
			return false;
		} else if (receivedWeight == 0.0) {
			JOptionPane.showMessageDialog(this, "Please enter weight!");
			fieldreceivedWeight.requestFocus();
			return false;
		} else if (type.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the type of the bag!");
			fieldtype.requestFocus();
			return false;
		}else if (color.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the color of the bag!");
			fieldcolor.requestFocus();
			return false;
		} else if (state.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the state of the bag!");
			fieldstate.requestFocus();
			return false;
		}else if (selectedAgent == null) {
			JOptionPane.showMessageDialog(this, "Please enter the ID number of the agent!");
			comboAgent.requestFocus();
			return false;
		}
		
		if (bag == null) {		
			bag = new Luggage();
		}
		
		bag.setNumBag(numBag);
		bag.setTagNumber(tagNumber);
		bag.setNameOnTag(nameOnTag);
		bag.setReceivedWeight(receivedWeight);
		bag.setType(type);
		bag.setColor(color);
		bag.setState(state);
		bag.setClaim((Claim) selectedClaim);
		bag.setFlight((Flight) selectedFlight);
		bag.setPilferage((Pilferage) selectedPilf);
		bag.setAgent((Agent) selectedAgent);
		bag.setDelivery((Delivery) selectedDelivery);
		bag.setSendBag((SendBag) selectedSendBag);
		
		return true;
	}

	public Luggage getBag() {
		return bag;
	}	
	
	public void setBag(Luggage bag) {
		setTitle("Edit Bag");
		fieldnumBag.setText(String.valueOf(bag.getNumBag()));
		fieldtagNumber.setText(bag.getTagNumber());
		fieldnameOnTag.setText(bag.getNameOnTag());
		fieldreceivedWeight.setText(String.valueOf(bag.getReceivedWeight()));
		fieldtype.setText(bag.getType());
		fieldcolor.setText(bag.getColor());
		fieldstate.setText(bag.getState());
		comboClaim.setSelectedItem(bag.getClaim());
		comboFlight.setSelectedItem(bag.getFlight());
		comboPilf.setSelectedItem(bag.getPilferage());
		comboAgent.setSelectedItem(bag.getAgent());
		comboDelivery.setSelectedItem(bag.getDelivery());
		comboSendBag.setSelectedItem(bag.getSendBag());
		this.bag = bag;
	}
	
	
	private void initComponents() {
		setBounds(100, 100, 683, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		labelnumBag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelnumBag.setBounds(29, 26, 165, 32);
		contentPane.add(labelnumBag);
		
		fieldnumBag.setColumns(10);
		fieldnumBag.setBounds(206, 26, 232, 32);
		contentPane.add(fieldnumBag);
		
		labeltagNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labeltagNumber.setBounds(29, 70, 134, 26);
		contentPane.add(labeltagNumber);
		
		fieldtagNumber.setBounds(206, 65, 232, 32);
		contentPane.add(fieldtagNumber);
		fieldtagNumber.setColumns(10);
		
		labelnameOnTag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelnameOnTag.setBounds(29, 110, 134, 30);
		contentPane.add(labelnameOnTag);
		
		fieldnameOnTag.setBounds(206, 108, 232, 32);
		contentPane.add(fieldnameOnTag);
		fieldnameOnTag.setColumns(10);
		
		labelreceivedWeight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelreceivedWeight.setBounds(29, 152, 118, 24);
		contentPane.add(labelreceivedWeight);
		
		fieldreceivedWeight.setBounds(206, 152, 232, 32);
		contentPane.add(fieldreceivedWeight);
		fieldreceivedWeight.setColumns(10);
		
		labeltype.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labeltype.setBounds(29, 197, 134, 24);
		contentPane.add(labeltype);
		
		fieldtype.setBounds(206, 196, 232, 32);
		contentPane.add(fieldtype);
		fieldtype.setColumns(10);
		
		labelcolor.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelcolor.setBounds(29, 241, 134, 24);
		contentPane.add(labelcolor);
		
		fieldcolor.setBounds(206, 240, 232, 32);
		contentPane.add(fieldcolor);
		fieldcolor.setColumns(10);
		labelstate.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		labelstate.setBounds(29, 288, 134, 24);
		contentPane.add(labelstate);
		
		fieldstate.setBounds(206, 287, 232, 32);
		contentPane.add(fieldstate);
		fieldstate.setColumns(10);
		
		labelclaimid.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelclaimid.setBounds(29, 330, 97, 26);
		contentPane.add(labelclaimid);
		
		comboClaim.setBounds(206, 331, 232, 32);
		contentPane.add(comboClaim);
		
		labelcoFlight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelcoFlight.setBounds(29, 375, 118, 22);
		contentPane.add(labelcoFlight);
		
		comboFlight.setBounds(206, 377, 232, 32);
		contentPane.add(comboFlight);
		
		labelcodePilf.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelcodePilf.setBounds(29, 414, 153, 26);
		contentPane.add(labelcodePilf);
		
		comboPilf.setBounds(206, 415, 232, 32);
		contentPane.add(comboPilf);
		
		labelagentId.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelagentId.setBounds(29, 451, 97, 22);
		contentPane.add(labelagentId);
		
		comboAgent.setBounds(206, 453, 232, 32);
		contentPane.add(comboAgent);
		
		labelDelivery.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelDelivery.setBounds(29, 487, 118, 26);
		contentPane.add(labelDelivery);
		
		comboDelivery.setBounds(206, 491, 232, 32);
		contentPane.add(comboDelivery);
		
		labelSendBag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelSendBag.setBounds(29, 529, 109, 25);
		contentPane.add(labelSendBag);
		
		comboSendBag.setBounds(206, 529, 232, 32);
		contentPane.add(comboSendBag);
	
		
		btnSave.setBounds(512, 155, 134, 66);
		contentPane.add(btnSave);
				
		btnCancel.setBounds(512, 312, 134, 66);
		contentPane.add(btnCancel);	
		
		add(contentPane);
		
	}

}
