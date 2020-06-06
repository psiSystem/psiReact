package com.br.psi.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Const;
import com.br.psi.model.Permission;
import com.br.psi.model.Professional;
import com.br.psi.model.User;
import com.br.psi.repository.PermissionRepository;
import com.br.psi.repository.ProfessionalRepository;
import com.br.psi.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ProfessionalController {

    @Autowired
    private ProfessionalRepository professionalRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/save", method = RequestMethod.POST)
    public ResponseEntity<Professional> save(@RequestBody @Valid Professional professional){
    	
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	professional.getPerson().setClient(user.getPerson().getClient());
    	
    	this.professionalRepository.save(professional);
    	
    	List<Permission> list = new ArrayList<Permission>();
    	list.add(permissionRepository.findByName(Const.ROLE_PRFESSIONAL));
    	
    	user = new User(professional.getPerson().getEmail(), passwordEncoder.encode(professional.getPassword()), list);
    	user.setEnable(Boolean.TRUE);
    	user.setPerson(professional.getPerson());
    	
    	this.userRepository.save(user);
    	
        return new ResponseEntity<Professional>(professional, HttpStatus.CREATED);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/edit", method = RequestMethod.PUT)
    public ResponseEntity<Professional> edit(@RequestBody @Valid Professional professional){
        this.professionalRepository.save(professional);
        return new ResponseEntity<Professional>(professional, HttpStatus.CREATED);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/professional/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Professional>> list(){
        
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return new ResponseEntity<List<Professional>>(professionalRepository.findByPersonClient(user.getPerson().getClient()), HttpStatus.OK);
    }


}
