# DA50-Project
Student project - Code a website and an app in Java

Install MySql localy, the 8.4.3 LTS version  : https://dev.mysql.com/downloads/mysql/

Then configure it, without adding user. 

Open the ```MySQL 8.4 Command Line Client``` command prompt.

Put your password.

Then apply this different commands, in order to create the table and an user.

```
CREATE DATABASE da50;
CREATE USER 'hibernate_user'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON da50.* TO 'hibernate_user'@'localhost';
FLUSH PRIVILEGES;
```

In order to see the different table :

```
USE da50;
SHOW TABLES;
SELECT * FROM tableName;
```

We can do any SQL query 
