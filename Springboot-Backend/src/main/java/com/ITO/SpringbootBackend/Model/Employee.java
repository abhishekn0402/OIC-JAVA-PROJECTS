package com.ITO.SpringbootBackend.Model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "employees")
public class Employee
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")		
	private String lastName;
	
	@Column(name = "email_id")
	private String email_Id;
	
	@Column(name = "record_Created_Time")
	private Date record_Created_Time;
	
	@Column(name = "record_Modified_Time")
	private Date record_Modified_Time;
	
	@Column(name = "record_Modified_User")
	private String record_Created_User;

	public Employee() {
		super();
	}
	
	public Employee(long id, String firstName, String lastName, String email_Id, Date record_Created_Time,
			Date record_Modified_Time, String record_Created_User) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email_Id = email_Id;
		this.record_Created_Time = record_Created_Time;
		this.record_Modified_Time = record_Modified_Time;
		this.record_Created_User = record_Created_User;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public Date getRecord_Created_Time() {
		return record_Created_Time;
	}

	public void setRecord_Created_Time(Date record_Created_Time) {
		this.record_Created_Time = record_Created_Time;
	}

	public Date getRecord_Modified_Time() {
		return record_Modified_Time;
	}

	public void setRecord_Modified_Time(Date record_Modified_Time) {
		this.record_Modified_Time = record_Modified_Time;
	}

	public String getRecord_Created_User() {
		return record_Created_User;
	}

	public void setRecord_Created_User(String record_Created_User) {
		this.record_Created_User = record_Created_User;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email_Id=" + email_Id
				+ ", record_Created_Time=" + record_Created_Time + ", record_Modified_Time=" + record_Modified_Time
				+ ", record_Created_User=" + record_Created_User + "]";
	}
}