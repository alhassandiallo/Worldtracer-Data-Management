package com.litige.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.litige.dao.Destination;

public class DestinationForm extends JDialog {
	private JLabel labelDestId = new JLabel("Destination Id:");
	private JLabel labelCodeDest = new JLabel("Code Destination:");
	private JLabel labelNameDest = new JLabel("Name of Destination:");
	
	private JTextField fieldDestIdt = new JTextField(20);
	private JTextField fieldCodeDest = new JTextField(20);
	private JTextField fieldNameDest = new JTextField(20);
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private Destination dest;
	
	private JPanel contentPane;
	
	public DestinationForm(MainWindow owner) {
		super(owner, true);
		setTitle("Create Destination");
		
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
		
		int destId = Integer.parseInt(fieldDestIdt.getText());
		String codeDest = fieldCodeDest.getText();
		String nameDest = fieldNameDest.getText();
		
		
		if (destId == 0) {
			JOptionPane.showMessageDialog(this, "Please enter destination Id!");
			fieldDestIdt.requestFocus();
			return false;
		} else if (codeDest.equals(null)) {
			JOptionPane.showMessageDialog(this, "Please the code of the destination!");
			fieldCodeDest.requestFocus();
			return false;
		} else if (nameDest.equals(null)) {
			JOptionPane.showMessageDialog(this, "Please enter the name of the destination!");
			fieldNameDest.requestFocus();
			return false;
		}
		
		if (dest == null) {		
			dest = new Destination();
		}
		
		dest.setId(destId);
		dest.setCoDest(codeDest);
		dest.setName(nameDest);
		
		return true;
	}
	
	public Destination getDest() {
		return dest;
	}
	
	public void setDest(Destination dest) {
		setTitle("Edit Destination");
		fieldDestIdt.setText(String.valueOf(dest.getId()));
		fieldCodeDest.setText(dest.getCoDest());
		fieldNameDest.setText(dest.getName());
		
		this.dest = dest;
	}
	
	private void initComponents() {
		setBounds(100, 100, 554, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		labelDestId.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelDestId.setBounds(57, 45, 146, 35);
		contentPane.add(labelDestId);
		
		fieldDestIdt = new JTextField();
		fieldDestIdt.setBounds(263, 46, 224, 39);
		contentPane.add(fieldDestIdt);
		fieldDestIdt.setColumns(10);
		
		labelCodeDest.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelCodeDest.setBounds(57, 96, 178, 35);
		contentPane.add(labelCodeDest);
		
		fieldCodeDest = new JTextField();
		fieldCodeDest.setBounds(263, 91, 224, 40);
		contentPane.add(fieldCodeDest);
		fieldCodeDest.setColumns(10);
		
		labelNameDest.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelNameDest.setBounds(57, 148, 210, 34);
		contentPane.add(labelNameDest);
		
		fieldNameDest = new JTextField();
		fieldNameDest.setBounds(263, 143, 224, 39);
		contentPane.add(fieldNameDest);
		fieldNameDest.setColumns(10);
		
		btnSave.setBounds(113, 250, 128, 70);
		contentPane.add(btnSave);
		
		btnCancel.setBounds(348, 252, 128, 67);
		contentPane.add(btnCancel);
		
		add(contentPane);
	}

}
