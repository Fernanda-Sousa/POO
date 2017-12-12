package fatec.poo.view;

import fatec.poo.control.Conexao;
import fatec.poo.control.DaoCliente;
import fatec.poo.model.Pedido;
import fatec.poo.control.DaoItemPedido;
import fatec.poo.control.DaoPedido;
import fatec.poo.control.DaoProduto;
import fatec.poo.control.DaoVendedor;
import fatec.poo.model.Cliente;
import fatec.poo.model.ItemPedido;
import fatec.poo.model.Produto;
import fatec.poo.model.Vendedor;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GUIEmitirPedido extends javax.swing.JFrame {

    public boolean verificaData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            java.util.Date date = sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void atualizaLista() {
        modTbItens.setRowCount(0);
        if (itensPedido != null) {
            double valorTotal = 0;
            int quantidePedido = 0;
            int i = 1;
            for (ItemPedido itensPedido1 : itensPedido) {
                valorTotal = valorTotal + (itensPedido1.getProduto().getPrecoUnit() * itensPedido1.getQtdeVendida());
                quantidePedido = quantidePedido + itensPedido1.getQtdeVendida();
                String[] Linha = {Integer.toString(i), itensPedido1.getProduto().getDescricao(),
                    Double.toString(itensPedido1.getProduto().getPrecoUnit()),
                    Integer.toString(itensPedido1.getQtdeVendida()),
                    Double.toString((itensPedido1.getQtdeVendida() * itensPedido1.getProduto().getPrecoUnit()))
                };
                modTbItens.addRow(Linha);
                i++;
                //Integer.toString(itensPedido1.getCodigo())
            }
            txtValorTotal.setText(Double.toString(valorTotal));
            txtQuantidadeItensPed.setText(Integer.toString(quantidePedido));
        }
    }

    public GUIEmitirPedido() {
        initComponents();
        modTbItens = (DefaultTableModel) tblItensPedido.getModel();
    }

    private void startconnection() {
      //        conexao = new Conexao("BD1513015","BD1513015");
//        conexao.setDriver("oracle.jdbc.driver.OracleDriver");
//        conexao.setConnectionString("jdbc:oracle:thin:@apolo:1521:xe");
/*TODO Trocar antes de entregar*/

        conexao = new Conexao("system", "rcl1230");
        conexao.setDriver("oracle.jdbc.driver.OracleDriver");
        conexao.setConnectionString("jdbc:oracle:thin:@localhost:1521:xe");

        daoPedido = new DaoPedido(conexao.conectar());
        daoCliente = new DaoCliente(conexao.conectar());
        daoVendedor = new DaoVendedor(conexao.conectar());
        daoProduto = new DaoProduto(conexao.conectar());
        daoItemPedido = new DaoItemPedido(conexao.conectar());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPedido = new javax.swing.JPanel();
        lblNumeroPedido = new javax.swing.JLabel();
        txtNumPedido = new javax.swing.JTextField();
        btnPedPesquisar = new javax.swing.JButton();
        lblDataPedido = new javax.swing.JLabel();
        ftfData = new javax.swing.JFormattedTextField();
        pnlPedido1 = new javax.swing.JPanel();
        lblCPF = new javax.swing.JLabel();
        ftfCPFCliente = new javax.swing.JFormattedTextField();
        txtNomeCliente = new javax.swing.JTextField();
        btnCliPesquisar = new javax.swing.JButton();
        pnlPedido2 = new javax.swing.JPanel();
        lblCPFVendedor = new javax.swing.JLabel();
        ftfCPFVendedor = new javax.swing.JFormattedTextField();
        txtNomeVendedor = new javax.swing.JTextField();
        btnVendPesquisar = new javax.swing.JButton();
        pnlPedido3 = new javax.swing.JPanel();
        lblCodigoProduto = new javax.swing.JLabel();
        txtProduto = new javax.swing.JTextField();
        btnProdPesquisar = new javax.swing.JButton();
        txtCodigoProd = new javax.swing.JTextField();
        txtQuantidadeVendida = new javax.swing.JTextField();
        lblQuantidadeVendida = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItensPedido = new javax.swing.JTable();
        lblQuantidadeVendida1 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        lblQuantidadeVendida2 = new javax.swing.JLabel();
        txtQuantidadeItensPed = new javax.swing.JTextField();
        btnSair = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Emitir Pedido");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlPedido.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedido"));
        pnlPedido.setToolTipText("Pedido");

        lblNumeroPedido.setText("Numero do Pedido");

        txtNumPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumPedidoActionPerformed(evt);
            }
        });
        txtNumPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumPedidoKeyTyped(evt);
            }
        });

        btnPedPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/pesq.png"))); // NOI18N
        btnPedPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedPesquisarActionPerformed(evt);
            }
        });

        lblDataPedido.setText("Data do Pedido");

        try {
            ftfData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftfData.setEnabled(false);

        javax.swing.GroupLayout pnlPedidoLayout = new javax.swing.GroupLayout(pnlPedido);
        pnlPedido.setLayout(pnlPedidoLayout);
        pnlPedidoLayout.setHorizontalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNumeroPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPedPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDataPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        pnlPedidoLayout.setVerticalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoLayout.createSequentialGroup()
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataPedido)
                        .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumeroPedido)
                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPedPesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPedido1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Cliente"));
        pnlPedido1.setToolTipText("");

        lblCPF.setText("CPF Cliente");

        try {
            ftfCPFCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfCPFCliente.setEnabled(false);

        txtNomeCliente.setEnabled(false);

        btnCliPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/pesq.png"))); // NOI18N
        btnCliPesquisar.setEnabled(false);
        btnCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPedido1Layout = new javax.swing.GroupLayout(pnlPedido1);
        pnlPedido1.setLayout(pnlPedido1Layout);
        pnlPedido1Layout.setHorizontalGroup(
            pnlPedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedido1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCPF)
                .addGap(18, 18, 18)
                .addComponent(ftfCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNomeCliente)
                .addContainerGap())
        );
        pnlPedido1Layout.setVerticalGroup(
            pnlPedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedido1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliPesquisar)
                    .addGroup(pnlPedido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCPF)
                        .addComponent(ftfCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );

        pnlPedido2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Vendedor"));
        pnlPedido2.setToolTipText("");

        lblCPFVendedor.setText("CPF Vendedor");

        try {
            ftfCPFVendedor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfCPFVendedor.setEnabled(false);

        txtNomeVendedor.setEnabled(false);

        btnVendPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/pesq.png"))); // NOI18N
        btnVendPesquisar.setEnabled(false);
        btnVendPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPedido2Layout = new javax.swing.GroupLayout(pnlPedido2);
        pnlPedido2.setLayout(pnlPedido2Layout);
        pnlPedido2Layout.setHorizontalGroup(
            pnlPedido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedido2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCPFVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfCPFVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVendPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNomeVendedor)
                .addContainerGap())
        );
        pnlPedido2Layout.setVerticalGroup(
            pnlPedido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedido2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPedido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVendPesquisar)
                    .addGroup(pnlPedido2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCPFVendedor)
                        .addComponent(ftfCPFVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNomeVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        pnlPedido3.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens do Pedido"));
        pnlPedido3.setToolTipText("");

        lblCodigoProduto.setText("Código do Produto");

        txtProduto.setEnabled(false);

        btnProdPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/pesq.png"))); // NOI18N
        btnProdPesquisar.setEnabled(false);
        btnProdPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdPesquisarActionPerformed(evt);
            }
        });

        txtCodigoProd.setEnabled(false);

        txtQuantidadeVendida.setEnabled(false);
        txtQuantidadeVendida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeVendidaActionPerformed(evt);
            }
        });

        lblQuantidadeVendida.setText("Qtde. Vendida");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/add.png"))); // NOI18N
        btnAdicionar.setText("Adicionar Item");
        btnAdicionar.setEnabled(false);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/rem.png"))); // NOI18N
        btnRemover.setText("Remover Item");
        btnRemover.setEnabled(false);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        tblItensPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Prec. Unit.", "Qtde. Vend", "SubTotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItensPedido.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblItensPedido);
        if (tblItensPedido.getColumnModel().getColumnCount() > 0) {
            tblItensPedido.getColumnModel().getColumn(0).setResizable(false);
            tblItensPedido.getColumnModel().getColumn(1).setResizable(false);
            tblItensPedido.getColumnModel().getColumn(2).setResizable(false);
            tblItensPedido.getColumnModel().getColumn(3).setResizable(false);
            tblItensPedido.getColumnModel().getColumn(4).setResizable(false);
        }

        lblQuantidadeVendida1.setText("Valor Total do Pedido");

        txtValorTotal.setEnabled(false);

        lblQuantidadeVendida2.setText("Quantidade de Itens do Pedido");

        txtQuantidadeItensPed.setEnabled(false);

        javax.swing.GroupLayout pnlPedido3Layout = new javax.swing.GroupLayout(pnlPedido3);
        pnlPedido3.setLayout(pnlPedido3Layout);
        pnlPedido3Layout.setHorizontalGroup(
            pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedido3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPedido3Layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemover))
                    .addGroup(pnlPedido3Layout.createSequentialGroup()
                        .addComponent(lblCodigoProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProdPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblQuantidadeVendida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidadeVendida, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPedido3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPedido3Layout.createSequentialGroup()
                        .addComponent(lblQuantidadeVendida2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidadeItensPed, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(pnlPedido3Layout.createSequentialGroup()
                        .addComponent(lblQuantidadeVendida1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPedido3Layout.setVerticalGroup(
            pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedido3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProdPesquisar)
                    .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodigoProduto)
                        .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQuantidadeVendida)
                        .addComponent(txtQuantidadeVendida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemover)
                    .addComponent(btnAdicionar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidadeVendida1)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPedido3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidadeVendida2)
                    .addComponent(txtQuantidadeItensPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/exit.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setMaximumSize(new java.awt.Dimension(99, 25));
        btnSair.setMinimumSize(new java.awt.Dimension(99, 25));
        btnSair.setPreferredSize(new java.awt.Dimension(99, 25));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/Eraser.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.setMaximumSize(new java.awt.Dimension(99, 25));
        btnExcluir.setMinimumSize(new java.awt.Dimension(99, 25));
        btnExcluir.setPreferredSize(new java.awt.Dimension(99, 25));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/Alterar.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setEnabled(false);
        btnAlterar.setMaximumSize(new java.awt.Dimension(99, 25));
        btnAlterar.setMinimumSize(new java.awt.Dimension(99, 25));
        btnAlterar.setPreferredSize(new java.awt.Dimension(99, 25));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fatec/poo/view/icon/add.png"))); // NOI18N
        btnIncluir.setText("Incluir");
        btnIncluir.setEnabled(false);
        btnIncluir.setMaximumSize(new java.awt.Dimension(99, 25));
        btnIncluir.setMinimumSize(new java.awt.Dimension(99, 25));
        btnIncluir.setPreferredSize(new java.awt.Dimension(99, 25));
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPedido3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPedido1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPedido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPedido1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPedido2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPedido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        if (alterou == true) {
            JOptionPane.showMessageDialog(null, "Termine a Alteração Iniciada");
        } else {
            conexao.fecharConexao();
            GUIMenu guiMenu = new GUIMenu();
            guiMenu.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Confirma Alteração?") == 0) {//Sim
            //produto.setNome(txtNome.getText());
            pedido.setCliente(daoCliente.consultar(ftfCPFCliente.getText()));
            pedido.setVendedor(daoVendedor.consultar(ftfCPFVendedor.getText()));
            daoPedido.alterar(pedido);
            daoItemPedido.alterarPedido(itensPedido, pedido.getNumero());
        }

        ftfCPFCliente.setText("");
        ftfCPFVendedor.setText("");
        ftfData.setText("");
        txtCodigoProd.setText("");
        txtNumPedido.setText("");
        txtProduto.setText("");

        btnIncluir.setEnabled(false);
        txtNumPedido.setEnabled(true);
        ftfCPFCliente.setEnabled(false);
        ftfCPFVendedor.setEnabled(false);
        ftfData.setEnabled(false);
        txtCodigoProd.setEnabled(false);

        txtQuantidadeVendida.setEnabled(false);
        txtNumPedido.requestFocus();

        btnPedPesquisar.setEnabled(true);
        btnCliPesquisar.setEnabled(false);
        btnProdPesquisar.setEnabled(false);
        btnVendPesquisar.setEnabled(false);
        btnRemover.setEnabled(false);
        btnAdicionar.setEnabled(false);
        itensPedido = null;
        alterou = false;
        atualizaLista();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void txtNumPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumPedidoKeyTyped
        int ascii = evt.getKeyChar();
        if (!(ascii >= 48 && ascii <= 57) && !(ascii == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
        if (txtNumPedido.getText().length() >= 15) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumPedidoKeyTyped

    private void btnPedPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedPesquisarActionPerformed
        pedido = null;
        if (txtNumPedido.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Ops, o campo Código está vazio!\nVerifique e tente novamente");
        } else {

            pedido = daoPedido.consultar(Integer.parseInt(txtNumPedido.getText()));

            if (pedido == null) {
                txtNumPedido.setEnabled(false);
                ftfCPFCliente.setEnabled(true);
                ftfCPFVendedor.setEnabled(true);
               
                ftfData.setEnabled(true);
                btnCliPesquisar.setEnabled(true);
                btnVendPesquisar.setEnabled(true);
                btnProdPesquisar.setEnabled(true);
                txtCodigoProd.setEnabled(true);
                ftfData.requestFocus();
                btnIncluir.setEnabled(true);
            } else if (pedido.getStatus() == true) {
                JOptionPane.showMessageDialog(null, "Pedido já pago impossivel alterar, Favor Criar um novo Pedido");

            } else {
                txtCodigoProd.setEnabled(true);
                btnProdPesquisar.setEnabled(true);
                btnAlterar.setEnabled(true);
            
                txtNumPedido.setEnabled(false);
           
                ftfData.setEnabled(false);
                ftfData.setEditable(false);

                ftfCPFCliente.setText(pedido.getCliente().getCpf());
                txtNomeCliente.setText(pedido.getCliente().getNome());
                ftfData.setText(pedido.getDataEmissaoPedido());
                ftfCPFVendedor.setText(pedido.getVendedor().getCpf());
                txtNomeVendedor.setText(pedido.getVendedor().getNome());

                btnExcluir.setEnabled(true);
                btnPedPesquisar.setEnabled(false);
                btnCliPesquisar.setEnabled(false);
                btnVendPesquisar.setEnabled(false);
                txtCodigoProd.requestFocus();
                itensPedido = daoItemPedido.listItensPedido(Integer.toString(pedido.getNumero()));
                atualizaLista();
            }
        }  
    }//GEN-LAST:event_btnPedPesquisarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        startconnection();
    }//GEN-LAST:event_formWindowOpened

    private void btnCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliPesquisarActionPerformed
        cliente = null;

        if (Cliente.verfiricaCPF(ftfCPFCliente.getText()) == true) {
         
            cliente = daoCliente.consultar(ftfCPFCliente.getText());

            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente Não Existe");
                ftfCPFCliente.requestFocus();
            } else {
                txtNomeCliente.setText(cliente.getNome());
                ftfCPFCliente.setEnabled(false);
            }

        } else {
            JOptionPane.showMessageDialog(null, "CPF do Cliente inválido");
            ftfCPFCliente.requestFocus();
        }


    }//GEN-LAST:event_btnCliPesquisarActionPerformed

    private void btnVendPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendPesquisarActionPerformed
        vendedor = null;

        if (Vendedor.verfiricaCPF(ftfCPFVendedor.getText()) == true) {

            vendedor = daoVendedor.consultar(ftfCPFVendedor.getText());

            if (vendedor == null) {
                JOptionPane.showMessageDialog(null, "Vendedor Não Existe");
                ftfCPFVendedor.requestFocus();
            } else {
                txtNomeVendedor.setText(vendedor.getNome());
                ftfCPFVendedor.setEnabled(false);
            }

        } else {
            JOptionPane.showMessageDialog(null, "CPF do Vendedor inválido");
            ftfCPFVendedor.requestFocus();
        }


    }//GEN-LAST:event_btnVendPesquisarActionPerformed

    private void btnProdPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdPesquisarActionPerformed
        produto = null;

        produto = daoProduto.consultar(Integer.parseInt(txtCodigoProd.getText()));

        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto não existe");
        } else {
            txtProduto.setText(produto.getDescricao());
            txtCodigoProd.setEnabled(false);
            btnProdPesquisar.setEnabled(false);
            btnAdicionar.setEnabled(true);
            btnRemover.setEnabled(true);
            txtQuantidadeVendida.setEnabled(true);

        }

    }//GEN-LAST:event_btnProdPesquisarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed

        double limiteCliente = daoCliente.verificarLimite(ftfCPFCliente.getText());
        double valorTotalProduto = daoProduto.valorProduto(produto.getCodigo()) * Integer.parseInt(txtQuantidadeVendida.getText());
        System.out.println(daoProduto.valorProduto(produto.getCodigo()) * Integer.parseInt(txtQuantidadeVendida.getText()));
        int estoque = daoProduto.verificarEstoque(produto.getCodigo());
        if (estoque < Integer.parseInt(txtQuantidadeVendida.getText())) {
            JOptionPane.showMessageDialog(null, "Não possui estoque suficiente para esta compra");
            txtProduto.setEnabled(false);
            txtProduto.setText("");
            txtCodigoProd.setText("");
            txtCodigoProd.requestFocus();
            btnProdPesquisar.setEnabled(true);
            txtQuantidadeVendida.setText("");
            txtCodigoProd.setEnabled(true);
        } else {

            if (limiteCliente < valorTotalProduto) {
                JOptionPane.showMessageDialog(null, "Cliente não possui saldo suficiente para esta compra");
                txtProduto.setEnabled(false);
                txtProduto.setText("");
                txtCodigoProd.setText("");
                txtCodigoProd.requestFocus();
                btnProdPesquisar.setEnabled(true);
                txtQuantidadeVendida.setText("");
                txtCodigoProd.setEnabled(true);
            } else {

                if (pedido == null) {
                    pedido = new Pedido(Integer.parseInt(txtNumPedido.getText()), ftfData.getText());
                    pedido.setCliente(cliente);
                    pedido.setVendedor(vendedor);
                    pedido.setStatus(true);
                }
                itempedido = null;
                itempedido = new ItemPedido(pedido, Integer.parseInt(txtQuantidadeVendida.getText()));
                itempedido.setProduto(produto);
                txtProduto.setEnabled(false);
                txtProduto.setText("");
                txtCodigoProd.setText("");
                txtCodigoProd.requestFocus();
                btnProdPesquisar.setEnabled(true);
                itensPedido.add(itempedido);
                txtCodigoProd.setEnabled(true);

                int qtde = Integer.valueOf(txtQuantidadeVendida.getText());
                estoque = estoque - qtde;
                txtQuantidadeVendida.setText("");
                daoProduto.atualizarEstoque(produto.getCodigo(), estoque);
                alterou = true;
            }

            atualizaLista();
        }


    }//GEN-LAST:event_btnAdicionarActionPerformed


    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        int a = -1;
        if (tblItensPedido.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Nada selecionado");
        } else {
            a = tblItensPedido.getSelectedRow();
            modTbItens.removeRow(a);
            int estoque = daoProduto.verificarEstoque(produto.getCodigo());
            estoque = estoque + itensPedido.get(a).getQtdeVendida();
            daoProduto.atualizarEstoque(itensPedido.get(a).getProduto().getCodigo(), estoque);
            itensPedido.remove(a);
            atualizaLista();
            alterou = true;
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        if (verificaData(ftfData.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Data Ivalida");
            ftfData.setText("");
            ftfData.requestFocus();
        } else {

            pedido = new Pedido(Integer.parseInt(txtNumPedido.getText()), ftfData.getText());
            pedido.setVendedor(vendedor);
            pedido.setCliente(cliente);
            pedido.setDataPagto("Pg Aberto");
            pedido.setStatus(false);

            daoPedido.inserir(pedido);
            daoItemPedido.inserirItemPedido(itensPedido);

            ftfCPFCliente.setText("");
            ftfCPFVendedor.setText("");
            ftfData.setText("");
            txtCodigoProd.setText("");
            txtNumPedido.setText("");
            txtProduto.setText("");
            txtNomeCliente.setText("");
            txtNomeVendedor.setText("");
          

            btnIncluir.setEnabled(false);
            txtNumPedido.setEnabled(true);
            ftfCPFCliente.setEnabled(false);
            ftfCPFVendedor.setEnabled(false);
            ftfData.setEnabled(false);
            txtCodigoProd.setEnabled(false);
            txtProduto.setEnabled(false);

            txtNumPedido.requestFocus();

            btnPedPesquisar.setEnabled(true);
            btnCliPesquisar.setEnabled(false);
            btnProdPesquisar.setEnabled(false);
            btnVendPesquisar.setEnabled(false);
            btnRemover.setEnabled(false);
            btnAdicionar.setEnabled(false);
            itensPedido = null;
            alterou = false;
            atualizaLista();
        }
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Confirma Exclusão?") == 0) {
            daoPedido.excluir(pedido);
            txtNumPedido.setText("");
            ftfCPFCliente.setText("");
            ftfCPFVendedor.setText("");
            ftfData.setText("");
            txtNomeCliente.setText("");
            txtNomeVendedor.setText("");
            txtProduto.setText("");
            txtQuantidadeVendida.setText("");

            txtNumPedido.setEnabled(true);
            txtProduto.setEnabled(false);
            ftfCPFCliente.setEnabled(false);
            ftfCPFVendedor.setEnabled(false);
            ftfData.setEnabled(false);
            txtNomeCliente.setEnabled(false);
            txtNomeVendedor.setEnabled(false);
            txtProduto.setEnabled(false);
            txtQuantidadeVendida.setEnabled(false);
            tblItensPedido.setEnabled(false);
            btnPedPesquisar.setEnabled(true);
            btnAdicionar.setEnabled(false);
            btnAlterar.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnIncluir.setEnabled(false);
            btnCliPesquisar.setEnabled(false);
            btnProdPesquisar.setEnabled(false);
            btnVendPesquisar.setEnabled(false);
            btnRemover.setEnabled(false);

            itensPedido = null;
            alterou = false;
            atualizaLista();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtQuantidadeVendidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeVendidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeVendidaActionPerformed

    private void txtNumPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumPedidoActionPerformed
        btnPedPesquisarActionPerformed(evt);
    }//GEN-LAST:event_txtNumPedidoActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIEmitirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIEmitirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIEmitirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIEmitirPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIEmitirPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCliPesquisar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnPedPesquisar;
    private javax.swing.JButton btnProdPesquisar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVendPesquisar;
    private javax.swing.JFormattedTextField ftfCPFCliente;
    private javax.swing.JFormattedTextField ftfCPFVendedor;
    private javax.swing.JFormattedTextField ftfData;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCPFVendedor;
    private javax.swing.JLabel lblCodigoProduto;
    private javax.swing.JLabel lblDataPedido;
    private javax.swing.JLabel lblNumeroPedido;
    private javax.swing.JLabel lblQuantidadeVendida;
    private javax.swing.JLabel lblQuantidadeVendida1;
    private javax.swing.JLabel lblQuantidadeVendida2;
    private javax.swing.JPanel pnlPedido;
    private javax.swing.JPanel pnlPedido1;
    private javax.swing.JPanel pnlPedido2;
    private javax.swing.JPanel pnlPedido3;
    private javax.swing.JTable tblItensPedido;
    private javax.swing.JTextField txtCodigoProd;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeVendedor;
    private javax.swing.JTextField txtNumPedido;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtQuantidadeItensPed;
    private javax.swing.JTextField txtQuantidadeVendida;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
    
    private DaoItemPedido daoItemPedido = null;
    private DaoPedido daoPedido = null;
    private Pedido pedido = null;
    private DaoVendedor daoVendedor = null;
    private Vendedor vendedor = null;
    private DaoCliente daoCliente = null;
    private Cliente cliente = null;
    private Conexao conexao = null;
    private DaoProduto daoProduto = null;
    private Produto produto = null;
    private ItemPedido itempedido = null;
    private DefaultTableModel modTbItens;
    private ArrayList<ItemPedido> itensPedido = new ArrayList<>();
    private boolean alterou = false;
}
