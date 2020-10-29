# Quarkus-Schulung

* Titel (GfU): Schulung Jakarta EE Microservices mit Quarkus & MicroProfile (s2204)
* Seminarprogramm (GfU): https://www.gfu.net/seminare-schulungen-kurse/java_sk5/jakartaee-microservices-quarkus-microprofile_s2204.html
* Dauer: 3 Tage

## Einleitung

Die Schulung Jakarta EE Microservices mit Quarkus & MicroProfile wurde stand 10/2020 bislang einmal gehalten. Geschult wurden zwei Mitarbeiter per Zoom.

Diese Dokument beschreibt die Durchführung der Schulung und listet bekannte Probleme / Fehler (Errata) auf, die vor einer erneuten Durchführung beachtet und geändert werden sollten.

## Durchführung

Die Schulung basiert auf dem reveal.js Template. Lediglich ein kleiner Theorie-Foliensatz ist - mangels Zeit - in Powerpoint vorhanden.
Inhaltlich beziehen Sich die Folien auf einen Dienstleister (GfU), einen Trainer (Jan Lühr) und ein konkretes Datum. Vor einer erneuten Schulung sind die Angaben anzupassen.

Die Ascii-Doc Folien befinden sich im Ordner https://gitlab.ads.anderscore.com/trainings/quarkus/-/tree/master/presentation und können wir üblich mit

`mvn clean process-resources` bzw. `mvn revealjs-server:serve` 

gerendert bzw. live gerendert werden (da sich Jan den letzteren Befehl nicht merken kann, gibt es ein Script `run.sh`, dass diesen ausführt). 
Die fertigen Folien liegen im Ordner `presentation/target/generated-slides`. Die sind im git abgelegt, damit sie von den Teilnehmern beachtet werden können.

Die GfU stellt Workstations für die Teilnehmer zur Verfügung - auch remote. Zuletzt jedoch noch recht kleine Intel NUC Geräte.
Für die Durchführung wird Docker (bzw. Linux benötigt). Da die GfU gerne RDP verwendet (ist angeblich schneller), gab es auf den Windows-Geräten (wegen RDP)
eine vmware-VM mit Ubuntu Linux.  Neben RDP gibt es auch einen Guacamole-Zugriff, den Jan bevorzugt (wg. RDP). Hier war das VNC der VM direkt in Guacomle integriert.
**Das Setup ist nicht reboot-save. Die VNC-Session wird nach einem Reboot der NUC nicht erneut gestartet!**

Für die Schulung werden ein kafka-Broker und gültige TLS-Zertifikate benötigt. Für die Schulung in 10/2020 hat Jan einen Kafka-Broker in der hetzner-Cloud angelegt
(inzwischen gelöscht). Let's encrypt Zertifikate liegen in der Datei certs.zip (Kennwort: GFUquarkus202010) - Ende der Gültigkeit 01/2021.

Da sich quarkus recht schnell entwickelt, muss vor einer Schulung die Folie mit den Versionssnummern angepasst werden.
Es bietet sich darüber hinaus an, die Beispiele mit der aktuellen Version zu testen. Da die Micoprofile-API eher konservativ ist, sollte es eher wenig Probleme geben.

## Übungsaufgaben: 

Zu praktisch jeder Übungsaufgabe gibt es ein Skelett in lesson0KAPITEL-NUMMER. Der Pfad ist auf den Folien verlink. 
Eine Beispiellösung gibt es im Unterverzeichnis solutions. 


## Zeitplan

Die Schulung ist für drei Tage angesetzt. Bei der letzten Schulung hätte das Material aber locker für 4 Tage gelangt. 
Es ist daher sinnvoll, Schwerpunkte zu setzen und z.T. praktische Aufgaben weg zu lassen. D.h. am Anfang mal Fragen, welche Schwerpunkte relevant sind.

Der Ablauf war zuletzt wie folgt.

1. Tag: Begrüßung bis Kapitel 3.1 (REST); Theorie aber keine Praxies
2. Tag: Rest (Praxis), bis etwa 1/2 Microprofile API (Metrics)
3. Tag: Rest Micrprofile-API bis kafka

