CREATE TABLE categories (
    category_id   NUMBER(4) NOT NULL,
    category_name VARCHAR2(30) NOT NULL
);

ALTER TABLE categories ADD CONSTRAINT categories_pk PRIMARY KEY ( category_id );

ALTER TABLE categories ADD CONSTRAINT categories__un UNIQUE ( category_name );

CREATE TABLE members (
    member_name  VARCHAR2(30) NOT NULL,
    phone_number VARCHAR2(30) NOT NULL,
    point        NUMBER(10) DEFAULT 0
);

ALTER TABLE members ADD CONSTRAINT members_pk PRIMARY KEY ( phone_number );

CREATE TABLE menu (
    menu_id     NUMBER(4) NOT NULL,
    menu_name   VARCHAR2(30) NOT NULL,
    price       NUMBER(10) NOT NULL,
    category_id NUMBER(4) NOT NULL
);

ALTER TABLE menu ADD CONSTRAINT menu_pk PRIMARY KEY ( menu_id );

ALTER TABLE menu ADD CONSTRAINT menu__un UNIQUE ( menu_name );

CREATE TABLE order_list (
    order_id    NUMBER NOT NULL,
    menu_id     NUMBER(4),
    quantity    NUMBER(4),
    total_price NUMBER(10)
);

CREATE TABLE orders (
    order_id     NUMBER NOT NULL,
    phone_number VARCHAR2(30),
    order_date   DATE DEFAULT SYSDATE NOT NULL
);

ALTER TABLE orders ADD CONSTRAINT order_pk PRIMARY KEY ( order_id );

CREATE TABLE sales (
    sales_id       NUMBER NOT NULL,
    order_id       NUMBER NOT NULL,
    payment_method VARCHAR2(30),
    use_point      NUMBER(10),
    sales_date     DATE DEFAULT SYSDATE NOT NULL
);

ALTER TABLE sales ADD CONSTRAINT sales_pk PRIMARY KEY ( sales_id );

ALTER TABLE sales ADD CONSTRAINT sales__un UNIQUE ( order_id );

ALTER TABLE menu
    ADD CONSTRAINT menu_categories_fk FOREIGN KEY ( category_id )
        REFERENCES categories ( category_id );

ALTER TABLE order_list
    ADD CONSTRAINT order_list_menu_fk FOREIGN KEY ( menu_id )
        REFERENCES menu ( menu_id )
        ON DELETE SET NULL;

ALTER TABLE order_list
    ADD CONSTRAINT order_list_order_fk FOREIGN KEY ( order_id )
        REFERENCES orders ( order_id );

ALTER TABLE orders
    ADD CONSTRAINT order_members_fk FOREIGN KEY ( phone_number )
        REFERENCES members ( phone_number )
        ON DELETE SET NULL
        ON UPDATE CASCADE;

ALTER TABLE sales
    ADD CONSTRAINT sales_order_fk FOREIGN KEY ( order_id )
        REFERENCES orders ( order_id );