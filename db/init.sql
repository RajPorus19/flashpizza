SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
USE `flashpizza` ;

--
-- Déchargement des données de la table `state`
--
INSERT INTO `state` (`Name`) VALUES
('Non disponible'),
('Disponible');
--
-- Déchargement des données de la table `pizza_size`
--
INSERT INTO `pizza_size` (`name`, `coefficient`) VALUES
('Humaine', '1.00'),
('Naine', '0.75'),
('Ogresse', '1.25');
--
-- Déchargement des données de la table `user`
--
INSERT INTO `user` (`firstname`, `phone_number`, `email`, `password`) VALUES
('Antonin', '+33641941449', 'anto@gmail.com', 'pwd'),
('Raj Porus', '+33699887766', 'raj@gmail.com', 'pwd');

INSERT INTO `transaction` (`user_id`, `amount`) VALUES
(2, 100),
(2, 45),
(1, 15);

--
-- Déchargement des données de la table `vehicle_type`
--
INSERT INTO `vehicle_type` (`name`) VALUES
('Car'),
('Motorcycle');

--
-- Déchargement des données de la table `vehicle`
--
INSERT INTO `vehicle` (`type_id`, `matriculation`) VALUES
(1, "AB-737-AT"),
(2, "AA-572-BF"),
(1, "AC-613-ET"),
(1, "AA-229-AA");

--
-- Déchargement des données de la table `pizza`
--
INSERT INTO `pizza` (`name`, `price`) VALUES
('Queen', '15.30'),
('Végétarienne', '15.00'),
('Regina', '9.95'),
('Hawaïenne', '17.30'),
('4 Fromages', '17.30'),
('Campagnarde', '17.30');

--
-- Déchargement des données de la table `ingredient`
--
INSERT INTO `ingredient` (`name`) VALUES
('Sauce Tomate'),
('Mozzarella'),
('Champignon'),
('Jambon'),
('Oignon'),
('Poivron'),
('Olive'),
('Roquette'),
('Fromage de chèvre'),
('Emmental'),
('Poulet'),
('Ananas'),
('Lardon'),
('Tomate fraîche'),
('Crème fraiche');

--
-- Déchargement des données de la table `pizza_ingredient`
--
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3),
(2, 5),
(2, 6),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 7),
(3, 8),
(4,1),
(4,2),
(4,11),
(4,12),
(5,1),
(5,2),
(5,9),
(5,10),
(6,2),
(6,13),
(6,15);
--
-- Déchargement des données de la table `messenger`
--
INSERT INTO `messenger` (`state_id`, `vehicle_id`, `name`, `phone_number`) VALUES
(2, NULL, 'Jean Pierre', '+33611223344');
--
-- Déchargement des données de la table `order_state`
--
INSERT INTO `order_state` (`name`) VALUES
('Basket'),
('Ordered'),
('Cooking'),
('Ready'),
('Delivering'),
('Delivered');
--
-- Déchargement des données de la table `order`
--
INSERT INTO `order` (`user_id`, `state_id`, `messenger_id`) VALUES
(1, 1, 1);
--
-- Déchargement des données de la table `order_line`
--
INSERT INTO `order_line` (`order_id`, `pizza_id`, `size_id`, `quantity`) VALUES
(1, 1, 1, 4),
(1, 2, 2, 1);