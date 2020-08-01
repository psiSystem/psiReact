package com.br.psi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.psi.model.Const;
import com.br.psi.model.Payment;
import com.br.psi.repository.PaymentRepository;

@RestController
@RequestMapping
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/payment/save", method = RequestMethod.POST)
    public ResponseEntity<Payment> save(@RequestBody Payment payment){
    	 this.paymentRepository.save(payment);
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "/payment/edit", method = RequestMethod.PUT)
    public ResponseEntity<Payment> edit(@RequestBody Payment payment){
        this.paymentRepository.save(payment);
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN,Const.ROLE_PRFESSIONAL})
    @RequestMapping(value = "/payment/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> list(){
        return new ResponseEntity<List<Payment>>(paymentRepository.findAll(), HttpStatus.OK);
    }


}
