package aufgabe6;

import java.util.ArrayList;
import java.util.List;

public class Getraenkezubereiter {

	public static void main(String[] args) {

		List<Trinkbar> koffeinGetraenk = new ArrayList<Trinkbar>();

		koffeinGetraenk.add(new Kaffee());
		koffeinGetraenk.add(new Tee());

		for (Trinkbar getraenk : koffeinGetraenk) {
			((KoffeinGetraenk) getraenk).bereiteZu();
			System.out.println("\n");
		}

	}

}
