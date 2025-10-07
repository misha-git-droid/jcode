CREATE TABLE employee (
    id INT,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    position VARCHAR(20),
    salary INT,
    department_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES department(id)
);