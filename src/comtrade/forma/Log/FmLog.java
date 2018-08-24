package comtrade.forma.Log;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comtrade.domen.Korisnik;
import comtrade.komunikacija.Komunikacija;
import comtrade.komunikacija.NitCitanje;

import comtrade.konstante.Konstante;
import comtrade.server.IPortServera;
import comtrade.transfer.TransferKlasa;


import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.awt.event.ActionEvent;

public class FmLog extends JFrame implements IPortServera {

	private JPanel contentPane;
	private JTextField tfKorIme;
	private JPasswordField pfSifra;
	private JTextField tfImeR;
	private JTextField tfPrezimeR;
	private JTextField tfKorisnickoImeR;
	private JTextField tfDatumRodjenjaR;
	private JTextField tfEmailR;
	private JButton btnRegistruj;
	private JButton btnLoguj;

	private NitCitanje nitCitanje;
	private int logIn;
	private NitCitanje nc;
	private JPasswordField pfSifraReg;
	private JPasswordField pfSifraRegPon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FmLog frame = new FmLog();
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
	public FmLog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(-39, 0, 1019, 142);
		contentPane.add(panel);

		JLabel label = new JLabel("Korisnicko ime");
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label.setBounds(543, 25, 113, 14);
		panel.add(label);

		JLabel label_1 = new JLabel("Sifra");
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_1.setBounds(543, 63, 113, 14);
		panel.add(label_1);

		tfKorIme = new JTextField();
		tfKorIme.setColumns(10);
		tfKorIme.setBounds(666, 25, 156, 20);
		panel.add(tfKorIme);

		JLabel label_2 = new JLabel("Logovanje");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		label_2.setBounds(203, 25, 269, 30);
		panel.add(label_2);

		btnLoguj = new JButton("Loguj se");
		btnLoguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String korIme = tfKorIme.getText();
				String sifra = pfSifra.getText();
				Korisnik k = new Korisnik(korIme, sifra);
				System.out.println(korIme + " " + sifra);
				
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.LOGOVANJE);
				tk.setKlijentObjekat(k);
				Komunikacija.vratiKomunikaciju().posalji(tk);
			
				
				TransferKlasa tfk2 =Komunikacija.vratiKomunikaciju().procitaj();
				
				logIn = tfk2.getServerPoruka_odgovorInt();

				
				int idKorisnika=tfk2.getServerPoruka_odgovorId();
				System.out.println(idKorisnika);
				Korisnik k3= new Korisnik(idKorisnika);
				System.out.println(logIn);
				Korisnik korisnikProfil= (Korisnik) tfk2.getServer_ObjekatOdgovor();
				
				if (logIn == Konstante.KORISNIK) {
					FmProfil profil = new FmProfil(korisnikProfil);
				//	FmProfil profil= new FmProfil();
					profil.setVisible(true);
					btnLoguj.setVisible(false);
					dispose();
					
				
				}

				if (logIn == Konstante.ADMINISTRATOR) {
					FmAdmin admin = new FmAdmin();
					admin.setVisible(true);
					btnLoguj.setVisible(false);
				}
				if (logIn == Konstante.NEUSPESNO) {
					JOptionPane.showMessageDialog(null, "Pogresno korisnicko ime ili sifra");
				}
				tfKorIme.setText("");
				pfSifra.setText("");

//			cek cek cek, ti direktno pozivas metodu vrati sve korisnike iz servera?????
				// da 
//				znas da to ne treba
//				to treba da bude fazon tc koja ima operaciju vrati korisnike, onda na serveru da se primi ta tc, spakuju korisnici 
//				ovde ih primis i postavis
				//pa meni dodje isto, nemam pojma, isto radi, brze mi je ovako
