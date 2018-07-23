/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azhar.university.magles.domain.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author interactive
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("id")
	@Expose
	protected Long id;

	@SerializedName("title")
	@Expose
	protected String title;

	@SerializedName("date")
	@Expose
	protected Date date;

	@SerializedName("content")
	@Expose
	protected String content;

	@SerializedName("note")
	@Expose
	protected String note;

	@SerializedName("owner")
	@Expose
	protected User ownerId;

	@SerializedName("currentStatus")
	@Expose
	private Status currentStatus;

	@SerializedName("creationDate")
	@Expose
	private Date creationDate;

	@SerializedName("modifiedDate")
	@Expose
	private Date modifiedDate;

	@SerializedName("createdBy")
	@Expose
	private User createdBy;

	@SerializedName("modifiedBy")
	@Expose
	private User modifiedBy;

	@SerializedName("department")
	@Expose
	private Department department;

	@SerializedName("history")
	@Expose
	private List<OrderHistory> history;

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setHistory(List<OrderHistory> history) {
		this.history = history;
	}

	public List<OrderHistory> getHistory() {
		return history;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Order)) {
			return false;
		}
		Order other = (Order) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", title=" + title + ", date=" + date + ", content=" + content + ", note=" + note
				+ ", ownerId=" + ownerId + ", currentStatus=" + currentStatus + ", creationDate=" + creationDate
				+ ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", department=" + department + ", history=" + history + "]";
	}
}
