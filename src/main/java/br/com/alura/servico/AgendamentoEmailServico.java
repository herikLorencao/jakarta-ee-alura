package br.com.alura.servico;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.entidade.AgendamentoEmail;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AgendamentoEmailServico {
    @Inject
    private AgendamentoEmailDAO dao;
    
    public List<AgendamentoEmail> listar() {
        return this.dao.listar();
    }
}
