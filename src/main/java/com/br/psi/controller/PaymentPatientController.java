package com.br.psi.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Const;
import com.br.psi.model.PaymentPatient;
import com.br.psi.repository.PaymentPatientRepository;

@RestController
@RequestMapping
public class PaymentPatientController {

    @Autowired
    private PaymentPatientRepository paymentPatientRepository;

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
        return new ResponseEntity<List<PaymentPatient>>(paymentPatientRepository.findAll(), HttpStatus.OK);
    }


}
