package comtrade.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import comtrade.server.IPortServera;
import comtrade.transfer.TransferKlasa;

public class Komunikacija implements IPortServera {

	private static Komunikacija instanca;
	private Socket soket;
	private TransferKlasa tk;

	private NitCitanje nc;

	private Komunikacija() {
		try {
			soket = new Socket("127.0.0.1", PORT);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Komunikacija vratiKomunikaciju() {
		if (instanca == null) {
			instanca = new Komunikacija();

		}
		return instanca;
	}

	public void posalji(TransferKlasa tk) { // 5.1 deo. nakon pravljenja Tklase u dugmetu sacuvaj pravimo metodu
											// posalji, koja u sebi ima transfer klasu
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(soket.getOutputStream());
			outStream.writeObject(tk);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSoket() {
		return soket;
	}

	public void setSoket(Socket soket) {
		this.soket = soket;
	}
// to treba da idem tamo
//	a sta sit i ovde probao
//	ovde nema nista sto ima smisla da radi
//	kkomunikacija je ok kao klasa
//	sitne mozda neke izmene ali to ove metode su dobre i to je vrvatno isto kaou onom zadatku
	
	public TransferKlasa procitaj() {
		
			ObjectInputStream ois = null; // ovo se stavlja u slucaju da pukne try catch i da ima da pokaze na null
			try {
				ois = new ObjectInputStream(soket.getInputStream());

			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				return (TransferKlasa) ois.readObject(); // odgovor sa servera ce biti Tklasa koja moze u sebi imati
															// objekat, poruku
				// radimo kastovanje u TransferKlasu
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		
		


}
