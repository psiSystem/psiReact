package com.br.psi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Const;
import com.br.psi.model.Gender;
import com.br.psi.repository.GenderRepository;

@RestController
@RequestMapping("/api")
public class GenderController {

    @Autowired
    private GenderRepository genderRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/gender/save", method = RequestMethod.POST)
    public ResponseEntity<Gender> save(@RequestBody Gender gender){
    	 this.genderRepository.save(gender);
        return new ResponseEntity<Gender>(gender, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/gender/edit", method = RequestMethod.PUT)
    public ResponseEntity<Gender> edit(@RequestBody Gender gender){
        this.genderRepository.save(gender);
        return new ResponseEntity<Gender>(gender, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/gender/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Gender>> list(){
        return new ResponseEntity<List<Gender>>(genderRepository.findAll(), HttpStatus.OK);
    }


}
