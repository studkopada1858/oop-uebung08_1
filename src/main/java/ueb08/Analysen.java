package ueb08;


import java.io.IOException;
import java.util.*;


class Analysen {

	/**
	 * Wie viele Tore fallen durchschnittlich in jedem Spiel?
	 */
	static double torstatistikenToreProSpiel() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int anzTore = 0;
		for (Spiel s : bl.spiele)
			anzTore = anzTore + s.getToreGast() + s.getToreHeim();

		return (double) anzTore / bl.spiele.size();
	}

	/**
	 * Wie viele Tore fallen durchschnittlich in einem Spiel der 1. Liga?
	 */
	static double torstatustikenToreProErstligaspiel() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int anzTore = 0;
		int anzSpiele = 0;
		for (Spiel s : bl.spiele) {
			// 18 Teams in der 1. Liga
			if (s.getGast() <= 18 && s.getHeim() <= 18) {
				anzTore = anzTore + s.getToreGast() + s.getToreHeim();
				anzSpiele += 1;
			}
		}

		return (double) anzTore / anzSpiele;
	}

	/**
	 * Wie viele Tore fallen durchschnittlich an einem Spieltag der 2. Liga?
	 */
	static double torstatistikenToreProSpieltag2teLiga() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		List<Spiel> spiele = new LinkedList<>();
		for (Spiel s : bl.spiele) {
			// keine Cross-Liga-Spiele...
			if (bl.vereine.get(s.getHeim()).getLiga() == 2)
				spiele.add(s);
		}

		// nach Spieltag sortieren
		spiele.sort(new Comparator<Spiel>() {
			@Override
			public int compare(Spiel o1, Spiel o2) {
				return Integer.compare(o1.getSpieltag(), o2.getSpieltag());
			}
		});

		int tag = -1;
		int anzSpieltage = 0;
		int anzTore = 0;
		for (Spiel s : spiele) {
			if (tag != s.getSpieltag()) {
				anzSpieltage += 1;
				tag = s.getSpieltag();
			}

			anzTore = anzTore + s.getToreHeim() + s.getToreGast();
		}

		return (double) anzTore / anzSpieltage;
	}

	/**
	 * Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore fallen, wie in den Abendspielen?
	 */
	static boolean torstatistikenToreNachmittagsAbends() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int toreNachmittag = 0;
		int spieleNachmittag = 0;
		int toreAbends = 0;
		int spieleAbends = 0;

		for (Spiel s : bl.spiele) {
			// Nachmittagsspiel?
			if (s.getUhrzeit().equals("15:30:00")) {
				toreNachmittag = toreNachmittag + s.getToreGast() + s.getToreHeim();
				spieleNachmittag += 1;
			}else {
				// ansonsten Abend
				toreAbends = toreAbends + s.getToreHeim() + s.getToreGast();
				spieleAbends += 1;
			}
		}

		double tn = (double) toreNachmittag / spieleNachmittag;
		double ta = (double) toreAbends / spieleAbends;

		System.out.println(tn + " " + ta);

		return tn > ta;
	}

	/**
	 * Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen als auswärts?
	 */
	static boolean torstatistikenToreDaheim() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int anzDaheim = 0;
		int anzAuswaerts = 0;
		int anzSpiele = 0;
		for (Spiel s : bl.spiele) {
			// 3. Liga: Vereine 37...56
			if (s.getGast() <= 36)
				continue;

			anzDaheim += s.getToreHeim();
			anzAuswaerts += s.getToreGast();
			anzSpiele += 1;
		}

		double td = (double) anzDaheim / anzSpiele;
		double ta = (double) anzAuswaerts / anzSpiele;

		System.out.println(td + " " + ta);

		return td < ta;
	}

	/**
	 * Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
	 */
	static int vereineToreVerein1erzielt() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int anzTore = 0;
		for (Spiel s : bl.spiele) {
			if (s.getHeim() == 1)
				anzTore += s.getToreHeim();
			else if (s.getGast() == 1)
				anzTore += s.getToreGast();
		}

		return anzTore;
	}

	/**
	 * Wie viele Tore hat der FC Schalke 04 (Verein 2) erhalten?
	 */
	static int vereineToreVerein2erhalten() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int anzTore = 0;
		for (Spiel s : bl.spiele) {
			if (s.getHeim() == 2)
				anzTore += s.getToreGast();
			else if (s.getGast() == 2)
				anzTore += s.getToreHeim();
		}

		return anzTore;
	}

	/**
	 * Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)?
	 * Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
	 */
	static int vereineToreVerein20punkte() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int anzPunkte = 0;
		for (Spiel s : bl.spiele) {
			if (s.getHeim() == 20 && s.getToreHeim() > s.getToreGast()
					|| s.getGast() == 20 && s.getToreGast() > s.getToreHeim())
				anzPunkte += 3;
			else if ((s.getHeim() == 20 || s.getGast() == 20) && s.getToreHeim() == s.getToreGast())
				anzPunkte += 1;
		}

		return anzPunkte;
	}

	/**
	 * Was ist das Torverhältnis des VfL Bochum (Verein 26)?
	 */
	static int vereineTorverhaeltnis26() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		int erzielt = 0, erhalten = 0;
		for (Spiel s : bl.spiele) {
			if (s.getHeim() == 26) {
				erzielt += s.getToreHeim();
				erhalten += s.getToreGast();
			} else if (s.getGast() == 26) {
				erzielt += s.getToreGast();
				erhalten += s.getToreHeim();
			}
		}

		int diff = erzielt - erhalten;

		return diff;
	}

	/**
	 * Hilfsklasse.
	 */
	static class VereinTore {
		String verein;
		int tore;

		public VereinTore(String v, int t) {
			this.verein = v;
			this.tore = t;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof VereinTore))
				return false;
			VereinTore that = (VereinTore) o;
			return tore == that.tore && verein.equals(that.verein);
		}
	}

	/**
	 * Welche drei Vereine haben die meisten Tore zuhause geschossen, und wie viele?
	 */
	static List<VereinTore> vereineMeisteToreZuhause() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		Map<Integer, Integer> toreZuhause = new HashMap<>();

		for (Spiel s : bl.spiele) {
			int tore = 0;
			if (toreZuhause.containsKey(s.getHeim()))
				tore = toreZuhause.get(s.getHeim());
			toreZuhause.put(s.getHeim(), tore + s.getToreHeim());
		}

		List<VereinTore> liste = new LinkedList<>();

		for (Map.Entry<Integer, Integer> e : toreZuhause.entrySet()) {
			Verein v = bl.vereine.get(e.getKey());
			Integer tore = e.getValue();
			liste.add(new VereinTore( v.getName(), tore));
		}

		liste.sort(new Comparator<VereinTore>() {
			@Override
			public int compare(VereinTore o1, VereinTore o2) {
				return Integer.compare(o2.tore, o1.tore);
			}
		});

		return liste.subList(0,3);
	}

	/**
	 * Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
	 */
	static VereinTore vereineWenigsteToreAuswaerts() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();

		Map<Integer, Integer> toreZuhause = new HashMap<>();

		for (Spiel s : bl.spiele) {
			int tore = 0;
			if (toreZuhause.containsKey(s.getGast()))
				tore = toreZuhause.get(s.getGast());
			toreZuhause.put(s.getGast(), tore + s.getToreGast());
		}

		VereinTore schlechtester = new VereinTore("", Integer.MAX_VALUE);

		for (Map.Entry<Integer, Integer> e : toreZuhause.entrySet()) {
			Verein v = bl.vereine.get(e.getKey());
			Integer tore = e.getValue();
			if (tore < schlechtester.tore)
				schlechtester = new VereinTore(v.getName(), tore);
		}

		return schlechtester;
	}

}