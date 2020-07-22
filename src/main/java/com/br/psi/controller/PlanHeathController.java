package com.br.psi.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Client;
import com.br.psi.model.Const;
import com.br.psi.model.PlanHeath;
import com.br.psi.model.PlanHeathClient;
import com.br.psi.model.User;
import com.br.psi.repository.PlanHeathClientRepository;
import com.br.psi.repository.PlanHeathRepository;

@RestController
@RequestMapping("/api")
public class PlanHeathController {

    @Autowired
    private PlanHeathRepository planHeathRepository;
    @Autowired
    private PlanHeathClientRepository planHeathClientRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/planHeathClient/save", method = RequestMethod.POST)
    public ResponseEntity<List<PlanHeathClient>> save(@RequestBody PlanHeathClient planHeathClient){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	planHeathClient.setClient( user.getPerson().getClient());
    	this.planHeathClientRepository.save(planHeathClient);
    	 
    	 
        return new ResponseEntity<List<PlanHeathClient>>(planHeathClientRepository.findAllByClient(user.getPerson().getClient()), HttpStatus.OK);
    }
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/planHeathClient/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<PlanHeathClient>> listPlanHeathClient(){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<List<PlanHeathClient>>(planHeathClientRepository.findAllByClient(user.getPerson().getClient()), HttpStatus.OK);
    }
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/planHeathClient/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody PlanHeathClient planHeathClient ){
    		planHeathClientRepository.delete(planHeathClient);
        return new ResponseEntity( HttpStatus.OK);
    }

    
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/planHeath/edit", method = RequestMethod.PUT)
    public ResponseEntity<PlanHeath> edit(@RequestBody PlanHeath planHeath){
        this.planHeathRepository.save(planHeath);
        return new ResponseEntity<PlanHeath>(planHeath, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/planHeath/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<PlanHeath>> list(){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List<PlanHeathClient> findAllByClient = planHeathClientRepository.findAllByClient(user.getPerson().getClient());
    	List<PlanHeath> list = new ArrayList<PlanHeath>() ;
    	 findAllByClient.forEach((plan) -> {
    		 list.add(plan.getPlanHeath());
    	});
        return new ResponseEntity<List<PlanHeath>>(list, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/planHeath/findAllWithoutClient", method = RequestMethod.GET)
    public ResponseEntity<List<PlanHeath>> findAllWithoutClient(){
        return new ResponseEntity<List<PlanHeath>>(planHeathRepository.findAll(), HttpStatus.OK);
    }

}
