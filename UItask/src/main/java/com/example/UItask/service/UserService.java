package com.example.UItask.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.UItask.DTO.User;
import com.example.UItask.repo.UserRepository;

@Service("userService")
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	public void updateUser(User userImage, MultipartFile file) throws IOException
	{

		/*


		Byte[] newByte=new Byte[userImage.getImage().length];
		int i=0;
		for (byte b : file.getBytes()){
			newByte[i++] = b;


	    }
		 */
		/*
		byte[]serviceImage=userImage.getImage();
		Deflater deflater = new Deflater();
		deflater.setInput(serviceImage);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(serviceImage.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished())
		{
			int count = deflater.deflate(buffer);			
			outputStream.write(buffer, 0, count);

		}
		 */
		userRepository.save(userImage);
	}

	public void updateUser(User user1)
	{

		userRepository.save(user1);
	}

	public void getImageById(User user1)
	{
		int id=user1.getId();
		userRepository.findById(id);
	}

	public List<User> getAllUser() 
	{
		List<User> users=userRepository.findAll();
		return users;
	}
	
	public Optional<User> getImageById(int id)
	{
		Optional<User> userinfo=userRepository.findById(id);
		return userinfo;
	}
	

}
