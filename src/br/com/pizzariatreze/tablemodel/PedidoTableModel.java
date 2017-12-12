package br.com.pizzariatreze.tablemodel;

import br.com.pizzariatreze.dto.ProdutoDto;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class PedidoTableModel extends AbstractTableModel {
    /* Lista para armazenar os cabeçalhos da tabela */

    private Vector colunas;

    /* Lista para armazenar os dados da tabela */
    private Vector linhas;

    public PedidoTableModel() {
        /* Definição das colunas da tabela */
        colunas = new Vector();
        colunas.add("ID");
        colunas.add("Produto");
        colunas.add("Qtd");
        colunas.add("Subtotal");
        
        /* Definição dos dados da tabela */
        linhas = new Vector();
    }

    public int getRowCount() {
        /* Captura o total de linhas da tabela */
        int totalLinhas = linhas.size();

        /* Retorna o total de linhas */
        return totalLinhas;
    }

    public int getColumnCount() {
        /* Captura o total de colunas da tabela */
        int totalColunas = colunas.size();

        /* Retorna o total de colunas */
        return totalColunas;
    }

    public String getColumnName(int coluna) {
        /* Captura o nome da coluna */
        String nomeColuna = (String) colunas.get(coluna);

        /* Retorna o nome da coluna */
        return nomeColuna;
    }

    public Object getValueAt(int linha, int coluna) {
        /* Captura o registro informado */
        Vector registro = (Vector) linhas.get(linha);

        /* Dentro do registro captura a coluna selecionada */
        Object dado = registro.get(coluna);

        /* Retorna o valor capturado */
        return dado;
    }
    
    public void setValueAt(Object value, int row, int column) {
        int valor=0;
        try{
            if(column == 2){
                value = Integer.parseInt(((String)value).trim());
            }
  
            ((Vector) linhas.elementAt(row)).set(column, value);
        }catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        
        fireTableCellUpdated(row, column);
    }
    
    public boolean isCellEditable(int row, int column) {
        return (column == 2);
    }
    
    public void adicionar(List<Object> lista) {
        /* Reinicializa os dados da tabela */
        linhas = new Vector();

        /* Percorre a lista copiando os dados para a tabela */
        for (Object d : lista) {
            ProdutoDto produto = new ProdutoDto();
            produto = (ProdutoDto) d;
            /* Cria uma linha da tabela */
            Vector<Object> linha = new Vector();
            linha.add(produto.getId());
            linha.add(produto.getNome());
            linha.add("1");
            linha.add(produto.getPreco());
            /* Adiciona a linha a tabela */
            linhas.add(linha);
        }

        /* Atualiza a tabela */
        fireTableDataChanged();
    }
    
    public void adicionarLinha(Object p){
        if(p != null){
            
            Vector<Object> linha = new Vector();
            ProdutoDto produto = (ProdutoDto) p;
            linha.add(produto.getId());
            linha.add(produto.getNome());
            linha.add(1);
            linha.add(produto.getPreco());
            /* Adiciona a linha a tabela */
            linhas.add(linha);

            /* Atualiza a tabela */
            fireTableDataChanged();
        }
    }

}
