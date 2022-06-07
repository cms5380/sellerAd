DROP TABLE IF EXISTS Settlement;

CREATE TABLE Settlement
(
    settlement_id    INT generated by default as identity (start with 1000000001) PRIMARY KEY,
    advertisement_id INT NOT NULL,
    click_count      INT NOT NULL
);
