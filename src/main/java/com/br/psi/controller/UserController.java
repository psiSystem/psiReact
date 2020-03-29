package com.br.psi.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.User;
import com.br.psi.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserRepository userRepository;
    
   
    public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
    Collection<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user")
    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("Request to create User: {}", user);
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/User/" + result.getId()))
                .body(result);
    }

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateGroup(@Valid @RequestBody User User) {
        log.info("Request to update User: {}", User);
        User result = userRepository.save(User);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete User: {}", id);
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}