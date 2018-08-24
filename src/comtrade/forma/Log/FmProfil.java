package comtrade.forma.Log;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comtrade.domen.Grupa;
import comtrade.domen.Grupna_Poruka;
import comtrade.domen.Korisnik;
import comtrade.domen.ListPomocnik;
import comtrade.domen.Poruka;
import comtrade.domen.Zahtev;
import comtrade.komunikacija.Komunikacija;
import comtrade.komunikacija.NitCitanje;
import comtrade.konstante.Konstante;
import comtrade.kontroler.Kontroler;
import comtrade.transfer.TransferKlasa;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.ListModel;

public class FmProfil extends JFrame {

	private JPanel contentPane;
	private JPanel panel_Grupe;
	private JPanel panel_Poruke;
	private JPanel panel_Profil;
	private JPanel panel_Prijatelji;
	private JPanel panel_Dugmici;
	private JButton btnGrupe;
	private JButton btnPrijatelji;
	private JButton btnPoruke;
	private JButton btnProfil;
	private JPanel panel_Korisnik;
	private JLabel lblKorisnik;
	private JPanel panel_1;
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblDatumRodjenja;
	private int idKorisnika;
	private String ime;
	private int idOznGrupe;
	private String prezime;
	private String datumRodjenja;
	private String korisnickoIme;
	private String OMeni;
	private JLabel lblKorIme;
	private JTextArea taOMeni;
	private JPanel panel;
	private JLabel lblSlika;
	private JTextField tfPretraga;
	private JButton btnPretrazi;
	private Korisnik primalacPoruke;
	private int index = -2;
	private Poruka porukaP;
	private String pojedinacnaPoruka;
	// private Korisnik primalacPoruke;
	private JList list;
	private String pretraga;
	private JScrollPane scrollPane;
	private List<String> listaKorisnika1 = new ArrayList<>();
	private DefaultListModel dlm = new DefaultListModel<>();
	private DefaultListModel dlm1 = new DefaultListModel<>();
	private DefaultListModel dlm2 = new DefaultListModel<>();
	private DefaultListModel dlm3 = new DefaultListModel<>();
	private DefaultListModel dlm4 = new DefaultListModel<>();
	private DefaultListModel dlm5 = new DefaultListModel<>();
	private DefaultListModel dlm6 = new DefaultListModel<>();
	private List<Korisnik> listaKorisnika = new ArrayList<>();
	private JButton btnPosaljiPoruku;
	private JScrollPane scrollPane_PojedinacnaPoruka;
	private JTextArea taPojedinacnaPoruka;
	private Korisnik korisnickiProfil1 = new Korisnik();
	private List<Korisnik> listaPrijatelja = new ArrayList<>();
	private List<Zahtev> listaZahteva = new ArrayList<>();
	private List<String> listaZahtevaString = new ArrayList<>();
	private List<String> listaPrijateljaString = new ArrayList<>();
	private List<String> listaPoruka = new ArrayList<>();
	private List<Korisnik> listaKorisnikaUGrupi = new ArrayList<>();
	private List<Grupa> listaGrupa = new ArrayList<>();
	private List<Grupa> listaMojihGrupa;
	private List<String> listaGrupnihPoruka;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTextField tfGrupnaPoruka;
	private JTextField tfPorukaPojedinacna;
	private int brojNepPoruka;
	private int red;
	private int idPrijatelja;
	private JList list_Prijatelji;
	private String oznacenPrijatelj;
	private JPanel panel_3;
	private String nazivPosiljaocaZahteva;
	private int idPosiljaocaZahteva;
	private Map<Integer, List<String>> hm;
	private Map<Integer, List<String>> hmnp;
	private Map<Integer, List<String>> hmgp;
	private JTextArea taGrupnePoruke;
	private JScrollPane scrollPane_PorukeGrupe;
	private JList list_Grupe;
	private JScrollPane scrollPane_Grupe;
	private JButton btnKreirajGrupu;
	private JButton btnPosaljiGrupnuPoruku;
	private JTextField tfNazivGrupe;
	private JLabel lblListaGrupa;
	private JLabel lblNazivGrupe;
	private JScrollPane scrollPane_MojeGrupe;
	private JList list_MojeGrupe;
	private ListPomocnik ozn;
	private ListPomocnik oznMG;
	private JLabel lblIme_1;
	private JLabel lblPrezime_1;
	private JLabel lblKorisnickoIme;
	private JLabel lblDatumRodnjenja;
	private int brojSveNeprocitanePoruke = 0;
	private Broj bnp = new Broj();
	private ListPomocnik lp;
	private JScrollPane scrollPane_Prijatelji2;
	private JList list_Prijatelji2;
	private JTextArea taKorisniciUGrupi;
	private JScrollPane scrollPane_2;
	private JLabel lblMojeGrupe;
	private JButton btnDodajGrupu;
	private JButton btnNapustiGrupu;

