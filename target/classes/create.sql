CREATE TABLE `Books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `People` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `second_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Accounts` (
  `id` int(11) NOT NULL,
  `balance` DECIMAL(11) NOT NULL,
  `user_id` int(11) NOT NULL,
   FOREIGN KEY (user_id) 
    REFERENCES public.People(id),
  PRIMARY KEY (`id`)
);

CREATE TABLE `Transfers` (
  `id` int(11) NOT NULL auto_increment,
  `account_from_id` int(11) NOT NULL,
  `account_to_id` int(11) NOT NULL,
  `amount` DECIMAL(11) NOT NULL,
  `dt` TIMESTAMP NOT NULL,
   FOREIGN KEY (account_from_id) 
    REFERENCES public.Accounts(id),
   FOREIGN KEY (account_to_id) 
    REFERENCES public.Accounts(id),
  PRIMARY KEY (`id`)
);