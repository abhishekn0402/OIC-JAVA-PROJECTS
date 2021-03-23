package com.example.UItask.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
    private String encodedImage;
    private String decodedimagedata;
    private String myFinalImage;
	public User() {
		super();
	}

	
	public User(String username,String encodedImage,String password,String decodedimagedata, String myFinalImage)//byte[] image
	{
		this.decodedimagedata=decodedimagedata;
		this.username = username;
		this.encodedImage = encodedImage;
		this.password=password;
		this.myFinalImage=myFinalImage;
	}
	
	public User(String encodedImage)
	{
		this.encodedImage = encodedImage;
	}


	public String getMyFinalImage() {
		return myFinalImage;
	}


	public void setMyFinalImage(String myFinalImage) {
		this.myFinalImage = myFinalImage;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	


	public String getEncodedImage() {
		return encodedImage;
	}


	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDecodedimagedata() {
		return decodedimagedata;
	}


	public void setDecodedimagedata(String decodedimagedata) {
		this.decodedimagedata = decodedimagedata;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", encodedImage=" + encodedImage
				+ ", decodedimagedata=" + decodedimagedata + ", myFinalImage=" + myFinalImage + "]";
	}


	

	


	
	

	


	
	
	/*
	 * public byte[] getImage() { return image; } public void setImage(byte[] image)
	 * { this.image = image; }
	 */
}
