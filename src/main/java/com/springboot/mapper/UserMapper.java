package com.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.dto.UserDTO;

@Mapper
public interface UserMapper {
	public List<UserDTO> findAll();
	public List<UserDTO> findUserByUsername(@Param("username") String username);
	public void insertUser(UserDTO userDTO);
	public void updateUserById(UserDTO userDTO);
	public void deleteUserById(Integer id);
}
