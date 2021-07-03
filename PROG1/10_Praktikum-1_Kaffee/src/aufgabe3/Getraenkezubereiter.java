package aufgabe3;

import java.util.ArrayList;
import java.util.List;

public class Getraenkezubereiter {

	public static void main(String[] args) {

		List<KoffeinGetraenk> koffeinGetraenk = new ArrayList<KoffeinGetraenk>();

		koffeinGetraenk.add(new Kaffee());
		koffeinGetraenk.add(new Tee());

		for (KoffeinGetraenk getraenk : koffeinGetraenk) {
			getraenk.bereiteZu();
		}

	}

}
