
# Conference Room Reservation System - REST API

The main goal of this system is to manage conference rooms reservations for a specific organization.


## API Reference examples

### GET all organizations:

```http
  GET /api/organization
```

#### Sample output:
```json
[
  {
    "organizationId": 1,
    "organizationName": "Transporeon",
    "numberOfRooms": "14"
  },
  {
    "organizationId": 2,
    "organizationName": "Uncle Sam",
    "numberOfRooms": "5"
  }
]
```


### GET a specific organization:

```http
  GET /api/organization/{organizationId}
```

| Parameter        | Type     | Description                               |
|:-----------------|:---------|:------------------------------------------|
| `organizationId` | `long`   | **Required**. requested organization's id |

#### Sample output:
```json
{
  "organizationId": 2,
  "organizationName": "Uncle Sam",
  "numberOfRooms": "5"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Organization not found."
}
```


### POST

```http
  POST /api/organization
```


| Parameter | Type   | Description |
|:----------|:-------|:------------|
| `TODO`    | `TODO` | TODO        |

#### Sample output:
```json
{
  "organizationId": 23,
  "organizationName": "Amazon",
  "numberOfRooms": "0"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Organization Amazon already exists."
}
```

### DELETE

```http
  DELETE /api/organization/{organizationId}
```

| Parameter        | Type     | Description                               |
|:-----------------|:---------|:------------------------------------------|
| `organizationId` | `long`   | **Required**. requested organization's id |


#### Sample output (invalid parameters):
```json
{
  "message": "Organization not found."
}
```

### PUT

```http
  PUT /api/organization
```


| Parameter | Type   | Description |
|:----------|:-------|:------------|
| `TODO`    | `TODO` | TODO        |

#### Sample output:
```json
{
  "organizationId": 23,
  "organizationName": "Amazon",
  "numberOfRooms": "0"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Organization Amazon already exists."
}
```


## Tech Stack

**Technologies:** Java, Spring Framework, H2, PostgreSQL




## Authors

- [@Paweł Barwiński](https://github.com/PawelB-93)
- [@Kamil Musiał](https://github.com/kmusial1988)
- [@Rafał Zieliński](https://github.com/rafalz94)
- [@Wojciech Wasilewski](https://github.com/wwasilewski)

