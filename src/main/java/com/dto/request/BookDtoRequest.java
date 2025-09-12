package com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoRequest {
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
}
