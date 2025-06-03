CREATE DATABASE cjs_db;
USE cjs_db;


CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Police', 'Judge', 'Lawyer', 'Criminal', 'Victim', 'Witness') NOT NULL,
    name VARCHAR(100) NOT NULL,
    cnic VARCHAR(15),
    designation VARCHAR(50),
    contact VARCHAR(15),
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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
    FOREIGN KEY (police_officer_id) REFERENCES users(user_id) ON DELETE SET NULL,
    FOREIGN KEY (judge_id) REFERENCES users(user_id) ON DELETE SET NULL,
    FOREIGN KEY (lawyer_id) REFERENCES users(user_id) ON DELETE SET NULL
);
SELECT username, password_hash FROM cjs_db.users WHERE username = 'admin01';
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

CREATE TABLE evidence (
    evidence_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    description TEXT,
    file_path VARCHAR(255),
    type ENUM('Document', 'Image', 'Video'),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

CREATE TABLE court_hearings (
    hearing_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    hearing_date DATE NOT NULL,
    court_room VARCHAR(50),
    details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id) ON DELETE CASCADE
);

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

CREATE TABLE notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    type ENUM('Case Update', 'Hearing Scheduled') NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    action TEXT NOT NULL,
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);USE cjs_db;

-- Assuming the schema is already defined; otherwise, recreate it
INSERT INTO cjs_db.users (username, password_hash, role, name, designation) VALUES
('admin01', 'hashed_adminpass', 'admin', 'Ali Raza', 'System Administrator'),
('police01', 'hashed_pass123', 'police', 'Shoaib Malik', 'Inspector'),
('judge01', 'hashed_judgepass', 'judge', 'Justice Ahmed', 'High Court Judge'),
('lawyer01', 'hashed_lawyerpass', 'lawyer', 'Nadia Mirza', 'Defense Lawyer'),
('victim01', 'hashed_victimpass', 'victim', 'Sara Khan', ''),
('witness01', 'hashed_witpass', 'witness', 'Asif Bashir', ''),
('criminal01', 'hashed_crimpass', 'criminal', 'Zahid Mehmood', ''),
('police02', 'hashed_pass456', 'police', 'Kashif Raza', 'DSP'),
('police03', 'hashed_pass789', 'police', 'Adnan Shah', 'SI'),
('judge02', 'hashed_judge123', 'judge', 'Justice Saima', 'Session Judge'),
('judge03', 'hashed_judge456', 'judge', 'Justice Aslam', 'District Judge'),
('lawyer02', 'hashed_lawpass', 'lawyer', 'Rashid Mehmood', 'Prosecution Lawyer'),
('lawyer03', 'hashed_lawpass2', 'lawyer', 'Mehreen Saeed', 'Civil Lawyer'),
('victim02', 'hashed_vic456', 'victim', 'Marium Iqbal', ''),
('victim03', 'hashed_vic789', 'victim', 'Ayesha Nadeem', ''),
('criminal02', 'hashed_crim456', 'criminal', 'Rauf Jutt', ''),
('criminal03', 'hashed_crim789', 'criminal', 'Iqbal Shah', ''),
('witness02', 'hashed_wit456', 'witness', 'Bilal Tariq', ''),
('witness03', 'hashed_wit789', 'witness', 'Farhan Yousuf', '');
INSERT INTO legal_acts (crime_name, section_codes, punishment, fine_amount) VALUES
('Murder (Qatl-e-Amd)', '302', 'Death penalty or life imprisonment', 0.00),
('Rape', '375, 376', 'Death penalty or 10+ years imprisonment', 0.00),
('Theft', '378, 379', 'Up to 3 years imprisonment or fine', 0.00),
('Robbery', '392', 'Up to 10 years imprisonment and fine', 0.00),
('Dacoity', '395', 'Life imprisonment or 10 years and fine', 0.00),
('Kidnapping for Ransom', '365-A', 'Death penalty or life imprisonment', 0.00),
('Blasphemy', '295, 295-A to 295-C', 'Life imprisonment or death penalty', 0.00),
('Bribery', '161, 165', 'Up to 3 years imprisonment or fine', 0.00),
('Cyber Terrorism', 'PECA 10', 'Death penalty or up to 14 years & fine', 0.00),
('Forgery', '463, 465', 'Up to 2 years imprisonment or fine', 0.00),
('Terrorism', 'ATA 7', 'Death penalty or life imprisonment and fine', 0.00),
('Acid Attack', '336-B', 'Min. 14 years imprisonment and fine', 0.00),
('Sexual Harassment', '509', 'Up to 3 years or Rs. 500,000 fine', 500000.00),
('Domestic Violence', '337-L2', 'Up to 2 years imprisonment or fine', 0.00),
('Honor Killing', '302', 'Death penalty or life imprisonment', 0.00),
('Drug Trafficking', 'CNSA 9', 'Death penalty or life imprisonment and fine', 0.00),
('Corruption by Public Servant', '409', 'Up to 10 years imprisonment and fine', 0.00),
('Counterfeiting Currency', '489-A', 'Up to 7 years imprisonment and fine', 0.00),
('Criminal Breach of Trust', '406', 'Up to 7 years imprisonment or fine', 0.00),
('Assault', '352', 'Up to 3 months imprisonment or Rs. 1,500 fine', 1500.00);


