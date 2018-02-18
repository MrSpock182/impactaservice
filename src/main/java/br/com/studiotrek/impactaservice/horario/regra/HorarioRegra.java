package br.com.studiotrek.impactaservice.horario.regra;

import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.model.HorarioDetalhado;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.util.Const;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HorarioRegra implements Serializable {

    private String turmaId;
    private String produto;
    private List<Horario> horarios;
    private Request request;

    public HorarioRegra(String turmaId, String produto, List<Horario> horarios) {
        this.turmaId = turmaId;
        this.produto = produto;
        this.horarios = horarios;
        this.request = new Request();
        //turmaid=MDE2TVRVeE9EY3lNRGN5TlE9PU16Z3hOQT09&produto=MDE2TVRVeE9EY3lNRGN5TlE9PU5UYz0=
    }

    public List<Horario> parseHtml(String cookie) throws Exception {
        String url = String.format(Const.URL_HORARIO, this.turmaId, this.produto);
        String html = this.request.post(url, cookie);
        Document doc = Jsoup.parse(html);

        Elements diaSemana = doc.select("div.dia-semana");
        for (Element element : diaSemana) {
            Horario horario = new Horario();
            horario.setDia(element.select("h2").text());
            Element test = element.select("div.dia-semana-content").first();
            List<String> list = new ArrayList<>();

            StringBuilder palavra = new StringBuilder();
            for (char ch: test.ownText().toCharArray()) {
                if(ch != ':') {
                    palavra.append(ch);
                } else {
                    if(!palavra.toString().equalsIgnoreCase("")) {
                        list.add(palavra.toString());
                        palavra = new StringBuilder();
                    }
                }
            }
            list.add(palavra.toString());

            List<HorarioDetalhado> horarioDetalhados = new ArrayList<>();
            for (int i = 0; i < list.size(); i+=3) {
                HorarioDetalhado horarioDetalhado = new HorarioDetalhado();
                horarioDetalhado.setDisciplina(list.get(i));
                horarioDetalhado.setProfessor(list.get(i + 1));
                horarioDetalhado.setSala(list.get(i + 2));
                horarioDetalhados.add(horarioDetalhado);
            }
            horario.setHorarioDetalhado(horarioDetalhados);
            this.horarios.add(horario);
        }

        return this.horarios;
    }

}
