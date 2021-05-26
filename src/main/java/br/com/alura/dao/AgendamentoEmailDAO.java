package br.com.alura.dao;

import br.com.alura.entidade.AgendamentoEmail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class AgendamentoEmailDAO {
    private EntityManager entityManager;
    
    public List<AgendamentoEmail> listar() {
        return entityManager.createQuery("SELECT ae FROM agendamentoemail",
                AgendamentoEmail.class).getResultList();
    }
}
