# Superhero-spring-boot
 Java 8, Spring Boot, H2
 
# Endpoints:
  All services return a common structure such as: GenericResponseDto<T>:  
  	{
    	"data": "T",
	    "errors": "...",
	    "status": "..."
	}
	Where "T" is a String, List, Object.
    Status: It is an Enum, indicating the success or not of the operation.
    
    
- GET .../api/superheroes/
	returns all registered superheroes, and are active
		output:
		{
		    "data": [
		        {
		            "id": 1,
		            "firstName": "firstName1",
		            "lastName": "lastName1",
		            "nickName": "nick1",
		            "powers": []
		        },
		        {
		            "id": 2,
		            "firstName": "firstName2",
		            "lastName": "lastName2",
		            "nickName": "nick2",
		            "powers": []
		        },
		        {
		            "id": 3,
		            "firstName": "firstName3",
		            "lastName": "lastName3",
		            "nickName": "nick3",
		            "powers": []
		        }
		    ],
		    "errors": null,
		    "status": "SUCCESS"
		}

- GET .../api/superheroes/{id}
	returns one superhero by id.
	Example: .../api/superheroes/2
		output:
		{
		    "data": {
		        "id": 2,
		        "firstName": "firstName2",
		        "lastName": "lastName2",
		        "nickName": "nick2",
		        "powers": []
		    },
		    "errors": null,
		    "status": "SUCCESS"
		}

- GET .../api/superheroes/filter/{name}
	returns all superheroes that contain the "name" on firstname, lastname or nickname, and are active
	Example:.../api/superheroes/filter/nick2
	output:
	{
	    "data": [
	        {
	            "id": 2,
	            "firstName": "firstName2",
	            "lastName": "lastName2",
	            "nickName": "nick2",
	            "powers": []
	        }
	    ],
	    "errors": null,
	    "status": "SUCCESS"
	}

- PUT .../api/superheroes/delete/{id}
	update/change active attribute to false value, by Id of the superhero.
	Example: .../api/superheroes/delete/2
	output:
	{
	    "data": {
	        "id": 2,
	        "firstName": "firstName2",
	        "lastName": "lastName2",
	        "nickName": "nick2",
	        "powers": []
	    },
	    "errors": null,
	    "status": "SUCCESS"
	}
	if, you run again the endpoint: GET ... /api/superheroes/
	output
	{
	    "data": [
	        {
	            "id": 1,
	            "firstName": "firstName1",
	            "lastName": "lastName1",
	            "nickName": "nick1",
	            "powers": []
	        },
	        {
	            "id": 3,
	            "firstName": "firstName3",
	            "lastName": "lastName3",
	            "nickName": "nick3",
	            "powers": []
	        }
	    ],
	    "errors": null,
	    "status": "SUCCESS"
	}

- POST .../api/superheroes/create
	register a new superhero with his power or powers
	Exameple:
	input body:
	 {
	            "firstName": "firstNameXX",
	            "lastName": "lastNameXX",
	            "nickName": "nickXX",
	            "powers": [{"name":"test"}]
	 } 
	output:
	{
	    "data": {
	        "id": 4,
	        "firstName": "firstNameXX",
	        "lastName": "lastNameXX",
	        "nickName": "nickXX",
	        "powers": [
	            {
	                "id": 4,
	                "name": "test"
	            }
	        ]
	    },
	    "errors": null,
	    "status": "SUCCESS"
	}
	if, you run again the same endpoint:
	output
	{
	    "data": "",
	    "errors": [
	        "Nick name, exists:nickXX"
	    ],
	    "status": "NOT_FOUND"
	}

- PUT .../api/superheroes/update/4
	update a superhero by id, with his power or powers
	Example:
	input body:
	{
	            "id":4,
	            "firstName": "firstNameAA",
	            "lastName": "lastNameXX",
	            "nickName": "flash",
	            "powers": [{"name":"speed"},{"name":"smart"}]
	}
	output
	{
	    "data": {
	        "id": 4,
	        "firstName": "firstNameAA",
	        "lastName": "lastNameXX",
	        "nickName": "flash",
	        "powers": [
	            {
	                "id": 5,
	                "name": "speed"
	            },
	            {
	                "id": 6,
	                "name": "smart"
	            }
	        ]
	    },
	    "errors": null,
	    "status": "SUCCESS"
	}
	
	if, you run again the same endpoint, but removing a power:
	input body:
	{
	            "id":4,
	            "firstName": "firstNameAA",
	            "lastName": "lastNameXX",
	            "nickName": "flash",
	            "powers": [{"name":"speed"}]
	}
	output
	{
	    "data": {
	        "id": 4,
	        "firstName": "firstNameAA",
	        "lastName": "lastNameXX",
	        "nickName": "flash",
	        "powers": [
	            {
	                "id": 5,
	                "name": "speed"
	            }
	        ]
	    },
	    "errors": null,
	    "status": "SUCCESS"
	}
