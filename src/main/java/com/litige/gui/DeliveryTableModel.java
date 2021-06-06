package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.Delivery;

public class DeliveryTableModel extends AbstractTableModel {
	private static final int COLUMN_DELIVERYID = 0;
	private static final int COLUMN_DATEDELIVERY = 1;
	private static final int COLUMN_TAGNUMBER = 2;
	private static final int COLUMN_WEIGHT = 3;
	private static final int COLUMN_NAME = 4;
	private static final int COLUMN_PHONE = 5;
	private static final int COLUMN_ADDRESS = 6;
	private static final int COLUMN_AGENT = 7;
	
	
	private static final String[] COLUMN_NAMES = {"Delivery ID", "Date Delivery", "Tag Number", "Weight", "Name",
			 "Phone", "Address", "Agent"};
	
	private List<Delivery> listDelivery;


	public DeliveryTableModel() {
		super();
	}

	public DeliveryTableModel(List<Delivery> listDelivery) {
		this.listDelivery = listDelivery;
	}

	public List<Delivery> getListDelivery() {
		return listDelivery;
	}

	public void setListDelivery(List<Delivery> listDelivery) {
		this.listDelivery = listDelivery;
	}

	@Override
	public int getRowCount() {
		return listDelivery == null ? 0 : listDelivery.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Delivery delivery = listDelivery.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_DELIVERYID:
			return delivery.getDeliveryId();
		case COLUMN_DATEDELIVERY:
			return delivery.getDateDelivery();
		case COLUMN_TAGNUMBER:
			return delivery.getTagNumber();
		case COLUMN_WEIGHT:
			return delivery.getWeight();
		case COLUMN_NAME:
			return delivery.getName();
		case COLUMN_PHONE:
			return delivery.getPhone();
		case COLUMN_ADDRESS:
			return delivery.getAddress();
		case COLUMN_AGENT:
			return delivery.getAgent();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Delivery getDeliveryAt(int row) {
		if (row >= 0 && row < listDelivery.size()) {
			return listDelivery.get(row);
		}
		
		return null;
	}

}
