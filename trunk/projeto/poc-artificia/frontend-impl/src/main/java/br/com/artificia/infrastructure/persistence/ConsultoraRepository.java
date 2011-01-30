package br.com.artificia.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.consultora.IConsultora;
import br.com.artificia.domain.model.consultora.IConsultoraRepository;

@Repository
public class ConsultoraRepository implements IConsultoraRepository {

	@PersistenceContext
	private EntityManager em;

	public IConsultora findById(long idConsultora) {
		return (IConsultora) em.find(Consultora.class, idConsultora);
	}
	
	

}
