from locust import HttpUser, task, between

counter = 0
class scenario(HttpUser):
    ######################################################################
    ######################################################################
    ######################################################################
    # First you need to get oauth2 token
    # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
    # and sign in to muni page and select scopes, then copy token, email, first name and last name and past then here
    token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4NTA0MDA5NiwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODUyMjg1OTAsImlhdCI6MTY4NTIyNDk5MCwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiYTdkZDUwOTYtNjU1OS00ZThlLWEwYTctNjgyNjQyNjZjNWNiIn0.dX73r7oyUjmmmLNvH8cxF3q5dKQM08Pc_eTjvZ4KzVk06lR0ZQmQIr928QlwN_Big5T-MceUsm-h8S9mzUP515q-eyCoakMdBLE9Fc8i4-n5DF-2ivKxJtRnpmishGKjnoJQutMXvia-voydTJlfa-RenU3G7b9EzS3XU77qg4fK_ekv7mAIyVC_HnKeh7BLIUSzMJhnu5cT_J-kQM7QTOV2X63cX-RfJU4PmFJCZJr0UZLK3M4BmV0IFAmfMahsKqR-n0u02J3XNnFu5ABAZXu5bdModdhk5nyiLw18EpGnbVI0em97vvanL13GS_UuPwtDoVWBs5xC1JSVsBJldw"
    email = "493182@mail.muni.cz" # UCO@mail.muni.cz
    first_name = "Patrik"
    second_name = "TEST"
    ######################################################################
    ######################################################################
    ######################################################################


    @task
    def scenarioManager(self):
        wait_time = between(1, 2)
        global counter
        if counter == 1:
            exit()

        try:
            print("\n\nScenario 01 - create user\n\n")
            manager = self.create_user("MANAGER")
            print(manager)
            print("\n\nScenario 01 - successful\n\n")

            print("\n\nScenario 02 - create band\n\n")
            band = self.create_band(manager)
            print(band)
            print("\n\nScenario 02 - successful\n\n")

            print("\n\nScenario 03 - create tour\n\n")
            tour = self.create_tour(band)
            print(tour)
            print("\n\nScenario 03 - successful\n\n")

            print("\n\nScenario 04 - update tour\n\n")
            tour_date1 = {"city": "Prague", "date": "2023-07-09", "venue": "O2 arena"}
            tour_date2 = {"city": "Budapest", "date": "2023-07-12", "venue": "Budapest park"}
            self.change_tour_dates(tour, [tour_date1, tour_date2])
            print("\n\nScenario 04 - successful\n\n")

            print("\n\nScenario 05 - create album\n\n")
            album = self.create_album(band)
            print(album)
            print("\n\nScenario 05 - successful\n\n")

            print("\n\nScenario 06 - create songs\n\n")
            song1 = self.create_songs("My song1", album["id"])
            print(song1)
            song2 = self.create_songs("My song2", album["id"])
            print(song2)
            print("\n\nScenario 06 - successful\n\n")

            print("\n\nScenario 07 - send email to manager\n\n")
            self.send_email_to_manager(band["id"])
            print("\n\nScenario 07 - successful\n\n")

        except Exception as e:
            print("\nERROR\n")
            print(e)
            exit()

        counter = counter + 1

    def create_user(self, type):
        response = self.client.post("http://users:8080/users-auth",
                                    json={"email": self.email, "userType": type,
                                          "firstName": self.first_name, "lastName": self.second_name},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create user " + str(response.status_code))
        print("create user " + str(response.headers))
        print("create user " + str(response.json()))
        if response.status_code != 200:
            raise Exception("create user " + str(response.status_code))
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
        if response.status_code != 200:
            raise Exception("create band " + str(response.status_code))
        return response.json()

    def create_tour(self, band):
        response = self.client.post("http://core:8080/tours",
                                    json={"name": "World Tour", "bandList": [band], "tourDates": []},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create tour " + str(response.status_code))
        print("create tour " + str(response.headers))
        print("create tour " + str(response.json()))
        if response.status_code != 200:
            raise Exception("create tour " + str(response.status_code))
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
        if response.status_code != 200:
            raise Exception("update tour " + str(response.status_code))

    def create_album(self, band):
        response = self.client.post("http://core:8080/albums",
                                    json={"name": "My album", "releaseDate": "2017-01-13",
                                          "genre": "ROCK", "band": band},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create album " + str(response.status_code))
        print("create album " + str(response.headers))
        print("create album " + str(response.json()))
        if response.status_code != 200:
            raise Exception("create album " + str(response.status_code))
        return response.json()

    def create_songs(self, title, album_id):
        response = self.client.post("http://core:8080/songs",
                                    json={"title": title, "releaseDate": "2017-01-13",
                                          "genre": "ROCK", "albumId": album_id, "duration": "PT3M"},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create album " + str(response.status_code))
        print("create album " + str(response.headers))
        print("create album " + str(response.json()))
        if response.status_code != 200:
            raise Exception("create song " + str(response.status_code))
        return response.json()

    def send_email_to_manager(self, bandId):
        response = self.client.post("http://email:8080/email/band/" + str(bandId) + "/manager",
                                    json={"subject": "Test email", "emailBody": "hello"},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("send_email_to_band_manager " + str(response.status_code))
        print("send_email_to_band_manager " + str(response.headers))
        if response.status_code != 200:
            raise Exception("send email to manager " + str(response.status_code))
