package teste;
import java.io.Serializable;
import java.util.Date;

import javadev.filereader.annotations.BooleanConverter;
import javadev.filereader.annotations.FileHeader;
import javadev.filereader.annotations.SimpleCSVBean;
import javadev.filereader.annotations.Size;

@SimpleCSVBean
public class Classificado implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Size(100)
	@FileHeader("CLASSIFICACAO")
	private Integer classificacao;
	@Size(100)
	@FileHeader("NOME")
	private String nome;
	@Size(100)
	@FileHeader("CARGO")
	private String cargo;
	@Size(100)
	@FileHeader("ESPECIFICIDADE")
	private String especificidade;
	@Size(100)
	@FileHeader("DATA_NASCIMENTO")
	private Date dataNascimento; 
	@Size(1)
	@BooleanConverter(trueValue="1", falseValue="0")
	@FileHeader("DEFICIENTE")
	private Boolean ehDeficiente;
	@Size(1)
	@FileHeader("SEXO")
	private Sexo sexo;

	public Integer getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEspecificidade() {
		return especificidade;
	}
	public void setEspecificidade(String especificidade) {
		this.especificidade = especificidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Boolean getEhDeficiente() {
		return ehDeficiente;
	}
	public void setEhDeficiente(Boolean ehDeficiente) {
		this.ehDeficiente = ehDeficiente;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Classificado [classificacao=" + classificacao + ", nome="
				+ nome + ", cargo=" + cargo + ", especificidade="
				+ especificidade + ", dataNascimento=" + dataNascimento
				+ ", ehDeficiente=" + ehDeficiente + ", sexo=" + sexo + "]";
	}

}
