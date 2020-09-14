package com.br.psi.repository.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.br.psi.model.Client;
import com.br.psi.model.PaymentPatient;
import com.br.psi.repository.PaymentPatientRepositoryService;

@Repository("paymentPatientRepositoryService")
public class PaymentPatientRepositoryDao implements PaymentPatientRepositoryService, Serializable {
	private static final long serialVersionUID = 1L;
	
	 @PersistenceContext
	 protected EntityManager entityManager;


	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentPatient> findByPatientPersonClient(Client client, PaymentPatient paymentPatient) {
		 return entityManager.createNativeQuery("select pp.*, s.amount amountConsumo from payment_patient pp "
				 	+ " inner join patient p on p.id = pp.patient_id"	
				 	+ " inner join person pe on pe.id = p.person_id"
				 	+" outer apply(" + 
				 	"	select sum(isnull(s.amount,0)) amount from schedule s where s.payment_patient_id = pp.id\r\n" + 
				 	") s\r\n" + 
				 	""
					+ " where pe.name like :name and pe.client_id =:client",PaymentPatient.class).setParameter("client", client.getId()).setParameter("name", "%"+paymentPatient.getPatient().getPerson().getName()+"%")
					.getResultList();
		
	}
	
	
}
