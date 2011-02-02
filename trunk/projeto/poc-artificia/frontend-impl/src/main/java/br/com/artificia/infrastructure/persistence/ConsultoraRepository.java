package br.com.artificia.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.consultora.IConsultoraRepository;

@Repository
public class ConsultoraRepository implements IConsultoraRepository<Consultora> {

	@PersistenceContext
	private EntityManager em;

	public Consultora findById(long idConsultora) {
		return  em.find(Consultora.class, idConsultora);
	}
	
	

}
