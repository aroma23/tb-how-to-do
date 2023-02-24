How to do - playlist
https://www.youtube.com/playlist?list=PLZ6CnHPbZ3yWMvnqh_qZcxLy9l5Nf1qdg

sample webservice mock app: https://reqres.in/

Software requirements
----------------
```markdown
Python 3.7.13
pyenv 2.3.5
pyenv-virtualenv 1.1.5
Robot Framework 5.0.1
```

## Pyenv
```commandline
pyenv virtualenv 3.7.2 robot_3.7.2
pyenv versions
pyenv activate robot_3.7.2
pyenv local robot_3.7.2
```

##### Important Libraries Need For This Project
```markdown
Refer requirements.txt
```

# Robot
```commandline
robot -d results -i all -e ignore -V config/mock.py tests/
robot -d results -i all -e ignore -V config/mock.py tests/positive.robot
robot -d results -i all -e ignore -V config/mock.py tests/ -L DEBUG:INFO tests/;open results/report.html
robot -d results -i get_users -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i create_user_negative -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i get_user_negative -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i login_user -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i create_user -e ignore -V config/mock.py -L DEBUG:INFO tests/;open results/report.html
robot -d results -i sample -e ignore tests/ ;open results/report.html

```
