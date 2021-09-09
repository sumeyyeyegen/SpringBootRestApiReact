package org.jhipster.intro.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.jhipster.intro.IntegrationTest;
import org.jhipster.intro.domain.ExamResult;
import org.jhipster.intro.repository.ExamResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ExamResultResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExamResultResourceIT {

    private static final Long DEFAULT_STUDENT_ID = 1L;
    private static final Long UPDATED_STUDENT_ID = 2L;

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final String ENTITY_API_URL = "/api/exam-results";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExamResultMockMvc;

    private ExamResult examResult;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamResult createEntity(EntityManager em) {
        ExamResult examResult = new ExamResult().student_id(DEFAULT_STUDENT_ID).score(DEFAULT_SCORE).course_id(DEFAULT_COURSE_ID);
        return examResult;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamResult createUpdatedEntity(EntityManager em) {
        ExamResult examResult = new ExamResult().student_id(UPDATED_STUDENT_ID).score(UPDATED_SCORE).course_id(UPDATED_COURSE_ID);
        return examResult;
    }

    @BeforeEach
    public void initTest() {
        examResult = createEntity(em);
    }

    @Test
    @Transactional
    void getAllExamResults() throws Exception {
        // Initialize the database
        examResultRepository.saveAndFlush(examResult);

        // Get all the examResultList
        restExamResultMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(examResult.getId().intValue())))
            .andExpect(jsonPath("$.[*].student_id").value(hasItem(DEFAULT_STUDENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].course_id").value(hasItem(DEFAULT_COURSE_ID.intValue())));
    }

    @Test
    @Transactional
    void getExamResult() throws Exception {
        // Initialize the database
        examResultRepository.saveAndFlush(examResult);

        // Get the examResult
        restExamResultMockMvc
            .perform(get(ENTITY_API_URL_ID, examResult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(examResult.getId().intValue()))
            .andExpect(jsonPath("$.student_id").value(DEFAULT_STUDENT_ID.intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.course_id").value(DEFAULT_COURSE_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingExamResult() throws Exception {
        // Get the examResult
        restExamResultMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
