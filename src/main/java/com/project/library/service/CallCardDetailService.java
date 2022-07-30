package com.project.library.service;

import com.project.library.model.Book;
import com.project.library.model.CallCardDetail;

import java.util.Date;
import java.util.List;

public interface CallCardDetailService {
    public List<CallCardDetail> getAll();

    public CallCardDetail get(Long id);

    public Long getCountByBook(Book book);

    public CallCardDetail save(CallCardDetail callCardDetail);

    public CallCardDetail addNew(CallCardDetail callCardDetail);

    List<Object[]> totalBookPrice(Date startDate, Date endDate);
}
