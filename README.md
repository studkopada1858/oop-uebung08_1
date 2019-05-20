_Übungsaufgabe zur Veranstaltung [Objektorientiertes Programmieren](https://hsro-wif-oop.github.io) im [Bachelorstudiengang Wirtschaftsinformatik](https://www.th-rosenheim.de/technik/informatik-mathematik/wirtschaftsinformatik-bachelor/) an der [Hochschule Rosenheim](http://www.th-rosenheim.de)._


# Datenverarbeitung

In der Vorlesung haben wir die Klassiker zur Datenverarbeitung behandelt:

- Sortieren
- Filtern
- Abbildem
- Reduzieren

In dieser Uebung gilt es nun das Gelernte anzuwenden. 

Implementieren Sie in der Klasse _ueb08.Analysen_ die Methoden zur Analyse der Fussballdaten. Beachten Sie hierzu die javadoc Kommentare. Die Testklasse _ueb08.AnalysenTest_ validiert ihre Ergbenisse.

Lesen sie hierzu ueber

```java
Bundesliga bl = Bundesliga.loadFromResource();
````
die Daten ein und Arbeiten Sie mit den vorhandenen Datenstrukturen **Bundesliga, Verein und Spiel**.

Viel Erfolg! 


# Aufgaben

## Torstatistiken

Schreiben Sie Methoden, die die Torstatistik auswerten.

1. Wie viele Tore fallen durchschnittlich in jedem Spiel?
2. Wie viele Tore fallen durchschnittlich in einem Spiel der 1. Liga?
3. Wie viele Tore fallen durchschnittlich an einem Spieltag der 2. Liga?
4. Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore fallen, wie in den Abendspielen?
5. Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen als auswärts?

---

## Vereine

1. Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
2. Wie viele Tore hat der FC Schalke 04 (Verein 2) kassiert?
3. Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)? Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
4. Was ist das Torverhältnis des VfL Bochum (Verein 26), also die Rate von erzielten zu kassierten Toren?
5. Welche drei Vereine haben die meisten Tore zuhause geschossen, und wie viele?
6. Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
