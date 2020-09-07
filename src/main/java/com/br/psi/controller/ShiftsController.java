package com.br.psi.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import com.br.psi.repository.DayWeekRepository;
import com.br.psi.repository.ShiftsRepository;
import com.br.psi.repository.ShiftsRepositoryService;

@RestController
@RequestMapping
public class ShiftsController {

    @Autowired
    private ShiftsRepository shiftsRepository;
    @Autowired
    private DayWeekRepository dayWeekRepository;
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
    		dayWeekRepository.save(shift.getDayWeek());
    		shiftsRepository.save(shift);
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
    	findByDayWeekOfficeRoom	 = shiftsRepositoryService.findByProfessionalAndProfessionalFormation(filter,user.getPerson().getClient());
    	List<Object> findByTimeAvailable = shiftsRepositoryService.findByTimeAvailable(filter, user.getPerson().getClient());
    	
    	findByDayWeekOfficeRoom.forEach((shifts) -> {
    		getCalendar(shifts,findByTimeAvailable);
    	});
    	
        return new ResponseEntity<List<Shifts>>(list, HttpStatus.OK);
    }

	private void getCalendar(Shifts shifts, List<Object> findByTimeAvailable) {
		Date start = shifts.getTimeStart();
		Date ended = shifts.getTimeEnd();
		LocalDateTime now = LocalDateTime.now();
		
		for(int i = 0; i < 56; i++) {
			if(now.getDayOfWeek().getValue() == shifts.getDayWeek().getDayOfWeek().intValue()) {
					LocalDateTime localStart = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), start.getHours(), start.getMinutes());
					LocalDateTime localEned = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), ended.getHours(), ended.getMinutes());
					Shifts model = new Shifts();
					model.setTimeStart(Date.from(localStart.atZone(ZoneId.systemDefault()).toInstant()));
					model.setTimeEnd(Date.from(localEned.atZone(ZoneId.systemDefault()).toInstant()));
					model.setProfessional(shifts.getProfessional());
					model.setDayWeek(shifts.getDayWeek());
					model.setTimeAvailable(getAvailable(findByTimeAvailable,model));
					if(localEned.isAfter(LocalDateTime.now()))
						list.add(model);
				}
				
			now = now.plusDays(1);
		}
	
		
	
		
	}

	private Boolean getAvailable(List<Object> findByTimeAvailable, Shifts shifts) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (Object object : findByTimeAvailable) {
			Object[] obj = (Object[]) object;
			System.out.println(object);
			Date dateStart = null;
			try {
				dateStart = format.parse(obj[3].toString());
			} catch (ParseException e) {
				return true;
			}
			String professionalId = obj[4].toString();
			String dayWeek = obj[1].toString();
			if (dateStart.compareTo(shifts.getTimeStart()) == 0
					&& professionalId.equals(shifts.getProfessional().getId().toString())
					&& dayWeek.equals(shifts.getDayWeek().getDayOfWeek().toString())) {
				return false;
			}

		}
		
		return true;
	}
}
