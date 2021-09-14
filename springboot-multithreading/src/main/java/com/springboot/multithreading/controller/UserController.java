package com.springboot.multithreading.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.multithreading.service.UserService;

@RestController
@RequestMapping("api/users/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
	public ResponseEntity saveUsers(@RequestParam(value ="files") MultipartFile[] files) {
		
		for(MultipartFile file: files) {
			try {
				userService.saveUsers(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(value="/list", produces="application/json")
	public CompletableFuture<ResponseEntity> findAllUsers(){
		return userService.findAllUsers().thenApply(ResponseEntity::ok);
	}
	
}
