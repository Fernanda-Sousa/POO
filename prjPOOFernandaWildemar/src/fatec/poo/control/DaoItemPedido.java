package fatec.poo.control;

import fatec.poo.model.ItemPedido;
import fatec.poo.model.Pedido;
import fatec.poo.model.Produto;
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

    public ArrayList<ItemPedido> consultar(int Numero) {
        ArrayList<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
        ItemPedido item = null;
        Pedido pedido = null;
        Produto produto = null;
        PreparedStatement ps = null;
        PreparedStatement psPedido = null;
        PreparedStatement psProduto = null;

        try {
            psPedido = conn.prepareStatement("SELECT * from tbpedido where numero_ped = ?");
            psPedido.setInt(1, Numero);
            ResultSet rsPedido = psPedido.executeQuery();
            if (rsPedido.next() == true) {
                pedido = new Pedido(rsPedido.getInt("PED_NUMERO"), rsPedido.getString("PED_DATA"));
                pedido.setCliente(null);
                pedido.setVendedor(null);
                if (rsPedido.getInt("PED_STATUS") == 1) {
                    pedido.setStatus(true);
                } else {
                    pedido.setStatus(false);
                }
            }

            ps = conn.prepareStatement("SELECT * from tbItemPedido where "
                    + "numero_ped = ? ");
            ps.setInt(1, Numero);
            ResultSet rs = ps.executeQuery();

            while (rs.next() == true) {
                item = new ItemPedido(rs.getInt("produto_itempedido"), rs.getInt("qtdevendida_itemproduto"));
                item.setPedido(pedido);
                psProduto = conn.prepareStatement("SELECT * from tbProduto where codigo_prod = ?");
                psProduto.setInt(1, rs.getInt("produto_itempedido"));
                ResultSet rsProduto = psProduto.executeQuery();
                if (rsProduto.next() == true) {
                    produto = new Produto(rsProduto.getInt("codigo_prod"), rsProduto.getString("descricao_prod"));
                    produto.setEstoqueMin(rsProduto.getInt("estoquemin_prod"));
                    produto.setPrecoUnit(rsProduto.getDouble("precounit_prod"));
                    produto.setQtdeDisponivel(rsProduto.getInt("qtdedisponivel_prod"));
                }
                item.setProduto(produto);
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
