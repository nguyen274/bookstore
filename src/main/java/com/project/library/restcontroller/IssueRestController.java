package com.project.library.restcontroller;

import com.project.library.common.Constants;
import com.project.library.model.Book;
import com.project.library.model.CallCard;
import com.project.library.model.CallCardDetail;
import com.project.library.model.LibraryCard;
import com.project.library.service.BookService;
import com.project.library.service.CallCardDetailService;
import com.project.library.service.CallCardService;
import com.project.library.service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/rest/issue")
public class IssueRestController {
    @Autowired
    private LibraryCardService libraryCardService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CallCardService callCardService;

    @Autowired
    private CallCardDetailService callCardDetailService;

    @GetMapping("/getBook/{bookCode}")
    public Book getBookByIdBook(@PathVariable String bookCode) {
        return bookService.getBookByBookCode(bookCode);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "book/form";
    }

    @RequestMapping(value = "/save-fines-price", method = RequestMethod.GET)
    public String saveFinesPrice(@RequestParam Map<String, String> payload) {
        Long id = Long.parseLong(payload.get("id"));
        Float total = Float.parseFloat(payload.get("total"));

        CallCard callCard = callCardService.get(id);
        callCard.setTotalFines(total);
        callCardService.save(callCard);

        return "success";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(@RequestParam Map<String, String> payload) {
        String menberIdStr = (String) payload.get("member");
        Float total = Float.parseFloat(payload.get("total"));
        Float totalDepositPrice = Float.parseFloat(payload.get("totalDepositPrice"));
        String[] bookIdsStr = payload.get("books").toString().split(",");
        String[] amountByBook = payload.get("amount").toString().split(",");

        Long memberId = null;
        List<Long> bookIds = new ArrayList<Long>();
        try {
            memberId = Long.parseLong(menberIdStr);
            for (int k = 0; k < bookIdsStr.length; k++) {
                bookIds.add(Long.parseLong(bookIdsStr[k]));
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return "Invalid number format";
        }
        Calendar cal = Calendar.getInstance();
        System.out.println(cal);
        cal.add(Calendar.MONTH, 30); // th??m 4 th??ng

        Date date = cal.getTime();

        LibraryCard libraryCard = libraryCardService.get(memberId);
        List<Book> books = bookService.get(bookIds);
        CallCard callCard = new CallCard();
        callCard.setTotalDepositPrice(totalDepositPrice);
        callCard.setTotal(total);
        callCard.setExpectedReturnDate(date);
        callCard.setLibraryCard(libraryCard);
        callCard = callCardService.addNew(callCard);
        if (menberIdStr == "MEMBER_STUDENT") {
            callCard.setTotal(total - total * 5 / 100);
        }
        CallCardDetail ccd = new CallCardDetail();


        for (int k = 0; k < books.size(); k++) {

//            Book book = bookService.findBookById(bookIds.get(k)).orElseThrow(() -> new IllegalArgumentException("Empty"));
//            book.setAmount(book.getAmount() - Integer.parseInt(amountByBook[k]));
//            System.out.println(book.getAmount() - Integer.parseInt(amountByBook[k]));
//            bookService.saveBook(book);

            Book bookId = books.get(k);
            bookId.setAmount(bookId.getAmount() - Integer.parseInt(amountByBook[k]));
            bookId.setStatus(Constants.BOOK_STATUS_ISSUED);
            bookId = bookService.saveBook(bookId);
//            bookId.setAmount(Integer.valueOf(amountByBook[k]));
            CallCardDetail callCardDetail = new CallCardDetail();
            // amount ccd
            callCardDetail.setAmount(Integer.valueOf(amountByBook[k]));
            callCardDetail.setCallCard(callCard);
            callCardDetail.setBook(bookId);
            callCardDetailService.addNew(callCardDetail);
        }

        return "success";
    }

    @RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
    public String returnAll(@PathVariable(name = "id") Long id) {
        CallCard callCard = callCardService.get(id);
        if (callCard != null) {
            List<CallCardDetail> callCardDetails = callCard.getCallCardDetails();


            for (int k = 0; k < callCardDetails.size(); k++) {
                CallCardDetail ib = callCardDetails.get(k);
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                ib.setReturnDate(date);
                ib.setReturned(Constants.BOOK_RETURNED);
                callCardDetailService.save(ib);

                Book book = ib.getBook();
                book.setStatus(Constants.BOOK_STATUS_AVAILABLE);
                bookService.saveBook(book);
            }
            callCard.setReturned(Constants.BOOK_RETURNED);
            callCardService.save(callCard);
            return "successful";
        } else {
            return "unsuccessful";
        }


    }

    @RequestMapping(value = "/{id}/pay", method = RequestMethod.GET)
    public String returnSelected(@RequestParam Map<String, String> payload, @PathVariable(name = "id") Long id) {
        CallCard callCard = callCardService.get(id);
        String[] issueBookIds = payload.get("ids").split(",");
        LocalDate return_date = LocalDate.now();

        if (callCard != null) {
            List<CallCardDetail> callCardDetails = callCard.getCallCardDetails();
            for (int k = 0; k < callCardDetails.size(); k++) {
                CallCardDetail ib = callCardDetails.get(k);
                if (Arrays.asList(issueBookIds).contains(ib.getId().toString())) {
                    long millis1 = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis1);
                    ib.setReturnDate(date);
                    ib.setReturned(Constants.BOOK_RETURNED);
                    callCardDetailService.save(ib);

                    Book book = ib.getBook();
                    book.setStatus(Constants.BOOK_STATUS_AVAILABLE);
                    bookService.saveBook(book);
                }
            }
            return "successful";
        } else {
            return "unsuccessful";
        }
    }
}
