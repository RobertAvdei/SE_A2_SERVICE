###SE_A2_SERVICE
###Backend & Frontend Project for Reading Habits Tracking

###🚀 Getting Started
###Backend Setup (Spring Boot)
Sync the project by right-clicking on pom.xml (if using an IDE like IntelliJ/Eclipse).

Ensure Java 17+ and Maven are installed.

Run the application via:
'''bash'''
mvn spring-boot:run
The backend will start at http://localhost:8080.

###Frontend Setup (React)
Install dependencies:
'''bash'''
npm install

Start the development server:
npm run dev
Access the app at http://localhost:5173.

###✅ Implemented Requirements
Requirement	Description	Location
1	Add a user to the database	UserController (POST endpoint)
2	Fetch all reading habits for a user	ReadingHabitController (GET endpoint)
3	Update a book’s title	ReadingHabitController (PUT endpoint)
4	Delete a record from ReadingHabit	ReadingHabitController (DELETE endpoint)
5	Calculate mean age of users	UserController (Line 40)
6	Count users who read a specific book	ReadingHabitController (Custom query)
7	Total pages read by all users	ReadingHabitController (Line 65)
8	Count users who read >1 book	UserController (Line 50)
9	Added Name (TEXT) column to User table	Database migration file or entity class

###📂 Project Structure
Backend: Spring Boot (Java)

Controllers: UserController, ReadingHabitController

Models: User, ReadingHabit

Repository: 

Frontend: React 

Components: Organized by feature (UserForm, HabitList)

###🔧 Troubleshooting
Dependency issues: Run mvn clean install (backend) or npm install --force (frontend).

CORS errors: Ensure backend allows requests from http://localhost:5173.

###📝 Notes



