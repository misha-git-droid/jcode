package com.controller;

import com.dto.request.BookDtoRequest;
import com.dto.request.BookDtoWithoutIdRequest;
import com.dto.response.BookDtoResponse;
import com.dto.response.DtoResponse;
import com.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<DtoResponse> insert(@RequestBody BookDtoWithoutIdRequest dto) {
            DtoResponse dtoResponse = bookService.insert(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDtoResponse> bookById(@PathVariable("id") Long id) {
            BookDtoResponse dto = bookService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping()
    public ResponseEntity<DtoResponse> update(@RequestBody BookDtoRequest dto) {
        DtoResponse dtoResponse = bookService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoResponse> delete(@PathVariable("id") Long id) {
        DtoResponse dtoResponse = bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
    }
}
