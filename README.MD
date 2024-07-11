### Building

```
./gradlew clean build
```


### Running

Change the <ACCESS_KEY> and <ACCESS_SECRET> for the keys from aws.
```
./gradlew bootRun --args='--aws.accessKey=<ACCESS_KEY> --aws.secretKey=<ACCESS_SECRET>'
```