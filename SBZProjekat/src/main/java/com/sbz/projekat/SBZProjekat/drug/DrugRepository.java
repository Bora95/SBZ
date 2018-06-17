package com.sbz.projekat.SBZProjekat.drug;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
	
	public List<Drug> findByIdIn(Set<Long> ids);
	
}
