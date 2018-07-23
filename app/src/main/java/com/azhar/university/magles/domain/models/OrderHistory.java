/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azhar.university.magles.domain.models;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author interactive
 */
public class OrderHistory implements Serializable {

	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	@Expose
	private Long id;
	
	@SerializedName("orderId")
	@Expose
	private Long orderId;

	@SerializedName("note")
	@Expose
	private String note;

	@SerializedName("creationDate")
	@Expose
	private Date creationDate;
	
	@SerializedName("status")
	@Expose
	private Status status;
	
	@SerializedName("createdBy")
	@Expose
	private User createdBy;

	public OrderHistory() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Status getStatusId() {
		return status;
	}

	public void setStatusId(Status statusId) {
		this.status = statusId;
	}
	
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	public User getCreatedBy() {
		return createdBy;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof OrderHistory)) {
			return false;
		}
		OrderHistory other = (OrderHistory) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "OrderHistory [id=" + id + ", note=" + note + ", creationDate=" + creationDate + ", status=" + status
				+ ", createdBy=" + createdBy + "]";
	}
}
