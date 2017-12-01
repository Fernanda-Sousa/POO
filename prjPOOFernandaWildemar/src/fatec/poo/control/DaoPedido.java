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
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("INSERT INTO Pedido(Numero, CPF_Cliente, CPF_Vendedor, "
                                        + "Status, Data_pedido, Data_pagto) "
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
            ps = conn.prepareStatement("SELECT * from Pedido where Numero = ?");
            
            ps.setInt(1, Numero);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                p = new Pedido (Numero, rs.getString("Data_pedido"));
                p.setCliente(new DaoCliente(conn).consultar(rs.getString("CPF_cliente")));
                p.setVendedor(new DaoVendedor(conn).consultar(rs.getString("CPF_Vendedor")));
                p.setStatus(rs.getBoolean("Status"));
                p.setDataPagto(rs.getString("Data_pagto"));
                
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
            ps = conn.prepareStatement("UPDATE Pedido set  Data_Pedido = ? ," 
                                                + "CPF_Cliente = ?, CPF_Vendedor = ? ,"
                                                + "Status = ?, Data_pagto = ? where Numero = ?");
            
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
            ps = conn.prepareStatement("DELETE FROM Pedido where Numero = ?");
            
            ps.setInt(1, pedido.getNumero());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}

