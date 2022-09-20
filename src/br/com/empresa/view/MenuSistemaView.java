package br.com.empresa.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Toolkit;

public class MenuSistemaView extends JFrame {
	/**
	 * Create the frame.
	 */
	public MenuSistemaView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuSistemaView.class.getResource("/br/com/empresa/view/img/logosenac.jpg")));
		setTitle("Sistema Simples de venda");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mniSair = new JMenuItem("Sair");
		mniSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		mnArquivo.add(mniSair);
		
		JMenu mnManutencao = new JMenu("Manutenção");
		menuBar.add(mnManutencao);
		
		JMenuItem mniConsumidor = new JMenuItem("Consumidor/Fornecedor");
		mniConsumidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				manterConsumidorFornecedor();
				
			}

		});
		mnManutencao.add(mniConsumidor);
		
		JMenuItem mniProduto = new JMenuItem("Produto");
		mniProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				manterProduto();
				
			}
		});
		mnManutencao.add(mniProduto);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mniSobre = new JMenuItem("Sobre");
		mniSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sobre();
			}
		});
		mnAjuda.add(mniSobre);
		
		//Maximizar a tela
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		try {
			
			InputStream streamLogo = getClass().getResourceAsStream("senac_logo.png");
			BufferedImage img = ImageIO.read(streamLogo);
			
			ImageIcon imageIcon = new ImageIcon(img);
			JLabel centerLabel = new JLabel(imageIcon);
			
			JPanel main = new JPanel(new BorderLayout());
			main.add(centerLabel, BorderLayout.CENTER);
			
			getContentPane().add(main, BorderLayout.CENTER);
			
		} catch (Exception e) {
		
			JOptionPane.showInputDialog(null, "Ocorreu um erro ao abrir a tela.",
					"Erro!", JOptionPane.ERROR_MESSAGE);
			
		}

	}
	

	private void manterProduto() {
		
		JDialog produtoView = new ConsultaProdutoView();
		produtoView.setModal(true);
		produtoView.setVisible(true);
		
	}
	
	private void manterConsumidorFornecedor() {
		
		JDialog consultaConsumidorFornecedorView = new ConsultaConsumidorFornecedorView();
		consultaConsumidorFornecedorView.setModal(true);
		consultaConsumidorFornecedorView.setVisible(true);
		
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
	
	private void sobre() {
		JOptionPane.showMessageDialog(null, "TOP, meu primeiro programa profi.");
	}

}
