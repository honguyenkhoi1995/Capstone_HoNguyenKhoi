CREATE DATABASE IF NOT EXISTS crm_project
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE crm_project;

DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS project_members;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(120) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(30),
    role_id INT NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE projects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    creator_id INT NOT NULL,
    CONSTRAINT fk_project_creator FOREIGN KEY (creator_id) REFERENCES users(id)
);

CREATE TABLE project_members (
    project_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (project_id, user_id),
    CONSTRAINT fk_pm_project FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_pm_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    assignee_id INT NOT NULL,
    project_id INT NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'NOT_STARTED',
    CONSTRAINT fk_task_user FOREIGN KEY (assignee_id) REFERENCES users(id),
    CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

INSERT INTO roles(name, description) VALUES
('ADMIN','Quản trị hệ thống'),
('LEADER','Quản lý dự án'),
('MEMBER','Nhân viên');

INSERT INTO users(email,password,full_name,address,phone,role_id) VALUES
('admin@gmail.com','123','Admin','Hà Nội','0900000001',1),
('leader@gmail.com','123','Leader Demo','Hà Nội','0900000002',2),
('member@gmail.com','123','Member Demo','Hà Nội','0900000003',3);

INSERT INTO projects(name,description,start_date,end_date,creator_id)
VALUES ('CRM Demo','Dự án CRM quản lý công việc','2026-01-01','2026-12-31',2);

INSERT INTO project_members(project_id,user_id) VALUES (1,3);

INSERT INTO tasks(name,description,start_date,end_date,assignee_id,project_id,status)
VALUES ('Xây dựng giao diện','Làm giao diện CRM','2026-01-02','2026-02-01',3,1,'IN_PROGRESS');
