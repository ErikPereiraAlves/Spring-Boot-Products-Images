
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS IMAGE;


CREATE TABLE PRODUCT
(
	PRODUCT_ID IDENTITY PRIMARY KEY,
	PRODUCT_PARENT_ID NUMBER(19) NULL,
	PRODUCT_NAME VARCHAR2(50) NOT NULL,
	PRODUCT_DESC VARCHAR2(200) NOT NULL,
	PRODUCT_PRICE NUMBER(19) NOT NULL,
	PRODUCT_CREATED_TS TIMESTAMP NOT NULL,
	PRODUCT_UPDATED_TS TIMESTAMP NOT NULL,
);


CREATE TABLE IMAGE
(
	IMAGE_ID IDENTITY PRIMARY KEY,
	PRODUCT_ID NUMBER(19) NOT NULL,
	URL VARCHAR2(200) NOT NULL,
	CONSTRAINT FK_IMG_PRDCT_ID FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID)
);
