package es.mindata.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.mindata.entity.Power;
import es.mindata.entity.Superhero;
import lombok.Data;

@Data
public class SuperheroDto {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("nickName")
	private String nickName;
	@JsonProperty("powers")
	private List<PowerDto> powers = new ArrayList<>();

	public SuperheroDto() {}
	
	public SuperheroDto(Superhero model) {
		this.id = model.getId();
		this.firstName = model.getFirstName();
		this.lastName = model.getLastName();
		this.nickName = model.getNickName();
		if (model.getPowers() != null) {
			this.powers = model.getPowers().stream()
					.map(item -> new PowerDto(item) ).collect(Collectors.toList());
		}
	}
	
	public List<PowerDto> getPowers() {
		return powers;
	}

	public void setPowers(List<PowerDto> list) {
		this.powers = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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


}
