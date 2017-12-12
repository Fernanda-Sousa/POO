package fatec.poo.control;

import fatec.poo.model.ItemPedido;
import fatec.poo.model.Pedido;
import fatec.poo.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoItemPedido {

    private Connection conn;

    public DaoItemPedido(Connection conn) {
        this.conn = conn;

    }

    public ArrayList<ItemPedido> listItensPedido(String numeroPedido) {
        ArrayList<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
        ItemPedido item = null;
        Pedido pedido = null;
        Produto produto = null;
        PreparedStatement ps = null;
        PreparedStatement psPedido = null;
        PreparedStatement psProduto = null;

        try {
            psPedido = conn.prepareStatement("SELECT * from tbPedido where Numero_Ped = ?");
            psPedido.setInt(1, Integer.parseInt(numeroPedido));
            ResultSet rsPedido = psPedido.executeQuery();
            if (rsPedido.next() == true) {
                pedido = new Pedido(rsPedido.getInt("NUMERO_PED"), rsPedido.getString("DATAEMISSAOPEDIDO_PED"));
                pedido.setCliente(null);
                pedido.setVendedor(null);
                if (rsPedido.getInt("STATUS_PED") == 1) {
                    pedido.setStatus(true);
                } else {
                    pedido.setStatus(false);
                }
            }

            ps = conn.prepareStatement("SELECT * from tbitempedido where "
                    + "pedido_itempedido = ? ");
            ps.setInt(1, Integer.parseInt(numeroPedido));
            ResultSet rs = ps.executeQuery();

            while (rs.next() == true) {
                item = new ItemPedido(pedido, rs.getInt("QTDEVENDIDA_ITEMPEDIDO"));
               
                psProduto = conn.prepareStatement("SELECT * from tbProduto where CODIGO_PROD = ?");
                psProduto.setInt(1, rs.getInt("PRODUTO_ITEMPEDIDO"));
                ResultSet rsProduto = psProduto.executeQuery();
                if (rsProduto.next()) {
                    produto = new Produto(rsProduto.getInt("CODIGO_PROD"), rsProduto.getString("DESCRICAO_PROD"));
                    produto.setEstoqueMin(rsProduto.getInt("ESTOQUEMIN_PROD"));
                    produto.setPrecoUnit(rsProduto.getDouble("PRECOUNIT_PROD"));
                    produto.setQtdeDisponivel(rsProduto.getInt("QTDEDISPONIVEL_PROD"));
                }
                item.setProduto(produto);
                itensPedido.add(item);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return itensPedido;
    }

    public void inserirItemPedido(ArrayList<ItemPedido> listaItem) {
        PreparedStatement ps = null;
        ItemPedido item = null;

        try {
            int i = 0;
            while (i < listaItem.size()) {
                item = listaItem.get(i);
               ps = conn.prepareStatement("INSERT INTO tbitempedido(PEDIDO_ITEMPEDIDO,PRODUTO_ITEMPEDIDO, QTDEVENDIDA_ITEMPEDIDO ) VALUES(?,?,?)");
           

                ps.setInt(1, item.getPedido().getNumero());
                ps.setInt(2, item.getProduto().getCodigo());
                ps.setInt(3, item.getQtdeVendida());
                ps.execute();
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    public void alterarPedido(ArrayList<ItemPedido> listaItem, int codigoPedido) {
        PreparedStatement ps = null;
        PreparedStatement ds = null;
        ItemPedido item = null;

        try {
            ds = conn.prepareStatement("DELETE FROM tbItemPedido where PEDIDO_ITEMPEDIDO = ?");
            ds.setInt(1, codigoPedido);
            ds.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        try {
            int i = 0;
            while (i < listaItem.size()) {
                item = listaItem.get(i);
                ps = conn.prepareStatement("INSERT INTO tbItemPedido(PEDIDO_ITEMPEDIDO,PRODUTO_ITEMPEDIDO,QTDEVENDIDA_ITEMPEDIDO) values"
                        + "(?,?,?)");

                ps.setInt(1, item.getPedido().getNumero());
                ps.setInt(2, item.getProduto().getCodigo());
                ps.setInt(3, item.getQtdeVendida());
                ps.execute();
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

}
