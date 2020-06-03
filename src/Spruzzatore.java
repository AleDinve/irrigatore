/**
 * 
 * @author Bertuccio Antonino, D'Inverno Giuseppe Alessio
 *
 */
public class Spruzzatore {
	private double portata;
	private String nome;
	private final static double PORTATA_DEFAULT=5.0;
	private final static String NOME_DEFAULT="Default";
	
	/**
	 * Crea lo spruzzatore di portata default 5.0 e nome "Default"
	 */
	public Spruzzatore() {
		this(PORTATA_DEFAULT, NOME_DEFAULT);
	}
	
	/**
	 * Crea lo spruzzatore di portata "portata" e nome "Default"
	 * @param portata	Portata dello spruzzatore
	 */
	public Spruzzatore(double portata) {
		this(portata, NOME_DEFAULT);
	}
	
	/**
	 * Crea lo spruzzatore di portata "portata" e nome "nome"
	 * @param portata	Portata dello spruzzatore
	 * @param nome	Nome dello spruzzatore
	 */
	public Spruzzatore(double portata, String nome) {
		this.portata=portata;
		this.nome=nome;
	}
	
	/**
	 * Il metodo restituisce la stringa con nome dello spruzzatore e portata.
	 */
	public String toString(){
		return "Spruzzatore " + nome + ": Portata " + portata + " l/m";
	}
	
	/**
	 * Restituisce la portata dello spruzzatore.
	 * @return this.portata	Portata dello spruzzatore
	 */
	public double getPortata()
	{
		return this.portata;
	}
	
	public String getNome() {
		return this.nome;
	}
}
