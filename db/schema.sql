-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 13 juin 2022 à 11:04
-- Version du serveur : 8.0.29
-- Version de PHP : 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `flashpizza`
--

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `available_messenger`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `available_messenger` (
`Nom` varchar(45)
,`Status` varchar(50)
,`Téléphone` varchar(15)
,`Vehicule` varchar(45)
,`Type vehicule` varchar(45)
);

-- --------------------------------------------------------

--
-- Structure de la table `ingredient`
--

CREATE TABLE `ingredient` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `ingredient`
--

INSERT INTO `ingredient` (`id`, `name`) VALUES
(1, 'Tomate'),
(2, 'Mozzarella'),
(3, 'Champignon'),
(4, 'Jambon'),
(5, 'Oignon'),
(6, 'Poivron'),
(7, 'Olive'),
(8, 'Roquette');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `menu`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `menu` (
`id` int
,`Pizza` varchar(45)
,`Ingredient` varchar(45)
,`Price` decimal(4,2)
);

-- --------------------------------------------------------

--
-- Structure de la table `messenger`
--

CREATE TABLE `messenger` (
  `id` int NOT NULL,
  `state_id` int NOT NULL DEFAULT '1',
  `vehicle_id` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone_number` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `messenger`
--

INSERT INTO `messenger` (`id`, `state_id`, `vehicle_id`, `name`, `phone_number`) VALUES
(1, 2, NULL, 'Boubakar', '+99mabite');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `messenger_status`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `messenger_status` (
`Nom` varchar(45)
,`Status` varchar(50)
,`Téléphone` varchar(15)
,`Vehicule` varchar(45)
,`Type vehicule` varchar(45)
);

-- --------------------------------------------------------

--
-- Structure de la table `order`
--

CREATE TABLE `order` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `state_id` int NOT NULL DEFAULT '1',
  `messenger_id` int DEFAULT NULL,
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delivery_date` timestamp NULL DEFAULT NULL,
  `price` decimal(5,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `order`
--

INSERT INTO `order` (`id`, `user_id`, `state_id`, `messenger_id`, `order_date`, `delivery_date`, `price`) VALUES
(2, 1, 6, 1, '2022-06-13 07:23:57', NULL, '52.00');

--
-- Déclencheurs `order`
--
DELIMITER $$
CREATE TRIGGER `after_update_order` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
if NEW.state_id = 2	THEN
    UPDATE user set user.balance = user.balance - NEW.price;
    end if;
    
if NEW.state_id = 5	THEN
    UPDATE messenger set messenger.state_id = 1 WHERE messenger.id = NEW.messenger_id;
    end if;
    
if NEW.state_id = 6	THEN
    UPDATE messenger set messenger.state_id = 2 WHERE messenger.id = NEW.messenger_id;
    end if;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_update_order` BEFORE UPDATE ON `order` FOR EACH ROW BEGIN
if (SELECT user.balance FROM user WHERE user.id = NEW.user_id)< NEW.price AND NEW.state_id = 2
    then
      SIGNAL sqlstate '45001' set message_text = "No way ! You cannot do this !";
    end if;
    
    if NEW.messenger_id is NULL AND NEW.state_id = 5 then
      SIGNAL sqlstate '45001' set message_text = "No way ! You cannot do this !";
    end if;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `order_line`
--

CREATE TABLE `order_line` (
  `id` int NOT NULL,
  `order_id` int NOT NULL,
  `pizza_id` int NOT NULL,
  `size_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `order_line`
--

INSERT INTO `order_line` (`id`, `order_id`, `pizza_id`, `size_id`, `quantity`, `price`) VALUES
(1, 2, 1, 1, 4, '52.00');

--
-- Déclencheurs `order_line`
--
DELIMITER $$
CREATE TRIGGER `after_insert_order_line` AFTER INSERT ON `order_line` FOR EACH ROW UPDATE `order` as o
SET o.price = o.price + NEW.price
WHERE o.id = NEW.order_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_insert_order_line` BEFORE INSERT ON `order_line` FOR EACH ROW SET NEW.price = 
(SELECT p.price from `pizza_price` as p
WHERE pizza_id = NEW.pizza_id AND size_id = NEW.size_id) * NEW.quantity
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `order_state`
--

CREATE TABLE `order_state` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `order_state`
--

INSERT INTO `order_state` (`id`, `name`) VALUES
(1, 'Basket'),
(2, 'Ordered'),
(3, 'Cooking'),
(4, 'Ready'),
(5, 'Delivering'),
(6, 'Delivered');

-- --------------------------------------------------------

--
-- Structure de la table `pizza`
--

CREATE TABLE `pizza` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `pizza`
--

INSERT INTO `pizza` (`id`, `name`, `price`) VALUES
(1, 'Reine', '13.00'),
(2, 'Végétarienne', '15.00'),
(3, 'Regina', '10.00');

-- --------------------------------------------------------

--
-- Structure de la table `pizza_ingredient`
--

CREATE TABLE `pizza_ingredient` (
  `id` int NOT NULL,
  `pizza_id` int NOT NULL,
  `ingredient_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `pizza_ingredient`
--

INSERT INTO `pizza_ingredient` (`id`, `pizza_id`, `ingredient_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 2, 1),
(6, 2, 2),
(7, 2, 3),
(8, 2, 5),
(9, 2, 6),
(10, 3, 1),
(11, 3, 2),
(12, 3, 3),
(13, 3, 4),
(14, 3, 5),
(15, 3, 7),
(16, 3, 8);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `pizza_price`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `pizza_price` (
`pizza_id` int
,`Pizza` varchar(45)
,`size_id` int
,`Taille` varchar(45)
,`Price` decimal(7,4)
);

-- --------------------------------------------------------

--
-- Structure de la table `pizza_size`
--

CREATE TABLE `pizza_size` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `coefficient` decimal(3,2) NOT NULL DEFAULT '1.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `pizza_size`
--

INSERT INTO `pizza_size` (`id`, `name`, `coefficient`) VALUES
(1, 'Humaine', '1.00'),
(2, 'Naine', '0.75'),
(3, 'Ogresse', '1.25');

-- --------------------------------------------------------

--
-- Structure de la table `state`
--

CREATE TABLE `state` (
  `id` int NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `state`
--

INSERT INTO `state` (`id`, `Name`) VALUES
(1, 'Non disponible'),
(2, 'Disponible');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `username` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `balance` decimal(18,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `phone_number`, `email`, `password`, `create_time`, `balance`) VALUES
(1, 'Antonin', '+33641941449', 'anto@gmail.com', 'pwd', '2022-06-10 12:43:08', '48.00');

-- --------------------------------------------------------

--
-- Structure de la table `vehicle`
--

CREATE TABLE `vehicle` (
  `id` int NOT NULL,
  `state_id` int NOT NULL,
  `type_id` int NOT NULL,
  `matriculation` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `vehicle_type`
--

CREATE TABLE `vehicle_type` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `vehicle_type`
--

INSERT INTO `vehicle_type` (`id`, `name`) VALUES
(1, 'Car'),
(2, 'Motorcycle');

-- --------------------------------------------------------

--
-- Structure de la vue `available_messenger`
--
DROP TABLE IF EXISTS `available_messenger`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `available_messenger`  AS SELECT `messenger_status`.`Nom` AS `Nom`, `messenger_status`.`Status` AS `Status`, `messenger_status`.`Téléphone` AS `Téléphone`, `messenger_status`.`Vehicule` AS `Vehicule`, `messenger_status`.`Type vehicule` AS `Type vehicule` FROM `messenger_status` WHERE (`messenger_status`.`Status` = 'Disponible')  ;

-- --------------------------------------------------------

--
-- Structure de la vue `menu`
--
DROP TABLE IF EXISTS `menu`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `menu`  AS SELECT DISTINCT `p`.`id` AS `id`, `p`.`name` AS `Pizza`, `i`.`name` AS `Ingredient`, `p`.`price` AS `Price` FROM ((`pizza` `p` join `pizza_ingredient` `pi` on((`pi`.`pizza_id` = `p`.`id`))) join `ingredient` `i` on((`pi`.`ingredient_id` = `i`.`id`)))  ;

-- --------------------------------------------------------

--
-- Structure de la vue `messenger_status`
--
DROP TABLE IF EXISTS `messenger_status`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `messenger_status`  AS SELECT `m`.`name` AS `Nom`, `s`.`Name` AS `Status`, `m`.`phone_number` AS `Téléphone`, `v`.`matriculation` AS `Vehicule`, `vt`.`name` AS `Type vehicule` FROM (((`messenger` `m` join `state` `s` on((`s`.`id` = `m`.`state_id`))) left join `vehicle` `v` on((`v`.`id` = `m`.`vehicle_id`))) left join `vehicle_type` `vt` on((`vt`.`id` = `v`.`type_id`)))  ;

-- --------------------------------------------------------

--
-- Structure de la vue `pizza_price`
--
DROP TABLE IF EXISTS `pizza_price`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pizza_price`  AS SELECT `p`.`id` AS `pizza_id`, `p`.`name` AS `Pizza`, `s`.`id` AS `size_id`, `s`.`name` AS `Taille`, (`s`.`coefficient` * `p`.`price`) AS `Price` FROM (`pizza` `p` join `pizza_size` `s`) ORDER BY `p`.`id` ASC, (`s`.`coefficient` * `p`.`price`) ASC  ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `messenger`
--
ALTER TABLE `messenger`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_messenger_state1_idx` (`state_id`),
  ADD KEY `fk_vehicle_id_idx` (`vehicle_id`);

--
-- Index pour la table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_user_idx` (`user_id`),
  ADD KEY `fk_order_order_state1_idx` (`state_id`),
  ADD KEY `fk_order_messenger1_idx` (`messenger_id`);

--
-- Index pour la table `order_line`
--
ALTER TABLE `order_line`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_line_order1_idx` (`order_id`),
  ADD KEY `fk_order_line_pizza1_idx` (`pizza_id`),
  ADD KEY `fk_order_line_pizza_size1_idx` (`size_id`);

--
-- Index pour la table `order_state`
--
ALTER TABLE `order_state`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pizza_ingredient`
--
ALTER TABLE `pizza_ingredient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pizza_ingredient_pizza1_idx` (`pizza_id`),
  ADD KEY `fk_pizza_ingredient_ingredient1_idx` (`ingredient_id`);

--
-- Index pour la table `pizza_size`
--
ALTER TABLE `pizza_size`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_vehicle_state1_idx` (`state_id`),
  ADD KEY `fk_vehicle_vehicle_type1_idx` (`type_id`);

--
-- Index pour la table `vehicle_type`
--
ALTER TABLE `vehicle_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `messenger`
--
ALTER TABLE `messenger`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `order`
--
ALTER TABLE `order`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `order_line`
--
ALTER TABLE `order_line`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `order_state`
--
ALTER TABLE `order_state`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `pizza`
--
ALTER TABLE `pizza`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `pizza_ingredient`
--
ALTER TABLE `pizza_ingredient`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `pizza_size`
--
ALTER TABLE `pizza_size`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `state`
--
ALTER TABLE `state`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `vehicle_type`
--
ALTER TABLE `vehicle_type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `messenger`
--
ALTER TABLE `messenger`
  ADD CONSTRAINT `fk_messenger_state1` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`),
  ADD CONSTRAINT `fk_vehicle_id1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_order_messenger1` FOREIGN KEY (`messenger_id`) REFERENCES `messenger` (`id`),
  ADD CONSTRAINT `fk_order_order_state1` FOREIGN KEY (`state_id`) REFERENCES `order_state` (`id`),
  ADD CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `order_line`
--
ALTER TABLE `order_line`
  ADD CONSTRAINT `fk_order_line_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `fk_order_line_pizza1` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`),
  ADD CONSTRAINT `fk_order_line_pizza_size1` FOREIGN KEY (`size_id`) REFERENCES `pizza_size` (`id`);

--
-- Contraintes pour la table `pizza_ingredient`
--
ALTER TABLE `pizza_ingredient`
  ADD CONSTRAINT `fk_pizza_ingredient_ingredient1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `fk_pizza_ingredient_pizza1` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`);

--
-- Contraintes pour la table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `fk_vehicle_state1` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`),
  ADD CONSTRAINT `fk_vehicle_vehicle_type1` FOREIGN KEY (`type_id`) REFERENCES `vehicle_type` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
