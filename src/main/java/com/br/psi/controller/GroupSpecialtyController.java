package com.br.psi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Client;
import com.br.psi.model.Const;
import com.br.psi.model.Formation;
import com.br.psi.model.PlanHeath;
import com.br.psi.repository.GroupSpecialtyRepository;
import com.br.psi.repository.PlanHeathRepository;

@RestController
@RequestMapping
public class GroupSpecialtyController {

    @Autowired
    private GroupSpecialtyRepository groupSpecialtyRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/groupSpecialty/save", method = RequestMethod.POST)
    public ResponseEntity<Formation> save(@RequestBody Formation groupSpecialty){
    	 this.groupSpecialtyRepository.save(groupSpecialty);
        return new ResponseEntity<Formation>(groupSpecialty, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/groupSpecialty/edit", method = RequestMethod.PUT)
    public ResponseEntity<Formation> edit(@RequestBody Formation groupSpecialty){
        this.groupSpecialtyRepository.save(groupSpecialty);
        return new ResponseEntity<Formation>(groupSpecialty, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/groupSpecialty/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Formation>> list(){
        return new ResponseEntity<List<Formation>>(groupSpecialtyRepository.findAll(), HttpStatus.OK);
    }


}
