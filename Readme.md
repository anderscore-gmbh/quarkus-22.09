# Quarkus-Schulung

* Titel (GfU): Schulung Jakarta EE Microservices mit Quarkus & MicroProfile (s2204)
* Seminarprogramm (GfU): https://www.gfu.net/seminare-schulungen-kurse/java_sk5/jakartaee-microservices-quarkus-microprofile_s2204.html
* Dauer: 3 Tage

## Einleitung

Die Schulung Jakarta EE Microservices mit Quarkus & MicroProfile wurde Stand 01/2021 bislang zweimal gehalten. Geschult wurden jeweils zwei Mitarbeiter per Zoom.

Dieses Dokument beschreibt die Durchführung der Schulung und listet bekannte Probleme / Fehler (Errata) auf, die vor einer erneuten Durchführung beachtet und geändert werden sollten.

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

Hinweis: Wg. der Aufgabe zu Kubernetes muss die Umgebung VMs starten können. VM-in-VM geht nicht überall.

## Errata

Vor einer erneuten Durchführung kann die Schulung in diesen Punkten verbessert werden:

1. Übungsaufgaben nutzen H2-in-memory, was wegen native-executables eher ungeschickt ist. Gleichzeitig ist es recht aufwändig, einen H2-Server lokal zu installieren.
Geschickter wäre, die Aufgaben von Anfang an mit Postgres zu machen und die Datenbank über Docker zu starten - die Postgres-Variante ist - Stand 2021/01 - nur für die
Native-Testing Aufgabe in Kapitel 6 vorgesehen.
2. Das Kapitel zu CDI kann noch ausgebaut werden - insb. zu den Teilen, die von ArC (Build Time CDI) unterstützt werden. Vorlage evtl. nach dem Buch "Quarkus Framework: Build Cloud-Native Enterprise Java Applications and Microservices (English Edition) 1. Auflage" - dort gibt es ein Kapitel zu CDI - oder dem Quarkus Guide: https://quarkus.io/guides/cdi-reference
3. Auf der Folie Hello World / CDI fehlt eine class-Deklaration mit dem richtigem Pfad
4. Aufgaben zu Config
