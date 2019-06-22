package com.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@JsonSerialize
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesForm {
    private String q;
    private Integer p;
    private Integer s;
    List<Cliente> data;
    List<String> headers = Arrays.asList("Id","Name");

    public void search(Datos datos) {
        data=datos.listByQuery(getQ());
    }

    public void search(Datos datos, Integer p, Integer s) {
        setP(p);
        setS(s);
        data=datos.listByQuery(getQ()).subList((p-1)*s, (((p-1)*s)+s)-1);

    }
}
