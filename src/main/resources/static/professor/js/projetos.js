/**/
var session_login = sessionStorage.getItem("sess_email_professor");


if(session_login == null){
        window.location.href = 'index.html';
}else{

  $(document).ready(function () {
      let projects;
    
/* Informações do Usuário Professor */
      $.post("/professorLogado", JSON.stringify({'email': session_login}), function(user){
        userData(user);
      }, "json");
 
    
/* </> Informações do Usuário CADI */


       /* <> Rotas de inicialização dos objetos */
      $.get('/myprojects', session_login)
          .done(function(projetos){
          projects = JSON.parse(projetos);
          insertMyProjects(projects);
      });
      
       /* </> Rotas de inicialização dos objetos */

      /* <> Funções */
    
      /* listagem de projetos do professor */
      function insertMyProjects(projecs) {
        
        console.log(projecs);
        let tbody = $('[data-myProjects-table-body]');
    
        projecs.forEach(project => {
          let project_id = project._id.$oid;
          let tr2 = $.parseHTML(`<tr data-project-item="${ project._id }> 
            <th scope="row">${ project.titulo }</th>
                <td>${ project.titulo }</td>
                <td>${ project['descricao-breve'] }</td>
                <td>Nome da Empresa</td>
                <td id="td-key">${project['key-projeto-aluno'] != null ? project['key-projeto-aluno'] : '<input type="text" class="form-control" id="keyAlId" name="key_al" placeholder="Inserir Chave">'}<td>
                <td id="td-alkey-${project_id}"></td>
                <td id="td-alunos-${project_id}"></td>
            </tr>
          `);
   
          tbody.append(tr2);

          /*Gerando Chave de Acesso do Aluno td-alkey */
          let generateKey = $.parseHTML(`<button type="button" class="btn btn-primary">
              Gerar Chave
          </button>
          </li>`);

          let removeKey = $.parseHTML(`<button type="button" class="btn btn-danger">
              Remover Chave
          </button>
          </li>`);

          if(project['key-projeto-aluno'] == null){
            let $generateKey = $(generateKey);
            $generateKey.click(function(e){
              e.preventDefault();
              var myKey = $("#keyAlId").val();
            
              if (confirm('Deseja realmente alterar o chave dos alunos ?')) {
                $.post("/atribuirChave", JSON.stringify({'_id':project._id, 'key-projeto-aluno': myKey}), "json");
                location.reload();
              }
            });
  
            $('#td-alkey-'+project_id).append(generateKey);
            /* </ >Gerando Chave de Acesso do Aluno td-alkey </ >*/
          }
          else{
            let $removeKey = $(removeKey);
            $removeKey.click(function(e){
              e.preventDefault();
              var myKey = null;
              if (confirm('Deseja realmente alterar o chave dos alunos ?')) {
                $.post("/atribuirChave", JSON.stringify({'_id':project._id, 'key-projeto-aluno': myKey}), "json");
                location.reload();
              }
            });
  
            $('#td-alkey-'+project_id).append(removeKey);
           
          }
         

          /*Gerenciar alunos presentes td-alunos*/
          let AlPresentes = $.parseHTML(`
          <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modal-alunos-presentes">
             Alunos Presentes
          </button>
          </li>`);

          let $AlPresentes = $(AlPresentes);

          $AlPresentes.click(function(e){
            e.preventDefault();
            _AlunosPresentes(project);
          });

          $('#td-alunos-'+project_id).append(AlPresentes);
          /*</ >Gerenciar alunos presentes td-alunos</ >*/
        });
      }


function userData(user){
      /* <> Logou do Usuário */
      let navCADI = $('[data-user]');
      let logout = $.parseHTML(`
      <li><i class="fa fa-sign-out" aria-hidden="true"></i> 
      <button type="button" class="btn btn-danger">Logout</button></li>`);

      let $logout = $(logout);
      $logout.click(function(e) {
          e.preventDefault();
          if (confirm('Realmente deseja Sair ?')) {
              sessionStorage.clear(session_login);
              window.location.href = 'index.html';
          }
      });


      /* Alterar Senha */
      let updateSenha = $.parseHTML(`
      <li><i class="fa fa-sign-out" aria-hidden="true"></i>
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-update-senha">
          Alterar Senha
      </button>
      </li>`);

      let $updateSenha = $(updateSenha);
      $updateSenha.click(function(e){
        e.preventDefault();
        _formUpdateSenha(user);
      });
      /* </> Alterar Senha */

      /* </> Logou do Usuário */

      /* Pupula Usuário Data */
      let data = $.parseHTML(`
      <li>${user.name}</li>
      <li>Professor</li>`);
      /* </> Pupula Usuário Data */
    
      navCADI.append(data);
      navCADI.append(updateSenha);
      navCADI.append(logout);
      $("li").addClass("list-inline-item");
  }

/* </> Funções */

  
});

}




