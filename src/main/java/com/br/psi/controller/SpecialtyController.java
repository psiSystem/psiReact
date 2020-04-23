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
import com.br.psi.model.GroupSpecialty;
import com.br.psi.model.Specialty;
import com.br.psi.repository.SpecialtyRepository;

@RestController
@RequestMapping("/api")
public class SpecialtyController {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/specialty/save", method = RequestMethod.POST)
    public ResponseEntity<Specialty> save(@RequestBody Specialty specialty){
    	 this.specialtyRepository.save(specialty);
        return new ResponseEntity<Specialty>(specialty, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/specialty/edit", method = RequestMethod.PUT)
    public ResponseEntity<Specialty> edit(@RequestBody Specialty specialty){
        this.specialtyRepository.save(specialty);
        return new ResponseEntity<Specialty>(specialty, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/specialty/findByGroupSpecialty", method = RequestMethod.POST)
    public ResponseEntity<List<Specialty>> list(@RequestBody GroupSpecialty groupSpecialty){
        return new ResponseEntity<List<Specialty>>(specialtyRepository.findByGroupSpecialty(groupSpecialty), HttpStatus.OK);
    }


}
