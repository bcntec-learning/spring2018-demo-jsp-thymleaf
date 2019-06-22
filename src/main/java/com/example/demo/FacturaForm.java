package com.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@JsonSerialize
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaForm {
    private Cliente client;
    String id;
    Date date;

}
