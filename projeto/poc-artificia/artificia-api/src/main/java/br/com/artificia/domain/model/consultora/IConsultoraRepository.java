package br.com.artificia.domain.model.consultora;


public interface IConsultoraRepository<C> {

	C findById(long idConsultora);
	
}
