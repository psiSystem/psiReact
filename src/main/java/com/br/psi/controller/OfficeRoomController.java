package com.br.psi.controller;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
import com.br.psi.model.DayWeek;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.User;
import com.br.psi.repository.DayWeekRepository;
import com.br.psi.repository.OfficeRoomRepository;
import com.br.psi.repository.ShiftsRepository;

@RestController
@RequestMapping("/api")
public class OfficeRoomController {


    @Autowired
    private OfficeRoomRepository officeRoomRepository;
    @Autowired
    private DayWeekRepository dayWeekRepository;
    @Autowired
    private ShiftsRepository shiftsRepository;
    
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/officeRoom/save", method = RequestMethod.POST)
    public ResponseEntity<OfficeRoom> save(@RequestBody OfficeRoom officeRoom){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	officeRoom.setClient(user.getPerson().getClient());
    	
    	this.officeRoomRepository.save(officeRoom);
    	
    	officeRoom.getListDayWeek().forEach(day ->{
    		 
    		 day.setOfficeRoom(officeRoom);
    		
    		 	dayWeekRepository.save(day);
    		 
    		 day.getListShifts().forEach(shifts -> {
    			 day.setDayOfWeek(DayWeek.dayWeek(day));
    			 shifts.setDayWeek(day);
    			 
    			 shiftsRepository.save(shifts);
    			 
    		 });
    	 });
    	
    	
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
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<List<OfficeRoom>>(officeRoomRepository.findByClient(user.getPerson().getClient()), HttpStatus.OK);
    }



}
