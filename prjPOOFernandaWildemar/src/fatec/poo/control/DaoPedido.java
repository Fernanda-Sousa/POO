package fatec.poo.control;

import fatec.poo.model.Cliente;
import fatec.poo.model.Pedido;
import fatec.poo.model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DaoPedido {

    private Connection conn;

    public DaoPedido(Connection conn) {
        this.conn = conn;
    }

    public Pedido consultar(int PED_NUMERO) {
        Pedido pedido = null;
        Cliente c = null;
        Vendedor v = null;

        PreparedStatement ps = null;
        PreparedStatement pv = null;
        PreparedStatement pc = null;

        try {

            ps = conn.prepareStatement("SELECT * from tbpedido where Numero_Ped = ?");

            ps.setInt(1, PED_NUMERO);
            ResultSet rst = ps.executeQuery();

            if (rst.next() == true) {

                pedido = new Pedido(rst.getInt("Numero_Ped"), rst.getString("DataEmissaoPedido_Ped"));
                String CPF = rst.getString("Cliente_Ped");
                String VCPF = rst.getString("Vendedor_Ped");

                pv = conn.prepareStatement("SELECT * from tbCliente where " + "cpf_cli = ?");

                pv.setString(1, CPF);
                ResultSet rs = pv.executeQuery();

                if (rs.next() == true) {
                    c = new Cliente(CPF, rs.getString("nome_cli"), rs.getDouble("LIMITECRED_CLI"));
                    c.setCep(rs.getString("CEP_CLI"));
                    c.setCidade(rs.getString("CIDADE_CLI"));
                    c.setDdd(rs.getString("DDD_CLI"));
                    c.setEndereco(rs.getString("ENDERECO_CLI"));
                    c.setUf(rs.getString("UF_CLI"));
                    c.setTelefone(rs.getString("TELEFONE_CLI"));
                    c.setLimiteDisp(rs.getDouble("LIMITEDISP_CLI"));
                }

                pc = conn.prepareStatement("SELECT * from tbVENDEDOR where " + "CPF_VEND = ?");

                pc.setString(1, VCPF);
                ResultSet rvs = pc.executeQuery();

                if (rvs.next() == true) {
                    v = new Vendedor(VCPF, rvs.getString("NOME_VEND"), rvs.getDouble("SALARIOBASE_VEND"));
                    v.setCep(rvs.getString("CEP_VEND"));
                    v.setCidade(rvs.getString("CIDADE_VEND"));
                    v.setDdd(rvs.getString("DDD_VEND"));
                    v.setEndereco(rvs.getString("ENDERECO_VEND"));
                    v.setUf(rvs.getString("UF_VEND"));
                    v.setTelefone(rvs.getString("TELEFONE_VEND"));
                    v.setComissao(rvs.getDouble("COMISSAO_VEND"));
                }
                    
                pedido.setCliente(c);
                pedido.setVendedor(v);

            } else {
                pedido = null;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar banco!" + ex.toString());
        }

        return (pedido);
    }

    public void inserir(Pedido pedido) {
        PreparedStatement ps = null;
        int status = 1;

        if (pedido.getStatus()) {
            status = 1;
        } else {
            status = 2;
        }

        try {
            ps = conn.prepareStatement("INSERT INTO tbpedido(Numero_Ped, Cliente_Ped, Vendedor_Ped, "
                                        + "Status_Ped, DataEmissaoPedido_Ped, DataPagto_Ped) "
                                        + "VALUES(?,?,?,?,?,?)");
            ps.setInt(1, pedido.getNumero());
            ps.setString(2, pedido.getCliente().getCpf());
            ps.setString(3, pedido.getVendedor().getCpf());
            ps.setInt(4, status);
            ps.setString(5, pedido.getDataEmissaoPedido());
            ps.setString(6, pedido.getDataPagto());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString() + "b");
        }
    }

    public void alterar(Pedido pedido) {
        PreparedStatement ps = null;
        int status = 0;

        if (pedido.getStatus()) {
            status = 1;
        } else {
            status = 0;
        }

        try {
            ps = conn.prepareStatement("UPDATE tbpedido set  DataEmissaoPedido_Ped = ? ," 
                                                + "Cliente_Ped = ?, Vendedor_Ped = ? ,"
                                                + "Status_Ped = ?, DataPagto_Ped = ? where Numero_Ped = ?");

            ps.setString(1, pedido.getDataEmissaoPedido());
            ps.setString(2, pedido.getCliente().getCpf());
            ps.setString(3, pedido.getVendedor().getCpf());
            ps.setInt(4, status);
            ps.setString(5, pedido.getDataPagto());
            ps.setInt(6, pedido.getNumero());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString() + "x");
        }
    }

    public void excluir(Pedido pedido) {

        PreparedStatement ps = null;
        PreparedStatement pr = null;
        try {
            pr = conn.prepareStatement("DELETE FROM TBItemPedido where PEDIDO_ITEMPEDIDO = ?");
            pr.setInt(1, pedido.getNumero());
            pr.execute();

            ps = conn.prepareStatement("DELETE FROM tbPedido where Numero_Ped = ?");
            ps.setInt(1, pedido.getNumero());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.toString() + "1");
        }

    }

    public ArrayList<Pedido> listPedido() {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido pedido = null;
        PreparedStatement ps = null;
        PreparedStatement vs = null;
        PreparedStatement cs = null;
        Cliente c = null;
        Vendedor v = null;

        try {
            ps = conn.prepareStatement("SELECT * from tbPedido Order by Numero_Ped");
            ResultSet rs = ps.executeQuery();

            while (rs.next() == true) {
                pedido = new Pedido(rs.getInt("Numero_Ped"), rs.getString("DataEmissaoPedido_Ped"));
                cs = conn.prepareStatement("SELECT * from tbCliente where "
                        + "Cpf_Cli = ?");
                cs.setString(1, rs.getString("Cpf_Cli"));
                ResultSet rcs = cs.executeQuery();
                if (rcs.next() == true) {
                    c = new Cliente(rcs.getString("Cpf_Cli"), rcs.getString("Nome_Cli"), rcs.getDouble("LimiteCred_Cli"));
                    c.setCep(rcs.getString("Cep_Cli"));
                    c.setCidade(rcs.getString("Cidade_Cli"));
                    c.setDdd(rcs.getString("Ddd_Cli"));
                    c.setEndereco(rcs.getString("Endereco_Cli"));
                    c.setLimiteDisp(rcs.getDouble("LimiteDisp_Cli"));
                    c.setTelefone(rcs.getString("Telefone_Cli"));
                    c.setUf(rcs.getString("UF_CLI"));
                }

                vs = conn.prepareStatement("SELECT * from tbVendedor where "
                        + "Cpf_Vend = ?");
                vs.setString(1, rs.getString("Cpf_Vend"));
                ResultSet rvs = vs.executeQuery();
                
                if (rvs.next() == true) {
                    v = new Vendedor(rvs.getString("Cpf_Vend"), rvs.getString("NOME_VEND"), rvs.getDouble("SalarioBase_Vend"));
                    v.setCep(rvs.getString("Cep_Vend"));
                    v.setCidade(rvs.getString("Cidade_Vend"));
                    v.setDdd(rvs.getString("Ddd_Vend"));
                    v.setEndereco(rvs.getString("Endereco_Vend"));
                    v.setComissao(rvs.getDouble("Comissao_Vend"));
                    v.setTelefone(rvs.getString("Telefone_Vend"));
                    v.setUf(rvs.getString("Uf_Vend"));

                }
                pedido.setCliente(c);
                pedido.setVendedor(v);
                if (rs.getInt("status_ped") == 1) {
                    pedido.setStatus(true);
                } else {
                    pedido.setStatus(false);
                }
                pedido.setDataPagto(rs.getString("datapagto_ped"));
                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString() + "2");
        }
        return pedidos;
    }

    public void atualizaDataPagamento(Pedido pedido) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("Update tbPedido set DATAPAGTO_PED_ = ?, PED_STATUS = ? where Numero_Ped = ?");
            ps.setString(1, pedido.getDataPagto());
            ps.setString(2, "1");
            ps.setString(3, Integer.toString(pedido.getNumero()));
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.toString() + "3");
        }
    }

}
