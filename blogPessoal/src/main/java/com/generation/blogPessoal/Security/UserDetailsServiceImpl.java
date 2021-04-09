package com.generation.blogPessoal.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		
		Optional<Usuario> user = userRepository.findByUsuario(userName);
		/* PARA VALIDAR O LOGIN, VERIFICAMOS SE O USUARIO CADASTRADOS SE ENCONTRA EM NOSSA BASE DE DADOS */
		user.orElseThrow(()-> new UsernameNotFoundException(userName + " not found."));
		
		/* CASO O USUARIO ESTAJA CADASTRADO EM NOSSA BASE DE DADOS, RETORNAMOS O LOGIN BEM SUCEDIDO DO USUARIO */
		return user.map(UserDetailsImplementation::new).get();
	}

}
