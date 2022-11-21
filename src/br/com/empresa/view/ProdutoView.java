package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

@SuppressWarnings("serial")
public class ProdutoView extends JDialog {
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JFormattedTextField tfCodBarras;
	private JFormattedTextField tfQtdEstoque;
	private JFormattedTextField tfVlrCompra;
	private JFormattedTextField tfVlrVenda;
	private JLabel lblVlrLucro;
	@SuppressWarnings("rawtypes")
	private JComboBox cbStatus;
	
    private ProdutoVO produtoVO;
	
	private IServicoBeanLocal servicoBeanLocal;
	
	private ConsultaProdutoView telaAnterior;
	private JFormattedTextField tfDatFab;
	private JFormattedTextField tfDatVal;


	public ProdutoView(ConsultaProdutoView jDialog) {
		super(jDialog, true);
		inicializarComponentes();
		telaAnterior = jDialog;
	}
	
	public ProdutoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProdutoView.class.getResource("/br/com/empresa/view/img/logosenac.jpg")));
		inicializarComponentes();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void inicializarComponentes() {
		
		servicoBeanLocal = new ServicoBeanLocal();
		produtoVO = new ProdutoVO();
		
		setTitle("Manutenção de Produto");
		setBounds(100, 100, 390, 450);
		getContentPane().setLayout(null);
		
		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width /
				2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 18, 92, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblDescricao = new JLabel("Descrição: *");
		lblDescricao.setBounds(10, 53, 92, 14);
		getContentPane().add(lblDescricao);
		
		JLabel lblCodBarras = new JLabel("Cód. Barras: *");
		lblCodBarras.setBounds(10, 88, 92, 14);
		getContentPane().add(lblCodBarras);
		
		JLabel lblQtdEstoque = new JLabel("Qtd. Estoque: *");
		lblQtdEstoque.setBounds(10, 123, 92, 14);
		getContentPane().add(lblQtdEstoque);
		
		JLabel lblVlrCompra = new JLabel("Vlr. Compra: *");
		lblVlrCompra.setBounds(10, 158, 92, 14);
		getContentPane().add(lblVlrCompra);
		
		JLabel lblVlrVenda = new JLabel("Vlr. Venda: *");
		lblVlrVenda.setBounds(10, 193, 92, 14);
		getContentPane().add(lblVlrVenda);
		
		JLabel lblStatus = new JLabel("Status: *");
		lblStatus.setBounds(10, 328, 92, 14);
		getContentPane().add(lblStatus);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(124, 15, 100, 20);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(124, 50, 250, 20);
		getContentPane().add(tfDescricao);
		tfDescricao.setColumns(10);
		
		tfCodBarras = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField("###################", tfCodBarras);
		tfCodBarras.setBounds(124, 85, 170, 20);
		getContentPane().add(tfCodBarras);
		tfCodBarras.setColumns(10);
		
		tfQtdEstoque = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField("#######.000", tfQtdEstoque);
		tfQtdEstoque.setBounds(124, 120, 79, 20);
		getContentPane().add(tfQtdEstoque);
		tfQtdEstoque.setColumns(10);
		
		tfVlrCompra = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField(tfVlrCompra);
		tfVlrCompra.setBounds(124, 155, 86, 20);
		getContentPane().add(tfVlrCompra);
		tfVlrCompra.setColumns(10);
		
		tfVlrVenda = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField(tfVlrVenda);
		tfVlrVenda.setBounds(124, 190, 86, 20);
		getContentPane().add(tfVlrVenda);
		tfVlrVenda.setColumns(10);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(ProdutoView.class.getResource("/br/com/empresa/view/img/Fechar.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(256, 367, 106, 33);
		getContentPane().add(btnFechar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ProdutoView.class.getResource("/br/com/empresa/view/img/salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(140, 367, 106, 33);
		getContentPane().add(btnSalvar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 354, 374, 2);
		getContentPane().add(separator);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.setBounds(124, 324, 86, 22);
		getContentPane().add(cbStatus);
		
		JLabel lblDatFab = new JLabel("Data Fabricação: *");
		lblDatFab.setBounds(10, 257, 104, 14);
		getContentPane().add(lblDatFab);
		
		tfDatFab = new JFormattedTextField();
		String formatDatFab = "##/##/####";
		MascaraJFormattedTextField.formatField(formatDatFab, tfDatFab);
		tfDatFab.setColumns(10);
		tfDatFab.setBounds(124, 254, 86, 20);
		getContentPane().add(tfDatFab);
		
		JLabel lblDatVal = new JLabel("Data Validade: *");
		lblDatVal.setBounds(10, 292, 104, 14);
		getContentPane().add(lblDatVal);
		
		tfDatVal = new JFormattedTextField();
		String formatDatVal = "##/##/####";
		MascaraJFormattedTextField.formatField(formatDatVal, tfDatVal);
		tfDatVal.setColumns(10);
		tfDatVal.setBounds(124, 289, 86, 20);
		getContentPane().add(tfDatVal);
		
		JLabel lblLucro = new JLabel("Lucro: *");
		lblLucro.setBounds(10, 225, 92, 14);
		getContentPane().add(lblLucro);
		
		lblVlrLucro = new JLabel("R$: 0,00");
		lblVlrLucro.setBounds(124, 225, 92, 14);
		getContentPane().add(lblVlrLucro);
		
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
		produtoVO.setClient(Dados.getClienteSelecionado());
		
		SimpleDateFormat datFabFormatada = new SimpleDateFormat("dd/MM/yyyy");
        Date dateFab = null;
        try {
        	dateFab = datFabFormatada.parse(tfDatFab.getText());
            produtoVO.setDatfab(dateFab);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        SimpleDateFormat datValFormatada = new SimpleDateFormat("dd/MM/yyyy");
        Date dateVal = null;
        try {
        	dateVal = datValFormatada.parse(tfDatVal.getText());
            produtoVO.setDatval(dateVal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		try {
			
			servicoBeanLocal.salvarProduto(produtoVO);
			tfCodigo.setText(produtoVO.getId().toString());
			if(produtoVO.getValcom() != null || produtoVO.getValven() != null) {
				BigDecimal lucro = 	produtoVO.getValven().subtract(produtoVO.getValcom());
				String lucrostring = lucro.toString();
				lblVlrLucro.setText("R$ : "+lucrostring);
			}
			
			LocalDateTime datFab = dateFab.toInstant().atZone( ZoneId.systemDefault() ).toLocalDateTime();
			LocalDateTime datVal = dateVal.toInstant().atZone( ZoneId.systemDefault() ).toLocalDateTime();
			
			long tempoValidade = (datFab.until(datVal, ChronoUnit.DAYS));
			
			if(tempoValidade > 365){
				JOptionPane.showMessageDialog(this, "Produto de longa duração", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
			}
			
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
		if(produto.getValcom() != null || produto.getValven() != null) {
			BigDecimal lucro = 	produto.getValven().subtract(produto.getValcom());
			String lucrostring = lucro.toString();
			this.lblVlrLucro.setText("R$ : "+lucrostring);
		}
		if(produtoVO.getDatfab() != null) {
			Date datFab = produtoVO.getDatfab();
	        DateFormat datFabFormatada = new SimpleDateFormat("dd/MM/yyyy");
	        String StringDatFabFormatada = datFabFormatada.format(datFab);
			this.tfDatFab.setText(StringDatFabFormatada);
		}
		
		if(produto.getDatval() != null) {
			Date datVab = produtoVO.getDatval();
	        DateFormat datValFormatada = new SimpleDateFormat("dd/MM/yyyy");
	        String StringDatValFormatada = datValFormatada.format(datVab);
			this.tfDatVal.setText(StringDatValFormatada);
		}
		
	}
}
