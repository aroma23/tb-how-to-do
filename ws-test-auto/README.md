How to do - playlist
https://www.youtube.com/playlist?list=PLZ6CnHPbZ3yWMvnqh_qZcxLy9l5Nf1qdg

sample webservice mock app: https://reqres.in/

Software requirements
----------------
```markdown
Maven 3.8.6
Java: openjdk 18.0.2.1
```

Important Libraries
```markdown
cucumber
testng
rest assured
rest assured json validator
```
maven commands to run
```markdown
mvn clean test -Dcucumber.filter.tags=@All
mvn clean test -Dcucumber.filter.tags="@Positive and @GetUser"
mvn clean test -Dcucumber.filter.tags="@GetUsers or @GetUser"
mvn clean test -Dcucumber.filter.tags="@GetUsers or @GetUser" -Dtest.env=qa
```