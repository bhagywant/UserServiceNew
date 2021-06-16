package com.user.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserRegistrationDto;
import com.user.entity.UserEntity;
import com.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String registerUser(@RequestBody UserRegistrationDto userData) {
		boolean isPresent = this.userService.findByEmail(userData.getEmail());

		if (isPresent) {
			return "User Allready Exist !";
		} else {
			boolean IsUserDataSaved = this.userService.saveUserData(userData);
			if (IsUserDataSaved)
				return "User Registered Successfully";
			else
				return "Unable to register  the user";
		}
	}

	@PostMapping("/users/login")
	public String loginUser(@Valid @RequestBody UserRegistrationDto user) {
		Optional<UserEntity> userData = userService.findById(user.getId());
		if (userData.isPresent()) {
			UserEntity userEntity = userData.get();
			if (userEntity.getEmail().equalsIgnoreCase(user.getEmail())
					&& userEntity.getPassword().equals(user.getPassword())) {
				userEntity.setLoggedIn(true);
				this.userService.updateUserDataForLogin(userEntity);
				return "User Logined successfully";
			}
		} else {
			return "User Not Found";
		}
		return null;
	}

	@PostMapping("/logout")
	public String logoutUser(@Valid @RequestBody UserRegistrationDto user) {
		Optional<UserEntity> userData = userService.findById(user.getId());

		if (userData.isPresent()) {
			UserEntity userEntity = userData.get();
			userEntity.setLoggedIn(false);
			this.userService.updateUserDataForLogin(userEntity);
			return "User Logout successfully";
		} else {
			return "User Not Found";
		}
	}

	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable long id) {
		boolean deleteUser = this.userService.deleteUser(id);
		if (deleteUser)
			return "User Deleted Successfully";
		else
			return "User Not deleted ";

	}
}
