package es.mindata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.mindata.entity.Power;
import lombok.Data;

@Data
public class PowerDto {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	private String name;

	public PowerDto() {}
	
	public PowerDto(Power model) {
		this.id = model.getId();
		this.name = model.getName();
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

}
