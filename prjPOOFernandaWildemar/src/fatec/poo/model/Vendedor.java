package fatec.poo.model;

import java.util.ArrayList;

/**
 *
 * @author FernandaPereiradosSa
 */
public class Vendedor extends Pessoa {

    private double salarioBase;
    private double comissao;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Vendedor(String cpf, String nome, double salarioBase) {
        super(cpf, nome);
        this.salarioBase = salarioBase;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double taxaComissao) {
        this.comissao = this.salarioBase * (taxaComissao / 100);  
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setVendedor(this);
    }

}
