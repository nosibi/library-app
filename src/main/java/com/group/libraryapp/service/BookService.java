package com.group.libraryapp.service;

import com.group.libraryapp.domain.user.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserLoanHistory;
import com.group.libraryapp.dto.book.SaveBookRequest;
import com.group.libraryapp.dto.book.UserLoanHistoryRequest;
import com.group.libraryapp.dto.book.UserReturnHistoryRequest;
import com.group.libraryapp.repository.BookRepository;
import com.group.libraryapp.repository.UserLoanHistoryRepository;
import com.group.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(SaveBookRequest request){
        bookRepository.save((new Book(request.getName())));
    }

    @Transactional
    public void loanBook(UserLoanHistoryRequest request){
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("대출중인 책입니다");}

        User user = userRepository.findByName(request.getUserName());
        if(user == null){
            throw new IllegalArgumentException();
        }
        user.loanBook(book.getName());

        /*
        User user = userRepository.findByName(request.getUserName());
        if(user == null){throw new IllegalArgumentException();}
        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(),book.getName()));
        */
    }

    @Transactional
    public void returnBook(UserReturnHistoryRequest request){
        User user = userRepository.findByName(request.getUserName());
        if(user == null){
            throw new IllegalArgumentException();
        }
        user.returnBook(request.getBookName());


        /* UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        userLoanHistory.doReturn();*/
    }
}
