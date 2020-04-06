package com.br.psi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permission implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String descricao; 
    private String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Module moduleId; 

    public Permission(String name, String descricao) {
        this.name = name;
        this.descricao = descricao;
    }
    public Permission() {
    }
    
    @Override
    public String getAuthority() {
        return  this.name;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Module getModuleId() {
		return moduleId;
	}
	public void setModuleId(Module moduleId) {
		this.moduleId = moduleId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
	
}
