import java.io.IOException;

public class TestIrrigazione {

	public static void main(String[] args) throws IOException {
		
		//un serbatoio è definito dalla sua capienza in litri e portata in litri/minuto
		Serbatoio serbatoio1 = new Serbatoio(100, 10.0);
		assert serbatoio1.getCapienza() == serbatoio1.getCapacitaAttuale();
		assert serbatoio1.getCapacitaAttuale() == 100;
		assert serbatoio1.getPortata() == 10d;
		
		
		//Opzionalmente può avere un nome
		Serbatoio serbatoio2 = new Serbatoio(50, 20.0, "Nord");
		
		
		assert serbatoio1.getNome().equals("Default");
//		System.out.println(serbatoio2);
		assert serbatoio2.toString().equals("Serbatoio Nord: Capienza 50 l, Capacità attuale 50 l, Portata 20.0 l/m");
		
		
		//Uno spruzzatore è definito da quanta acqua eroga in l/m e opzionalmente da un nome
		Spruzzatore spruzzatore1 = new Spruzzatore(15.0);
		Spruzzatore spruzzatore2 = new Spruzzatore(10.0, "Sud");
		
		//lo spruzzatore di default eroga 5 l/m
		Spruzzatore spruzzatore3 = new Spruzzatore();
//		System.out.println(spruzzatore3);

		assert spruzzatore3.getPortata() == 5.0;
		assert spruzzatore3.toString().equals("Spruzzatore Default: Portata 5.0 l/m");
	

		// Un sistema di irrigazione ha un numero massimo di serbatoi
		// e un numero massimo di spruzzatori
		SistemaIrrigazione sistema = new SistemaIrrigazione(2, 3);
		assert sistema.MAX_SERBATOI == 2;
		assert sistema.MAX_SPRUZZATORI == 3;
		
		assert sistema.aggiungiSerbatoio(serbatoio1);
		assert sistema.aggiungiSerbatoio(serbatoio2);


		
		//non si dovrebbero poter aggiungere 3 serbatoi
		assert !sistema.aggiungiSerbatoio(new Serbatoio(1, 1));
		
		assert sistema.getPortata() == 30d;
		assert sistema.getCapienza() == 150;
		assert sistema.getCapacitaResidua() == 150;
		
		// è possibile aggiungere uno spruzzatore solo se l'impianto ha abbastanza portata
		assert sistema.aggiungiSpruzzatore(spruzzatore1);
		assert sistema.getPortataInutilizzata() == 15d;
		
		assert !sistema.aggiungiSpruzzatore(new Spruzzatore(20));
		
		assert sistema.aggiungiSpruzzatore(spruzzatore2);
		assert sistema.aggiungiSpruzzatore(spruzzatore3);
		assert sistema.getPortataInutilizzata() == 0;
		
		sistema.printStato();
		
		System.out.println();
		System.out.println(sistema.toString());
		assert sistema.toString().equals("Serbatoio Default capacità attuale 100 - Serbatoio Nord capacità attuale 50");
		
		//attiva tutti gli spruzzatori del sistema per un minuto
		//restituisce i litri usati
		//assumete che tutti i serbatoi si svuotino alla stessa velocità,
		// non tenendo conto della portata (per questa volta)
		assert sistema.attiva() == 30.0; 
		
		System.out.println(sistema.toString());
		assert sistema.toString().equals("Serbatoio Default capacità attuale 85 - Serbatoio Nord capacità attuale 35");

		//stampa l'elenco dei serbatoio e il loro stato, e l'elenco degli spruzzatori
		sistema.printStato();
		
		//attiva tutti gli spruzzatori del sistema per 2 minuti
		//restituisce i litri usati
		assert sistema.attiva(2) == 60.0;
		
		System.out.println(sistema);
		assert sistema.toString().equals("Serbatoio Default capacità attuale 55 - Serbatoio Nord capacità attuale 5");

		//il sistema si blocca e non eroga acqua
		//se anche solo uno dei serbatoio non ha abbastanza acqua per soddisfare la richiesta
//		assert sistema.attiva() == 0;	
		System.out.println(sistema);
		assert sistema.toString().equals("Serbatoio Default capacità attuale 55 - Serbatoio Nord capacità attuale 5");
		
		sistema.attiva();
		System.out.println(sistema);
		
		sistema.riempiSerbatoi();
		System.out.println(sistema);
		assert sistema.toString().equals("Serbatoio Default capacità attuale 100 - Serbatoio Nord capacità attuale 50");
		sistema.attiva(2);
//		
//		sistema.attiva(6);
//		System.out.println(sistema);
		
		sistema.writeStatus("prova");
		sistema.readStatus("prova");
	}
	
	
}
