package com.litige.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Flight")
public class Flight {
	private String codeFlight;
	private Date dateFlight;
	private int numberOfBags;
	
	
	public Flight() {
	}


	public Flight(String codeFlight, Date dateFlight, int numberOfBags) {
		super();
		this.codeFlight = codeFlight;
		this.dateFlight = dateFlight;
		this.numberOfBags = numberOfBags;
	}

	@Id
	@Column(name = "codeFlight")
	public String getCodeFlight() {
		return codeFlight;
	}


	public void setCodeFlight(String codeFlight) {
		this.codeFlight = codeFlight;
	}


	public Date getDateFlight() {
		return dateFlight;
	}


	public void setDateFlight(Date dateFlight) {
		this.dateFlight = dateFlight;
	}


	public int getNumberOfBags() {
		return numberOfBags;
	}


	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}


	@Override
	public String toString() {
		return "Flight [codeFlight=" + codeFlight + ", dateFlight=" + dateFlight + ", numberOfBags=" + numberOfBags
				+ "]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof Flight) {
			Flight another = (Flight) obj;
			if (this.codeFlight == another.codeFlight) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.codeFlight).intValue();
	}
	

}
