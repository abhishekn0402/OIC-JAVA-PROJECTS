package com.ImageHandler.Project.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int imageID;
	private String imageName;
	private String imageData;
	
	

	//BufferedImage image = ImageIO.read(new File("C:\\Users\\Abhilash Pramod\\Desktop\\abhishek\\ABHISHEK\\dog.jpg"));

	//private BufferedImage image;

	
	public Image()
	{
		super();
	}
	public Image(int imageID, String imageName, String imageData) {//BufferedImage image
		super();
		this.imageID = imageID;
		this.imageName = imageName;
		this.imageData=imageData;
		
		//this.image = image;
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}


	
	/*
	 * public BufferedImage getImage() { return image; } public void
	 * setImage(BufferedImage image) { this.image = image; }
	 */
	
	
}

