package org.jhipster.intro.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.jhipster.intro.domain.ExamResult;
import org.jhipster.intro.repository.ExamResultRepository;
import org.jhipster.intro.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.jhipster.intro.domain.ExamResult}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ExamResultResource {

    private final Logger log = LoggerFactory.getLogger(ExamResultResource.class);

    private final ExamResultRepository examResultRepository;

    public ExamResultResource(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    /**
     * {@code GET  /exam-results} : get all the examResults.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examResults in body.
     */
    @GetMapping("/exam-results")
    public ResponseEntity<List<ExamResult>> getAllExamResults(Pageable pageable) {
        log.debug("REST request to get a page of ExamResults");
        Page<ExamResult> page = examResultRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /exam-results/:id} : get the "id" examResult.
     *
     * @param id the id of the examResult to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examResult, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exam-results/{id}")
    public ResponseEntity<ExamResult> getExamResult(@PathVariable Long id) {
        log.debug("REST request to get ExamResult : {}", id);
        Optional<ExamResult> examResult = examResultRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(examResult);
    }
}
