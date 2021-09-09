package org.jhipster.intro.repository;

import org.jhipster.intro.domain.ExamResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExamResult entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
	
}
