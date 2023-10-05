insert into users (username, email, enabled) VALUES
    ('admin', 'to_be_encoded', true),
    ('user', 'to_be_encoded', true);


insert into authorities (username, authority) VALUES
    ('admin', 'ADMIN'),
    ('user', 'USER');