package ueb08;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;


class AnalysenTest {

	/**
	 * Wie viele Tore fallen durchschnittlich in jedem Spiel?
	 */
	@Test
	void testTorstatistikenToreProSpiel() throws IOException {
		// 2.6458966565349544
		assertEquals(2.645, Analysen.torstatistikenToreProSpiel(), 0.001);
	}

	/**
	 * Wie viele Tore fallen durchschnittlich in einem Spiel der 1. Liga?
	 */
	@Test
	void testTorstatustikenToreProErstligaspiel() throws IOException {
		// 2.7465277777777777
		assertEquals(2.746, Analysen.torstatustikenToreProErstligaspiel(), 0.001);
	}

	/**
	 * Wie viele Tore fallen durchschnittlich an einem Spieltag der 2. Liga?
	 */
	@Test
	void testTorstatistikenToreProSpieltag2teLiga() throws IOException {
		// 24.96875
		assertEquals(24.968, Analysen.torstatistikenToreProSpieltag2teLiga(), 0.001);
	}

	/**
	 * Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore fallen, wie in den Abendspielen?
	 */
	@Test
	void testTorstatistikenToreNachmittagsAbends() throws IOException {
		assertTrue(Analysen.torstatistikenToreNachmittagsAbends());
	}

	/**
	 * Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen als auswärts?
	 */
	@Test
	void testTorstatistikenToreDaheim() throws IOException {
		assertTrue(Analysen.torstatistikenToreDaheim());
	}

	/**
	 * Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
	 */
	@Test
	void testVereineToreVerein1erzielt() throws IOException {
		assertEquals(88, Analysen.vereineToreVerein1erzielt());
	}

	/**
	 * Wie viele Tore hat der FC Schalke 04 (Verein 2) erhalten?
	 */
	@Test
	void testVereineToreVerein2erhalten() throws IOException {
		assertEquals(36, Analysen.vereineToreVerein2erhalten());
	}

	/**
	 * Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)?
	 * Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
	 */
	@Test
	void testVereineToreVerein20punkte() throws IOException {
		assertEquals(57, Analysen.vereineToreVerein20punkte());
	}

	/**
	 * Was ist das Torverhältnis des VfL Bochum (Verein 26)?
	 */
	@Test
	void testVereineTorverhaeltnis26() throws IOException {
		assertEquals(-1, Analysen.vereineTorverhaeltnis26());
	}

	/**
	 * Welche drei Vereine haben die meisten Tore zuhause geschossen, und wie viele?
 	 */
	@Test
	void testVereineMeisteToreZuhause() throws IOException {
		List<Pair<String, Integer>> besteDrei = Analysen.vereineMeisteToreZuhause();

		assertEquals(Pair.of("SV Meppen", 34), besteDrei.get(0));
		assertEquals(Pair.of("FC Bayern München", 16), besteDrei.get(1));
		assertEquals(Pair.of("FC Schalke 04", 16), besteDrei.get(2));
	}

	/**
	 * Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
	 */
	@Test
	void vereineWenigsteToreAuswaerts() throws IOException {
		assertEquals(Pair.of("VfR Aalen", 1), Analysen.vereineWenigsteToreAuswaerts());
	}
}