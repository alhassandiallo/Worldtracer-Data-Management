package com.litige.gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import com.litige.dao.Delivery;

public class DeliveryForm extends JDialog{
	private JLabel labeldeliveryId = new JLabel("Delivery ID:");
	private JLabel labelDateDelivery = new JLabel("Date delivery:");
	private JLabel labelTagNumber = new JLabel("Tag Number:");
	private JLabel labelWeight = new JLabel("Weight:");
	private JLabel labelName = new JLabel("Name:");
	private JLabel labelPhone = new JLabel("Phone:");
	private JLabel labelAddress = new JLabel("Address:");
	private JLabel labelAgent = new JLabel("Agent:");
	
	private JTextField fieldDeliveryId = new JTextField(20);
	private JTextField fieldDateDelivery = new JTextField(20);
	private JTextField fieldTagNumber = new JTextField(20);
	private JTextField fieldWeight = new JTextField(20);
	private JTextField fieldName = new JTextField(20);
	private JTextField fieldPhone = new JTextField(20);
	private JTextField fieldAddress = new JTextField(20);
	
	private JComboBox<Agent> comboAgent = new JComboBox<>();
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private Delivery delivery;
	
	private JPanel contentPane;
	
	public DeliveryForm(MainWindow owner) {
		super(owner, true);
		setTitle("Create Delivery");
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
	
		int deliveryId = Integer.parseInt(fieldDeliveryId.getText());
		Date dateDelivery = format.parse(fieldDateDelivery.getText());
		String tagNumber = fieldTagNumber.getText();
		double weight = Double.parseDouble(fieldWeight.getText());
		String name = fieldName.getText();
		String phone = fieldPhone.getText();
		String address = fieldAddress.getText();
		
		
		Object selectedAgent = comboAgent.getSelectedItem();
		
		
		
		if (deliveryId == 0) {
			JOptionPane.showMessageDialog(this, "Please enter delivery ID!");
			fieldDeliveryId.requestFocus();
			return false;
		} else if (name == null) {
			JOptionPane.showMessageDialog(this, "Please enter the person's name!");
			fieldName.requestFocus();
			return false;
		} else if (dateDelivery == null) {
			JOptionPane.showMessageDialog(this, "Please enter the date of the delivery!");
			fieldDateDelivery.requestFocus();
			return false;
		}else if (weight == 0.0) {
			JOptionPane.showMessageDialog(this, "Please enter the weight of the bags!");
			fieldWeight.requestFocus();
			return false;
		} else if (address.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the person's address !");
			fieldAddress.requestFocus();
			return false;
		}else if (selectedAgent == null) {
			JOptionPane.showMessageDialog(this, "Please enter the ID number of the agent!");
			comboAgent.requestFocus();
			return false;
		}
		
		if (delivery == null) {		
			delivery = new Delivery();
		}
		
		delivery.setDeliveryId(deliveryId);
		delivery.setDateDelivery(dateDelivery);
		delivery.setTagNumber(tagNumber);
		delivery.setWeight(weight);
		delivery.setName(name);
		delivery.setPhone(phone);
		delivery.setAddress(address);
		delivery.setAgent((Agent) selectedAgent);
		
		
		return true;
	}
	
	public Delivery getDelivery() {
		return delivery;
	}
	
	public void setDelivery(Delivery delivery) {
		setTitle("Edit Delivery");
		fieldDeliveryId.setText(String.valueOf(delivery.getDeliveryId()));
		fieldDateDelivery .setText(String.valueOf(delivery.getDateDelivery()));
		fieldTagNumber.setText(delivery.getTagNumber());
		fieldWeight.setText(String.valueOf(delivery.getWeight()));
		fieldName.setText(delivery.getName());
		fieldPhone.setText(delivery.getPhone());
		fieldAddress.setText(delivery.getAddress());
		
		comboAgent.setSelectedItem(delivery.getAgent());
		
		this.delivery = delivery;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initComponents() {
		setBounds(100, 100, 683, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		//
		labeldeliveryId.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labeldeliveryId.setBounds(57, 45, 128, 34);
		contentPane.add(labeldeliveryId);
		
		fieldDeliveryId.setColumns(10);
		fieldDeliveryId.setBounds(243, 40, 190, 39);
		contentPane.add(fieldDeliveryId);
		//
		labelDateDelivery.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelDateDelivery.setBounds(57, 96, 178, 35);
		contentPane.add(labelDateDelivery);
		
		fieldDateDelivery.setBounds(243, 91, 190, 40);
		contentPane.add(fieldDateDelivery);
		fieldDateDelivery.setColumns(10);
		//
		labelTagNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelTagNumber.setBounds(57, 148, 146, 34);
		contentPane.add(labelTagNumber);
		
		fieldTagNumber.setBounds(243, 143, 190, 39);
		contentPane.add(fieldTagNumber);
		fieldTagNumber.setColumns(10);
		//
		labelWeight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelWeight.setBounds(57, 205, 178, 39);
		contentPane.add(labelWeight);
		
		fieldWeight.setBounds(243, 200, 190, 39);
		contentPane.add(fieldWeight);
		fieldWeight.setColumns(10);
		//
		labelName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelName.setBounds(57, 259, 198, 34);
		contentPane.add(labelName);
		
		fieldName.setBounds(243, 254, 190, 39);
		contentPane.add(fieldName);
		fieldName.setColumns(10);
		//
		labelPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelPhone.setBounds(57, 313, 94, 34);
		contentPane.add(labelPhone);
		
		fieldPhone.setBounds(243, 308, 190, 39);
		contentPane.add(fieldPhone);
		fieldPhone.setColumns(10);
		//
		labelAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelAddress.setBounds(57, 359, 94, 39);
		contentPane.add(labelAddress);
		
		fieldAddress.setBounds(243, 362, 190, 39);
		contentPane.add(fieldAddress);
		fieldAddress.setColumns(10);
		//
		labelAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelAgent.setBounds(57, 410, 94, 42);
		contentPane.add(labelAgent);
		
		comboAgent.setBounds(243, 413, 190, 39);
		contentPane.add(comboAgent);
		//
		btnSave.setBounds(476, 112, 128, 70);
		contentPane.add(btnSave);
		
		btnCancel.setBounds(476, 240, 128, 67);
		contentPane.add(btnCancel);
		
		add(contentPane);		
		
	}

}
