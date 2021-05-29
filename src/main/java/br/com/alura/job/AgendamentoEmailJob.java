package br.com.alura.job;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class AgendamentoEmailJob {

    @Inject
    private AgendamentoEmailServico agendamentoEmailServico;

    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void enviarEmailsNaoAgendados() {
        List<AgendamentoEmail> emailsNaoAgendados = agendamentoEmailServico
                .listarEmailsNaoAgendados();
        emailsNaoAgendados.forEach(email -> {
            agendamentoEmailServico.enviarEmail(email);
            agendamentoEmailServico.atualizarEmailParaAgendado(email);
        });
    }
}