//				aj stavi server na drugi kom, pa da vidis da li ce da radi
				
		
				// FmProfil fmProofil= new FmProfil();
				// fmProofil.setVisible(true);

			}
		});
		btnLoguj.setBounds(666, 108, 89, 23);
		panel.add(btnLoguj);

		pfSifra = new JPasswordField();
		pfSifra.setBounds(666, 61, 156, 16);
		panel.add(pfSifra);
		
		JButton btnZaboravljenaSifra = new JButton("Zaboravljena sifra");
		btnZaboravljenaSifra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FmZaboravljenaSifra fmzs= new FmZaboravljenaSifra();
				fmzs.setVisible(true);
			}
		});
		btnZaboravljenaSifra.setBounds(827, 108, 132, 23);
		panel.add(btnZaboravljenaSifra);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 153, 980, 470);
		contentPane.add(panel_1);

		JLabel label_3 = new JLabel("Registracija");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		label_3.setBounds(237, 33, 219, 69);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("Ime");
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_4.setBounds(487, 61, 83, 14);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("Prezime");
		label_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_5.setBounds(487, 98, 83, 14);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("Korisnicko Ime");
		label_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_6.setBounds(487, 173, 94, 14);
		panel_1.add(label_6);

		JLabel label_7 = new JLabel("Sifra");
		label_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_7.setBounds(487, 204, 83, 14);
		panel_1.add(label_7);

		JLabel label_8 = new JLabel("Email");
		label_8.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_8.setBounds(487, 266, 83, 14);
		panel_1.add(label_8);

		tfImeR = new JTextField();
		tfImeR.setColumns(10);
		tfImeR.setBounds(609, 59, 168, 20);
		panel_1.add(tfImeR);

		tfPrezimeR = new JTextField();
		tfPrezimeR.setColumns(10);
		tfPrezimeR.setBounds(609, 96, 168, 20);
		panel_1.add(tfPrezimeR);

		tfKorisnickoImeR = new JTextField();
		tfKorisnickoImeR.setColumns(10);
		tfKorisnickoImeR.setBounds(609, 171, 168, 20);
		panel_1.add(tfKorisnickoImeR);

		btnRegistruj = new JButton("Registruj se");
		btnRegistruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime1=tfImeR.getText();
				String prezime1 = tfPrezimeR.getText();
				String datumrodjenja1 = tfDatumRodjenjaR.getText();
				String korisnickoIme1 = tfKorisnickoImeR.getText();
				String sifra1 = pfSifraReg.getText();
				String sifra2 = pfSifraRegPon.getText();		
				String email1 = tfEmailR.getText();
				int pravaPristupa1=0;
				if(sifra1.equals(sifra2)) {
				Korisnik korisnik1 = new Korisnik(ime1, prezime1, korisnickoIme1, sifra1,datumrodjenja1,pravaPristupa1, email1);
			
				TransferKlasa tfk3 = new TransferKlasa();
				tfk3.setOperacija(Konstante.REGISTRACIJA);
				tfk3.setKlijentObjekat(korisnik1);
				Komunikacija.vratiKomunikaciju().posalji(tfk3);

				TransferKlasa tfk4 = Komunikacija.vratiKomunikaciju().procitaj();
				JOptionPane.showMessageDialog(null, tfk4.getServerPoruka_odgovor());
				}else {
					JOptionPane.showMessageDialog(null, "Molimo unesite indenticne sifre");
				tfImeR.setText("");
				tfPrezimeR.setText("");
				tfKorisnickoImeR.setText("");
				tfEmailR.setText("");
				pfSifraReg.setText("");
				pfSifraRegPon.setText("");
				tfDatumRodjenjaR.setText("");
			
			}
			}
		});
		btnRegistruj.setBounds(609, 323, 89, 23);
		panel_1.add(btnRegistruj);

		JLabel label_9 = new JLabel("Mini Viber");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 35));
		label_9.setBounds(63, 202, 269, 127);
		panel_1.add(label_9);

		JLabel label_10 = new JLabel("Datum rodjenja");
		label_10.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_10.setBounds(487, 137, 94, 14);
		panel_1.add(label_10);

		tfDatumRodjenjaR = new JTextField();
		tfDatumRodjenjaR.setToolTipText("2018-05-30");
		tfDatumRodjenjaR.setColumns(10);
		tfDatumRodjenjaR.setBounds(609, 135, 168, 20);
		panel_1.add(tfDatumRodjenjaR);

		tfEmailR = new JTextField();
		tfEmailR.setColumns(10);
		tfEmailR.setBounds(609, 264, 168, 20);
		panel_1.add(tfEmailR);

		JLabel lblPonoviteSifru = new JLabel("Ponovite sifru");
		lblPonoviteSifru.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblPonoviteSifru.setBounds(487, 229, 83, 14);
		panel_1.add(lblPonoviteSifru);
		
		pfSifraReg = new JPasswordField();
		pfSifraReg.setBounds(609, 202, 168, 20);
		panel_1.add(pfSifraReg);
		
		pfSifraRegPon = new JPasswordField();
		pfSifraRegPon.setBounds(609, 233, 168, 20);
		panel_1.add(pfSifraRegPon);
	}
}
