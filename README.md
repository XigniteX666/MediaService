# Media service

This project is created to build a media service which will convert movie files through a rest service.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Testing the application

There are 2 enpoints exposed in the application

``
GET localhost:8080/media?fileName=test.mov
``
This service takes a local file and converts it.
Filelocations and conversion settings can be configured in the application properties

``
POST http://localhost:8080/media
``

This service takes a file as payload converts it and stores it on the configured location.
The filelocation can be configured in the application properties.