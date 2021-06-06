package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.Pilferage;

public class PilferageTableModel extends AbstractTableModel {
	private static final int COLUMN_CODEPILF = 0;
	private static final int COLUMN_PASSENGERNAME = 1;
	private static final int COLUMN_TAGNUMBER = 2;
	private static final int COLUMN_WEIGHT = 3;
	private static final int COLUMN_DELIVEREDWEIGHT = 4;
	private static final int COLUMN_REASON = 5;
	private static final int COLUMN_AGENT = 6;
	
	private static final String[] COLUMN_NAMES = {"Code Pilf", "Passenger Name", "Tag Number", "Weight", "Delivered Weight",
			 "Reason", "Agent"};
	
	private List<Pilferage> listPilf;


	public PilferageTableModel() {
		super();
	}

	public PilferageTableModel(List<Pilferage> listPilf) {
		this.listPilf = listPilf;
	}

	public List<Pilferage> getListPilf() {
		return listPilf;
	}

	public void setListPilf(List<Pilferage> listPilf) {
		this.listPilf = listPilf;
	}

	@Override
	public int getRowCount() {
		return listPilf == null ? 0 : listPilf.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pilferage pilf = listPilf.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_CODEPILF:
			return pilf.getCodePilf();
		case COLUMN_PASSENGERNAME:
			return pilf.getPassengerName();
		case COLUMN_TAGNUMBER:
			return pilf.getTagNumber();
		case COLUMN_WEIGHT:
			return pilf.getWeight();
		case COLUMN_DELIVEREDWEIGHT:
			return pilf.getDeliveredWeight();
		case COLUMN_REASON:
			return pilf.getReason();
		case COLUMN_AGENT:
			return pilf.getAgent();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Pilferage getPilfAt(int row) {
		if (row >= 0 && row < listPilf.size()) {
			return listPilf.get(row);
		}
		
		return null;
	}

}
