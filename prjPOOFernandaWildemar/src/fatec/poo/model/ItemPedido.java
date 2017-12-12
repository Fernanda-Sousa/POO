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

 
      public ItemPedido(Pedido pedido, int qtdeVendida) {
        this.pedido = pedido;
        this.qtdeVendida = qtdeVendida;
    }
      
    public int getNumeroItem() {
        return numeroItem;
    }

    public int getQtdeVendida() {
        return qtdeVendida;
    }

    public void setQtdeVendida(int qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
        produto.setQtdeDisponivel(produto.getQtdeDisponivel() - qtdeVendida);
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public double getPrecoUnitProduto(){
        return produto.getPrecoUnit();
    }
    public String getDescricao(){
        return produto.getDescricao();
    } 

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setNumeroItem(int numeroItem) {
        this.numeroItem = numeroItem;
    }
    
    
}
    
    

