# fullstack-assessment-tanyoungsim
Contains backend (card_ops_api &amp; place_app) and frontend (react_places) assessment projects
This repository contains two backend assessments and one frontend project using a shared MSSQL database (`TESTDB`).

## 📂 Project Structure
card_ops_api/ → Backend API for card operations
postman_collection/ → Postman collection for testing card_ops_api endpoints
place_app/ → Backend API for place app
frontend_place_app/ → React frontend for place_app (for this frontend react place is using no-key Nominatim + Leaflet as currently have not setup Google API Key)
database/TESTDB.bak → MSSQL database backup

## 🧩 Requirements
- Java 17+
- Spring Boot 3+
- Node.js 18+
- SQL Server 2022
- Postman (for testing)


## ⚙️ Setup Instructions

### 1️⃣ Restore Database
- Open SQL Server Management Studio
- Restore `TESTDB.bak` to your local instance
- Ensure DB name is **TESTDB**


### 2️⃣ Run Backend #1 (`card_ops_api`)
```bash
cd card_ops_api
mvn clean install
mvn spring-boot:run

Notes: Runs on http://localhost:8088 (can configure the port in application.properties from card_ops_api)


### 3️⃣ Run Backend #2 (place_app)
```bash
cd place_app
mvn clean install
mvn spring-boot:run

Notes: Runs on http://localhost:8089 (can configure the port in application.properties from place_app)


### 4️⃣ Run Frontend
```bash
cd frontend_place_app/react_places
npm install
npm start

App runs on http://localhost:3000 (once it successful started, you may test on the page)


### 5️⃣ Test API (Optional)
Import Postman collection from /postman_collection/ to test card_ops_api

