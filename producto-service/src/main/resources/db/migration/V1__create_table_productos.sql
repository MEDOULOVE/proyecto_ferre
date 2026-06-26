CREATE TABLE productos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,

    categoria VARCHAR(255),
    marca VARCHAR(255),
    codigo_producto VARCHAR(255) UNIQUE
);