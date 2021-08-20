CREATE TABLE CLIENT(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name varchar(200) NOT NULL,
    last_name varchar(200) NOT NULL,
    cpf varchar(15) NOT NULL,
    birthday date NOT NULL,
    celphone varchar(20) NOT NULL,
    gender varchar(20) NOT NULL,
    mail varchar(50) NOT NULL,
    password varchar(100) NOT NULL
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

)