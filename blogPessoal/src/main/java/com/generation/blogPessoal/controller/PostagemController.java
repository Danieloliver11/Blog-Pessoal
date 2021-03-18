package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;


@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostagemController {
	@Autowired
	private PostagemRepository repository; //pega da interface esse PostagemRepository.
	
	@GetMapping					
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
					// {id} valor que vai vir para uri (url)
	@GetMapping("/{id}")						//@PathVariable vai trazer uma variação do caminho para o id 
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) // lambda 
				.orElse(ResponseEntity.notFound().build());
	}
	// fazendo uma busca por titulo
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>>GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllBytituloContainingIgnoreCase(titulo));
	}
	
	//end point de postagem.
												//"post" vai devolver uma postagem no ResponseEntity
												//"Postagem" objeto e o nome dele sera "postagem". 
	@PostMapping								//"@RequestBody" Para Recepcionar os valores/objetos que são passadas via body para nossa aplicação
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		//para salvar os dados recebidos 
	}
	
	//put atualização de dados 
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	//delete
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
}
