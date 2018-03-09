package br.com.studiotrek.impactaservice.nota_falta.regra;

import br.com.studiotrek.impactaservice.access_error.regra.AccessErrorRegra;
import br.com.studiotrek.impactaservice.base.regra.IRegra;
import br.com.studiotrek.impactaservice.nota_falta.model.Nota;
import br.com.studiotrek.impactaservice.nota_falta.model.NotaFalta;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.util.Const;
import br.com.studiotrek.impactaservice.util.SerializerUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotaFaltaRegra implements Serializable, IRegra<NotaFalta> {

    private String url;
    private NotaFalta notaFalta;
    private Request request;

    public NotaFaltaRegra(String url, NotaFalta notaFalta) {
        this.url = Const.URL_NOTA_FALTA + url;
        this.notaFalta = notaFalta;
        this.request = new Request();
    }

    @Override
    public NotaFalta parseHtml(String cookie) throws IllegalAccessException, Exception {
        String html = "";

        try {
            html = this.request.post(this.url, cookie);
            Document doc = Jsoup.parse(html);

            Element contentsBulletin = doc.select("div.well-fit-default").first().select("div#div_bulletin").first();
            Elements dataBoletim = contentsBulletin.select("tr");

            List<Nota> notas = new ArrayList<>();
            for (Element element : dataBoletim) {
                if(!element.attr("data-boletim").equals("")
                        && !element.select("td.table-left").text().equals("")) {
                    Nota nota = new Nota();
                    nota.setNomeMateria(element.select("td.table-left").text());
                    nota.setFalta(SerializerUtils.JsonStringToObject(element.attr("data-boletim"), HashMap.class));

                    notas.add(nota);
                }
            }
            this.notaFalta.setNotas(notas);
            return this.notaFalta;
        } catch (Exception ex) {
            try {
                new AccessErrorRegra().parseHtml(html);
                throw new IllegalAccessException();
            } catch (IllegalAccessException iaex) {
                throw iaex;
            } catch (Exception ex1) {
                throw ex;
            }
        }
    }

}
