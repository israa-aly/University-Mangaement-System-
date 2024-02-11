## University Management System (JavaFX Client-Server Application)
# Overview
This project is a JavaFX client-server application for managing university data. 
It allows admins of faculties to perform various operations such as adding students, professors, departments, courses, and more.
The server side of the application is built using Java, while the client side utilizes JavaFX for the graphical interface. 
The data is stored and managed using an Oracle database.
# Features
- Admin Authentication: Users can log in with their username and password to access the system.
- Adding Students and Professors: Administrators can add new students and professors to the system.
- Managing Departments: Administrators can add, update, and delete departments.
- Managing Courses: Courses can be added, updated, and deleted by administrators.
- Registration for Courses: Students can register for courses offered by the Faculty.
- Viewing Data: Users can view information such as student details, professor details, course details, etc.
- Data Validation: The application validates input data to ensure accuracy and consistency.
- 
## Installation
1) Database Setup:
Install Oracle database and create a new schema for the university management system.
Execute the provided SQL scripts to create the necessary tables and populate initial data.

2) Server Setup:
Import the server-side Java project into your IDE.
Configure the database connection parameters in the DataAccessLayer class.
Run the UniversityServer class to start the server.

4) Client Setup:
Import the client-side JavaFX project into your IDE.
Configure the server IP address and port number in the client application.
Run the client application to start the user interface.
## Future Enhancements:
🔸 Student Portal: Empowering students to log in and access their personalized accounts, view enrolled courses, and track grades and academic progress.
🔸 Professor Dashboard: Providing professors with dedicated login credentials to manage courses, update grades, and communicate with students effectively.
