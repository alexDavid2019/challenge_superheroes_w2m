package es.mindata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mindata.entity.Power;

@Repository
public interface IPowerRepository extends JpaRepository<Power, Integer> {
	
}
