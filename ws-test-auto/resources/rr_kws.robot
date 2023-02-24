*** Settings ***
Library     RequestsLibrary
Library     JSONLibrary
Library     Collections


*** Keywords ***
Create Session for RR Service
    [Documentation]    Create a HTTP session for ${HOST_URL}
    ${headers}    Create Dictionary    Content-Type=application/json
    Create Session    alias=${RR_SESSION}
    ...    url=${HOST_URL}
    ...    disable_warnings=1
    ...    backoff_factor=2
    ...    max_retries=2
    ...    retry_method_list=['POST', 'GET', 'DELETE', 'PUT', 'PATCH']
    ...    retry_status_list=[400, 401, 402, 500, 501, 502]
    ...    headers=${headers}
    Sleep    2s

Get Users
    [Documentation]    GET /api/users/{user_id}
    [Arguments]    ${user_id}=${EMPTY}
    ${response}    GET On Session    ${RR_SESSION}    url=/api/users/${user_id}
    Request Should Be Successful
    RETURN    ${response}

Create User
    [Documentation]    POST /api/users
    [Arguments]    ${body}
    ${response}    POST On Session    ${RR_SESSION}    url=/api/users    json=${body}
    Request Should Be Successful
    RETURN    ${response}

Update User
    [Documentation]    PUT /api/users
    [Arguments]    ${user_id}    ${body}
    ${response}    PUT On Session    ${RR_SESSION}    url=/api/users/${user_id}    json=${body}
    Request Should Be Successful
    RETURN    ${response}

Delete User
    [Documentation]    Delete /api/users/${user_id}
    [Arguments]    ${user_id}
    ${response}    Delete On Session    ${RR_SESSION}    url=/api/users/${user_id}
    Request Should Be Successful
    RETURN    ${response}
#    [Return]    ${response}
#    Request Should Be Successful
#    ${response}    DELETE On Session    ${FA_SESSION}    url=/api/v1/coreNetworkL3Device/${device_id}
#    [Arguments]    ${device_id}
#    [Documentation]    GET /api/v1/coreNetworkL3Device/{device_id}
#Delete L3 Device
#
#    [Return]    ${response}
#    Request Should Be Successful
#    ${response}    GET On Session    ${FA_SESSION}    url=/api/v1/coreNetworkL3Device/${device_id}
#    [Arguments]    ${device_id}
#    [Documentation]    GET /api/v1/coreNetworkL3Device/{device_id}
#Get L3 Device
#
#    [Return]    ${response}
#    Request Should Be Successful
#    ${response}    PATCH On Session    ${FA_SESSION}    url=/api/v1/coreNetworkL3Device/${device_id}    json=${body}
#    [Arguments]    ${device_id}    ${body}
#    [Documentation]    PATCH /api/v1/coreNetworkL3Device/{device_id}
#Update L3 Device
