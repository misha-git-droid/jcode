package controller;

import com.controller.BookController;
import com.dto.request.BookDtoRequest;
import com.dto.request.BookDtoWithoutIdRequest;
import com.dto.response.BookDtoResponse;
import com.dto.response.DtoResponse;
import com.exception.DatabaseException;
import com.exception.NotFoundException;
import com.repository.BookRepository;
import com.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    BookService bookService;
    @InjectMocks
    BookController bookController;

    @Test
    void insert_shouldReturnResponseEntityAndDtoResponse() {
        BookDtoWithoutIdRequest dto = new BookDtoWithoutIdRequest("title", "author", 2025);
        DtoResponse dtoResponse = new DtoResponse("Success");
        ResponseEntity<DtoResponse> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
        when(bookService.insert(dto)).thenReturn(dtoResponse);

        ResponseEntity<DtoResponse> receivedDtoResponse = bookController.insert(dto);

        assertEquals(receivedDtoResponse, expectedResponse);
        verify(bookService, times(1)).insert(dto);
    }

    @Test
    void insert_shouldThrowDatabaseException() {
        BookDtoWithoutIdRequest dto = new BookDtoWithoutIdRequest("title", "author", 2025);

        when(bookService.insert(dto)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookController.insert(dto));
        verify(bookService, times(1)).insert(dto);
    }

    @Test
    void bookById_shouldReturnResponseEntityAndBookDtoResponse() {
        Long id = 1L;
        BookDtoResponse dtoResponse = new BookDtoResponse(id, "title", "author", 2025);
        ResponseEntity<BookDtoResponse> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
        when(bookService.findById(id)).thenReturn(dtoResponse);

        ResponseEntity<BookDtoResponse> receivedDtoResponse = bookController.bookById(id);

        assertEquals(expectedResponse, receivedDtoResponse);
        verify(bookService, times(1)).findById(id);
    }

    @Test
    void bookById_shouldThrowDatabaseException() {
        Long id = 1L;
        when(bookService.findById(id)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookController.bookById(id));
        verify(bookService, times(1)).findById(id);
    }

    @Test
    void bookById_shouldThrowNotFoundException() {
        Long id = 1L;

        when(bookService.findById(id)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> bookController.bookById(id));
        verify(bookService, times(1)).findById(id);
    }

    @Test
    void update_shouldReturnResponseEntityAndDtoResponse() {
        Long id = 1L;
        BookDtoRequest bookDtoRequest = new BookDtoRequest(id, "NewTitle", "NewAuthor", 2024);
        DtoResponse dtoResponse = new DtoResponse("Success");
        ResponseEntity<DtoResponse> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
        when(bookService.update(bookDtoRequest)).thenReturn(dtoResponse);

        ResponseEntity<DtoResponse> receivedDtoResponse = bookController.update(bookDtoRequest);

        assertEquals(expectedResponse, receivedDtoResponse);
        verify(bookService, times(1)).update(bookDtoRequest);
    }

    @Test
    void update_shouldThrowDatabaseException() {
        Long id = 1L;
        BookDtoRequest bookDtoRequest = new BookDtoRequest(id, "NewTitle", "NewAuthor", 2024);
        when(bookService.update(bookDtoRequest)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookController.update(bookDtoRequest));
        verify(bookService, times(1)).update(bookDtoRequest);
    }

    @Test
    void update_shouldThrowNotFoundException() {
        Long id = 1L;
        BookDtoRequest bookDtoRequest = new BookDtoRequest(id, "NewTitle", "NewAuthor", 2024);
        when(bookService.update(bookDtoRequest)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> bookController.update(bookDtoRequest));
        verify(bookService, times(1)).update(bookDtoRequest);
    }

    @Test
    void delete_shouldReturnResponseEntityAndDtoResponse() {
        Long id = 1L;
        DtoResponse dtoResponse = new DtoResponse("Success");
        ResponseEntity<DtoResponse> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
        when(bookService.delete(id)).thenReturn(dtoResponse);

        ResponseEntity<DtoResponse> receivedDtoResponse = bookController.delete(id);

        assertEquals(expectedResponse, receivedDtoResponse);
        verify(bookService, times(1)).delete(id);
    }

    @Test
    void delete_shouldThrowDatabaseException() {
        Long id = 1L;
        when(bookService.delete(id)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookController.delete(id));
        verify(bookService, times(1)).delete(id);
    }

    @Test
    void delete_shouldThrowNotFoundException() {
        Long id = 1L;
        when(bookService.delete(id)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> bookController.delete(id));
        verify(bookService, times(1)).delete(id);
    }
}
