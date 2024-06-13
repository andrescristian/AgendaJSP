<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");

//Teste de recebimento (No topo da página)
/*for (int i = 0; i < lista.size(); i++){
	out.println(lista.get(i).getIdcon());
	out.println(lista.get(i).getNome());
	out.println(lista.get(i).getFone());
	out.println(lista.get(i).getEmail());
}*/
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="icon" href="img/favicon.png">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

<title>Agenda de contatos</title>
</head>
<body>
	<section
		class="container text-center text-primary py-3 bg-dark bordasA">
		<h1>Agenda de Contatos</h1>
		<a href="novo.html" class="btn btn-primary btn-lg mb-3"> 
			<img src="img/contato.png" alt="Novo contato"> Novo contato
		</a>
		<a href="report" class="btn btn-primary btn-lg mb-3">	<!-- report é pra Requisição --> 
			<img src="img/relatorio.png" alt="Relatório em PDF"> Relatório
		</a>
	

		<table class="table table-striped table-hover">
			<thead class="text-primary">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Nome</th>
					<th scope="col">Telefone</th>
					<th scope="col">E-mail</th>
					<th scope="col">Opções</th>
				</tr>
			</thead>
			<tbody class="text-light">

				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<th scope="row"><%=lista.get(i).getIdcon()%></th>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td>
					<a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="btn btn-light"> <!-- Configurar o Link para requisição de nome "select" ao Servlet -->
							Editar
					</a> <!-- Criar novo Botão Excluir. Dentro do botão "confirmar" chamar o Id 18/04 -->
					<a href="javascript:confirmar(<%= lista.get(i).getIdcon() %>)" class="btn btn-danger"> <!-- Configurar o Link para requisição de nome "select" ao Servlet -->
							Excluir	<!-- Primeiro no JS, e depois no Servlet -->
					</a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<p class="pt-3 text-light">&#169; 2024 - Somente para fins educativos.</p>
	</section>

	<!-- Links dos Scripts-->
	<script src="scripts/confirmador.js"></script>
	<script src="scripts/jquery.js"></script>
	<script src="scripts/popper.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>