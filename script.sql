drop database ATS2;
CREATE DATABASE ATS2;
USE ATS;

CREATE DATABASE IF NOT EXISTS ATS;
USE ATS;

-- Table: Admins
CREATE TABLE Admins (
    email VARCHAR(100) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

-- Table: Login
CREATE TABLE Login (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    failedLoginAttempts INT DEFAULT 0,
    accountLocked BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_login_email FOREIGN KEY (email) REFERENCES Admins(email)
);

-- Table: Planes
CREATE TABLE Planes (
    planeCode VARCHAR(10) NOT NULL PRIMARY KEY,
    registrationNumber VARCHAR(50) NOT NULL,
    model VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    manufacturer VARCHAR(100) NOT NULL
);

-- Table: Airports
CREATE TABLE Airports (
    airportCode VARCHAR(10) NOT NULL PRIMARY KEY,
    airportName VARCHAR(100) NOT NULL,
    countryId VARCHAR(10) NOT NULL,
    UNIQUE(countryId)
);

-- Table: Bookings
CREATE TABLE Bookings (
    bookingId VARCHAR(10) NOT NULL PRIMARY KEY,
    countryId VARCHAR(10) NOT NULL,
    airportCode VARCHAR(10) NOT NULL,
    planeCode VARCHAR(10) NOT NULL,
    bookingDate DATE NOT NULL,
    seatNumber INT NOT NULL,
    ticketCost DOUBLE NOT NULL,
    totalAmount DOUBLE NOT NULL,
    CONSTRAINT fk_bookings_country FOREIGN KEY (countryId) REFERENCES Airports(countryId),
    CONSTRAINT fk_bookings_airport FOREIGN KEY (airportCode) REFERENCES Airports(airportCode),
    CONSTRAINT fk_bookings_plane FOREIGN KEY (planeCode) REFERENCES Planes(planeCode)
);
