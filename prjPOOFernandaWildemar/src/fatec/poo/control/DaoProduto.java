package fatec.poo.control;

import fatec.poo.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernanda // Wildemar
 */
public class DaoProduto {
    private Connection conn;
    
    public DaoProduto(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO tbproduto(Codigo_Prod, Descricao_Prod, QtdeDisponivel_Prod, PrecoUnit_Prod, EstoqueMin_Prod) VALUES(?,?,?,?,?)");
            ps.setInt(1, produto.getCodigo());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getQtdeDisponivel());
            ps.setDouble(4, produto.getPrecoUnit());
            ps.setInt(5, produto.getEstoqueMin());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE tbproduto set Descricao_Prod = ?, QtdeDisponivel_Prod = ?, PrecoUnit_Prod = ?, EstoqueMin_Prod = ? " 
                                        + "where Codigo_Prod = ?");
            
            ps.setString(1, produto.getDescricao());
            ps.setInt(2, produto.getQtdeDisponivel());
            ps.setDouble(3, produto.getPrecoUnit());
            ps.setInt(4, produto.getEstoqueMin());
            ps.setInt(5, produto.getCodigo());
           
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }

    public  Produto consultar (int codigo) {
        Produto p = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * from tbproduto where " +
                                                 "Codigo_Prod = ?");
            
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                p = new Produto (codigo, rs.getString("Descricao_Prod"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (p);
    }
    
     public void excluir(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM tbproduto where Codigo_Prod = ?");
            
            ps.setInt(1, produto.getCodigo());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}