INSERT INTO logs (user_id, action, description) VALUES
(1, 'LOGIN', 'Admin logged in'),
(2, 'CASE_ASSIGNED', 'Case #2 assigned to Judge Saima'),
(3, 'UPLOAD_EVIDENCE', 'Evidence added to Case #3'),
(4, 'CREATE_CASE', 'New FIR registered for cybercrime'),
(5, 'UPDATE_STATUS', 'Case #5 status changed to Investigating'),
(6, 'JUDGMENT_ADDED', 'Final verdict submitted in Case #1'),
(7, 'WITNESS_STATEMENT', 'Witness statement recorded for Case #4'),
(8, 'LOGIN', 'Police officer accessed dashboard'),
(9, 'HEARING_SCHEDULED', 'Court hearing added for Case #6'),
(10, 'PROTECTION_REQUESTED', 'Victim requested protection for Case #7');


INSERT INTO cases (fir_number, police_officer_id, judge_id, lawyer_id, status, crime_type, incident_date, location) VALUES
('FIR-2025-001', 2, 3, 4, 'Under Investigation', 'Murder (Qatl-e-Amd)', '2025-01-01', 'Lahore'),
('FIR-2025-002', 8, 10, 12, 'In Court', 'Theft', '2025-02-01', 'Karachi'),
('FIR-2025-003', 9, NULL, NULL, 'Under Investigation', 'Cyber Terrorism', '2025-03-01', 'Islamabad'),
('FIR-2025-004', 2, 11, 13, 'In Court', 'Rape', '2025-01-15', 'Rawalpindi'),
('FIR-2025-005', 8, NULL, NULL, 'Under Investigation', 'Robbery', '2025-02-10', 'Faisalabad'),
('FIR-2025-006', 9, 3, 4, 'Closed', 'Assault', '2025-03-05', 'Multan'),
('FIR-2025-007', 2, 10, 12, 'In Court', 'Kidnapping for Ransom', '2025-01-20', 'Peshawar'),
('FIR-2025-008', 8, NULL, NULL, 'Under Investigation', 'Drug Trafficking', '2025-02-15', 'Quetta'),
('FIR-2025-009', 9, 11, 13, 'Solved', 'Bribery', '2025-03-10', 'Hyderabad'),
('FIR-2025-010', 2, 3, 4, 'In Court', 'Dacoity', '2025-01-25', 'Sialkot'),
('FIR-2025-011', 8, NULL, NULL, 'Under Investigation', 'Forgery', '2025-02-20', 'Gujranwala'),
('FIR-2025-012', 9, 10, 12, 'Closed', 'Sexual Harassment', '2025-03-15', 'Larkana'),
('FIR-2025-013', 2, 11, 13, 'In Court', 'Terrorism', '2025-01-30', 'Bahawalpur'),
('FIR-2025-014', 8, NULL, NULL, 'Under Investigation', 'Acid Attack', '2025-02-25', 'Sargodha'),
('FIR-2025-015', 9, 3, 4, 'Solved', 'Domestic Violence', '2025-03-20', 'Mirpur Khas'),
('FIR-2025-016', 2, 10, 12, 'In Court', 'Honor Killing', '2025-02-05', 'Sukkur'),
('FIR-2025-017', 8, NULL, NULL, 'Under Investigation', 'Corruption by Public Servant', '2025-03-25', 'Sheikhupura'),
('FIR-2025-018', 9, 11, 13, 'Closed', 'Counterfeiting Currency', '2025-02-10', 'Rahim Yar Khan'),
('FIR-2025-019', 2, 3, 4, 'In Court', 'Criminal Breach of Trust', '2025-03-30', 'Jhelum'),
('FIR-2025-020', 8, 10, 12, 'Under Investigation', 'Blasphemy', '2025-02-15', 'Mardan'),
('FIR-2025-021', 9, NULL, NULL, 'Solved', 'Theft', '2025-03-01', 'Abbottabad'),
('FIR-2025-022', 2, 11, 13, 'In Court', 'Murder (Qatl-e-Amd)', '2025-02-20', 'Gujrat');

