package com.generation.blogPessoal.Service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64; // importar manualmente para funcionar o Base64.encodeBase64
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogPessoal.model.UserLogin;
import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		
		if(repository.findByUsuario(usuario.getUsuario()).isPresent()) //verifica se esse usuario já existe
			return null;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(repository.save(usuario)); //salvamos a senha ja incriptada
	}
	
	//logar
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				// aplicando senha encriptada

				String auth = user.get().getUsuario() + ":" + user.get().getSenha(); 
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				// preencher  o token
				user.get().setToken(authHeader);
				
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
			
		return user;
			} 
		}
		return null;
	}
}
