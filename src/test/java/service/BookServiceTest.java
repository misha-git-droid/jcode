package service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void insert_shouldReturnDtoResponse() {
        BookDtoWithoutIdRequest dto = new BookDtoWithoutIdRequest("title", "author", 2025);
        DtoResponse expectedDtoResponse = new DtoResponse("Success");
        when(bookRepository.insert(dto)).thenReturn(expectedDtoResponse);

        DtoResponse receivedDtoResponse = bookService.insert(dto);

        assertEquals(expectedDtoResponse, receivedDtoResponse);
        verify(bookRepository, times(1)).insert(dto);
    }

    @Test
    void insert_whenDatabaseAccessException_shouldThrowDatabaseException() {
        BookDtoWithoutIdRequest dto = new BookDtoWithoutIdRequest("title", "author", 2025);
        when(bookRepository.insert(dto)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookService.insert(dto));
        verify(bookRepository, times(1)).insert(dto);
    }

    @Test
    void update_whenBookExists_shouldWillReturnDtoResponse() {
        Long id = 1L;
        BookDtoRequest dto = new BookDtoRequest(id, "title", "author", 2025);
        DtoResponse expectedDtoResponse = new DtoResponse("Success");
        when(bookRepository.update(dto)).thenReturn(expectedDtoResponse);

        DtoResponse receivedDtoResponse = bookService.update(dto);

        assertEquals(expectedDtoResponse.getMessage(), receivedDtoResponse.getMessage());
        verify(bookRepository, times(1)).update(dto);
    }

    @Test
    void update_whenBookNotExists_shouldThrowNotFoundException() {
        Long id = 1L;
        BookDtoRequest dto = new BookDtoRequest(id, "title", "author", 2025);
        when(bookRepository.update(dto)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> bookService.update(dto));
        verify(bookRepository, times(1)).update(dto);
    }

    @Test
    void update_whenDatabaseAccessException_shouldThrowDatabaseException() {
        Long id = 1L;
        BookDtoRequest dto = new BookDtoRequest(id, "title", "author", 2025);
        when(bookRepository.update(dto)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookService.update(dto));
        verify(bookRepository, times(1)).update(dto);
    }

    @Test
    void findById_whenBookFound_shouldReturnBookDtoResponse() {
        Long id = 1L;
        BookDtoResponse expectedDto = new BookDtoResponse(id, "title",  "author", 2025);
        when(bookRepository.findById(id)).thenReturn(expectedDto);

        BookDtoResponse receivedDto = bookService.findById(id);

        assertEquals(expectedDto.getId(), receivedDto.getId());
        assertEquals(expectedDto.getTitle(), receivedDto.getTitle());
        assertEquals(expectedDto.getAuthor(), receivedDto.getAuthor());
        assertEquals(expectedDto.getPublicationYear(), receivedDto.getPublicationYear());
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void findById_whenBookNotFound_shouldThrowNotFoundException() {
        Long id = 1L;
        when(bookRepository.findById(id)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> bookService.findById(id));
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void findById_whenDatabaseAccessException_shouldThrowDatabaseException() {
        Long id = 1L;
        when(bookRepository.findById(id)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookService.findById(id));
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void delete_whenBookFound_shouldReturnDtoResponse() {
        Long id = 1L;
        DtoResponse expectedDto = new DtoResponse("Success");
        when(bookRepository.delete(id)).thenReturn(expectedDto);

        DtoResponse receivedDto = bookService.delete(id);

        assertEquals(expectedDto, receivedDto);
        verify(bookRepository, times(1)).delete(id);
    }

    @Test
    void delete_whenBookNotFound_shouldThrowNotFoundException() {
        Long id = 1L;
        when(bookRepository.delete(id)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> bookService.delete(id));
        verify(bookRepository, times(1)).delete(id);
    }

    @Test
    void delete_whenDatabaseAccessException_shouldThrowDatabaseException() {
        Long id = 1L;
        when(bookRepository.delete(id)).thenThrow(DatabaseException.class);

        assertThrows(DatabaseException.class, () -> bookService.delete(id));
        verify(bookRepository, times(1)).delete(id);
    }
}
