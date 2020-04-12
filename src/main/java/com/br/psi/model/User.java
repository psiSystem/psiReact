package com.br.psi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    @JsonIgnore
    private String password;
    private Boolean enable;
    @ManyToOne(cascade = CascadeType.PERSIST)
	private Person person;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="permission_user", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="permission_id")
    )
    private List<Permission> permissions;

    public User() {
    }

    public User(String userName) {
        super();
        this.userName = userName;
    }
    public User(User user) {
        super();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.permissions = user.getPermissions();
        this.id = user.getId();
    }
    public User(String userName,  String password, List<Permission> permissions) {
        super();
        this.userName = userName;
        this.permissions = permissions;
        this.password = password;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
    
    
}