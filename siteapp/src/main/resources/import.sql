delete from AUTHORITY;
delete from USERS;
delete from SITES;
delete from CATEGORY;

INSERT INTO AUTHORITY (ID, NAME) VALUES (nextval('authority_id_seq'), 'ROLE_ADMIN');
INSERT INTO AUTHORITY (ID, NAME) VALUES (nextval('authority_id_seq'), 'ROLE_USER'); 

INSERT INTO category VALUES (nextval('category_id_seq'), 'PETROGLIFO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'CASTILLO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'CASTRO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'FERVENZA');
INSERT INTO category VALUES (nextval('category_id_seq'), 'MOLINO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'PLAYA FLUVIAL');
INSERT INTO category VALUES (nextval('category_id_seq'), 'TRADICIONAL');
INSERT INTO category VALUES (nextval('category_id_seq'), 'CABO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'PLAYA');
INSERT INTO category VALUES (nextval('category_id_seq'), 'CRUCEIRO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'MIRADOR');
INSERT INTO category VALUES (nextval('category_id_seq'), 'PENEDO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'PUENTE');
INSERT INTO category VALUES (nextval('category_id_seq'), 'YACIMIENTO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'DÓLMEN');
INSERT INTO category VALUES (nextval('category_id_seq'), 'MONASTERIO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'IGLESIA');
INSERT INTO category VALUES (nextval('category_id_seq'), 'HÓRREO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'RUINA');
INSERT INTO category VALUES (nextval('category_id_seq'), 'PAZO');
INSERT INTO category VALUES (nextval('category_id_seq'), 'SENDA');
INSERT INTO category VALUES (nextval('category_id_seq'), 'EMBALSE');

INSERT INTO site_details VALUES (nextval('sitedetails_id_seq'), 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.', 'Quisque pulvinar tortor sem, ac bibendum est eleifend eget. Aliquam vel ligula id augue iaculis malesuada non eu arcu. Suspendisse non tellus et risus vulputate iaculis. Cras eros erat, auctor sit amet tellus a, laoreet ultricies neque. Integer eget odio lobortis, accumsan nibh non, accumsan magna. Mauris ac lacus at libero consectetur sodales. Sed id pulvinar magna. Aliquam erat volutpat. Praesent luctus rhoncus eleifend.', 'Quisque porta, sem a dignissim eleifend, magna massa pretium nunc, sed aliquet augue nulla quis ipsum. Pellentesque efficitur sed nulla ac rhoncus. Suspendisse non neque ligula. Vestibulum libero arcu, cursus sed pulvinar facilisis, porta viverra sapien. Duis nec tortor metus. Nunc lacinia venenatis eros, vitae aliquet nisi venenatis vitae. Curabitur consectetur orci tortor, eget pretium leo aliquet a. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
INSERT INTO site_details VALUES (nextval('sitedetails_id_seq'), 'Cras vel lectus convallis, molestie leo nec, interdum tellus. Donec maximus pharetra leo, ac dignissim nulla pellentesque vulputate. Nam ut ultrices ligula. Aenean eget posuere neque, vitae imperdiet dolor. Ut interdum et ipsum ut placerat. Mauris venenatis in dolor sit amet condimentum. Quisque ac quam pretium, auctor ex et, posuere nibh.', 'Quisque pulvinar tortor sem, ac bibendum est eleifend eget. Aliquam vel ligula id augue iaculis malesuada non eu arcu. Suspendisse non tellus et risus vulputate iaculis. Cras eros erat, auctor sit amet tellus a, laoreet ultricies neque. Integer eget odio lobortis, accumsan nibh non, accumsan magna. Mauris ac lacus at libero consectetur sodales. Sed id pulvinar magna. Aliquam erat volutpat. Praesent luctus rhoncus eleifend.', 'Quisque porta, sem a dignissim eleifend, magna massa pretium nunc, sed aliquet augue nulla quis ipsum. Pellentesque efficitur sed nulla ac rhoncus. Suspendisse non neque ligula. Vestibulum libero arcu, cursus sed pulvinar facilisis, porta viverra sapien. Duis nec tortor metus. Nunc lacinia venenatis eros, vitae aliquet nisi venenatis vitae. Curabitur consectetur orci tortor, eget pretium leo aliquet a. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
INSERT INTO site_details VALUES (nextval('sitedetails_id_seq'), 'Cras vel lectus convallis, molestie leo nec, interdum tellus. Donec maximus pharetra leo, ac dignissim nulla pellentesque vulputate. Nam ut ultrices ligula. Aenean eget posuere neque, vitae imperdiet dolor. Ut interdum et ipsum ut placerat. Mauris venenatis in dolor sit amet condimentum. Quisque ac quam pretium, auctor ex et, posuere nibh.', 'Quisque pulvinar tortor sem, ac bibendum est eleifend eget. Aliquam vel ligula id augue iaculis malesuada non eu arcu. Suspendisse non tellus et risus vulputate iaculis. Cras eros erat, auctor sit amet tellus a, laoreet ultricies neque. Integer eget odio lobortis, accumsan nibh non, accumsan magna. Mauris ac lacus at libero consectetur sodales. Sed id pulvinar magna. Aliquam erat volutpat. Praesent luctus rhoncus eleifend.', 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.');

INSERT INTO USERS (ID, CREATED_AT, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE, AUTHORITY_ID) VALUES (nextval('user_id_seq'), now(), 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', true, now(), 1);
INSERT INTO USERS (ID, CREATED_AT, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE, AUTHORITY_ID) VALUES (nextval('user_id_seq'), now(), 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', true, now(), 2);
INSERT INTO USERS (ID, CREATED_AT, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE, AUTHORITY_ID) VALUES (nextval('user_id_seq'), now(), 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', false, now(), 2);

INSERT INTO SITES (ID, CREATED_AT, ENABLED, NAME, PROVINCE, TOWN_HALL, DESCRIPTION, CATEGORY_ID, SITEDETAILS_ID) VALUES (nextval('site_id_seq'), now(), true, 'Sitio A', 'Provincia A', 'Ayuntamiento A', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.', 1, 1);
INSERT INTO SITES (ID, CREATED_AT, ENABLED, NAME, PROVINCE, TOWN_HALL, DESCRIPTION, CATEGORY_ID, SITEDETAILS_ID) VALUES (nextval('site_id_seq'), now(), true, 'Sitio B', 'Provincia B', 'Ayuntamiento B', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.', 3, 1);
INSERT INTO SITES (ID, CREATED_AT, ENABLED, NAME, PROVINCE, TOWN_HALL, DESCRIPTION, CATEGORY_ID, SITEDETAILS_ID) VALUES (nextval('site_id_seq'), now(), true, 'Sitio C', 'Provincia C', 'Ayuntamiento C', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.', 2, 1);
INSERT INTO SITES (ID, CREATED_AT, ENABLED, NAME, PROVINCE, TOWN_HALL, DESCRIPTION, CATEGORY_ID, SITEDETAILS_ID) VALUES (nextval('site_id_seq'), now(), true, 'Sitio D', 'Provincia D', 'Ayuntamiento D', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.', 2, 1);
INSERT INTO SITES (ID, CREATED_AT, ENABLED, NAME, PROVINCE, TOWN_HALL, DESCRIPTION, CATEGORY_ID, SITEDETAILS_ID) VALUES (nextval('site_id_seq'), now(), true, 'Sitio E', 'Provincia E', 'Ayuntamiento E', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent gravida arcu a tortor mattis venenatis. Nunc gravida varius est et faucibus. Sed ullamcorper vehicula dui.', 1, 1);





