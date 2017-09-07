package ifma.dcomp.sistema.loja.repository.filtro;

import java.math.BigDecimal;

import ifma.dcomp.sistema.loja.model.Fornecedor;
import ifma.dcomp.sistema.loja.model.Origem;

public class ProdutoFilter {

	private String sku;
	private String nome;
	private Fornecedor fornecedor;
	private Origem origem;
	private BigDecimal precoDe;
	private BigDecimal precoAte;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public BigDecimal getPrecoDe() {
		return precoDe;
	}

	public void setPrecoDe(BigDecimal valorDe) {
		this.precoDe = valorDe;
	}

	public BigDecimal getPrecoAte() {
		return precoAte;
	}

	public void setPrecoAte(BigDecimal valorAte) {
		this.precoAte = valorAte;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
