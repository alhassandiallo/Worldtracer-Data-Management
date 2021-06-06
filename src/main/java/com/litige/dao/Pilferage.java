package com.litige.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PilferageReport")
public class Pilferage {
	private String codePilf;
	private String passengerName;
	private String tagNumber;
	private double weight;
	private double deliveredWeight;
	private String reason;
	private Agent agent;
	
	public Pilferage() {
	}

	public Pilferage(String codePilf, String passengerName, String tagNumber, double weight, double deliveredWeight,
			String reason) {
		super();
		this.codePilf = codePilf;
		this.passengerName = passengerName;
		this.tagNumber = tagNumber;
		this.weight = weight;
		this.deliveredWeight = deliveredWeight;
		this.reason = reason;
	}

	public Pilferage(String codePilf, String passengerName, String tagNumber, double weight, double deliveredWeight,
			String reason, Agent agent) {
		super();
		this.codePilf = codePilf;
		this.passengerName = passengerName;
		this.tagNumber = tagNumber;
		this.weight = weight;
		this.deliveredWeight = deliveredWeight;
		this.reason = reason;
		this.agent = agent;
	}

	@Id
	@Column(name = "codePilf")
	public String getCodePilf() {
		return codePilf;
	}

	public void setCodePilf(String codePilf) {
		this.codePilf = codePilf;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getDeliveredWeight() {
		return deliveredWeight;
	}

	public void setDeliveredWeight(double deliveredWeight) {
		this.deliveredWeight = deliveredWeight;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@ManyToOne
	@JoinColumn(name = "agentID")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "Pilferage [codePilf=" + codePilf + ", passengerName=" + passengerName + ", tagNumber=" + tagNumber
				+ ", weight=" + weight + ", deliveredWeight=" + deliveredWeight + ", reason=" + reason + ", agent="
				+ agent + "]";
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Pilferage) {
			Pilferage another = (Pilferage) obj;
			if (this.codePilf == another.codePilf) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.codePilf).intValue();
	}
	

}
