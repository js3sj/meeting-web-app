## Meeting storage web app

### Java web application to manage internal meetings using Java Spring Boot.

● Rest API endpoint to create a new meeting. All the meeting data is stored in a JSON file. Application retains data between restarts. 

Meeting model contains the following properties:

○ Name

○ ResponsiblePerson

○ Description

○ Category (Fixed values - CodeMonkey / Hub / Short / TeamBuilding)

○ Type (Fixed values - Live / InPerson)

○ StartDate

○ EndDate

● Rest API endpoint to delete a meeting. Only the person responsible can delete the meeting.

● Rest API endpoint to add a person to the meeting.

○ Command specifies who is being added and at what time.

○ If a person is already in a meeting which intersects with the one being added, a warning message is given.

○ Prevents the same person from being added twice.

● Rest API endpoint to remove a person from the meeting.

○ If a person is responsible for the meeting, he can not be removed.

● Rest API endpoint to list all the meetings. 

The following parameters filters the data:

○ Filter by description (if the description is “Jonhn Java meet”, searching for java, return this entry)

○ Filter by responsible person

○ Filter by category

○ Filter by type

○ Filter by dates (e.g meetings that will happen starting from 2022-12-01 / meetings that will happen between 2023-01-01 and 2023-02-01)

○ Filter by the number of attendees (e.g show meetings that have over 10 people attending)

### Technologies
- Java jdk-17
- maven
- SpringBoot 3.0.0
- thymeleaf
- jakarta validation
- JSON parser
