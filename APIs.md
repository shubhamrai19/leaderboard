# Player

## 1 POST /v1/player

### Request

	{
		"name":"ram",
		"email":"abc@hmail.com",
		"mobile_num":"9999999999"
	}

### Response

	{
		"id":1,
		"name":"ram",
		"email":"abc@hmail.com",
		"mobile_num":"9999999999"
	}

## 2- Get /v1/player/{id}
### Response

	{
		"id":1,
		"name":"ram",
		"email":"abc@hmail.com",
		"mobile_num":"9999999999"
	}


# Game

## 1-Post /v1/game
### Request
    {
    "name":"cricket",
    "maxplayer_count":11,
    "duration_minutes":60
    }

### Response
    {
    "id":11,
    "name":"cricket",
    "maxplayer_count":11,
    "duration_minutes":60
    }

## 2- Get /v1/game/{id}
### Response
    {
    "id":11,
    "name":"cricket",
    "maxplayer_count":11,
    "duration_minutes":60
    }
	
# main service

## 1.GET /v1/leaderboard
### Response
    {
    "data": [
    {
    "player_id": "ram123",
    "score": 12,
    "rank": 1
    },
    {
    "player_id": "sita222",
    "score": 50,
    "rank": 2
    },
    {
    "player_id": "shyam212",
    "score": 65,
    "rank": 3
    }
    ]
    }

## 2.POST /v1/{game_id}/score

### Request
    {
    "player_id":"ram123",
    "score": 12
    }

### Response
    {
    "player_id":"ram123",
    "score": 12
    }
