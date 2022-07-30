package com.project.library.service.impl;

import com.project.library.common.Constants;
import com.project.library.model.CallCard;
import com.project.library.repository.CallCardRepository;
import com.project.library.service.CallCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CallCardServiceImpl implements CallCardService {
    @Autowired
    private CallCardRepository callCardRepository;
    @Override
    public List<CallCard> getAll() {
        return callCardRepository.findAll();
    }

    @Override
    public CallCard get(Long id) {
        return callCardRepository.findById(id).get();
    }

    @Override
    public List<CallCard> getAllUnreturned() {
        return callCardRepository.findByReturned(Constants.BOOK_NOT_RETURNED);
    }

    @Override
    public CallCard addNew(CallCard callCard) {

        callCard.setIssueDate(new Date());
        callCard.setReturned(Constants.BOOK_NOT_RETURNED);
        return callCardRepository.save(callCard);
    }

    @Override
    public CallCard save(CallCard callCard) {
        return callCardRepository.save(callCard);
    }

    @Override
    public Optional<CallCard> findById(Long id) {
        return callCardRepository.findById(id);
    }

    @Override
    public List<Object[]> totalTurnoverOfMonth(Date year, Date month) {
        return callCardRepository.turnoverOfMoth(year, month);
    }
}
