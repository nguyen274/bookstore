package com.project.library.controller;

import com.project.library.service.CallCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Controller
public class StatsController {
    @Autowired
    private CallCardService callCardService;

    @RequestMapping(value = "/turnover-of-month", method = RequestMethod.GET)
    public String turnoverOfMonth(Model model, @RequestParam Map<String, String> payload) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate nowTime = LocalDate.now();

        Date startDate = null;
        String start = payload.get("startDate");
        if (start != null)
            startDate = f.parse(start);

        Date endDate = null;
        String end = payload.get("endDate");
        if (end != null)
            endDate = f.parse(end);

        model.addAttribute("bookStats", this.callCardService.totalTurnoverOfMonth(startDate, endDate));
        return "/stats/turnover-month";
    }
}
