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