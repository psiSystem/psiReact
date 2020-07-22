package com.br.psi.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Client;
import com.br.psi.model.Const;
import com.br.psi.model.Patient;
import com.br.psi.model.PatientPayment;
import com.br.psi.model.PaymentPatient;
import com.br.psi.model.Permission;
import com.br.psi.model.Professional;
import com.br.psi.model.User;
import com.br.psi.repository.ClientRepository;
import com.br.psi.repository.PatientRepository;
import com.br.psi.repository.PaymentPatientRepository;
import com.br.psi.repository.PermissionRepository;
import com.br.psi.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PaymentPatientRepository paymentPatientRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public ResponseEntity<Patient> save(@RequestBody @Valid PaymentPatient patientPayment){
    	
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Patient patient = patientPayment.getPatient();
    	patient.getPerson().setClient(user.getPerson().getClient());
    	
    	 this.patientRepository.save(patient);
    	 
    	List<Permission> list = new ArrayList<Permission>();
    	list.add(permissionRepository.findByName(Const.ROLE_PATIENT));
    	
    	user = new User(patient.getPerson().getEmail(), passwordEncoder.encode(patient.getPerson().getCpf().replace(".", "").replace("-", "")), list);
    	user.setEnable(Boolean.TRUE);
    	user.setPerson(patient.getPerson());
    	
    	this.userRepository.save(user);
    	
    	this.paymentPatientRepository.save(patientPayment);
    	
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/edit", method = RequestMethod.PUT)
    public ResponseEntity<Patient> edit(@RequestBody Patient patient){
        this.patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> list(){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return new ResponseEntity<List<Patient>>(patientRepository.findByPersonClient(user.getPerson().getClient()), HttpStatus.OK);
    }


}
