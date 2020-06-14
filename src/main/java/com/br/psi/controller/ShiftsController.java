package com.br.psi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Const;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Shifts;
import com.br.psi.model.User;
import com.br.psi.repository.ShiftsRepository;

@RestController
@RequestMapping("/api")
public class ShiftsController {

    @Autowired
    private ShiftsRepository shiftsRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/shift/save", method = RequestMethod.POST)
    public ResponseEntity<Shifts> save(@RequestBody Shifts shift){
    	 this.shiftsRepository.save(shift);
        return new ResponseEntity<Shifts>(shift, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/shift/edit", method = RequestMethod.PUT)
    public ResponseEntity<List<Shifts>>  edit(@RequestBody List<Shifts> listShift){
    	
    	listShift.forEach(shift -> {
    		 this.shiftsRepository.save(shift);
    	});
       
        return new ResponseEntity<List<Shifts>>( listShift,HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/shift/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Shifts>> list(){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<List<Shifts>>(shiftsRepository.findByDayWeekOfficeRoomClient(user.getPerson().getClient()), HttpStatus.OK);
    }
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/shift/findAllOfficeRoom", method = RequestMethod.POST)
    public ResponseEntity<List<Shifts>> listOfficeRoom(@RequestBody OfficeRoom officeRoom){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<List<Shifts>>(shiftsRepository.findByDayWeekOfficeRoom(officeRoom), HttpStatus.OK);
    }


}
