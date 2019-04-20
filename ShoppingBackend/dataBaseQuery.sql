CREATE TABLE category(
id number(10),
name varchar2(20),
description varchar2(30),
image_url varchar2(100),
isActive number(5),
  CONSTRAINT pk_category_id primary key(id)
);