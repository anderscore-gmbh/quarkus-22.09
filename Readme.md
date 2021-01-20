# Quarkus-Schulung

* Titel (GfU): Schulung Jakarta EE Microservices mit Quarkus & MicroProfile (s2204)
* Seminarprogramm (GfU): https://www.gfu.net/seminare-schulungen-kurse/java_sk5/jakartaee-microservices-quarkus-microprofile_s2204.html
* Dauer: 3 Tage

## Einleitung

Die Schulung Jakarta EE Microservices mit Quarkus & MicroProfile wurde Stand 01/2021 bislang zweimal gehalten. Geschult wurden jeweils zwei Mitarbeiter per Zoom.

Dieses Dokument beschreibt die Durchführung der Schulung und listet bekannte Probleme / Fehler (Errata) auf, die vor einer erneuten Durchführung beachtet und geändert werden sollten.

## Durchführung

Die Schulung basiert auf dem reveal.js Template. Lediglich ein kleiner Theorie-Foliensatz ist - mangels Zeit - in Powerpoint vorhanden.
Inhaltlich beziehen Sich die Folien auf einen Dienstleister, einen Trainer und ein konkretes Datum. Vor einer erneuten Schulung sind die Angaben anzupassen.

Die Ascii-Doc Folien befinden sich im Ordner https://gitlab.ads.anderscore.com/trainings/quarkus/-/tree/master/presentation und können wie üblich mit

`mvn clean process-resources` bzw. `mvn revealjs-server:serve` 

gerendert bzw. live gerendert werden (da sich Jan den letzteren Befehl nicht merken kann, gibt es ein Script `run.sh`, das diesen ausführt). 
Die fertigen Folien liegen im Ordner `presentation/target/generated-slides`. Die sind im git abgelegt, damit sie von den Teilnehmern beachtet werden können.

Die GfU stellt Workstations für die Teilnehmer zur Verfügung - auch remote. Zuletzt jedoch noch recht kleine Intel NUC Geräte.
Für die Durchführung wird Docker (bzw. Linux benötigt). Da die GfU gerne RDP verwendet (ist angeblich schneller), gab es auf den Windows-Geräten (wegen RDP)
eine vmware-VM mit Ubuntu Linux.

Für die Schulung werden gültige TLS-Zertifikate benötigt. Let's encrypt Zertifikate liegen in der Datei certs.zip (Kennwort: GFUquarkus202101) - Ende der Gültigkeit 01/2021.

Da sich Quarkus recht schnell entwickelt, muss vor einer Schulung die Folie mit den Versionsnummern angepasst werden.
Es bietet sich darüber hinaus an, die Beispiele mit der aktuellen Version zu testen. Da die MicroProfile-API eher konservativ ist, sollte es eher wenig Probleme geben.

## Übungsaufgaben: 

Zu praktisch jeder Übungsaufgabe gibt es ein Skelett in lesson0KAPITEL-NUMMER. Der Pfad ist auf den Folien verlinkt. 
Eine Beispiellösung gibt es im Unterverzeichnis solutions. Zuletzt (2021/01) gab es massive Stabilitätsprobleme mit der Kombination aus IntelliJ (Community/Ultimate) und Quarkus,
welche vor einer erneuten Durchführung einmal zu überprüfen sind.

## Zeitplan

Die Schulung ist für drei Tage angesetzt. Bei den letzten Schulungen hätte das Material aber locker für 4 Tage gelangt. 
Es ist daher sinnvoll, Schwerpunkte zu setzen und z.T. praktische Aufgaben weg zu lassen. Am Besten also am Anfang mal Fragen, welche Schwerpunkte relevant sind.

Der Ablauf war zuletzt wie folgt:

1. Tag: Begrüßung bis Kapitel 3.1 (REST)
2. Tag: Rest (Praxis), Microprofile API, Security
3. Tag: Docker, Kubernetes und Kafka

Für den Schluss gibt es nochmal eine größere Übungsaufgabe, die wir aber nicht mehr machen konnten. 

## Vorbereitung

Bei der GfU haben wir die Installation folgender Komponenten angefragt:

1. OpenJDK 11 (inkl. Path und JAVA_HOME)
2. GraalVM Community Edition (inkl. Path und GRAALVM_HOME)
3. Apache Maven
4. IntelliJ Community Edition + Quarkus Tools + Quarkus Run Configs
5. Docker CE - der Benutzer des Teilnehmenden sollte in der Docker-Gruppe sein, d.h. den Daemon aufrufen können
6. Minikube + KVM
7. git
8. Postman

Die Teilnehmer haben zuletzt das Buch Beginning "Quarkus Framework: Build Cloud-Native Enterprise Java Applications and Microservices (English Edition) 1. Auflage" bekommen - evtl.
gibt es beim nächsten Mal aber ein aktuelleres oder schönes Buch auf Deutsch.

Hinweis: Wg. der Aufgabe zu Kubernetes muss die Umgebung VMs starten können. VM-in-VM geht nicht überall.
Jan weiß nicht, wie zuverlässig Vagrant / Virtualbox da ist: Einerseits hat Jan im Hinterkopf, dass Virtualbox das Feature mitrbringt; andererseits ist die GfU genau deswegen auf VMWare 
auf dem Host ausgewichen.

## Errata

Vor einer erneuten Durchführung kann die Schulung in diesen Punkten verbessert werden (Priorität aus Sicht von Daniel):

1. Ein Großteil der Integration Tests läuft nicht fehlerfrei durch und muss gefixt werden.
2. Die in der Musterlösung lesson04-openapi enthaltenen Annotationen an der OrderResource finden sich zur Laufzeit nicht in der OpenAPI Beschreibung wieder. Dies ist zu prüfen.
3. Versionen in den Startern und Musterlösungen sind mittlerweile veraltet und sollten aktualisiert werden.
4. Die TLS-Zertifikate für localhost.k.anderscore.com, order.k.anderscore.com, franchise.k.anderscore.com hat Jan auf seinem Hetzner Server gemacht. Das kann Jan machen, weil er wegen einer Goldschmiede von 2019 noch eine DNS-Delegation in seinen privaten DNS-Account hat. Besser wäre, ein Zertfikat für localhost.training.anderscore.com mit der anderScore-CI zu erzeugen.
5. Übungsaufgaben nutzen H2-in-memory, was wegen native-executables eher ungeschickt ist. Gleichzeitig ist es recht aufwändig, einen H2-Server lokal zu installieren.
Geschickter wäre, die Aufgaben von Anfang an mit Postgres zu machen und die Datenbank über Docker zu starten - die Postgres-Variante ist - Stand 2021/01 - nur für die 
Native-Testing Aufgabe in Kapitel 6 vorgesehen.
6. Die Kafka-Folien (Powerpoint) nach Reveal-JS übernehmen. Redundanz entfernen.
7. Das Kapitel zu CDI kann noch ausgebaut werden - insb. zu den Teilen, die von ArC (Build Time CDI) unterstützt werden. Vorlage evtl. nach dem Buch "Quarkus Framework: Build Cloud-Native Enterprise Java Applications and Microservices (English Edition) 1. Auflage" - dort gibt es ein Kapitel zu CDI - oder dem Quarkus Guide: https://quarkus.io/guides/cdi-reference
8. Links zu den einzelnen Punkten bspw. der MicroProfile API wären nett. Ebenso links im MicroProfile-Kapitel.
9. Jan findet: Die Folien zu GraalVM haben z.T. recht wenig Inhalt. Dafür gibt es recht viele. Jan fände es besser, weniger Folien mit dem gleichen Inhalt da wären.
