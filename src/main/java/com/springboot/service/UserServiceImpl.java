package com.springboot.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.dto.UserDTO;
import com.springboot.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public List<UserDTO> getListUsers() {
		return userMapper.findAll();
	}

	@Override
	public Boolean checkLogin(UserDTO userDTO) {
		List<UserDTO> listUsers = userMapper.findAll();
		for (UserDTO user : listUsers) {
			if (userDTO.getUsername().equals(user.getUsername()) && userDTO.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public Boolean checkRegisterUser(UserDTO userDTO) {
		try {
			List<UserDTO> listUsers = userMapper.findAll();
			for (UserDTO user : listUsers) {
				if (userDTO.getUsername().equals(user.getUsername())) {
					return false;
				}
			}

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = simpleDateFormat.format(new Date());
			userDTO.setDate_created(date);
			userMapper.insertUser(userDTO);
			return true;
		} catch (Exception e) {
			throw new NullPointerException("An error occurred!");
		}
	}

	@Override
	@Transactional
	public Boolean updateUser(UserDTO userDTO) {
		try {
			userMapper.updateUserById(userDTO);
			return true;
		} catch (Exception e) {
			throw new NullPointerException("An error occurred!");
		}
	}

	@Override
	@Transactional
	public Boolean deleteUser(Integer id) {
		try {
			userMapper.deleteUserById(id);
			return true;
		} catch (Exception e) {
			throw new NullPointerException("An error occurred!");
		} 
	}

	@Override
	public List<UserDTO> getListUsersByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}
}
