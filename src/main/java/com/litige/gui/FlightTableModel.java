package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.Flight;

public class FlightTableModel extends AbstractTableModel {
	private static final int COLUMN_CODEFLIGHT = 0;
	private static final int COLUMN_DATEFLIGHT = 1;
	private static final int COLUMN_NUMBEROFBAGS = 2;
	
	private static final String[] COLUMN_NAMES = {"Code Flight", "Flight Date", "Number of Bags"};
	
	private List<Flight> listFlight;


	public FlightTableModel() {
		super();
	}

	public FlightTableModel(List<Flight> listFlight) {
		this.listFlight = listFlight;
	}

	public List<Flight> getListFlight() {
		return listFlight;
	}

	public void setListFlight(List<Flight> listFlight) {
		this.listFlight = listFlight;
	}

	@Override
	public int getRowCount() {
		return listFlight == null ? 0 : listFlight.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Flight flight = listFlight.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_CODEFLIGHT:
			return flight.getCodeFlight();
		case COLUMN_DATEFLIGHT:
			return flight.getDateFlight();
		case COLUMN_NUMBEROFBAGS:
			return flight.getNumberOfBags();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Flight getFlightAt(int row) {
		if (row >= 0 && row < listFlight.size()) {
			return listFlight.get(row);
		}
		
		return null;
	}

}
