CREATE_USER_RESPONSE_SCHEMA = {
    "required": [
        "email",
        "first_name",
        "last_name",
        "avatar",
        "job",
        "id",
        "createdAt",
    ],
    "properties": {
        "email": {
            "type": "string",
        },
        "first_name": {
            "type": "string",
        },
        "last_name": {
            "type": "string",
        },
        "avatar": {
            "type": "string",
        },
        "job": {
            "type": "string",
        },
        "id": {
            "type": "string",
        },
        "createdAt": {
            "type": "string",
        },
    },
}
UPDATE_USER_RESPONSE_SCHEMA = {
    "required": ["email", "first_name", "last_name", "avatar", "job", "updatedAt"],
    "properties": {
        "email": {
            "type": "string",
        },
        "first_name": {
            "type": "string",
        },
        "last_name": {
            "type": "string",
        },
        "avatar": {
            "type": "string",
        },
        "job": {
            "type": "string",
        },
        "updatedAt": {
            "type": "string",
        },
    },
}
GET_USER_RESPONSE_SCHEMA = {
    "required": ["data"],
    "properties": {
        "data": {
            "type": "object",
            "properties": {
                "id": {"type": "integer"},
                "email": {"type": "string"},
                "first_name": {"type": "string"},
                "last_name": {"type": "string"},
                "avatar": {"type": "string"},
            },
            "required": ["id", "email", "first_name", "last_name", "avatar"],
        }
    },
}
LOGIN_USER_RESPONSE_SCHEMA = {
    "required": ["token"],
    "properties": {"token": {"type": "string"}},
}
GET_USERS_RESPONSE_SCHEMA = {
    "required": ["page", "per_page", "total", "total_pages", "data", "support"],
    "properties": {
        "page": {"type": "integer"},
        "per_page": {"type": "integer"},
        "total": {"type": "integer"},
        "total_pages": {"type": "integer"},
        "data": {
            "type": "array",
            "items": {
                "type": "object",
                "required": ["id", "email", "first_name", "last_name", "avatar"],
                "properties": {
                    "id": {"type": "integer"},
                    "email": {"type": "string"},
                    "first_name": {"type": "string"},
                    "last_name": {"type": "string"},
                    "avatar": {"type": "string"},
                },
            },
        },
        "support": {
            "type": "object",
            "required": ["url", "text"],
            "properties": {"url": {"type": "string"}, "text": {"type": "string"}},
        },
    },
}
