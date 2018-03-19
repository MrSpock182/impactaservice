package br.com.studiotrek.impactaservice.quadro_horario.regra;

import br.com.studiotrek.impactaservice.access_error.regra.AccessErrorRegra;
import br.com.studiotrek.impactaservice.base.regra.IRegra;
import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.util.Const;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.Serializable;

public class QuadroHorarioRegra implements Serializable, IRegra<QuadroHorario> {

    private String cookie;
    private QuadroHorario quadroHorario;
    private Request request;

    public QuadroHorarioRegra(String cookie, QuadroHorario quadroHorario) {
        this.cookie = cookie;
        this.quadroHorario = quadroHorario;
        this.request = new Request();
    }

    @Override
    public QuadroHorario parseHtml() throws Exception {
        String html = "";
        try {
            html = this.request.post(Const.URL_QUADRO_HORARIO, cookie);

            Document doc = Jsoup.parse(html);
            Element wellFitDefault = doc.select("div.well-fit-default").first();
            Element rowFluid = wellFitDefault.select("div.row-fluid").first();
            Element tbody = rowFluid.select("tbody").first();
            Element tr = tbody.select("tr").first();

            this.quadroHorario.setTurmaId(tr.attr("data-turmaid"));
            this.quadroHorario.setProduto(tr.attr("data-produto"));

            return this.quadroHorario;
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
