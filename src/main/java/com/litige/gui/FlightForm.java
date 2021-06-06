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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.litige.dao.Flight;

public class FlightForm extends JDialog {
	private JLabel labelCodeFlight = new JLabel("Code Flight:");
	private JLabel labelDateFlight = new JLabel("Flight Date:");
	private JLabel labelNumberOfBags = new JLabel("Number of bags:");
	
	private JTextField fieldCodeFlight = new JTextField(20);
	private JTextField fieldDateFlight = new JTextField(20);
	private JTextField fieldNumberOfBags = new JTextField(20);
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private Flight flight;
	
	private JPanel contentPane;
	
	public FlightForm(MainWindow owner) {
		super(owner, true);
		setTitle("Create Flight Report");
		
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
		
		String codeFlight = fieldCodeFlight.getText();
		Date dateFlight = format.parse(fieldDateFlight.getText());
		int numberOfBags = Integer.parseInt(fieldNumberOfBags.getText());
		
		
		if (codeFlight.equals(null)) {
			JOptionPane.showMessageDialog(this, "Please enter pilferage code!");
			fieldCodeFlight.requestFocus();
			return false;
		} else if (dateFlight == null) {
			JOptionPane.showMessageDialog(this, "Please the date of flight!");
			fieldDateFlight.requestFocus();
			return false;
		} else if (numberOfBags == 0) {
			JOptionPane.showMessageDialog(this, "Please enter the number bags!");
			fieldNumberOfBags.requestFocus();
			return false;
		}
		
		if (flight == null) {		
			flight = new Flight();
		}
		
		flight.setCodeFlight(codeFlight);
		flight.setDateFlight(dateFlight);
		flight.setNumberOfBags(numberOfBags);
		
		return true;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	public void setFlight(Flight flight) {
		setTitle("Edit Flight");
		fieldCodeFlight.setText(flight.getCodeFlight());
		fieldDateFlight.setText(String.valueOf(flight.getDateFlight()));
		fieldNumberOfBags.setText(String.valueOf(flight.getNumberOfBags()));
		
		this.flight = flight;
	}
	
	private void initComponents() {
		setBounds(100, 100, 539, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		labelCodeFlight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelCodeFlight.setBounds(57, 45, 194, 35);
		contentPane.add(labelCodeFlight);
		
		fieldCodeFlight.setBounds(263, 46, 224, 39);
		contentPane.add(fieldCodeFlight);
		fieldCodeFlight.setColumns(10);
		
		labelDateFlight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelDateFlight.setBounds(57, 96, 178, 35);
		contentPane.add(labelDateFlight);
		
		fieldDateFlight.setBounds(263, 91, 224, 40);
		contentPane.add(fieldDateFlight);
		fieldDateFlight.setColumns(10);
		
		labelNumberOfBags.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelNumberOfBags.setBounds(57, 148, 210, 34);
		contentPane.add(labelNumberOfBags);
		
		fieldNumberOfBags.setBounds(263, 143, 224, 39);
		contentPane.add(fieldNumberOfBags);
		fieldNumberOfBags.setColumns(10);
		
		btnSave.setBounds(139, 218, 128, 70);
		contentPane.add(btnSave);
		
		btnCancel.setBounds(359, 220, 128, 67);
		contentPane.add(btnCancel);
		
		add(contentPane);
	}

}
