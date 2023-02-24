*** Settings ***
Resource            ../resources/rr_kws.robot
Variables           ../data/payloads/create_update_user.py
Variables           ../data/schemas/all_schema.py
Library             String

Suite Setup         Create RR Sessions
Suite Teardown      Delete All Sessions

Force Tags          all


*** Test Cases ***
Create User Test
    [Documentation]    _Validate create user api when email is not send_
    [Tags]    create_user    create_user_negative
    ${first_name}    Generate Random String    6    [LOWER]
    ${last_name}    Generate Random String    6    [LOWER]
    ${payload_as_dictionary}    Convert To Dictionary    ${CREATE_UPDATE_USER_PAYLOAD}
    Set To Dictionary    ${payload_as_dictionary}    first_name=${first_name}    last_name=${last_name}
    Log To Console    ${\n}Payload to send: ${payload_as_dictionary}
    ${response}    Create User    ${payload_as_dictionary}
    Status Should Be    400    ${response}

Get User Test
    [Documentation]    _Validate get user api when trying to get invalid user_
    [Tags]    get_user    get_user_negative
    ${response}    Get Users    250    404
    Status Should Be    404    ${response}

Login User Test
    [Documentation]    _Validate login user api when user never exists_
    [Tags]    login_user    login_user_negative
    ${LOGIN_USER_PAYLOAD}    Convert To Dictionary    ${LOGIN_USER_PAYLOAD}
    Set To Dictionary    ${LOGIN_USER_PAYLOAD}    email=user.notexists@testbots.com
    Log To Console    ${LOGIN_USER_PAYLOAD}
    ${response}    Login User    ${LOGIN_USER_PAYLOAD}    400
    Status Should Be    400    ${response}
    Should Contain    ${response.text}    user not found


*** Keywords ***
Create RR Sessions
    Create Session for RR Service
