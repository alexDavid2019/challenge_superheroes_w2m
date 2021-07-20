package es.mindata.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mindata.advise.TrackExecutionTime;
import es.mindata.dto.GenericResponseDto;
import es.mindata.dto.SuperheroDto;
import es.mindata.enums.Status;
import es.mindata.services.SuperheroService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/superheroes")
@Slf4j
@Validated
public class SuperheroController {

	@Autowired
	private SuperheroService superService;
	
	@CrossOrigin()
	@GetMapping(value = "/")
    @TrackExecutionTime
	public ResponseEntity<?> getSuperheroes() {
		
		try
    	{
			List<SuperheroDto> _results = superService.findAllSuperheroes();
			GenericResponseDto<List<SuperheroDto>> _ret = new GenericResponseDto<List<SuperheroDto>>(_results,Status.SUCCESS,null);
    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
		} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}
	}
	
	@CrossOrigin()
	@GetMapping(value = "/{id}")
    @TrackExecutionTime
	public ResponseEntity<?> getSuperhero(@PathVariable(value = "id")
											@NotNull
											final Integer id) {
		
		try
    	{
			SuperheroDto _result = superService.findSuperheroById(id);
			if (_result != null) {
				GenericResponseDto<SuperheroDto> _ret = new GenericResponseDto<SuperheroDto>(_result,Status.SUCCESS,null);
				return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else {
			   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,	Status.NOT_FOUND,
						Arrays.asList("No se encuentran regitros con id:" + id));
			   	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_ret);
			}
		} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}
	}

	@CrossOrigin()
	@GetMapping(value = "/filter/{name}")
    @TrackExecutionTime
	public ResponseEntity<?> getSuperheroesByFilter(@PathVariable(value = "name")
											@NotNull
											@NotBlank
											final String name) {
		try
    	{
			List<SuperheroDto> _results = superService.findSuperheroesByFilter(name);
			if (_results != null) {
				GenericResponseDto<List<SuperheroDto>> _ret = new GenericResponseDto<List<SuperheroDto>>(_results,Status.SUCCESS,null);
				return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else {
			   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,	Status.NOT_FOUND,
						Arrays.asList("No se encuentran regitros con contenido:" + name));
			   	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_ret);
			}
		} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}
	}

	@CrossOrigin()
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateSuperheroes(
			@PathVariable(value = "id") @NotNull final Integer id,
			@Valid @RequestBody SuperheroDto herotoUpdate) {

		if (herotoUpdate == null) 
        {
		   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
					Status.NOT_FOUND,
					Arrays.asList("Argumentos invalidos"));
		   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
        }

		try
    	{
			SuperheroDto _result = superService.updateSuperheroById(id,herotoUpdate);
			if (_result != null) {
				GenericResponseDto<SuperheroDto> _ret = new GenericResponseDto<SuperheroDto>(_result,Status.SUCCESS,null);
				return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else {
			   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,	Status.NOT_FOUND,
						Arrays.asList("No se encuentran regitros con id:" + id));
			   	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_ret);
			}
		} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}
		
	}

	@CrossOrigin()
	@PutMapping("/delete/{id}")
	public ResponseEntity<?> deleteSuperheroes(
			@PathVariable(value = "id") @NotNull final Integer id) {

		try
    	{
			SuperheroDto _result = superService.deleteSuperheroById(id);
			if (_result != null) {
				GenericResponseDto<SuperheroDto> _ret = new GenericResponseDto<SuperheroDto>(_result,Status.SUCCESS,null);
				return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else {
			   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,	Status.NOT_FOUND,
						Arrays.asList("No se encuentran regitros con id:" + id));
			   	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_ret);
			}
		} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}		
	}

	@CrossOrigin()
	@PostMapping("/create")
	public ResponseEntity<?> createSuperheroes(
			@Valid @RequestBody SuperheroDto herotoCreate) {
        
		if (herotoCreate == null) 
        {
		   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
					Status.NOT_FOUND,
					Arrays.asList("Argumentos invalidos"));
		   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
        }
		
		try
    	{
			SuperheroDto _result = superService.createSuperhero(herotoCreate);
			if (_result != null) {
				GenericResponseDto<SuperheroDto> _ret = new GenericResponseDto<SuperheroDto>(_result,Status.SUCCESS,null);
				return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else {
			   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,	Status.NOT_FOUND,
						Arrays.asList("Nick ya existe:" + herotoCreate.getNickName() ));
			   	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_ret);
			}
		} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}		
    }
}
