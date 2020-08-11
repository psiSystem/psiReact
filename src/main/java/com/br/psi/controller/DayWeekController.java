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
import com.br.psi.model.DayWeek;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.User;
import com.br.psi.repository.DayWeekRepository;
import com.br.psi.repository.OfficeRoomRepository;

@RestController
@RequestMapping("/api")
public class DayWeekController {

	 @Autowired
	    private DayWeekRepository dayWeekRepository;

	    @Secured({Const.ROLE_ADMIN})
	    @RequestMapping(value = "/dayWeek/save", method = RequestMethod.POST)
	    public ResponseEntity<DayWeek> save(@RequestBody DayWeek dayWeek){
	    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	dayWeek.getOfficeRoom().setClient(user.getPerson().getClient());
	    	 this.dayWeekRepository.save(dayWeek);
	        return new ResponseEntity<DayWeek>(dayWeek, HttpStatus.OK);
	    }

	    @Secured({Const.ROLE_ADMIN})
	    @RequestMapping(value = "/dayWeek/edit", method = RequestMethod.PUT)
	    public ResponseEntity<DayWeek> edit(@RequestBody DayWeek dayWeek){
	        this.dayWeekRepository.save(dayWeek);
	        dayWeek.setDayOfWeek(DayWeek.dayWeek(dayWeek));
	        return new ResponseEntity<DayWeek>(dayWeek, HttpStatus.OK);
	    }

	    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
	    @RequestMapping(value = "/dayWeek/findAll", method = RequestMethod.GET)
	    public ResponseEntity<List<DayWeek>> list(){
	        return new ResponseEntity<List<DayWeek>>(dayWeekRepository.findAll(), HttpStatus.OK);
	    }

	    
}
