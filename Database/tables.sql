-- Users sequence
CREATE SEQUENCE IF NOT EXISTS public.s_users
    INCREMENT 1
    START 1;

ALTER SEQUENCE public.s_users
    OWNER TO hotelly_user;

CREATE TABLE IF NOT EXISTS users(
	user_id varchar(100) not null primary key,
	email varchar(100) not null unique,
	password varchar(100) not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	created_on timestamp not null default NOW(),
	edited_on timestamp
);

CREATE TABLE roles(
	role varchar(50) not null primary key
);

INSERT INTO roles (role) VALUES ('ADMIN');
INSERT INTO roles (role) VALUES ('USER');

CREATE SEQUENCE public.s_user_roles
	INCREMENT 1
	START 1;
	
ALTER SEQUENCE public.s_user_roles
	OWNER TO hotelly_user;

CREATE TABLE user_roles(
	user_role_id int not null primary key default nextval('s_user_roles'),
	user_id varchar(100) not null references users(user_id),
	role varchar(50) not null references roles(role),
	created_on timestamp not null default NOW()
);

CREATE TABLE addresses(
	address_id varchar(100) not null primary key,
	country_code varchar(5) not null,
	city varchar(400) not null,
	street varchar(300) not null,
	number int not null,
	--hotel_id varchar(100) not null references hotels(hotel_id) on cascade delete
	created_on timestamp not null default NOW()
);

CREATE TABLE hotels(
	hotel_id varchar(100) not null primary key,
	name varchar(200) not null,
	address varchar(100) not null references addresses(address_id) on update cascade on delete restrict,
	stars int not null default 0,
	main_image text,
	owner varchar(100) not null references users(user_id) on update cascade on delete cascade,
	created_on timestamp not null default NOW(),
	edited_on timestamp
);