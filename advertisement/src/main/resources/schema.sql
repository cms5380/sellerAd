DROP TABLE IF EXISTS Advertisement;
DROP TABLE IF EXISTS ADVERTISEMENT_CPC;

CREATE TABLE Advertisement
(
    advertisement_id    INT generated by default as identity(start with 1000000001) PRIMARY KEY,
    company_id          INT NOT NULL,
    product_id          INT NOT NULL,
    advertisement_price INT NOT NULL
);

CREATE TABLE CPC_TARGET
(
    CPC_ID    INT generated by default as identity(start with 1000000001) PRIMARY KEY,
    advertisement_id          INT NOT NULL,
    CLICK_DATETIME          DATETIME NOT NULL
);

