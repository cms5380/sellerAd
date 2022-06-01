DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Company;

CREATE TABLE Product
(
    product_id   INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    price        INT          NOT NULL,
    stock_count  INT          NOT NULL

);

CREATE TABLE Company
(
    company_id              INT AUTO_INCREMENT PRIMARY KEY,
    company_name            VARCHAR(255) NOT NULL,
    company_business_number INT          NOT NULL,
    company_tel             VARCHAR(15)  NOT NULL,
    company_address         VARCHAR(100) NOT NULL
);
