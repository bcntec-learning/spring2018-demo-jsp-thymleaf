package com.example.demo;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class FacturaPDF extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        Factura f = (Factura) model.get("factura");
        Table table = new Table(2);
        table.addCell("Nro");
        table.addCell(f.getId());

        document.add(table);
    }

    public void setNada(String nada){
        log.info("nada->"+nada);
    }
}
