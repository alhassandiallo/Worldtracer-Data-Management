package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.SendBag;

public class SendBagTableModel extends AbstractTableModel {
	private static final int COLUMN_COSENDBAG = 0;
	private static final int COLUMN_DATESEND = 1;
	private static final int COLUMN_TAGRUSH = 2;
	private static final int COLUMN_WEIGHT = 3;
	private static final int COLUMN_DESTINATION = 4;
	private static final int COLUMN_AGENT = 5;
	
	private static final String[] COLUMN_NAMES = {"Code SendBag", "Date Send", "Tag Rush", "Weight", "Destination", "Agent"};
	
	private List<SendBag> listSendBag;


	public SendBagTableModel() {
		super();
	}

	public SendBagTableModel(List<SendBag> listSendBag) {
		this.listSendBag = listSendBag;
	}

	public List<SendBag> getlistSendBag() {
		return listSendBag;
	}

	public void setlistSendBag(List<SendBag> listSendBag) {
		this.listSendBag = listSendBag;
	}

	@Override
	public int getRowCount() {
		return listSendBag == null ? 0 : listSendBag.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SendBag sendBag = listSendBag.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_COSENDBAG:
			return sendBag.getCoSendBag();
		case COLUMN_DATESEND:
			return sendBag.getDateSent();
		case COLUMN_TAGRUSH:
			return sendBag.getTagRush();
		case COLUMN_WEIGHT:
			return sendBag.getWeight();
		case COLUMN_DESTINATION:
			return sendBag.getDestination();
		case COLUMN_AGENT:
			return sendBag.getAgent();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public SendBag getSendBagAt(int row) {
		if (row >= 0 && row < listSendBag.size()) {
			return listSendBag.get(row);
		}
		
		return null;
	}

}
