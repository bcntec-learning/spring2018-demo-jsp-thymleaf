package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    final Datos datos;

    public ClienteController(@Autowired Datos datos) {
        this.datos = datos;
    }

    @GetMapping(value = "/{id}",  produces = MediaType.TEXT_HTML_VALUE)
    public String line(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("item", datos.findClienteById(id).get());
        return "clientes :: item";
    }

    @GetMapping(value = "/page",  produces = MediaType.TEXT_HTML_VALUE)
    public String page(@RequestParam(required = false) String q,
                       @RequestParam(required = false, defaultValue = "1") Integer p,
                       @RequestParam(required = false, defaultValue = "5") Integer s,
                       @ModelAttribute("clientesForm") ClientesForm clientesForm, ModelMap modelMap) {
        if (q != null) {
            clientesForm.setQ(q);
        }
        clientesForm.search(datos, p, s);

        return "clientes :: page";
    }


    @GetMapping()
    public String list(@ModelAttribute("clientesForm") ClientesForm clientesForm, ModelMap modelMap) {

        //clientesForm.search(datos);

        return "clientes";
    }


    @PostMapping(value = "/page",  produces = MediaType.TEXT_HTML_VALUE)
    public String pagePost(@RequestParam(required = false) String q,
                       @RequestParam(required = false, defaultValue = "1") Integer p,
                       @RequestParam(required = false, defaultValue = "5") Integer s,
                       @ModelAttribute("clientesForm") ClientesForm clientesForm, ModelMap modelMap) {
        if (q != null) {
            clientesForm.setQ(q);
        }
        clientesForm.search(datos, p, s);

        return "clientes :: page";
    }

    @PostMapping()
    public String list(@Valid @ModelAttribute("clientesForm") ClientesForm clientesForm) {

        clientesForm.search(datos);

        return "clientes";
    }

    @ModelAttribute("clientesForm")
    ClientesForm getClientesForm() {
        return new ClientesForm();
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public Cliente add(@PathVariable("id") Long id, @RequestParam String name) {

        return datos.add(new Cliente(id, name));
    }

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.PUT, value = "/")
    public Cliente add(@RequestBody Cliente cliente) {

        return datos.add(cliente);
    }



    @ResponseBody
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Cliente findById(@PathVariable("id") Long id) {
        return datos.findClienteById(id).get();
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Cliente add(@PathVariable("id") Long id) {

        return datos.deleteClienteById(id).get();
    }


    @ResponseBody
    @GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    public List<Cliente> list() {

        return datos.listClients();
    }

}
