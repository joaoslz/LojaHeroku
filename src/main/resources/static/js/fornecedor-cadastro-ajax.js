
$( function() {
	
	var modal = $('#modal-cadastro-fornecedor');
	var form = modal.find('form');
	
	var inputNome = modal.find('#nome');

	modal.on('shown.bs.modal', function(e) {
		inputNome.focus();
	});
	
	
	modal.on('hide.bs.modal', function(e) {
		inputNome.val('');
/*		alertErro = form.find('.msg-modal-cadastro-fornecedor-js')
		form.find('.msg-modal-cadastro-fornecedor-js').addClass('d-none');
		form.find('.form-group, .nome-js').removeClass('has-danger');
*/	});


	form.submit( function(event) {
		event.preventDefault();
	});
	
	
	var botaoSalvar = modal.find('.btn-cadastro-fornecedor-modal-js');
	botaoSalvar.click(function() {
		
		var nomeFornecedor = inputNome.val().trim();
		var url = form.attr('action');

		$.ajax({
			url : url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({nome: nomeFornecedor}),
			
			error: function(obj) {
				var mensagemErro = obj.responseText;
				var alertErro = form.find('.msg-modal-cadastro-fornecedor-js');
				alertErro.removeClass('d-none');
				alertErro.html('<span>' + mensagemErro + '</span>');
				form.find('.form-group, .nome-js').addClass('has-danger');
				
			},
		    /*error: function() {
			   console.log('erro ao salvar fornecedor');
			   console.log(arguments);
		    }*/
			
			success : function(fornecedor) {
				console.log(fornecedor);

				var comboFornecedor = $('#selectFornecedor');
				comboFornecedor.append('<option value= ' + fornecedor.id + '>' +  fornecedor.nome + '</option>');
				comboFornecedor.val(fornecedor.id );

				modal.modal('hide');

			}
		
			
			
			
		});
	});
	
	
});



