1.	Application using in SQLite database, when application started, visit DB console using command
sqlite3 mydatabase.db

You can use INSERT INTO ... to insert some init records

2.	A swagger UI 3 is also implemented with application, once application started, visit 
http://localhost:8980/swagger-ui.html , you can test from there.
 
3.	GET all endpoint: http://localhost:8090/api/todos/
        GET by id: http://localhost:8090/api/todos/1
        Create record: POST http://localhost:8090/api/todos/
         Payload = {
                    "id": 1,
                    "description": "fdfdf",
                    "completed": true }
        delete by id: DELETE http://localhost:8090/api/todos/1

4.	Console log implemented

Enjoy
