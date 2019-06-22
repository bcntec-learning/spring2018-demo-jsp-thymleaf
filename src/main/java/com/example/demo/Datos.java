package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;


@Component
@ApplicationScope
public class Datos {
    private Map<String, Factura> facturas = new HashMap<>();
    private Map<Long, Cliente> clients = new HashMap<>();


    @PostConstruct
    public void load() {
        for (long i = 1; i < 10; i++) {
            add(
                    new Cliente(i, "Cliente " + i));
        }


    }

    public Optional<Cliente> findClienteById(Long id) {
        return Optional.ofNullable(clients.get(id));
    }

    public Optional<Cliente> deleteClienteById(Long id) {
        return Optional.ofNullable(clients.remove(id));
    }


    public Cliente add(Cliente cliente) {
        return clients.put(cliente.getId(), cliente);
    }

    public Optional<Cliente> delete(Cliente cliente) {
        return Optional.ofNullable(clients.remove(cliente.getId()));
    }


    public List<Cliente> listByQuery(String q) {
        return isEmpty(q) ? new ArrayList<>(clients.values()) :

                clients.values().stream().filter(a -> a.getName().contains(q)).collect(Collectors.toList());
    }


    //

    public Optional<Factura> findFacturaById(String id) {
        return Optional.ofNullable(facturas.get(id));
    }

    public Factura add(Factura factura) {
        return facturas.put(factura.getId(), factura);
    }

    public Optional<Factura> delete(Factura factura) {
        return Optional.ofNullable(facturas.remove(factura.getId()));
    }

    public Optional<Factura> deleteFacturaById(String id) {
        return Optional.ofNullable(facturas.remove(id));
    }

    public List<Cliente> listClients() {
        return new ArrayList(clients.values());

    }
}
