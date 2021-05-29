package br.com.alura.mdb;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
            propertyName = "destinationLookup",
            propertyValue = "java:/jms/queue/EmailQueue"
    ),
    @ActivationConfigProperty(
            propertyName = "destinationType",
            propertyValue = "javax.jms.Queue"
    )
})
public class AgendamentoEmailMDB implements MessageListener {

    @Inject
    private AgendamentoEmailServico agendamentoEmailServico;

    @Override
    public void onMessage(Message msg) {
        try {
            AgendamentoEmail email = msg.getBody(AgendamentoEmail.class);
            agendamentoEmailServico.enviarEmail(email);
        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }
    }

}
