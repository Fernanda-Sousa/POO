package fatec.poo.control;

import fatec.poo.model.ItemPedido;
import fatec.poo.model.Pedido;
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

    public ArrayList<ItemPedido> consultar(Pedido pedido) { 
      
        
        ArrayList<ItemPedido> itensPedido = new ArrayList<>();

        try {
            
            PreparedStatement ps = conn.prepareStatement("SELECT * from tbItemPedido where "
                    + "PEDIDO_ITEMPEDIDO = ? ");
            ps.setInt(1, pedido.getNumero());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido(rs.getInt("PRODUTO_ITEMPEDIDO"), rs.getInt("QTDEVENDIDA_ITEMPEDIDO"));
                item.setPedido(pedido);
               
                System.err.println(rs.getInt("PRODUTO_ITEMPEDIDO"));
                
                itensPedido.add(item);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return itensPedido;
    }

    public ArrayList<ItemPedido> excluirItem(Integer Numero, Integer Codigo) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<ItemPedido> itensPedidos = new ArrayList<>();

        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("delete from tbitempedido where Pedido_ItemPedido = ? and Produto_ItemPedido = ?");
            ps.setInt(1, Numero);
            ps.setInt(2, Codigo);

            ps.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return (itensPedidos);
    }

    public ArrayList<ItemPedido> consultarItem(Integer Numero, Integer Codigo_Produto) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<ItemPedido> itensPedidos = new ArrayList<>();

        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("select * from tbitempedido where Pedido_ItemPedido = ? and Produto_ItemPedido = ?");
            ps.setInt(1, Numero);
            ps.setInt(2, Codigo_Produto);

            rs = ps.executeQuery();

            if (rs.next()) {
                ItemPedido itemPedido = new ItemPedido(rs.getInt("numeroItem"), rs.getInt("qtdeVendida"));
                itemPedido.setProduto(new DaoProduto(conn).consultar(rs.getInt("codigo")));
                itemPedido.setPedido(new DaoPedido(conn).consultar(Numero));
                itensPedidos.add(itemPedido);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return (itensPedidos);
    }

    public void alterar(ItemPedido itemPedido) {
        PreparedStatement ps = null;
        try {
            //TODO verificar nomes nas tabelas e colunas / colocar no mesmo padrão
            ps = conn.prepareStatement("UPDATE tbitempedido set  Produto_ItemPedido = ? ,"
                    + "QtdeVendida = ?, NumItem_ItemPedido =? where Pedido_ItemPedido = ?");

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
            ps = conn.prepareStatement("DELETE FROM tbitempedido where Pedido_ItemPedido = ?");

            ps.setInt(1, itemPedido.getPedido().getNumero());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void inserirItemPedido(ArrayList<ItemPedido> itensPedido, int pedido) {
        PreparedStatement ps;

        try {
            for (ItemPedido ip : itensPedido) {

                ps = conn.prepareStatement("INSERT INTO tbitemPedido(Pedido_ItemPedido,Produto_ItemPedido, QtdeVendida_ItemPedido) VALUES(?,?,?)");
                System.out.println("PEDIDO: " + pedido);
                System.out.println("NUMERO DO ITEM: " + ip.getProduto().getCodigo());
                System.out.println("QUANTIDADE: " + ip.getQtdeVendida());

                ps.setInt(1, pedido);
                ps.setInt(2, ip.getProduto().getCodigo());
                ps.setInt(3, ip.getQtdeVendida());
                ps.execute();

            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
