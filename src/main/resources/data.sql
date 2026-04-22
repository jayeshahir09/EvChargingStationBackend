INSERT INTO user_table (name,email,mobile_number,password)
VALUES
    ("Jayesh Ahir","jayeshrb@gmail.com","7069100765","jayesh7069"),
    ("Ashvin Ahir","ashvinahir@gmail.com","9157458131","ash91574"),
    ("Devan Ahir","devanahir2@gmail.com","9157505461","dev91574");

INSERT INTO station_owner (name, email, password, mobile_number)
VALUES
    ('Rajesh Patel', 'rajesh.patel@gmail.com', 'rajesh@123', '9876543210'),
    ('Amit Shah', 'amit.shah@gmail.com', 'amit@123', '9123456789');


INSERT INTO charging_station
(station_name, latitude, longitude, address, city, state, station_owner_station_owner_id)
VALUES
    ('City Light EV Fast Charge', 21.1474, 72.7791, 'City Light Road', 'Surat', 'Gujarat', 1),

    ('Adajan EV Charging Hub', 21.1938, 72.8003, 'Adajan Gam', 'Surat', 'Gujarat', 1),

    ('Vesu Public EV Station', 21.1396, 72.7702, 'Vesu Main Road', 'Surat', 'Gujarat', 2),

    ('Ring Road EV Charging Point', 21.1702, 72.8311, 'Ring Road', 'Surat', 'Gujarat', 2),

    ('Athwa Lines EV Station', 21.1810, 72.8086, 'Athwa Lines', 'Surat', 'Gujarat', 1);

INSERT INTO charger (charger_type, power_kw, price_per_unit, station_id, connector_type)
VALUES
    ('FAST',50,12.0,1,'CCS2'),
    ('NORMAL',22,8.0,1,'TYPE2'),

    ('FAST',60,14.0,2,'CHAdeMO'),
    ('NORMAL',30,9.0,2,'TYPE2'),

    ('FAST',100,18.0,3,'CCS2'),
    ('NORMAL',40,10.0,3,'CHAdeMO');

-- Simple slots with static dates (Jan 22-23, 2024)
INSERT INTO slot (start_time, end_time, slot_status, charger_charger_id) VALUES
-- TODAY (Jan 22, 2024)
-- Charger 1
('2026-04-17 22:00:00', '2026-04-17 23:30:00', 'AVAILABLE', 1),
('2026-04-17 10:00:00', '2026-04-17 11:00:00', 'BOOKED', 1),
('2026-04-17 11:00:00', '2026-04-17 12:00:00', 'AVAILABLE', 1),
('2026-04-17 14:00:00', '2026-04-17 15:00:00', 'AVAILABLE', 1),
('2026-04-17 21:00:00', '2026-04-17 22:00:00', 'BOOKED', 1),

-- Charger 2
('2026-04-17 21:20:00', '2026-04-17 22:00:00', 'AVAILABLE', 2),
('2026-04-17 10:00:00', '2026-04-17 11:00:00', 'AVAILABLE', 2),
('2026-04-17 11:00:00', '2026-04-17 12:00:00', 'BOOKED', 2),
('2026-04-17 23:00:00', '2026-04-18 00:00:00', 'AVAILABLE', 2),

-- TOMORROW (Jan 23, 2024)
-- Charger 1
('2026-04-18 09:00:00', '2026-04-18 10:00:00', 'AVAILABLE', 1),
('2026-04-18 10:00:00', '2026-04-18 11:00:00', 'AVAILABLE', 1),
('2026-04-19 11:00:00', '2026-04-19 12:00:00', 'AVAILABLE', 1),
('2026-04-19 14:00:00', '2026-04-19 15:00:00', 'AVAILABLE', 1),

-- Charger 2
('2026-04-18 22:00:00', '2026-04-18 23:00:00', 'BOOKED', 2),
('2026-04-18 10:00:00', '2026-04-18 11:00:00', 'AVAILABLE', 2),
('2026-04-19 11:00:00', '2026-04-19 12:00:00', 'AVAILABLE', 2);


INSERT INTO booking
(cost, booking_status, slot_slot_id, charger_charger_id, charging_station_station_id, user_user_id)
VALUES
-- 1️⃣ FUTURE booking (CONFIRMED → IN_PROGRESS later)
(120.00, 'CONFIRMED', 2, 1, 1, 1),

-- 2️⃣ CURRENT booking (IN_PROGRESS)
(132.00, 'IN_PROGRESS', 5, 1, 1, 1),

-- 3️⃣ COMPLETED booking (PAST)
(96.00, 'COMPLETED', 8, 2, 1, 1),

-- 4️⃣ CANCELLED booking
(0.00, 'CANCELLED', 15, 2, 1, 1);

INSERT INTO notification
(title, message, is_read, created_at, user_user_id)
VALUES
    ('Charging Slot Reminder ⚡', 'Your charging slot starts at 9:30 AM.', false, NOW(), 1),
    ('Booking Cancelled ❌', 'Your booking at Green EV Hub was cancelled.', true, NOW(), 1);
