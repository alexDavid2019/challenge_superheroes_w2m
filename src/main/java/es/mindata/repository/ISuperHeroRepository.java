package es.mindata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mindata.entity.Superhero;

@Repository
public interface ISuperHeroRepository extends JpaRepository<Superhero, Integer>{

}
