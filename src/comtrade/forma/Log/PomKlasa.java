package comtrade.forma.Log;

public class PomKlasa {
	String naziv;
	int broj;
	@Override
	public String toString() {
		return naziv +" "+ broj;
	}
	public PomKlasa() {
		super();
	}
	public PomKlasa(String naziv, int broj) {
		super();
		this.naziv = naziv;
		this.broj = broj;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
}
