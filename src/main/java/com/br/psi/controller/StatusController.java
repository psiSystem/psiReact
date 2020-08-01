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
import com.br.psi.model.Status;
import com.br.psi.repository.StatusRepository;

@RestController
@RequestMapping
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/status/save", method = RequestMethod.POST)
    public ResponseEntity<Status> save(@RequestBody Status status){
    	 this.statusRepository.save(status);
        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/status/edit", method = RequestMethod.PUT)
    public ResponseEntity<Status> edit(@RequestBody Status status){
        this.statusRepository.save(status);
        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/status/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Status>> list(){
        return new ResponseEntity<List<Status>>(statusRepository.findAll(), HttpStatus.OK);
    }


}
