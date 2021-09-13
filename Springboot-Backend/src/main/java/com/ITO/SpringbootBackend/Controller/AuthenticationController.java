package com.ITO.SpringbootBackend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ITO.SpringbootBackend.Model.Authentication;
import com.ITO.SpringbootBackend.Service.AuthenticationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController
{
	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping("/auth")
	public List<Authentication> get_Credentials()
	{
		return authenticationService.get_credentials();
	}
	@PostMapping("/auth")
	public Authentication createCredentails(@RequestBody Authentication authentication)
	{
		return authenticationService.createCredentials(authentication);
	}
	/*	
	@GetMapping("/auth/{userName}")
	public Authentication getCredentialsByName(@PathVariable("userName") String userName)
	{
		return authenticationService.getCredentialsByName(userName);
	}
	*/
	@PutMapping("/auth/{userName}")
	public Optional<Authentication> updateCrendentials(@RequestBody Authentication authentication,@PathVariable("auth_Id") int auth_Id)
	{
		return authenticationService.updateCrendentials(authentication,auth_Id);
	}
	
	@DeleteMapping("/auth/delete/{auth_Id}")
	public void deleteCrendentials(@PathVariable("auth_Id") int auth_Id)
	{
		authenticationService.deleteCrendentials(auth_Id);
	}
	
}
