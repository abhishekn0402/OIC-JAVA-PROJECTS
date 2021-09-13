package com.ITO.SpringbootBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ITO.SpringbootBackend.Model.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {

//	public Authentication getCredentialsByName(String userName);
		
}
