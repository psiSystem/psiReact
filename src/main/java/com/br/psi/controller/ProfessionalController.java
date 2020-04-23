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
import com.br.psi.model.Professional;
import com.br.psi.repository.ClientRepository;
import com.br.psi.repository.ProfessionalRepository;

@RestController
@RequestMapping("/api")
public class ProfessionalController {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/save", method = RequestMethod.POST)
    public ResponseEntity<Professional> save(@RequestBody Professional professional){
    	 this.professionalRepository.save(professional);
        return new ResponseEntity<Professional>(professional, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/edit", method = RequestMethod.PUT)
    public ResponseEntity<Professional> edit(@RequestBody Professional professional){
        this.professionalRepository.save(professional);
        return new ResponseEntity<Professional>(professional, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/professional/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Professional>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return new ResponseEntity<Page<Professional>>(professionalRepository.findAll(pageable), HttpStatus.OK);
    }


}
