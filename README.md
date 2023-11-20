# Reference design: Spring Boot 3 CRUD service

This is a reference design to build microservices (CRUD service) on the basis of Java17, Spring Boot 3 and H2 database

## Building and running a service:

```shell
mvn clean install
mvn spring-boot:run
```

## Testing

The last command will start server at `localhost:8080`. The in-memory database H2 is populated with a couple of records, so one can test service in action.

To test endpoints, go to `http://localhost:8080/swagger-ui/index.html` - this url points to Swagger page with generated API

Go to `POST /login` endpoint down the page and authorize yourself with user/pwd pair `admin:admin`. This will give you the bearer token, use it to authorize Swagger to invoke other endpoints on behalf of you.

After that one can invoke any endpoints, for example `GET /api/cars` will return json like this:

```json
{
  "_embedded": {
    "cars": [
      {
        "brand": "Ford",
        "model": "Mustang",
        "color": "Red",
        "registrationNumber": "ADF-1121",
        "modelYear": 2023,
        "price": 59000,
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/cars/1"
          },
          "car": {
            "href": "http://localhost:8080/api/cars/1"
          },
          "owner": {
            "href": "http://localhost:8080/api/cars/1/owner"
          }
        }
      },
...
``` 

Note the auto-generated fields "_links" - they are created due to presence of data-rest dependency on class path which enriches the output to match HATEOS(Hypertext as the Engine of Application State) principles.

  
 




