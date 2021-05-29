package br.com.alura.job;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Singleton
// Valor abaixo já é padrão (não precisa inserir)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgendamentoEmailJob {

    @Inject
    private AgendamentoEmailServico agendamentoEmailServico;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/EmailQueue")
    private Queue queue;

    @Schedule(hour = "*", minute = "*", second = "*/10")
    // Valor abaixo já é padrão (não precisa inserir)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void enviarEmailsNaoAgendados() {
        List<AgendamentoEmail> emailsNaoAgendados = agendamentoEmailServico
                .listarEmailsNaoAgendados();
        emailsNaoAgendados.forEach(email -> {
            context.createProducer().send(queue, email);
            agendamentoEmailServico.atualizarEmailParaAgendado(email);
        });
    }
}
