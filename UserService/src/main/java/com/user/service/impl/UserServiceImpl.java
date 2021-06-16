package com.user.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.UserRegistrationDto;
import com.user.entity.UserEntity;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean findByEmail(String email) {

		UserEntity userData = this.userRepository.findByEmail(email);
		if (Objects.nonNull(userData)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean saveUserData(UserRegistrationDto userData) {

		UserEntity user = new UserEntity();
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setEmail(userData.getEmail());
		// user.setPassword(passwordEncoder.encode(userData.getPassword()));
		user.setPassword(userData.getPassword());
		UserEntity IsSaved = userRepository.save(user);
		return IsSaved.getId() != null ? true : false;

	}

	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserEntity> findById(long id) {
		Optional<UserEntity> userData = this.userRepository.findById(id);
		return userData;
	}

	@Override
	public boolean deleteUser(long id) {
		try {
			this.userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
		}
		return false;

	}

	@Override
	public void updateUserDataForLogin(UserEntity userEntity) {
		this.userRepository.save(userEntity);
	}

}
