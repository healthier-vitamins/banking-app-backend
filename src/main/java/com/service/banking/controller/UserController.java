package com.service.banking.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.banking.exception.RoleNotFoundException;
import com.service.banking.model.BankAccount;
import com.service.banking.model.Customer;
import com.service.banking.model.ChangeUserPasswordRequest;
import com.service.banking.model.Role;
import com.service.banking.model.User;
import com.service.banking.repo.UserRepo;
import com.service.banking.service.UserService;
import com.service.banking.utility.DateFormatterUtil;

import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
//	@PutMapping("/save-updated")
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	public ResponseEntity<User> saveUpdatedUser(@RequestBody User user) {
//		return ResponseEntity.ok().body(userService.saveUser(user));
//	}

	@GetMapping("/get-user/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		return ResponseEntity.ok(userService.getByUsername(username));
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<Object> editUser(@RequestBody ChangeUserPasswordRequest editUserCreds) {
		User user = userService.getByUsername(editUserCreds.getUsername()); 
		if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username not found");
		// to add throws within service class
		// surround all with try catch
		
//		String encodedOldPassword = passwordEncoder.encode(editUserCreds.getOldPassword());
		if(passwordEncoder.matches(editUserCreds.getOldPassword(), user.getPassword())) {
			user.setPassword(editUserCreds.getNewPassword());
			userService.saveUser(user);
			System.out.println("[TERMINAL] -- Password changed for " + user.getUsername() + " --");
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Old password does not match");
		}
	}
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		String authorizationHeader = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				User user = userService.getByUsername(username);
				String access_token = JWT.create().withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRole().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
//				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception e) {
				e.printStackTrace();
				response.setHeader("error", e.getMessage());
				response.setStatus(org.springframework.http.HttpStatus.FORBIDDEN.value());
//				response.sendError(org.springframework.http.HttpStatus.FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
				response.setStatus(400);
//				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}

		} else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
	
}
