# DA50-Project

Student project - Code a website and an app in Java

## Installation

This application requires **MySQL 8.4.3** to be installed locally.

**Steps:**

1.  **Install MySQL 8.4.3 LTS:** Download and install the MySQL 8.4.3 LTS version from: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
    *   **Important:** During the installation, you will be asked to set a **root password**. Remember this password, as you will need it for the next step.

2.  **Run the installation script:**
    *   Open a command prompt or terminal.
    *   Navigate to the root directory of the project (where the `pom.xml` and `install.bat` files are located).
    *   Execute the command: `install.bat`
    *   The script will perform the following actions:
        *   Prompt you to enter the path to your MySQL 8.4 installation directory (e.g., `C:\Program Files\MySQL\MySQL Server 8.4`).
        *   Prompt you to enter the MySQL root password.
        *   Create the `da50` database if it doesn't exist.
        *   Create the `hibernate_user` user with the password 'root' if it doesn't exist, and grant it all privileges on the `da50` database.
        *   Ask if you want to create an admin account. If you answer "y" (yes), it will run a Java program (`CreateAdminUser`) to guide you through the process.

3.  **Build and run the application:**
    *   After the database is initialized and the admin account is created (optional), you can build and run the application using Maven.

## Database Information

*   **Database Name:** `da50`
*   **Username:** `hibernate_user`
*   **Password:** `root`

**Note:** The `hibernate_user` is created with limited privileges for security reasons. The admin account (if created) can be used for administrative tasks.

## Manual Database Setup (Alternative)**

If you prefer to set up the database manually, you can use the following SQL commands after installing MySQL and opening the `MySQL 8.4 Command Line Client`:

```sql
CREATE DATABASE IF NOT EXISTS da50;
CREATE USER IF NOT EXISTS 'hibernate_user'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON da50.* TO 'hibernate_user'@'localhost';
FLUSH PRIVILEGES;