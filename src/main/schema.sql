CREATE DATABASE IF NOT EXISTS cjs_db;
USE cjs_db;

-- Users table (aligned with your provided structure)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'police', 'judge', 'lawyer', 'victim', 'witness', 'criminal') NOT NULL,
    full_name VARCHAR(100),
    designation VARCHAR(100),
    contact_info VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cases table
CREATE TABLE cases (
    case_id INT AUTO_INCREMENT PRIMARY KEY,
    fir_number VARCHAR(50) UNIQUE NOT NULL,
    police_officer_id INT,
    judge_id INT,
    lawyer_id INT,
    status ENUM('Under Investigation', 'In Court', 'Solved', 'Closed') NOT NULL,
    crime_type VARCHAR(100),
    incident_date DATE,
    location VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (police_officer_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (judge_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (lawyer_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Criminals table
CREATE TABLE criminals (
    criminal_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    cnic VARCHAR(15),
    address TEXT,
    charges VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

-- Victims table
CREATE TABLE victims (
    victim_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    cnic VARCHAR(15),
    contact VARCHAR(15),
    statement TEXT,
    protection_requested BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

-- Witnesses table
CREATE TABLE witnesses (
    witness_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    cnic VARCHAR(15),
    statement TEXT,
    protection_requested BOOLEAN DEFAULT FALSE,
    verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

-- Evidence table
CREATE TABLE evidence (
    evidence_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    description TEXT,
    file_path VARCHAR(255),
    type ENUM('Document', 'Image', 'Video'),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

-- Court Hearings table
CREATE TABLE court_hearings (
    hearing_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    hearing_date DATE NOT NULL,
    court_room VARCHAR(50),
    details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

-- Judgments table
CREATE TABLE judgments (
    judgment_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    verdict VARCHAR(255) NOT NULL,
    act_section VARCHAR(50),
    punishment VARCHAR(255),
    fine INT,
    judgment_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

-- Notifications table
CREATE TABLE notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    type ENUM('Case Update', 'Hearing Scheduled') NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Logs table (aligned with your provided structure)
CREATE TABLE logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    action VARCHAR(255),
    description TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Legal Acts table (replacing pakistani_acts.json)
CREATE TABLE legal_acts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    crime_name VARCHAR(100) NOT NULL,
    section_codes VARCHAR(50) NOT NULL,
    punishment TEXT NOT NULL,
    fine_amount DECIMAL(10,2) DEFAULT 0.00
);