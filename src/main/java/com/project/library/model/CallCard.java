package com.project.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "call_card")
public class CallCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "returned")
    private Integer returned;

    @Column(name = "expected_return_date")
    private Date expectedReturnDate;

    @OneToMany(mappedBy = "callCard", cascade = CascadeType.ALL)
    private List<CallCardDetail> callCardDetails;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "staff_id")
    @NotNull
    private Staff staff;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "library_card_id")
    @NotNull
    private LibraryCard libraryCard;

    @Column(name = "total", nullable = false)
    private Float total;

    @Column(name = "total_fines")
    private Float totalFines;

    @Column(name = "total_deposit_price")
    private Float totalDepositPrice;

    @Column(name = "full_name")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Float getTotalDepositPrice() {
        return totalDepositPrice;
    }

    public Integer getReturned() {
        return returned;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setTotalDepositPrice(Float totalDepositPrice) {
        this.totalDepositPrice = totalDepositPrice;
    }

    public Float getTotalFines() {
        return totalFines;
    }

    public void setTotalFines(Float totalFines) {
        this.totalFines = totalFines;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Staff getStaff() {
        return staff;
    }

    public List<CallCardDetail> getCallCardDetails() {
        return callCardDetails;
    }

    public Long getId() {
        return id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public void setCallCardDetails(List<CallCardDetail> callCardDetails) {
        this.callCardDetails = callCardDetails;
    }
}
