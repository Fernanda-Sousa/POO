
package fatec.poo.model;

/**
 *
 * @author FernandaPereiradosSa
 */
public class ItemPedido {
    private int numeroItem;
    private int qtdeVendida;
    private Pedido pedido;
    private Produto produto;

    public ItemPedido(int numeroItem, int qtdeVendida) {
        this.numeroItem = numeroItem;
        this.qtdeVendida = qtdeVendida;
    }

    public int getQtdeVendida() {
        return qtdeVendida;
    }

    public void setQtdeVendida(int qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
        produto.setQtdeDisponivel(produto.getQtdeDisponivel() - qtdeVendida);
    }

    public int getNumeroItem() {
        return numeroItem;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        
    }
    
    
}
