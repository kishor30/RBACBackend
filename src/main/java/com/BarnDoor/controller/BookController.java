package com.BarnDoor.controller;


import com.BarnDoor.model.ResponseDTO;
import com.BarnDoor.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseDTO getBooks(){
        ResponseDTO response = bookService.getBooks();
        return response;
    }

    @DeleteMapping("/books/remove/{bookId}")
    public ResponseDTO deleteBook(@PathVariable(value = "bookId") Integer bookId ){
        ResponseDTO responseDTO = bookService.deleteBook(bookId);
        return responseDTO;
    }
}
