package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.Destination;

public class DestinationTableModel extends AbstractTableModel {
	private static final int COLUMN_DESTID = 0;
	private static final int COLUMN_CODEDEST = 1;
	private static final int COLUMN_NAMEDEST = 2;
	
	private static final String[] COLUMN_NAMES = {"Destination ID", "Code Destination", "Name of Destination"};
	
	private List<Destination> listDest;


	public DestinationTableModel() {
		super();
	}

	public DestinationTableModel(List<Destination> listDest) {
		this.listDest = listDest;
	}

	public List<Destination> getListDest() {
		return listDest;
	}

	public void setListDest(List<Destination> listDest) {
		this.listDest = listDest;
	}

	@Override
	public int getRowCount() {
		return listDest == null ? 0 : listDest.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Destination dest = listDest.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_DESTID:
			return dest.getId();
		case COLUMN_CODEDEST:
			return dest.getCoDest();
		case COLUMN_NAMEDEST:
			return dest.getName();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Destination getDestAt(int row) {
		if (row >= 0 && row < listDest.size()) {
			return listDest.get(row);
		}
		
		return null;
	}

}
