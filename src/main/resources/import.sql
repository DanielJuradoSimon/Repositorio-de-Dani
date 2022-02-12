/*TABLA USUARIOS - POTENTIALCLIENT*/
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (1, '1111A', 'Contreras del Campo',  'Calle de la alegría', 'smiles@gmail.com',  '1976-10-02', 'Carmela', '695924211');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (2, '2222B',  'Caballero Blanco', 'Plaza de la esperanza', 'cabama70@gmail.com', '1970-04-22', 'Marcos', '695924222');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (3, '3333C', 'La Hoz Moreno', 'Paseo de la victoria', 'megustanadar@gmail.com', '2000-12-11', 'Lucía', '695924233');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (4, '4444D', 'Adoración Rodrigo', 'Calle Tomellosera', 'quijote_y_sancho_panza@gmail.com', '1943-10-11', 'Dorita', '695924244');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (5, '5555E', 'Jurado Barrón', 'Arrubial de la palmera', 'givememusic_givemelife@gmail.com', '1989-06-30', 'Rafa', '695924255');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (6, '6666f', 'Campos', 'Carlos III', 'abcdf@gmail.com', '1980-01-05', 'Lupita', '695924266');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (7, '7777G', 'Rodriguez Linares', 'Calle La Amapola', 'massoloquelauna@gmail.com', '1996-05-18', 'Javier', '695924277');


/*TABLA CUENTAS - ACCOUNT*/
INSERT INTO cuentas(id, fecha_de_creacion, numero_de_cuenta, saldo) VALUES (1, '2000-05-18', 'ES21-1989-0100-72-2030875684', 1500);
INSERT INTO cuentas(id, fecha_de_creacion, numero_de_cuenta, saldo) VALUES (2, '2001-01-10', 'ES24-1989-0100-72-2030876288', 15000);
INSERT INTO cuentas(id, fecha_de_creacion, numero_de_cuenta, saldo) VALUES (3, '2010-04-21', 'ES86-1989-0100-72-2030876156', 2500);
INSERT INTO cuentas(id, fecha_de_creacion, numero_de_cuenta, saldo) VALUES (4, '2006-11-03', 'ES15-1989-0100-72-2030889295', 30000);

/*TABLA CLIENT_ACCOUNT*/
INSERT INTO cliente_cuenta(cliente_id, cuenta_id) VALUES (3, 1);
INSERT INTO cliente_cuenta(cliente_id, cuenta_id) VALUES (1, 2);
INSERT INTO cliente_cuenta(cliente_id, cuenta_id) VALUES (2, 2);
INSERT INTO cliente_cuenta(cliente_id, cuenta_id) VALUES (5, 3);
INSERT INTO cliente_cuenta(cliente_id, cuenta_id) VALUES (4, 4);
INSERT INTO cliente_cuenta(cliente_id, cuenta_id) VALUES (6, 4);

/*TABLA OPERACIONES - OPERATION*/
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (1, 500, '2021-12-01', 'RETIRADA', 1);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (2, 500, '2021-12-02', 'INGRESO', 2);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (3, 1000, '2021-12-03', 'TRANSFERENCIA_EMITIDA', 2);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (4, 1000, '2021-12-03', 'TRANSFERENCIA_RECIBIDA', 3);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (5, 125, '2021-12-04', 'RETIRADA', 3);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (6, 1250, '2021-12-05', 'RETIRADA', 2);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (7, 2500, '2021-12-06', 'TRANSFERENCIA_EMITIDA', 4);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (8, 2500, '2021-12-06', 'TRANSFERENCIA_RECIBIDA', 1);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (9, 1750, '2021-12-07', 'RETIRADA', 4);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (10, 500, '2021-12-08', 'INGRESO', 4);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (11, 50, '2021-12-09', 'INGRESO', 3);
INSERT INTO operacion(id, cantidad, fecha_de_realizacion, operacion, cuenta_id) VALUES (12, 750, '2021-12-10', 'INGRESO', 2);

