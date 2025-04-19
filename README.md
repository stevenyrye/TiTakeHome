1.	Application using in SQLite database, when application started, visit DB console using command
sqlite3 mydatabase.db

You can use INSERT INTO ... to insert some init records

2.	A swagger UI 3 is also implemented with application, once application started, visit 
http://localhost:8980/swagger-ui.html , you can test from there.
 
3.	GET all endpoint: http://localhost:8090/api/todos/
4.	GET by id: http://localhost:8090/api/todos/1
        Create record: POST http://localhost:8090/api/todos/
         Payload = {
                    "id": 1,
                    "description": "fdfdf",
                    "completed": true }
5. delete by id: DELETE http://localhost:8090/api/todos/1

6.	Console log implemented
7.	Branch RestfulOnly implemented rest api as required
8.	Branch Restful-and-gRPC also have gRPC api implemented. this project is built under MacOS UNIX, if you want build it in Windows or other OS, minor change is required in pom.xml such as grpc.version, protobuf.version and build extension groupId=kr.motd.maven; artifactId=os-maven-plugin; version=1.7.0


Enjoy
