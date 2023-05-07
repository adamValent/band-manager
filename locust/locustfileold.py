from locust import HttpUser, task

class HelloWorldUser(HttpUser):

    @task
    def hello_world(self):
        token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4MzQ4MjYyNCwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODM0ODc5OTYsImlhdCI6MTY4MzQ4NDM5NiwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiYzUzMDZiZmEtYTI5Yy00ODQ5LTk0N2UtYmNjZjI5YzE4ZDhkIn0.IxP1ZXwba9cfg2pwV3-EYO25GjGtSfM9rNLyvSgCXyf-RbNM4APMVss-YUcPSBkby-OhiM6q8NI_M8-vbGqoxjmC6c8N6TexxEoEiAhV3fNVKcrWu6tSZTqs8-3jDoKIaTX0zjxgnnMAk7D9K1aIKJygk1iUHxsRS5TsJLwhskpq-q9qg2uzVgNZlu0vdtGJQYxL9NL05NPTyWugeBIa-s1OBGhbImZL9ZyooVQlwDRHgvOORR8NNuxkwFK-ukAmAoWUPNooyPvPaxoRkk5lZhKt6iA-_rsSAbqbKvFEPftagpK5kqXVZndqEm2lJPxMTqfo9vHEwd0f6F-jhL6IAw"
        email = "493182@muni.cz"
        first_name = "Patrik"
        second_name = "Cangel"
        scope_test_1 = True #SET TO TRUE IF YOU SELECTED test_1 as scope
        scope_test_2 = True #SET TO TRUE IF YOU SELECTED test_2 as scope

        response = self.client.post('/users-auth', data=
        {
            "email": email,
            "userType": "MANAGER",
            "firstName": first_name,
            "lastName": second_name
        }, headers={"Authorization": ("Bearer " + token)})

        print("\n\n")
        print("Response status code:", response.status_code)
        print("Response text:", response.json())
        print("\n\n")