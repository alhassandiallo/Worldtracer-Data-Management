package com.litige.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SendBag")
public class SendBag {
	private String coSendBag;
	private Date dateSent;
	private String tagRush;
	private double weight;
	private Destination destination;
	private Agent agent;
	
	public SendBag() {
	}

	public SendBag(String coSendBag, Date dateSent, String tagRush, double weight) {
		super();
		this.coSendBag = coSendBag;
		this.dateSent = dateSent;
		this.tagRush = tagRush;
		this.weight = weight;
	}

	public SendBag(String coSendBag, Date dateSent, String tagRush, double weight, Destination destination,
			Agent agent) {
		super();
		this.coSendBag = coSendBag;
		this.dateSent = dateSent;
		this.tagRush = tagRush;
		this.weight = weight;
		this.destination = destination;
		this.agent = agent;
	}

	@Id
	@Column(name = "coSendBag")
	public String getCoSendBag() {
		return coSendBag;
	}

	public void setCoSendBag(String coSendBag) {
		this.coSendBag = coSendBag;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public String getTagRush() {
		return tagRush;
	}

	public void setTagRush(String tagRush) {
		this.tagRush = tagRush;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@ManyToOne
	@JoinColumn(name = "coDest")
	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
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
		return "SendBag [coSendBag=" + coSendBag + ", dateSent=" + dateSent + ", tagRush=" + tagRush + ", weight="
				+ weight + ", destination=" + destination + ", agent=" + agent + "]";
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof SendBag) {
			SendBag another = (SendBag) obj;
			if (this.coSendBag == another.coSendBag) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.coSendBag).intValue();
	}

}
