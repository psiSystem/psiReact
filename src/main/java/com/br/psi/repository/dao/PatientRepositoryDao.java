package com.br.psi.repository.dao;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.br.psi.dto.FinanceProfessional;
import com.br.psi.dto.PatientDto;
import com.br.psi.model.Client;
import com.br.psi.model.Formation;
import com.br.psi.model.Patient;
import com.br.psi.model.PaymentPatient;
import com.br.psi.repository.PatientRepositoryService;


@Component
@Repository("patientRepositoryService")
public class PatientRepositoryDao implements PatientRepositoryService, Serializable {
	private static final long serialVersionUID = 1L;
	
	 @PersistenceContext
	 protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value = "findByPersonClientAndPatient", key = "#client.id")
	public List<PatientDto> findByPersonClient(Client client) {
		 return entityManager.createNativeQuery("select convert(varchar(45),p.id) as id,pe.name,pe.cpf,pe.phone,a.anddress,pe.rg, pe.born_date as bornDate,g.description as gender from patient p "
					+ " inner join person pe on pe.id = p.person_id"
					+ " inner join gender g on g.id = pe.gender_id"
					+ " inner join anddress a on a .id = pe.anddress_id"
					+ " where pe.client_id =:client")
				 .setParameter("client", client.getId())
				 .unwrap(org.hibernate.query.Query.class)
					.setResultTransformer(Transformers.aliasToBean(PatientDto.class))
					.getResultList();
	}

	@Override
	public Patient findById(Long id) {
		return (Patient) entityManager.createQuery("from Patient p where p.id =:id ").setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
//    @Cacheable(value = "findByPersonClientAndPatient", key = "#client.id+#patient?.person?.name")
	public List<PatientDto> findByPersonClientAndPatient(Client client, Patient patient) {
		 List<PatientDto> resultList = entityManager.createNativeQuery("select convert(varchar(45),p.id) as id,pe.name,pe.cpf,pe.phone,a.anddress from patient p "
					+ " inner join person pe on pe.id = p.person_id"
					+ " inner join anddress a on a .id = pe.anddress_id"
					+ " where pe.name like :name and pe.client_id =:client")
				 .setParameter("client", client.getId())
				 .setParameter("name", "%"+patient.getPerson().getName()+"%")
				 .unwrap(org.hibernate.query.Query.class)
				 .setResultTransformer(Transformers.aliasToBean(PatientDto.class))
				 .getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentPatient> findPaymentPatient(Patient patient, Formation formation) {
		return  entityManager.createNativeQuery(""
				+ " select \r\n" + 
				"	pt.* ,s.amount amountConsumo\r\n" + 
				"from payment_patient pt\r\n" + 
				"outer apply(\r\n" + 
				"	select count(s.id) amount from schedule s where s.payment_patient_id = pt.id\r\n" + 
				") s\r\n" + 
				"where isnull(pt.amount,s.amount+1) > s.amount\r\n" + 
				"and pt.patient_id = :patient"
				+" and pt.formation_id =:formation"
				,PaymentPatient.class).setParameter("patient", patient.getId()).setParameter("formation", formation.getId()).getResultList();
		
	}
	
	@Override
//	@CacheEvict(value = "findByPersonClientAndPatient",  allEntries = true)
    public Patient save(Patient patient) {
        executeInsideTransaction(entityManager -> entityManager.persist(patient));
		return patient;
    }
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }


	
}
