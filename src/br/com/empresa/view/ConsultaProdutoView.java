package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;

@SuppressWarnings("serial")
public class ConsultaProdutoView extends JDialog {
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfCodBarras;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox cbStatus;
	private TableModel tableModel;
	
	private ProdutoVO produtoVO;
	
	private IServicoBeanLocal serviceBeanLocal;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConsultaProdutoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsultaProdutoView.class.getResource("/br/com/empresa/view/img/logosenac.jpg")));
		
		serviceBeanLocal = new ServicoBeanLocal();
		
		setTitle("Manutenção de Produto");
		setBounds(100, 100, 620, 390);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 586, 108);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 11, 56, 14);
		panel.add(lblCodigo);
		
		JLabel lbl = new JLabel("Status");
		lbl.setBounds(10, 42, 56, 14);
		panel.add(lbl);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(76, 8, 76, 20);
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(178, 11, 79, 14);
		panel.add(lblDescricao);
		
		JLabel lblCodBarras = new JLabel("Cód. Barras");
		lblCodBarras.setBounds(178, 42, 79, 14);
		panel.add(lblCodBarras);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(267, 8, 309, 20);
		panel.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		tfCodBarras = new JTextField();
		tfCodBarras.setBounds(267, 39, 309, 20);
		panel.add(tfCodBarras);
		tfCodBarras.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(475, 74, 101, 23);
		panel.add(btnPesquisar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpa();
			}
		});
		btnLimpar.setBounds(376, 74, 89, 23);
		panel.add(btnLimpar);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.insertItemAt(null, 0);
		cbStatus.setSelectedIndex(0);
		cbStatus.setBounds(76, 38, 76, 22);
		panel.add(cbStatus);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(ConsultaProdutoView.class.getResource("/br/com/empresa/view/img/novo.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(10, 130, 120, 33);
		getContentPane().add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(ConsultaProdutoView.class.getResource("/br/com/empresa/view/img/edit.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(140, 130, 120, 33);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(ConsultaProdutoView.class.getResource("/br/com/empresa/view/img/remove.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(270, 130, 120, 33);
		getContentPane().add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 172, 586, 121);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(ConsultaProdutoView.class.getResource("/br/com/empresa/view/img/Fechar.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(474, 304, 120, 33);
		getContentPane().add(btnFechar);
		
		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Qtd. Estoque");
		tableModel.addColumn("Status");
		tableModel.addColumn("Vlr. Compra");
		tableModel.addColumn("Vlr. Venda");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(50);
		tableColumnModel.getColumn(1).setPreferredWidth(230);
		tableColumnModel.getColumn(2).setPreferredWidth(100);
		tableColumnModel.getColumn(3).setPreferredWidth(100);
		tableColumnModel.getColumn(4).setPreferredWidth(100);
		tableColumnModel.getColumn(5).setPreferredWidth(100);

		scrollPane.setViewportView(table);
		
		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width /
				2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		JButton btnImportar = new JButton("Importar .csv");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					importarProdutos();
				} catch (BOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnImportar.setIcon(new ImageIcon(ConsultaProdutoView.class.getResource("/br/com/empresa/view/img/importar.jpg")));
		btnImportar.setBounds(10, 304, 138, 33);
		getContentPane().add(btnImportar);
		
		JButton btnExportar = new JButton("Exportar .csv");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					exportarProdutos();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExportar.setIcon(new ImageIcon(ConsultaProdutoView.class.getResource("/br/com/empresa/view/img/exportar.jpg")));
		btnExportar.setBounds(158, 304, 138, 33);
		getContentPane().add(btnExportar);
		
		pesquisar();

	}
	
	private void exportarProdutos() throws BOException {

		try {
			
			File raiz = null;
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				raiz = fileChooser.getSelectedFile();
			} else {
				JOptionPane.showMessageDialog(null, "O programa não pode continuar pois um directorio/arquivo não foi definido.");
			}
			
			serviceBeanLocal.exportarProdutos(raiz, Dados.getClienteSelecionado());
			
			JOptionPane.showMessageDialog(this, "Operação Realizada com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
		
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	private void importarProdutos() throws BOValidationException, BOException {
		List<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		try {
				
		File raiz = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int option = fileChooser.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {			
			raiz = fileChooser.getSelectedFile();		
		} else {
			JOptionPane.showMessageDialog(null, "O programa não pode continuar pois um arquivo não foi selecionado.");
		}
		
		
		//Classes responsavel pela leitura do arquivo
		FileReader fileReader = new FileReader(raiz);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String linha = null;
		int numLinha = 0;
		
		while((linha = bufferedReader.readLine()) != null) {
			
			numLinha++;
			if(numLinha > 1 && linha.trim().length() > 0) {
				produtoVO = new ProdutoVO();
				String[] particionamento = linha.split(";");
				String descri = particionamento[1].replaceAll("\s+", " ");
				String codbar = particionamento[2].trim();
				
				produtoVO.setDescri(descri);
				produtoVO.setCodbar(codbar);
				produtoVO.setQtdest(BigDecimal.ZERO);
				produtoVO.setValcom(BigDecimal.ZERO);
				produtoVO.setValven(BigDecimal.ZERO);
				produtoVO.setStatus("A");
				produtoVO.setClient(Dados.getClienteSelecionado());
				produtos.add(produtoVO);
				
			}
		}
        
        fileReader.close();
		bufferedReader.close();
		
		serviceBeanLocal.importarProdutos(produtos);
		
		JOptionPane.showMessageDialog(this, "Operação Realizada com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
		
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		}		
		
	}
	
	protected void pesquisar() {
		
		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();
		
		try {
			
			StatusEnum statusEnum = (StatusEnum) cbStatus.getSelectedItem();
			
			String statusProduto = null;
			if (statusEnum != null) {
				statusProduto = statusEnum.name();
			}
			
			String idProd = tfCodigo.getText();
			BigInteger idProdBI = null;
			if(idProd != null && idProd.trim().length() > 0) {
				idProdBI = new BigInteger(idProd);
			}
			
			List<ProdutoVO> produtos = serviceBeanLocal.listarProduto(idProdBI, tfDescricao.getText(),statusProduto, 
					tfCodBarras.getText(), Dados.getClienteSelecionado());
			
			DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
			DecimalFormat decimalFormatQtd = new DecimalFormat("###,###.000");
			
			for (ProdutoVO pd : produtos) {

				RowData rowData = new RowData();
				rowData.getValues().put(0, pd.getId().toString());
				rowData.getValues().put(1, pd.getDescri());
				rowData.getValues().put(2, decimalFormatQtd.format(pd.getQtdest()));
				//rowData.getValues().put(3, pd.getStatus());
				if (pd.getStatus().equals("A")) {
					rowData.getValues().put(3, "Ativo");
				} else if (pd.getStatus().equals("I")) {
					rowData.getValues().put(3, "Inativo");
				}
				rowData.getValues().put(4, decimalFormat.format(pd.getValcom()));
				rowData.getValues().put(5, decimalFormat.format(pd.getValven()));
				rowData.setElement(pd);
				tableModel.addRow(rowData);
			}
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void fechar() {
		
		this.setVisible(false);
		this.dispose();
		
	}
	
	private void adicionar() {
		
		ProdutoView produtoView = new ProdutoView(this);
		produtoView.setVisible(true);
		
	}
	

	private void excluir() {
		
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso!",
					JOptionPane.WARNING_MESSAGE);
		}else {

			Object[] options = { "Sim", "Não" };
			int n = JOptionPane.showOptionDialog(null, "Deseja realmente excluir o registro?", "Confirmação",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				ProdutoVO produtoVO = (ProdutoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				try {
					serviceBeanLocal.excluirProduto(produtoVO);
					pesquisar();
				} catch (BOException e) {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}
		
	}
	
	private void limpa() {
		
		tfDescricao.setText(null);
		tfCodBarras.setText(null);
		tfCodigo.setText(null);
		cbStatus.setSelectedIndex(0);
		pesquisar();
		
	}
	
	private void editar() {
		
		if(table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, 
					"É necessario selecionar um registro!",
					"Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		}else {
			ProdutoView pv = new ProdutoView(this);
			
			ProdutoVO aux = (ProdutoVO)tableModel.getRows().get(table.getSelectedRow()).getElement();
			
			pv.editar(aux);
			
			pv.setVisible(true);
		}
		
	}
}
