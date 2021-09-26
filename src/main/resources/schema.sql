CREATE TABLE USER
(
    id        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name      varchar(200) NOT NULL,
    last_name varchar(200) NOT NULL,
    cpf       varchar(15)  NOT NULL,
    birthday  date         NOT NULL,
    celphone  varchar(20)  NOT NULL,
    gender    varchar(20)  NOT NULL,
    mail      varchar(50)  NOT NULL,
    password  varchar(100) NOT NULL,
    role      varchar(100) NOT NULL
)

CREATE TABLE ADDRESS
(
    id           INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    address      varchar(200),
    name_address varchar(200),
    type_address varchar(200),
    country      varchar(50),
    state        varchar(100),
    number       varchar(10),
    complement   varchar(200),
    postal_code  varchar(100),
    id_user      int,
    category     varchar(100),
    observation  varchar(100)
)

CREATE TABLE PRODUCT
(
    id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name        varchar(200),
    description varchar(1000),
    category    varchar(100),
    value       varchar(100),
    id_manager  integer,
    size        varchar(200),
    sale_price   varchar(100),
    image varchar(100)
)

CREATE TABLE CREDIT_CARD
(
    id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    card_number varchar(50),
    cvv         varchar(10),
    flag        varchar(30),
    expire_date varchar(30),
    card_name   varchar(10),
    id_user     int
)

CREATE TABLE CUPON
(
    id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name        varchar(50),
    id_manager  INT,
    quantity    int,
    count_using int,
    type        varchar(10),
    value       varchar(30),
    is_valid    varchar(30)
)

CREATE TABLE CATEGORY
(
    id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    category   varchar(200),
    id_manager int,
    profit     varchar(50)
)

CREATE TABLE PRODUCT_INVETORY
(
    ID                INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_manager        INT,
    id_product        INT,
    original_quantity INT,
    current_quantity  INT
)

CREATE TABLE CARD_FLAG
(
    ID        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name      varchar(200),
    is_active varchar(20)
)

CREATE TABLE PURCHASE_ORDER
(
    ID             INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_user        INT,
    id_address     INT,
    id_credit_card INT NULL,
    payment_type   varchar(20),
    id_cupom       INT NULL,
    total_value    VARCHAR(200),
    data           DATE
)

CREATE TABLE ORDER_ITEM
(
    ID         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_pedido  INT,
    id_user    INT,
    id_manager INT,
    id_produto INT,
    unit_price varchar(100),
    product_image varchar(100),
    quantity    INT
)