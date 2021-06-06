package com.litige.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Destination")
public class Destination {
	private int destId;
	private String coDest;
	private String name;
	
	public Destination() {
	}

	public Destination(int destId, String coDest, String name) {
		this.destId = destId;
		this.coDest = coDest;
		this.name = name;
	}

	@Id
	@Column(name = "destID")
	public int getId() {
		return destId;
	}

	public void setId(int destId) {
		this.destId = destId;
	}
	
	public String getCoDest() {
		return coDest;
	}

	public void setCoDest(String coDest) {
		this.coDest = coDest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Destination [destId=" + destId + ", coDest=" + coDest + ", name=" + name + "]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof Destination) {
			Destination another = (Destination) obj;
			if (this.destId == another.destId) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.destId).intValue();
	}

}
