package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.view.util.TamanhoFixoJTextField;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.enums.EstadoEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ConsumidorFornecedorView extends JDialog {
	
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JFormattedTextField ftfCep;
	private JTextField tfEndereco;
	private JTextField tfComplemento;
	private JTextField tfNumero;
	private JTextField tfBairro;
	private JTextField tfCidade;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPessoa;
	@SuppressWarnings("rawtypes")
	private JComboBox cbEstado;
	private JFormattedTextField ftfCpfCnpj;
	private JLabel lblCpfCnpj;
	
	@SuppressWarnings("unused")
	private PessoaVO pessoaVO;
	
	@SuppressWarnings("unused")
	private IServicoBeanLocal servicoBeanLocal;
	
	@SuppressWarnings("unused")
	private ConsultaConsumidorFornecedorView telaAnterior;


	public ConsumidorFornecedorView(ConsultaConsumidorFornecedorView jDialog) {
		super(jDialog, true);
		inicializarComponentes();
		telaAnterior = jDialog;
	}
	
	/**
	 * Create the dialog.
	 */
	public ConsumidorFornecedorView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsumidorFornecedorView.class.getResource("/br/com/empresa/view/img/logosenac.jpg")));
		inicializarComponentes();

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void inicializarComponentes() {
		
		servicoBeanLocal = new ServicoBeanLocal();
		pessoaVO = new PessoaVO();
		
		setTitle("Manutenção de Consumidor / Fornecedor");
		setBounds(100, 100, 409, 421);
		
		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width /
				2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 12, 92, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblTipoPessoa = new JLabel("Tipo Pessoa:");
		lblTipoPessoa.setBounds(10, 42, 92, 14);
		getContentPane().add(lblTipoPessoa);
		
		lblCpfCnpj = new JLabel("CPF: *");
		lblCpfCnpj.setBounds(10, 72, 92, 14);
		getContentPane().add(lblCpfCnpj);
		
		JLabel lblNome = new JLabel("Nome: *");
		lblNome.setBounds(10, 101, 92, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCep = new JLabel("CEP: *");
		lblCep.setBounds(10, 130, 46, 14);
		getContentPane().add(lblCep);
		
		JLabel lblEndereco = new JLabel("Endereço: *");
		lblEndereco.setBounds(10, 159, 92, 14);
		getContentPane().add(lblEndereco);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(10, 188, 92, 14);
		getContentPane().add(lblComplemento);
		
		JLabel lblNumero = new JLabel("Número: *");
		lblNumero.setBounds(10, 217, 92, 14);
		getContentPane().add(lblNumero);
		
		JLabel lblBairro = new JLabel("Bairro: *");
		lblBairro.setBounds(10, 246, 92, 14);
		getContentPane().add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade: *");
		lblCidade.setBounds(10, 275, 92, 14);
		getContentPane().add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado: *");
		lblEstado.setBounds(10, 305, 46, 14);
		getContentPane().add(lblEstado);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(112, 9, 106, 20);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);
		
		cbPessoa = new JComboBox();
		cbPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTipoPessoa();
			}
		});
		
		cbPessoa.setModel(new DefaultComboBoxModel(TipoPessoaEnum.values()));
		cbPessoa.setBounds(112, 38, 106, 22);
		getContentPane().add(cbPessoa);
		
		ftfCpfCnpj = new JFormattedTextField();
		ftfCpfCnpj.setBounds(112, 69, 150, 20);
		getContentPane().add(ftfCpfCnpj);
		
		alterarTipoPessoa();
		
		tfNome = new JTextField();
		tfNome.setDocument(new TamanhoFixoJTextField(50));
		tfNome.setBounds(112, 98, 263, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		ftfCep = new JFormattedTextField();
		String formatString = "#####-###";
		MascaraJFormattedTextField.formatField(formatString, ftfCep);
		ftfCep.setBounds(112, 127, 86, 20);
		getContentPane().add(ftfCep);
		ftfCep.setColumns(10);
		
		tfEndereco = new JTextField();
		tfEndereco.setDocument(new TamanhoFixoJTextField(80));
		tfEndereco.setBounds(112, 156, 263, 20);
		getContentPane().add(tfEndereco);
		tfEndereco.setColumns(10);
		
		tfComplemento = new JTextField();
		tfComplemento.setDocument(new TamanhoFixoJTextField(80));
		tfComplemento.setBounds(112, 185, 263, 20);
		getContentPane().add(tfComplemento);
		tfComplemento.setColumns(10);
		
		tfNumero = new JTextField();
		tfNumero.setDocument(new TamanhoFixoJTextField(20));
		tfNumero.setBounds(112, 214, 86, 20);
		getContentPane().add(tfNumero);
		tfNumero.setColumns(10);
		
		tfBairro = new JTextField();
		tfBairro.setDocument(new TamanhoFixoJTextField(30));
		tfBairro.setBounds(112, 243, 225, 20);
		getContentPane().add(tfBairro);
		tfBairro.setColumns(10);
		
		tfCidade = new JTextField();
		tfCidade.setDocument(new TamanhoFixoJTextField(30));
		tfCidade.setBounds(112, 272, 225, 20);
		getContentPane().add(tfCidade);
		tfCidade.setColumns(10);
		
		cbEstado = new JComboBox(new DefaultComboBoxModel(EstadoEnum.values()));
		cbEstado.setBounds(112, 301, 150, 22);
		getContentPane().add(cbEstado);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(ConsumidorFornecedorView.class.getResource("/br/com/empresa/view/img/Fechar.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(277, 340, 106, 33);
		getContentPane().add(btnFechar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ConsumidorFornecedorView.class.getResource("/br/com/empresa/view/img/salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//salvar();
			}
		});
		btnSalvar.setBounds(156, 340, 106, 33);
		getContentPane().add(btnSalvar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 330, 393, 2);
		getContentPane().add(separator);
	}
	
	/*private void salvar() {
		
		TipoPessoaEnum tp = (TipoPessoaEnum) cbPessoa.getSelectedItem();
		pessoaVO.setTippes(tp.name());
		pessoaVO.setCpfcnpj(ftfCpfCnpj.getText());
		pessoaVO.setDescri(tfNome.getText());
		String cepAux = ftfCep.getText();
		if(cepAux.trim().length() > 1) {
			pessoaVO.setCepend(Integer.parseInt(cepAux.replaceAll("-", "")));
		}
		pessoaVO.setRuaend(tfEndereco.getText());
		pessoaVO.setComend(tfComplemento.getText());
		pessoaVO.setNumend(tfNumero.getText());
		pessoaVO.setBaiend(tfBairro.getText());
		pessoaVO.setCidade(tfCidade.getText());
		pessoaVO.setClienteVO(Dados.getClienteSelecionado());
		
		EstadoEnum estadoEnum = (EstadoEnum)cbEstado.getSelectedItem();
		if(estadoEnum != null) {
			pessoaVO.setEstado(estadoEnum.name());
		}
		
		try {
			servicoBeanLocal.salvarPessoa(pessoaVO);
			
			tfCodigo.setText(pessoaVO.getId().toString());
			
			telaAnterior.pesquisar();
			
			JOptionPane.showMessageDialog(this, "Operação Realizada com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de alerta", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação", "Mensagem de erro", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
	}*/
	
	private void fechar() {
		this.setVisible(false);
		this.dispose();
	}
	
	private void alterarTipoPessoa() {
		TipoPessoaEnum tipoPessoaEnum = (TipoPessoaEnum) cbPessoa.getSelectedItem();

		if (tipoPessoaEnum == null || tipoPessoaEnum.name().equals("F")) {

			lblCpfCnpj.setText("CPF");
			String formatString = "#########-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);

		} else if (tipoPessoaEnum.name().equals("J")) {
			lblCpfCnpj.setText("CNPJ");
			String formatString = "##.###.###/####-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);
		}
	}

	public void editar(PessoaVO pessoa) {
		
		this.pessoaVO = pessoa;
		this.tfCodigo.setText(pessoa.getId().toString());
		this.cbPessoa.setSelectedItem(TipoPessoaEnum.valueOf(pessoa.getTippes()));
		this.cbEstado.setSelectedItem(EstadoEnum.valueOf(pessoa.getEstado()));
		this.ftfCpfCnpj.setText(pessoa.getCpfcnpj());
		this.tfNome.setText(pessoa.getDescri());
		this.ftfCep.setText(pessoa.getCepend().toString());
		this.tfEndereco.setText(pessoa.getRuaend());
		this.tfNumero.setText(pessoa.getNumend());
		this.tfComplemento.setText(pessoa.getComend());
		this.tfBairro.setText(pessoa.getBaiend());
		this.tfCidade.setText(pessoa.getCidade());
		
	}
	
}