	public FmProfil(Korisnik korisnikProfil) {

		Socket soket = Komunikacija.vratiKomunikaciju().getSoket();
		NitCitanje nitCitanje = new NitCitanje(soket);
		nitCitanje.start();
		nitCitanje.postaviFormu(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1109, 821);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		idKorisnika = korisnikProfil.getIdKorisnika();
		ime = korisnikProfil.getIme();
		prezime = korisnikProfil.getPrezime();
		korisnickoIme = korisnikProfil.getKorisnickoIme();
		datumRodjenja = korisnikProfil.getDatumRodjenja();
		OMeni = korisnikProfil.getOMeni();
		korisnickiProfil1 = new Korisnik(idKorisnika, ime, prezime, korisnickoIme);
		vratiSveKorisnikeZahtev();
		vratiListuZahteva();
		vratiListuPrijatelja();
		vratiListuPoruka();
		vratiListuGrupa();
		vratiListuMojihGrupa();
		vratiListuGrupnihPoruka();
		panel_Profil = new JPanel();
		panel_Profil.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_Profil.setBackground(new Color(255, 255, 255));
		panel_Profil.setBounds(156, 51, 920, 720);
		contentPane.add(panel_Profil);
		panel_Profil.setLayout(null);

		lblSlika = new JLabel("");
		lblSlika.setIcon(new ImageIcon(
				"C:\\Users\\Eror\\eclipse-workspace\\Mini_Viber_Klijent\\avatar_blond_insurer_manager_marketer_person_user_icon_256.png"));
		lblSlika.setBounds(108, 32, 326, 320);
		panel_Profil.add(lblSlika);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EmptyBorder(1, 0, 1, 1));
		panel.setBounds(444, 32, 421, 261);
		panel_Profil.add(panel);
		panel.setLayout(null);

		lblIme = new JLabel("");
		lblIme.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblIme.setBounds(178, 40, 218, 40);
		panel.add(lblIme);

		lblPrezime = new JLabel("");
		lblPrezime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPrezime.setBounds(178, 91, 218, 40);
		panel.add(lblPrezime);

		lblDatumRodjenja = new JLabel("");
		lblDatumRodjenja.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDatumRodjenja.setBounds(178, 193, 218, 40);
		panel.add(lblDatumRodjenja);

		lblKorIme = new JLabel("");
		lblKorIme.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblKorIme.setBounds(178, 142, 218, 40);
		panel.add(lblKorIme);

		JLabel lblOMeni = new JLabel("O Meni");
		lblOMeni.setHorizontalAlignment(SwingConstants.CENTER);
		lblOMeni.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOMeni.setBounds(48, 11, 253, 14);
		panel.add(lblOMeni);

		lblIme_1 = new JLabel("Ime  :");
		lblIme_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblIme_1.setBounds(10, 40, 123, 40);
		panel.add(lblIme_1);

		lblPrezime_1 = new JLabel("Prezime  :");
		lblPrezime_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPrezime_1.setBounds(10, 91, 123, 40);
		panel.add(lblPrezime_1);

		lblKorisnickoIme = new JLabel("Korisnicko ime :");
		lblKorisnickoIme.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblKorisnickoIme.setBounds(10, 142, 144, 40);
		panel.add(lblKorisnickoIme);

