package com.br.psi.model;

import javax.validation.Valid;

public class PatientPayment {
	@Valid
	private Patient patient;
	@Valid
	private PaymentPatient paymentPatient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PaymentPatient getPaymentPatient() {
		return paymentPatient;
	}

	public void setPaymentPatient(PaymentPatient paymentPatient) {
		this.paymentPatient = paymentPatient;
	}

}
