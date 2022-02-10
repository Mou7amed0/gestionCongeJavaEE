package com.projetjee.gestionConge.entities;

import javax.persistence.*;

@Entity
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name = "LOGIN")
	private String username;
	@Column(name = "PASSWORD")
	 private String password;

	@OneToOne(mappedBy = "login")
	private Salarie user;

	public Login() {
	}

	public Login(String username, String password){
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Salarie getUser() {
		return user;
	}

	public void setUser(Salarie user) {
		this.user = user;
	}
}