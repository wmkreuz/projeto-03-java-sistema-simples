package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioClienteVO;

@SuppressWarnings("serial")
public class SelecaoClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField textFiltro;
	@SuppressWarnings("rawtypes")
	private JList list;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SelecaoClienteView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelecaoClienteView.class.getResource("/br/com/empresa/view/img/logosenac.jpg")));
		setTitle("Seleção de instituição");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(10, 11, 46, 14);
		contentPane.add(lblFiltro);
		
		textFiltro = new JTextField();
		textFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caregarValoresListModel();
			}
		});
		textFiltro.setBounds(10, 33, 309, 20);
		contentPane.add(textFiltro);
		textFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 309, 153);
		contentPane.add(scrollPane);
		
	    list = new JList();
		scrollPane.setViewportView(list);
		
		ListModel<ClienteVO> listModel = new DefaultListModel<ClienteVO>();
		list.setModel(listModel);
		
		caregarValoresListModel();
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCliente();
			}
		});
		btnSelecionar.setBounds(10, 221, 108, 23);
		contentPane.add(btnSelecionar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();			
			}
		});
		btnCancelar.setBounds(230, 221, 89, 23);
		contentPane.add(btnCancelar);
		
		//Colocar a janela no centro da tela.
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,
		dim.height / 2 - this.getSize().height / 2);
		
	}
	
	private void selecionarCliente() {
		@SuppressWarnings("rawtypes")
		DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
		
		if(list.getSelectedIndex() >= 0) {
			
			ClienteVO clienteVO = (ClienteVO)defaultListModel.get(list.getSelectedIndex());
			Dados.setClienteSelecionado(clienteVO);
			
			MenuSistemaView menu = new MenuSistemaView();
			menu.setVisible(true);
			this.setVisible(false);
			this.dispose();
			
		}else {
			JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente "+
		                                         "para continuar.","Validação", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void sair() {
		Object[] options = { "Sim", "Não"};
		LoginView frame = new LoginView();
		
		int n = JOptionPane.showOptionDialog(null, 
				                             "Deseja voltar para tela de login?", 
				                             "Voltar", 
				                             JOptionPane.YES_NO_CANCEL_OPTION, 
				                             JOptionPane.QUESTION_MESSAGE, 
				                             null, 
				                             options, 
				                             options[1]); 
		if(n == 0) {
			frame.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void caregarValoresListModel() {
		
		IServicoBeanLocal serviceBeanLocal = new ServicoBeanLocal();
		try {
			List<UsuarioClienteVO> usuarioClienteVOs = 
					serviceBeanLocal.listarClienteUsuario(Dados.getUsuarioSelecionado());
			
			@SuppressWarnings("rawtypes")
			DefaultListModel defautListModel = (DefaultListModel) list.getModel();
			defautListModel.clear();
			
			if(textFiltro != null && textFiltro.getText() != null) {
				for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
					
					if(usuarioClienteVO.getClienteVO().getDescri().toUpperCase().contains(textFiltro.getText().toUpperCase())) {
						defautListModel.addElement(usuarioClienteVO.getClienteVO());
					}
					
				}
			}else {
				for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
					defautListModel.addElement(usuarioClienteVO.getClienteVO());
				}
			}
			
		} catch (BOException e) {
			
			e.printStackTrace();
		}
		
	}
}
