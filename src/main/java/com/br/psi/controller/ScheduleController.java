package com.br.psi.controller;
import java.text.ParseException;
import java.util.Comparator;
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
import com.br.psi.model.Patient;
import com.br.psi.model.PaymentPatient;
import com.br.psi.model.Professional;
import com.br.psi.model.Schedule;
import com.br.psi.model.User;
import com.br.psi.repository.PaymentPatientRepository;
import com.br.psi.repository.ScheduleRepository;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PaymentPatientRepository paymentPatientRepository;
    
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/schedule/save", method = RequestMethod.POST)
    public ResponseEntity<List<Schedule>> save(@RequestBody List<Schedule> listSchedule) throws Exception{
    	for (Schedule schedule : listSchedule) {
    		if(schedule.getId() == null && schedule.getProfessional() != null && schedule.getKind() != null) {
    				List<Schedule> list = scheduleRepository.findByProfessionalAndDayOfWeekAndPatientAndDateStartAndKind(schedule.getProfessional(),schedule.getDayOfWeek(),schedule.getPatient(),new Date(),schedule.getKind());
    				proccessKindSchedule(schedule,list);
    		}
		}
    	 
        return new ResponseEntity<List<Schedule>>(listSchedule, HttpStatus.OK);
    }


	private void proccessKindSchedule(Schedule schedule, List<Schedule> listSch) throws Exception {
			switch (schedule.getKind().getAmountDay()) {
			case 0:
				createUnique(schedule,listSch);
				break;
			case 7:
				createWeek(schedule,listSch);
				break;
			case 14:
				createBiweekly(schedule,listSch);
				break;
			case 28:
				createMonth(schedule,listSch);
				break;
			default:
				break;
			}
		
	}

	private void creatList(Schedule schedule, List<Schedule> list) throws Exception {
		List<PaymentPatient> paymentPatients = paymentPatientRepository.findByPatient(schedule.getPatient());
		for (PaymentPatient paymentPatient : paymentPatients) {
			
			Integer amountMultiple = schedule.getKind().getAmountMultiple();
			schedule.setPaymentPatient(paymentPatient);
				if(paymentPatient.getAmount() != null) {
					List<Schedule> findByPaymentPatient = scheduleRepository.findByPaymentPatient(paymentPatient);
					amountMultiple = paymentPatient.getAmount() - findByPaymentPatient.size() - 1;
				}
			
			if(amountMultiple > 0) {
				list.add(schedule);
			for (int i = 1; i <= amountMultiple ; i++) {
				Date dateStart = new Date(schedule.getDateStart().getTime());
				Date dateEnd = new Date(schedule.getDateEnd().getTime());
				Schedule model = new Schedule();
				model.setProfessional(schedule.getProfessional());
				model.setPatient(schedule.getPatient());
				model.setKind(schedule.getKind());
				dateStart.setDate(dateStart.getDate() + (schedule.getKind().getAmountDay() * i));
				dateEnd.setDate(dateEnd.getDate() + (schedule.getKind().getAmountDay() * i));
				model.setDateStart(dateStart);
				model.setDateEnd(dateEnd);
				model.setDayOfWeek(schedule.getDayOfWeek());
				model.setPaymentPatient(paymentPatient);
				list.add(model);
			}
		
			}else {
				throw new Exception("Paciente não possui créditos disponível.");
			}
		}
	}

	private void createMonth(Schedule schedule, List<Schedule> list) throws Exception {
		creatList(schedule,list);
		this.scheduleRepository.saveAll(list);
	}

	private void createBiweekly(Schedule schedule, List<Schedule> list) throws Exception {
		creatList(schedule,list);
		this.scheduleRepository.saveAll(list);
		
	}

	private void createWeek(Schedule schedule, List<Schedule> list) throws Exception {
		creatList(schedule,list);
		this.scheduleRepository.saveAll(list);		
	}

	private void createUnique(Schedule schedule, List<Schedule> list) throws Exception {
		//alterar metodo para validar status de pagamentos validos.
		List<PaymentPatient> paymentPatients = paymentPatientRepository.findByPatient(schedule.getPatient());
		for (PaymentPatient paymentPatient : paymentPatients) {
			List<Schedule> findByPaymentPatient = scheduleRepository.findByPaymentPatient(paymentPatient);
				if(findByPaymentPatient.size() < paymentPatient.getAmount()) {
					this.scheduleRepository.save(schedule);
					return;
				}
		}
		throw new Exception("Paciente não possui créditos disponível.");
	}


	private void deleteList(List<Schedule> list) {
		this.scheduleRepository.deleteAll(list);
		list.removeAll(list);
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
	@Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/schedule/releaseSchedule", method = RequestMethod.POST)
    public ResponseEntity releaseSchedule(@RequestBody Schedule schedule){
		schedule = scheduleRepository.findById(schedule.getId());
		List<Schedule> list = scheduleRepository.findByProfessionalAndDayOfWeekAndPatientAndDateStartAndKind(schedule.getProfessional(),schedule.getDayOfWeek(),schedule.getPatient(), new Date(),schedule.getKind());
        this.scheduleRepository.deleteAll(list);
        return new ResponseEntity<>( HttpStatus.OK);
    }
	

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/schedule/findByProfession", method = RequestMethod.POST)
    public ResponseEntity<List<Schedule>> list(@RequestBody Schedule schedule){
    	List<Schedule> list = scheduleRepository.findByProfessionalAndDateStartAndDateEnd(schedule.getProfessional(),schedule.getDateStart(),schedule.getDateEnd());
    	list = createListSchedule(list,schedule);
    	
        return new ResponseEntity<List<Schedule>>(list, HttpStatus.OK);
    }

	private List<Schedule> createListSchedule(List<Schedule> list, Schedule schedule) {
		List<Schedule> listSchedule = list;
		Date date = new Date();
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
			model.setDayOfWeek(dateStart.getDay());
			for (Schedule schedule2 : list) {
				if(schedule2.getDateStart().compareTo(model.getDateStart()) == 0){
					model = null;
				}
				if(dateStart.before(schedule2.getDateEnd())) {
					dateEnd = schedule2.getDateEnd();
				}
			}
			
			dateStart = dateEnd;
			if(model != null && model.getDateStart().after(date) && !model.getDateEnd().after(new Date(end)))
				listSchedule.add(model);
		}
		
		listSchedule.sort(new Comparator<Schedule>() {

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
		return listSchedule;
	}

	
	@Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
	@RequestMapping(value = "/schedule/findAllByprofessional", method = RequestMethod.POST)
	public ResponseEntity<List<Schedule>> findAllByprofessional(@RequestBody Professional professional){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        
	    return new ResponseEntity<List<Schedule>>(scheduleRepository.findByProfessionalId(professional.getId()), HttpStatus.OK);
	}

}
