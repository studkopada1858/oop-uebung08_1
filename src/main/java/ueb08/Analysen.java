package ueb08;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.*;




class Analysen {

	/**
	 * Wie viele Tore fallen durchschnittlich in jedem Spiel?
	 */
	static double torstatistikenToreProSpiel() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		double Tore =0.0;
		for(int i=0;i<bl.spiele.size();i++){
			Tore+=bl.spiele.get(i).getToreGast()+bl.spiele.get(i).getToreHeim();
		}
		return Tore/bl.spiele.size();
	}

	/**
	 * Wie viele Tore fallen durchschnittlich in einem Spiel der 1. Liga?
	 */
	static double torstatustikenToreProErstligaspiel() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		double Tore =0.0;
		for(int i=0;i<bl.spiele.size();i++){
			if(bl.spiele.get(i).getHeim()<=18){
				Tore+=bl.spiele.get(i).getToreGast()+bl.spiele.get(i).getToreHeim();			}

		}
		return Tore/getSpielProLiga(1);
	}
	static int getSpielProLiga(int l) throws IOException{
		Bundesliga bl = Bundesliga.loadFromResource();
		int anzahl=0;
		for(Spiel spl: bl.spiele){
			if(bl.vereine.get(spl.getHeim()).getLiga()==l){
				anzahl++;
			}
		}


		return anzahl;
	}

	/**
	 * Wie viele Tore fallen durchschnittlich an einem Spieltag der 2. Liga?
	 */
	static double torstatistikenToreProSpieltag2teLiga() throws IOException {
	Bundesliga bl = Bundesliga.loadFromResource();
	double anz=0.0;
	int st=0;
	for(Spiel spl: bl.spiele){
		if(bl.vereine.get(spl.getHeim()).getLiga()==2){
			anz+=spl.getToreHeim()+spl.getToreGast();
			st=spl.getSpieltag();
		}
	}

		return anz/st;
	}


	/**
	 * Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore fallen, wie in den Abendspielen?
	 */
	static boolean torstatistikenToreNachmittagsAbends() throws IOException {
		Bundesliga bl=Bundesliga.loadFromResource();
		double toremittag=0.0;
		double toreabend=0.0;
		int anzMittag=0;
		int anzAbend=0;
		for(Spiel spl: bl.spiele){
			if(spl.getUhrzeit().equals("15:30:00")){
				toremittag+=spl.getToreGast()+spl.getToreHeim();
				anzMittag++;
			}else{
				toreabend+=spl.getToreGast()+spl.getToreHeim();
				anzAbend++;
			}
		}
		return (toremittag/anzMittag>toreabend/anzAbend);
	}

	/**
	 * Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen als auswärts?
	 */
	static boolean torstatistikenToreDaheim() throws IOException {
		Bundesliga bl=Bundesliga.loadFromResource();
		double toreDaheim=0.0;
		double toreAusw=0.0;
		int spiele=0;
		for(Spiel spl: bl.spiele){
			if(bl.vereine.get(spl.getHeim()).getLiga()==3){
				toreDaheim+=spl.getToreHeim();
				toreAusw+=spl.getToreGast();
				spiele++;
			}
		}
		System.out.println(toreDaheim+" "+toreAusw);
		return toreDaheim>toreAusw;
	}

	/**
	 * Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
	 */
	static int vereineToreVerein1erzielt() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int tore=0;
		for(Spiel spl : bl.spiele){
			if(spl.getHeim()==1){
				tore+=spl.getToreHeim();
			}
			if(spl.getGast()==1){
				tore+=spl.getToreGast();
			}
		}


		return tore;
	}

	/**
	 * Wie viele Tore hat der FC Schalke 04 (Verein 2) erhalten?
	 */
	static int vereineToreVerein2erhalten() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int tore=0;
		for(Spiel spl : bl.spiele){
			if(spl.getHeim()==2){
				tore+=spl.getToreGast();
			}
			if(spl.getGast()==2){
				tore+=spl.getToreHeim();
			}
		}


		return tore;}


	/**
	 * Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)?
	 * Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
	 */
	static int vereineToreVerein20punkte() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int punkte=0;
		for(Spiel spl : bl.spiele){
			if(spl.getHeim()==20){
				if(spl.getToreHeim()>spl.getToreGast()){
					punkte+=3;
				}else if(spl.getToreHeim()==spl.getToreGast()){
					punkte++;
				}

			}
			if(spl.getGast()==20){
				if(spl.getToreGast()>spl.getToreHeim()){
					punkte+=3;
				}else if(spl.getToreGast()==spl.getToreHeim()){
					punkte++;
				}

			}
		}


		return punkte;
	}

	/**
	 * Was ist das Torverhältnis des VfL Bochum (Verein 26)?
	 */
	static int vereineTorverhaeltnis26() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int torverhaeltnis=0;
		for (Spiel spl: bl.spiele){
			if(spl.getHeim()==26){
				torverhaeltnis=torverhaeltnis+spl.getToreHeim()-spl.getToreGast();
			}
			if(spl.getGast()==26){
				torverhaeltnis=torverhaeltnis+spl.getToreGast()-spl.getToreHeim();
			}
		}
		return torverhaeltnis;
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
		List<VereinTore> listeVereine=new LinkedList<>();
		Bundesliga bl= Bundesliga.loadFromResource();
		for(int i=1;i<=bl.vereine.size();i++){
			listeVereine.add(new VereinTore(bl.vereine.get(i).getName(),heimtore(i)));
		}

		listeVereine.sort(new Comparator<VereinTore>(){
				@Override
				public int compare(VereinTore o1, VereinTore o2) {
					return (o2.tore - o1.tore);
				}});
		List<VereinTore> listeVereine2=new LinkedList<>();
		listeVereine2.add(listeVereine.get(0));
		listeVereine2.add(listeVereine.get(1));
		listeVereine2.add(listeVereine.get(2));


		return listeVereine2;
	}

	static int heimtore(int v) throws IOException{
		Bundesliga bl = Bundesliga.loadFromResource();
		int tore=0;
		for(Spiel spl : bl.spiele){
			if(spl.getHeim()==v){
				tore+=spl.getToreHeim();
			}

		}


		return tore;
	}

	/**
	 * Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
	 */
	static VereinTore vereineWenigsteToreAuswaerts() throws IOException {
		List<VereinTore> listeVereine=new LinkedList<>();
		Bundesliga bl= Bundesliga.loadFromResource();
		for(int i=1;i<=bl.vereine.size();i++){
			listeVereine.add(new VereinTore(bl.vereine.get(i).getName(),gasttore(i)));
		}

		listeVereine.sort(new Comparator<VereinTore>(){
			@Override
			public int compare(VereinTore o1, VereinTore o2) {
				return (o1.tore - o2.tore);
			}});

		return listeVereine.get(0);
	}

	static int gasttore(int v) throws IOException{
		Bundesliga bl = Bundesliga.loadFromResource();
		int tore=0;
		for(Spiel spl : bl.spiele){
			if(spl.getGast()==v){
				tore+=spl.getToreGast();
			}

		}


		return tore;
	}

}