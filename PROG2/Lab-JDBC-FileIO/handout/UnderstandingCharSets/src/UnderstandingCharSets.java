import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class UnderstandingCharSets {
    public static void main(String args[]) throws IOException {


        /* Teilaufgabe a
         * In der Vorlesung haben Sie gelernt, dass Java-Klassen fuer Unicode entworfen wurden.
         * Nun ist Unicode aber nicht der einzige Zeichensatz und Java unterstuetz durchaus Alternativen.
         * Welche Zeichensaetze auf einem System konkret unterstuetzt werden haengt von der Konfiguration des Betriebssystems JVM ab.
         * Schreiben Sie ein Programm, welches alle Unterstuetzten Zeichensaetze auf der Konsole (System.out) ausgibt, zusammen mit dem Standardzeichensatz.
         * https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html
         */

        // ToDo: Print default character set


        // Todo: Print all available character sets


        /* Ende Teilaufgabe a */


        /* Teilaufgabe b
         * Schreiben Sie ein Program welches im Standardzeichensatz einzele Zeichen (also Zeichen fuer Zeichen) von der Konsole einliest und ebenso im Zeichensatz US_ASCII in eine Datei schreibt.
         * Die Eingabe des Zeichens 'q' soll das Program ordentlich beenden.
         * Die Datei soll "CharSetEvaluation.txt" genannt werden und wird entweder erzeugt oder wenn Sie bereits existiert, einfach geoeffnet und der Inhalt uebeschrieben werden.
         * Lesen von der Konsole und Schreiben in die Datei soll leistungsoptimiert geschehen, also vom jeweiligen Input-/Output-Medium entkoppelt.
         * Testen Sie Ihr Program mit den folgenden Eingabereihenfolge und Zeichen: a b c d € f g q
         * Oeffnen Sie die Textdatei nach Durchfuehrung des Programs mit einem Texteditor und erklaeren Sie das Ergebnis.
         * Oeffnen Sie die Datei anschliessend mit einem HEX-Editor und vergleichen Sie.
         */


    }
}

