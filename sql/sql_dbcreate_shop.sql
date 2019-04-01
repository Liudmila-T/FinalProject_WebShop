
DROP DATABASE IF EXISTS kidsToys;

CREATE DATABASE kidsToys DEFAULT CHARACTER SET UTF8;

USE kidsToys;

CREATE TABLE roles(
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');
INSERT INTO roles VALUES(2, 'blocked_user');

CREATE TABLE users(
	id INT NOT NULL auto_increment,
	 name VARCHAR(20) NOT NULL,
	login VARCHAR(15) UNIQUE NOT NULL,
	password VARCHAR(10) NOT NULL,
	e_mail VARCHAR(20) NOT NULL,
 	local_name VARCHAR(20),
	role_id INT NOT NULL,
  PRIMARY KEY (id, role_id),
  FOREIGN KEY(role_id) REFERENCES roles(id) ON DELETE CASCADE	ON UPDATE RESTRICT
);

INSERT INTO users VALUES(DEFAULT,'Liudmila', 'admin', 'a', 'timlv@gmail.com','en', 0);
INSERT INTO users VALUES(DEFAULT, 'Nickolay','user1', 'user1', 'a@gmail.com', NULL, 1);


CREATE TABLE statuses(
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO statuses VALUES(0, 'registered');
INSERT INTO statuses VALUES(1, 'paid');
INSERT INTO statuses VALUES(2, 'canceled');

CREATE TABLE categories(
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO categories VALUES(1, 'For babies'); 
INSERT INTO categories VALUES(2, 'For girls'); 
INSERT INTO categories VALUES(3, 'For boys'); 
INSERT INTO categories VALUES(4, 'Developing'); 

CREATE TABLE orders(
	id INT NOT NULL auto_increment,
	user_id INT NOT NULL,
	status_id INT NOT NULL,
	date DATE NOT NULL,
	amount INT NOT NULL,
	  PRIMARY KEY (id, user_id, status_id),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY(status_id) REFERENCES statuses(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE products(
	id INT NOT NULL  auto_increment,
	name VARCHAR(100) NOT NULL unique,
	price INT NOT NULL,
  color VARCHAR(100) NOT NULL,
  weight INT NOT NULL,
  dateManufacturer DATE NOT NULL,
  quantityInStock INT NOT NULL,
	category_id INT NOT NULL,
    PRIMARY KEY (id, category_id),
    FOREIGN KEY(category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO products VALUES(DEFAULT, 'Beanbag', 70, 'blue', 200, '2018/12/12', 100,1); -- 1 (product id)
INSERT INTO products VALUES(DEFAULT, 'Pyramid', 85,'multicolor', 300, '2018/4/12', 100,1); -- 2
INSERT INTO products VALUES(DEFAULT, 'Play mat', 200,'green', 200, '2017/12/12', 100, 1); -- 3

INSERT INTO products VALUES(DEFAULT, 'Puzzles', 70,'multicolor', 50, '2017/02/12', 100, 4); -- 4
INSERT INTO products VALUES(DEFAULT, 'Lotto', 50,'white', 100, '2018/03/12', 100, 4); -- 5
INSERT INTO products VALUES(DEFAULT, 'Mosaic', 100,'multicolor', 250, '2018/09/12', 100, 4); -- 6

INSERT INTO products VALUES(DEFAULT, 'Doll', 250, 'multicolor', 500, '2018/12/31', 100, 2); -- 7
INSERT INTO products VALUES(DEFAULT, 'Doll Barby', 300, 'multicolor', 400, '2018/01/31', 100, 2); -- 8
INSERT INTO products VALUES(DEFAULT, 'Kids Kitchen', 200, 'multicolor', 1000, '2016/11/12', 100, 2); -- 9
      
INSERT INTO products VALUES(DEFAULT, 'Car', 160, 'red', 350, '2018/08/12', 100, 3); -- 10
INSERT INTO products VALUES(DEFAULT, 'Robot', 260, 'black', 300, '2015/12/12', 100, 3); -- 11
INSERT INTO products VALUES(DEFAULT, 'Robot new', 260, 'blue', 300, '2018/12/12', 100, 3); -- 12

CREATE TABLE carts(
	order_id INT NOT NULL,
	products_id INT NOT NULL,
  quantity INT NOT NULL,
  price INT NOT NULL,
    PRIMARY KEY (order_id, products_id),
    FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY(products_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE RESTRICT
);


SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM categories;
SELECT * FROM statuses;
SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM carts;