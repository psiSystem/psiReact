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
import com.br.psi.model.Schedule;
import com.br.psi.repository.ScheduleRepository;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/schedule/save", method = RequestMethod.POST)
    public ResponseEntity<Schedule> save(@RequestBody Schedule schedule){
    	 this.scheduleRepository.save(schedule);
        return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/schedule/edit", method = RequestMethod.PUT)
    public ResponseEntity<Schedule> edit(@RequestBody Schedule schedule){
        this.scheduleRepository.save(schedule);
        return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/schedule/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> list(){
        return new ResponseEntity<List<Schedule>>(scheduleRepository.findAll(), HttpStatus.OK);
    }


}
