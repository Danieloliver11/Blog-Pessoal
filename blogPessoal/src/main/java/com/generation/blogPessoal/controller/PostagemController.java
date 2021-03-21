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


@RestController // define uma classe controller
@RequestMapping("/post") // define uma URI para ser consumida no front-end
@CrossOrigin("*")   // aceita requisicao de qualquer origem, seja ela web, mobile etc
public class PostagemController {
	@Autowired // criamos uma ingecao de dependecia porque a classe abaixo, se trata de uma interface, entao nao pode ser instanciada
	private PostagemRepository repository; //pega da interface esse PostagemRepository.
	
	@GetMapping		// retorna todos os dados contidos dentro da base de dados			
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll()); // caso a resposta do servidor seja OK, entao e impresso os dados ao usuario
	}
					// {id} valor que vai vir para uri (url)
	@GetMapping("/{id}")						//@PathVariable vai trazer uma variação do caminho para o id 
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id) // cria uma condicao de retorno
				.map(resp -> ResponseEntity.ok(resp)) // lambda caso haja dados nesse id requisitado, entao e retornado os dados do id
				.orElse(ResponseEntity.notFound().build()); // caso contrario, e retornado uma mensagem de erro 404, informado que os dados solicitados nao foram localizados na base de dados
	}
	// fazendo uma busca por titulo
	@GetMapping("/titulo/{titulo}")  // retorna uma pequisa de um titulo ou por partes das letras desse titulo
	public ResponseEntity<List<Postagem>>GetByTitulo(@PathVariable String titulo){  // recebe como parametro letras de um titulo
		return ResponseEntity.ok(repository.findAllBytituloContainingIgnoreCase(titulo)); // por meio da query criada dentro do Repository, podemos utilizalo para 
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
