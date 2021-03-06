package fatec.poo.control;

import fatec.poo.model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernanda // Wildemar
 */
public class DaoVendedor {

    private Connection conn;

    public DaoVendedor(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO tbvendedor(Cpf_Vend, Nome_Vend, Endereco_Vend, Cidade_Vend, Cep_Vend, Uf_Vend, Ddd_Vend, Telefone_Vend, SalarioBase_Vend, Comissao_Vend) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, vendedor.getCpf());
            ps.setString(2, vendedor.getNome());
            ps.setString(3, vendedor.getEndereco());
            ps.setString(4, vendedor.getCidade());
            ps.setString(5, vendedor.getCep());
            ps.setString(6, vendedor.getUf());
            ps.setString(7, vendedor.getDdd());
            ps.setString(8, vendedor.getTelefone());
            ps.setDouble(9, vendedor.getSalarioBase());
            ps.setDouble(10, vendedor.getComissao());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void alterar(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE tbvendedor set Nome_Vend = ?, Endereco_Vend = ?, Cidade_Vend = ?, Cep_Vend = ?, Uf_Vend = ?, Ddd_Vend = ?, Telefone_Vend = ?, SalarioBase_Vend = ?, Comissao_Vend = ? "
                    + "where Cpf_Vend = ?");

            ps.setString(1, vendedor.getNome());
            ps.setString(2, vendedor.getEndereco());
            ps.setString(3, vendedor.getCidade());
            ps.setString(4, vendedor.getCep());
            ps.setString(5, vendedor.getUf());
            ps.setString(6, vendedor.getDdd());
            ps.setString(7, vendedor.getTelefone());
            ps.setDouble(8, vendedor.getSalarioBase());
            ps.setDouble(9, vendedor.getComissao());
            ps.setString(10, vendedor.getCpf());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public Vendedor consultar(String cpf) {
        Vendedor v = null;

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * from tbvendedor where "
                    + "Cpf_Vend = ?");

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next() == true) {
                v = new Vendedor(cpf, rs.getString("Nome_Vend"), rs.getDouble("SalarioBase_Vend"));
                v.setCidade(rs.getString("Cidade_Vend"));
                v.setDdd(rs.getString("Ddd_Vend"));
                v.setTelefone(rs.getString("Telefone_Vend"));
                v.setUf(rs.getString("Uf_Vend"));
                v.setCep(rs.getString("Cep_Vend"));
                v.setComissao(rs.getDouble("Comissao_Vend"));
                v.setEndereco(rs.getString("Endereco_Vend"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return (v);
    }

    public void excluir(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM tbvendedor where Cpf_Vend = ?");

            ps.setString(1, vendedor.getCpf());

            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
