# insert category date
insert into category value ('CLOTHING');
insert into category value ('SHOES');
insert into category value ('ACCESSORY');

# insert color date
insert into color value ('BLACK');
insert into color value ('WHITE');
insert into color value ('PINK');
insert into color value ('RED');
insert into color value ('BLUE');
insert into color value ('GREY');
insert into color value ('GREEN');
insert into color value ('YELLOW');
insert into color value ('ORANGE');
insert into color value ('BROWN');

#inserd type for all Categories
insert into type values ('SPORT');

# insert type date for CLOTHING
insert into type value ('BODYSUITS');
insert into type value ('DRESSES');
insert into type value ('HOODIES');
insert into type value ('SWEATS');
insert into type value ('JACKETS');
insert into type value ('COATS');
insert into type value ('JEANS');
insert into type value ('PANTS');
insert into type value ('LEGGINGS');
insert into type value ('ROMPERS');
insert into type value ('JUMPSUITS');
insert into type value ('SHIRTS');
insert into type value ('BLOUSES');
insert into type value ('SWEATERS');
insert into type value ('KNITS');

# insert type date for SHOES
insert into type value ('CLASSICAL');

# insert category types date
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'BODYSUITS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'DRESSES');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'HOODIES');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'SWEATS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'JACKETS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'COATS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'JEANS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'PANTS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'LEGGINGS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'ROMPERS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'JUMPSUITS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'SHIRTS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'BLOUSES');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'SWEATERS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'KNITS');
insert into category_type (category_name, type_name) VALUES ('CLOTHING', 'SPORT');

insert into category_type (category_name, type_name) VALUES ('SHOES','CLASSICAL');
insert into category_type (category_name, type_name) VALUES ('SHOES', 'SPORT');

