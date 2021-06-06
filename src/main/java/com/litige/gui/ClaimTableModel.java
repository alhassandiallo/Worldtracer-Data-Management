package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.Claim;

public class ClaimTableModel extends AbstractTableModel{
	private static final int COLUMN_CLAIMID = 0;
	private static final int COLUMN_PASSENGERNAME = 1;
	private static final int COLUMN_DATECLAIM = 2;
	private static final int COLUMN_NUMBEROFBAGS = 3;
	private static final int COLUMN_PASSENGERADDRESS = 4;
	private static final int COLUMN_PHONE = 5;
	private static final int COLUMN_AGENT = 6;
	private static final int COLUMN_DELIVERY = 7;
	
	private static final String[] COLUMN_NAMES = {"Claim ID", "Passenger Name", "Date", "Number of Bags", "Passenger Address",
			 "Phone", "Agent", "Delivery"};
	
	private List<Claim> listClaim;


	public ClaimTableModel() {
		super();
	}

	public ClaimTableModel(List<Claim> listClaim) {
		this.listClaim = listClaim;
	}

	public List<Claim> getListClaim() {
		return listClaim;
	}

	public void setListClaim(List<Claim> listClaim) {
		this.listClaim = listClaim;
	}

	@Override
	public int getRowCount() {
		return listClaim == null ? 0 : listClaim.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Claim claim = listClaim.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_CLAIMID:
			return claim.getClaimId();
		case COLUMN_PASSENGERNAME:
			return claim.getPassengerName();
		case COLUMN_DATECLAIM:
			return claim.getDateClaim();
		case COLUMN_NUMBEROFBAGS:
			return claim.getNumberOfBags();
		case COLUMN_PASSENGERADDRESS:
			return claim.getPassengerAddress();
		case COLUMN_PHONE:
			return claim.getPassengerAddress();
		case COLUMN_AGENT:
			return claim.getAgent();
		case COLUMN_DELIVERY:
			return claim.getDelivery();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Claim getClaimAt(int row) {
		if (row >= 0 && row < listClaim.size()) {
			return listClaim.get(row);
		}
		
		return null;
	}

}
