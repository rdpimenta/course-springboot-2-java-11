package com.rdpimenta.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rdpimenta.course.entities.User;
import com.rdpimenta.course.repositories.UserRepository;
import com.rdpimenta.course.services.excepetions.DatabaseException;
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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		try {
			User obj = repository.getById(id);
			
			updateData(user, obj);
			
			return repository.save(obj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User user, User obj) {
		obj.setName(user.getName());
		obj.setEmail(user.getEmail());
		obj.setPhone(user.getPhone());
	}
}
