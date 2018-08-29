package com.ddobrowolski.budgetManager.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
public class User implements UserDetails {
	public static enum Role{ USER }

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	@NotEmpty
	private String username;
	@Transient
	private String password;
	private BigDecimal monthBudget;
	private String role;
	@Transient
	private String passwordEncrypted;
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotEmpty
	private String email;
	public User() {}
	public User(Long id, String username, String password, BigDecimal monthBudget, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.monthBudget = monthBudget;
		this.email = email;
	}
	
	public String getPassword() {
		return passwordEncrypted;
	}
	public void setPassword(String password) {
		this.passwordEncrypted = password;
	}
	
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	
	
}
