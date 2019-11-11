
package hello;

import static spark.Spark.*;

import org.bson.Document;

import com.mongodb.client.FindIterable;
//import org.json.me;

import Professor.ControllerProfessor;
import Professor.ModelProfessor;


public class MainServer {
	
	final static ModelProfessor model = new ModelProfessor();
	
    public static void main(String[] args) {
 
		// Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 8083;
        }
        port(port);

		//Servir conteudo html, css e javascript
		staticFileLocation("/static");

		inicializarPesquisa();
		inicializarUsers();
  
		ControllerProfessor controller = new ControllerProfessor(model);

	    controller.Auth();
	    controller.ativarUsuario();  
	    controller.loginProfessor();
	    controller.atribuirChave();
	    controller.searchprofessor();
	    controller.atualizaProfessor();
	 
    }
    
    public static void inicializarUsers() {
    	//CADI
 
    	//Professores
    	model.addProfessor(Document.parse("{'name':'Giuliano', 'email':'Giuliano@fatec.sp.gov.br','senha':'1234', 'ativo':true}"));
    	model.addProfessor(Document.parse("{'name':'Sakaue', 'email':'Sakaue@fatec.sp.gov.br','senha':'1234', 'ativo':true}"));
    	model.addProfessor(Document.parse("{'name':'Nanci', 'email':'Nanci@fatec.sp.gov.br','senha':'1234', 'ativo':true}"));
    }
    
    public static void inicializarPesquisa(){
    	
    	
    	model.addProjeto(Document.parse("{'titulo':'Teste','descricao-breve' :'Teste descricao', 'descricao-completa':'','descricao-tecnologias':'','link-externo-1':'','link-externo-2':'','fase': 1,'reuniao' :{'data' :'','horario' :'','local':'','datas-possiveis' : [] },'status' : {'negado' : false,'motivo':'' },'entregas' : [],'alunos':[],'responsavel-cadi':'','responsavel-professor':[{'email':'Giuliano@fatec.sp.gov.br'},{'email':'sakaue@fatec.sp.gov.br'}],'responsavel-empresario':'teste@teste'}"));
		model.addProjeto(Document.parse("{'titulo' : 'Teste2', 'descricao-breve' : 'Teste descricao', 'descricao-completa' : 'Essa é a descrição completa', 'descricao-tecnologias' : 'Essa é a descrição de tecnologias', 'link-externo-1' : 'http://linkzao.com', 'link-externo-2' : 'http://linkzera.com', 'fase' : 3, 'reuniao' : { 'data' : '', 'horario' : '', 'local' : '', 'datas-possiveis' : [] }, 'status' : { 'negado' : false, 'motivo' : 'falta de informaÃ§Ãµes' }, 'entregas' : [], 'alunos' : [], 'responsavel-cadi' : '', 'responsavel-professor' : [], 'responsavel-empresario' : 'teste@teste','key-projeto-aluno':'' }"));
		model.addProjeto(Document.parse("{'titulo' : 'Teste3', 'descricao-breve' : 'Teste descricao', 'descricao-completa' : 'Essa é a descrição completa', 'descricao-tecnologias' : 'Essa é a descrição de tecnologias', 'link-externo-1' : 'http://linkzao.com', 'link-externo-2' : 'http://linkzera.com', 'fase' : 4, 'reuniao' : { 'data' : '', 'horario' : '', 'local' : '', 'datas-possiveis' : [] }, 'status' : { 'negado' : false, 'motivo' : 'falta de informaÃ§Ãµes' }, 'entregas' : [], 'alunos' : [], 'responsavel-cadi' : '', 'responsavel-professor' : [], 'responsavel-empresario' : 'teste@teste','key-projeto-aluno':'' }"));
    	}
    }
    
    
