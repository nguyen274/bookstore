package com.project.library.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId", nullable = false) 
	private Long id;
	
	@NotBlank(message = "Please enter course code")
    @Size(min = 3, max = 15)
    @Column(name = "courseCode", length = 10, nullable = false)
	private String courseCode;

	
    @NotBlank(message = "*Please enter course name")
    @Column(name = "courseName", length = 100, nullable = false)
	private String courseName;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Grade> grades;

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
 	
	
	
	

}
