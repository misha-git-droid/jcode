package com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDtoWithoutIdRequest {
    private String title;
    private String author;
    private Integer publicationYear;
}
