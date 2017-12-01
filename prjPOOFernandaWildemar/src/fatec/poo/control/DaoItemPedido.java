package fatec.poo.control;

import fatec.poo.model.ItemPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fernanda / Wildemar
 */
public class DaoItemPedido {
    private Connection conn;

    public DaoItemPedido(Connection conn) {
        this.conn = conn;
    }
    
    public void inserir(ItemPedido itemPedido) {
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("INSERT INTO ItemPedido(NrItem, Numero_Pedido, Codigo_Produto, Qt_Vendida) VALUES(?,?,?,?)");
            
            ps.setInt(1, itemPedido.getNumeroItem());
            ps.setInt(2, itemPedido.getPedido().getNumero());
            ps.setInt(3, itemPedido.getProduto().getCodigo());
            ps.setInt(4, itemPedido.getQtdeVendida());
            
            
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public ArrayList<ItemPedido> consultar(Integer Numero){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<ItemPedido> itensPedidos = new ArrayList<>();
        
        try{
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("Select * from ItemPedido where Numero_Pedido = ?");
            ps.setInt(1, Numero);
            rs = ps.executeQuery();
            
            while(rs.next()){
                ItemPedido itemPedido = new ItemPedido(rs.getInt("NrItem"), rs.getInt("Qt_Vendida"));
                itemPedido.setProduto(new DaoProduto(conn).consultar(rs.getInt("Codigo_Produto")));
                itemPedido.setPedido(new DaoPedido(conn).consultar(Numero));
                itensPedidos.add(itemPedido);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
        return (itensPedidos);
    }
    
    public ArrayList<ItemPedido> excluirItem(Integer Numero, Integer Codigo){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<ItemPedido> itensPedidos = new ArrayList<>();
        
        try{
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("delete from ItemPedido where Numero_Pedido = ? and Codigo_Produto = ?");
            ps.setInt(1, Numero);
            ps.setInt(2, Codigo);
            
            ps.executeQuery();
  
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
        return (itensPedidos);
    }
    
    public ArrayList<ItemPedido> consultarItem(Integer Numero, Integer Codigo_Produto){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<ItemPedido> itensPedidos = new ArrayList<>();
        
        try{
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("select * from ItemPedido where Numero_Pedido = ? and Codigo_Produto = ?");
            ps.setInt(1, Numero);
            ps.setInt(2, Codigo_Produto);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                ItemPedido itemPedido = new ItemPedido(rs.getInt("NrItem"), rs.getInt("Qt_Vendida"));
                itemPedido.setProduto(new DaoProduto(conn).consultar(rs.getInt("Codigo_Produto")));
                itemPedido.setPedido(new DaoPedido(conn).consultar(Numero));
                itensPedidos.add(itemPedido);
            }
  
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
        return (itensPedidos);
    }
    
    public void alterar(ItemPedido itemPedido) {
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("UPDATE ItemPedido set  Codigo_Produto = ? ," 
                                                + "Qt_Vendida = ?, nritem =? where Numero_Pedido = ?");
            
            ps.setInt(1, itemPedido.getProduto().getCodigo());
            ps.setInt(2, itemPedido.getQtdeVendida());
            ps.setInt(3, itemPedido.getNumeroItem());
            ps.setInt(4, itemPedido.getPedido().getNumero());
                       
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void excluir(ItemPedido itemPedido) {
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("DELETE FROM ItemPedido where Numero_Pedido = ?");
            
            ps.setInt(1, itemPedido.getPedido().getNumero());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}

