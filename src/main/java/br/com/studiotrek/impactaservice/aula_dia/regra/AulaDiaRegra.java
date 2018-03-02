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
        String nome = "";
        int dia = c.get(c.DAY_OF_WEEK);

        for (Horario horario : horarios) {
            switch (dia) {
                case Calendar.SUNDAY:
                    break;
                case Calendar.MONDAY:
                    horario.getDia().equals("Segunda");
                    diaHorario = horario;
                    break;
                case Calendar.TUESDAY:
                    horario.getDia().equals("Terça");
                    diaHorario = horario;
                    break;
                case Calendar.WEDNESDAY:
                    horario.getDia().equals("Quarta");
                    diaHorario = horario;
                    break;
                case Calendar.THURSDAY:
                    horario.getDia().equals("Quinta");
                    diaHorario = horario;
                    break;
                case Calendar.FRIDAY:
                    horario.getDia().equals("Sexta");
                    diaHorario = horario;
                    break;
                case Calendar.SATURDAY:
                    break;
            }
        }

        return diaHorario;
    }

}
