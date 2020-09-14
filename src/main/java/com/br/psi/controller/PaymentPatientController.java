package com.br.psi.controller;
import java.util.List;

import javax.validation.Valid;

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
import com.br.psi.model.PaymentPatient;
import com.br.psi.model.Schedule;
import com.br.psi.model.User;
import com.br.psi.repository.PaymentPatientRepository;
import com.br.psi.repository.PaymentPatientRepositoryService;
import com.br.psi.repository.ScheduleRepository;

@RestController
@RequestMapping
public class PaymentPatientController {

    @Autowired
    private PaymentPatientRepository paymentPatientRepository;
    @Autowired
    private PaymentPatientRepositoryService paymentPatientRepositoryService;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/paymentPatient/save", method = RequestMethod.POST)
    public ResponseEntity<PaymentPatient> save(@RequestBody @Valid PaymentPatient payment){
    	 this.paymentPatientRepository.save(payment);
        return new ResponseEntity<PaymentPatient>(payment, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/paymentPatient/edit", method = RequestMethod.PUT)
    public ResponseEntity<PaymentPatient> edit(@RequestBody PaymentPatient payment){
        this.paymentPatientRepository.save(payment);
        return new ResponseEntity<PaymentPatient>(payment, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/paymentPatient/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentPatient>> list(){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List<PaymentPatient> list = paymentPatientRepository.findByPatientPersonClient(user.getPerson().getClient());
    	
    	list.forEach(payment ->{
    		List<Schedule> Schedules = scheduleRepository.findByPaymentPatient(payment);
    		if(Schedules!= null) {
    			payment.setAmountConsumo(Schedules.size());
    		}
    	});
    	
        return new ResponseEntity<List<PaymentPatient>>(list, HttpStatus.OK);
    }
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/paymentPatient/findAllPaymentPaitent", method = RequestMethod.POST)
    public ResponseEntity<List<PaymentPatient>> findAllPaymentPaitent(@RequestBody PaymentPatient paymentPatient){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List<PaymentPatient> list = paymentPatientRepositoryService.findByPatientPersonClient(user.getPerson().getClient(),paymentPatient);
    	
    	
        return new ResponseEntity<List<PaymentPatient>>(list, HttpStatus.OK);
    }

    
}
