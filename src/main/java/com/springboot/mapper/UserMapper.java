package com.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.dto.UserDTO;

@Mapper
public interface UserMapper {
	public List<UserDTO> findAll();
	public void insertUser(UserDTO userDTO);
	public void updateUserById(UserDTO userDTO);
	public void deleteUserById(Integer id);
	public void testTransaction();
}
