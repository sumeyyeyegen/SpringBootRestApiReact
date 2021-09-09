package org.jhipster.intro.service;

import java.util.List;
import java.util.Optional;

import org.jhipster.intro.domain.Student;
import org.jhipster.intro.domain.User;
import org.jhipster.intro.repository.StudentRepository;
import org.jhipster.intro.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.jhipster.web.util.PaginationUtil;

@Service
@Transactional
public class StudentService {
	
	private final Logger log = LoggerFactory.getLogger(UserService.class);
	
	private StudentRepository studentRepository;
	private UserRepository userRepository;
	
	public StudentService(StudentRepository studentRepository,UserRepository userRepository) {
		this.studentRepository=studentRepository;
		this.userRepository=userRepository;
	}
	
	public Page<Student> getAllStudents(Pageable pageable) {
//        log.debug("REST request to get a page of Students");
        Page<Student> page = studentRepository.findAll(pageable);
        return page;
    }
	
	public boolean add(Student student) {
		Student res = studentRepository.save(student);
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public Optional<Student> getStudent(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		
		return student;
	}

}
