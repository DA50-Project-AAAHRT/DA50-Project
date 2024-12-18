CREATE DATABASE da50;
CREATE USER 'hibernate_user'@'%' IDENTIFIED BY 'root'; -- Remplace 'localhost' par '%'
GRANT ALL PRIVILEGES ON da50.* TO 'hibernate_user'@'%'; -- Remplace 'localhost' par '%'
FLUSH PRIVILEGES;