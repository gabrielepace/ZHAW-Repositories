package aufgabe2;

import java.util.List;

public class KonsoleDrucken implements BriefdruckStrategie {
	
	@Override
	public void druckeBrief(Adresse sender, Adresse empfaenger, Inhalt inhalt) {

		Brief brief = new Brief(sender, empfaenger, inhalt);
		System.out.println(brief);
	}
	
	@Override
	public void druckeSerienbrief(Adresse sender, List<Adresse> listeEmpfaenger, Inhalt inhalt) {

		for (Adresse empfaenger : listeEmpfaenger) {

			Brief brief = new Brief(sender, empfaenger, inhalt);
			System.out.println(brief);
		}
	}
	

}
