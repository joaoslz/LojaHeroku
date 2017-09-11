package ifma.dcomp.sistema.loja.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ifma.dcomp.sistema.loja.model.DetalhesImagemProduto;

public class ImagemProdutoUploadService {
	
	private static final String PASTA_IMG_PRODUTOS = "img-produtos";

	
	
	private MultipartFile multipartFile;
	
	private Integer produtoId;
	private String realPath;
	
	@Autowired
	public ImagemProdutoUploadService(MultipartFile arquivo) {
		this.multipartFile = arquivo;
	}
		
    // Salva imagem do produto no servidor
	public void salvaImagem(Integer produtoId, HttpServletRequest request) {
		
		this.produtoId = produtoId;
		
		try {
			realPath = request.getServletContext().getRealPath("/" + PASTA_IMG_PRODUTOS );
			
			//criaPastaNoServidorCasoNaoExista(realPath);
			
			//String  pathArquivoServidor = realPath + "/" + multipartFile.getOriginalFilename() ;
			String  pathArquivoServidor = realPath + "/" + produtoId + ".png" ;

			System.out.println(">>>>>>> " + pathArquivoServidor );			
			
			// transfere o multipartFile para o servidor
			multipartFile.transferTo( new File(pathArquivoServidor) );
			
		} catch (IllegalStateException | IOException e ) {
			throw new RuntimeException(e );
		}
	}
	
	public DetalhesImagemProduto criaDetalhesImagemLivro() {
		
		DetalhesImagemProduto imagemLivro = new DetalhesImagemProduto();
		
		imagemLivro.setImagemPath(PASTA_IMG_PRODUTOS );
		imagemLivro.setRealPath(realPath );

		//imagemLivro.setNomeArquivo(multipartFile.getOriginalFilename() );
		imagemLivro.setNomeArquivo(produtoId + ".png" );
		
		imagemLivro.setTipoArquivo(multipartFile.getContentType() );

		imagemLivro.setTamanhoArquivo(multipartFile.getSize() );
		
		return imagemLivro;
	}
	
/*	private void criaPastaNoServidorCasoNaoExista(String realPath) {
		File diretorio = new File(realPath);
		
		if ( !diretorio.exists() ) {
			diretorio.mkdirs();
		}
	}
*/
}
