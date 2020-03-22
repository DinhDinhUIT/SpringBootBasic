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
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	@Transactional
	public List<UserDTO> getListUsers() {
		return userMapper.findAll();
		// throw new NullPointerException("abc");
	}

	@Override
	@Transactional
	public Boolean checkRegisterUser(UserDTO userDTO) {
		List<UserDTO> listUsers = userMapper.findAll();
		for(UserDTO user:listUsers) {
			if(userDTO.getUsername().equals(user.getUsername())) {
				return false;
			}
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(new Date());
		userDTO.setDate_created(date);
		userMapper.insertUser(userDTO);
		return true;
	}

	@Override
	@Transactional
	public Boolean checkLogin(UserDTO userDTO) {
		List<UserDTO> listUsers = userMapper.findAll();
		for(UserDTO user:listUsers) {
			if(userDTO.getUsername().equals(user.getUsername()) && userDTO.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public Boolean updateUser(UserDTO userDTO) {
		userMapper.updateUserById(userDTO);
		return true;
	}

	@Override
	@Transactional
	public Boolean deleteUser(Integer id) {
		userMapper.deleteUserById(id);
		return true;
	}
}