		lblDatumRodnjenja = new JLabel("Datum rodjenja :");
		lblDatumRodnjenja.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDatumRodnjenja.setBounds(10, 193, 144, 40);
		panel.add(lblDatumRodnjenja);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 255));
		panel_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(41, 366, 824, 287);
		panel_Profil.add(panel_1);
		panel_1.setLayout(null);

		taOMeni = new JTextArea();
		taOMeni.setFont(new Font("Monospaced", Font.ITALIC, 15));
		taOMeni.setLineWrap(true);
		taOMeni.setBounds(43, 33, 739, 228);
		panel_1.add(taOMeni);

		JButton btnIzmeniProfil = new JButton("Izmeni profil");
		btnIzmeniProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FmIzmeniProfil fmip = new FmIzmeniProfil(korisnikProfil);
				fmip.setVisible(true);

			}
		});
		btnIzmeniProfil.setBounds(755, 308, 110, 44);
		panel_Profil.add(btnIzmeniProfil);

		panel_Prijatelji = new JPanel();
		panel_Prijatelji.setBounds(156, 51, 920, 720);
		contentPane.add(panel_Prijatelji);
		panel_Prijatelji.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(51, 204, 255));
		panel_3.setBounds(0, 688, 920, 32);
		panel_Prijatelji.add(panel_3);

		JList list_Zahtevi = new JList(dlm2);
		list_Zahtevi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (list_Zahtevi.getSelectedIndex() != -1) {
					int indeks = list_Zahtevi.getSelectedIndex();
					ListPomocnik oznacenObjekat = (ListPomocnik) list_Zahtevi.getSelectedValue();
					String oznacenKorisnik = oznacenObjekat.getNaziv();
					int idOznacenogKorisnika = oznacenObjekat.getId();
					String[] niz = oznacenKorisnik.split(" ");
					Korisnik kor2 = new Korisnik(niz[0], niz[1], niz[2]);
					for (Korisnik korisnikPrimalac : listaKorisnika) {
						if (niz[2].equals(korisnikPrimalac.getKorisnickoIme())) {
							String korIme = korisnikPrimalac.getKorisnickoIme();
							Korisnik kZahtev = new Korisnik(idOznacenogKorisnika, idKorisnika);
							System.out.println(korIme);
							String zahtev = "Korisnik " + oznacenKorisnik + " vam je poslao zahtev za prijateljstvo."
									+ "\n" + "Zelite li postati prijatelji?";
							TransferKlasa tkOdgZahtev = new TransferKlasa();

							Object[] options = { "Da", "Ne" };
							int n = JOptionPane.showOptionDialog(null, zahtev, "Zahtev",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
									null);
							switch (n) {

							case 0:
								tkOdgZahtev.setOperacija(Konstante.PRIHVACEN_NAKNADNO);
								tkOdgZahtev.setKlijentObjekat(kZahtev);
								Komunikacija.vratiKomunikaciju().posalji(tkOdgZahtev);
								System.out.println("Zahtev prihvacen i poslat serveru");
								dlm2.addElement(new ListPomocnik(idPosiljaocaZahteva, zahtev));
								list_Zahtevi.remove(indeks);
								vratiListuZahteva();
								srediListuPrijatelja();

								break;

							case 1:
								tkOdgZahtev.setOperacija(Konstante.ODBIJEN);
								tkOdgZahtev.setKlijentObjekat(kZahtev);
								Komunikacija.vratiKomunikaciju().posalji(tkOdgZahtev);
								System.out.println("Zahtev odbijen i poslat serveru");
								list_Zahtevi.remove(indeks);
								vratiListuZahteva();
								break;

							default:
								break;
							}
						}
					}
				}
			}
		});

		list_Zahtevi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_Zahtevi.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		JScrollPane scrollPane_Zahtevi = new JScrollPane(list_Zahtevi);
		scrollPane_Zahtevi.setBounds(40, 48, 236, 594);
		panel_Prijatelji.add(scrollPane_Zahtevi);
		scrollPane_Zahtevi.setViewportView(list_Zahtevi);

		JLabel lblListaZahteva = new JLabel("Lista zahteva");
		lblListaZahteva.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblListaZahteva.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaZahteva.setBounds(40, 11, 236, 32);
		panel_Prijatelji.add(lblListaZahteva);

		JLabel lblListaPrijatelja_1 = new JLabel("Lista prijatelja");
		lblListaPrijatelja_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaPrijatelja_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblListaPrijatelja_1.setBounds(543, 11, 236, 32);
		panel_Prijatelji.add(lblListaPrijatelja_1);

		list_Prijatelji2 = new JList(dlm1);
		list_Prijatelji2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				String oznacenPrijatelj = list_Prijatelji2.getSelectedValue().toString();
				;
				String[] niz = oznacenPrijatelj.split(" ");
				Korisnik kor2 = new Korisnik(niz[0], niz[1], niz[2]);
				for (Korisnik korisnikPrimalac : listaKorisnika) {
					if (niz[2].equals(korisnikPrimalac.getKorisnickoIme())) {
						FmPregled fmPregled = new FmPregled(korisnikPrimalac, korisnikProfil, listaPrijatelja);
						fmPregled.setVisible(true);
					}
				}
			}
		});
		scrollPane_Prijatelji2 = new JScrollPane(list_Prijatelji2);
		scrollPane_Prijatelji2.setBounds(553, 48, 236, 570);
		panel_Prijatelji.add(scrollPane_Prijatelji2);
		scrollPane_Prijatelji2.setViewportView(list_Prijatelji2);

		panel_Grupe = new JPanel();
		panel_Grupe.setBounds(156, 51, 920, 720);
		contentPane.add(panel_Grupe);
		panel_Grupe.setLayout(null);

		tfGrupnaPoruka = new JTextField();
		tfGrupnaPoruka.setBounds(527, 631, 237, 20);
		tfGrupnaPoruka.setColumns(10);
		panel_Grupe.add(tfGrupnaPoruka);

		btnPosaljiGrupnuPoruku = new JButton("Posalji na grupu");
		btnPosaljiGrupnuPoruku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_MojeGrupe.getSelectedIndex() != -1) {
					int idGrupe = oznMG.getId();
					String grupnaPoruka = tfGrupnaPoruka.getText();
					String jaGp = "Ja  : " + grupnaPoruka;
					String imePosilj = korisnickiProfil1.getIme();
					Grupna_Poruka gp = new Grupna_Poruka(idGrupe, idKorisnika, imePosilj, grupnaPoruka);
					TransferKlasa tkgp = new TransferKlasa();
					tkgp.setOperacija(Konstante.POSALJI_GRUPNU_PORUKU);
					tkgp.setKlijentObjekat(gp);
					Komunikacija.vratiKomunikaciju().posalji(tkgp);
					taGrupnePoruke.append(jaGp + "\n");
				}
			}
		});
		btnPosaljiGrupnuPoruku.setBounds(774, 630, 117, 23);
		panel_Grupe.add(btnPosaljiGrupnuPoruku);

		btnKreirajGrupu = new JButton("Kreiraj Grupu");
		btnKreirajGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nazivGrupe = tfNazivGrupe.getText();
				Grupa g = new Grupa(nazivGrupe);
				TransferKlasa tk = new TransferKlasa();
				tk.setOperacija(Konstante.KREIRAJ_GRUPU);
				tk.setKlijentObjekat(g);
				Komunikacija.vratiKomunikaciju().posalji(tk);
				vratiListuGrupa();

			}
		});
		btnKreirajGrupu.setBounds(660, 125, 171, 23);
		panel_Grupe.add(btnKreirajGrupu);

		list_Grupe = new JList(dlm4);
		list_Grupe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ozn = (ListPomocnik) list_Grupe.getSelectedValue();

			}
		});
		scrollPane_Grupe = new JScrollPane(list_Grupe);
		scrollPane_Grupe.setBounds(40, 42, 235, 217);
		panel_Grupe.add(scrollPane_Grupe);
		scrollPane_Grupe.setViewportView(list_Grupe);

		scrollPane_PorukeGrupe = new JScrollPane();
		scrollPane_PorukeGrupe.setBounds(527, 293, 364, 327);
		panel_Grupe.add(scrollPane_PorukeGrupe);

		taGrupnePoruke = new JTextArea();
		scrollPane_PorukeGrupe.setViewportView(taGrupnePoruke);

		lblListaGrupa = new JLabel("Lista Grupa");
		lblListaGrupa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblListaGrupa.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaGrupa.setBounds(40, 17, 235, 14);
		panel_Grupe.add(lblListaGrupa);

		tfNazivGrupe = new JTextField();
		tfNazivGrupe.setBounds(622, 71, 209, 20);
		panel_Grupe.add(tfNazivGrupe);
		tfNazivGrupe.setColumns(10);

		lblNazivGrupe = new JLabel("Naziv Grupe");
		lblNazivGrupe.setBounds(505, 74, 88, 14);
		panel_Grupe.add(lblNazivGrupe);

		list_MojeGrupe = new JList(dlm5);

		list_MojeGrupe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (list_MojeGrupe.getSelectedIndex() != -1) {
					taGrupnePoruke.setText("");
					oznMG = (ListPomocnik) list_MojeGrupe.getSelectedValue();
					idOznGrupe = oznMG.getId();
					vratiListuKorisnikaUGrupi();
					vratiListuGrupnihPoruka();

				}
			}
		});
		scrollPane_MojeGrupe = new JScrollPane(list_MojeGrupe);
		scrollPane_MojeGrupe.setBounds(40, 293, 272, 327);
		panel_Grupe.add(scrollPane_MojeGrupe);

		scrollPane_MojeGrupe.setViewportView(list_MojeGrupe);

		btnDodajGrupu = new JButton("Dodaj Grupu");
		btnDodajGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int idGrupe = ozn.getId();
				Grupa kor_grupa = new Grupa(idGrupe, idKorisnika);
				TransferKlasa tkdg = new TransferKlasa();
				tkdg.setOperacija(Konstante.DODAJ_GRUPU);
				tkdg.setKlijentObjekat(kor_grupa);
				Komunikacija.vratiKomunikaciju().posalji(tkdg);
				vratiListuMojihGrupa();
			}
		});
		btnDodajGrupu.setBounds(306, 125, 125, 23);
		panel_Grupe.add(btnDodajGrupu);

		btnNapustiGrupu = new JButton("Napusti Grupu");
		btnNapustiGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idGrupe = oznMG.getId();
				Grupa gNG = new Grupa(idGrupe, idKorisnika);
				TransferKlasa tkng = new TransferKlasa();
				tkng.setOperacija(Konstante.NAPUSTI_GRUPU);
				tkng.setKlijentObjekat(gNG);
				Komunikacija.vratiKomunikaciju().posalji(tkng);
				vratiListuMojihGrupa();
				taKorisniciUGrupi.setText("");
				taGrupnePoruke.setText("");
				list_MojeGrupe.setSelectedIndex(-1);

			}
		});
		btnNapustiGrupu.setBounds(196, 630, 116, 23);
		panel_Grupe.add(btnNapustiGrupu);

		lblMojeGrupe = new JLabel("Moje Grupe");
		lblMojeGrupe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblMojeGrupe.setHorizontalAlignment(SwingConstants.CENTER);
		lblMojeGrupe.setBounds(40, 268, 272, 14);
		panel_Grupe.add(lblMojeGrupe);

		taKorisniciUGrupi = new JTextArea();
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(341, 293, 162, 327);
		panel_Grupe.add(scrollPane_2);

		scrollPane_2.setViewportView(taKorisniciUGrupi);

		JLabel lblNewLabel = new JLabel("Korisnici u grupi");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(341, 269, 162, 13);
		panel_Grupe.add(lblNewLabel);

		JLabel lblPorukeUGrupi = new JLabel("Poruke u grupi");
		lblPorukeUGrupi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPorukeUGrupi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorukeUGrupi.setBounds(527, 269, 364, 14);
		panel_Grupe.add(lblPorukeUGrupi);

		panel_Dugmici = new JPanel();
		panel_Dugmici.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_Dugmici.setBackground(new Color(102, 204, 255));
		panel_Dugmici.setBounds(10, 11, 136, 760);
		contentPane.add(panel_Dugmici);
		panel_Dugmici.setLayout(null);

		btnGrupe = new JButton("Grupe");
		btnGrupe.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGrupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Profil.setVisible(false);
				panel_Grupe.setVisible(true);
				btnGrupe.setForeground(Color.GREEN);
				btnPoruke.setForeground(Color.BLACK);
				btnPrijatelji.setForeground(Color.BLACK);
				btnProfil.setForeground(Color.BLACK);
				panel_Prijatelji.setVisible(false);
				panel_Poruke.setVisible(false);
			}
		});

		btnPrijatelji = new JButton("Prijatelji");
		btnPrijatelji.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrijatelji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Profil.setVisible(false);
				panel_Grupe.setVisible(false);
				btnPrijatelji.setForeground(Color.GREEN);
				btnGrupe.setForeground(Color.BLACK);
				btnPoruke.setForeground(Color.BLACK);
				btnProfil.setForeground(Color.BLACK);
				panel_Prijatelji.setVisible(true);
				panel_Poruke.setVisible(false);
			}
		});

		btnPoruke = new JButton("Poruke");
		btnPoruke.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPoruke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panel_Profil.setVisible(false);
				panel_Grupe.setVisible(false);
				btnPoruke.setForeground(Color.GREEN);
				btnGrupe.setForeground(Color.BLACK);
				btnPrijatelji.setForeground(Color.BLACK);
				btnProfil.setForeground(Color.BLACK);
				panel_Prijatelji.setVisible(false);
				panel_Poruke.setVisible(true);
			}
		});
		btnPoruke.setBounds(10, 252, 116, 43);
		panel_Dugmici.add(btnPoruke);
		btnPrijatelji.setBounds(10, 306, 116, 43);
		panel_Dugmici.add(btnPrijatelji);
		btnGrupe.setBounds(10, 366, 116, 43);
		panel_Dugmici.add(btnGrupe);

		btnProfil = new JButton("Profil");
		btnProfil.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_Profil.setVisible(true);
				panel_Grupe.setVisible(false);
				btnProfil.setForeground(Color.CYAN);
				btnGrupe.setForeground(Color.BLACK);
				btnPoruke.setForeground(Color.BLACK);
				btnPrijatelji.setForeground(Color.BLACK);
				panel_Prijatelji.setVisible(false);
				panel_Poruke.setVisible(false);

			}
		});
		btnProfil.setBounds(10, 198, 116, 43);
		panel_Dugmici.add(btnProfil);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 116, 117);
	/*	Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage("/logo.png");
		lblLogo.setIcon(new ImageIcon(image)); */
		lblLogo.setIcon(new ImageIcon("C:\\Users\\Eror\\eclipse-workspace\\Mini_Viber_Klijent\\logo.jpg"));
		
		
		panel_Dugmici.add(lblLogo);

		panel_Korisnik = new JPanel();
		panel_Korisnik.setBackground(new Color(51, 204, 255));
		panel_Korisnik.setBounds(156, 11, 920, 39);
		contentPane.add(panel_Korisnik);
		panel_Korisnik.setLayout(null);

		lblKorisnik = new JLabel("");
		lblKorisnik.setHorizontalAlignment(SwingConstants.CENTER);
		lblKorisnik.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
		lblKorisnik.setBounds(587, 11, 300, 14);
		panel_Korisnik.add(lblKorisnik);

		tfPretraga = new JTextField();
		tfPretraga.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				vratiSveKorisnikeZahtev();
			}
		});
		tfPretraga.setBounds(38, 10, 237, 18);
		panel_Korisnik.add(tfPretraga);
		tfPretraga.setColumns(10);

		btnPretrazi = new JButton("Pretrazi");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vratiSveKorisnikeZahtev();
				System.out.println("zahtev za listu korisnika poslat");
				pretraga = tfPretraga.getText();
				for (Korisnik lk : listaKorisnika) {
					if (lk.getIme().contains(pretraga) || lk.getPrezime().contains(pretraga)) {
						String[] red = { lk.getIme(), lk.getPrezime(), lk.getKorisnickoIme() };
						String ime = red[0] + " " + red[1] + " " + red[2];
						listaKorisnika1.add(ime);
						for (String k : listaKorisnika1) {
							System.out.println(k);
						}
					}
				}
				for (int i = 0; i < listaKorisnika1.size(); i++) {
					dlm.add(i, listaKorisnika1.get(i));
					list.setVisible(true);
				}
			}
		});
		btnPretrazi.setForeground(new Color(0, 51, 51));
		btnPretrazi.setBackground(new Color(255, 255, 255));
		btnPretrazi.setBounds(302, 9, 89, 23);
		panel_Korisnik.add(btnPretrazi);

		panel_Poruke = new JPanel();
		panel_Poruke.setBounds(156, 51, 920, 720);
		contentPane.add(panel_Poruke);
		panel_Poruke.setLayout(null);

		btnPosaljiPoruku = new JButton("Posalji");
		btnPosaljiPoruku.setBounds(736, 583, 136, 23);
		btnPosaljiPoruku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String p = tfPorukaPojedinacna.getText();
				int idPosiljalacPoruke = idKorisnika;
				int idPrimalacPoruke = idPrijatelja;
				String ja = "Ja  : " + p;
				Poruka porukaP = new Poruka(idPosiljalacPoruke, idPrimalacPoruke, p);
				System.out.println(idPosiljalacPoruke + " salje " + idPrimalacPoruke + " poruku " + p);
				primalacPoruke = new Korisnik(idPosiljalacPoruke, idPrimalacPoruke);
				Poruka poruka = new Poruka(p, primalacPoruke);
				TransferKlasa tkPojedinacnaPoruka = new TransferKlasa();
				tkPojedinacnaPoruka.setOperacija(Konstante.POSALJI_PORUKU);
				tkPojedinacnaPoruka.setKlijentObjekat(porukaP);
				Komunikacija.vratiKomunikaciju().posalji(tkPojedinacnaPoruka);
				taPojedinacnaPoruka.append(ja + "\n");

			}
		});
		panel_Poruke.add(btnPosaljiPoruku);

		scrollPane_PojedinacnaPoruka = new JScrollPane();
		scrollPane_PojedinacnaPoruka.setBounds(328, 106, 544, 450);
		panel_Poruke.add(scrollPane_PojedinacnaPoruka);
		taPojedinacnaPoruka = new JTextArea();
		taPojedinacnaPoruka.setEditable(false);
		taPojedinacnaPoruka.setTabSize(10);
		scrollPane_PojedinacnaPoruka.setViewportView(taPojedinacnaPoruka);

		list_Prijatelji = new JList(dlm1);
		JScrollPane scrollPane_Prijatelji = new JScrollPane(list_Prijatelji);
		scrollPane_Prijatelji.setBounds(10, 106, 281, 570);
		panel_Poruke.add(scrollPane_Prijatelji);
		scrollPane_Prijatelji.setViewportView(list_Prijatelji);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(328, 583, 384, 61);
		panel_Poruke.add(scrollPane_1);

		tfPorukaPojedinacna = new JTextField();
		tfPorukaPojedinacna.setColumns(10);
		scrollPane_1.setViewportView(tfPorukaPojedinacna);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 204, 255));
		panel_2.setBounds(0, 688, 920, 32);
		panel_Poruke.add(panel_2);

		JLabel lblListaPrijatelja = new JLabel("Lista prijatelja");
		lblListaPrijatelja.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		lblListaPrijatelja.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaPrijatelja.setBounds(10, 75, 281, 23);
		panel_Poruke.add(lblListaPrijatelja);
		list_Prijatelji.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				if (list_Prijatelji.getSelectedIndex() != -1 && list_Prijatelji.getSelectedIndex() != idPrijatelja) {
					vratiListuPoruka();
					index = list_Prijatelji.getSelectedIndex();
					taPojedinacnaPoruka.setText("");
					ListPomocnik oznacenObjekat = (ListPomocnik) list_Prijatelji.getSelectedValue();
					idPrijatelja = oznacenObjekat.getId();
					oznacenPrijatelj = oznacenObjekat.getNaziv();
					brojNepPoruka = oznacenObjekat.getBnp();
					TransferKlasa tknp = new TransferKlasa();
					Poruka pnp = new Poruka(idKorisnika, idPrijatelja, Konstante.PORUKA_PRIMLJENA);
					tknp.setOperacija(Konstante.PORUKA_PRIMLJENA);
					tknp.setKlijentObjekat(pnp);
					Komunikacija.vratiKomunikaciju().posalji(tknp);
					oznacenObjekat.setBnp(0);
					oznacenObjekat.setNaziv(oznacenPrijatelj);
					String oznP = oznacenPrijatelj.replaceAll(":\\d", "");
					brojSveNeprocitanePoruke -= brojNepPoruka;
					if (brojSveNeprocitanePoruke != 0) {
						btnPoruke.setText("Poruke" + " :" + brojSveNeprocitanePoruke);
					} else {
						btnPoruke.setText("Poruke");
						btnPoruke.setForeground(Color.BLACK);
					}
					oznacenObjekat.setNaziv(oznP);
					System.out.println(oznacenPrijatelj + " " + idPrijatelja);
					for (Korisnik lp : listaPrijatelja) {
						int idPrij = lp.getIdKorisnika();
						if (idPrij == idPrijatelja) {
							List<String> listaPoruka1 = hm.get(idPrij);
							for (String da : listaPoruka1) {
								taPojedinacnaPoruka.append(da + "\n");
								System.out.println(da);
							}
						}
					}
				}
			}
		});

		list = new JList(dlm);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String oznacenPrijatelj = list.getSelectedValue().toString();
				;
				String[] niz = oznacenPrijatelj.split(" ");
				Korisnik kor2 = new Korisnik(niz[0], niz[1], niz[2]);
				for (Korisnik korisnikPrimalac : listaKorisnika) {
					if (niz[2].equals(korisnikPrimalac.getKorisnickoIme())) {
						FmPregled fmPregled = new FmPregled(korisnikPrimalac, korisnikProfil, listaPrijatelja);
						fmPregled.setVisible(true);
					}
				}
			}
		});
		list.setBounds(0, 0, 237, 37);
		contentPane.add(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(183, 51, 239, 50);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(155, 51, 203, 720);
		contentPane.add(scrollPane_4);
		list.setVisible(false);
		srediFormu();
		vratiSveKorisnikeZahtev();
		vratiListuPoruka();
		vratiListuNeprimljenihPoruka();

	}

	public void srediFormuTk(TransferKlasa tk) {

		if (tk.getOperacija() == Konstante.VRATI_SVE_KORISNIKE) {
			listaKorisnika = (List<Korisnik>) tk.getServer_ObjekatOdgovor();
		}
		if (tk.getOperacija() == Konstante.VRATI_LISTU_PRIJATELJA) {
			listaPrijatelja = (List<Korisnik>) tk.getServer_ObjekatOdgovor();
			if (listaPrijatelja.isEmpty()) {
				System.out.println("Lista prijatelja je vracena prazna, nema prijatelja.");
				srediListuPrijatelja();
			} else {
				System.out.println("vracena lista prijatelja");
				for (int p = 0; p < listaPrijatelja.size(); p++) {
					System.out.println(listaPrijatelja.get(p).getKorisnickoIme());
				}
				srediListuPrijatelja();
			}
		}
		if (tk.getOperacija() == Konstante.VRATI_LISTU_ZAHTEVA) {
			System.out.println("lista zahteva je stigla");
			listaZahteva = (List<Zahtev>) tk.getServer_ObjekatOdgovor();
			if (listaZahteva.isEmpty()) {
				System.out.println("Lista zahteva je vracena prazna, nema zahteva.");
			} else {
				System.out.println("vracena lista zahteva");
				for (int p = 0; p < listaZahteva.size(); p++) {
					nazivPosiljaocaZahteva = listaZahteva.get(p).getPrimalacZahteva();
					listaPrijateljaString.add(nazivPosiljaocaZahteva);
					idPosiljaocaZahteva = listaZahteva.get(p).getId();
					System.err.println(nazivPosiljaocaZahteva);
				}
				srediListuZahteva();
			}

		}

		if (tk.getOperacija() == Konstante.REGISTRACIJA) {
			String poruka = tk.getServerPoruka_odgovor();
			JOptionPane.showMessageDialog(null, poruka);
		}

		if (tk.getOperacija() == Konstante.VRATI_LISTU_PORUKA) {
			hm = (Map<Integer, List<String>>) tk.getServer_ObjekatOdgovor();
			System.out.println("Lista poruka je stigla");
		}

		if (tk.getOperacija() == Konstante.VRATI_LISTU_NEPRIMLJENIH_PORUKA) {
			hmnp = (Map<Integer, List<String>>) tk.getServer_ObjekatOdgovor();
			System.out.println("Lista neprimljenih poruka stigla");
			srediDugmiceZaNeprocitanePoruke();
		}
		/*
		 * if (tk.getOperacija() == Konstante.POSALJI_PORUKU_SVIMA) { String poruka =
		 * tk.getServerPoruka_odgovor(); if (poruka != null && !poruka.isEmpty()) {
		 * taPoruke.append(poruka + "\n"); } }
		 */
		if (tk.getOperacija() == Konstante.PRIMLJEN) {
			String zahtev = tk.getServerPoruka_odgovor();
			// Zahtev z= (Zahtev) tk.getServer_ObjekatOdgovor();
			Zahtev kZahtev = (Zahtev) tk.getServer_ObjekatOdgovor();
			Korisnik posiljalac = kZahtev.getPosiljalac();
			idPosiljaocaZahteva = posiljalac.getIdKorisnika();

			Korisnik primalac = kZahtev.getPrimalac();
			if (zahtev != null && !zahtev.isEmpty()) {
				// JOptionPane.showConfirmDialog(null, zahtev);
				TransferKlasa tkOdgZahtev = new TransferKlasa();
				Object[] options = { "Da", "Ne" };
				int n = JOptionPane.showOptionDialog(null, zahtev, "Zahtev", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, null);
				switch (n) {
				case 0:
					tkOdgZahtev.setOperacija(Konstante.PRIHVACEN);
					tkOdgZahtev.setKlijentObjekat(kZahtev);
					Komunikacija.vratiKomunikaciju().posalji(tkOdgZahtev);
					System.out.println("Zahtev prihvacen i poslat serveru");

					vratiListuPrijatelja();
					break;
				case 1:
					tkOdgZahtev.setOperacija(Konstante.ODBIJEN);
					tkOdgZahtev.setKlijentObjekat(kZahtev);
					Komunikacija.vratiKomunikaciju().posalji(tkOdgZahtev);
					System.out.println("Zahtev odbijen i poslat serveru");
					break;
				default:
					break;
				}
			}
		}

		if (tk.getOperacija() == Konstante.POSALJI_PORUKU) {
			String pojedinacnaPoruka = tk.getServerPoruka_odgovor();
			if (pojedinacnaPoruka != null && !pojedinacnaPoruka.isEmpty()) {
				if (tk.getServerPoruka_odgovorId() == idPrijatelja) {
					taPojedinacnaPoruka.append(pojedinacnaPoruka + "\n");
				}
			}
		}
		if (tk.getOperacija() == Konstante.POSALJI_GRUPNU_PORUKU) {
			String grupnaPoruka = tk.getServerPoruka_odgovor();
			if (grupnaPoruka != null && !grupnaPoruka.isEmpty()) {
				taGrupnePoruke.append(grupnaPoruka + "\n");
			}
		}

		if (tk.getOperacija() == Konstante.KREIRAJ_GRUPU) {
			String poruka = tk.getServerPoruka_odgovor();
			JOptionPane.showMessageDialog(null, poruka);
		}

		if (tk.getOperacija() == Konstante.VRATI_LISTU_GRUPA) {
			listaGrupa = (List<Grupa>) tk.getServer_ObjekatOdgovor();
			System.out.println("Lista grupa vracena");
			srediListuGrupa();
		}
		if (tk.getOperacija() == Konstante.VRATI_LISTU_MOJIH_GRUPA) {
			listaMojihGrupa = (List<Grupa>) tk.getServer_ObjekatOdgovor();
			System.out.println("Lista mojih grupa vracena");
			srediListuMojihGrupa();
		}
		if (tk.getOperacija() == Konstante.VRATI_LISTU_GRUPNIH_PORUKA) {
			hmgp = (Map<Integer, List<String>>) tk.getServer_ObjekatOdgovor();
			System.out.println("Hash mapa grupnih poruka je stigla");
			srediListuGrupnihPoruka();
		}

		if (tk.getOperacija() == Konstante.VRATI_LISTU_KORISNIKA_U_GRUPI) {
			listaKorisnikaUGrupi = (List<Korisnik>) tk.getServer_ObjekatOdgovor();
			System.out.println("Lista korisnika u grupi je stigla");
			srediListuKorisnikaUGrupi();
		}

	}

	public String getNazivPosiljaocaZahteva() {
		return nazivPosiljaocaZahteva;
	}

	public void setNazivPosiljaocaZahteva(String nazivPosiljaocaZahteva) {
		this.nazivPosiljaocaZahteva = nazivPosiljaocaZahteva;
	}

	public void vratiSveKorisnikeZahtev() {
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_SVE_KORISNIKE);
		Komunikacija.vratiKomunikaciju().posalji(tk);
	}

	private void vratiListuPrijatelja() {
		TransferKlasa tklp = new TransferKlasa();
		tklp.setOperacija(Konstante.VRATI_LISTU_PRIJATELJA);
		tklp.setKlijentObjekat(korisnickiProfil1);
		Komunikacija.vratiKomunikaciju().posalji(tklp);
		System.out.println("zahtev za listu prijatelja poslat");
	}

	private void vratiListuZahteva() {
		dlm2.clear();
		TransferKlasa tklz = new TransferKlasa();
		tklz.setOperacija(Konstante.VRATI_LISTU_ZAHTEVA);
		tklz.setKlijentObjekat(korisnickiProfil1);
		Komunikacija.vratiKomunikaciju().posalji(tklz);
		System.out.println("zahtev za listu zahteva priajteljstava poslat");
	}

	private void vratiListuPoruka() {
		TransferKlasa tklp = new TransferKlasa();
		tklp.setOperacija(Konstante.VRATI_LISTU_PORUKA);
		tklp.setKlijentObjekat(korisnickiProfil1);
		Komunikacija.vratiKomunikaciju().posalji(tklp);
		System.out.println("zahtev za listu poruka poslat");
	}

	private void vratiListuNeprimljenihPoruka() {
		TransferKlasa tknp = new TransferKlasa();
		tknp.setOperacija(Konstante.VRATI_LISTU_NEPRIMLJENIH_PORUKA);
		tknp.setKlijentObjekat(korisnickiProfil1);
		Komunikacija.vratiKomunikaciju().posalji(tknp);
		System.out.println("zahtev za listu neprimljenih poruka poslat");
	}

	private void vratiListuGrupa() {
		TransferKlasa tklg = new TransferKlasa();
		tklg.setOperacija(Konstante.VRATI_LISTU_GRUPA);
		Komunikacija.vratiKomunikaciju().posalji(tklg);
	}

	private void vratiListuMojihGrupa() {
		TransferKlasa tklmg = new TransferKlasa();
		tklmg.setOperacija(Konstante.VRATI_LISTU_MOJIH_GRUPA);
		Grupa g = new Grupa(idKorisnika);
		tklmg.setKlijentObjekat(g);
		Komunikacija.vratiKomunikaciju().posalji(tklmg);

	}

	private void vratiListuGrupnihPoruka() {
		TransferKlasa tklgp = new TransferKlasa();
		tklgp.setOperacija(Konstante.VRATI_LISTU_GRUPNIH_PORUKA);
		Komunikacija.vratiKomunikaciju().posalji(tklgp);
		System.out.println("Lista grunpih poruka je stigla");
	}

	private void vratiListuKorisnikaUGrupi() {
		TransferKlasa tklkug = new TransferKlasa();
		tklkug.setOperacija(Konstante.VRATI_LISTU_KORISNIKA_U_GRUPI);
		Grupa grupa = new Grupa(idOznGrupe);
		tklkug.setKlijentObjekat(grupa);
		Komunikacija.vratiKomunikaciju().posalji(tklkug);
		System.out.println("Zahtev za listu korisnika u grupi je poslat");

	}

	private void srediFormu() {
		lblIme.setText(ime);
		lblKorIme.setText(korisnickoIme);
		lblPrezime.setText(prezime);
		taOMeni.setText(OMeni);
		lblDatumRodjenja.setText(datumRodjenja);
		lblKorisnik.setText(ime + "  " + prezime);

	}

	private void srediListuPrijatelja() {
		dlm1.clear();
		for (Korisnik lp : listaPrijatelja) {
			idPrijatelja = lp.getIdKorisnika();
			String[] red = { lp.getIme(), lp.getPrezime(), lp.getKorisnickoIme() };
			String ime = red[0] + " " + red[1] + " " + red[2];
			System.out.println(ime);
			listaPrijateljaString.add(ime);
			dlm1.addElement(new ListPomocnik(idPrijatelja, ime));
		}
	}

	private void srediListuZahteva() {
		dlm2.clear();
		for (Zahtev zaht : listaZahteva) {
			nazivPosiljaocaZahteva = zaht.getPrimalacZahteva();
			idPosiljaocaZahteva = zaht.getId();
			dlm2.addElement(new ListPomocnik(idPosiljaocaZahteva, nazivPosiljaocaZahteva));
			System.out.println(nazivPosiljaocaZahteva);
		}
	}

	private void srediListuGrupa() {
		dlm4.clear();
		for (Grupa grupa : listaGrupa) {
			int idGrupe = grupa.getIdGrupe();
			String nazivGrupe = grupa.getNazivGrupe();
			dlm4.addElement(new ListPomocnik(idGrupe, nazivGrupe));
			System.out.println(nazivGrupe);
		}
	}

	private void srediListuMojihGrupa() {
		dlm5.clear();
		for (Grupa grupa : listaMojihGrupa) {
			int idGrupe = grupa.getIdGrupe();
			String nazivGrupe = grupa.getNazivGrupe();
			dlm5.addElement(new ListPomocnik(idGrupe, nazivGrupe));
			System.out.println(nazivGrupe);
		}
	}

	private void srediDugmiceZaNeprocitanePoruke() {

		for (int i = 0; i < dlm1.size(); i++) {
			lp = (ListPomocnik) dlm1.getElementAt(i);
			String imeP = lp.getNaziv();
			int id = lp.getId();
			System.out.println(id);
			System.out.println(imeP);

			List<String> listaNeprocPoruka = hmnp.get(id);
			int brojPP = bnp.broj;
			for (String da : listaNeprocPoruka) {
				brojSveNeprocitanePoruke++;
				System.out.println(brojSveNeprocitanePoruke);
				brojPP++;
				System.out.println(brojPP);
			}
			if (brojPP != 0) {
				lp.setBnp(brojPP);
				lp.setNaziv(imeP + " :" + brojPP);
			}

		}
		if (brojSveNeprocitanePoruke != 0) {
			String naziv = btnPoruke.getText();
			btnPoruke.setText(naziv + " :" + brojSveNeprocitanePoruke);
			btnPoruke.setForeground(Color.RED);

		}

	}

	private void srediListuKorisnikaUGrupi() {
		dlm6.clear();
		taKorisniciUGrupi.setText("");
		List<ListPomocnik> listaAktivNeakt = new ArrayList<>();
		for (Korisnik lkug : listaKorisnikaUGrupi) {
			String ime = lkug.getIme();
			String prezime = lkug.getPrezime();
			String korIme = lkug.getKorisnickoIme();
			String[] red = { lkug.getIme(), lkug.getPrezime(), lkug.getKorisnickoIme() };
			String imeKUG = red[0] + " " + red[1] + "  (" + red[2] + ")";
			taKorisniciUGrupi.append(imeKUG + "\n");
			System.out.println("korinsik u grupi je " + imeKUG);
			dlm6.addElement(new ListPomocnik(idKorisnika, imeKUG));
		}
	}

	private void srediListuGrupnihPoruka() {
		for (Grupa lmg : listaMojihGrupa) {
			int idGrupe = lmg.getIdGrupe();
			if (idGrupe == idOznGrupe) {
				listaGrupnihPoruka = hmgp.get(idGrupe);
				for (String da : listaGrupnihPoruka) {
					taGrupnePoruke.append(da + "\n");
					System.out.println(da);
				}
			}
		}
	}
}
