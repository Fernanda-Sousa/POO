
package fatec.poo.model;

import java.util.ArrayList;

/**
 *
 * @author FernandaPereiradosSa
 */
public class Pedido {
    private int numero;
    private String dataEmissaoPedido;
    private String dataPagto;
    private boolean status;
    private Cliente cliente;
    private Vendedor vendedor;
    private ArrayList<ItemPedido> itensPedidos = new ArrayList<>();
   
    public Pedido(int numero, String dataEmissaoPedido) {
        this.numero = numero;
        this.dataEmissaoPedido = dataEmissaoPedido;
    }

    public String getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(String dataPagto) {
        this.dataPagto = dataPagto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public String getDataEmissaoPedido() {
        return dataEmissaoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Vendedor getVendedor() {
    return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void addItemPedido(ItemPedido itemPedido){
        itensPedidos.add(itemPedido);
        itemPedido.setPedido(this);
//        double limite = cliente.getLimiteDisp() - ((itemPedido.getProduto().getPrecoUnit()) 
//                                                    * (itemPedido.getQtdeVendida()));
        cliente.setLimiteDisp(cliente.getLimiteDisp() - ((itemPedido.getProduto().getPrecoUnit()) 
                                                    * (itemPedido.getQtdeVendida())));
    }
}
