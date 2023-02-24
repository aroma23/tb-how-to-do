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


# Python
```commandline
python 3.7.2
pyenv 2.3.4
pyenv-virtualenv 1.1.5
```

## Pyenv
```commandline
pyenv virtualenv 3.7.2 robot_3.7.2
pyenv versions
pyenv activate robot_3.7.2
pyenv local robot_3.7.2
```

# Robot

```commandline
robot -d results -i GetUsers -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i CreateUser -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i createuser -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i UpdateUser -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html

robot -d results -i sample -e ignore tests/ ;open results/report.html

robot -d results -e ignore tests/om.robot;open results/report.html

robot -d results -i OM -e ignore -V config/dev.py -L DEBUG tests/om.robot;open results/report.html

robot -d results -i OM -e ignore -V config/dev.py -L DEBUG:INFO tests/om.robot;open results/report.html

robot -d results -i FA -e ignore -V config/dev.py -L DEBUG:INFO tests/;open results/report.html

robot -d results -i fa_add_l3_device -e ignore -V config/dev.py -L DEBUG:INFO tests/;open results/report.html

robot -d results -i fa_update_l3_device -e ignore -V config/dev.py -L DEBUG:INFO tests/;open results/report.html

robot -d results -i fa_delete_l3_device -e ignore -V config/dev.py -L DEBUG:INFO tests/;open results/report.html

robot -d results -i fa_e2e_l3_device -e ignore -V config/dev.py -L DEBUG:INFO tests/;open results/report.html

```

# Dockerfile
```commandline
docker build -t robot-com-qa-3.7.2 .

docker login -u muthudocker83 -p

docker push muthudocker83/robot-com-qa-3.7.2:latest

docker run --rm --interactive -v $(pwd):/app robot-com-qa-3.7.2 robot -d results -i fa_e2e_l3_device -e ignore -V config/dev.py -L DEBUG:INFO tests/;open results/report.html

docker run --rm --interactive -v $(pwd):/app muthudocker83/robot-com-qa-3.7.2:latest robot -d results -i fa_e2e_l3_device -e ignore -V config/dev.py -L DEBUG:INFO tests/;open results/report.html

robot --output output.xml suite.robot & robot --rerunfailed output.xml --output output2.xml suite.robot & robot --output output.xml --merge output.xml output2.xml
robot --rerunfailed output.xml --output output2.xml suite.robot
rebot --output output.xml --merge output.xml output2.xml
```

# Git
```commandline
git remote add origin https://github.com/<userName>/robot-components-qa.git
git branch -M main
git push -u origin main
```

# Jenkins
```commandline
https://<personal access token from git>@github.com/<userName>/<repository>.git

```
