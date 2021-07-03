public class Simulation {
	
	Betreuungsverhaeltnis betreuungsverhaeltnis;

	public Simulation() {
		Dozent dozent = new Dozent("Albert Einstein", "1234-5678", "TG210", "058-9347259");
		betreuungsverhaeltnis = new Betreuungsverhaeltnis(dozent);
	}

	private void simulieren() {
		betreuungsverhaeltnis.studentZufuegen(new Student("Adam Alder", "abcd-efgh"));
		betreuungsverhaeltnis.studentZufuegen(new Student("Bea Bingo", "ace-gikm"));
		betreuungsverhaeltnis.studentZufuegen(new Student("Clea Clever", "bdfh-jlnp"));
		betreuungsverhaeltnis.studentZufuegen(new Student("Dino Dasen", "qwer-tzui"));
		betreuungsverhaeltnis.studentZufuegen(new Student("Eva Ente", "asdf-ghjk"));
		betreuungsverhaeltnis.studentZufuegen(new Student("Fritz Floh", "yxcv-bnmm"));
		betreuungsverhaeltnis.studentZufuegen(new Student("Geri Gugger", "uvwa-xyzb"));
		betreuungsverhaeltnis.verteileCredits();
		betreuungsverhaeltnis.ausgeben();
	}

	public static void main(String[] args) {
		(new Simulation()).simulieren();
	}
}
