# 📊 Data Analytics Dashboard Application

## Overview

The Data Analytics Dashboard Application is a full-stack business intelligence platform that enables users to upload datasets and generate interactive dashboards for data visualization and analysis.

The system processes uploaded Excel or CSV files, stores data in MySQL, performs backend processing using Spring Boot, and integrates with Power BI to create dynamic reports and dashboards.

This application helps organizations transform raw data into actionable insights through visual analytics and reporting.

---

## Key Features

### Data Upload

* Upload Excel (.xlsx) files
* Upload CSV files
* Automatic data validation
* Error handling for invalid datasets

### Data Management

* Store uploaded datasets in MySQL
* Dataset version management
* Data retrieval and filtering

### Analytics Engine

* Data aggregation
* KPI calculation
* Statistical analysis
* Trend identification

### Dashboard Generation

* Interactive charts
* Tables and reports
* Real-time visualizations
* Custom dashboard views

### Power BI Integration

* Connect MySQL database to Power BI
* Automated report generation
* Business intelligence dashboards
* Data-driven insights

---

## Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Data Processing

* Apache POI (Excel Processing)
* OpenCSV

### Business Intelligence

* Power BI Desktop
* Power BI Service

### Build Tool

* Maven

### Development Environment

* IntelliJ IDEA
* MySQL Workbench
* Postman

---

## System Architecture

```text
User
  │
  ▼
Upload CSV/Excel
  │
  ▼
Spring Boot Backend
  │
  ├── Data Validation
  ├── Data Processing
  └── Data Transformation
  │
  ▼
MySQL Database
  │
  ▼
Power BI
  │
  ▼
Interactive Dashboards
```

---

## Project Modules

### 1. Authentication Module

* User Registration
* User Login
* Role Management

### 2. Dataset Upload Module

* File Upload API
* Dataset Validation
* Data Parsing

### 3. Data Processing Module

* Cleaning Data
* Transformation
* Aggregation

### 4. Database Module

* Data Storage
* Query Management
* Dataset Retrieval

### 5. Analytics Module

* KPI Calculation
* Trend Analysis
* Performance Metrics

### 6. Dashboard Module

* Visual Reports
* Charts and Graphs
* Summary Insights

---

## Database Design

### Users Table

| Column   | Type    |
| -------- | ------- |
| id       | BIGINT  |
| name     | VARCHAR |
| email    | VARCHAR |
| password | VARCHAR |

### Datasets Table

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| file_name   | VARCHAR   |
| upload_date | TIMESTAMP |
| uploaded_by | BIGINT    |

### Analytics Table

| Column       | Type    |
| ------------ | ------- |
| id           | BIGINT  |
| dataset_id   | BIGINT  |
| metric_name  | VARCHAR |
| metric_value | DOUBLE  |

---

## API Endpoints

### Upload Dataset

```http
POST /api/datasets/upload
```

### Get All Datasets

```http
GET /api/datasets
```

### Get Dashboard Data

```http
GET /api/dashboard
```

### Get Analytics

```http
GET /api/analytics
```

---

## Installation

### Clone Repository

```bash
git clone https://github.com/Himanshu172003/Weather_analytics.git
```

### Configure Database

Create database:

```sql
CREATE DATABASE analytics_db;
```

### Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/analytics_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Power BI Integration Steps

1. Open Power BI Desktop.
2. Select MySQL Database Connector.
3. Connect to analytics_db.
4. Import required tables.
5. Create visualizations.
6. Publish dashboard to Power BI Service.

---

## Future Enhancements

* AI-powered analytics
* Predictive forecasting
* Real-time streaming data
* Automated report generation
* Dashboard sharing
* Multi-tenant architecture
* Cloud deployment on AWS

---

## Project Outcomes

* Simplified business reporting.
* Faster decision-making.
* Centralized data management.
* Automated dashboard creation.
* Improved data visualization capabilities.

---

## Author

Himanshu Yadav

B.Tech Computer Science Engineering

---

## License

This project is intended for educational and academic purposes.
