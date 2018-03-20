package br.com.studiotrek.impactaservice.horario.regra;

import br.com.studiotrek.impactaservice.access_error.regra.AccessErrorRegra;
import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.model.HorarioDetalhado;
import br.com.studiotrek.impactaservice.nota_falta.model.Nota;
import br.com.studiotrek.impactaservice.nota_falta.model.NotaFalta;
import br.com.studiotrek.impactaservice.nota_falta.regra.NotaFaltaRegra;
import br.com.studiotrek.impactaservice.request_impacta.Request;
import br.com.studiotrek.impactaservice.semestre_nota.model.Semestre;
import br.com.studiotrek.impactaservice.semestre_nota.model.SemestreNota;
import br.com.studiotrek.impactaservice.semestre_nota.regra.SemestreNotaRegra;
import br.com.studiotrek.impactaservice.util.Const;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.*;

public class HorarioRegra implements Serializable {

    private Request request;
    private String cookie;
    private String turmaId;
    private String produto;
    private List<Horario> horarios;

    public HorarioRegra(String cookie) throws Exception {
        this.cookie = cookie;
    }

    public HorarioRegra(String cookie, String turmaId, String produto, List<Horario> horarios) throws Exception {
        this.cookie = cookie;
        this.turmaId = turmaId;
        this.produto = produto;
        this.horarios = horarios;
        this.request = new Request();
    }

    public List<Horario> parseHtml() throws Exception {
        String html = "";

        try {
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

                horario.setHorarioDetalhado(horarioDetalhados);
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
