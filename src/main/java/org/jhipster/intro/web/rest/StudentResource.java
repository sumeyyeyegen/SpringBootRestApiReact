package org.jhipster.intro.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.jhipster.intro.domain.Student;
import org.jhipster.intro.repository.StudentRepository;
import org.jhipster.intro.service.StudentService;
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
 * REST controller for managing {@link org.jhipster.intro.domain.Student}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StudentResource {

    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private final StudentRepository studentRepository;
    //StudentService entegre edildi. Api'den doğruca Repository'e ulaşmak yerine
    //Business katmanı olarak StudentService eklendi. 
    //TODO: Service kısmında tanımlanan fonskiyonları interface'e ekleyip oradan override işlemi gerçekleştirilebilir.
    //DRY
    private final StudentService studentService;

    
    public StudentResource(StudentRepository studentRepository,StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService=studentService;
    }
    
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(Pageable pageable) {
        log.debug("REST request to get a page of Students");
        Page<Student> page = studentService.getAllStudents(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/students/add")
    public boolean add(Student student) {
    	//service kısmında oluşturulan add fonksiyonu.
    	boolean res = studentService.add(student);
        return res;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        log.debug("REST request to get Student : {}", id);
        Optional<Student> student = studentService.getStudent(id);
        return ResponseUtil.wrapOrNotFound(student);
    }
}
