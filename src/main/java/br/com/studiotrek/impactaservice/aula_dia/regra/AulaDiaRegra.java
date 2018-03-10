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

    private AulaDia aulaDia;
    private SemestreNota semestreNota;
    private List<Horario> horarios;

    public AulaDiaRegra(AulaDia aulaDia) {
        this.aulaDia = aulaDia;
        this.semestreNota = new SemestreNota();
        this.horarios = new ArrayList<>();
    }

    public AulaDia getAulaDia(String cookie) throws Exception {
        semestreNota = new SemestreNotaRegra(semestreNota).parseHtml(cookie);

        this.aulaDia.setNome(this.semestreNota.getNomeAluno());
        this.aulaDia.setCurso(this.semestreNota.getCurso());
        this.aulaDia.setRm(this.semestreNota.getRmAluno());
        this.aulaDia.setHorario(getHorario(cookie));

        return this.aulaDia;
    }

    private Horario getHorario(String cookie) throws Exception {
        Horario diaHorario = null;

        QuadroHorario quadroHorario = new QuadroHorario();
        QuadroHorarioRegra quadroHorarioRegra = new QuadroHorarioRegra(quadroHorario);
        quadroHorario = quadroHorarioRegra.parseHtml(cookie);
        this.horarios = new HorarioRegra(quadroHorario.getTurmaId(), quadroHorario.getProduto(), this.horarios).parseHtml(cookie);

        Date date = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.setTimeZone(TimeZone.getTimeZone("GMT-2"));

        String nome = "";
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
