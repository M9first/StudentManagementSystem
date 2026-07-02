
╔══════════════════════════════════════════════════════════════════════════════════╗
║           STUDENT MANAGEMENT SYSTEM — COMPLETE POSTMAN API GUIDE               ║
║                    SVC Engineering College - NEC                                ║
╚══════════════════════════════════════════════════════════════════════════════════╝

BASE URL  :  http://localhost:8080
API PREFIX:  /api/v1/students
SWAGGER UI:  http://localhost:8080/swagger-ui.html


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  STEP 0 — PRE-REQUISITES (DO THIS FIRST)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  1. Make sure MySQL is running and "student_db" database exists.
     → Run in MySQL Workbench / CMD:
         CREATE DATABASE student_db;

  2. Update application.properties with your MySQL credentials:
     → File path: src/main/resources/application.properties
         spring.datasource.username=root
         spring.datasource.password=YOUR_PASSWORD

  3. Start the Spring Boot application in STS-4:
     → Right-click StudentManagementApplication.java
     → Run As → Spring Boot App
     → Wait for: "Started StudentManagementApplication on port 8080"

  4. Open Postman application on your PC.

  5. In Postman, make sure:
     → No proxy is blocking localhost
     → Content-Type header is set for POST/PUT requests (shown below)


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  STEP 1 — HOW TO SET UP POSTMAN (ONE TIME SETUP)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  A) Create a Collection:
     → Click "Collections" on the left sidebar
     → Click "+" (New Collection)
     → Name it: "Student Management System"
     → Click "Create"

  B) Create a Base URL Variable (optional but recommended):
     → Click on the collection name
     → Go to "Variables" tab
     → Add variable:
         Variable Name  : baseUrl
         Initial Value  : http://localhost:8080/api/v1/students
     → Save with Ctrl+S

  C) For every POST / PUT request, set the Header:
     → Go to "Headers" tab in your request
     → Add:
         KEY           : Content-Type
         VALUE         : application/json


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 1 — ADD A NEW STUDENT  (POST)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : POST
  URL     : http://localhost:8080/api/v1/students

  STEPS IN POSTMAN:
  -----------------
  1. Click "New Request" → select POST
  2. Paste the URL above
  3. Click "Headers" tab → Add:
       Key   : Content-Type
       Value : application/json
  4. Click "Body" tab
  5. Select "raw" radio button
  6. Select "JSON" from the dropdown (right side)
  7. Paste the following JSON in the text area:

  REQUEST BODY (JSON):
  --------------------
  {
    "name": "Rahul Kumar",
    "email": "rahul.kumar@gmail.com",
    "department": "Computer Science and Engineering",
    "semester": 5,
    "cgpa": 8.75,
    "phoneNumber": "9876543210",
    "address": "Chennai, Tamil Nadu"
  }

  8. Click the blue "Send" button

  EXPECTED RESPONSE (201 Created):
  ---------------------------------
  {
    "id": 1,
    "name": "Rahul Kumar",
    "email": "rahul.kumar@gmail.com",
    "department": "Computer Science and Engineering",
    "semester": 5,
    "cgpa": 8.75,
    "phoneNumber": "9876543210",
    "address": "Chennai, Tamil Nadu"
  }

  NOTE: Add more students by changing the name, email, etc. in the body.
        Email must be UNIQUE for each student.

  SAMPLE STUDENTS TO ADD (copy one at a time and send):

  --- Student 2 ---
  {
    "name": "Priya Sharma",
    "email": "priya.sharma@gmail.com",
    "department": "Electronics and Communication",
    "semester": 3,
    "cgpa": 9.10,
    "phoneNumber": "8765432109",
    "address": "Bangalore, Karnataka"
  }

  --- Student 3 ---
  {
    "name": "Mohammed Ali",
    "email": "mohammed.ali@gmail.com",
    "department": "Mechanical Engineering",
    "semester": 7,
    "cgpa": 7.50,
    "phoneNumber": "7654321098",
    "address": "Hyderabad, Telangana"
  }

  --- Student 4 ---
  {
    "name": "Ananya Reddy",
    "email": "ananya.reddy@gmail.com",
    "department": "Computer Science and Engineering",
    "semester": 5,
    "cgpa": 9.50,
    "phoneNumber": "9123456780",
    "address": "Vizag, Andhra Pradesh"
  }

  --- Student 5 ---
  {
    "name": "Karthik Raja",
    "email": "karthik.raja@gmail.com",
    "department": "Civil Engineering",
    "semester": 2,
    "cgpa": 6.80,
    "phoneNumber": "6234567890",
    "address": "Coimbatore, Tamil Nadu"
  }


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 2 — GET STUDENT BY ID  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/1
            (Replace "1" with any student ID you want)

  STEPS IN POSTMAN:
  -----------------
  1. Click "New Request" → select GET
  2. Paste the URL (change the ID number at the end)
  3. No Body needed for GET
  4. Click "Send"

  EXPECTED RESPONSE (200 OK):
  ----------------------------
  {
    "id": 1,
    "name": "Rahul Kumar",
    "email": "rahul.kumar@gmail.com",
    "department": "Computer Science and Engineering",
    "semester": 5,
    "cgpa": 8.75,
    "phoneNumber": "9876543210",
    "address": "Chennai, Tamil Nadu"
  }

  WHEN ID DOES NOT EXIST — Response (404 Not Found):
  ---------------------------------------------------
  {
    "status": 404,
    "error": "Not Found",
    "message": "Student not found with ID: 99",
    "path": "/api/v1/students/99",
    "timestamp": "2026-06-30T08:15:00",
    "details": null
  }


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 3 — GET ALL STUDENTS WITH PAGINATION & SORTING  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students

  STEPS IN POSTMAN:
  -----------------
  1. Click "New Request" → select GET
  2. Paste the URL
  3. Click "Params" tab
  4. Add the following KEY-VALUE pairs:

     KEY          VALUE       DESCRIPTION
     ─────────    ─────────   ────────────────────────────────────────
     page         0           Page number (starts from 0)
     size         10          Number of records per page
     sortBy       name        Sort by field: id, name, cgpa, semester
     direction    asc         Sort direction: asc or desc

  5. Click "Send"

  EXAMPLES:
  ---------
  → Get page 1, 5 records, sorted by CGPA descending:
    http://localhost:8080/api/v1/students?page=0&size=5&sortBy=cgpa&direction=desc

  → Get all students sorted by name A-Z:
    http://localhost:8080/api/v1/students?sortBy=name&direction=asc

  → Get second page with 3 records per page:
    http://localhost:8080/api/v1/students?page=1&size=3

  EXPECTED RESPONSE (200 OK):
  ----------------------------
  {
    "content": [
      {
        "id": 4,
        "name": "Ananya Reddy",
        "cgpa": 9.50,
        ...
      },
      {
        "id": 2,
        "name": "Priya Sharma",
        "cgpa": 9.10,
        ...
      }
    ],
    "pageable": { ... },
    "totalElements": 5,
    "totalPages": 1,
    "last": true,
    "first": true,
    "size": 10,
    "number": 0
  }


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 4 — UPDATE STUDENT  (PUT)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : PUT
  URL     : http://localhost:8080/api/v1/students/1
            (Replace "1" with the ID of the student you want to update)

  STEPS IN POSTMAN:
  -----------------
  1. Click "New Request" → select PUT
  2. Paste the URL (with the correct student ID)
  3. Click "Headers" tab → Add:
       Key   : Content-Type
       Value : application/json
  4. Click "Body" tab → raw → JSON
  5. Paste the full updated student JSON:

  REQUEST BODY (JSON):
  --------------------
  {
    "name": "Rahul Kumar",
    "email": "rahul.kumar@gmail.com",
    "department": "Information Technology",
    "semester": 6,
    "cgpa": 9.00,
    "phoneNumber": "9876543210",
    "address": "Mumbai, Maharashtra"
  }

  NOTE: You MUST send ALL fields even if only one field changed.
        This is a full update (PUT), not a partial update (PATCH).

  6. Click "Send"

  EXPECTED RESPONSE (200 OK):
  ----------------------------
  {
    "id": 1,
    "name": "Rahul Kumar",
    "email": "rahul.kumar@gmail.com",
    "department": "Information Technology",
    "semester": 6,
    "cgpa": 9.00,
    "phoneNumber": "9876543210",
    "address": "Mumbai, Maharashtra"
  }


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 5 — DELETE STUDENT  (DELETE)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : DELETE
  URL     : http://localhost:8080/api/v1/students/1
            (Replace "1" with the ID of the student to delete)

  STEPS IN POSTMAN:
  -----------------
  1. Click "New Request" → select DELETE
  2. Paste the URL with the student ID
  3. No Body or Headers needed
  4. Click "Send"

  EXPECTED RESPONSE (204 No Content):
  -------------------------------------
  → Response body will be EMPTY
  → Status code: 204 No Content
  → This means deletion was successful

  WHEN ID DOES NOT EXIST — Response (404 Not Found):
  ---------------------------------------------------
  {
    "status": 404,
    "error": "Not Found",
    "message": "Student not found with ID: 99"
  }


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 6 — SEARCH BY NAME  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/search/name

  STEPS IN POSTMAN:
  -----------------
  1. Select GET → paste URL
  2. Click "Params" tab and add:

     KEY          VALUE
     ─────────    ─────────────────
     name         Rahul
     page         0
     size         10
     sortBy       name
     direction    asc

  3. Click "Send"

  FULL URL EXAMPLE:
  http://localhost:8080/api/v1/students/search/name?name=Rahul&page=0&size=10

  NOTE: Search is case-insensitive and partial match.
        Searching "ra" will return "Rahul", "Priya", etc.


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 7 — SEARCH BY DEPARTMENT  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/search/department

  PARAMS IN POSTMAN:
  ------------------
     KEY          VALUE
     ─────────    ────────────────────────────────
     department   Computer Science
     page         0
     size         10
     sortBy       name
     direction    asc

  FULL URL EXAMPLE:
  http://localhost:8080/api/v1/students/search/department?department=Computer+Science

  NOTE: Partial match. "CSE" or "Computer" both will find
        "Computer Science and Engineering" students.


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 8 — SEARCH BY SEMESTER  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/search/semester

  PARAMS IN POSTMAN:
  ------------------
     KEY          VALUE
     ─────────    ─────
     semester     5
     page         0
     size         10
     sortBy       name
     direction    asc

  FULL URL EXAMPLE:
  http://localhost:8080/api/v1/students/search/semester?semester=5

  NOTE: Valid semester values: 1 to 8


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 9 — SEARCH BY MINIMUM CGPA  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/search/cgpa

  PARAMS IN POSTMAN:
  ------------------
     KEY          VALUE
     ─────────    ─────
     minCgpa      8.0
     page         0
     size         10
     sortBy       cgpa
     direction    desc

  FULL URL EXAMPLE:
  http://localhost:8080/api/v1/students/search/cgpa?minCgpa=8.0&sortBy=cgpa&direction=desc

  NOTE: Returns all students with CGPA >= minCgpa.
        Sorted desc so highest CGPA appears first.


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 10 — SEARCH BY CGPA RANGE  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/search/cgpa-range

  PARAMS IN POSTMAN:
  ------------------
     KEY          VALUE
     ─────────    ─────
     minCgpa      7.0
     maxCgpa      9.0
     page         0
     size         10
     sortBy       cgpa
     direction    desc

  FULL URL EXAMPLE:
  http://localhost:8080/api/v1/students/search/cgpa-range?minCgpa=7.0&maxCgpa=9.0

  NOTE: Returns students with CGPA between 7.0 and 9.0 (both inclusive).


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 11 — SEARCH BY EMAIL  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/search/email

  PARAMS IN POSTMAN:
  ------------------
     KEY          VALUE
     ─────────    ────────────────────────────
     email        rahul.kumar@gmail.com

  FULL URL EXAMPLE:
  http://localhost:8080/api/v1/students/search/email?email=rahul.kumar@gmail.com

  NOTE: This is an EXACT match search. The full email must be provided.
        Returns a single student object (not a page).


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  API 12 — FULL-TEXT KEYWORD SEARCH  (GET)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  METHOD  : GET
  URL     : http://localhost:8080/api/v1/students/search/keyword

  PARAMS IN POSTMAN:
  ------------------
     KEY          VALUE
     ─────────    ───────────────
     keyword      computer
     page         0
     size         10
     sortBy       name
     direction    asc

  FULL URL EXAMPLE:
  http://localhost:8080/api/v1/students/search/keyword?keyword=computer

  NOTE: Searches across NAME, EMAIL, and DEPARTMENT fields simultaneously.
        Very useful for a global search bar.
        Example: keyword=gmail  → finds all students with gmail emails
        Example: keyword=kumar  → finds students with kumar in name/email


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  VALIDATION ERRORS — WHAT HAPPENS WITH WRONG INPUT?
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  EXAMPLE: Sending empty body → POST /api/v1/students

  RESPONSE (400 Bad Request):
  ---------------------------
  {
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed. Please check the request fields.",
    "path": "/api/v1/students",
    "timestamp": "2026-06-30T08:15:00",
    "details": [
      "Name is required",
      "Email is required",
      "Department is required",
      "Semester is required",
      "CGPA is required",
      "Phone number is required"
    ]
  }

  FIELD VALIDATION RULES:
  -----------------------
  Field         | Rule
  ─────────     | ──────────────────────────────────────────────────────────
  name          | Cannot be blank. Min 2 chars, Max 100 chars.
  email         | Cannot be blank. Must be valid email format. Must be unique.
  department    | Cannot be blank.
  semester      | Cannot be null. Must be between 1 and 8.
  cgpa          | Cannot be null. Must be between 0.0 and 10.0.
  phoneNumber   | 10-digit Indian mobile number starting with 6, 7, 8, or 9.
  address       | Optional (can be empty or null).


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  QUICK REFERENCE — ALL API ENDPOINTS
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  #   METHOD   URL                                          DESCRIPTION
  ─── ──────   ──────────────────────────────────────────   ─────────────────────
  1   POST     /api/v1/students                             Add new student
  2   GET      /api/v1/students/{id}                        Get by ID
  3   GET      /api/v1/students                             Get all (paginated)
  4   PUT      /api/v1/students/{id}                        Update student
  5   DELETE   /api/v1/students/{id}                        Delete student
  6   GET      /api/v1/students/search/name?name=           Search by name
  7   GET      /api/v1/students/search/department?dept=     Search by department
  8   GET      /api/v1/students/search/semester?semester=   Search by semester
  9   GET      /api/v1/students/search/cgpa?minCgpa=        Search by min CGPA
  10  GET      /api/v1/students/search/cgpa-range?min&max   Search by CGPA range
  11  GET      /api/v1/students/search/email?email=         Search by email
  12  GET      /api/v1/students/search/keyword?keyword=     Full-text search


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  COMMON PAGINATION PARAMS (for all list/search endpoints)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  PARAM       DEFAULT   DESCRIPTION
  ──────────  ───────   ──────────────────────────────────────────────────────
  page        0         Page number. Starts from 0 (0 = first page)
  size        10        Number of records per page
  sortBy      id        Field to sort by (id, name, cgpa, semester, email...)
  direction   asc       Sort direction: "asc" (A→Z, 0→9) or "desc" (Z→A, 9→0)


━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  TIPS & TROUBLESHOOTING
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  PROBLEM                          SOLUTION
  ───────────────────────────────  ────────────────────────────────────────────
  "Connection Refused" error       Spring Boot is not running. Start it in STS-4
  "Access Denied" for MySQL        Wrong username/password in application.properties
  "Table not found"                Run the app once with ddl-auto=update (auto creates)
  "Email already registered"       Use a different unique email for each student
  "400 Bad Request"                Check Body → raw → JSON and validate all fields
  "404 Not Found" for an ID        That ID doesn't exist. Check with GET all students
  Postman shows "Could not send"   Check if localhost:8080 is correct, app is running
  Swagger not loading              Try: http://localhost:8080/swagger-ui/index.html

  USEFUL SWAGGER URL:
  → http://localhost:8080/swagger-ui.html
    (All APIs can also be tested directly from the browser without Postman!)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                       END OF POSTMAN GUIDE
                  SVC Engineering College - NEC
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
