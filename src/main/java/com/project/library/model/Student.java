package com.project.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentId", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "grade_id")
	private Grade grade;

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@NotBlank(message = "Please enter student code")
	@Column(name = "studentCode", length = 100, nullable = false)
	private String studentCode;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private LibraryCard libraryCard;

	@Column(name = "status", nullable = false)
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LibraryCard getLibraryCard() {
		return libraryCard;
	}

	public void setLibraryCard(LibraryCard libraryCard) {
		this.libraryCard = libraryCard;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	@NotBlank(message = "*Please enter student name")
	@Column(name = "studentName", length = 100, nullable = false)
	private String studentName;

	@NotBlank(message = "*Please enter student address")
	@Column(name = "studentAddress", length = 100, nullable = false)
	private String studentAddress;

	@NotBlank(message = "*Please enter student phone")
	@Column(name = "studentPhone", length = 10, nullable = false)
	private String studentPhone;

	@NotNull(message = "*Please enter student birth")
	@Column(name = "studentBirth", nullable = false)
	private Date studentBirth;

	@NotNull(message = "*Please enter student genre")
	@Column(name = "studentGenre", nullable = false)
	private String studentGenre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public Date getStudentBirth() {
		return studentBirth;
	}

	public void setStudentBirth(Date studentBirth) {
		this.studentBirth = studentBirth;
	}

	public String getStudentGenre() {
		return studentGenre;
	}

	public void setStudentGenre(String studentGenre) {
		this.studentGenre = studentGenre;
	}

}
