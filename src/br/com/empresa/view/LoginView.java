package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.UsuarioVO;
import javax.swing.ImageIcon;


public class LoginView extends JFrame {
	private JTextField textLogin;
	
	private IServicoBeanLocal servicoBeanLocal;
	private JPasswordField pfSenha;

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/br/com/empresa/view/img/logosenac.jpg")));
		
		servicoBeanLocal = new ServicoBeanLocal();
		
		setTitle("Autenticação Sistema");
		setBounds(100, 100, 310, 169);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/empresa/view/img/entrar.png")));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticarAcesso();
			}

			
		});
		btnEntrar.setBounds(10, 86, 107, 33);
		getContentPane().add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/empresa/view/img/Fechar.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(174, 86, 107, 33);
		getContentPane().add(btnSair);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 11, 46, 14);
		getContentPane().add(lblLogin);
		
		textLogin = new JTextField();
		textLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticarAcesso();
			}
		});
		textLogin.setBounds(61, 8, 220, 20);
		getContentPane().add(textLogin);
		textLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		
		lblSenha.setBounds(10, 55, 46, 14);
		getContentPane().add(lblSenha);
		
		pfSenha = new JPasswordField();
		pfSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticarAcesso();
			}
		});
		pfSenha.setBounds(61, 52, 220, 20);
		getContentPane().add(pfSenha);
		
		//Colocar a janela no centro da tela.
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,
			dim.height / 2 - this.getSize().height / 2);

	}
	
	private void sair() {
		Object[] options = { "Sim", "Não"};
		
		int n = JOptionPane.showOptionDialog(null, 
				                             "Deseja sair?", 
				                             "Sair", 
				                             JOptionPane.YES_NO_CANCEL_OPTION, 
				                             JOptionPane.QUESTION_MESSAGE, 
				                             null, 
				                             options, 
				                             options[1]); 
		if(n == 0) {
			System.exit(0);
		}
	}
	
	private void autenticarAcesso() {
		String login = this.textLogin.getText();
		String senha = new String(this.pfSenha.getPassword());

		try {
			UsuarioVO usuario = servicoBeanLocal.validarAcesso(login, senha);
			
			Dados.setUsuarioLogado(usuario);
			
			SelecaoClienteView selecao = new SelecaoClienteView();
			selecao.setVisible(true);
			
			super.setVisible(false);
			super.dispose();
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Mensagem de alerta!", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, procure o administrador", 
					"Mensagem de erro!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
