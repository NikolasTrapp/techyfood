CREATE TABLE IF NOT EXISTS category
(
    id     UUID         NOT NULL,
    "name" VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS price
(
    id    UUID        NOT NULL,
    value NUMERIC(19) NOT NULL
);


CREATE TABLE IF NOT EXISTS restaurant
(
    id     UUID         NOT NULL,
    active BOOLEAN      NOT NULL,
    "name" VARCHAR(255) NULL
);


CREATE TABLE IF NOT EXISTS product
(
    id          UUID         NOT NULL,
    "name"      VARCHAR(90)  NOT NULL,
    category    UUID         NULL,
    price       UUID         NULL,
    active      BOOLEAN      NOT NULL,
    description VARCHAR(255) NULL,
    image       VARCHAR(255) NULL,
    restaurant  UUID         NULL
);


ALTER TABLE IF EXISTS category ADD CONSTRAINT pk_category_id PRIMARY KEY (id);
ALTER TABLE IF EXISTS category ADD CONSTRAINT un_category_name UNIQUE (name);

ALTER TABLE IF EXISTS price ADD CONSTRAINT pk_price_id PRIMARY KEY (id);

ALTER TABLE IF EXISTS restaurant ADD CONSTRAINT pk_restaurant_id PRIMARY KEY (id);

ALTER TABLE IF EXISTS product ADD CONSTRAINT pk_product_id PRIMARY KEY (id);
ALTER TABLE IF EXISTS product ADD CONSTRAINT fk_price FOREIGN KEY (price) REFERENCES price (id);
ALTER TABLE IF EXISTS product ADD CONSTRAINT fk_category FOREIGN KEY (category) REFERENCES category (id);
ALTER TABLE IF EXISTS product ADD CONSTRAINT fk_restaurant FOREIGN KEY (restaurant) REFERENCES restaurant (id);