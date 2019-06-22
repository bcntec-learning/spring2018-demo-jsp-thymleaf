package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    final Datos datos;

    public FacturaController(@Autowired Datos datos) {
        this.datos = datos;
    }

    @ResponseBody
    @RequestMapping(
            params = {"valor", "origen", "tipo", "cliente"},
            method = RequestMethod.PUT, value = "/{id}")
    public Factura add(@PathVariable("id") String id,
                       @RequestParam BigDecimal valor,
                       @RequestParam Tipo tipo,
                       @RequestParam Origen origen,
                       @RequestParam Cliente cliente) {

        datos.add(new Factura(id, cliente, tipo, origen, valor));
        return datos.findFacturaById(id).get();
    }


    @ResponseBody
    @RequestMapping(
            consumes = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT, value = "/")
    public Factura add(@RequestBody Factura factura) {

        return datos.add(factura);
    }


    @ResponseBody
    @RequestMapping("/{id}")
    public Factura findById(@PathVariable("id") String id) {
        return datos.findFacturaById(id).get();
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Factura add(@PathVariable("id") String id) {

        return datos.deleteFacturaById(id).get();
    }


    @RequestMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView findByIdJSP(@PathVariable("id") String id) {
        Factura f = datos.findFacturaById(id).get();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("factura", f);
        modelAndView.setViewName("factura-jsp");
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public String findByIdPDF(@PathVariable("id") String id, Model model) {
        Factura f = datos.findFacturaById(id).get();
        model.addAttribute("factura", f);
        return  "factura-pdf";
    }



    @ModelAttribute("facturaForm")
    FacturaForm getFacturaForm() {
        return new FacturaForm();
    }

    @GetMapping(value = "/",  produces = MediaType.TEXT_HTML_VALUE)
    public String form(
                       @ModelAttribute("facturaForm") FacturaForm clientesForm, ModelMap modelMap) {


        return "factura";
    }
}
