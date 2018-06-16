package com.sbz.projekat.SBZProjekat.disies;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DisiesRepository extends JpaRepository<Disies, Long> {

	List<Disies> findByType(Disies.Type type);

	Set<Disies> findBySymptoms_IdInOrSpecificSymptoms_IdIn(Set<Long> symptoms1, Set<Long> symptoms2);

}
