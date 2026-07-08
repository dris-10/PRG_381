CREATE TABLE users(
user_id SERIAL PRIMARY KEY,
full_name VARCHAR(100) NOT NULL,
username VARCHAR(50) UNIQUE NOT NULL,
email VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(250) NOT NULL,
role VARCHAR(20) NOT NULL
CHECK (role IN ('Storekeeper', 'Supervisor'))
);

CREATE TABLE suppliers(
supplier_id SERIAL PRIMARY KEY,
supplier_name VARCHAR(100) NOT NULL,
contact_person VARCHAR(100),
phone VARCHAR(20),
email VARCHAR(100),
address TEXT
);

CREATE TABLE materials(
material_id SERIAL PRIMARY KEY,
material_name VARCHAR(100) NOT NULL,
category VARCHAR(50),
quantity INT NOT NULL CHECK( quantity >= 0),
reorder_level INT NOT NULL CHECK(reorder_level >= 0),
unit VARCHAR(20),
supplier_id INT,

CONSTRAINT fk_supplier
FOREIGN KEY (supplier_id)
REFERENCES suppliers(supplier_id)
ON DELETE SET NULL
);

CREATE TABLE cleaners(
cleaner_id SERIAL PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
phone VARCHAR(20),
department VARCHAR(100)
);

CREATE TABLE stock_issuance(
issuance_id SERIAL PRIMARY KEY,
cleaner_id INT NOT NULL,
material_id INT NOT NULL,
quantity_issued INT NOT NULL CHECK(quantity_issued > 0),
issued_by INT NOT NULL,
issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (cleaner_id) REFERENCES cleaners(cleaner_id),
FOREIGN KEY (issued_by) REFERENCES users(user_id)
);