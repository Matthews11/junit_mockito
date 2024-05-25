package org.curso.junit.ejemplos.models;

import java.math.BigDecimal;

import org.curso.junit.ejemplos.exception.DineroInsuficienteException;

public class Cuenta {
	
	private String persona;
	
	private BigDecimal saldo;
	
	private Banco banco;
	
	
	public Cuenta() {
		// TODO Auto-generated constructor stub
	}



	public Cuenta(String persona, BigDecimal saldo) {
		super();
		this.persona = persona;
		this.saldo = saldo;
	}



	public String getPersona() {
		return persona;
	}



	public void setPersona(String persona) {
		this.persona = persona;
	}



	public BigDecimal getSaldo() {
		return saldo;
	}



	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


	
	public Banco getBanco() {
		return banco;
	}



	public void setBanco(Banco banco) {
		this.banco = banco;
	}



	public void debito(BigDecimal monto) {
		BigDecimal nuevoSaldo=this.saldo.subtract(monto);
		if(nuevoSaldo.compareTo(BigDecimal.ZERO)<0) {
			throw new DineroInsuficienteException("Dinero Insuficiente");
		}else {
			this.saldo=nuevoSaldo;
		}
	}
	
    public void credito(BigDecimal monto) {
    	this.saldo=this.saldo.add(monto);
	}
	

	@Override
	public String toString() {
		return "Cuenta [persona=" + persona + ", saldo=" + saldo + "]";
	}
	
	
}
