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
		 List<PaymentPatient> resultList = (List<PaymentPatient>) entityManager.createNativeQuery("select pp.* from payment_patient pp "
				 	+ " inner join patient p on p.id = pp.patient_id"	
				 	+ " inner join person pe on pe.id = p.person_id"
					+ " inner join person_client cp on cp.person_id = pe.id"
					+ " where pe.name like :name and cp.client_id =:client",PaymentPatient.class).setParameter("client", client.getId()).setParameter("name", "%"+paymentPatient.getPatient().getPerson().getName()+"%")
					.getResultList();
		return resultList;
	}
	
	
}
