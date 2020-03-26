package com.springboot.api;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.dto.UserDTO;
import com.springboot.service.UserService;

@Controller
public class HomeAPI {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<UserDTO>> getAll() {
		List<UserDTO> listUsers = new ArrayList<UserDTO>();
		listUsers = userService.getListUsers();
		return ResponseEntity.ok(listUsers);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> requestLogin(@RequestBody UserDTO userDTO) {
		Boolean result = userService.checkLogin(userDTO);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	@PostMapping("/register")
	public ResponseEntity<Boolean> requestRegisterUser(@RequestBody UserDTO userDTO) {
		Boolean result = userService.checkRegisterUser(userDTO);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/list-users")
	public String userListPage() {
		return "list-users";
	}
	
	@GetMapping("/get-list-users")
	public ResponseEntity<List<UserDTO>> requestListUser(){
		List<UserDTO> listUsers = userService.getListUsers();
		return ResponseEntity.ok(listUsers);
	}

	@PutMapping("/update-user/{id}")
	public ResponseEntity<Boolean> requestUpdateUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO){
		userDTO.setId(id);
		Boolean result = userService.updateUser(userDTO);
		if(result.equals(true)){
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<Boolean> requestDeleteUser(@PathVariable("id") Integer id){
		Boolean result = userService.deleteUser(id);
		if(result.equals(true)){
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

	@GetMapping("/login-webix")
	public String userLoginWebixPage() {
		return "login-webix";
	}

	@GetMapping("/design-webix")
	public String designWebixPage() {
		return "design-webix";
	}

	@GetMapping("/get-list-users-by-username")
	public ResponseEntity<List<UserDTO>> requestGetListUsersByUsername(@PathParam("username") String username) {
		List<UserDTO> result = userService.getListUsersByUsername(username);
		return ResponseEntity.ok(result);
	}
}
