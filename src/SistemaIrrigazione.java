import java.io.*;

/**
 * 
 * @author Bertuccio Antonino, D'Inverno Giuseppe Alessio
 *
 */
public class SistemaIrrigazione {
	private double portataTot=0;
	private double portataRes=0;
	private int capienzaTot=0;
	private int capacitaResidua=0;
	private Serbatoio[] serbatoi;
	private Spruzzatore[] spruzzatori;
	public final int MAX_SERBATOI;
	public final int MAX_SPRUZZATORI;

	int numSerb=0;
	int numSpruz=0;

	/**
	 * Il costruttore inizializza il sistema assegnando il numero massimo di
	 * serbatoi e il numero massimo di spruzzatori.
	 * 
	 * @param maxserbatoi    Numero massimo di serbatoi
	 * @param maxspruzzatori Numero massimo di spruzzatori
	 */
	public SistemaIrrigazione(int maxserbatoi, int maxspruzzatori) {
		MAX_SERBATOI = maxserbatoi;
		MAX_SPRUZZATORI = maxspruzzatori;
		serbatoi = new Serbatoio[MAX_SERBATOI];
		spruzzatori = new Spruzzatore[MAX_SPRUZZATORI];
		}

	/**
	 * Il metodo restituisce false se i serbatoi hanno già raggiunto il numero
	 * massimo; altrimenti, aggiunge un serbatoio e incrementa portata totale,
	 * portata residua, capienza totale e capacità residua del sistema, e
	 * restituisce true.
	 * 
	 * @param serb Serbatoio
	 * @return boolean
	 */
	public boolean aggiungiSerbatoio(Serbatoio serb) {
		if (this.numSerb == MAX_SERBATOI)
			return false;
		this.serbatoi[this.numSerb] = serb;
		this.portataTot += serb.getPortata();
		this.portataRes += serb.getPortata();
		this.capienzaTot += serb.getCapienza();
		this.capacitaResidua += serb.getCapacitaAttuale();
		this.numSerb++;
		return true;
	}

	/**
	 * Il metodo restituisce false se gli spruzzatori hanno già raggiunto il numero
	 * massimo; altrimenti, aggiunge uno spruzzatore e incrementa portata residua e
	 * restituisce true.
	 * 
	 * @param spruz Spruzzatore
	 * @return boolean
	 */
	public boolean aggiungiSpruzzatore(Spruzzatore spruz) {
		if (numSpruz == MAX_SPRUZZATORI || (portataRes - spruz.getPortata() < 0))
			return false;
		this.spruzzatori[numSpruz] = spruz;
		this.portataRes -= spruz.getPortata();
		this.numSpruz++;
		return true;
	}

	/**
	 * Il metodo restituisce la portata totale del sistema.
	 * 
	 * @return this.portataTot
	 */
	public double getPortata() {
		return this.portataTot;
	}

	/**
	 * Il metodo restituisce la portata inutilizzata del sistema.
	 * 
	 * @return this.portataRes
	 */
	public double getPortataInutilizzata() {
		return this.portataRes;
	}

	/**
	 * Il metodo restituisce la capienza totale del sistema.
	 * 
	 * @return this.capienzaTot
	 */
	public int getCapienza() {
		return this.capienzaTot;
	}

	/**
	 * Il metodo restituisce la capacità residua del sistema.
	 * 
	 * @return this.capacitaResidua
	 */
	public int getCapacitaResidua() {
		return this.capacitaResidua;
	}

	/**
	 * Il metodo restituisce come String l'elenco dei serbatoi con le relative
	 * capacità residue.
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < numSerb; i++) {
			s = s + "Serbatoio " + serbatoi[i].getNome() + " capacità attuale " + (int) serbatoi[i].getCapacitaAttuale()
					+ " - ";
		}
		if(s=="")
			return s;
		return s.substring(0, s.length() - 3);

	}

	/**
	 * Il metodo attiva gli spruzzatori per la quantità "minuti" di tempo ed eroga
	 * un numero di litri pari alla portata totale degli spruzzatori moltiplicata
	 * per il tempo.
	 * 
	 * @param minuti Tempo di attivazione degli spruzzatori
	 * @return litri Quantità di acqua erogata in totale.
	 */
	public double attiva(int minuti) {
		int numSerbDin = numSerb;
		double litri = (this.portataTot - this.portataRes) * minuti;
		double litriDin = litri;
		if (this.capacitaResidua < litri) {
			System.out.println("Il numero di litri da erogare è maggiore "
					+ "della capacità attuale");
			return 0;}
		double portataAt = litri / numSerb;
		int[] pos = new int[numSerb];
		for (int i = 0; i < numSerb; i++)
			if (serbatoi[i].getCapacitaAttuale() < portataAt) {
				litriDin -= serbatoi[i].getCapacitaAttuale();
				serbatoi[i].utilizza(serbatoi[i].getCapacitaAttuale());
				this.capacitaResidua -= serbatoi[i].getCapacitaAttuale();
				pos[i] = -1;
				numSerbDin--;
				portataAt = litriDin / numSerbDin;

			}

		for (int i = 0; i < numSerb; i++)
			if (pos[i] != -1)
				serbatoi[i].utilizza(portataAt);
		return litri;
	}

	/**
	 * Il metodo attiva gli spruzzatori per un minuto.
	 * 
	 */
	public double attiva() {
		return attiva(1);
	}

	/**
	 * Il metodo stampa l'elenco dei serbatoi con i relativi parametri e l'elenco
	 * degli spruzzatori con i relativi parametri.
	 */
	public void printStato() {
		for (int i = 0; i < numSerb; i++)
			System.out.println(serbatoi[i].toString());
		for (int i = 0; i < numSpruz; i++)
			System.out.println(spruzzatori[i].toString());
	}

	/**
	 * Il metodo riempie tutti i serbatoi fino alla capienza massima.
	 */
	public void riempiSerbatoi() {
			this.capacitaResidua=0;
		for (int i = 0; i < numSerb; i++) {
			serbatoi[i].riempi();
			this.capacitaResidua += serbatoi[i].getCapienza();
		}
	}
	
	public void readStatus(String file) throws IOException {
		FileReader in = new FileReader("src/"+file+".txt");
		BufferedReader bIn=new BufferedReader(in);
		String c="";
		c= bIn.readLine();
		while(c!=null) {
		System.out.println(c);
		c= bIn.readLine();
		}
		try {	in.close();}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public void writeStatus(String file) throws IOException {
		FileWriter out= new FileWriter("src/"+file+".txt");
		BufferedWriter bOut=new BufferedWriter(out);
		for (int i=0; i<serbatoi.length; i++)
		{	
			bOut.write("Serbatoio "+serbatoi[i].getNome()+": capacità "+serbatoi[i].getCapacitaAttuale()+"\n");
			bOut.flush();
		}
		bOut.close();
		
	}

}