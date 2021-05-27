package br.com.alura.servlet;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("emails")
public class AgendamentoEmailServlet extends HttpServlet {

    @Inject
    private AgendamentoEmailServico agendamentoEmailServico;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        this.agendamentoEmailServico.listar()
                .forEach(email -> pw.print("Os emails disponíveis são: " + email.getEmail()));
    }

    @Override
    // email, assunto, mensagem
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String[] emailInfo = reader.readLine().split(",");
        AgendamentoEmail agendamentoEmail = new AgendamentoEmail();
        agendamentoEmail.setEmail(emailInfo[0]);
        agendamentoEmail.setAssunto(emailInfo[1]);
        agendamentoEmail.setMensagem(emailInfo[2]);
        this.agendamentoEmailServico.inserir(agendamentoEmail);
    }
}
