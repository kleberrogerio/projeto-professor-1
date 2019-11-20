
package professor.hello;

import static spark.Spark.*;

import org.bson.Document;



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

		//inicializarPesquisa();
		//inicializarUsers();
   
		ControllerProfessor controller = new ControllerProfessor(model);
 
	    controller.Auth();
	    controller.ativarUsuario();  
	    controller.loginProfessor();
	    controller.updateProjetoProfessor();
	    controller.searchprofessor();
	    controller.atualizaProfessor();
	    controller.inserirProfessor();
	 
    }
    
    public static void inicializarUsers() {
    	//CADI
 
    	//Professores
    	model.addProfessor(Document.parse("{'name':'Giuliano', 'email':'Giuliano@fatec.sp.gov.br','senha':'1234', 'ativo':true}"));
    	model.addProfessor(Document.parse("{'name':'Sakaue', 'email':'Sakaue@fatec.sp.gov.br','senha':'1234', 'ativo':true}"));
    	model.addProfessor(Document.parse("{'name':'Nanci', 'email':'Nadalete@fatec.sp.gov.br','senha':'1234', 'ativo':true}"));
    }
    
    public static void inicializarPesquisa(){
    	
    	
    	model.addProjeto(Document.parse("{'titulo':'Teste','descricao-breve' :'Teste descricao', 'descricao-completa':'','descricao-tecnologias':'','link-externo-1':'','link-externo-2':'','fase': 1,'reuniao' :{'data' :'','horario' :'','local':'','datas-possiveis' : [] },'status' : {'negado' : false,'motivo':'' },'entregas' : [{'email':'rone.bento@fatec.sp.gov.br'},{'email':'jose.bento@fatec.sp.gov.br'}],'alunos':[{'email':'Isabella@fatec.sp.gov.br'},{'email':'Kleber@fatec.sp.gov.br'}],'responsavel-cadi':'','responsavel-professor':[{'email':'Giuliano@fatec.sp.gov.br'},{'email':'Sakaue@fatec.sp.gov.br'}],'responsavel-empresario':'teste@teste'}"));
    	model.addProjeto(Document.parse("{'titulo':'Teste2','descricao-breve' :'Teste descricao', 'descricao-completa':'','descricao-tecnologias':'','link-externo-1':'','link-externo-2':'','fase': 1,'reuniao' :{'data' :'','horario' :'','local':'','datas-possiveis' : [] },'status' : {'negado' : false,'motivo':'' },'entregas' : [{'email':'rone.bento@fatec.sp.gov.br'},{'email':'jose.bento@fatec.sp.gov.br'}],'alunos':[{'email':'Isabella@fatec.sp.gov.br'},{'email':'Kleber@fatec.sp.gov.br'}],'responsavel-cadi':'','responsavel-professor':[{'email':'Nadalete@fatec.sp.gov.br'},{'email':'Sakaue@fatec.sp.gov.br'}],'responsavel-empresario':'teste@teste'}"));
    	model.addProjeto(Document.parse("{'titulo':'Teste3','descricao-breve' :'Teste descricao', 'descricao-completa':'','descricao-tecnologias':'','link-externo-1':'','link-externo-2':'','fase': 1,'reuniao' :{'data' :'','horario' :'','local':'','datas-possiveis' : [] },'status' : {'negado' : false,'motivo':'' },'entregas' : [{'email':'rone.bento@fatec.sp.gov.br'},{'email':'jose.bento@fatec.sp.gov.br'}],'alunos':[{'email':'Isabella@fatec.sp.gov.br'},{'email':'Kleber@fatec.sp.gov.br'}],'responsavel-cadi':'','responsavel-professor':[{'email':'Giuliano@fatec.sp.gov.br'},{'email':'Nadalete@fatec.sp.gov.br'}],'responsavel-empresario':'teste@teste'}"));
    	model.addProjeto(Document.parse("{'titulo':'Teste4','descricao-breve' :'Teste descricao', 'descricao-completa':'','descricao-tecnologias':'','link-externo-1':'','link-externo-2':'','fase': 1,'reuniao' :{'data' :'','horario' :'','local':'','datas-possiveis' : [] },'status' : {'negado' : false,'motivo':'' },'entregas' : [{'email':'rone.bento@fatec.sp.gov.br'},{'email':'jose.bento@fatec.sp.gov.br'}],'alunos':[{'email':'Isabella@fatec.sp.gov.br'},{'email':'Kleber@fatec.sp.gov.br'}],'responsavel-cadi':'','responsavel-professor':[{'email':'Nadalete@fatec.sp.gov.br'},{'email':'Giuliano@fatec.sp.gov.br'}],'responsavel-empresario':'teste@teste'}"));
    	}
    }
    
    
