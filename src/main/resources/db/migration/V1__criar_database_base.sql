CREATE TABLE `category` (
                            `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
                            `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `category` (`name`) values ('Lazer');
INSERT INTO `category` (`name`) values ('Alimentação');
INSERT INTO `category` (`name`) values ('Supermercado');
INSERT INTO `category` (`name`) values ('Farmácia');
INSERT INTO `category` (`name`) values ('Outros');

CREATE TABLE `person` (
                          `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
                          `active` BOOLEAN NOT NULL,
                          `complement` VARCHAR(30),
                          `neighborhood` varchar(30),
                          `number` varchar(30),
                          `public_place` varchar(30),
                          `zip_code` varchar(30),
                          `name` VARCHAR(50) NOT NULL,
                          `city_id` bigint(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-121', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-121', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-121', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-121', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-121', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-121', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-121', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 2, true);
INSERT INTO `person` (`name`, `public_place`, `number`, `complement`, `neighborhood`, `zip_code`, `city_id`, `active`) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-121', 2, true);

CREATE TABLE `release` (
                           `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                           `date_of_payment` DATE,
                           `description` VARCHAR(50) NOT NULL,
                           `due_date` DATE NOT NULL,
                           `observation` VARCHAR(100),
                           `type` VARCHAR(20) NOT NULL,
                           `value` DECIMAL(10,2) NOT NULL,
                           `category_id` BIGINT(20) NOT NULL,
                           `person_id` BIGINT(20) NOT NULL,
                           FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
                           FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Salário mensal', '2022-06-10', null, 6500.00, 'Distribuição de lucros', 'REVENUE', 1, 1);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Bahamas', '2022-02-10', '2022-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Top Club', '2022-06-10', null, 120, null, 'REVENUE', 3, 3);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('CEMIG', '2022-02-10', '2022-02-10', 110.44, 'Geração', 'REVENUE', 3, 4);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('DMAE', '2022-06-10', null, 200.30, null, 'EXPENSE', 3, 5);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Extra', '2022-03-10', '2022-03-10', 1010.32, null, 'REVENUE', 4, 6);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Bahamas', '2022-06-10', null, 500, null, 'REVENUE', 1, 7);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Top Club', '2022-03-10', '2022-03-10', 400.32, null, 'EXPENSE', 4, 8);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Despachante', '2022-06-10', null, 123.64, 'Multas', 'EXPENSE', 3, 9);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Pneus', '2022-04-10', '2022-04-10', 665.33, null, 'REVENUE', 5, 10);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Café', '2022-06-10', null, 8.32, null, 'EXPENSE', 1, 5);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Eletrônicos', '2022-04-10', '2022-04-10', 2100.32, null, 'EXPENSE', 5, 4);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Instrumentos', '2022-06-10', null, 1040.32, null, 'EXPENSE', 4, 3);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Café', '2022-04-10', '2022-04-10', 4.32, null, 'EXPENSE', 4, 2);
INSERT INTO `release` (`description`, `due_date`, `date_of_payment`, `value`, `observation`, `type`, `category_id`, `person_id`) values ('Lanche', '2022-06-10', null, 10.20, null, 'EXPENSE', 4, 1);

CREATE TABLE `user` (
                        `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                        `email` VARCHAR(50) NOT NULL unique,
                        `first_name` VARCHAR(50) NOT NULL,
                        `last_name` VARCHAR(150) NOT NULL,
                        `password` VARCHAR(150) NOT NULL,
                        `role` VARCHAR(20) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES (1, 'admin@algamoney.com', 'Administrador', 'Teste', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 'ROLE_USER');
INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES (2, 'maria@algamoney.com', 'Maria', 'Silva', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq', 'ROLE_USER');

CREATE TABLE `contact` (
                           `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                           `mail` VARCHAR(100) NOT NULL,
                           `name` VARCHAR(50) NOT NULL,
                           `phone` VARCHAR(20) NOT NULL,
                           `person_id` BIGINT(20) NOT NULL,
                           FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

insert into `contact` (`id`, `person_id`, `name`, `mail`, `phone`) values (1, 1, 'Marcos Henrique', 'marcos@algamoney.com', '00 0000-0000');

CREATE TABLE `state` (
                         `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                         `name` VARCHAR(50) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `state` (`id`, `name`) VALUES(1, 'Acre');
INSERT INTO `state` (`id`, `name`) VALUES(2, 'Alagoas');
INSERT INTO `state` (`id`, `name`) VALUES(3, 'Amazonas');
INSERT INTO `state` (`id`, `name`) VALUES(4, 'Amapá');
INSERT INTO `state` (`id`, `name`) VALUES(5, 'Bahia');
INSERT INTO `state` (`id`, `name`) VALUES(6, 'Ceará');
INSERT INTO `state` (`id`, `name`) VALUES(7, 'Distrito Federal');
INSERT INTO `state` (`id`, `name`) VALUES(8, 'Espírito Santo');
INSERT INTO `state` (`id`, `name`) VALUES(9, 'Goiás');
INSERT INTO `state` (`id`, `name`) VALUES(10, 'Maranhão');
INSERT INTO `state` (`id`, `name`) VALUES(11, 'Minas Gerais');
INSERT INTO `state` (`id`, `name`) VALUES(12, 'Mato Grosso do Sul');
INSERT INTO `state` (`id`, `name`) VALUES(13, 'Mato Grosso');
INSERT INTO `state` (`id`, `name`) VALUES(14, 'Pará');
INSERT INTO `state` (`id`, `name`) VALUES(15, 'Paraíba');
INSERT INTO `state` (`id`, `name`) VALUES(16, 'Pernambuco');
INSERT INTO `state` (`id`, `name`) VALUES(17, 'Piauí');
INSERT INTO `state` (`id`, `name`) VALUES(18, 'Paraná');
INSERT INTO `state` (`id`, `name`) VALUES(19, 'Rio de Janeiro');
INSERT INTO `state` (`id`, `name`) VALUES(20, 'Rio Grande do Norte');
INSERT INTO `state` (`id`, `name`) VALUES(21, 'Rondônia');
INSERT INTO `state` (`id`, `name`) VALUES(22, 'Roraima');
INSERT INTO `state` (`id`, `name`) VALUES(23, 'Rio Grande do Sul');
INSERT INTO `state` (`id`, `name`) VALUES(24, 'Santa Catarina');
INSERT INTO `state` (`id`, `name`) VALUES(25, 'Sergipe');
INSERT INTO `state` (`id`, `name`) VALUES(26, 'São Paulo');
INSERT INTO `state` (`id`, `name`) VALUES(27, 'Tocantins');

CREATE TABLE `city` (
                        `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                        `name` VARCHAR(50) NOT NULL,
                        `state_id` BIGINT(20) NOT NULL,
                        FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (1, 'Belo Horizonte', 11);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (2, 'Uberlândia', 11);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (3, 'Uberaba', 11);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (4, 'São Paulo', 26);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (5, 'Campinas', 26);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (6, 'Rio de Janeiro', 19);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (7, 'Angra dos Reis', 19);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (8, 'Goiânia', 9);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (9, 'Caldas Novas', 9);

ALTER TABLE `person` ADD CONSTRAINT `fk_pessoa_cidade` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`);