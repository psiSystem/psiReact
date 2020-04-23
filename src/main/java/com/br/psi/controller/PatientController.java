package com.br.psi.controller;
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
import com.br.psi.model.Patient;
import com.br.psi.repository.ClientRepository;
import com.br.psi.repository.PatientRepository;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
    	 this.patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/patient/edit", method = RequestMethod.PUT)
    public ResponseEntity<Patient> edit(@RequestBody Patient patient){
        this.patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/patient/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Patient>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return new ResponseEntity<Page<Patient>>(patientRepository.findAll(pageable), HttpStatus.OK);
    }


}
