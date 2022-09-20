package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;
import javax.swing.ImageIcon;

public class ProdutoView extends JDialog {
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfCodBarras;
	private JTextField tfQtdEstoque;
	private JTextField tfVlrCompra;
	private JTextField tfVlrVenda;
	private JComboBox cbStatus;
	
    private ProdutoVO produtoVO;
	
	private IServicoBeanLocal servicoBeanLocal;
	
	private ConsultaProdutoView telaAnterior;


	public ProdutoView(ConsultaProdutoView jDialog) {
		super(jDialog, true);
		inicializarComponentes();
		telaAnterior = jDialog;
	}
	
	public ProdutoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProdutoView.class.getResource("/br/com/empresa/view/img/logosenac.jpg")));
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		
		servicoBeanLocal = new ServicoBeanLocal();
		produtoVO = new ProdutoVO();
		
		setTitle("Manutenção de Produto");
		setBounds(100, 100, 390, 318);
		getContentPane().setLayout(null);
		
		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width /
				2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 17, 92, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblDescricao = new JLabel("Descrição: *");
		lblDescricao.setBounds(10, 48, 92, 14);
		getContentPane().add(lblDescricao);
		
		JLabel lblCodBarras = new JLabel("Cód. Barras: *");
		lblCodBarras.setBounds(10, 79, 92, 14);
		getContentPane().add(lblCodBarras);
		
		JLabel lblQtdEstoque = new JLabel("Qtd. Estoque: *");
		lblQtdEstoque.setBounds(10, 110, 92, 14);
		getContentPane().add(lblQtdEstoque);
		
		JLabel lblVlrCompra = new JLabel("Vlr. Compra: *");
		lblVlrCompra.setBounds(10, 141, 92, 14);
		getContentPane().add(lblVlrCompra);
		
		JLabel lblVlrVenda = new JLabel("Vlr. Venda: *");
		lblVlrVenda.setBounds(10, 172, 92, 14);
		getContentPane().add(lblVlrVenda);
		
		JLabel lblStatus = new JLabel("Status: *");
		lblStatus.setBounds(10, 203, 92, 14);
		getContentPane().add(lblStatus);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(112, 14, 100, 20);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(112, 45, 250, 20);
		getContentPane().add(tfDescricao);
		tfDescricao.setColumns(10);
		
		tfCodBarras = new JTextField();
		tfCodBarras.setBounds(112, 76, 170, 20);
		getContentPane().add(tfCodBarras);
		tfCodBarras.setColumns(10);
		
		tfQtdEstoque = new JTextField();
		tfQtdEstoque.setBounds(112, 107, 79, 20);
		getContentPane().add(tfQtdEstoque);
		tfQtdEstoque.setColumns(10);
		
		tfVlrCompra = new JTextField();
		tfVlrCompra.setBounds(112, 138, 86, 20);
		getContentPane().add(tfVlrCompra);
		tfVlrCompra.setColumns(10);
		
		tfVlrVenda = new JTextField();
		tfVlrVenda.setBounds(112, 169, 86, 20);
		getContentPane().add(tfVlrVenda);
		tfVlrVenda.setColumns(10);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(ProdutoView.class.getResource("/br/com/empresa/view/img/Fechar.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(256, 236, 106, 33);
		getContentPane().add(btnFechar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ProdutoView.class.getResource("/br/com/empresa/view/img/salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(140, 236, 106, 33);
		getContentPane().add(btnSalvar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 228, 374, 2);
		getContentPane().add(separator);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.insertItemAt(null, 0);
		cbStatus.setSelectedIndex(0);
		cbStatus.setBounds(112, 199, 86, 22);
		getContentPane().add(cbStatus);
		
	}
	

	private void salvar() {
		produtoVO.setDescri(tfDescricao.getText());
		produtoVO.setCodbar(tfCodBarras.getText());
		String qtdest = tfQtdEstoque.getText().trim();
		qtdest = qtdest.replaceAll("\\.", "").replaceAll(",", ".");
		if(qtdest.length() > 1) {
			BigDecimal qtdestoque = new BigDecimal(qtdest);
			produtoVO.setQtdest(qtdestoque);
		}
		String vlrcom = tfVlrCompra.getText().trim();
		vlrcom = vlrcom.replaceAll("\\.", "").replaceAll(",", ".");
		if(vlrcom.length() > 1) {
			BigDecimal vlrCompra = new BigDecimal(vlrcom);
			produtoVO.setValcom(vlrCompra);
		}
		String vlrven = tfVlrVenda.getText().trim();
		vlrven = vlrven.replaceAll("\\.", "").replaceAll(",", ".");
		if(vlrven.length() > 1) {
			BigDecimal vlrVenda = new BigDecimal(vlrven);
			produtoVO.setValven(vlrVenda);
		}
		StatusEnum st = (StatusEnum) cbStatus.getSelectedItem();
		if(st != null) {
			produtoVO.setStatus(st.name());
		}
		produtoVO.setCliente(Dados.getClienteSelecionado());
		
		try {
			
			servicoBeanLocal.salvarProduto(produtoVO);
			tfCodigo.setText(produtoVO.getId().toString());
			telaAnterior.pesquisar();
			JOptionPane.showMessageDialog(this, "Operação Realizada com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (BOValidationException e) {
			
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de alerta", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			
		} catch (BOException e) {
			
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação", "Mensagem de erro", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			
		}
	}
	
	private void fechar() {
		this.setVisible(false);
		this.dispose();
	}
	
    public void editar(ProdutoVO produto) {
    	DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    	DecimalFormat decimalFormatQtd = new DecimalFormat("###,###.000");
		this.produtoVO = produto;
		this.tfCodigo.setText(produto.getId().toString());
		this.tfDescricao.setText(produto.getDescri());
		this.tfCodBarras.setText(produto.getCodbar());
		this.tfQtdEstoque.setText(decimalFormatQtd.format(produto.getQtdest()));
		this.tfVlrCompra.setText(decimalFormat.format(produto.getValcom()));
		this.tfVlrVenda.setText(decimalFormat.format(produto.getValven()));
		this.cbStatus.setSelectedItem(StatusEnum.valueOf(produto.getStatus()));
	}
}
