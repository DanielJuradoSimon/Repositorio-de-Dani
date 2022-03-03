/*TABLA USUARIOS - POTENTIALCLIENT*/
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (1, '11111111A', 'Sanchez',  'Calle 1', '1@gmail.com',  '1976-10-02', 'Pepe', '695924211');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (2, '22222222B',  'Rodriguez', 'Calle 2', '2@gmail.com', '1970-04-22', 'Laura', '695924222');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (3, '33333333C', 'Perez', 'Calle 3', '3@gmail.com', '2000-12-11', 'Marcos', '695924233');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (4, '44444444D', 'Simon', 'Calle 4', '4@gmail.com', '1943-10-11', 'Ismael', '695924244');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (5, '55555555E', 'Marquez', 'Calle 5', '5@gmail.com', '1989-06-30', 'Marta', '695924255');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (6, '66666666f', 'Campos', 'Calle 6', '6@gmail.com', '1980-01-05', 'Luis', '695924266');
INSERT INTO clientes(id, dni, apellidos, direccion, email, fecha_de_nacimiento, nombre, telefono) VALUES (7, '77777777G', 'Luna', 'Calle 7', '7@gmail.com', '1996-05-18', 'David', '695924277');


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

