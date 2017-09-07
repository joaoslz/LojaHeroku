package ifma.dcomp.sistema.loja.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "{form.produto.sku}")
	@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?", 
	         message = "{form.produto.sku.formato}")
	private String sku;

	@NotBlank(message = "{form.produto.nome}")
	@Size(min = 3, message = "{form.produto.nome.tamanho}")
	private String nome;

	@NotNull(message = "{form.produto.preco}")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal preco;

	@Column(columnDefinition = "text")
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Origem origem; 

	@Future(message = "{form.produto.dataValidade}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar dataValidade;

	@ManyToOne
	private Fornecedor fornecedor;

	@Size(min = 1, message = "{form.produto.categorias.quantidade}")
	@ManyToMany
	private List<Categoria> categorias;

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private DetalhesImagemProduto detalhesImagem;
	
	// callback para formatar o campo SKU em caixa alta
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		this.sku = this.sku.toUpperCase();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public DetalhesImagemProduto getDetalhesImagem() {
		return detalhesImagem;
	}

	public void setDetalhesImagem(DetalhesImagemProduto detalhesImagem) {
		this.detalhesImagem = detalhesImagem;
	}

	public boolean ehNovo() {
		return ( this.id == null );
	}
	
	
	public boolean temImagem() {
		return     ( this.detalhesImagem != null ) 
				&& (this.detalhesImagem.getNomeArquivo() != null);
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}


}
