package com.ITO.SpringbootBackend.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITO.SpringbootBackend.Model.Authentication;
import com.ITO.SpringbootBackend.Repository.AuthenticationRepository;

@Service
public class AuthenticationService
{
	@Autowired
	private AuthenticationRepository authenticationRepository;

	Logger logger
	= Logger.getLogger(
			AuthenticationService.class.getName());


	public List<Authentication> get_credentials() {
		return authenticationRepository.findAll();
	}


	//to create credentials
	public Authentication createCredentials(Authentication authentication) throws AuthenticationException {
		Authentication auth=null;
		String username=authentication.getUserName();
		String pass=authentication.getPassword();
		if (username!=null && pass!=null) {
			auth=authenticationRepository.findCredentialsByUserNameAndPassword(username,pass);
			if (auth==null) {
				authenticationRepository.save(authentication);	
			}	
			else{
				throw new AuthenticationException("User exsist with the mentioned credentials..");
			}
		}
		return auth;
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


	public Authentication login(Authentication authentication) throws AuthenticationException {
		Authentication auth=null;
		String username=authentication.getUserName();
		String pass=authentication.getPassword();	
		if (username!=null && pass!=null){
			auth=authenticationRepository.findCredentialsByUserNameAndPassword(username,pass);
		}
		if (auth==null) {
			throw new AuthenticationException("bad credentials..");	
		}
		return auth;
	}
}
