##Requirements to run the application:

Java 11
Gradle

### View all books
`curl -u user:password http://localhost:8080/books`

### Add Books - this will return 403
`curl -u user:password --request POST 'http://localhost:8080/book' \
    --header 'Authorization: Basic' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "name": "2 Rich Dad Poor Dad",
    "author": "Robert Kiyosaki",
    "price": 10.99 }'`

### View the authorities on user
`curl -u admin:password http://localhost:8080/users/user   -> {"id":2,"userName":"user","password":"password","
authorities":"READ_AUTHORITY"}`

### Update the authorities on user
`curl -u admin:password --request PUT 'http://localhost:8080/users/user/roles' \
--header 'Authorization: Basic' \
--header 'Content-Type: application/json' \
--data-raw '["READ_AUTHORITY","WRITE_AUTHORITY"]'`

### Try adding the book -- this should return 200
`curl -u user:password --request POST 'http://localhost:8080/book' \
--header 'Authorization: Basic' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "2 Rich Dad Poor Dad",
"author": "Robert Kiyosaki",
"price": 10.99 }'`

### View all books
`curl -u user:password http://localhost:8080/books`

### Possible improvements
- Add JWT
- Add the authority within the roles
- Exception handling