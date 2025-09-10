# Building Occupancy Web Application

![occupancy](https://i.ibb.co/0Vy7TQ7m/occupancy.jpg)

## Overview
This project is a web application for smart building management and attendance tracking. It provides a visual map of building occupancy, allowing users to add or remove people, assign them to different rooms, and monitor overall occupancy in real time.

---

## Features
- *Interactive Occupancy Map:* Visual representation of building rooms and their current occupancy.
- *Add/Remove People:* Easily manage the list of people present in the building.
- *Room Assignment:* Assign individuals to specific rooms.
- *Live Monitoring:* View and track occupancy changes instantly.
- *Persistent Data:* All occupancy data is stored on the server and remains consistent after page refreshes.

---

## Technology Stack
### Frontend
- Modern JavaScript framework (React, Angular, or Vue recommended)
- Responsive UI for map and controls

### Backend
- *Java* with *Spring Boot*
- *Maven* for project management
- *SQL* database for persistent storage

---

## How It Works
1. *User Interface:* Users interact with a web-based map to view and manage room occupancy.
2. *API Communication:* The frontend communicates with the backend via RESTful APIs.
3. *Data Management:* The backend handles all data operations, ensuring consistency and reliability.
4. *Persistence:* Occupancy data is stored in a SQL database, so information is retained even after refreshing the page or restarting the server.

---

## Use Cases
- *Attendance Tracking:* Monitor who is present in the building at any time.
- *Smart Building Management:* Optimize space usage and improve safety by tracking occupancy.

---

## Getting Started
1. *Clone the repository*
2. *Set up the backend*
   - Install Java, Maven, and a SQL database (e.g., MySQL, PostgreSQL)
   - Configure database connection in the backend project
   - Run mvn spring-boot:run to start the backend server
3. *Set up the frontend*
   - Install dependencies (npm install or equivalent)
   - Start the frontend development server (npm start or equivalent)
4. *Access the application*
   - Open your browser and navigate to the frontend URL

---

## License
This project is open source and available under the MIT License.