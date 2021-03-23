package com.example.UItask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.UItask.DTO.User;
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>
{

}
