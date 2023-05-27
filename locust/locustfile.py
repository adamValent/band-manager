from locust import HttpUser, task, between

counter = 0
class scenario(HttpUser):
    token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4NTA0MDA5NiwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODUxOTE0MzQsImlhdCI6MTY4NTE4NzgzNCwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiMzZkM2M2NjQtMzQ4Zi00MmQxLWFlOWQtOGIwZDU4YTc1ZDhjIn0.REnwmPdE1z0j888hFqeo8mIipKcXhraUBLo9FvGuJ5eh5PenwqRquWeCPTTieAGIudjrKlADIEG8HF0Jaw8d2pjD2w3F5GNuDr0_hCWNQP9UwHuAURo35SXge6izTXfZsDw9SfWRF7GVbnexbgJFwBTE3htwhY4W-kWi1KNUYmvlpx74vpYwYBc4S8LWMMIKvEEO4nsIIKEikYlJC7HEpO-FNevKBAUIdRotX3vAglJmQqvGr0FCp2u3KyDv_oQYEp1PkvBd8kYKaLvcpdAuPG3oBCct8xSsim54IHDRtIWPrh6oGw-_dUf_-qeWWriHlMkZvKtQlq-CJA1va-orGg"
    email = "485515@muni.cz"
    first_name = "Adam"
    second_name = "TEST"

    # @task
    # def addUsers(self):
    #     wait_time = between(1, 2)
    #     try:
    #         user = self.create_user("BAND_MEMBER", "basic@basic.com")
    #         manager = self.create_user("MANAGER", self.email)
    #         print(user)
    #         print(manager)
    #         users = self.list_users()
    #         print(users)
    #     except:
    #         print("error occured")
    #         exit()

    @task
    def manageTour(self):
        wait_time = between(1, 2)
        # First you need to get oauth2 token
        # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
        # and sign in to muni page and select scopes, then copy token, email, first name and last name and past then here

        global counter
        if counter == 1:
            exit()

        try:
            manager = self.create_user("MANAGER", self.email)
            print(manager)
            band = self.create_band(manager)
            print(band)
            tour = self.create_tour(band)
            print(tour)
            tour_date1 = {"city": "Prague", "date": "2023-07-09", "venue": "O2 arena"}
            tour_date2 = {"city": "Budapest", "date": "2023-07-12", "venue": "Budapest park"}
            self.change_tour_dates(tour, [tour_date1, tour_date2])
        except:
            print("error occured")
            exit()

        counter = counter + 1


    def manageAlbum(self):
        wait_time = between(1, 2)
        # First you need to get oauth2 token
        # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
        # and sign in to muni page and select scopes, then copy token, email, fist name and last name and past then here
        scope_test_1 = True  # for this scenario you need scope_test_1

        global counter
        if counter == 1:
            exit()

        try:
            user = self.create_user("MANAGER")
            print(user)
            band = self.create_band(user)
            print(band)
            album = self.create_album(band)
            print(album)
            self.send_email_to_band_manager(band["id"])  # for testing purposes to your band
        except:
            exit()

        counter = counter + 1

    def list_users(self):
        response = self.client.get("http://core:8080/users",
                                   headers={"Authorization": ("Bearer " + self.token),
                                            "Content-Type": "application/json"})
        print("get all users " + str(response.status_code))
        print("get all users " + str(response.headers))
        print("get all users " + str(response.json()))
        return response.json()

    def create_user(self, type, email):
        response = self.client.post("http://users:8080/users-auth",
                                    json={"email": email, "userType": type,
                                          "firstName": self.first_name, "lastName": self.second_name},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create user " + str(response.status_code))
        print("create user " + str(response.headers))
        print("create user " + str(response.json()))
        return response.json()

    def create_band(self, user):
        response = self.client.post("http://core:8080/bands",
                                    json={"name": "My band", "genre": "ROCK",
                                          "image": ["67", "7", "89"], "manager": user},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create band " + str(response.status_code))
        print("create band " + str(response.headers))
        print("create band " + str(response.json()))
        return response.json()

    def create_tour(self, band):
        response = self.client.post("http://core:8080/tours",
                                    json={"name": "World Tour", "bandList": [band], "tourDates": []},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create tour " + str(response.status_code))
        print("create tour " + str(response.headers))
        print("create tour " + str(response.json()))
        return response.json()

    def change_tour_dates(self, tour, tour_dates):
        tour["tourDates"] = tour_dates
        response = self.client.put("http://core:8080/tours/" + str(tour["id"]),
                                   json=tour,
                                   headers={"Authorization": ("Bearer " + self.token),
                                            "Content-Type": "application/json"})
        print("update tour " + str(response.status_code))
        print("update tour " + str(response.headers))
        print("update tour " + str(response.json()))

    def create_album(self, band):
        response = self.client.post("http://core:8080/albums",
                                    json={"name": "My album", "releaseDate": "2017-01-13",
                                          "genre": "ROCK", "band": band},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create album " + str(response.status_code))
        print("create album " + str(response.headers))
        print("create album " + str(response.json()))
        return response.json()

    def send_email(self, email):
        response = self.client.post("http://email:8080/email",
                                    json={"subject": "Test email", "emailBody": "hello", "recipients": [email]},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("send_email_to_band_manager " + str(response.status_code))
        print("send_email_to_band_manager " + str(response.headers))
        print("send_email_to_band_manager " + str(response.json()))
