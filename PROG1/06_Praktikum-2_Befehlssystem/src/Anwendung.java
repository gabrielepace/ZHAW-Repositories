public class Anwendung {

	public void start() {

		System.out.println("Kommand eingeben:\n");

		Parser parser = new Parser();
		
		Kontroller kontroller = new Kontroller();
        boolean goOn = true;
		
		
        do {
			Befehl befehl = parser.liefereBefehl();
			goOn = kontroller.verarbeiteBefehl(befehl);
		} while(goOn);
		
	}

	public static void main(String[] args) {

		Anwendung anwendung = new Anwendung();
		anwendung.start();

	}

}
