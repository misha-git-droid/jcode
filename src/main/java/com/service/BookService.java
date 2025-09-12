package com.service;

import com.dto.request.BookDtoRequest;
import com.dto.request.BookDtoWithoutIdRequest;
import com.dto.response.BookDtoResponse;
import com.dto.response.DtoResponse;
import com.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public DtoResponse insert(BookDtoWithoutIdRequest dto) { return bookRepository.insert(dto); }
    public DtoResponse update(BookDtoRequest dto) { return bookRepository.update(dto); }
    public DtoResponse delete(Long id) { return bookRepository.delete(id); }
    public BookDtoResponse findById(Long id) {
        return bookRepository.findById(id);
    }
}
