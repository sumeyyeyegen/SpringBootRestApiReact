package org.jhipster.intro.service.dto;

public class ExamResultDTO {
	private Long id;
	private Long studentId;
	private Long courseId;
	private int score;
	
	public ExamResultDTO() {
		
	}
	
	public ExamResultDTO(Long id, Long studentId, Long courseId, int score) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.score = score;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ExamResultDTO [id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", score=" + score
				+ "]";
	}

}
