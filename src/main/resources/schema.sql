CREATE TABLE USER(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name varchar(200) NOT NULL,
    last_name varchar(200) NOT NULL,
    cpf varchar(15) NOT NULL,
    birthday date NOT NULL,
    celphone varchar(20) NOT NULL,
    gender varchar(20) NOT NULL,
    mail varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    role varchar(100) NOT NULL
)

CREATE TABLE ADDRESS(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    address varchar(200),
    name_address varchar(200),
    type_address varchar(200),
    country varchar(50),
    state varchar(100),
    number varchar(10),
    complement varchar(200),
    postal_code varchar(100),
    id_user int,
    category varchar(100),
    observation varchar(100)
)

CREATE TABLE CREDIT_CARD(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    card_number varchar(50),
    cvv varchar(10),
    flag varchar(30),
    expire_date varchar(30),
    card_name varchar(10),
    id_user int
)