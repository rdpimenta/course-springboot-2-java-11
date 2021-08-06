package com.rdpimenta.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdpimenta.course.entities.User;
import com.rdpimenta.course.repositories.UserRepository;
import com.rdpimenta.course.services.excepetions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public void deleteUserById(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User user) {
		User obj = repository.getById(id);
		
		updateData(user, obj);
		
		return repository.save(obj);
	}
	
	private void updateData(User user, User obj) {
		obj.setName(user.getName());
		obj.setEmail(user.getEmail());
		obj.setPhone(user.getPhone());
	}
}
