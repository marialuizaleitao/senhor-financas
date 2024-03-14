package controller;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.bo.RelatorioBO;
import model.dto.RelatorioDTO;

@Path("/relatorio")
public class RelatorioRest {

	@GET
	@Path("/{idusuario}/{ano}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RelatorioDTO> gerarRelatorioReceitasController(@PathParam("idusuario") int idUsuario,
			@PathParam("ano") int ano) {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.gerarRelatorioPorUsuarioBO(idUsuario, ano);
	}

}
