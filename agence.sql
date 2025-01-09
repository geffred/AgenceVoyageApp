-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 09 jan. 2025 à 17:05
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `agence`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `email`, `nom`, `prenom`, `telephone`) VALUES
(1, 'jean.dupont@gmail.com', 'Dupont', 'Jean', '0476123456'),
(2, 'marie.peeters@gmail.com', 'Peeters', 'Marie', '0486234567'),
(3, 'luc.vandenbroeck@gmail.com', 'Van den Broeck', 'Luc', '0495345678'),
(4, 'sophie.desmet@gmail.com', 'De Smet', 'Sophie', '0487456789'),
(5, 'pierre.lemaire@gmail.com', 'Lemaire', 'Pierre', '0475567890'),
(6, 'laura.verhoeven@gmail.com', 'Verhoeven', 'Laura', '0496678901'),
(7, 'paul.jacobs@gmail.com', 'Jacobs', 'Paul', '0485789012'),
(8, 'clara.maes@gmail.com', 'Maes', 'Clara', '0494890123');

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `paye` bit(1) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `voyage_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`paye`, `client_id`, `id`, `voyage_id`) VALUES
(b'1', 1, 1, 1),
(b'0', 1, 2, 2),
(b'1', 2, 3, 2),
(b'1', 2, 4, 3),
(b'0', 3, 5, 4),
(b'0', 5, 6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `transports`
--

CREATE TABLE `transports` (
  `heure_arrivee` time(6) NOT NULL,
  `heure_depart` time(6) NOT NULL,
  `type_transport` tinyint(4) NOT NULL,
  `id` bigint(20) NOT NULL,
  `compagnie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `transports`
--

INSERT INTO `transports` (`heure_arrivee`, `heure_depart`, `type_transport`, `id`, `compagnie`) VALUES
('22:40:00.000000', '16:43:00.000000', 0, 1, 'SkyBelgium Airlines'),
('10:30:00.000000', '07:00:00.000000', 0, 2, 'Royal AeroLines'),
('15:45:00.000000', '09:15:00.000000', 3, 3, 'SNCB'),
('16:10:00.000000', '07:00:00.000000', 2, 4, 'Flex Bus'),
('20:10:00.000000', '10:20:00.000000', 1, 5, 'bla bla car'),
('23:00:00.000000', '05:00:00.000000', 4, 6, '237 Bateau'),
('16:50:00.000000', '12:30:00.000000', 0, 7, 'Altitude Express'),
('20:00:00.000000', '14:50:00.000000', 3, 8, 'SNCF');

-- --------------------------------------------------------

--
-- Structure de la table `voyages`
--

CREATE TABLE `voyages` (
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `prix` double NOT NULL,
  `id` bigint(20) NOT NULL,
  `transport_id` bigint(20) NOT NULL,
  `destination` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `voyages`
--

INSERT INTO `voyages` (`date_debut`, `date_fin`, `prix`, `id`, `transport_id`, `destination`) VALUES
('2025-01-10', '2025-01-11', 50, 1, 4, 'Paris'),
('2025-01-10', '2025-01-11', 60, 2, 1, 'Allemagne ( Berlin )'),
('2025-01-10', '2025-01-11', 80, 3, 2, 'Pays Bas '),
('2025-01-15', '2025-01-16', 15, 4, 3, 'Mons ( Belgique )'),
('2025-01-10', '2025-01-11', 75, 5, 5, 'Espagne');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsrv16ica2c1csub334bxjjb59` (`email`),
  ADD UNIQUE KEY `UKchbwssnlj6d3v8fcet3hl92he` (`telephone`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6lekctbt4u88agg0b7cjsj6lf` (`client_id`),
  ADD KEY `FKk76afidsdh9xtx96oq0q229yb` (`voyage_id`);

--
-- Index pour la table `transports`
--
ALTER TABLE `transports`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `voyages`
--
ALTER TABLE `voyages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrwt7jfnok4jmlkq15l6phayd` (`transport_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `transports`
--
ALTER TABLE `transports`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `voyages`
--
ALTER TABLE `voyages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `FK6lekctbt4u88agg0b7cjsj6lf` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  ADD CONSTRAINT `FKk76afidsdh9xtx96oq0q229yb` FOREIGN KEY (`voyage_id`) REFERENCES `voyages` (`id`);

--
-- Contraintes pour la table `voyages`
--
ALTER TABLE `voyages`
  ADD CONSTRAINT `FKrwt7jfnok4jmlkq15l6phayd` FOREIGN KEY (`transport_id`) REFERENCES `transports` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
