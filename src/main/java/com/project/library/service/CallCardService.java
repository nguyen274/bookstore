package com.project.library.service;

import com.project.library.model.CallCard;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CallCardService {
    public List<CallCard> getAll();

    public CallCard get(Long id);

    public List<CallCard> getAllUnreturned();

    public CallCard addNew(CallCard callCard);

    public CallCard save(CallCard callCard);

    Optional<CallCard> findById(Long id);

    List<Object[]> totalTurnoverOfMonth(Date year, Date month);

}
