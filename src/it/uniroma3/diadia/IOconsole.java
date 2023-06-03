package it.uniroma3.diadia;

import java.util.Scanner;

public class IOconsole implements IO{
	private Scanner scanner;
	public IOconsole(Scanner scanner) {
		this.scanner = scanner;
	}
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	@Override
	public String leggiRiga() {
		//Scanner scannerDiLinee = new Scanner(System.in);
		Scanner scannerDiLinee = this.scanner;
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
}
