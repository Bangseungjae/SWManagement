package com.example.swmanagement.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpectFinalDate {
    private Integer year;
    private Integer month;
    private Integer day;
}
