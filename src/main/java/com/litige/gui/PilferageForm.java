package com.litige.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.litige.dao.Agent;
import com.litige.dao.Pilferage;

public class PilferageForm extends JDialog {
	private JLabel labelCodePilf = new JLabel("Code pilferage:");
	private JLabel labelPassName = new JLabel("Passenger Name:");
	private JLabel labelTagNumber = new JLabel("Tag Number:");
	private JLabel labelWeight = new JLabel("Weight");
	private JLabel labelDeliveredWeight = new JLabel("Delivered Weight:");
	private JLabel labelReason = new JLabel("Reason:");
	private JLabel labelAgent = new JLabel("Agent:");
	
	private JTextField fieldCodePilf = new JTextField(20);
	private JTextField fieldPassName = new JTextField(20);
	private JTextField fieldTagNumber = new JTextField(20);
	private JTextField fieldWeight = new JTextField(20);
	private JTextField fieldDeliveredWeight = new JTextField(20);
	private JTextField fieldReason = new JTextField(20);
	
	private JComboBox<Agent> comboAgent = new JComboBox<>();
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private Pilferage pilferage;
	
	private JPanel contentPane;
	
	public PilferageForm(MainWindow owner) {
		super(owner, true);
		setTitle("Create Pilferage Report");
		
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
			
	
		String codePilf = fieldCodePilf.getText();
		String passengerName = fieldPassName.getText();
		String tagNumber = fieldTagNumber.getText();
		double weight = Double.parseDouble(fieldWeight.getText());
		double deliveredWeight = Double.parseDouble(fieldDeliveredWeight.getText());
		String reason = fieldReason.getText();
		
		Object selectedAgent = comboAgent.getSelectedItem();
		
		
		if (codePilf.equals(null)) {
			JOptionPane.showMessageDialog(this, "Please enter pilferage code!");
			fieldCodePilf.requestFocus();
			return false;
		} else if (passengerName == null) {
			JOptionPane.showMessageDialog(this, "Please enter passenger name!");
			fieldPassName.requestFocus();
			return false;
		} else if (tagNumber == null) {
			JOptionPane.showMessageDialog(this, "Please enter the tag number!");
			fieldTagNumber.requestFocus();
			return false;
		}else if (weight == 0.0) {
			JOptionPane.showMessageDialog(this, "Please enter the weight of bags!");
			fieldWeight.requestFocus();
			return false;
		}else if (deliveredWeight == 0.0) {
			JOptionPane.showMessageDialog(this, "Please enter the delivered weight of bags!");
			fieldDeliveredWeight.requestFocus();
			return false;
		} else if (reason.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the reason of damage or pilferage!");
			fieldReason.requestFocus();
			return false;
		}else if (selectedAgent == null) {
			JOptionPane.showMessageDialog(this, "Please enter the ID number of the agent!");
			comboAgent.requestFocus();
			return false;
		}
		
		if (pilferage == null) {		
			pilferage = new Pilferage();
		}
		
		pilferage.setCodePilf(codePilf);
		pilferage.setPassengerName(passengerName);
		pilferage.setTagNumber(tagNumber);
		pilferage.setWeight(deliveredWeight);
		pilferage.setDeliveredWeight(deliveredWeight);
		pilferage.setReason(reason);
		pilferage.setAgent((Agent) selectedAgent);
		
		return true;
	}
	
	public Pilferage getPilferage() {
		return pilferage;
	}
	
	public void setPilferage(Pilferage pilferage) {
		setTitle("Edit Pilferage");
		fieldCodePilf.setText(pilferage.getCodePilf());
		fieldPassName.setText(pilferage.getPassengerName());
		fieldTagNumber.setText(pilferage.getTagNumber());
		fieldWeight.setText(String.valueOf(pilferage.getWeight()));
		fieldDeliveredWeight.setText(String.valueOf(pilferage.getDeliveredWeight()));
		fieldReason.setText(pilferage.getReason());
		
		comboAgent.setSelectedItem(pilferage.getAgent());
		this.pilferage = pilferage;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initComponents() {
		setBounds(100, 100, 727, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
	
		labelCodePilf.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelCodePilf.setBounds(57, 45, 194, 35);
		contentPane.add(labelCodePilf);
		
		fieldCodePilf.setBounds(263, 46, 224, 39);
		contentPane.add(fieldCodePilf);
		fieldCodePilf.setColumns(10);
		
		labelPassName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelPassName.setBounds(57, 96, 178, 35);
		contentPane.add(labelPassName);
		
		fieldPassName.setBounds(263, 91, 224, 40);
		contentPane.add(fieldPassName);
		fieldPassName.setColumns(10);
		
		labelTagNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelTagNumber.setBounds(57, 148, 210, 34);
		contentPane.add(labelTagNumber);
		
		fieldTagNumber.setBounds(263, 143, 224, 39);
		contentPane.add(fieldTagNumber);
		fieldTagNumber.setColumns(10);
		
		labelWeight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelWeight.setBounds(57, 202, 178, 27);
		contentPane.add(labelWeight);
		
		fieldWeight.setBounds(263, 194, 224, 39);
		contentPane.add(fieldWeight);
		fieldWeight.setColumns(10);
		
		labelDeliveredWeight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelDeliveredWeight.setBounds(57, 307, 178, 27);
		contentPane.add(labelDeliveredWeight);
		
		fieldDeliveredWeight.setBounds(263, 245, 224, 42);
		contentPane.add(fieldDeliveredWeight);
		fieldDeliveredWeight.setColumns(10);
		
		labelReason.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelReason.setBounds(57, 260, 140, 27);
		contentPane.add(labelReason);
		
		fieldReason.setBounds(263, 299, 224, 42);
		contentPane.add(fieldReason);
		fieldReason.setColumns(10);
		
		labelAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelAgent.setBounds(57, 343, 133, 36);
		contentPane.add(labelAgent);
		
		comboAgent.setBounds(263, 348, 224, 35);
		contentPane.add(comboAgent);
		
		btnSave.setBounds(566, 82, 128, 70);
		contentPane.add(btnSave);
		
		btnCancel.setBounds(566, 220, 128, 67);
		contentPane.add(btnCancel);
		
		add(contentPane);
	}

}

