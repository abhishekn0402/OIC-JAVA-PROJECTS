package com.ITO.SpringbootBackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITO.SpringbootBackend.Model.Authentication;
import com.ITO.SpringbootBackend.Repository.AuthenticationRepository;

@Service
public class AuthenticationService
{
	@Autowired
	private AuthenticationRepository authenticationRepository;

	public List<Authentication> get_credentials() {
		return authenticationRepository.findAll();
	}

	public Authentication createCredentials(Authentication authentication) {
		return authenticationRepository.save(authentication);
	}

	public Optional<Authentication> updateCrendentials(Authentication authentication,int auth_Id) {	
		Optional<Authentication> auth= authenticationRepository.findById(auth_Id);
		auth.get().setUserName(authentication.getUserName());
		auth.get().setPassword(authentication.getPassword());
		auth.get().setSecrete_Key(authentication.getSecrete_Key());
		return auth;
	}
	public void deleteCrendentials(int auth_Id) {		
		authenticationRepository.deleteById(auth_Id);
	}
}
