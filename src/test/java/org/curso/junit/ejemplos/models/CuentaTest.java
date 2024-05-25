package org.curso.junit.ejemplos.models;

import java.math.BigDecimal;

import org.curso.junit.ejemplos.exception.DineroInsuficienteException;
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
	
	@Test
	void testDineroInsuficienteExcepcionCuenta() {
		Cuenta cuenta = new Cuenta("Jared",new BigDecimal("1000.12345"));
		Exception excepcion =Assertions.assertThrows(DineroInsuficienteException.class, ()->{
			cuenta.debito(new BigDecimal(1500));
		});
		String actual = excepcion.getLocalizedMessage();
		String esperado="Dinero Insuficiente";
		Assertions.assertEquals(esperado, actual);
	}
	
	@Test
	void testTransferirDineroCuenta() {
		Cuenta cuenta1 = new Cuenta("Jared",new BigDecimal("2500"));
		Cuenta cuenta2 = new Cuenta("Mathew",new BigDecimal("1000"));
		Banco BFA = new Banco();
		BFA.setNombre("Banco de Fomento Agropecuario");
		BFA.transferir(cuenta1, cuenta2, new BigDecimal(1000));
		Assertions.assertEquals(1500, cuenta1.getSaldo().intValue());
		Assertions.assertEquals(2000, cuenta2.getSaldo().intValue());
		
	}
	
	
	@Test
	void testRelacionBancoCuentas() {
		Cuenta cuenta1 = new Cuenta("Jared",new BigDecimal("2500"));
		Cuenta cuenta2 = new Cuenta("Mathew",new BigDecimal("1000"));
		Banco BFA = new Banco();
		BFA.AgregarCuenta(cuenta1);
		BFA.AgregarCuenta(cuenta2);
		BFA.setNombre("Banco de Fomento Agropecuario");
		BFA.transferir(cuenta1, cuenta2, new BigDecimal(1000));
		Assertions.assertEquals(1500, cuenta1.getSaldo().intValue());
		Assertions.assertEquals(2000, cuenta2.getSaldo().intValue());
		
		Assertions.assertEquals(2, BFA.getCuentas().size());
		Assertions.assertEquals("Banco de Fomento Agropecuario",cuenta1.getBanco().getNombre());
		Assertions.assertEquals("Mathew", BFA.getCuentas().stream()
				.filter(c ->c.getPersona().equals("Mathew"))
				.findFirst().get().getPersona());
		
		Assertions.assertTrue(BFA.getCuentas().stream()
				.anyMatch(c ->c.getPersona().equals("Mathew")));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
