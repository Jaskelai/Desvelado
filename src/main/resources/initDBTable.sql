CREATE TABLE desvelado_user
(
  id        SERIAL NOT NULL
    CONSTRAINT desvelado_user_pkey
    PRIMARY KEY,
  email     VARCHAR(50),
  password  VARCHAR(100),
  country   VARCHAR(20),
  gender    BOOLEAN,
  birthdate VARCHAR(15)
);