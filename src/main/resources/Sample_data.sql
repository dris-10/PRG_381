INSERT INTO materials
(material_name, category, quantity, reorder_level, unit, supplier_id)
VALUES

('Dishwashing Liquid', 'Cleaning Chemicals', 50, 10, 'Bottle', 1),

('Floor Cleaner', 'Cleaning Chemicals', 35, 8, 'Bottle', ),

('Microfiber Cloth', 'Cleaning Equipment', 100, 20, 'Pack', 1),

('Rubber Gloves', 'Safety Equipment', 75, 15, 'Box', 1),

('Toilet Paper', 'Paper Products', 200, 50, 'Roll', 1),

('Hand Soap', 'Cleaning Chemicals', 60, 15, 'Bottle', ),

('Bin Bags', 'Waste Management', 150, 30, 'Pack', 1),

('Mop Heads', 'Cleaning Equipment', 25, 5, 'Piece', 1),

('Air Freshener', 'Hygiene Products', 40, 10, 'Can', 1),

('Bleach', 'Cleaning Chemicals', 30, 10, 'Bottle', 1);

INSERT INTO users (full_name, username, email, password, role)
VALUES
('John Smith', 'jsmith', 'john.smith@company.com', 'Password123', 'Storekeeper'),
('Sarah Williams', 'swilliams', 'sarah.williams@company.com', 'Password123', 'Supervisor'),
('Michael Brown', 'mbrown', 'michael.brown@company.com', 'Password123', 'Storekeeper');

INSERT INTO suppliers
(supplier_name, contact_person, phone, email, address)
VALUES
('CleanPro Supplies', 'David Jones', '0115551234', 'sales@cleanpro.co.za', '15 Main Road, Johannesburg'),
('Hygiene Solutions', 'Amanda Peters', '0215559876', 'info@hygienesolutions.co.za', '22 Long Street, Cape Town'),
('Sparkle Distributors', 'Peter Adams', '0315554455', 'orders@sparkledist.co.za', '8 Beach Road, Durban');

INSERT INTO cleaners
(first_name, last_name, phone, department)
VALUES
('Alice', 'Nkosi', '0821112233', 'Administration'),
('Brian', 'Mokoena', '0832223344', 'Engineering'),
('Cynthia', 'Naidoo', '0843334455', 'Reception'),
('Daniel', 'Khumalo', '0814445566', 'Production'),
('Emily', 'Botha', '0825556677', 'Warehouse');

INSERT INTO stock_issuance
(cleaner_id, material_id, quantity_issued, issued_by)
VALUES
(1, 1, 2, 1),
(2, 3, 1, 2),
(3, 2, 5, 1),
(4, 4, 3, 3),
(5, 5, 2, 2),
(1, 6, 1, 1),
(2, 7, 4, 3),
(3, 8, 2, 2);