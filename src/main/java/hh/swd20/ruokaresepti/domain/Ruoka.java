package hh.swd20.ruokaresepti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ruoka {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ryhmaid")
	private Ryhma ryhma;

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

	public Ryhma getRyhma() {
		return ryhma;
	}

	public void setRyhma(Ryhma ryhma) {
		this.ryhma = ryhma;
	}

	public Ruoka() {
	}
	public Ruoka(String name, Ryhma ryhma) {
		super();
		this.name = name;
		this.ryhma = ryhma;
	}

	@Override
	public String toString() {
		if (this.ryhma != null)
		return "Ruoka [id=" + id + ", name=" + name + ", ryhma=" + ryhma + "]";
		else
			return "Ruoka [id=" + id + ", name=" + name;
	}
	
	
}
