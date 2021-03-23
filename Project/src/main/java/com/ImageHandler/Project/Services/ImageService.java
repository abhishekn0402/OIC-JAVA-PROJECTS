package com.ImageHandler.Project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ImageHandler.Project.DTO.Image;
import com.ImageHandler.Project.Repo.ImageRepository;

@Service
public class ImageService 
{
	@Autowired
	private ImageRepository imageRepository;
	
	public List<Image> getAllImage()
	
	{
		List<Image> images=imageRepository.findAll();
		return images;
	}
	
	public void addImage(Image image)
	{
		imageRepository.save(image);
	}
	/*		
		try
		{
		File file =new File("C:\\Users\\Abhilash Pramod\\Desktop\\abhishek\\ABHISHEK\\ImageHandler\\P67656 (002).bmp");
		FileInputStream fileInputStreamReader=new FileInputStream(file);
		byte[] bytes = new byte[(int)file.length()];
		fileInputStreamReader.read(bytes);
		encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
		
		
		image.setImageData(encodedfile);
		//String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
		//image.se
		imageRepository.save(image);
		}
		 catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
	}
*/
}
