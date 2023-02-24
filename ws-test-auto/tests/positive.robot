*** Settings ***
Resource            ../resources/rr_kws.robot
Variables           ../data/payloads/create_update_user.py
Variables           ../data/schemas/all_schema.py
Library             String

Suite Setup         Create RR Sessions
Suite Teardown      Delete All Sessions

Force Tags          all


*** Test Cases ***
User Workflow Cases
    [Tags]    user_work_flow    ignore
    #Get Users
    Log To Console    ${\n}Get Users.....
    Get Users Wrapper
    #Add
    Log To Console    ${\n}Create User.....
    Create User Wrapper
    #Get
    Log To Console    ${\n}Get User.....
    Get User Wrapper    ${id_sv}
    #Update
    Log To Console    ${\n}Update User.....
    Update User Wrapper    ${id_sv}
    #Get
    Log To Console    ${\n}Get User.....
    Get User Wrapper    ${id_sv}
    #Delete
    Log To Console    ${\n}Delete User.....
    Delete User Wrapper    ${id_sv}

Get Users Test
    [Documentation]    *Validate get users api*
    [Tags]    get_users
    Get Users Wrapper

Create User Test
    [Documentation]    *Validate create user api*
    [Tags]    create_user
    Create User Wrapper

Get User Test
    [Documentation]    *Validate get user api*
    [Tags]    get_user
    Get User Wrapper    2

Update User Test
    [Documentation]    *Validate update user api*
    [Tags]    update_user
    Update User Wrapper    2

Delete User Test
    [Documentation]    *Validate delete user api*
    [Tags]    delete_user
    Delete User Wrapper    2


*** Keywords ***
Create RR Sessions
    Create Session for RR Service

Create User Wrapper
    ${first_name}    Generate Random String    6    [LOWER]
    ${last_name}    Generate Random String    6    [LOWER]
    ${payload_as_dictionary}    Convert To Dictionary    ${CREATE_UPDATE_USER_PAYLOAD}
    Set To Dictionary    ${payload_as_dictionary}    first_name=${first_name}    last_name=${last_name}
    ...    email=${first_name}.${last_name}@testbots.com
    Log To Console    ${\n}Payload to send: ${payload_as_dictionary}
    ${response}    Create User    ${payload_as_dictionary}
    Status Should Be    201    ${response}
    ${response_as_dictionary}    Convert To Dictionary    ${response.json()}
    ${schema_as_dictionary}    Convert To Dictionary    ${CREATE_USER_RESPONSE_SCHEMA}
    Validate Json By Schema    ${response_as_dictionary}    ${schema_as_dictionary}
    ${id}    Set Variable    ${response_as_dictionary['id']}
    Set Suite Variable    ${id_sv}    ${response_as_dictionary['id']}
#    Set Global Variable    ${id_gv}    ${response_as_dictionary['id']}
    should be equal    ${response_as_dictionary['first_name']}    ${first_name}
    should be equal    ${response_as_dictionary['last_name']}    ${last_name}
    should be equal    ${response_as_dictionary['email']}    ${first_name}.${last_name}@testbots.com

Update User Wrapper
    [Arguments]    ${user_id}
    ${first_name}    Generate Random String    6    [LOWER]
    ${last_name}    Generate Random String    6    [LOWER]
    ${payload_as_dictionary}    Convert To Dictionary    ${CREATE_UPDATE_USER_PAYLOAD}
    Set To Dictionary    ${payload_as_dictionary}    first_name=${first_name}    last_name=${last_name}
    ...    email=${first_name}.${last_name}@testbots.com
    Log To Console    ${\n}Payload to send: ${payload_as_dictionary}
    ${response}    Update User    ${user_id}    ${payload_as_dictionary}
    Status Should Be    200    ${response}
    ${response_as_dictionary}    Convert To Dictionary    ${response.json()}
    ${schema_as_dictionary}    Convert To Dictionary    ${UPDATE_USER_RESPONSE_SCHEMA}
    Validate Json By Schema    ${response_as_dictionary}    ${schema_as_dictionary}
    should be equal    ${response_as_dictionary['first_name']}    ${first_name}
    should be equal    ${response_as_dictionary['last_name']}    ${last_name}
    should be equal    ${response_as_dictionary['email']}    ${first_name}.${last_name}@testbots.com

Get User Wrapper
    [Arguments]    ${user_id}
    ${response}    Get Users    ${user_id}
    Status Should Be    200    ${response}
    ${resp_dict}    Convert To Dictionary    ${response.json()}
    Validate Json By Schema file    ${resp_dict}    ${CURDIR}/../data/schemas/get_user_response_schema.json
    ${schema_dict}    Convert To Dictionary    ${GET_USER_RESPONSE_SCHEMA}
    Validate Json By Schema    ${resp_dict}    ${schema_dict}
    ${first_name}    Get From Dictionary    ${resp_dict['data']}    first_name
    should be equal    ${first_name}    Janet
    should be equal    ${resp_dict['data']['last_name']}    Weaver

Get Users Wrapper
    ${response}    Get Users
    Status Should Be    200    ${response}
    ${resp_dict}    Convert To Dictionary    ${response.json()}
    ${schema_dict}    Convert To Dictionary    ${GET_USERS_RESPONSE_SCHEMA}
    Validate Json By Schema    ${resp_dict}    ${schema_dict}
    Should Contain    ${response.text}    Janet    Weaver

Delete User Wrapper
    [Arguments]    ${user_id}
    ${response}    Delete User    ${user_id}
    Status Should Be    204    ${response}
