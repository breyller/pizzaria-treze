package br.com.pizzariatreze.test.br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.ClienteDao;
import br.com.pizzariatreze.dto.ClienteDto;
import br.com.pizzariatreze.model.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {
    
    public ClienteTest() {
    }
    
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 4;
        Cliente instance = new Cliente();
        Object result = instance.getById(id);
        assertNotNull(result);
    }
    
    @Test
    public void testGetByIdNull() {
        System.out.println("getById null");
        int id = 784798375;
        Cliente instance = new Cliente();
        try {
            Object result = instance.getById(id);
            assertNull(result);
        } catch (Exception e) {
            fail("Falha em cliente getById: " + e.getMessage());
        }
    }
    
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("nome", "Astrogildo Ribeiro");
        cliente.put("telefone", "(19) 99312-1234");
        cliente.put("endereco", "Rua Estrelina Astrogilda, 23");
        cliente.put("cpf", "39238451209");
        Cliente instance = new Cliente();
        boolean expResult = true;
        boolean result = instance.save(cliente);
        assertEquals(expResult, result);
    }

    @Test
    public void testSaveSemNome() throws Exception {
        System.out.println("save sem nome");
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("nome", "");
        cliente.put("telefone", "(19) 99312-1234");
        cliente.put("endereco", "Rua Estrelina Astrogilda, 23");
        cliente.put("cpf", "39238451209");
        Cliente instance = new Cliente();
        try {
            instance.save(cliente);
            fail("Falha no save cliente, deveria dar exception por falta de nome.");
        } catch (Exception e) {
            assertEquals("Nome deve estar preenchido.", e.getMessage());
        }
    }

    @Test
    public void testSaveSemTelefone() throws Exception {
        System.out.println("save sem telefone");
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("nome", "Eleutério Souza");
        cliente.put("telefone", "");
        cliente.put("endereco", "Rua Estrelina Astrogilda, 23");
        cliente.put("cpf", "39238451209");
        Cliente instance = new Cliente();
        try {
            instance.save(cliente);
            fail("Falha no save cliente, deveria dar exception por falta de telefone.");
        } catch (Exception e) {
            assertEquals("Telefone deve estar corretamente preenchido.", e.getMessage());
        }
    }

    @Test
    public void testSaveSemEndereco() throws Exception {
        System.out.println("save sem endereco");
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("nome", "Regnier Roger");
        cliente.put("telefone", "(19) 99312-1234");
        cliente.put("endereco", "");
        cliente.put("cpf", "39238451209");
        Cliente instance = new Cliente();
        try {
            instance.save(cliente);
            fail("Falha no save cliente, deveria dar exception por falta de endereco.");
        } catch (Exception e) {
            assertEquals("Endereco deve estar preenchido.", e.getMessage());
        }
    }

    @Test
    public void testSaveSemCpf() throws Exception {
        System.out.println("save sem cpf");
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("nome", "Vandisclei Somario");
        cliente.put("telefone", "(19) 99312-1234");
        cliente.put("endereco", "Rua Estrelina Astrogilda, 23");
        cliente.put("cpf", "");
        Cliente instance = new Cliente();
        try {
            instance.save(cliente);
            fail("Falha no save cliente, deveria dar exception por falta de cpf.");
        } catch (Exception e) {
            assertEquals("CPF deve estar preenchido.", e.getMessage());
        }
    }

    @Test
    public void testListar_String() {
        System.out.println("listar telefone");
        String telefone = "(19) 99312-1234";
        Cliente instance = new Cliente();
        List<Object> result = instance.listar(telefone);
        assertNotNull(result);
    }

    @Test
    public void testListar_0args() {
        System.out.println("listar");
        Cliente instance = new Cliente();
        List<Object> result = instance.listar();
        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        ClienteDao cdao = new ClienteDao();
        ArrayList<ClienteDto> cli = cdao.getByTelefone("(19) 99312-1234");
        if (cli.size() > 0) {
            Cliente instance = new Cliente();
            boolean expResult = true;
            boolean result = instance.delete(cli.get(0).getId());
            assertEquals(expResult, result);
        } else {
            fail("Falha ao deletar cliente.");
        }
    }
    
    @Test
    public void testDeleteErro() {
        System.out.println("delete com erro");
        int id = 234567654;
        Cliente instance = new Cliente();
        boolean expResult = false;
        boolean result = instance.delete(id);
        assertEquals(expResult, result);
    }
    
}
