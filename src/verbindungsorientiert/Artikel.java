package verbindungsorientiert;

import java.io.Serializable;

public class Artikel implements Serializable {

	private static final long serialVersionUID = 1L;

	public int		ID;
	public String	Name;
	public double	Preis;

	public Artikel() {
		this.ID = 0;
		this.Name = null;
		this.Preis = 0.0;
	}

	public Artikel(int i, String s, double d) {
		this.ID = i;
		this.Name = s;
		this.Preis = d;
	}

	public void ausgeben() {
		System.out.println("\tID: " + this.ID);
		System.out.println("\tName: " + this.Name);
		System.out.println("\tPreis: " + this.Preis);
	}

}
