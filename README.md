##Requirements to run the application:
- Java 11
- Gradle

#### Application is protected with basic auth. There are two users:
```
- userName: admin password: password - Permissions to create/update/view features and assign features 
- userName: user password: password -Permission to view features 
```

### Default features:
```[
{
"id": 3,
"name": "Feature 1",
"globallyEnabled": true
},
{
"id": 4,
"name": "Feature 2",
"globallyEnabled": false
},
{
"id": 5,
"name": "Feature 3",
"globallyEnabled": false
},
{
"id": 6,
"name": "Feature 4",
"globallyEnabled": true
}
]
```
``
Admin end-points - 
``
### Possible improvements
- Add Unit and Integration testing
- Protect end points with JWT