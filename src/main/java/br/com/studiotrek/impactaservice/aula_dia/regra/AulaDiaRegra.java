package br.com.studiotrek.impactaservice.aula_dia.regra;

import br.com.studiotrek.impactaservice.aula_dia.model.AulaDia;
import br.com.studiotrek.impactaservice.horario.model.Horario;
import br.com.studiotrek.impactaservice.horario.regra.HorarioRegra;
import br.com.studiotrek.impactaservice.quadro_horario.model.QuadroHorario;
import br.com.studiotrek.impactaservice.quadro_horario.regra.QuadroHorarioRegra;
import br.com.studiotrek.impactaservice.semestre_nota.model.SemestreNota;
import br.com.studiotrek.impactaservice.semestre_nota.regra.SemestreNotaRegra;

import java.util.*;

public class AulaDiaRegra {

    private String cookie;
    private AulaDia aulaDia;
    private SemestreNota semestreNota;
    private List<Horario> horarios;

    public AulaDiaRegra(String cookie) {
        this.cookie = cookie;
    }

    public AulaDiaRegra(String cookie, AulaDia aulaDia) {
        this.cookie = cookie;
        this.aulaDia = aulaDia;
        this.semestreNota = new SemestreNota();
        this.horarios = new ArrayList<>();
    }

    public AulaDia getAulaDia() throws Exception {
        semestreNota = new SemestreNotaRegra(this.cookie, this.semestreNota).parseHtml();

        this.aulaDia.setNome(this.semestreNota.getNomeAluno());
        this.aulaDia.setCurso(this.semestreNota.getCurso());
        this.aulaDia.setRm(this.semestreNota.getRmAluno());
        this.aulaDia.setHorario(getHorario());

        return this.aulaDia;
    }

    private Horario getHorario() throws Exception {
        Horario diaHorario = null;

        QuadroHorario quadroHorario = new QuadroHorario();
        QuadroHorarioRegra quadroHorarioRegra = new QuadroHorarioRegra(this.cookie, quadroHorario);
        quadroHorario = quadroHorarioRegra.parseHtml();
        this.horarios = new HorarioRegra(this.cookie, quadroHorario.getTurmaId(), quadroHorario.getProduto(), this.horarios).parseHtml();

        TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
        TimeZone.setDefault(tz);

        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz);

        int dia = c.get(c.DAY_OF_WEEK);

        switch (dia) {
            case Calendar.SUNDAY:
                break;
            case Calendar.MONDAY:
                diaHorario = this.horarios.get(0);
                break;
            case Calendar.TUESDAY:
                diaHorario = this.horarios.get(1);
                break;
            case Calendar.WEDNESDAY:
                diaHorario = this.horarios.get(2);
                break;
            case Calendar.THURSDAY:
                diaHorario = this.horarios.get(3);
                break;
            case Calendar.FRIDAY:
                diaHorario = this.horarios.get(4);
                break;
            case Calendar.SATURDAY:
                break;
            default:
                break;
        }

        return diaHorario;
    }

}
