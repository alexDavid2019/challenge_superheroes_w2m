package es.mindata.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mindata.entity.Power;
import es.mindata.repository.IPowerRepository;

@Service
public class PowerService {

	 @Autowired
	 private IPowerRepository powerRepository;

	 public Power Register(String name)  {
		 Power _new = new Power(name);
    	return SavePower(_new);
     }

	 public Power SavePower(Power p)  {
	    	return powerRepository.save(p);
	 }

	 public Optional<Power> getById(Integer id)  {
	    	return powerRepository.findById(id);
     }

	  public List<Power> ListAll() {
		  return powerRepository.findAll(); 
	  }
}
