package comtrade.forma.Log;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comtrade.komunikacija.Komunikacija;
import comtrade.konstante.Konstante;
import comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FmZaboravljenaSifra extends JFrame {

	private JPanel contentPane;
	private JTextField tfKorisnickoIme;

	public FmZaboravljenaSifra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unesite vase korisnicko ime");
		lblNewLabel.setBounds(38, 43, 155, 14);
		contentPane.add(lblNewLabel);
		
		tfKorisnickoIme = new JTextField();
		tfKorisnickoIme.setBounds(38, 86, 155, 20);
		contentPane.add(tfKorisnickoIme);
		tfKorisnickoIme.setColumns(10);
		
		JButton btnPosaljiNaEmail = new JButton("Posalji na email");
		btnPosaljiNaEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String korIme= tfKorisnickoIme.getText();
				TransferKlasa tk= new TransferKlasa();
				tk.setOperacija(Konstante.POSALJI_SIFRU_NA_EMAIL);
				tk.setKlijentObjekat(korIme);
				Komunikacija.vratiKomunikaciju().posalji(tk);
			}
		});
		btnPosaljiNaEmail.setBounds(240, 85, 148, 23);
		contentPane.add(btnPosaljiNaEmail);
	}

}
