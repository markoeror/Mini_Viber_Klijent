package comtrade.forma.Log;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import comtrade.kontroler.Kontroler;

public class Chat {
	private Chat ime;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private static String naziv;
	private JPanel contentPane;
	private static Chat instanca;
	private String text;
	private List<String> listaPoruka= new ArrayList<>();
	
	
	public String getText() {
		return text;
	}
	public void postaviChat(String naziv) {
		this.naziv=naziv;

	}
	
	public static Chat vratiInstancu() {
		if (instanca == null) {
			instanca = new Chat(naziv);			
			System.out.println("chat "+naziv);
			
		}
		return instanca;
	}
	
	public Chat(String naziv) {
		srediScrollPane();
	}
	public void srediScrollPane() {
		textArea = new JTextArea();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 99, 394, 369);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textArea);
		System.out.println("Scroll Pane "+ naziv);
	
	
	}
	
	public void dodajUTextAreu(String text){
		
		System.out.println(naziv);
		textArea.setText("");
		if(text!=null && naziv!=null) {		
		listaPoruka.add(text);
		for(String lp:listaPoruka) {
			textArea.append(lp+"\n");
		//ta.append(naziv+" kaze :  "+text+"\n");
		}}
	}
	
	

	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public Chat(JTextArea textArea) {
		super();
		this.textArea = textArea;
	}
	

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	

	public void postaviContentPane(JPanel contentPane) {
		this.contentPane=contentPane;
		
	}
	public static String getNaziv() {
		return naziv;
	}

	public static void setNaziv(String naziv) {
		Chat.naziv = naziv;
	}
	public void postaviText(String text) {
		this.text=text;
		
	}
	
	
}
