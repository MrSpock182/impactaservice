package br.com.studiotrek.impactaservice.horario.regra;

import br.com.studiotrek.impactaservice.access_error.regra.AccessErrorRegra;
import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.model.HorarioDetalhado;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.util.Const;
import br.com.studiotrek.impactaservice.util.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.*;

public class HorarioRegra implements Serializable {

    @Autowired
    private Request request;

    private String cookie;
    private String turmaId;
    private String produto;
    private List<Horario> horarios;

    public HorarioRegra(String cookie, String turmaId, String produto, List<Horario> horarios) throws Exception {
        this.cookie = cookie;
        this.turmaId = turmaId;
        this.produto = produto;
        this.horarios = horarios;
    }

    public List<Horario> parseHtml() throws Exception {
        String html = "";

        try {
            this.request = Inject.getContext().getBean(Request.class);

            String url = String.format(Const.URL_HORARIO, this.turmaId, this.produto);
            html = this.request.post(url, this.cookie);
            Document doc = Jsoup.parse(html);

            Elements diaSemana = doc.select("div.dia-semana");
            for (Element element : diaSemana) {
                Horario horario = new Horario();
                horario.setDia(element.select("h2").text());
                Element test = element.select("div.dia-semana-content").first();
                List<String> list = new ArrayList<>();

                StringBuilder palavra = new StringBuilder();
                for (char ch : test.ownText().toCharArray()) {
                    if (ch != ':') {
                        palavra.append(ch);
                    } else {
                        if (!palavra.toString().equalsIgnoreCase("")) {
                            list.add(palavra.toString().trim());
                            palavra = new StringBuilder();
                        }
                    }
                }
                list.add(palavra.toString().trim());

                Set<HorarioDetalhado> horarioDetalhados = new HashSet<>();

                for (int i = 0; i < list.size(); i += 3) {
                    HorarioDetalhado horarioDetalhado = new HorarioDetalhado();
                    horarioDetalhado.setDisciplina(list.get(i));
                    horarioDetalhado.setProfessor(list.get(i + 1));
                    horarioDetalhado.setSala(list.get(i + 2));
                    horarioDetalhados.add(horarioDetalhado);
                }

                List<HorarioDetalhado> listaHorarioDetalhados = new ArrayList<>();
                listaHorarioDetalhados.addAll(horarioDetalhados);
                Collections.reverse(listaHorarioDetalhados);

                horario.setHorarioDetalhado(listaHorarioDetalhados);

                this.horarios.add(horario);
            }

            return this.horarios;
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
