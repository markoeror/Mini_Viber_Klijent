package comtrade.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import comtrade.domen.Korisnik;
import comtrade.forma.FmServer;
import comtrade.forma.Log.FmAdmin;
import comtrade.forma.Log.FmLog;
import comtrade.forma.Log.FmProfil;
import comtrade.konstante.Konstante;
import comtrade.transfer.TransferKlasa;

public class NitCitanje extends Thread {
	private TransferKlasa tk;
	private Socket soket;
	private FmProfil fmProfil;
	private FmAdmin fmAdmin;

	public NitCitanje(TransferKlasa tk) {
		super();
		this.tk = tk;
	}

	public void postaviFormu(FmProfil fmProfil) {
		this.fmProfil = fmProfil;
	}

	public void postaviAdminFormu(FmAdmin fmAdmin) {
		this.fmAdmin = fmAdmin;
	}

	public NitCitanje(Socket soket) {
		super();
		this.soket = soket;
	}

	public void run() {
		while (true) {
			try {
				// System.out.println("cekam objekat");
				ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
				// System.out.println("objekat stigao");
				tk = (TransferKlasa) ois.readObject();
				System.out.println("objekat stigao i procitan");
				System.out.println("Operacija sa servera je: " + tk.getOperacija());		
				if(tk.getOperacija()== Konstante.VRATI_SVE_KORISNIKE_ADMIN) {
					fmAdmin.srediFormuTk(tk);
					
				}else if(tk.getOperacija()== Konstante.VRATI_LISTU_GRUPA_ADMIN) {
					fmAdmin.srediFormuTk(tk);
				}else
				fmProfil.srediFormuTk(tk);
			

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
