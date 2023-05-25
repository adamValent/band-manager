from locust import HttpUser, task, between

counter=0
class test(HttpUser):

    @task
    def test(self):
        wait_time = between(1, 2)
        # First you need to get oauth2 token
        # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
        # and sign in to muni page and select scopes, then copy token, email, fist name and last name and past then here
        token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4NTA0MDA5NiwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODUwNDY0MDgsImlhdCI6MTY4NTA0MjgwOCwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiZTc0OWQ5MWYtNzcwOC00OGVmLTgwZTEtNmExNGY4ZTA4ODFiIn0.OUhn8Rr75rWaYVV0xu8KY13lC136CRmBycChu6oDHL4_xmsH-_F5jNtLpQOobImPM81KsoMsfjofwX9fPdTWuZdjbDfT3jKH4A6wbh69zZMhuv7jipcLq3_yvaWbv7ZKjwRgFwm4fg5fZyFa4Y_7fQ0ksl1cCCdNhjnyu2U844nvitnknOUI4tg-FBmrzFhlGhqFAWwfNDy6iTBvg1HwFNYwZK21SP0DLbZBNEXmPAAYz6VZ1_Ny1w3ctISljO1Vz_bYfR-CNITy9-lamjbKkmsOJusiTCbwUXUzf7L_5xIUeQ64aKZ44v68y5g93f_F_0VeWYk6NBzdE1t88ItIsA"
        email = "EMAIL"
        first_name = "NAME"
        second_name = "NAME"
        scope_test_1 = True  # for this scenario you need scope_test_1

        global counter
        if counter == 1:
            exit()

        try:
            user = self.create_user(token, email, first_name, second_name)
            print(user)
            band = self.create_band(token, user)
            print(band)
            album = self.create_album(token, band)
            print(album)
            self.send_email_to_band_manager(token, band["id"]) # for testing purposes to your band
        except:
            exit()

        counter = counter + 1

    def create_user(self, token, email, first_name, second_name):
        response = self.client.post("http://users:8080/users-auth",
                                    json={"email": email, "userType": "MANAGER",
                                          "firstName": first_name, "lastName": second_name},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("create user " + str(response.status_code))
        #print("create user " + str(response.headers))
        #print("create user " + str(response.json()))
        return response.json()

    def create_band(self, token, user):
        response = self.client.post("http://core:8080/bands",
                                    json={"name": "My band", "genre": "ROCK",
                                          "image": ["67","7","89"], "manager": user},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("create band " + str(response.status_code))
        #print("create band " + str(response.headers))
        #print("create band " + str(response.json()))
        return response.json()

    def create_album(self, token, band):
        response = self.client.post("http://core:8080/albums",
                                    json={"name": "My album", "releaseDate": "2017-01-13",
                                          "genre": "ROCK", "band": band},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("create album " + str(response.status_code))
        #print("create album " + str(response.headers))
        #print("create album " + str(response.json()))
        return response.json()

    def send_email(self, token, email):
        response = self.client.post("http://email:8080/email",
                                    json={"subject": "Test email", "emailBody": "hello", "recipients": [email]},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("send_email_to_band_manager " + str(response.status_code))
        #print("create album " + str(response.headers))
        #print("create album " + str(response.json()))
