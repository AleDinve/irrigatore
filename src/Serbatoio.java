/**
 * 
 * @author Bertuccio Antonino, D'Inverno Giuseppe Alessio
 *
 */
public class Serbatoio {
	
	private double portata;
	private int capienza;
	private int capacita;
	private String nome;
	private final static String NOME="Default";
	/**
	 * Viene inizializzato un serbatoio di capienza "capienza", portata "portata" e
	 * nome "Default".
	 * @param capienza	Capienza del serbatoio
	 * @param portata	Portata del serbatoio
	 */
		public Serbatoio(int capienza, double portata){
			this(capienza, portata, NOME);
		}
		
		/**
		 * Viene inizializzato un serbatoio di capienza "capienza", portata "portata" e
		 * nome "nome".
		 * @param capienza	Capienza assegnata al serbatoio
		 * @param portata	Portata assegnata al serbatoio
		 * @param nome		Nome assegnato al serbatoio
		 */
		public Serbatoio(int capienza, double portata, String nome){
			this.nome=nome;
			this.capienza=capienza;
			this.capacita=capienza;
			this.portata=portata;
			
		}
		
		/**
		 * Restituisce una stringa con nome, capienza, 
		 * capacità attuale e portata del serbatoio.
		 */
		public String toString(){
			return "Serbatoio " + nome + ": Capienza " + this.capienza + 
					" l, Capacità attuale " + capacita + " l, Portata " + portata + " l/m";
		}
		
		/**
		 * Restituisce la portata del serbatoio.
		 * @return this.portata	Portata del serbatoio
		 */
		public double getPortata() {
			return this.portata;
		}
		
		/**
		 * Restituisce la capienza del serbatoio.
		 * @return this.capienza	Capienza del serbatoio
		 */
		public int getCapienza() {
			return this.capienza;
		}
		
		/**
		 * Restituisce il nome del serbatoio.
		 * @return this.nome	Nome del serbatoio
		 */
		public String getNome() {
			return this.nome;
		}
		
		/**
		 * Restituisce la capacità attuale del serbatoio.
		 * @return this.capacita	Capacità attuale del serbatoio
		 */
		public double getCapacitaAttuale()
		{
			return this.capacita;
		}
		
		/**
		 * Utilizza il serbatoio riducendo la capacità attuale della quantità di acqua
		 * erogata.
		 * @param acqua	Litri di acqua erogati da ogni serbatoio
		 */
		public void utilizza(double acqua) {
			this.capacita-=acqua;
			
		}
		
		/**
		 * Riempie totalmente il serbatoio, inizializzando la capacità alla capienza
		 * iniziale.
		 */
		public void riempi() {
			this.capacita=this.capienza;
			
		}
}
