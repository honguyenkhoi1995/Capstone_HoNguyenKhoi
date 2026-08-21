# CRM Project - NetBeans 13 / Tomcat 9 / JSP Servlet / MySQL

Project is intentionally Maven-free and uses `javax.servlet.*` for Tomcat 9.

## Modules implemented from the supplied CRM analysis
- Authentication for ADMIN / LEADER / MEMBER
- Role management
- User management
- Project management
- Project member management
- Task management
- Member task progress update
- Personal/project progress statistics
- Personal account management

## Database
1. Create a MySQL database.
2. Run `database/crm.sql`.
3. Edit `src/java/utils/DBConnection.java` with your MySQL username/password.
4. Add MySQL Connector/J to NetBeans:
   Project > Properties > Libraries > Compile > Add JAR/Folder.

Demo accounts created by the SQL:
- admin@gmail.com / 123
- leader@gmail.com / 123
- member@gmail.com / 123

For a classroom assignment these are plaintext demo passwords. Production applications should hash passwords.

## Run
Right click project > Run.
Open:
http://localhost:8080/CRMProject/
