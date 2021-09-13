package com.ITO.SpringbootBackend.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authentication")
public class Authentication
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int auth_Id;
private String userName;
private String password;
private String secrete_Key;
public Authentication() {
	super();
}
public Authentication(int auth_Id, String userName, String password, String secrete_Key) {
	super();
	this.auth_Id = auth_Id;
	this.userName = userName;
	this.password = password;
	this.secrete_Key = secrete_Key;
}
public int getAuth_Id() {
	return auth_Id;
}
public void setAuth_Id(int auth_Id) {
	this.auth_Id = auth_Id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSecrete_Key() {
	return secrete_Key;
}
public void setSecrete_Key(String secrete_Key) {
	this.secrete_Key = secrete_Key;
}
@Override
public String toString() {
	return "Authentication [auth_Id=" + auth_Id + ", userName=" + userName + ", password=" + password + ", secret_Key="
			+ secrete_Key + "]";
}
}