/**
 * Validação do Formulário (novo.html)
 */

function validar() {
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value

	if (nome === "") {
		alert("Preencha o nome")
		frmContato.nome.focus()
	} else if (fone === "") {
		alert("Preencha o Telefone")
		frmContato.fone.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}