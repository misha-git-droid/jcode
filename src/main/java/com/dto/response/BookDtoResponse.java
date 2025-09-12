package com.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoResponse {
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
}
