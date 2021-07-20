package es.mindata.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.mindata.entity.Power;
import es.mindata.entity.Superhero;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase {

	 @Bean
	 CommandLineRunner initPowers(IPowerRepository repo) {
		 Set<Power> powers = new HashSet<Power>(){{
			    add(new Power(1,"name1"));
			    add(new Power(2,"name2"));
			    add(new Power(3,"name3"));
			}};
	        return args -> {
	            for (Power item:powers) {
	                repo.save(item);
	            }
	        };
	    }
	 
	 @Bean
	 CommandLineRunner initSuperheroes(ISuperHeroRepository repo) {
		 Set<Superhero> heroes = new HashSet<Superhero>(){{
			    add(new Superhero(1,"firstName1","lastName1","nick1"));
			    add(new Superhero(2,"firstName2","lastName2","nick2"));
			    add(new Superhero(3,"firstName3","lastName3","nick3"));
			}};
	        return args -> {
	            for (Superhero item:heroes) {
	            	//item.addPower(new Power("name1"));
	                repo.save(item);
	            }
	        };
	    }
}
