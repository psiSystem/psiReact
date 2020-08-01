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
import com.br.psi.repository.ClientRepository;

@RestController
@RequestMapping
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/client/save", method = RequestMethod.POST)
    public ResponseEntity<Client> save(@RequestBody Client client){
    	 this.clientRepository.save(client);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/client/edit", method = RequestMethod.PUT)
    public ResponseEntity<Client> edit(@RequestBody Client client){
        this.clientRepository.save(client);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/client/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Client>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return new ResponseEntity<Page<Client>>(clientRepository.findAll(pageable), HttpStatus.OK);
    }


}
