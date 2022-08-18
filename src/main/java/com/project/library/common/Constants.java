package com.project.library.common;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_LIBRARIAN = "Librarian";

    public static final String MEMBER_CUSTOMER = "Customer";
    public static final String MEMBER_STUDENT = "Student";
	  /* public static final String MEMBER_OTHER = "Other"; */
    public static final List<String> MEMBER_TYPES = new ArrayList<String>() {{
        add(MEMBER_CUSTOMER);
        add(MEMBER_STUDENT);
        /* add(MEMBER_OTHER); */
    }};

    public static final Integer BOOK_STATUS_AVAILABLE = 1;
    public static final Integer BOOK_STATUS_ISSUED = 2;

    public static final Integer BOOK_NOT_RETURNED = 0;
    public static final Integer BOOK_RETURNED = 1;

    public static final Integer STUDENT_NOT_STATUS = 0;
    public static final Integer STUDENT_HAVE_STATUS = 1;

    public static final Integer CUSTOMER_NOT_STATUS = 0;
    public static final Integer CUSTOMER_STATUS = 1;


}
