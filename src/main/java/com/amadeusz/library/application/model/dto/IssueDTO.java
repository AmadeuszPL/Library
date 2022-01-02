package com.amadeusz.library.application.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class IssueDTO {

    private String bookIsbn;
    private UUID issuerId;

}
