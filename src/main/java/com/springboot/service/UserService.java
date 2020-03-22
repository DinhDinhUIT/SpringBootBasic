package com.springboot.service;

import java.util.List;

import com.springboot.dto.UserDTO;

public interface UserService {
	public List<UserDTO> getListUsers();
	public Boolean checkRegisterUser(UserDTO userDTO);
	public Boolean checkLogin(UserDTO userDTO);
	public Boolean updateUser(UserDTO userDTO);
	public Boolean deleteUser(Integer id);
}
