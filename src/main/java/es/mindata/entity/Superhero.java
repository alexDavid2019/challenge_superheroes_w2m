package es.mindata.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "superheroes",uniqueConstraints = {@UniqueConstraint(columnNames = "nickname")})
public class Superhero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
    @NotEmpty
	@Column(name = "firstname")
	private String firstName;

	@NotBlank
    @NotEmpty
	@Column(name = "lastname")
	private String lastName;

	@NotBlank
    @NotEmpty
	@Column(name = "nickname")
	private String nickName;

	@Column(name = "active")
	private Boolean active;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "superheroes_powers",
		joinColumns = { @JoinColumn(name = "superhero_id") },
		inverseJoinColumns = { @JoinColumn(name = "power_id") })
	@JsonIgnore
	private List<Power> powers = new ArrayList<>();

	
	public Superhero() {
	}

	public Superhero(@NotBlank String firstName, 
							@NotBlank String lastName, 
							@NotBlank String nickName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.active = true;
	}
	
	public Superhero(@NotBlank Integer id, 
							@NotBlank String firstName, 
							@NotBlank String lastName, 
							@NotBlank String nickName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.active = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean val) {
		this.active = val;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<Power> getPowers() {
		return powers;
	}

	public void setPowers(List<Power> list) {
		this.powers = list;
	}
	
	public void addPower(Power val) {
	    this.powers.add(val);
    }
	
	public void removePower(Power val) {
	    this.powers.remove(val);
    }
	
	public void cleanPower() {
	    this.powers.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Superhero)) return false;
        Superhero data = (Superhero) o;
        return Objects.equals(this.firstName, data.getFirstName()) &&
                Objects.equals(this.lastName, data.getLastName()) && 
                Objects.equals(this.nickName, data.getNickName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nickName);
    }

    @Override
    public String toString() {
        return "Superhero{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

}
