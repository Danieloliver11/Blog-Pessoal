package com.generation.blogPessoal.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogPessoal.model.Usuario;

public class UserDetailsImplementation implements UserDetails {

	private static final long serialVersionUID =1L;
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities; /* LEMBRAR DE SEMPRE INSERIR ESSE ATRIBUTO, PARA QUE POSSAMOS TRAZER O SEU VALOR 
	QUANDO FORMOS AUTENTICAR UM USUARIO PARA ACESSAR UMA DETERMIANDA PAGINA DA APLICACA */
	
	public UserDetailsImplementation(Usuario user) {
		this.userName = user.getUsuario();
		this.password =user.getSenha();
	}
	
	public UserDetailsImplementation() {} 
	
	/* COMO ESTAMOS IMPLEMENTADNDO UMA CLASSE, DEVEMOS IMPORTAR TODOS OS SEUS METODOS CRIADOS */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
