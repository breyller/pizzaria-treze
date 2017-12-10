package br.com.pizzariatreze.test.br.com.pizzariatreze.dao;

import br.com.pizzariatreze.dao.ClienteDao;
import br.com.pizzariatreze.dto.ClienteDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteDaoTest {
    
    public ClienteDaoTest() {
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

    /**
     * Test of getById method, of class ClienteDao.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        ClienteDao instance = new ClienteDao();
        ClienteDto expResult = null;
        ClienteDto result = instance.getById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByNome method, of class ClienteDao.
     */
    @Test
    public void testGetByNome() {
        System.out.println("getByNome");
        String nome = "";
        ClienteDao instance = new ClienteDao();
        ArrayList<ClienteDto> expResult = null;
        ArrayList<ClienteDto> result = instance.getByNome(nome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByTelefone method, of class ClienteDao.
     */
    @Test
    public void testGetByTelefone() {
        System.out.println("getByTelefone");
        String telefone = "";
        ClienteDao instance = new ClienteDao();
        ArrayList<ClienteDto> expResult = null;
        ArrayList<ClienteDto> result = instance.getByTelefone(telefone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class ClienteDao.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        ClienteDto cliente = null;
        ClienteDao instance = new ClienteDao();
        boolean expResult = false;
        boolean result = instance.save(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class ClienteDao.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        ClienteDto cliente = null;
        ClienteDao instance = new ClienteDao();
        List<Object> expResult = null;
        List<Object> result = instance.search(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ClienteDao.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int id = 0;
        ClienteDao instance = new ClienteDao();
        boolean expResult = false;
        boolean result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
