package comtrade.forma.Log;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comtrade.domen.Grupa;
import comtrade.domen.Korisnik;
import comtrade.domen.ListPomocnik;
import comtrade.komunikacija.Komunikacija;
import comtrade.komunikacija.NitCitanje;
import comtrade.konstante.Konstante;
import comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class FmAdmin extends JFrame {

	private JPanel contentPane;
	private JList listGrupe;
	private JScrollPane scrollPaneGrupe;
	private JList listKorisnici;
	private JScrollPane scrollPaneKorisnici;
	private DefaultListModel dlm= new DefaultListModel<>();
	private DefaultListModel dlm1=  new DefaultListModel<>();
	private List<Korisnik> listaKorisnika = new ArrayList<>();
	private List<Grupa> listaGrupa = new ArrayList<>();
	
	public FmAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Socket soket = Komunikacija.vratiKomunikaciju().getSoket();
		NitCitanje nitCitanje = new NitCitanje(soket);
		nitCitanje.start();
		nitCitanje.postaviAdminFormu(this);
		
		JLabel lblAdministrator = new JLabel("Administrator");
		lblAdministrator.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrator.setBounds(159, 11, 247, 20);
		contentPane.add(lblAdministrator);
		
		vratiSveKorisnikeZahtev();
		vratiListuGrupa();
		JLabel lblKorisnici = new JLabel("Svi korisnici");
		lblKorisnici.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblKorisnici.setHorizontalAlignment(SwingConstants.CENTER);
		lblKorisnici.setBounds(82, 67, 121, 14);
		contentPane.add(lblKorisnici);
		
		JLabel lblGrupe = new JLabel("Sve Grupe");
		lblGrupe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblGrupe.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupe.setBounds(315, 67, 121, 14);
		contentPane.add(lblGrupe);
		
		listKorisnici = new JList(dlm);
		scrollPaneKorisnici = new JScrollPane(listKorisnici);
		scrollPaneKorisnici.setBounds(23, 104, 236, 371);
		contentPane.add(scrollPaneKorisnici);
		scrollPaneKorisnici.setViewportView(listKorisnici);
		
		listGrupe = new JList(dlm1);
		scrollPaneGrupe = new JScrollPane(listGrupe);
		scrollPaneGrupe.setBounds(269, 104, 247, 371);
		contentPane.add(scrollPaneGrupe);
		scrollPaneGrupe.setViewportView(listGrupe);
		
		srediListuKorisnika();
		srediListuGrupa();
	}

	public void srediFormuTk(TransferKlasa tk) {
		if (tk.getOperacija() == Konstante.VRATI_SVE_KORISNIKE_ADMIN) {
			listaKorisnika = (List<Korisnik>) tk.getServer_ObjekatOdgovor();
			System.out.println("Lista korisnika vracena");
			srediListuKorisnika();
		}
		if(tk.getOperacija() == Konstante.VRATI_LISTU_GRUPA_ADMIN) {
			listaGrupa = (List<Grupa>) tk.getServer_ObjekatOdgovor();
			System.out.println("Lista grupa vracena");
			srediListuGrupa();
			
		}
		
	}
	
	private void srediListuGrupa() {
		dlm1.clear();
		for (Grupa grupa : listaGrupa) {
			int idGrupe = grupa.getIdGrupe();
			String nazivGrupe = grupa.getNazivGrupe();
			dlm1.addElement(new ListPomocnik(idGrupe, nazivGrupe));
			System.out.println(nazivGrupe);
		}
	
		
	}

	private void srediListuKorisnika() {
		dlm.clear();
		for (Korisnik lk : listaKorisnika) {
			if(lk.getPravaPristupa()==Konstante.KORISNIK) {
			int IdKorisnika=lk.getIdKorisnika();		
			String[] red = { lk.getIme(), lk.getPrezime(), lk.getKorisnickoIme() };
			String ime = red[0] + " " + red[1] + " " + red[2];
			System.out.println(ime);
			dlm.addElement(new ListPomocnik(IdKorisnika, ime));
		}
	
		}
	}

	public void vratiSveKorisnikeZahtev() {
		TransferKlasa tk = new TransferKlasa();
		tk.setOperacija(Konstante.VRATI_SVE_KORISNIKE_ADMIN);
		Komunikacija.vratiKomunikaciju().posalji(tk);
	}
	
	private void vratiListuGrupa() {
		TransferKlasa tklg = new TransferKlasa();
		tklg.setOperacija(Konstante.VRATI_LISTU_GRUPA_ADMIN);
		Komunikacija.vratiKomunikaciju().posalji(tklg);
	}
}
