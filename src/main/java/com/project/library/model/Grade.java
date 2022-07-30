package com.project.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "grade")
public class Grade implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gradeId", nullable = false) 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	@NotNull(message = "*Please select course")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "major_id")
	@NotNull(message = "*Please select major")
	private Major major;

	@OneToMany(mappedBy = "grade",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Course getCourse() {
		return course;
	}

	@NotBlank(message = "Please enter grade code")
    @Size(min = 3, max = 15)
    @Column(name = "gradeCode", length = 10, nullable = false)
	private String gradeCode;
	
    @NotBlank(message = "*Please enter grade name")
    @Column(name = "gradeName", length = 100, nullable = false)
	private String gradeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	
	
 	
	
	
	

}
