package br.com.studiotrek.impactaservice.quadro_horario.regra;

import br.com.studiotrek.impactaservice.base.regra.IRegra;
import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.util.Const;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.Serializable;

public class QuadroHorarioRegra implements Serializable, IRegra<QuadroHorario> {

    private QuadroHorario quadroHorario;
    private Request request;

    public QuadroHorarioRegra(QuadroHorario quadroHorario) {
        this.quadroHorario = quadroHorario;
        this.request = new Request();
    }

    @Override
    public QuadroHorario parseHtml(String cookie) throws Exception {
        String html = this.request.post(Const.URL_QUADRO_HORARIO, cookie);

        Document doc = Jsoup.parse(html);
        Element wellFitDefault = doc.select("div.well-fit-default").first();
        Element rowFluid = wellFitDefault.select("div.row-fluid").first();
        Element tbody = rowFluid.select("tbody").first();
        Element tr = tbody.select("tr").first();

        this.quadroHorario.setTurmaId(tr.attr("data-turmaid"));
        this.quadroHorario.setProduto(tr.attr("data-produto"));

        return this.quadroHorario;
    }

}
