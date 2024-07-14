CREATE TABLE IF NOT EXISTS Todo (
    id INT NOT NULL,
    goal VARCHAR(100) NOT NULL,
    create_date VARCHAR(100) NOT NULL,
    due_date VARCHAR(100) NOT NULL,
    completed INT NOT NULL,
    version INT,
    PRIMARY KEY (id)
);

-- CREATE TABLE IF NOT EXISTS "User" (
--     id INT NOT NULL,
--     [name] VARCHAR(100) NOT NULL,
--     [password] VARCHAR(100) NOT NULL,
--     [email] VARCHAR(100) NOT NULL UNIQUE,
--     version INT,
--     PRIMARY KEY (id)
-- );