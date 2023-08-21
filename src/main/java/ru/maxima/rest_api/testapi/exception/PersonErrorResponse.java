package ru.maxima.rest_api.testapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PersonErrorResponse {
    private String msg;
    private Date dateOfError;
}
