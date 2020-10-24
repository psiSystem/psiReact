package com.br.psi.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.dto.PatientDto;
import com.br.psi.dto.ValidateCpfAndEmail;
import com.br.psi.model.Const;
import com.br.psi.model.Patient;
import com.br.psi.model.PaymentPatient;
import com.br.psi.model.Permission;
import com.br.psi.model.Professional;
import com.br.psi.model.Schedule;
import com.br.psi.model.User;
import com.br.psi.repository.PatientRepository;
import com.br.psi.repository.PatientRepositoryService;
import com.br.psi.repository.PaymentPatientRepository;
import com.br.psi.repository.PermissionRepository;
import com.br.psi.repository.ProfessionalRepository;
import com.br.psi.repository.UserRepository;

@Component
@RestController
@RequestMapping
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private ProfessionalRepository professionalRepository;
    
    @Autowired
    private PatientRepositoryService patientRepositoryService;

    @Autowired
    private PaymentPatientRepository paymentPatientRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/validateCpfAndEmail", method = RequestMethod.POST)
    public ResponseEntity<ValidateCpfAndEmail> validateCpfAndEmail(@RequestBody @Valid  ValidateCpfAndEmail validateCpfAndEmail){
    	return new ResponseEntity<ValidateCpfAndEmail>(validateCpfAndEmail, HttpStatus.OK);
    }
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public ResponseEntity<Patient> save(@RequestBody @Valid  PaymentPatient patientPayment){
    	
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Patient patient = patientPayment.getPatient();
    	patient.getPerson().setClient(user.getPerson().getClient());
    	
    	try {
    		this.patientRepository.save(patient);	
		} catch (Exception e) {
			
			return new ResponseEntity<Patient>(patient, HttpStatus.BAD_REQUEST);
			
		}
    	 
    	 
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
    public ResponseEntity<List<PatientDto>> list(){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return new ResponseEntity<List<PatientDto>>(patientRepositoryService.findByPersonClient(user.getPerson().getClient()), HttpStatus.OK);
    }
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/findAllPatient", method = RequestMethod.POST)
    public ResponseEntity<List<PatientDto>> findAllPatient(@RequestBody Patient patient){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List<PatientDto> list = patientRepositoryService.findByPersonClientAndPatient(user.getPerson().getClient(),patient);
        return new ResponseEntity<List<PatientDto>>(list, HttpStatus.OK);
    }
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/findById", method = RequestMethod.GET)
    public ResponseEntity<Patient> findById(@RequestParam(value = "id") Long id){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Patient patient = patientRepositoryService.findById(id);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }
    
    
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/patient/findPaymentPatient", method = RequestMethod.POST)
    public ResponseEntity<List<PaymentPatient>> findPaymentPatient(@RequestBody Schedule schedule){
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Professional professional = professionalRepository.findAllById(schedule.getProfessional().getId());
    	
    	List<PaymentPatient> list = patientRepositoryService.findPaymentPatient(schedule.getPatient(),professional.getFormation());
    	
        return new ResponseEntity<List<PaymentPatient>>(list, HttpStatus.OK);
    }
    

}
