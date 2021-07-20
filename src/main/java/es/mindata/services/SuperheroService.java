package es.mindata.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mindata.dto.PowerDto;
import es.mindata.dto.SuperheroDto;
import es.mindata.entity.Power;
import es.mindata.entity.Superhero;
import es.mindata.repository.IPowerRepository;
import es.mindata.repository.ISuperHeroRepository;

@Service
public class SuperheroService {

	  @Autowired
	  private ISuperHeroRepository heroesRepository;

	  @Autowired
	  private IPowerRepository powersRepository;

	  public Superhero Register(String firstName, String lastName, String nickName)  {
		  Superhero _new = new Superhero(firstName, lastName, nickName);
		  return SaveSuperHero(_new);
	  }
	  
	  public Superhero SaveSuperHero(Superhero p)  {
	    	return heroesRepository.save(p);
	  }

	  public Optional<Superhero> getById(Integer id)  {
	    	return heroesRepository.findById(id);
	  }
	  
	  public List<Superhero> ListAll() {
		  return heroesRepository.findAll(); 
	  }
	  
	  public List<SuperheroDto> findAllSuperheroes(){
		  List<Superhero> _list = heroesRepository.findAll(); 
		  if (_list != null) {
			  return _list.stream()
					  .filter(e -> e.getActive().equals(true))
					  .map(item -> new SuperheroDto(item) ).collect(Collectors.toList());
		  }
		  return null;
	  }
	  
	  public SuperheroDto findSuperheroById(Integer id){
		  Superhero _item = heroesRepository.getOne(id); 
		  if (_item != null) {
			  return new SuperheroDto(_item);
		  }
		  return null;
	  }
	  	
	  
	  public SuperheroDto createSuperhero(SuperheroDto toCreate)
	  {
		  List<Superhero> _exists = null;
		  List<Superhero> _heroes = heroesRepository.findAll(); 
		  List<Power> _powers = powersRepository.findAll();
		  
		  if (_heroes != null) {
			  _exists = _heroes.stream()
					  .filter(e -> e.getActive().equals(true) 
							  && e.getNickName().equals(toCreate.getNickName()))
					 .collect(Collectors.toList());
		  }
		  
		  if (_exists == null || _exists.isEmpty()) {
			  Superhero _hero = new Superhero();
			  _hero.setFirstName(toCreate.getFirstName());
			  _hero.setLastName(toCreate.getLastName());
			  _hero.setNickName(toCreate.getNickName());
			  _hero.setActive(true);
			  
			  if ( toCreate.getPowers() != null) 
			  {
				  for(PowerDto p:toCreate.getPowers()) 
				  {
					  if (_hero.getPowers() != null) 
					  {
						 Optional<Power> _powerInHero = _hero.getPowers().stream()
								 	.filter(e -> e.getName().toLowerCase().equals(p.getName())).findAny();
						 Optional<Power> _powerAll = _powers.stream()
								 	.filter(e -> e.getName().toLowerCase().equals(p.getName())).findAny();
						
						 if (!_powerInHero.isPresent()) {
							 if (!_powerAll.isPresent()) {
								 _hero.addPower(new Power(p.getName()));
							 } else {
								 _hero.addPower(_powerAll.get());
							 }
						 }
					  }
				  }
			  }
			  
			  Superhero saved = SaveSuperHero(_hero);
			  return new SuperheroDto(saved);
		  }
		  return null;
	  }

	  public SuperheroDto updateSuperheroById(Integer id,SuperheroDto toUpdate){
		  Superhero _hero = heroesRepository.getOne(id); 
		  List<Power> _powers = powersRepository.findAll();
		  
		  if (_hero != null) 
		  {
			  _hero.setFirstName(toUpdate.getFirstName());
			  _hero.setLastName(toUpdate.getLastName());
			  _hero.setNickName(toUpdate.getNickName());
			  _hero.setActive(true);
			  
			  if ( toUpdate.getPowers() != null) 
			  {
				  if (_hero.getPowers() != null) 
				  {
					  _hero.cleanPower();
				  }
				  
				  for(PowerDto p:toUpdate.getPowers()) 
				  {
					 Optional<Power> _powerAll = _powers.stream()
							 	.filter(e -> e.getName().toLowerCase().equals(p.getName())).findAny();
					 if (!_powerAll.isPresent()) {
						 _hero.addPower(new Power(p.getName()));
					 } else {
						 _hero.addPower(_powerAll.get());
					 }
				  }
			  }
			  
			  Superhero saved = SaveSuperHero(_hero);
			  
			  return new SuperheroDto(saved);
		  }
		  return null;
	  }
	  
	  public SuperheroDto deleteSuperheroById(Integer id){
		  Superhero _hero = heroesRepository.getOne(id); 
		  if (_hero != null) 
		  {
			  _hero.setActive(false);
			  Superhero saved = SaveSuperHero(_hero);
			  return new SuperheroDto(saved);
		  }
		  return null;
	  }
	  
	  public List<SuperheroDto> findSuperheroesByFilter(String name){
		  List<Superhero> _list = heroesRepository.findAll();
		  if (_list != null) {
			  return _list.stream()
					  .filter(e -> e.getActive().equals(true) && 
							  (e.getNickName().toLowerCase().contains(name.toLowerCase()) 
							  || e.getFirstName().toLowerCase().contains(name.toLowerCase())
							  || e.getLastName().toLowerCase().contains(name.toLowerCase())))
					  .map(item -> new SuperheroDto(item) ).collect(Collectors.toList());
		  }
		  return null;
	  }
	  
}
