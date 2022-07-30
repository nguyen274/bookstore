package com.project.library.repository;

import com.project.library.model.CallCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CallCardRepository extends JpaRepository<CallCard, Long> {
    public List<CallCard> findByReturned(Integer returned);

    @Query("select  c  from CallCard as c where c.issueDate between :year and :month  ")
    List<Object[]> turnoverOfMoth(Date year, Date month);

}
