package com.project.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "Vui lòng nhập mã khách hàng")
    @Column(name = "customer_code", nullable = false)
    private String customerCode;

    @NotBlank(message = "Vui lòng nhập tên khách hàng!")
    @Column(name = "customer_name",length = 100, nullable = false)
    private String customerName;

    @NotEmpty(message = "*Please select gender")
    @NotNull(message = "*Please select gender")
    @Column(name = "gender")
    private String gender;

    @NotNull(message = "*Please enter birth date")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "joining_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joiningDate;

    @NotBlank(message = "Please enter contact")
    @Column(name = "contact")
    private String contact;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "status", nullable = false)
    private Integer status;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private LibraryCard libraryCard;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
