# SE_A2_SERVICE
Backend & Frontend Project for Reading Habits Tracking Assignmnet 2

## Features
1. Provide the ability to add a user to the database
2. Provide all the reading habit data for a certain user
3. Provide the ability to change the title of a book in the database
4. Provide the ability to delete a record/row from the ReadingHabit table
5. Provide the mean age of the users
6. Provide the total number of users that have read pages from a specific book
7. Provide the total number of pages read by all users
8. Provide the total number of users that have read more than one book
9. Add a column to the User table named "Name" which contains TEXT data

## 🚀 Getting Started
### Backend Setup (Spring Boot)
1. **Sync the project** by right-clicking on `pom.xml` (if using an IDE like IntelliJ/Eclipse)
2. **Ensure you have:**
    - Java 17+
    - Maven 3.8+
3. **Run the application:**
   ```bash
   mvn spring-boot:run
The backend will start at http://localhost:8080.

### Frontend Setup (React)
Install dependencies:
'''bash
npm install
'''

Start the development server:
''' bash
npm run dev
'''

Access the app at http://localhost:5173.

## ✅ Implemented Requirements
Requirement	Description	Location
1	Add a user to the database	UserController (POST endpoint) ///////
2	Fetch all reading habits for a user	ReadingHabitController (GET endpoint) ///////
3	Update a book’s title	ReadingHabitController (PUT endpoint) ///////
4	Delete a record from ReadingHabit	ReadingHabitController (DELETE endpoint) /////
5	Calculate mean age of users	UserController (Line 40)
6	Count users who read a specific book	ReadingHabitController (Custom query) //////
7	Total pages read by all users	ReadingHabitController (Line 65)
8	Count users who read >1 book	UserController (Line 50)
9	Added Name (TEXT) column to User table	Database migration file or entity class /////

## 📂 Project Structure
Backend: Spring Boot (Java)

Controllers: UserController, ReadingHabitController

Models: User, ReadingHabit

Repository: 

Frontend: React 

Components: Organized by feature (UserForm, HabitList)

## 🔧 Troubleshooting
Dependency issues: Run mvn clean install (backend) or npm install --force (frontend).

CORS errors: Ensure backend allows requests from http://localhost:5173.

## 📝 Notes
This project was developed for SE Assignment 2
## Extra
A low fidelity picture of our design steps;
![img.png](img.png)
![img_1.png](img_1.png)


