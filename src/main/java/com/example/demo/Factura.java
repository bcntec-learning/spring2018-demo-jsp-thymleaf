package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    String id;
    Cliente client;
    Tipo tipo;
    Origen origen;
    BigDecimal valor;
}
