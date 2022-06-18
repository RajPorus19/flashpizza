SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

USE `flashpizza` ;

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

--
-- Déchargement des données de la table `state`
--

INSERT INTO `state` (`id`, `Name`) VALUES
(1, 'Non disponible'),
(2, 'Disponible');

--
-- Déchargement des données de la table `pizza_size`
--

INSERT INTO `pizza_size` (`id`, `name`, `coefficient`) VALUES
(1, 'Humaine', '1.00'),
(2, 'Naine', '0.75'),
(3, 'Ogresse', '1.25');

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `phone_number`, `email`, `password`, `create_time`, `balance`) VALUES
(1, 'Antonin', '+33641941449', 'anto@gmail.com', 'pwd', '2022-06-10 12:43:08', '48.00');

--
-- Déchargement des données de la table `vehicle_type`
--

INSERT INTO `vehicle_type` (`id`, `name`) VALUES
(1, 'Car'),
(2, 'Motorcycle');

--
-- Déchargement des données de la table `pizza`
--

INSERT INTO `pizza` (`id`, `name`, `price`) VALUES
(1, 'Reine', '13.00'),
(2, 'Végétarienne', '15.00'),
(3, 'Regina', '10.00');

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

--
-- Déchargement des données de la table `messenger`
--

INSERT INTO `messenger` (`id`, `state_id`, `vehicle_id`, `name`, `phone_number`) VALUES
(1, 2, NULL, 'Boubakar', '+99mabite');

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

--
-- Déchargement des données de la table `order`
--

INSERT INTO `order` (`id`, `user_id`, `state_id`, `messenger_id`, `order_date`, `delivery_date`, `price`) VALUES
(2, 1, 6, 1, '2022-06-13 07:23:57', NULL, '52.00');

--
-- Déchargement des données de la table `order_line`
--

INSERT INTO `order_line` (`id`, `order_id`, `pizza_id`, `size_id`, `quantity`, `price`) VALUES
(1, 2, 1, 1, 4, '52.00');