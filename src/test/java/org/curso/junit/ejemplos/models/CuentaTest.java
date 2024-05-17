package org.curso.junit.ejemplos.models;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CuentaTest {
	
	@Test
	void TestCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setPersona("Jared");
		String resultadoEsperado ="Jared";
		String resultadoObtenido =cuenta.getPersona();
		Assertions.assertEquals("Jared", resultadoObtenido);
	} 
	
	
	
	@Test
	void TestSuma() {
		Suma suma = new Suma();
		int resultadoObtenido =suma.suma(1, 2);
		Assertions.assertEquals(3, resultadoObtenido);
		
	}
	
	
	@Test
	void testDebitoCuenta() {
		Cuenta cuenta = new Cuenta("Jared",new BigDecimal("1000.12345"));
		cuenta.debito(new BigDecimal(100));
		Assertions.assertNotNull(cuenta.getSaldo());
		Assertions.assertEquals(900, cuenta.getSaldo().intValue());
		Assertions.assertEquals("900.12345", cuenta.getSaldo().toString());
		
		
	}

	@Test
	void testCreditoCuenta() {
		Cuenta cuenta = new Cuenta("Jared",new BigDecimal("1000.12345"));
		cuenta.credito(new BigDecimal(100));
		Assertions.assertNotNull(cuenta.getSaldo());
		Assertions.assertEquals(1100, cuenta.getSaldo().intValue());
		Assertions.assertEquals("1100.12345", cuenta.getSaldo().toString());
	}
}
