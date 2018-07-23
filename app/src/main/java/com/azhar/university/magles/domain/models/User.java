package com.azhar.university.magles.domain.models;

import android.util.Base64;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
	@SerializedName("id")
	@Expose
	private Long id;
	
	@SerializedName("username")
	@Expose
	private String username;
	
	@SerializedName("username")
	@Expose
	private boolean enabled;
	
	@SerializedName("name")
	@Expose
	private String name;
	
	@SerializedName("email")
	@Expose
	private String email;
	
	@SerializedName("phone")
	@Expose
	private String phone;
	
	@SerializedName("address")
	@Expose
	private String address;
	
	@SerializedName("nationalId")
	@Expose
	private String nationalId;
	
	@SerializedName("birthDate")
	@Expose
	private Date birthDate;
	
	@SerializedName("note")
	@Expose
	private String note;

	@SerializedName("department")
	@Expose
	private Department department;

	private String password;
	private String authorization;
	private boolean logged;
    private Language language;

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.logged = false;
		String credentials = username + ":" + password;
		authorization = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Department getDepartment() {
		return department;
	}

	public String getAuthorization() {
		return authorization;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", enabled=" + enabled + ", name=" + name + ", email="
				+ email + ", phone=" + phone + ", address=" + address + ", nationalId=" + nationalId + ", birthDate="
				+ birthDate + ", note=" + note + ", department=" + department + "]";
	}
}
