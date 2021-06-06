package com.litige.gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.litige.dao.Agent;
import com.litige.dao.Destination;
import com.litige.dao.SendBag;

public class SendBagForm extends JDialog {
	private JLabel labelCoSendBag = new JLabel("Code SendBag:");
	private JLabel labelDateSend = new JLabel("Date Sent:");
	private JLabel labelTagRush = new JLabel("Tag Rush:");
	private JLabel labelWeight = new JLabel("Weight of Bags:");
	private JLabel labelDestination = new JLabel("Destination:");
	private JLabel labelAgent = new JLabel("Agent:");
	
	private JTextField fieldCoSendBag = new JTextField(20);
	private JTextField fieldDateSend = new JTextField(20);
	private JTextField fieldTagRush = new JTextField(20);
	private JTextField fieldWeight = new JTextField(20);
	
	private JComboBox<Agent> comboAgent = new JComboBox<>();
	private JComboBox<Destination> comboDestination = new JComboBox<>();
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private SendBag sendBag;
	
	private JPanel contentPane;
	
	public SendBagForm(MainWindow owner) {
		super(owner, true);
		setTitle("Create SendBag");
		
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
	
		String coSendBag = fieldCoSendBag.getText();
		Date dateSend = format.parse(fieldDateSend.getText());
		String tagRush = fieldTagRush.getText();
		double weight = Double.parseDouble(fieldWeight.getText());
		
		Object selectedAgent = comboAgent.getSelectedItem();
		Object selectedDestination = comboDestination.getSelectedItem();
		
		
		if (coSendBag.equals(null)) {
			JOptionPane.showMessageDialog(this, "Please enter code of sendbag!");
			fieldCoSendBag.requestFocus();
			return false;
		} else if (dateSend == null) {
			JOptionPane.showMessageDialog(this, "Please enter the date!");
			fieldDateSend.requestFocus();
			return false;
		} else if (tagRush.equals(null)) {
			JOptionPane.showMessageDialog(this, "Please enter the tag rush!");
			fieldTagRush.requestFocus();
			return false;
		}else if (weight == 0.0) {
			JOptionPane.showMessageDialog(this, "Please enter the weight of bags!");
			fieldWeight.requestFocus();
			return false;
		} else if (selectedDestination == null) {
			JOptionPane.showMessageDialog(this, "Please select the destination!");
			comboDestination.requestFocus();
			return false;
		}else if (selectedAgent == null) {
			JOptionPane.showMessageDialog(this, "Please select the agent!");
			comboAgent.requestFocus();
			return false;
		}
		
		if (sendBag == null) {		
			sendBag = new SendBag();
		}
		
		sendBag.setCoSendBag(coSendBag);
		sendBag.setDateSent(dateSend);
		sendBag.setTagRush(tagRush);
		sendBag.setWeight(weight);
		sendBag.setDestination((Destination) selectedDestination);
		sendBag.setAgent((Agent) selectedAgent);
		
		return true;
	}
	
	public SendBag getSendBag() {
		return sendBag;
	}
	
	public void setSendBag(SendBag sendBag) {
		setTitle("Edit SendBag");
		fieldCoSendBag.setText(sendBag.getCoSendBag());
		fieldDateSend.setText(String.valueOf(sendBag.getDateSent()));
		fieldTagRush.setText(sendBag.getTagRush());
		fieldWeight.setText(String.valueOf(sendBag.getWeight()));
		
		comboDestination.setSelectedItem(sendBag.getDestination());
		comboAgent.setSelectedItem(sendBag.getAgent());
		
		this.sendBag = sendBag;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initComponents() {
		setBounds(100, 100, 727, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		labelCoSendBag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelCoSendBag.setBounds(57, 45, 146, 35);
		contentPane.add(labelCoSendBag);
		
		fieldCoSendBag.setBounds(263, 46, 224, 39);
		contentPane.add(fieldCoSendBag);
		fieldCoSendBag.setColumns(10);
		
		labelDateSend.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelDateSend.setBounds(57, 96, 178, 35);
		contentPane.add(labelDateSend);
		
		fieldDateSend.setBounds(263, 91, 224, 40);
		contentPane.add(fieldDateSend);
		fieldDateSend.setColumns(10);
		
		labelTagRush.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelTagRush.setBounds(57, 148, 210, 34);
		contentPane.add(labelTagRush);
		
		fieldTagRush.setBounds(263, 143, 224, 39);
		contentPane.add(fieldTagRush);
		fieldTagRush.setColumns(10);
		
		labelWeight.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelWeight.setBounds(57, 202, 178, 27);
		contentPane.add(labelWeight);
		
		fieldWeight.setBounds(263, 194, 224, 39);
		contentPane.add(fieldWeight);
		fieldWeight.setColumns(10);
		
		labelDestination.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelDestination.setBounds(57, 260, 146, 27);
		contentPane.add(labelDestination);
		
		comboDestination.setBounds(263, 248, 224, 47);
		contentPane.add(comboDestination);
		
		labelAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelAgent.setBounds(57, 315, 133, 28);
		contentPane.add(labelAgent);
		
		comboAgent.setBounds(263, 316, 224, 35);
		contentPane.add(comboAgent);
		
		btnSave.setBounds(566, 82, 128, 70);
		contentPane.add(btnSave);
		
		btnCancel.setBounds(566, 220, 128, 67);
		contentPane.add(btnCancel);
		
		add(contentPane);
	}

}
