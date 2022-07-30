package com.project.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long id;

    @NotBlank(message = "*Please enter staff code")
    @NotNull(message = "*Please enter staff code")
    @Column(name = "staff_code", length = 10, nullable = false)
    private String staffCode;

    @NotBlank(message = "*Please enter staff name")
    @NotNull(message = "*Please enter staff name")
    @Column(name = "staff_name", length = 100, nullable = false)
    private String staffName;

    @NotNull(message = "*Please enter gender")
    @Column(name = "gender", length = 100, nullable = false)
    private String gender;

    @NotBlank(message = "*Please enter phone number")
    @NotNull(message = "*Please enter phone number")
    @Column(name = "phone_number", length = 100, nullable = false)
    private String phoneNumber;

    @NotNull(message = "*Please enter birth date")
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User users;

    @OneToMany( mappedBy = "staff",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CallCard> callCards;

    public List<CallCard> getCallCards() {
        return callCards;
    }

    public void setCallCards(List<CallCard> callCards) {
        this.callCards = callCards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
