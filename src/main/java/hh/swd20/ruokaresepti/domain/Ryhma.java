package hh.swd20.ruokaresepti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ryhma {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
private Long ryhmaid;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ryhma")
	private List<Ruoka> ruoat;
	
	public List<Ruoka> getRuoat() {
		return ruoat;
	}
public Ryhma() {}

public Ryhma(String name) {
	super();
	this.name = name;
}
	@Override
	public String toString() {
		return "Ryhma [ryhmaid=" + ryhmaid + ", name=" + name + "]";
	}

	public void setRuoat(List<Ruoka> ruoat) {
		this.ruoat = ruoat;
	}

	public Long getRyhmaid() {
		return ryhmaid;
	}

	public void setRyhmaid(Long ryhmaid) {
		this.ryhmaid = ryhmaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
