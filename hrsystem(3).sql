-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 31. Mai 2017 um 09:53
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `hrsystem`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abteilung`
--

CREATE TABLE `abteilung` (
  `id` int(11) NOT NULL,
  `beschreibung` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `abteilung`
--

INSERT INTO `abteilung` (`id`, `beschreibung`) VALUES
(1, 'asdasdasdasd'),
(4, 'it'),
(5, 'einkauf'),
(6, 'vertrieb'),
(7, 'forschung');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `arbeitszeit`
--

CREATE TABLE `arbeitszeit` (
  `id` int(11) NOT NULL,
  `anfang` date NOT NULL,
  `ende` date NOT NULL,
  `ma_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `gehalt`
--

CREATE TABLE `gehalt` (
  `id` int(11) NOT NULL,
  `gehalt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `gehalt`
--

INSERT INTO `gehalt` (`id`, `gehalt`) VALUES
(1, 40000),
(2, 50000),
(3, 60000),
(4, 60000),
(5, 60000),
(6, 40000),
(7, 50000),
(8, 60000),
(9, 60000),
(10, 60000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `gehaltsabrechnungvariableeingaben`
--

CREATE TABLE `gehaltsabrechnungvariableeingaben` (
  `id` int(11) NOT NULL,
  `ma_id` int(11) NOT NULL,
  `urlaubsgeld` double NOT NULL,
  `praemie` double NOT NULL,
  `dienstwagen` double NOT NULL,
  `dienstwagen_weg` double NOT NULL,
  `nachschicht` double NOT NULL,
  `sonntage` double NOT NULL,
  `feiertage` double NOT NULL,
  `sonderverguetung` double NOT NULL,
  `auslagen_erstattungen` double NOT NULL,
  `fahrtgeld` double NOT NULL,
  `gehalt` double NOT NULL,
  `steuerklasse` int(11) NOT NULL,
  `kinder` int(11) NOT NULL,
  `krankenversicherung` double NOT NULL,
  `zusatzbeitrag_prozent` double NOT NULL,
  `lohnsteuerfreibetrag` double NOT NULL,
  `hinzurechnungsbetrag` double NOT NULL,
  `eintrittsdatum` date NOT NULL,
  `urlaubsanspruch` int(11) NOT NULL,
  `av_pflichtig` int(11) NOT NULL,
  `altersfreibetrag` double NOT NULL,
  `krankheitstage` int(11) NOT NULL,
  `urlaubstage_genutzt` int(11) NOT NULL,
  `lohnsteuer` double NOT NULL,
  `kirchensteuer` double NOT NULL,
  `solidaritätszuschlag` double NOT NULL,
  `krankenkassenbeitrag` double NOT NULL,
  `zusatzbeitrag_geld` double NOT NULL,
  `arbeitslosenversicherung` double NOT NULL,
  `rentenversicherung` double NOT NULL,
  `pflegeversicherung` double NOT NULL,
  `ag_zuschuss_baV` double NOT NULL,
  `auszahlungsbetrag` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `info`
--

CREATE TABLE `info` (
  `id` int(11) NOT NULL,
  `beschreibung` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `info`
--

INSERT INTO `info` (`id`, `beschreibung`) VALUES
(1, 'sdsfgdfgdgf'),
(2, 'sdfgdgfdsfgdsfg'),
(3, 'sdfgsdfgdsfgsfdg'),
(4, 'sdsfgdfgdgf'),
(5, 'sdfgdgfdsfgdsfg'),
(6, 'sdfgsdfgdsfgsfdg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `krankheit`
--

CREATE TABLE `krankheit` (
  `id` int(11) NOT NULL,
  `anfang` date NOT NULL,
  `ende` date NOT NULL,
  `attest` tinyint(1) NOT NULL,
  `ma_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `land`
--

CREATE TABLE `land` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `land`
--

INSERT INTO `land` (`id`, `name`) VALUES
(2, 'DE'),
(3, 'dgdgfdgdgf'),
(4, 'DE'),
(5, 'dgdgfdgdgf'),
(6, 'DE'),
(7, 'dgdgfdgdgf'),
(8, 'DE'),
(9, 'dgdgfdgdgf');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lohnkonto`
--

CREATE TABLE `lohnkonto` (
  `id` int(11) NOT NULL,
  `monat` int(11) NOT NULL,
  `bruttolohn` int(11) NOT NULL,
  `geltwertevorteilelaufend` int(11) NOT NULL,
  `einmalbezuegeimbruttolohn` int(11) NOT NULL,
  `geltwertevorteileeinmalig` int(11) NOT NULL,
  `steuerfreiebezuege` int(11) NOT NULL,
  `lohnsteuer` int(11) NOT NULL,
  `solidaritaetszuschlag` int(11) NOT NULL,
  `kirchensteuer` int(11) NOT NULL,
  `kvan` int(11) NOT NULL,
  `kvzusatzbeitrag` int(11) NOT NULL,
  `rvan` int(11) NOT NULL,
  `avan` int(11) NOT NULL,
  `pvan` int(11) NOT NULL,
  `kvag` int(11) NOT NULL,
  `rvag` int(11) NOT NULL,
  `avag` int(11) NOT NULL,
  `pvag` int(11) NOT NULL,
  `umlage1` int(11) NOT NULL,
  `umlage2` int(11) NOT NULL,
  `insolvenzumlage` int(11) NOT NULL,
  `beitragzurbav` int(11) NOT NULL,
  `davonsozvpflichtig` int(11) NOT NULL,
  `steuerbrutto` int(11) NOT NULL,
  `svbruttorv` int(11) NOT NULL,
  `svbruttokv` int(11) NOT NULL,
  `gesamtnetto` int(11) NOT NULL,
  `maid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `lohnkonto`
--

INSERT INTO `lohnkonto` (`id`, `monat`, `bruttolohn`, `geltwertevorteilelaufend`, `einmalbezuegeimbruttolohn`, `geltwertevorteileeinmalig`, `steuerfreiebezuege`, `lohnsteuer`, `solidaritaetszuschlag`, `kirchensteuer`, `kvan`, `kvzusatzbeitrag`, `rvan`, `avan`, `pvan`, `kvag`, `rvag`, `avag`, `pvag`, `umlage1`, `umlage2`, `insolvenzumlage`, `beitragzurbav`, `davonsozvpflichtig`, `steuerbrutto`, `svbruttorv`, `svbruttokv`, `gesamtnetto`, `maid`) VALUES
(3, 1, 3433, 0, 100, 0, 0, 95183, 52, 86, 251, 38, 321, 52, 40, 251, 321, 52, 40, 0, 0, 0, 0, 0, 3433, 3433, 3433, 1643, 4),
(11, 2, 3433, 0, 100, 0, 0, 95083, 52, 86, 251, 38, 321, 52, 40, 251, 321, 52, 40, 0, 0, 0, 0, 0, 3433, 3433, 3433, 1643, 4),
(12, 3, 3433, 0, 100, 0, 0, 95083, 52, 86, 251, 38, 321, 52, 40, 251, 321, 52, 40, 0, 0, 0, 0, 0, 3433, 3433, 3433, 1643, 4),
(13, 4, 3333, 0, 0, 0, 0, 91483, 50, 82, 243, 37, 312, 50, 39, 243, 312, 50, 39, 0, 0, 0, 0, 0, 3333, 3333, 3333, 1605, 4),
(14, 5, 3333, 0, 0, 0, 0, 53183, 29, 48, 243, 37, 312, 50, 39, 243, 312, 50, 39, 0, 0, 0, 0, 0, 3333, 3333, 3333, 2044, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ma_historie`
--

CREATE TABLE `ma_historie` (
  `id` int(11) NOT NULL,
  `anfang` date NOT NULL,
  `ende` date NOT NULL,
  `ma_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ma_schulung`
--

CREATE TABLE `ma_schulung` (
  `schulung_id` int(11) NOT NULL,
  `ma_id` int(11) NOT NULL,
  `abgeschlossen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ma_skills`
--

CREATE TABLE `ma_skills` (
  `ma_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mitarbeiter`
--

CREATE TABLE `mitarbeiter` (
  `id` int(11) NOT NULL,
  `nachname` varchar(255) NOT NULL,
  `vorname` varchar(255) NOT NULL,
  `geburtsdatum` date NOT NULL,
  `id_gehalt` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `plz` int(11) NOT NULL,
  `land` int(11) NOT NULL,
  `abteilungs_id` int(11) NOT NULL,
  `positions_id` int(11) NOT NULL,
  `info_id` int(11) NOT NULL,
  `urlaubstage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `mitarbeiter`
--

INSERT INTO `mitarbeiter` (`id`, `nachname`, `vorname`, `geburtsdatum`, `id_gehalt`, `id_user`, `plz`, `land`, `abteilungs_id`, `positions_id`, `info_id`, `urlaubstage`) VALUES
(4, 'Bauer', 'Hans', '2017-05-17', 1, 1, 234234, 3, 1, 1, 1, 23),
(8, 'hubert', 'marco', '2017-05-17', 2, 4, 12333, 2, 4, 2, 2, 23),
(10, 'maier', 'Hans', '2017-05-17', 3, 8, 341312, 4, 5, 3, 3, 23),
(11, 'ulli', 'bully', '2017-05-17', 4, 9, 345345, 5, 6, 4, 4, 23),
(12, 'ulli', 'bully', '2017-05-17', 4, 1, 345345, 2, 1, 6, 1, 23),
(13, 'ulli', 'bully', '2017-05-17', 4, 1, 345345, 2, 1, 6, 1, 23);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ort`
--

CREATE TABLE `ort` (
  `plz` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `ort`
--

INSERT INTO `ort` (`plz`, `name`) VALUES
(876, '.,mnkj,'),
(12333, 'dsfsdfsdfsdf'),
(12334, 'dsfsdfsdfsdf'),
(234234, 'sdfsdfsdf'),
(234235, 'sdfsdfsdf'),
(341312, 'dffsdf'),
(345345, 'dffsdf'),
(2342435, 'fssfsfsfsdf');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `permission`
--

CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `beschreibung` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `permission`
--

INSERT INTO `permission` (`id`, `beschreibung`) VALUES
(1, 'Leiter'),
(2, 'Normal'),
(3, 'Gruppenleiter'),
(4, 'dfgd');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `position`
--

CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `beschreibung` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `position`
--

INSERT INTO `position` (`id`, `beschreibung`) VALUES
(1, 'Leitung'),
(2, 'Normal'),
(3, 'Leitung'),
(4, 'Normal'),
(5, 'Leitung'),
(6, 'Normal'),
(7, 'Leitung'),
(8, 'Normal');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `raum`
--

CREATE TABLE `raum` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `abteilungs_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `schulung`
--

CREATE TABLE `schulung` (
  `id` int(11) NOT NULL,
  `beschreibung` text NOT NULL,
  `kosten` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `skills`
--

CREATE TABLE `skills` (
  `id` int(11) NOT NULL,
  `beschreibung` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `stammdaten`
--

CREATE TABLE `stammdaten` (
  `id` int(11) NOT NULL,
  `steuerklasse` int(11) NOT NULL,
  `Ehegattenfaktor` int(11) NOT NULL COMMENT 'vom FA mitgeteilter Ehegattenfaktor (nur bei StKl IV, keiner=1,000)',
  `rentenversichert` int(1) NOT NULL COMMENT ' nein=0 ja=1',
  `arbeitslosenversicherungspflichtig` int(1) NOT NULL COMMENT ' nein=0 ja=1',
  `kinderfreibetrag` decimal(10,0) NOT NULL COMMENT '(0, 0.5, 1, 1.5, 2.0, 2.5 usw)',
  `krankenversicherung` double NOT NULL,
  `kvzuschlag` double NOT NULL,
  `arbeitgeberzuschussKvPv` double NOT NULL COMMENT 'Arbeitgeberzuschuss zur privaten KV/PV (max. 50% und 359,13 â‚¬/Monat)',
  `kinderlos` int(1) NOT NULL COMMENT 'nein=0 ja=1',
  `stelleImOsten` int(11) NOT NULL COMMENT 'nein=0 ja=1',
  `stelleInSachsen` int(11) NOT NULL COMMENT 'nein=0 ja=1',
  `kirchensteuer` double NOT NULL COMMENT '(0=keine)',
  `lohnsteuerfreibetrag` int(11) NOT NULL,
  `hinzurechnungsbetrag` int(11) NOT NULL,
  `minijobGleizonenberechnung` int(1) NOT NULL COMMENT ' nein=0 ja=1',
  `rentenanwartschaft` int(11) NOT NULL COMMENT 'mit Rentenanwartschaft nein=0 ja=1 (bei Neueinstellung Pflicht)',
  `mitarbeiterid` int(11) NOT NULL,
  `rentenversicherung` double NOT NULL,
  `pflegeversicherungallgemein` double NOT NULL,
  `pflegeversicherungsachsen` double NOT NULL,
  `pflegeversicherungarbeitgeber` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `stammdaten`
--

INSERT INTO `stammdaten` (`id`, `steuerklasse`, `Ehegattenfaktor`, `rentenversichert`, `arbeitslosenversicherungspflichtig`, `kinderfreibetrag`, `krankenversicherung`, `kvzuschlag`, `arbeitgeberzuschussKvPv`, `kinderlos`, `stelleImOsten`, `stelleInSachsen`, `kirchensteuer`, `lohnsteuerfreibetrag`, `hinzurechnungsbetrag`, `minijobGleizonenberechnung`, `rentenanwartschaft`, `mitarbeiterid`, `rentenversicherung`, `pflegeversicherungallgemein`, `pflegeversicherungsachsen`, `pflegeversicherungarbeitgeber`) VALUES
(6, 1, 1000, 1, 1, '0', 14.6, 1.1, 0, 0, 0, 0, 9, 0, 0, 0, 0, 4, 18.7, 1.175, 1.675, 0.675),
(7, 1, 1000, 1, 1, '0', 14.6, 1.1, 0, 1, 0, 0, 9, 0, 0, 0, 0, 8, 18.7, 1.175, 1.675, 0.675),
(8, 1, 1000, 1, 1, '0', 14.6, 1.1, 0, 1, 0, 0, 9, 0, 0, 0, 0, 10, 18.7, 1.175, 1.675, 0.675),
(9, 1, 1000, 1, 1, '0', 14.6, 1.1, 0, 1, 0, 0, 9, 0, 0, 0, 0, 11, 18.7, 1.175, 1.675, 0.675);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `unternehmen`
--

CREATE TABLE `unternehmen` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `strasse` varchar(255) NOT NULL,
  `ort` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `urlaub`
--

CREATE TABLE `urlaub` (
  `id` int(11) NOT NULL,
  `anfang` date NOT NULL,
  `ende` date NOT NULL,
  `beschreibung` text NOT NULL,
  `ma_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `username`, `passwort`, `permission_id`) VALUES
(1, 'hans', '123', 1),
(4, 'maier', '123', 2),
(8, 'georg', '123', 3),
(9, 'hubert', '123', 4);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `abteilung`
--
ALTER TABLE `abteilung`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `arbeitszeit`
--
ALTER TABLE `arbeitszeit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `arbeitszeit_ibfk_1` (`ma_id`);

--
-- Indizes für die Tabelle `gehalt`
--
ALTER TABLE `gehalt`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `gehaltsabrechnungvariableeingaben`
--
ALTER TABLE `gehaltsabrechnungvariableeingaben`
  ADD PRIMARY KEY (`id`),
  ADD KEY `gehaltsabrechnungvariableeingaben_ibfk_1` (`ma_id`);

--
-- Indizes für die Tabelle `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `krankheit`
--
ALTER TABLE `krankheit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `krankheit_ibfk_1` (`ma_id`);

--
-- Indizes für die Tabelle `land`
--
ALTER TABLE `land`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `lohnkonto`
--
ALTER TABLE `lohnkonto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lohnkonto_ibfk_1` (`maid`);

--
-- Indizes für die Tabelle `ma_historie`
--
ALTER TABLE `ma_historie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ma_historie_ibfk_1` (`ma_id`);

--
-- Indizes für die Tabelle `ma_schulung`
--
ALTER TABLE `ma_schulung`
  ADD PRIMARY KEY (`schulung_id`,`ma_id`),
  ADD KEY `ma_schulung_ibfk_1` (`ma_id`);

--
-- Indizes für die Tabelle `ma_skills`
--
ALTER TABLE `ma_skills`
  ADD PRIMARY KEY (`ma_id`,`skill_id`),
  ADD KEY `ma_skills_ibfk_1` (`skill_id`);

--
-- Indizes für die Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mitarbeiter_ibfk_1` (`id_user`),
  ADD KEY `mitarbeiter_ibfk_2` (`land`),
  ADD KEY `mitarbeiter_ibfk_3` (`id_gehalt`),
  ADD KEY `mitarbeiter_ibfk_4` (`info_id`),
  ADD KEY `mitarbeiter_ibfk_5` (`plz`),
  ADD KEY `mitarbeiter_ibfk_6` (`abteilungs_id`),
  ADD KEY `mitarbeiter_ibfk_7` (`positions_id`);

--
-- Indizes für die Tabelle `ort`
--
ALTER TABLE `ort`
  ADD PRIMARY KEY (`plz`);

--
-- Indizes für die Tabelle `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `raum`
--
ALTER TABLE `raum`
  ADD PRIMARY KEY (`id`),
  ADD KEY `raum_ibfk_1` (`abteilungs_id`);

--
-- Indizes für die Tabelle `schulung`
--
ALTER TABLE `schulung`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `skills`
--
ALTER TABLE `skills`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `stammdaten`
--
ALTER TABLE `stammdaten`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `unternehmen`
--
ALTER TABLE `unternehmen`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `urlaub`
--
ALTER TABLE `urlaub`
  ADD PRIMARY KEY (`id`),
  ADD KEY `urlaub_ibfk_1` (`ma_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_ibfk_1` (`permission_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `abteilung`
--
ALTER TABLE `abteilung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT für Tabelle `arbeitszeit`
--
ALTER TABLE `arbeitszeit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `gehalt`
--
ALTER TABLE `gehalt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT für Tabelle `gehaltsabrechnungvariableeingaben`
--
ALTER TABLE `gehaltsabrechnungvariableeingaben`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `info`
--
ALTER TABLE `info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT für Tabelle `krankheit`
--
ALTER TABLE `krankheit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `land`
--
ALTER TABLE `land`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `lohnkonto`
--
ALTER TABLE `lohnkonto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT für Tabelle `ma_historie`
--
ALTER TABLE `ma_historie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `ma_schulung`
--
ALTER TABLE `ma_schulung`
  MODIFY `schulung_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT für Tabelle `permission`
--
ALTER TABLE `permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT für Tabelle `position`
--
ALTER TABLE `position`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT für Tabelle `raum`
--
ALTER TABLE `raum`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `schulung`
--
ALTER TABLE `schulung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `skills`
--
ALTER TABLE `skills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `stammdaten`
--
ALTER TABLE `stammdaten`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `unternehmen`
--
ALTER TABLE `unternehmen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `urlaub`
--
ALTER TABLE `urlaub`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `arbeitszeit`
--
ALTER TABLE `arbeitszeit`
  ADD CONSTRAINT `arbeitszeit_ibfk_1` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`);

--
-- Constraints der Tabelle `gehaltsabrechnungvariableeingaben`
--
ALTER TABLE `gehaltsabrechnungvariableeingaben`
  ADD CONSTRAINT `gehaltsabrechnungvariableeingaben_ibfk_1` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`);

--
-- Constraints der Tabelle `krankheit`
--
ALTER TABLE `krankheit`
  ADD CONSTRAINT `krankheit_ibfk_1` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`);

--
-- Constraints der Tabelle `lohnkonto`
--
ALTER TABLE `lohnkonto`
  ADD CONSTRAINT `lohnkonto_ibfk_1` FOREIGN KEY (`maid`) REFERENCES `mitarbeiter` (`id`);

--
-- Constraints der Tabelle `ma_historie`
--
ALTER TABLE `ma_historie`
  ADD CONSTRAINT `ma_historie_ibfk_1` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`);

--
-- Constraints der Tabelle `ma_schulung`
--
ALTER TABLE `ma_schulung`
  ADD CONSTRAINT `ma_schulung_ibfk_1` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`),
  ADD CONSTRAINT `ma_schulung_ibfk_2` FOREIGN KEY (`schulung_id`) REFERENCES `schulung` (`id`);

--
-- Constraints der Tabelle `ma_skills`
--
ALTER TABLE `ma_skills`
  ADD CONSTRAINT `ma_skills_ibfk_1` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`),
  ADD CONSTRAINT `ma_skills_ibfk_2` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`);

--
-- Constraints der Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  ADD CONSTRAINT `mitarbeiter_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_2` FOREIGN KEY (`land`) REFERENCES `land` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_3` FOREIGN KEY (`id_gehalt`) REFERENCES `gehalt` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_4` FOREIGN KEY (`info_id`) REFERENCES `info` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_5` FOREIGN KEY (`plz`) REFERENCES `ort` (`plz`),
  ADD CONSTRAINT `mitarbeiter_ibfk_6` FOREIGN KEY (`abteilungs_id`) REFERENCES `abteilung` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_7` FOREIGN KEY (`positions_id`) REFERENCES `position` (`id`);

--
-- Constraints der Tabelle `raum`
--
ALTER TABLE `raum`
  ADD CONSTRAINT `raum_ibfk_1` FOREIGN KEY (`abteilungs_id`) REFERENCES `abteilung` (`id`);

--
-- Constraints der Tabelle `urlaub`
--
ALTER TABLE `urlaub`
  ADD CONSTRAINT `urlaub_ibfk_1` FOREIGN KEY (`ma_id`) REFERENCES `mitarbeiter` (`id`);

--
-- Constraints der Tabelle `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
