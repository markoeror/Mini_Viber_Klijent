package comtrade.forma.Log;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comtrade.domen.Korisnik;
import comtrade.domen.Zahtev;
import comtrade.komunikacija.Komunikacija;
import comtrade.komunikacija.NitCitanje;
import comtrade.konstante.Konstante;
import comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FmPregled extends JFrame {

	private JPanel contentPane;
	private	String ime;
	private String prezime;
	private String korIme;
	private String email;
	private JPanel panel_3;
	private JLabel lblKorIme;
	private JLabel lblEmail;
	private JLabel lblPrezime;
	private JLabel lblIme;
	private JLabel label;
	private JTextArea taOMeni;
	private String OMeni;
	private JLabel lblOdgovor;
	private JButton button;
	public FmPregled(Korisnik korisnikPrimalac, Korisnik korisnikProfil, List<Korisnik> listaPrijatelja) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 957, 777);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ime=korisnikPrimalac.getIme();
		prezime=korisnikPrimalac.getPrezime();
		korIme=korisnikPrimalac.getKorisnickoIme();
		email=korisnikPrimalac.getEmail();
		OMeni=korisnikPrimalac.getOMeni();
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 938, 716);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 930, 720);
		panel.add(panel_1);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Eror\\eclipse-workspace\\Mini_Viber_Klijent\\avatar_blond_insurer_manager_marketer_person_user_icon_256.png"));
		label.setBounds(23, 28, 275, 287);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(444, 32, 421, 316);
		panel_1.add(panel_2);
		
		lblIme = new JLabel("<dynamic>");
		lblIme.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblIme.setBounds(48, 28, 218, 40);
		panel_2.add(lblIme);
		
		lblPrezime = new JLabel("<dynamic>");
		lblPrezime.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblPrezime.setBounds(48, 91, 218, 40);
		panel_2.add(lblPrezime);
		
		lblEmail = new JLabel("<dynamic>");
		lblEmail.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblEmail.setBounds(48, 195, 218, 40);
		panel_2.add(lblEmail);
		
		lblKorIme = new JLabel("<dynamic>");
		lblKorIme.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		lblKorIme.setBounds(48, 142, 218, 40);
		panel_2.add(lblKorIme);
		
		
		button = new JButton("Posalji Zahtev");
		for(Korisnik lp:listaPrijatelja) {
			if(lp.getIdKorisnika()==korisnikPrimalac.getIdKorisnika()) {
				button.setText("Obrisi prijatelja");
			}
		}
		button.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				if(button.getText().toString().equals("Posalji Zahtev")) {
				TransferKlasa tk= new TransferKlasa();
				Zahtev z= new Zahtev(korisnikProfil,korisnikPrimalac);
				tk.setOperacija(Konstante.POSALJI_ZAHTEV);
				tk.setKlijentObjekat(z);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				}else {
					TransferKlasa tk2= new TransferKlasa();
					Zahtev z= new Zahtev(korisnikProfil,korisnikPrimalac);
					tk2.setOperacija(Konstante.OBRISI_PRIJATELJA);
					tk2.setKlijentObjekat(z);
					Komunikacija.vratiKomunikaciju().posalji(tk2);
					
				}
			}
		});
		button.setBounds(222, 225, 147, 67);
		panel_2.add(button);
		
		lblOdgovor = new JLabel("");
		lblOdgovor.setBounds(242, 58, 127, 59);
		panel_2.add(lblOdgovor);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(new Color(204, 204, 255));
		panel_3.setBounds(41, 366, 824, 287);
		panel_1.add(panel_3);
		
		taOMeni = new JTextArea();
		taOMeni.setText("<dynamic>");
		taOMeni.setLineWrap(true);
		taOMeni.setFont(new Font("Monospaced", Font.ITALIC, 15));
		taOMeni.setBounds(43, 33, 739, 228);
		panel_3.add(taOMeni);
		srediFormu();
	}


	private void srediFormu() {
		lblEmail.setText(email);
		lblIme.setText(ime);
		lblKorIme.setText(korIme);
		lblPrezime.setText(prezime);
		taOMeni.setText(OMeni);
	
	}
}
