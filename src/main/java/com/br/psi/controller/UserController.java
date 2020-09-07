package com.br.psi.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.dto.ChangePassw;
import com.br.psi.model.Const;
import com.br.psi.model.User;
import com.br.psi.repository.UserRepository;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Secured({Const.ROLE_ADMIN,Const.ROLE_CLIENT,Const.ROLE_PRFESSIONAL,Const.ROLE_PATIENT})
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public ResponseEntity<User> save(@RequestBody ChangePassw changePassw){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String newPassword = passwordEncoder.encode(changePassw.getNewPassword());
    		User us = userRepository.findById(user.getId());
    		us.setPassword(newPassword);
    		user = userRepository.save(us);
    		return new ResponseEntity<User>(user, HttpStatus.OK);
    	
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/user/edit", method = RequestMethod.PUT)
    public ResponseEntity<User> edit(@RequestBody User user){
        user = this.userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("userName"));
        return new ResponseEntity<Page<User>>(userRepository.findAll(pageable), HttpStatus.OK);
    }


}
