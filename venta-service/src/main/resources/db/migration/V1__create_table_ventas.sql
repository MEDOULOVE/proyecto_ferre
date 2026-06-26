CREATE TABLE ventas (

                        id BIGINT PRIMARY KEY AUTO_INCREMENT,

                        cliente VARCHAR(255) NOT NULL,

                        producto_id BIGINT NOT NULL,

                        producto_nombre VARCHAR(255) NOT NULL,

                        cantidad INT NOT NULL,

                        total DOUBLE NOT NULL

);