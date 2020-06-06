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
import com.br.psi.model.GroupSpecialty;
import com.br.psi.model.PlanHeath;
import com.br.psi.repository.GroupSpecialtyRepository;
import com.br.psi.repository.PlanHeathRepository;

@RestController
@RequestMapping("/api")
public class GroupSpecialtyController {

    @Autowired
    private GroupSpecialtyRepository groupSpecialtyRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/groupSpecialty/save", method = RequestMethod.POST)
    public ResponseEntity<GroupSpecialty> save(@RequestBody GroupSpecialty groupSpecialty){
    	 this.groupSpecialtyRepository.save(groupSpecialty);
        return new ResponseEntity<GroupSpecialty>(groupSpecialty, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/groupSpecialty/edit", method = RequestMethod.PUT)
    public ResponseEntity<GroupSpecialty> edit(@RequestBody GroupSpecialty groupSpecialty){
        this.groupSpecialtyRepository.save(groupSpecialty);
        return new ResponseEntity<GroupSpecialty>(groupSpecialty, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/groupSpecialty/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<GroupSpecialty>> list(){
        return new ResponseEntity<List<GroupSpecialty>>(groupSpecialtyRepository.findAll(), HttpStatus.OK);
    }


}
