package com.repository;

import com.dto.request.BookDtoRequest;
import com.dto.request.BookDtoWithoutIdRequest;
import com.dto.response.BookDtoResponse;
import com.dto.response.DtoResponse;
import com.exception.DatabaseException;
import com.exception.NotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<BookDtoResponse> mapper = new RowMapper<BookDtoResponse>() {
        @Override
        public BookDtoResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookDtoResponse dto = new BookDtoResponse();
            dto.setId(rs.getLong(1));
            dto.setTitle(rs.getString(2));
            dto.setAuthor(rs.getString(3));
            dto.setPublicationYear(rs.getInt(4));
            return dto;
        }
    };

    @Transactional
    public DtoResponse insert(BookDtoWithoutIdRequest dto) {
        try {
            String sql = "INSERT INTO books (title, author, publication_year) VALUES (?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
                ps.setString(1, dto.getTitle());
                ps.setString(2, dto.getAuthor());
                ps.setInt(3, dto.getPublicationYear());
                return ps;
            }, keyHolder);
            return new DtoResponse("The book has been successfully created");
        }  catch (DataAccessException exception) {
            throw new DatabaseException("Database error");
        }
    }

    @Transactional
    public DtoResponse update(BookDtoRequest dto) {
         try {
             String sql = "UPDATE books SET title = ?, author = ?, publication_year = ? WHERE id = ?";

             int changedLines = jdbcTemplate.update(sql, dto.getTitle(), dto.getAuthor(), dto.getPublicationYear(), dto.getId());
             if (changedLines == 0) throw new NotFoundException("Book with id - " + dto.getId() + " not found");

             return new DtoResponse("The book has been successfully updated");
         } catch (DataAccessException exception) {
             throw new DatabaseException("Database error");
         }
    }

    @Transactional
    public DtoResponse delete(Long id) {
        try {
            String sql = "DELETE FROM books WHERE id = ?";
            int changedLines = jdbcTemplate.update(sql, id);
            if (changedLines == 0) throw new NotFoundException("Book with id - " + id + " not found");

            return new DtoResponse("The book has been successfully deleted");
        } catch (DataAccessException exception) {
            throw new DatabaseException("Database error");
        }
    }

    @Transactional
    public BookDtoResponse findById(Long id) {
        try {
            String sql = "SELECT * FROM books WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, mapper, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Book with id - " + id + " not found");
        }
    }
}
