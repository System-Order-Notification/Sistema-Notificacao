package br.com.sp.notificacoes.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.notificacoes.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}
