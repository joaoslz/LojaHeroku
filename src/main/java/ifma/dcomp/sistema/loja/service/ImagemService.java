package ifma.dcomp.sistema.loja.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import ifma.dcomp.sistema.loja.model.Imagem;
import ifma.dcomp.sistema.loja.repository.Imagens;

@Service
public class ImagemService {
	
	private final static String PASTA_UPLOAD_IMAGEM = "upload-imagens";
	
	private Imagens repositorio;
	private ResourceLoader resourceLoader;

	@Autowired
	public ImagemService(Imagens repositorio, ResourceLoader resourceLoader) {
		this.repositorio = repositorio;
		this.resourceLoader = resourceLoader;
	}
	
	public Resource buscaImagemPelo(String nome) {
		return resourceLoader.getResource("file:" + PASTA_UPLOAD_IMAGEM + "/" + nome );
	}
	
	public void criaImagem(MultipartFile arquivo) throws IOException {
		
		if (!arquivo.isEmpty() ) {
			Files.copy( arquivo.getInputStream(), 
					    Paths.get(PASTA_UPLOAD_IMAGEM, 
					    arquivo.getOriginalFilename() )
					  );
			repositorio.save(new Imagem(arquivo.getOriginalFilename()) );
		}
	}
	
	public void excluirImagem(String nomeArquivo ) throws IOException {
		final Imagem peloNome = repositorio.findByNome(nomeArquivo );
		repositorio.delete(peloNome);
		
		Files.deleteIfExists(Paths.get(PASTA_UPLOAD_IMAGEM, nomeArquivo) );
		
	}
	
	
	@Bean
	//@Profile("dev")
	CommandLineRunner setUp(Imagens repositorio ) throws IOException {
		
		// limpa a pasta com as imagens
		
		return (args) -> {
				FileSystemUtils.deleteRecursively(new File(PASTA_UPLOAD_IMAGEM) );
				
				Files.createDirectory(Paths.get(PASTA_UPLOAD_IMAGEM) );
				
			    FileCopyUtils.copy("Teste arquivo", 
			    		           new FileWriter(PASTA_UPLOAD_IMAGEM + "/test" ) );
			    repositorio.save(new Imagem("test") );

			    FileCopyUtils.copy("Teste arquivo2", 
			    		new FileWriter(PASTA_UPLOAD_IMAGEM + "/test2" ) );
			    repositorio.save(new Imagem("test2") );
			    
			    FileCopyUtils.copy("Teste arquivo3", 
			    		new FileWriter(PASTA_UPLOAD_IMAGEM + "/test3" ) );
			    repositorio.save(new Imagem("test3") );
				
			};
		}
}
