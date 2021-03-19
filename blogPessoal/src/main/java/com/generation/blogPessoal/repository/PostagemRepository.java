package com.generation.blogPessoal.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogPessoal.model.Postagem;


@Repository													//mapea A Postagem do tipo long que é referente ao Id.
public interface PostagemRepository extends JpaRepository<Postagem,Long> {
	public List<Postagem> findAllBytituloContainingIgnoreCase (String titulo);
	// vai fazer uma lista de Postagem e o nome dessa lista vai ser :findAllBytituloContainingIgnoreCase.
	// findAllBytituloContainingIgnoreCasede: de Acordo com o cliente digitar sera ignorado maiúsculo ou minusculo e usara como parâmetro é titulo.
} 
