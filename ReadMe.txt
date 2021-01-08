Description:
    The project gives the possibility to upload a xlsx file and save the data to the database, and allow to get the saved data filtered by team, product, booking  type etc. .
Technologies:
    - Spring Boot 2.4.1
    - Java 11
    - H2 database
Requirements:
XLSX Import

Create Spring boot based app with the following API endpoints.
POST /upload  - Provides the ability to upload XLSX (from Excel, not CSV)
Other parameters to help to extract data from XLSX file are worksheet’s name and selected area string, for example A1:D20 to mark the rectangle which is going to be imported.

After file is uploaded, uploaded file metadata to be stored into DB table (name, creation date, size, upload date)
Data from XLSX file to be added into corresponding table in DB, excluding duplicates by field OpportunityID

GET /opportunity returns all objects if no filter is applied
Add parameters to filter by team, product, bookingtype and date range (startDate, endDate based on bookingdate field  

Project should be buildable via maven 
Libraries/external dependencies choose is up to developer
No UI is required, Postman or Paw file to test API is a plus