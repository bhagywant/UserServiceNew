package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.dto.UserRegistrationDto;
import com.user.entity.UserEntity;

public interface UserService {

	boolean findByEmail(String email);

	boolean saveUserData(UserRegistrationDto userData);

	List<UserEntity> findAll();

	Optional<UserEntity> findById(long id);

	void updateUserDataForLogin(UserEntity userEntity);

	boolean deleteUser(long id);

}
