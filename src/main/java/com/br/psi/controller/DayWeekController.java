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
import com.br.psi.model.OfficeRoom;
import com.br.psi.repository.OfficeRoomRepository;

@RestController
@RequestMapping("/api")
public class DayWeekController {

    @Autowired
    private OfficeRoomRepository officeRoomRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/officeRoom/save", method = RequestMethod.POST)
    public ResponseEntity<OfficeRoom> save(@RequestBody OfficeRoom officeRoom){
    	 this.officeRoomRepository.save(officeRoom);
        return new ResponseEntity<OfficeRoom>(officeRoom, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/officeRoom/edit", method = RequestMethod.PUT)
    public ResponseEntity<OfficeRoom> edit(@RequestBody OfficeRoom officeRoom){
        this.officeRoomRepository.save(officeRoom);
        return new ResponseEntity<OfficeRoom>(officeRoom, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/officeRoom/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<OfficeRoom>> list(){
        return new ResponseEntity<List<OfficeRoom>>(officeRoomRepository.findAll(), HttpStatus.OK);
    }


}
