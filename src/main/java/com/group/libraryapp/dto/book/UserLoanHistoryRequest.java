package com.group.libraryapp.dto.book;

public class UserLoanHistoryRequest {

    private String userName;
    private String bookName;
    private boolean isUserReturn;

    public String getUserName() { return userName;}
    public String getBookName() {
        return bookName;
    }
    public boolean isUserReturn() {
        return isUserReturn;
    }
}
