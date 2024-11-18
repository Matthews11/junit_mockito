package org.curso.junit.ejemplos.models;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.curso.junit.ejemplos.exception.DineroInsuficienteException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CuentaTest {
	
	private Cuenta cuenta;
	
	@BeforeEach
	// puede tener configuraciones como de BDD seguridad para cargarlo antes de todos los metodos
	void initMetodoTest() {
		System.out.println("Iniciando metodo");
		this.cuenta = new Cuenta("Jared",new BigDecimal("1000.12345"));
		
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("Finalizando el metodo de prueba");
	}
	
	@Test
	@DisplayName("Probando nombre de la cuenta")
	void TestCuenta() {
		this.cuenta  = new Cuenta();
		cuenta.setPersona("Jared");
		String resultadoEsperado ="Jared";
		String resultadoObtenido =cuenta.getPersona();
		// se pone lambda () -> para que no se instancie el mensaje
		Assertions.assertEquals("Jared", resultadoObtenido,()->"Se esperaba: ".concat(resultadoEsperado)+"\n Sin embargo fue: "+resultadoObtenido);
	} 
	
	
	
	@Test
	@Disabled
	@DisplayName("Probando la clase suma")
	void TestSuma() {
		fail("Todo");
		Suma suma = new Suma();
		int resultadoObtenido =suma.suma(1, 2);
		Assertions.assertEquals(3, resultadoObtenido);
		
	}
	
	
	@Test
	@DisplayName("Probando que la cuenta tenga saldo")
	void testDebitoCuenta() {
		this.cuenta  = new Cuenta("Jared",new BigDecimal("1000.12345"));
		cuenta.debito(new BigDecimal(100));
		Assertions.assertNotNull(cuenta.getSaldo(), "El saldo no puede ser nullo");
		Assertions.assertEquals(900, cuenta.getSaldo().intValue());
		Assertions.assertEquals("900.12345", cuenta.getSaldo().toString());
		
		
	}

	@Test
	@Disabled
	@DisplayName("Probando la resta en el saldo")
	void testCreditoCuenta() {
		this.cuenta  = new Cuenta("Jared",new BigDecimal("1000.12345"));
		cuenta.credito(new BigDecimal(100));
		Assertions.assertNotNull(cuenta.getSaldo(), ()-> "El saldo no debe ser null");
		Assertions.assertEquals(1100, cuenta.getSaldo().intValue());
		Assertions.assertEquals("1100.12345", cuenta.getSaldo().toString());
	}
	
	@Test
	@Disabled
	@DisplayName("Validando Dinero insuficiente")
	void testDineroInsuficienteExcepcionCuenta() {
		this.cuenta  = new Cuenta("Jared",new BigDecimal("1000.12345"));
		Exception excepcion =Assertions.assertThrows(DineroInsuficienteException.class, ()->{
			cuenta.debito(new BigDecimal(1500));
		});
		String actual = excepcion.getLocalizedMessage();
		String esperado="Dinero Insuficiente";
		Assertions.assertEquals(esperado, actual);
	}
	
	@Test
	@DisplayName("Probando transferencias entre cuentas")
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
	@DisplayName("Probando relaciones entre cuentas y bando con assertAll")
	void testRelacionBancoCuentas() {
		Cuenta cuenta1 = new Cuenta("Jared",new BigDecimal("2500"));
		Cuenta cuenta2 = new Cuenta("Mathew",new BigDecimal("1000"));
		Banco BFA = new Banco();
		BFA.AgregarCuenta(cuenta1);
		BFA.AgregarCuenta(cuenta2);
		BFA.setNombre("Banco de Fomento Agropecuario");
		BFA.transferir(cuenta1, cuenta2, new BigDecimal(1000));
		Assertions.assertAll(()->{
			Assertions.assertEquals(1500, cuenta1.getSaldo().intValue());
		},()->{
			Assertions.assertEquals(2000, cuenta2.getSaldo().intValue());
		},()->{
			Assertions.assertEquals(2, BFA.getCuentas().size());
		},()->{
			Assertions.assertEquals("Banco de Fomento Agropecuario",cuenta1.getBanco().getNombre());
			
		},()->{
			Assertions.assertEquals("Mathew", BFA.getCuentas().stream()
					.filter(c ->c.getPersona().equals("Mathew"))
					.findFirst().get().getPersona());
		},()->{
			Assertions.assertTrue(BFA.getCuentas().stream()
					.anyMatch(c ->c.getPersona().equals("Mathew")));
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
