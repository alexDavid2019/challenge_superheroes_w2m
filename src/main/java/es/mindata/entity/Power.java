package es.mindata.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "power",uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Power {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
    @NotEmpty
	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "powers")
	@JsonIgnore
	private Set<Superhero> superheroes = new HashSet<>();

	public Power() {
	}

	public Power(@NotBlank String name) {
		this.name = name;
	}
	
	public Power(@NotBlank Integer id, @NotBlank String name) {
		this.id = id;
		this.name = name;
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Superhero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(Set<Superhero> superheroes) {
        this.superheroes = superheroes;
    }

    public void addSuperhero(Superhero superhero) {
	    this.superheroes.add(superhero);
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Power)) return false;
        Power data = (Power) o;
        return Objects.equals(this.name, data.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Power{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