INSERT INTO criminals (case_id, name, cnic, address, charges) VALUES
(1, 'Zahid Mehmood', '35202-8901234-8', 'Lahore', 'PPC-302'),
(1, 'Imran Qureshi', '35202-8901235-9', 'Lahore', 'PPC-302'),
(2, 'Rauf Jutt', '35202-9012345-9', 'Karachi', 'PPC-379'),
(3, 'Faisal Baloch', '35202-9123456-0', 'Islamabad', 'PECA-10'),
(4, 'Kamran Ali', '35202-9234567-1', 'Rawalpindi', 'PPC-375'),
(5, 'Nadeem Khan', '35202-9345678-2', 'Faisalabad', 'PPC-392'),
(5, 'Asad Ullah', '35202-9456789-3', 'Faisalabad', 'PPC-392'),
(6, 'Sajid Mehmood', '35202-9567890-4', 'Multan', 'PPC-352'),
(7, 'Waseem Akhtar', '35202-9678901-5', 'Peshawar', 'PPC-365-A'),
(8, 'Zubair Shah', '35202-9789012-6', 'Quetta', 'CNSA-9'),
(9, 'Arif Hussain', '35202-9890123-7', 'Hyderabad', 'PPC-161'),
(10, 'Qasim Raza', '35202-9901234-8', 'Sialkot', 'PPC-395'),
(10, 'Bilal Ahmed', '35202-9012345-9', 'Sialkot', 'PPC-395'),
(11, 'Tahir Iqbal', '35202-9123456-0', 'Gujranwala', 'PPC-465'),
(12, 'Shoaib Butt', '35202-9234567-1', 'Larkana', 'PPC-509'),
(13, 'Javed Khan', '35202-9345678-2', 'Bahawalpur', 'ATA-7'),
(14, 'Amir Sohail', '35202-9456789-3', 'Sargodha', 'PPC-336-B'),
(15, 'Khalid Mehmood', '35202-9567890-4', 'Mirpur Khas', 'PPC-337-L2'),
(16, 'Rizwan Ali', '35202-9678901-5', 'Sukkur', 'PPC-302'),
(17, 'Usman Ghani', '35202-9789012-6', 'Sheikhupura', 'PPC-409'),
(18, 'Farooq Azam', '35202-9890123-7', 'Rahim Yar Khan', 'PPC-489-A'),
(19, 'Naeem Abbas', '35202-9901234-8', 'Jhelum', 'PPC-406'),
(20, 'Hassan Raza', '35202-9012345-9', 'Mardan', 'PPC-295');


