package br.com.alura.dao;

import br.com.alura.entidade.AgendamentoEmail;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AgendamentoEmailDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private UserTransaction userTransaction;

    public List<AgendamentoEmail> listar() {
        return entityManager.createQuery("SELECT ae FROM AgendamentoEmail ae",
                AgendamentoEmail.class).getResultList();
    }

    public void inserir(AgendamentoEmail agendamentoEmail) {
        entityManager.persist(agendamentoEmail);
    }

    public List<AgendamentoEmail> listarEmailsNaoAgendados() {
        return entityManager.createQuery(
                "SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado = FALSE",
                AgendamentoEmail.class
        ).getResultList();
    }

    public void atualizar(AgendamentoEmail agendamentoEmail) {
        try {
            userTransaction.begin();
            entityManager.merge(agendamentoEmail);
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
