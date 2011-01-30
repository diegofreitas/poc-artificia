package br.com.artificia.domain.model.consultora;

import br.com.artificia.domain.model.consultora.IConsultora;

public interface IConsultoraRepository {

	IConsultora findById(long idConsultora);
	
}
