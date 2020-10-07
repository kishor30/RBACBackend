package com.BarnDoor.service;

import com.BarnDoor.model.Book;
import com.BarnDoor.model.ResponseDTO;
import com.BarnDoor.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseDTO getBooks(){
        List<Book> books =  bookRepository.findAll();
       return new ResponseDTO(HttpStatus.OK,"successfully fetched books",books);

    }

    public ResponseDTO deleteBook(Integer bookId){

        try{
            if(bookRepository.existsById(bookId)){
                bookRepository.deleteById(bookId);
                return new ResponseDTO(HttpStatus.OK,"Book deleted successfully.");
            }
            else {
                return new ResponseDTO(HttpStatus.NOT_FOUND,"Book with Id:"+ bookId );
            }
        }catch (Exception e){
            return null;
        }

    }


}
