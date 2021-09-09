package org.jhipster.intro.web.rest;

import java.util.List;
import java.util.Optional;

import org.jhipster.intro.domain.Course;
import org.jhipster.intro.domain.ExamResult;
import org.jhipster.intro.domain.Student;
import org.jhipster.intro.repository.CourseRepository;
import org.jhipster.intro.repository.ExamResultRepository;
import org.jhipster.intro.repository.StudentRepository;
import org.jhipster.intro.service.dto.ExamResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public ExamResultResource(ExamResultRepository examResultRepository,CourseRepository courseRepository,StudentRepository studentRepository) {
        this.examResultRepository = examResultRepository;
        this.courseRepository=courseRepository;
        this.studentRepository=studentRepository;
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
        System.out.println(page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/exam-results/add")
    public ExamResult add(ExamResultDTO dto) {
    	ExamResult exam = new ExamResult();
    	Course course = new Course();
    	Student stu = new Student();
    	exam.setCourse(courseRepository.getOne(dto.getCourseId()));
    	exam.setStudent(studentRepository.getOne(dto.getStudentId()));
    	exam.setId(dto.getId());
    	exam.setScore(dto.getScore());
    	
    	examResultRepository.save(exam);
        return exam;
    }

    @GetMapping("/exam-results/{id}")
    public ResponseEntity<ExamResult> getExamResult(@PathVariable Long id) {
        log.debug("REST request to get ExamResult : {}", id);
        Optional<ExamResult> examResult = examResultRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(examResult);
    }
}
