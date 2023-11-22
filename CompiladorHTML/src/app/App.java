package app;
import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {

	private JFrame frame;
	private JTextField edCaminho;
	private final JButton btLimpar = new JButton("Limpar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 301);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][grow][][grow]"));
		
		JLabel lbCaminho = new JLabel("Arquivo:");
		frame.getContentPane().add(lbCaminho, "cell 0 0,alignx trailing");
		
		edCaminho = new JTextField();
		frame.getContentPane().add(edCaminho, "flowx,cell 1 0,growx");
		edCaminho.setColumns(10);
		
		JTextPane pnResultado = new JTextPane();
		pnResultado.setEditable(false);
		frame.getContentPane().add(pnResultado, "cell 1 1 1 3,grow");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnResultado.setText(null);
			}
		});
		frame.getContentPane().add(btLimpar, "cell 2 1,growx,aligny top");
	

		JButton btAnalisar = new JButton("Analisar");
		btAnalisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompiladorHTML compilador = new CompiladorHTML();
				
				if (edCaminho.getText() != "") {
					 pnResultado.setText(compilador.validaArquivo(edCaminho.getText()));
				}
				
			}
		});
		frame.getContentPane().add(btAnalisar, "cell 2 0,growx");
	}

}
