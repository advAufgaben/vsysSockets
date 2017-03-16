package verbindungslos;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

public class Artikel implements Serializable{
	
	private static final String separator = "!";
	
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
		System.out.println("ID: " + this.ID);
		System.out.println("Name: " + this.Name);
		System.out.println("Preis: " + this.Preis);
	}
	
	public byte[] serializeViaSerializable(){
		return SerializationUtils.serialize(this);
	}
	
	public byte[] serialize(){
		String concatenation = this.ID + separator + this.Name + separator + this.Preis;
		return concatenation.getBytes();
	}
	
	public static Artikel deserializeViaSerializable(byte[] array){
		return SerializationUtils.deserialize(array);
	}
	
	public static Artikel deserialize(byte[] array, int length){
		String result = new String(array, 0, length);
		String[] parts = result.split(separator);
		Artikel artikel = new Artikel(Integer.valueOf(parts[0]), parts[1], Double.valueOf(parts[2]));
		return artikel;
	}
}
