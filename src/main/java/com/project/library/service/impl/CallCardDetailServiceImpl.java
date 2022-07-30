package com.project.library.service.impl;

import com.project.library.common.Constants;
import com.project.library.model.Book;
import com.project.library.model.CallCardDetail;
import com.project.library.repository.CallCardDetailRepository;
import com.project.library.service.CallCardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CallCardDetailServiceImpl implements CallCardDetailService {
   @Autowired
   private CallCardDetailRepository callCardDetailRepository;

    @Override
    public List<CallCardDetail> getAll() {
        return callCardDetailRepository.findAll();
    }

    @Override
    public CallCardDetail get(Long id) {
        return callCardDetailRepository.findById(id).get();
    }

    @Override
    public Long getCountByBook(Book book) {
        return null;
    }

    @Override
    public CallCardDetail save(CallCardDetail callCardDetail) {
        return callCardDetailRepository.save(callCardDetail);
    }

    @Override
    public CallCardDetail addNew(CallCardDetail callCardDetail) {
        callCardDetail.setReturned(Constants.BOOK_NOT_RETURNED);
        return callCardDetailRepository.save(callCardDetail);
    }

    @Override
    public List<Object[]> totalBookPrice(Date startDate, Date endDate) {
        return callCardDetailRepository.totalBookPrice(startDate, endDate);
    }
}
