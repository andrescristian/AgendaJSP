/**
 * Confirmador de exclusão de dados
 */

function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão deste contato?")
	if(resposta === true){
		//alert(idcon)	Teste de confirmação
		window.location.href = "delete?idcon=" + idcon	//Encaminhar a requisição ao Servlet
		//window.location	--> Usado para redirecionar ao Servlet. Sair desse documento e ir para outro local, que no caso é pra requisição ao Servlet
	}
}