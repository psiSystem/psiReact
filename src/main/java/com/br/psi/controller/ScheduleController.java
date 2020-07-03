package com.br.psi.controller;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public ResponseEntity<List<Schedule>> save(@RequestBody List<Schedule> listSchedule){
    	for (Schedule schedule : listSchedule) {
    		if(schedule.getProfessional() != null && schedule.getKind() != null) {
    				this.scheduleRepository.save(schedule);	
    		}
		}
    	 
        return new ResponseEntity<List<Schedule>>(listSchedule, HttpStatus.OK);
    }


	@Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/schedule/edit", method = RequestMethod.PUT)
    public ResponseEntity<Schedule> edit(@RequestBody Schedule schedule){
        this.scheduleRepository.save(schedule);
        return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
    }
	
	@Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/schedule/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody Schedule schedule){
        this.scheduleRepository.delete(schedule);
        return new ResponseEntity<>( HttpStatus.OK);
    }
	

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/schedule/findByProfession", method = RequestMethod.POST)
    public ResponseEntity<List<Schedule>> list(@RequestBody Schedule schedule){
    	List<Schedule> list = scheduleRepository.findByProfessionalAndDateStartAndDateEnd(schedule.getProfessional(),schedule.getDateStart(),schedule.getDateEnd());
    	list = createListSchedule(list,schedule);
    	list.sort(new Comparator<Schedule>() {

			@Override
			public int compare(Schedule o1, Schedule o2) {
				if(o1.getId() != null && o2.getId() != null) {
					if(o1.getId() < o2.getId()) {
						return -1;
					}
					return 1;
				}
				
				if(o1.getId() == null && o2.getId() != null) {
					return -1;
				}
							
				return 1;
			}
    		
		});
        return new ResponseEntity<List<Schedule>>(list, HttpStatus.OK);
    }

	private List<Schedule> createListSchedule(List<Schedule> list, Schedule schedule) {
		List<Schedule> listSchedule = list;
		int timeSession = 30;
		long start = schedule.getDateStart().getTime();
		long end = schedule.getDateEnd().getTime();
		long interval = end - start;
		long amoutPosibled = (interval /1000 / 60) / timeSession;
		Date dateStart = schedule.getDateStart();
		for (int i = 0; i < amoutPosibled; i++) {
			Schedule model = new Schedule();
			model.setDateStart(dateStart);
			Date dateEnd = new Date(dateStart.getTime());
			dateEnd.setMinutes(dateEnd.getMinutes()+timeSession);
			model.setDateEnd(dateEnd);
			model.setProfessional(schedule.getProfessional());
			for (Schedule schedule2 : list) {
				if(schedule2.getDateStart().compareTo(model.getDateStart()) == 0){
					model = null;
					break;
				}
			}
			dateStart = dateEnd;
			if(model != null)
				listSchedule.add(model);
		}
		return listSchedule;
	}


}
