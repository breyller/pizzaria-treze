package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {
    
    public ClienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetId() {
        System.out.println("Teste getId");
        Cliente cli = new Cliente();
        int resultadoEsperado = 0;
        int id = cli.getId();
        assertEquals(resultadoEsperado, id);
        fail("O id não corresponde ao esperado. Era esperado " + resultadoEsperado + ", mas foi recebido " + id);
    }

    @Test
    public void testSetId() {
        System.out.println("Teste setId");
        int id = 10;
        Cliente cli = new Cliente();
        cli.setId(id);
        assertEquals(cli.getId(), id);
        fail("O id não corresponde ao esperado. Era esperado " + id + ", mas foi recebido " + cli.getId());
    }

    @Test
    public void testGetTelefone() {
        System.out.println("getTelefone");
        Cliente instance = new Cliente();
        long expResult = 0L;
        long result = instance.getTelefone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetTelefone() {
        System.out.println("setTelefone");
        long telefone = 0L;
        Cliente instance = new Cliente();
        instance.setTelefone(telefone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Cliente instance = new Cliente();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Cliente instance = new Cliente();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetEnderco() {
        System.out.println("getEnderco");
        Cliente instance = new Cliente();
        String expResult = "";
        String result = instance.getEnderco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetEnderco() {
        System.out.println("setEnderco");
        String enderco = "";
        Cliente instance = new Cliente();
        instance.setEnderco(enderco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
