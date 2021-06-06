package com.litige.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.litige.dao.Agent;

public class AgentTableModel extends AbstractTableModel {
	private static final int COLUMN_AGENTID = 0;
	private static final int COLUMN_NAME = 1;
	private static final int COLUMN_PHONE = 2;
	private static final int COLUMN_ADDRESS = 3;
	private static final int COLUMN_PASSWORD = 4;
	
	private static final String[] COLUMN_NAMES = {"Agent ID", "Agent Name", "Phone", "Address", "Password"};
	
	private List<Agent> listAgent;


	public AgentTableModel() {
		super();
	}

	public AgentTableModel(List<Agent> listAgent) {
		this.listAgent = listAgent;
	}

	public List<Agent> getListAgent() {
		return listAgent;
	}

	public void setListAgent(List<Agent> listAgent) {
		this.listAgent = listAgent;
	}

	@Override
	public int getRowCount() {
		return listAgent == null ? 0 : listAgent.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Agent agent = listAgent.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_AGENTID:
			return agent.getId();
		case COLUMN_NAME:
			return agent.getName();
		case COLUMN_PHONE:
			return agent.getPhone();
		case COLUMN_ADDRESS:
			return agent.getAddress();
		case COLUMN_PASSWORD:
			return agent.getPassword();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Agent getAgentAt(int row) {
		if (row >= 0 && row < listAgent.size()) {
			return listAgent.get(row);
		}
		
		return null;
	}

}