function _AlunosPresentes(project){

  let form_alunos =  `
    <div class="modal fade" id="modal-alunos-presentes" tabindex="-1" role="dialog" aria-labelledby="modal-alunos-presentes" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Alteração de Senha</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
      
        </div>
        <div class="modal-footer" >
        
        </div>
    </div>
  </div>`;

  /* Evento insere modal no HTML */
  $(document.body).prepend(form_alunos);
  /* Evento Remove modal do HTML */
  $('.close').click(function(e){
    e.preventDefault();
    $("#modal-update-senha").remove();
    $(".modal-backdrop ").remove();
  });


}


function fechaPopupSemDono(event) {
  event.preventDefault();
  document.getElementById('modal_semdono').style.display='none';    
}


function _formUpdateSenha(user){

  let form_senha =  `
    <div class="modal fade" id="modal-update-senha" tabindex="-1" role="dialog" aria-labelledby="modal-update-senha" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Alteração de Senha</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
       Senha: <input class="form-control"  type="password" id="senha-antiga" name="senha-antiga" placeholder="Senha Atual" style="max-width:350px" required>
       Nova Senha: </label><input class="form-control" type="password" id="senha-nova1" name="senha-nova1" placeholder="Nova Senha" style="max-width:350px" required>
       Nova Senha Novamente: </label><input class="form-control" type="password" id="senha-nova2" name="senha-nova2" placeholder="Nova Senha" style="max-width:350px" required>
        </div>
        <div class="modal-footer" >
          <button type="submit" class="btn btn-primary alterarSenha" id="alterarSenha">Salvar mudanças</button>
          <div id="modal-footer-password"></div>
          </div>
      </div>
    </div>
  </div>`;

  /* Evento insere modal no HTML */
  $(document.body).prepend(form_senha);
  /* Evento Remove modal do HTML */
  $('.close').click(function(e){
    e.preventDefault();
    $("#modal-update-senha").remove();
    $(".modal-backdrop ").remove();
  });
  /* Evento submita a senha nova */
  $('#alterarSenha').click(function(e){
    e.preventDefault();
    $("#modal-footer-password").html('');
    var senhaAntiga = $("#senha-antiga").val();
    var senha1 = $("#senha-nova1").val();
    var senha2 = $("#senha-nova2").val();
    
 
    if(senhaAntiga === user.senha && senhaAntiga != null){
        if(senha1 === senha2 && senha1 != null && senha2 != null){
          $.post("/updateProfessor", JSON.stringify({'_id':user._id, 'senha': senha1}), "json");
          $('#modal-footer-password').append($.parseHTML(`<div class="alert alert-success" role="alert">
          Senha alterada com sucesso</div>`));
        }else{
          $('#modal-footer-password').append($.parseHTML(`<div class="alert alert-danger" role="alert">
          Senha de nova ou senha de confirmação inválidas ou não correspondentes.</div>`));
        }
    }else{
      $('#modal-footer-password').append($.parseHTML(`<div class="alert alert-danger" role="alert">
          Senha não corresponde com a atual!, por favor insira a senha correta.
      </div>`));
    }
  });
}

