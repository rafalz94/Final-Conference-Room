
# Conference Room Reservation System - REST API

The main goal of this system is to manage conference rooms reservations for a specific organization.


## API Reference examples

### - Organization

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

---

### - Conference Room

### GET all conference-rooms:

```http
  GET /api/conference-room
```

#### Sample output:
```json
[
  {
    "conferenceRoomId": 1,
    "conferenceRoomName": "big-room1",
    "level": 2,
    "availability": "NO",
    "sittingPlaces": 12,
    "standingPlaces": 54,
    "organizationName": "Transporeon"
  },
  {
    "conferenceRoomId": 2,
    "conferenceRoomName": "big-room2",
    "level": 3,
    "availability": "NO",
    "sittingPlaces": 12,
    "standingPlaces": 54,
    "organizationName": "Transporeon"
  }
]
```

### GET a specific conference-room:

```http
  GET /api/conference-room/{conferenceRoomId}
```

| Parameter          | Type     | Description                       |
|:-------------------|:---------|:----------------------------------|
| `conferenceRoomId` | `long`   | **Required**. requested room's id |

#### Sample output:
```json
{
  "conferenceRoomId": 1,
  "conferenceRoomName": "big-room1",
  "level": 2,
  "availability": "NO",
  "sittingPlaces": 12,
  "standingPlaces": 54,
  "organizationName": "Transporeon"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Conference room not found."
}
```

### POST

```http
  POST /api/conference-roon
```


| Parameter | Type   | Description |
|:----------|:-------|:------------|
| `TODO`    | `TODO` | TODO        |

#### Sample output:
```json
{
  "conferenceRoomId": 3,
  "conferenceRoomName": "big-room3",
  "level": 3,
  "availability": "NO",
  "sittingPlaces": 12,
  "standingPlaces": 54,
  "organizationName": "Transporeon"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Conference room big-room3 already exists."
}
```

### DELETE

```http
  DELETE /api/conference-room/{conferenceRoomId}
```

| Parameter          | Type     | Description                       |
|:-------------------|:---------|:----------------------------------|
| `conferenceRoomId` | `long`   | **Required**. requested room's id |


#### Sample output (invalid parameters):
```json
{
  "message": "Conference room not found."
}
```

### PUT

```http
  PUT /api/conference-room
```


| Parameter | Type   | Description |
|:----------|:-------|:------------|
| `TODO`    | `TODO` | TODO        |

#### Sample output:
```json
{
  "conferenceRoomId": 1,
  "conferenceRoomName": "big-room1",
  "level": 7,
  "availability": "NO",
  "sittingPlaces": 2,
  "standingPlaces": 2,
  "organizationName": "Transporeon"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Conference room not found."
}
```

---

### - Reservation

### GET all reservations:

```http
  GET /api/reservation
```

#### Sample output:
```json
[
  {
    "conferenceRoomId": 1,
    "conferenceRoomName": "big-room1",
    "level": 2,
    "availability": "NO",
    "sittingPlaces": 12,
    "standingPlaces": 54,
    "organizationName": "Transporeon"
  },
  {
    "conferenceRoomId": 2,
    "conferenceRoomName": "big-room2",
    "level": 3,
    "availability": "NO",
    "sittingPlaces": 12,
    "standingPlaces": 54,
    "organizationName": "Transporeon"
  }
]
```

## Tech Stack

**Technologies:** Java, Spring Framework, H2, PostgreSQL




## Authors

- [@Paweł Barwiński](https://github.com/PawelB-93)
- [@Kamil Musiał](https://github.com/kmusial1988)
- [@Rafał Zieliński](https://github.com/rafalz94)
- [@Wojciech Wasilewski](https://github.com/wwasilewski)

