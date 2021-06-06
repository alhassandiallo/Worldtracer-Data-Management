package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.Luggage;

public class BagTableModel extends AbstractTableModel {
	private static final int COLUMN_NUMBAG = 0;
	private static final int COLUMN_TAGNUMBER = 1;
	private static final int COLUMN_NAMEONTAG = 2;
	private static final int COLUMN_WEIGHT = 3;
	private static final int COLUMN_TYPE = 4;
	private static final int COLUMN_COLOR = 5;
	private static final int COLUMN_STATE = 6;
	private static final int COLUMN_CLAIM = 7;
	private static final int COLUMN_FLIGHT = 8;
	private static final int COLUMN_PILFERAGE = 9;
	private static final int COLUMN_AGENT = 10;
	private static final int COLUMN_DELIVERY = 11;
	private static final int COLUMN_SENDBAG = 12;
	
	private static final String[] COLUMN_NAMES = {"NumBag", "TagNumber", "NameOnTag", "ReceivedWeight", "TypeOfBag",
		 "Color", "StateOfBag", "Claim", "Flight", "Pilferage", "Agent", "Delivery", "SendBag"};
	
	
	private List<Luggage> listBag;


	public BagTableModel() {
		super();
	}

	public BagTableModel(List<Luggage> listBag) {
		this.listBag = listBag;
	}

	public List<Luggage> getListBag() {
		return listBag;
	}

	public void setListBag(List<Luggage> listBag) {
		this.listBag = listBag;
	}

	@Override
	public int getRowCount() {
		return listBag == null ? 0 : listBag.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Luggage bag = listBag.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_NUMBAG:
			return bag.getNumBag();
		case COLUMN_TAGNUMBER:
			return bag.getTagNumber();
		case COLUMN_NAMEONTAG:
			return bag.getNameOnTag();
		case COLUMN_WEIGHT:
			return bag.getReceivedWeight();
		case COLUMN_TYPE:
			return bag.getType();
		case COLUMN_COLOR:
			return bag.getColor();
		case COLUMN_STATE:
			return bag.getState();
		case COLUMN_CLAIM:
			return bag.getClaim();
		case COLUMN_FLIGHT:
			return bag.getFlight();
		case COLUMN_PILFERAGE:
			return bag.getPilferage();
		case COLUMN_AGENT:
			return bag.getAgent();
		case COLUMN_DELIVERY:
			return bag.getDelivery();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Luggage getBagAt(int row) {
		if (row >= 0 && row < listBag.size()) {
			return listBag.get(row);
		}
		
		return null;
	}

}
