package br.com.alura.servico;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.entidade.AgendamentoEmail;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AgendamentoEmailServico {
    
    @Inject
    private AgendamentoEmailDAO dao;
    
    private static final Logger LOGGER = Logger.getLogger(AgendamentoEmail.class.getName());
    
    public List<AgendamentoEmail> listar() {
        return this.dao.listar();
    }
    
    public void inserir(AgendamentoEmail agendamentoEmail) {
        agendamentoEmail.setAgendado(Boolean.FALSE);
        this.dao.inserir(agendamentoEmail);
    }
    
    public List<AgendamentoEmail> listarEmailsNaoAgendados() {
        return this.dao.listarEmailsNaoAgendados();
    }
    
    public void atualizarEmailParaAgendado(AgendamentoEmail agendamentoEmail) {
        agendamentoEmail.setAgendado(Boolean.TRUE);
        this.dao.atualizar(agendamentoEmail);
    }
    
    public void enviarEmail(AgendamentoEmail agendamentoEmail) {
        try {
            Thread.sleep(5000);
            LOGGER.info("Email: " + agendamentoEmail.getEmail() + " enviado!");
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
