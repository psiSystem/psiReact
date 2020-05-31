package com.br.psi.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Const;
import com.br.psi.model.Professional;
import com.br.psi.model.User;
import com.br.psi.repository.ProfessionalRepository;

@RestController
@RequestMapping("/api")
public class ProfessionalController {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/save", method = RequestMethod.POST)
    public ResponseEntity<Professional> save(@RequestBody @Valid Professional professional){
    	
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	professional.getPerson().setClient(user.getPerson().getClient());
    	
    	this.professionalRepository.save(professional);
    	
        return new ResponseEntity<Professional>(professional, HttpStatus.CREATED);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/edit", method = RequestMethod.PUT)
    public ResponseEntity<Professional> edit(@RequestBody Professional professional){
        this.professionalRepository.save(professional);
        return new ResponseEntity<Professional>(professional, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Professional>> list(){
        
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return new ResponseEntity<List<Professional>>(professionalRepository.findByPersonClient(user.getPerson().getClient()), HttpStatus.OK);
    }


}
