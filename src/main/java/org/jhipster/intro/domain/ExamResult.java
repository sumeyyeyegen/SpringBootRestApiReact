package org.jhipster.intro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ExamResult.
 */
@Entity
@Table(name = "exam_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

//    @Column(name = "STUDENT_ID", insertable = false, updatable = false)
//    private Long student_id;

    @Column(name = "score")
    private Integer score;
//
//    @Column(name = "COURSE_ID", insertable = false, updatable = false)
//    private Long course_id;

    @OneToOne
    @JoinColumn(unique = true)
    private Student student;

    @JsonIgnoreProperties(value = { "students" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Course course;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamResult id(Long id) {
        this.id = id;
        return this;
    }

//    public Long getStudent_id() {
//        return this.student_id;
//    }
//
//    public ExamResult student_id(Long student_id) {
//        this.student_id = student_id;
//        return this;
//    }
//
//    public void setStudent_id(Long student_id) {
//        this.student_id = student_id;
//    }

    public Integer getScore() {
        return this.score;
    }

    public ExamResult score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

//    public Long getCourse_id() {
//        return this.course_id;
//    }
//
//    public ExamResult course_id(Long course_id) {
//        this.course_id = course_id;
//        return this;
//    }
//
//    public void setCourse_id(Long course_id) {
//        this.course_id = course_id;
//    }

    public Student getStudent() {
        return this.student;
    }

    public ExamResult student(Student student) {
        this.setStudent(student);
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return this.course;
    }

    public ExamResult course(Course course) {
        this.setCourse(course);
        return this;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamResult)) {
            return false;
        }
        return id != null && id.equals(((ExamResult) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExamResult{" +
            "id=" + getId() +
//            ", student_id=" + getStudent_id() +
            ", score=" + getScore() +
//            ", course_id=" + getCourse_id() +
            "}";
    }
}
