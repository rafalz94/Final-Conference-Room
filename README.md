
# Conference Room Reservation System - REST API

The main goal of this system is to manage conference rooms reservations for a specific organization.
This repository contains back-end code, front-end code is available in the link below:


## [Front-end Angular App](https://github.com/kmusial1988/webAppFinal)

# API Reference examples

## --- Organization ---

### GET all organizations:

```http
  GET /organizations
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
  GET /organizations/{organizationId}
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
  POST /organizations
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
  DELETE /organizations/{organizationId}
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
  PUT /organizations
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

## --- Conference Room ---

### GET all conference rooms:

```http
  GET /conference-rooms
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

### GET a specific conference room:

```http
  GET /conference-rooms/{conferenceRoomId}
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
  POST /conference-rooms
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
  DELETE /conference-rooms/{conferenceRoomId}
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
  PUT /conference-rooms
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

## --- Reservation ---

### GET all reservations:

```http
  GET /reservations
```

#### Sample output:
```json
[
  {
    "reservationId": 1,
    "reservationIdentifier": "res1",
    "reservationStartDate": "2021-02-23 12:12",
    "conferenceRoomName": "big-room1",
    "organizationName": "Transporeon",
    "reservationEndDate": "2021-02-23 13:32"
  },
  {
    "reservationId": 2,
    "reservationIdentifier": "res2",
    "reservationStartDate": "2021-02-23 12:12",
    "conferenceRoomName": "small-room1",
    "organizationName": "Transporeon",
    "reservationEndDate": "2021-02-23 13:32"
  }
]
```

### GET a specific reservation:

```http
  GET /reservations/{reservationId}
```

| Parameter       | Type     | Description                              |
|:----------------|:---------|:-----------------------------------------|
| `reservationId` | `long`   | **Required**. requested reservations' id |

#### Sample output:
```json
{
  "reservationId": 1,
  "reservationIdentifier": "res1",
  "reservationStartDate": "2021-02-23 12:12",
  "conferenceRoomName": "big-room1",
  "organizationName": "Transporeon",
  "reservationEndDate": "2021-02-23 13:32"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Reservation not found."
}
```

### POST

```http
  POST /reservations
```


| Parameter | Type   | Description |
|:----------|:-------|:------------|
| `TODO`    | `TODO` | TODO        |

#### Sample output:
```json
{
  "reservationId": 2,
  "reservationIdentifier": "res2",
  "reservationStartDate": "2021-02-23 12:12",
  "conferenceRoomName": "small-room1",
  "organizationName": "Transporeon",
  "reservationEndDate": "2021-02-23 13:32"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Reservation already exists."
}
```

### DELETE

```http
  DELETE /reservations/{reservationId}
```

| Parameter       | Type   | Description                              |
|:----------------|:-------|:-----------------------------------------|
| `reservationId` | `long` | **Required**. requested reservations' id |


#### Sample output (invalid parameters):
```json
{
  "message": "Reservation not found."
}
```

### PUT

```http
  PUT /reservations
```

| Parameter | Type   | Description |
|:----------|:-------|:------------|
| `TODO`    | `TODO` | TODO        |

#### Sample output:
```json
{
  "reservationId": 2,
  "reservationIdentifier": "res2",
  "reservationStartDate": "2021-02-23 12:12",
  "conferenceRoomName": "small-room1",
  "organizationName": "Transporeon",
  "reservationEndDate": "2021-02-23 14:32"
}
```

#### Sample output (invalid parameters):
```json
{
  "message": "Reservation not found."
}
```
## Tech Stack

**Technologies:** Java, Spring Framework, H2, PostgreSQL

## Authors

- [@Paweł Barwiński](https://github.com/PawelB-93)
- [@Kamil Musiał](https://github.com/kmusial1988)
- [@Rafał Zieliński](https://github.com/rafalz94)
- [@Wojciech Wasilewski](https://github.com/wwasilewski)