INSERT INTO victims (case_id, name, cnic, contact, statement, protection_requested) VALUES
(1, 'Sara Khan', '35202-0123456-0', '03090123456', 'Victim of murder incident', TRUE),
(1, 'Amina Bibi', '35202-1234567-1', '03101234567', 'Family member of deceased', TRUE),
(2, 'Marium Iqbal', '35202-2345678-2', '03212345678', 'Victim of theft', FALSE),
(3, 'Hina Aslam', '35202-3456789-3', '03323456789', 'Targeted by cyber attack', TRUE),
(4, 'Sobia Naz', '35202-4567890-4', '03434567890', 'Survivor of assault', TRUE),
(5, 'Fatima Zafar', '35202-5678901-5', '03545678901', 'Robbed at gunpoint', FALSE),
(6, 'Ayesha Siddiqui', '35202-6789012-6', '03656789012', 'Assaulted in public', FALSE),
(7, 'Zainab Riaz', '35202-7890123-7', '03767890123', 'Kidnapped child', TRUE),
(8, 'Naila Parveen', '35202-8901234-8', '03878901234', 'Witnessed drug deal', TRUE),
(9, 'Saima Javed', '35202-9012345-9', '03989012345', 'Bribed official', FALSE),
(10, 'Rubina Akhtar', '35202-0123456-0', '03001234567', 'Victim of dacoity', TRUE),
(11, 'Bushra Khan', '35202-1234567-1', '03112345678', 'Received forged documents', FALSE),
(12, 'Sana Ullah', '35202-2345678-2', '03223456789', 'Harassed at workplace', TRUE),
(13, 'Farida Begum', '35202-3456789-3', '03334567890', 'Targeted in terror attack', TRUE),
(14, 'Shazia Malik', '35202-4567890-4', '03445678901', 'Acid attack survivor', TRUE),
(15, 'Mehwish Noor', '35202-5678901-5', '03556789012', 'Abused by spouse', TRUE),
(16, 'Khadija Bano', '35202-6789012-6', '03667890123', 'Victim of honor killing', TRUE),
(17, 'Sadia Rehman', '35202-7890123-7', '03778901234', 'Defrauded by official', FALSE),
(18, 'Rukhsana Bibi', '35202-8901234-8', '03889012345', 'Received fake currency', FALSE),
(19, 'Noreen Aslam', '35202-9012345-9', '03990123456', 'Trust betrayed by partner', FALSE),
(20, 'Tahira Yasmeen', '35202-0123456-0', '03012345678', 'Accused of blasphemy', TRUE),
(21, 'Amna Batool', '35202-1234567-1', '03123456789', 'Theft victim', FALSE);


INSERT INTO witnesses (case_id, name, cnic, statement, protection_requested, verified) VALUES
(1, 'Asif Bashir', '35202-2345678-0', 'Witnessed the murder', TRUE, TRUE),
(1, 'Bilal Tariq', '35202-3456789-1', 'Saw the suspect fleeing', TRUE, TRUE),
(2, 'Farhan Yousuf', '35202-4567890-2', 'Saw the theft incident', FALSE, TRUE),
(3, 'Imtiaz Ahmed', '35202-5678901-3', 'Noticed suspicious online activity', FALSE, TRUE),
(4, 'Zeeshan Haider', '35202-6789012-4', 'Heard the assault', TRUE, TRUE),
(5, 'Noman Raza', '35202-7890123-5', 'Saw the robbery', FALSE, TRUE),
(6, 'Adeel Qureshi', '35202-8901234-6', 'Witnessed the assault', FALSE, TRUE),
(7, 'Saqib Mehmood', '35202-9012345-7', 'Saw the kidnapping', TRUE, TRUE),
(8, 'Haris Khan', '35202-0123456-8', 'Reported drug activity', TRUE, TRUE),
(9, 'Umair Ali', '35202-1234567-9', 'Overheard bribery deal', FALSE, TRUE),
(10, 'Taimoor Shah', '35202-2345678-0', 'Saw the dacoity', TRUE, TRUE),
(11, 'Waqas Javed', '35202-3456789-1', 'Received forged papers', FALSE, TRUE),
(12, 'Fahad Butt', '35202-4567890-2', 'Reported harassment', TRUE, TRUE),
(13, 'Irfan Ullah', '35202-5678901-3', 'Survived terror attack', TRUE, TRUE),
(14, 'Sohail Akram', '35202-6789012-4', 'Saw the acid attack', TRUE, TRUE),
(15, 'Arsalan Malik', '35202-7890123-5', 'Reported domestic abuse', FALSE, TRUE),
(16, 'Yasir Iqbal', '35202-8901234-6', 'Witnessed honor killing', TRUE, TRUE),
(17, 'Danish Rehman', '35202-9012345-7', 'Reported corruption', FALSE, TRUE),
(18, 'Junaid Aslam', '35202-0123456-8', 'Received fake notes', FALSE, TRUE),
(19, 'Salman Riaz', '35202-1234567-9', 'Noticed trust breach', FALSE, TRUE),
(20, 'Ahsan Nadeem', '35202-2345678-0', 'Heard blasphemy remarks', TRUE, TRUE),
(21, 'Zain Abbas', '35202-3456789-1', 'Saw the theft suspect', FALSE, TRUE);