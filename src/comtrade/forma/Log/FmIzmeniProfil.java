package comtrade.forma.Log;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comtrade.domen.Korisnik;
import comtrade.komunikacija.Komunikacija;
import comtrade.konstante.Konstante;
import comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FmIzmeniProfil extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfDatumRodj;
	private JTextField tfSifra;
	private JTextField tfPonovljenaSifra;
	private String ime;
	private String prezime;
	private String datumRodjenja;
	private String sifra;
	private String OMeni;
	private JButton btnIzmeniProfil;
	private JTextArea taOMeni;
	private JLabel lblOMeni;
	private JLabel lblNewLabel;
	private JLabel lblPrezime;
	private JLabel lblIme;
	private JLabel lblNewLabel_1;
	private JLabel lblDatumRodj;
	private JLabel lblEmail;
	private JTextField tfEmail;
	private String email;
	private JLabel lblIzmenaProfila;
	
	public FmIzmeniProfil(Korisnik korisnikProfil) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ime=korisnikProfil.getIme();
		prezime=korisnikProfil.getPrezime();
		datumRodjenja=korisnikProfil.getDatumRodjenja();
		sifra=korisnikProfil.getSifra();
		OMeni=korisnikProfil.getOMeni();
		email=korisnikProfil.getEmail();
		lblIme = new JLabel("Ime");
		lblIme.setBounds(56, 53, 97, 14);
		contentPane.add(lblIme);
		
		lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(56, 97, 97, 14);
		contentPane.add(lblPrezime);
		
		lblDatumRodj = new JLabel("Datum Rodjenja");
		lblDatumRodj.setBounds(56, 142, 97, 14);
		contentPane.add(lblDatumRodj);
		
		lblNewLabel_1 = new JLabel("Sifra");
		lblNewLabel_1.setBounds(56, 190, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("Ponovljena sifra");
		lblNewLabel.setBounds(56, 233, 86, 14);
		contentPane.add(lblNewLabel);
		
		tfIme = new JTextField();
		tfIme.setBounds(163, 50, 176, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setBounds(163, 94, 176, 20);
		contentPane.add(tfPrezime);
		tfPrezime.setColumns(10);
		
		tfDatumRodj = new JTextField();
		tfDatumRodj.setBounds(163, 139, 176, 20);
		contentPane.add(tfDatumRodj);
		tfDatumRodj.setColumns(10);
		
		tfSifra = new JTextField();
		tfSifra.setBounds(163, 187, 176, 20);
		contentPane.add(tfSifra);
		tfSifra.setColumns(10);
		
		tfPonovljenaSifra = new JTextField();
		tfPonovljenaSifra.setBounds(163, 230, 176, 20);
		contentPane.add(tfPonovljenaSifra);
		tfPonovljenaSifra.setColumns(10);
		
		lblOMeni = new JLabel("O Meni");
		lblOMeni.setBounds(56, 314, 46, 14);
		contentPane.add(lblOMeni);
		
		taOMeni = new JTextArea();
		taOMeni.setLineWrap(true);
		taOMeni.setBounds(162, 309, 279, 200);
		contentPane.add(taOMeni);
		
		btnIzmeniProfil = new JButton("Izmeni Profil");
		btnIzmeniProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ime=tfIme.getText();
				prezime=tfPrezime.getText();
				datumRodjenja=tfDatumRodj.getText();
				if(tfSifra.getText().equals(tfPonovljenaSifra.getText())) {
					sifra=tfSifra.getText();
				}
				OMeni=taOMeni.getText();
				int idKorisnika=korisnikProfil.getIdKorisnika();
				String korisnickoIme=korisnikProfil.getKorisnickoIme();
				
				Korisnik izmKor= new Korisnik(idKorisnika,ime,prezime,korisnickoIme,sifra,datumRodjenja,email,OMeni);
				TransferKlasa tkip= new TransferKlasa();
				tkip.setOperacija(Konstante.IZMENI_PROFIL);
				tkip.setKlijentObjekat(izmKor);
				Komunikacija.vratiKomunikaciju().posalji(tkip);
			}
		});
		btnIzmeniProfil.setBounds(305, 520, 136, 23);
		contentPane.add(btnIzmeniProfil);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(56, 274, 46, 14);
		contentPane.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(163, 271, 176, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		lblIzmenaProfila = new JLabel("Izmena Profila ");
		lblIzmenaProfila.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzmenaProfila.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIzmenaProfila.setBounds(117, 11, 247, 20);
		contentPane.add(lblIzmenaProfila);
		srediFormu();
	}

	private void srediFormu() {
		tfIme.setText(ime);
		tfPrezime.setText(prezime);
		tfDatumRodj.setText(datumRodjenja);
		tfSifra.setText(sifra);
		tfPonovljenaSifra.setText(sifra);
		taOMeni.setText(OMeni);
		tfEmail.setText(email);
		
	}
}
