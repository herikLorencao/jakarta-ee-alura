package br.com.alura.controller;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("emails")
public class AgendamentoEmailController {
    @Inject
    private AgendamentoEmailServico agendamentoEmailServico;
    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listar() {
        return Response.ok(this.agendamentoEmailServico.listar()).build();
    }
    
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response inserir(AgendamentoEmail agendamentoEmail) {
        this.agendamentoEmailServico.inserir(agendamentoEmail);
        return Response.ok().status(Response.Status.CREATED).build();
    }
}
