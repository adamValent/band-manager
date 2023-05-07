from locust import HttpUser, task


class HelloWorldUser(HttpUser):

    def runnable_scenario(self):

        @task
        def create_user(self):
            token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4MzQ4MjYyNCwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODM0ODc5OTYsImlhdCI6MTY4MzQ4NDM5NiwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiYzUzMDZiZmEtYTI5Yy00ODQ5LTk0N2UtYmNjZjI5YzE4ZDhkIn0.IxP1ZXwba9cfg2pwV3-EYO25GjGtSfM9rNLyvSgCXyf-RbNM4APMVss-YUcPSBkby-OhiM6q8NI_M8-vbGqoxjmC6c8N6TexxEoEiAhV3fNVKcrWu6tSZTqs8-3jDoKIaTX0zjxgnnMAk7D9K1aIKJygk1iUHxsRS5TsJLwhskpq-q9qg2uzVgNZlu0vdtGJQYxL9NL05NPTyWugeBIa-s1OBGhbImZL9ZyooVQlwDRHgvOORR8NNuxkwFK-ukAmAoWUPNooyPvPaxoRkk5lZhKt6iA-_rsSAbqbKvFEPftagpK5kqXVZndqEm2lJPxMTqfo9vHEwd0f6F-jhL6IAw"
            email = "493182@muni.cz"
            first_name = "Patrik"
            second_name = "Cangel"
            scope_test_1 = True #SET TO TRUE IF YOU SELECTED test_1 as scope
            scope_test_2 = True #SET TO TRUE IF YOU SELECTED test_2 as scope

            self.client.post('/users-auth', data=
            {
                "email": email,
                "userType": "BAND_MEMBER" if scope_test_1 else "MANAGER",
                "firstName": first_name,
                "lastName": second_name
            }, headers={"Authorization": ("Bearer " + token)})

        # First you need to get oauth2 token
        # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
        # and sign in to muni page and select scopes, then copy token, email, fist name and last name and past then here
        #token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4MzQ4MjYyNCwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODM0ODc5OTYsImlhdCI6MTY4MzQ4NDM5NiwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiYzUzMDZiZmEtYTI5Yy00ODQ5LTk0N2UtYmNjZjI5YzE4ZDhkIn0.IxP1ZXwba9cfg2pwV3-EYO25GjGtSfM9rNLyvSgCXyf-RbNM4APMVss-YUcPSBkby-OhiM6q8NI_M8-vbGqoxjmC6c8N6TexxEoEiAhV3fNVKcrWu6tSZTqs8-3jDoKIaTX0zjxgnnMAk7D9K1aIKJygk1iUHxsRS5TsJLwhskpq-q9qg2uzVgNZlu0vdtGJQYxL9NL05NPTyWugeBIa-s1OBGhbImZL9ZyooVQlwDRHgvOORR8NNuxkwFK-ukAmAoWUPNooyPvPaxoRkk5lZhKt6iA-_rsSAbqbKvFEPftagpK5kqXVZndqEm2lJPxMTqfo9vHEwd0f6F-jhL6IAw"
        #email = "493182@muni.cz"
        #first_name = "Patrik"
        #second_name = "Cangel"
        #scope_test_1 = True #SET TO TRUE IF YOU SELECTED test_1 as scope
        #scope_test_2 = True #SET TO TRUE IF YOU SELECTED test_2 as scope

        #test = user_module(HttpUser)

        #test.create_user(token, email, first_name, second_name, scope_test_1)


        # Post request - data je predavani parametru v url
        # Headers funguje uplne stejne u obou, GET a dalsi requesty maji podobne parametry
        ###response = self.client.post("/posts", data=
        #{
        #    "title": "Silence of the Lambs",
        #    "body": "Thriller Book",
        #    "userId": 1
        #}, headers={"Content-type": "application/x-www-form-urlencoded"})
        ## Vytahnuti status codu a dat
        #print("Response status code:", response.status_code)
        #print("Response text:", response.json())
        ## Post request - json je predavani dat v body
        #response = self.client.post("/posts", json=
        #{
        #    "title": "Silence of the Lambs",
        #    "body": "Thriller Book",
        #    "userId": 1
        #}, headers={"Content-type": "application/json"})
        #print("Response status code:", response.status_code)
        #print("Response text:", response.json()



#class user_module(HttpUser):
#
#    #host = 'http://users:8084'
#
#    @task
#    def create_user(self, token, email, first_name, second_name, scope_test_1):
#        self.client.post('/users-auth', data=
#        {
#            "email": email,
#            "userType": "BAND_MEMBER" if scope_test_1 else "MANAGER",
#            "firstName": first_name,
#            "lastName": second_name
#        }, headers={"Authorization": ("Bearer " + token)})
