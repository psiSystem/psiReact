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
import com.br.psi.model.PlanHeath;
import com.br.psi.repository.PlanHeathRepository;

@RestController
@RequestMapping("/api")
public class PlanHeathController {

    @Autowired
    private PlanHeathRepository planHeathRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/planHeath/save", method = RequestMethod.POST)
    public ResponseEntity<PlanHeath> save(@RequestBody PlanHeath planHeath){
    	 this.planHeathRepository.save(planHeath);
        return new ResponseEntity<PlanHeath>(planHeath, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/planHeath/edit", method = RequestMethod.PUT)
    public ResponseEntity<PlanHeath> edit(@RequestBody PlanHeath planHeath){
        this.planHeathRepository.save(planHeath);
        return new ResponseEntity<PlanHeath>(planHeath, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "/planHeath/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<PlanHeath>> list(){
        return new ResponseEntity<List<PlanHeath>>(planHeathRepository.findAll(), HttpStatus.OK);
    }


}
