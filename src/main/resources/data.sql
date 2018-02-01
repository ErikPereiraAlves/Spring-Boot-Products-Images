

INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_PARENT_ID, PRODUCT_NAME,PRODUCT_DESC,PRODUCT_PRICE,PRODUCT_CREATED_TS,PRODUCT_UPDATED_TS)
VALUES (1,1,'Apple Iphone','Apple Smartphones', 0.0,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_PARENT_ID, PRODUCT_NAME,PRODUCT_DESC,PRODUCT_PRICE,PRODUCT_CREATED_TS,PRODUCT_UPDATED_TS)
VALUES (2,1,'Apple Iphone 5','Apple Smartphone version 5', 1000.500,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_PARENT_ID, PRODUCT_NAME,PRODUCT_DESC,PRODUCT_PRICE,PRODUCT_CREATED_TS,PRODUCT_UPDATED_TS)
VALUES (3,1,'Apple Iphone 6','Apple Smartphone version 6', 2000.200,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_PARENT_ID, PRODUCT_NAME,PRODUCT_DESC,PRODUCT_PRICE,PRODUCT_CREATED_TS,PRODUCT_UPDATED_TS)
VALUES (4,4,'Sumsung smartphone','Sumsung Smartphones', 0.0,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_PARENT_ID, PRODUCT_NAME,PRODUCT_DESC,PRODUCT_PRICE,PRODUCT_CREATED_TS,PRODUCT_UPDATED_TS)
VALUES (5,4,'Sumsung Galaxy 1','Sumsung Galaxy 1', 600.0,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_PARENT_ID, PRODUCT_NAME,PRODUCT_DESC,PRODUCT_PRICE,PRODUCT_CREATED_TS,PRODUCT_UPDATED_TS)
VALUES (6,4,'Sumsung Galaxy 2','Sumsung Galaxy 2', 900.0,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (1,2,'www.google.com/iphone5-1.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (2,2,'www.google.com/iphone5-2.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (3,2,'www.google.com/iphone5-3.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (4,3,'www.google.com/iphone6-1.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (5,3,'www.google.com/iphone6-2.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (6,3,'www.google.com/iphone6-3.png');


INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (7,5,'www.google.com/sumsung-galaxy1-1.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (8,5,'www.google.com/sumsung-galaxy1-2.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (9,5,'www.google.com/sumsung-galaxy1-3.png');


INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (10,6,'www.google.com/sumsung-galaxy2-1.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (11,6,'www.google.com/sumsung-galaxy2-2.png');

INSERT INTO IMAGE (IMAGE_ID, PRODUCT_ID, URL)
VALUES (12,6,'www.google.com/sumsung-galaxy2-3.png');
