delete from AUTHORITY;
delete from USERS;
delete from SITES;
delete from CATEGORY;

INSERT INTO AUTHORITY (ID, NAME) VALUES (nextval('authority_id_seq'), 'ROLE_ADMIN');
INSERT INTO AUTHORITY (ID, NAME) VALUES (nextval('authority_id_seq'), 'ROLE_USER'); 

INSERT INTO CATEGORY (ID, NAME) VALUES (nextval('category_id_seq'), 'CAT_A');
INSERT INTO CATEGORY (ID, NAME) VALUES (nextval('category_id_seq'), 'CAT_B');
INSERT INTO CATEGORY (ID, NAME) VALUES (nextval('category_id_seq'), 'CAT_C');
INSERT INTO CATEGORY (ID, NAME) VALUES (nextval('category_id_seq'), 'CAT_D');

INSERT INTO USERS (ID, CREATED_AT, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE, AUTHORITY_ID) VALUES (nextval('user_id_seq'), now(), 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', true, now(), 1);
INSERT INTO USERS (ID, CREATED_AT, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE, AUTHORITY_ID) VALUES (nextval('user_id_seq'), now(), 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', true, now(), 2);
INSERT INTO USERS (ID, CREATED_AT, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE, AUTHORITY_ID) VALUES (nextval('user_id_seq'), now(), 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', false, now(), 2);

INSERT INTO SITES (ID, CREATED_AT, ENABLED, LATITUDE, LONGITUDE, NAME, PROVINCE, TOWN_HALL, DESCRIPTION) VALUES (nextval('site_id_seq'), now(), true, 1.0, 1.0, 'Sitio A', 'Provincia A', 'Ayuntamiento A', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.');
INSERT INTO SITES (ID, CREATED_AT, ENABLED, LATITUDE, LONGITUDE, NAME, PROVINCE, TOWN_HALL, DESCRIPTION) VALUES (nextval('site_id_seq'), now(), true, 1.0, 1.0, 'Sitio B', 'Provincia B', 'Ayuntamiento B', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.');
INSERT INTO SITES (ID, CREATED_AT, ENABLED, LATITUDE, LONGITUDE, NAME, PROVINCE, TOWN_HALL, DESCRIPTION) VALUES (nextval('site_id_seq'), now(), true, 1.0, 1.0, 'Sitio C', 'Provincia C', 'Ayuntamiento C', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.');
INSERT INTO SITES (ID, CREATED_AT, ENABLED, LATITUDE, LONGITUDE, NAME, PROVINCE, TOWN_HALL, DESCRIPTION) VALUES (nextval('site_id_seq'), now(), true, 1.0, 1.0, 'Sitio D', 'Provincia D', 'Ayuntamiento D', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.');
INSERT INTO SITES (ID, CREATED_AT, ENABLED, LATITUDE, LONGITUDE, NAME, PROVINCE, TOWN_HALL, DESCRIPTION) VALUES (nextval('site_id_seq'), now(), true, 1.0, 1.0, 'Sitio E', 'Provincia E', 'Ayuntamiento E', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.');


