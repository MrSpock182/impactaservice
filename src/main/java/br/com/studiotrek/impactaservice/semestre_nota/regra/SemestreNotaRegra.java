package br.com.studiotrek.impactaservice.semestre_nota.regra;

import br.com.studiotrek.impactaservice.access_error.regra.AccessErrorRegra;
import br.com.studiotrek.impactaservice.base.regra.IRegra;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.semestre_nota.model.Semestre;
import br.com.studiotrek.impactaservice.semestre_nota.model.SemestreNota;
import br.com.studiotrek.impactaservice.util.Const;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SemestreNotaRegra implements Serializable, IRegra<SemestreNota> {

    private String cookie;
    private SemestreNota semestreNota;
    private Request request;

    public SemestreNotaRegra(String cookie, SemestreNota semestreNota) {
        this.cookie = cookie;
        this.semestreNota = semestreNota;
        this.request = new Request();
    }

    @Override
    public SemestreNota parseHtml() throws Exception {
        String html = "";

        try {
            html = this.request.post(Const.URL_SEMESTRE_NOTA, this.cookie);
            Document document = Jsoup.parse(html);
            Element rowFluid = document.select("div.row-fluid").get(1);
            Elements rowElements = rowFluid.select("strong");

            semestreNota.setNomeAluno(rowElements.get(0).text());
            semestreNota.setRmAluno(rowElements.get(1).text());
            semestreNota.setCurso(rowElements.get(2).text());

            Element wellFitDefault = document.select("div.well-fit-default").first();
            Elements elements = wellFitDefault.select("tbody").select("tr");

            List<Semestre> semestres = new ArrayList<>();
            for (Element element : elements) {
                Semestre semestre = new Semestre();

                semestre.setFaculdade(element.select("td").get(0).text());
                semestre.setAnoSemestre(element.select("td").get(1).text());
                semestre.setCurso(element.select("td").get(2).text());
                semestre.setHorario(element.select("td").get(3).text());
                semestre.setSemestre(element.select("td").get(4).text());
                semestre.setStatus(element.select("td").get(5).text());
                semestre.setUrlBoletim(element.select("td").select("a").attr("href"));

                semestres.add(semestre);
            }
            this.semestreNota.setSemestres(semestres);
            return this.semestreNota;
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

    public Semestre getLastSemester() throws Exception {
        SemestreNota semestreNota = parseHtml();

        for (Semestre semester : semestreNota.getSemestres()) {
            return semester;
        }

        throw new Exception("none semester found");
    }

}
