package aufgabe2;

import java.util.List;

public interface BriefdruckStrategie {
	
	public void druckeBrief(Adresse sender, Adresse empfaenger, Inhalt inhalt);
	
	public void druckeSerienbrief(Adresse sender, List<Adresse> listeEmpfaenger, Inhalt inhalt);
}
