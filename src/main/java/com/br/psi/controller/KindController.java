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
import com.br.psi.model.Kind;
import com.br.psi.repository.KindRepository;

@RestController
@RequestMapping("/api")
public class KindController {

    @Autowired
    private KindRepository kindRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/kind/save", method = RequestMethod.POST)
    public ResponseEntity<Kind> save(@RequestBody Kind kind){
    	 this.kindRepository.save(kind);
        return new ResponseEntity<Kind>(kind, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/kind/edit", method = RequestMethod.PUT)
    public ResponseEntity<Kind> edit(@RequestBody Kind schedule){
        this.kindRepository.save(schedule);
        return new ResponseEntity<Kind>(schedule, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/kind/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Kind>> list(){
    	
        return new ResponseEntity<List<Kind>>(kindRepository.findAll(), HttpStatus.OK);
    }


}
