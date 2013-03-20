

INSERT INTO `permission` (`id`, `description`, `permission`) VALUES
(1, 'desc', 'menu:user'),
(2, 'desc', 'menu:personal_info'),
(3, 'desc', 'menu:account'),
(4, 'desc', 'user:new'),
(5, 'desc', 'user:view'),
(6, 'desc', 'user:edit'),
(7, 'desc', 'user:delete'),
(8, 'desc', 'user:undelete'),
(9, 'desc', 'user:enable'),
(10, 'desc', 'user:disable'),
(11, 'desc', 'user:unlock'),
(12, 'desc', 'user:list_superadmin'),
(13, 'desc', 'user:list_enabled'),
(14, 'desc', 'menu:logs'),
(15, 'desc', 'menu:quiz'),
(16, 'desc', 'menu:session');

INSERT INTO `role` (`id`, `description`, `role`) VALUES
(1, 'desc', 'Superadmin'),
(2, 'desc', 'Admin'),
(3, 'desc', 'User');

INSERT INTO `role_permission` (`id_role`, `id_permission`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 8),
(2, 9),
(2, 10),
(2, 15),
(2, 16),
(3, 2),
(3, 3),
(3, 15);


--
-- Dump dei dati per la tabella `user_role`
--

INSERT INTO `user_role` (`id_user`, `id_role`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 3),
(5, 3);