Für den Schluss gibt es nochmal eine größere Übungsaufgabe, die wir aber nicht mehr machen konnten. 

## Vorbereitung

Bei der GfU haben wir die Installation folgender Komponenten angefragt:

1. OpenJDK 11 
2. Apache Maven
3. Gradle, gerne 
4. GraalVM community Edition - bitte in das Homeverz. des Benutzers und die env-variable GRAALVM_HOME entsprechend setzen. Bitte auch in den Path aufnehmen.
5. NodeJS, Version 10 oder neuer; gerne systemweit
6. IntelliJ Community Edition
7. Docker CE - der Benutzer des Teilnehmenden sollte in der Docker-Gruppe sein, d.h. den Daemon aufrufen können
8. Die aktuelle (sic) Version von Minishift wie im Anhang dargestellt. Falls es mit KVM in Virtualbox Probleme gibt, bitte ich um Rückmeldung.
9. git
10. Postman (https://www.postman.com/downloads/)
11. Quarkus IntelliJ Plugin (https://plugins.jetbrains.com/plugin/13234-quarkus-tools)

Die Teilnehmer haben zuletzt das Buch Beginning "Quarkus Framework: Build Cloud-Native Enterprise Java Applications and Microservices (English Edition) 1. Auflage" bekommen - evtl.
gibt es beim nächsten Mal aber ein aktuelleres oder schönes Buch auf Deutsch.

Hinweis: Beim letzten Training konnten die Teilnehmer OpenShift nicht mehr mit `./minishift start` starten, nachdem minishift erstmal gestartet wurde.
Das wäre zu überprüfen.

Hinweis (2): Wg. der Aufgabe zu kubernetes / openshift muss die Umgebung VMs starten können. VM-in-VM geht nicht überall.
Jan weiß nicht, wie zuverlässig Vagrant / Virtualbox da ist: Einerseits hat jan im Hinterkopf, dass Virtualbox das Feature mitrbringt; andererseits ist die GfU genau deswegen auf VMWare 
auf dem Host ausgewichen.

## Errata

Vor einer erneuten Durchführung kann die Schulung in diesen Punkten verbessert werden:

1. Tippfehler auf den Folien entfernen
2. Ab einem Kapitel (spätestens ab 6. Application-Building / GraalVM) rennen die Integration-Tests in HTTP-400 Fehler, obwohl sie eigentlich bei der Entwicklung funktionieren.
Fehler finden und beheben. Folgende (!) Aufgaben im Beispiel-Code und im Skelett anpassen.
3. Das Kubernetes-Kapitel ist recht "hackish" und geht auf OpenShift aus dem Buch "Hands-On Cloud-Native Applications with Java and Quarkus: Build high performance, Kubernetes-native Java serverless applications (English Edition)" zurück.
Besser wäre wohl minikube nach dem Quarkus-Guide
4. Die Folien zu GraalVM haben z.T. recht wenig Inhalt. 
5. Gut wäre noch ein Kapitel zu CDI-Grundlagen - insb. zu den Teilen, die von ArC (Build time CI) unterstützt werden. Jan hat bei der Schulung Dependency-Injetion mit einem 
Ascii-Editor erklärt. Vorlage evtl. nach dem Buch "Quarkus Framework: Build Cloud-Native Enterprise Java Applications and Microservices (English Edition) 1. Auflage" - dort gibt es ein Kapitel zu CDI - oder dem Quarkus Guide: https://quarkus.io/guides/cdi-reference
6. Die Kafka-Folien (Powerpoint) nach Reveal-JS übernehmen. Redundanz entfernen.
7. Übungsaufgaben nutzen H2-in-memory, was wegen native-executables eher ungeschickt ist. Gleichzeitig ist es recht aufwändig, einen H2-Server lokal zu installieren.
Geschickter wäre, die Aufgaben von Anfang an mit Postgres zu machen und die Datenbank über Docker zu starten - die Postgres-Variante ist - stand 2020/10 - nur für die 
Native-Testing Aufgabe in Kapitel 6 vorgesehen.
8. Z.T. fehlen Links in die Unterkapitel. Links zu den einzelnen Punkten bspw. der Microprofile API wären nett. Ebenso links im Microprofile-Kapitel.
