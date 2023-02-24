*** Settings ***
Library         ../libraries/utils.py

Force Tags      sample


*** Test Cases ***
Success
    Check variable    pass

Failure
    [Tags]    ignore
    Check variable    fail

Sum
    ${rsult}=    Add Numbers    ${3}    ${9}
    Should be Equal As Numbers    ${rsult}    ${12}


*** Keywords ***
Check variable
    [Arguments]    ${value}
    IF    "${value}" == "fail"    Run keyword    fail    Process failed
