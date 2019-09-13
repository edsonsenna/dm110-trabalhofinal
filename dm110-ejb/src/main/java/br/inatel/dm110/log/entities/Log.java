package br.inatel.dm110.log.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="log")
public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="log_id_seq", initialValue=1, allocationSize=1)
	private int id;
	private String codigo;
	private String operacao;
	private LocalDate data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "LOG => "+this.getId()+" "+this.getCodigo()+" "+this.getOperacao()+" "+this.getData() ;
	}
	
	
	

}
