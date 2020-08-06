package com.br.psi.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.br.psi.dto.FilterCalendar;
import com.br.psi.model.Const;
import com.br.psi.model.DayWeek;
import com.br.psi.model.Formation;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Shifts;
import com.br.psi.model.User;
import com.br.psi.repository.ShiftsRepository;
import com.br.psi.repository.ShiftsRepositoryService;

@RestController
@RequestMapping
public class ShiftsController {

    @Autowired
    private ShiftsRepository shiftsRepository;
    @Autowired
    private ShiftsRepositoryService shiftsRepositoryService;
    private List<Shifts> list ;
    
    @Secured({Const.ROLE_ADMIN,Const.ROLE_CLIENT})
    @RequestMapping(value = "/shift/save", method = RequestMethod.POST)
    public ResponseEntity<Shifts> save(@RequestBody Shifts shift){
    	 this.shiftsRepository.save(shift);
        return new ResponseEntity<Shifts>(shift, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN,Const.ROLE_CLIENT})
    @RequestMapping(value = "/shift/edit", method = RequestMethod.PUT)
    public ResponseEntity<List<Shifts>>  edit(@RequestBody List<Shifts> listShift){
    	
    	listShift.forEach(shift -> {
    		shift.getDayWeek().setDayOfWeek(DayWeek.dayWeek(shift.getDayWeek()));
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
    @RequestMapping(value = "/shift/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody Shifts shift){
    	shift.setDayWeek(null);
    	shiftsRepository.delete(shift);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/shift/findAllOfficeRoom", method = RequestMethod.POST)
    public ResponseEntity<List<Shifts>> listOfficeRoom(@RequestBody OfficeRoom officeRoom){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<List<Shifts>>(shiftsRepository.findByDayWeekOfficeRoom(officeRoom), HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/shift/listOfficeRoomCalendar", method = RequestMethod.POST)
    public ResponseEntity<List<Shifts>> listOfficeRoomCalendar(@RequestBody FilterCalendar filter){
    	list = new ArrayList<Shifts>();
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List<Shifts> findByDayWeekOfficeRoom  = new ArrayList<Shifts>();
    	if(filter.getFormation().getId() != null && filter.getProfessional().getId() != null) {
    		findByDayWeekOfficeRoom	 = shiftsRepository.findByProfessionalAndProfessionalFormation(filter.getProfessional(),filter.getFormation());
    	}else if (filter.getFormation().getId() != null) {
    		findByDayWeekOfficeRoom	 = shiftsRepository.findByProfessionalFormation(filter.getFormation());
    	}else if (filter.getProfessional().getId() != null){
    		findByDayWeekOfficeRoom	 = shiftsRepository.findByProfessional(filter.getProfessional());
    	}else {
    		findByDayWeekOfficeRoom = shiftsRepository.findAllByDayWeekOfficeRoomClient(user.getPerson().getClient());
    	}
    	
    	
    	findByDayWeekOfficeRoom.forEach((shifts) -> {
    		getCalendar(shifts);
    	});
    	
        return new ResponseEntity<List<Shifts>>(list, HttpStatus.OK);
    }

	private void getCalendar(Shifts shifts) {
		Date start = new Date(shifts.getTimeStart());
		Date ended = new Date(shifts.getTimeEnd());
		LocalDateTime now = LocalDateTime.now();
		for(int i = 0; i < 56; i++) {
			if(now.getDayOfWeek().getValue() == shifts.getDayWeek().getDayOfWeek().intValue()) {
					LocalDateTime localStart = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), start.getHours(), start.getMinutes());
					LocalDateTime localEned = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), ended.getHours(), ended.getMinutes());
					Shifts model = new Shifts();
					model.setTimeStart(localStart.toString());
					model.setTimeEnd(localEned.toString());
					model.setProfessional(shifts.getProfessional());
					model.setDayWeek(shifts.getDayWeek());
					model.setTimeAvailable(shiftsRepositoryService.findByTimeAvailable(model.getDayWeek().getDayOfWeek(),model.getProfessional(),localStart,localEned,model.getDayWeek().getOfficeRoom()));
					if(localEned.isAfter(LocalDateTime.now()))
						list.add(model);
				}
				
			now = now.plusDays(1);
		}
	}
}
