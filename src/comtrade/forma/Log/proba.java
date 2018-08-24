package comtrade.forma.Log;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comtrade.kontroler.Kontroler;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Proba extends JFrame {

	private JPanel contentPane;
	private static JTextArea instanca;
	private JTextField tfText;
	private DefaultListModel dlm= new DefaultListModel<>();
	private JList list;
	private JScrollPane scrollPane_1;
	private String  nazivKorisnika;
	private List<String> listaKornisnika= new ArrayList<>();
	private String text;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proba frame = new Proba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Proba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnPosalji = new JButton("Posalji");
		btnPosalji.setBounds(446, 50, 89, 23);
		btnPosalji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text=tfText.getText();
				Chat.vratiInstancu().postaviText(text);
				Chat.vratiInstancu().dodajUTextAreu(text);
				
				
				
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnPosalji);
		Chat.vratiInstancu().postaviContentPane(contentPane);
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 99, 394, 369);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textArea);
		
		tfText = new JTextField();
		tfText.setBounds(101, 38, 267, 20);
		contentPane.add(tfText);
		tfText.setColumns(10);
		
		scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(101, 99, 117, 292);
		contentPane.add(scrollPane_1);
		listaKornisnika.add("Marko");
		listaKornisnika.add("Pera");
		list = new JList(dlm);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				nazivKorisnika=list.getSelectedValue().toString();
				Chat.vratiInstancu();
						
			
				
			
				
			}
		});
		scrollPane_1.setViewportView(list);
		for(String lk:listaKornisnika) {
		dlm.addElement(lk);
		System.out.println(lk);
		}
		
		
		
		
		
	}
}
