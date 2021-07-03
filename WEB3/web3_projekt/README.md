# WEB3 Projekt

## Gruppe:
* Gabriel Wicki (wickigab)
* Gabriele Pace (pacegab1)
* Maximilian Bürgi (buergmax)

**Klasse:** IT18a_ZH

**Heroku:** [Issue Tracker](https://web3projekt-wicki-pace-buergi.herokuapp.com "WEB3 Projekt")

**Tests durchführen:** [Jasmine Tests](https://web3projekt-wicki-pace-buergi.herokuapp.com/assets/js/SpecRunner.html "WEB3 Projekt Tests")
-- --
## Setup (lokal):
Zuerst
```bash
npm install
```
im Root-Directory (Hauptordner) ausführen. Falls es ```npm audit fix``` fragt, dann auch ausführen.

Um der Web Server zu starten DATABASE_URL in api/database.js setzen
```bash
DATABASE_URL=postgres://YourUserName:YourPassword@YourHost:5432/YourDatabase
```
wobei jeder "Your" entspricht die lokale Konfiguration der PostgreSQL.

Dann

```bash
npm start
```
im Root-Directory (Hauptordner) ausführen. Schliesslich Web Browser öffnen.

**Web Browser:** http://localhost:8080

Web Server abstellen? Ctrl+C drücken.

**Voraussetzung:** Node.js (ab Version 12) und PostgreSQL (ab Version 7.14) müssen installiert sein.

-- --
**Extra features:**
Validierungen auf der Client- und Serverseite von:
* Inputs von neuen Issues
* Inputs von neuen Projekte
