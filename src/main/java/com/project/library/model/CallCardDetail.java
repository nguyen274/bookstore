package com.project.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "call_card_detail")
public class CallCardDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    @NotNull
    private Book book;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "call_card_id")
    @NotNull
    private CallCard callCard;

    @Column(name = "returned")
    private Integer returned;

    @Column(name = "return_date")
    private Date returnDate;

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CallCard getCallCard() {
        return callCard;
    }

    public void setCallCard(CallCard callCard) {
        this.callCard = callCard;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }
}
