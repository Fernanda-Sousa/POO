package fatec.poo.control;

import fatec.poo.model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernanda / Wildemar
 */
public class DaoPedido {
    private Connection conn;

    public DaoPedido(Connection conn) {
        this.conn = conn;
    }
    
    public void inserir(Pedido pedido) {
        PreparedStatement ps;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("INSERT INTO tbpedido(Numero_Ped, Cliente_Ped, Vendedor_Ped, "
                                        + "Status_Ped, DataEmissaoPedido_Ped, DataPagto_Ped) "
                                        + "VALUES(?,?,?,?,?,?)");
            ps.setInt(1, pedido.getNumero());
            ps.setString(2, pedido.getCliente().getCpf());
            ps.setString(3, pedido.getVendedor().getCpf());
            ps.setBoolean(4, pedido.isStatus());
            ps.setString(5, pedido.getDataEmissaoPedido());
            ps.setString(6, pedido.getDataPagto());
                                  
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    public  Pedido consultar (Integer Numero) {
        Pedido p = null;
       
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("SELECT * from tbpedido where Numero_Ped = ?");
            
            ps.setInt(1, Numero);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                p = new Pedido (Numero, rs.getString("DataEmissaoPedido_Ped"));
                p.setCliente(new DaoCliente(conn).consultar(rs.getString("Cliente_Ped")));
                p.setVendedor(new DaoVendedor(conn).consultar(rs.getString("Vendedor_Ped")));
                p.setStatus(rs.getBoolean("Status_Ped"));
                p.setDataPagto(rs.getString("DataPagto_Ped"));
                
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (p);
    }
    
    public void alterar(Pedido pedido) {
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas
            ps = conn.prepareStatement("UPDATE tbpedido set  DataEmissaoPedido_Ped = ? ," 
                                                + "Cliente_Ped = ?, Vendedor_Ped = ? ,"
                                                + "Status_Ped = ?, DataPagto_Ped = ? where Numero_Ped = ?");
            
            ps.setString(1, pedido.getDataEmissaoPedido());
            ps.setString(2, pedido.getCliente().getCpf());
            ps.setString(3, pedido.getVendedor().getCpf());
            ps.setBoolean(4, pedido.isStatus());
            ps.setString(5, pedido.getDataPagto());
            ps.setInt(6, pedido.getNumero());
                       
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void excluir(Pedido pedido) {
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("DELETE FROM tbpedido where Numero_Ped = ?");
            
            ps.setInt(1, pedido.getNumero());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}

