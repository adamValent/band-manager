from locust import HttpUser, task, between

counter = 0
class scenario(HttpUser):
    ######################################################################
    ######################################################################
    ######################################################################
    # First you need to get oauth2 token
    # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
    # and sign in to muni page and select scopes, then copy token, email, first name and last name and past then here
    token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4NTA0MDA5NiwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODUyNDAwNTEsImlhdCI6MTY4NTIzNjQ1MSwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiY2I5MDNiZTEtNmQ3OC00Yzg4LTllNDAtNjM1NGI3NTA3YzhlIn0.LbQCP2AJ3IMGPBZ6FHGIYxfqJJw8he9Bu1oDBEYEavkNBTtKreud0Qo8g46SUmqLYAVmlddR6jTQTK2bkBzWFvCXdmswCmmIV0XjNKK3-3ioAT0k3yvPvMAfVwrsVSpSyh1cUQ_BQ5_pdovtTO81zEPxLl96dYv-w7rIes33CDMyZOzvoLB1iZuyFNmLJqdU94QznXSS2ADinlMpa0wI_B-7V2MYPO_9zttP9v3XzZNuexwx6Gp6bWL8mHFI1Uk8z_1fN2w8SbNHLfothEu7sF1CAIrPa6Hu3ozXt9NkbnmDPyQBx4MYoYQ2uv3O9jJOSH9uwTzyhXKS_DjdziTm8A"
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
            manager_module_user = self.create_user("MANAGER")
            print(manager_module_user)
            print("\n\nScenario 01 - successful\n\n")

            print("\n\nScenario 01.1 - get user\n\n")
            manager = self.get_user(manager_module_user["email"])
            print(manager)
            print("\n\nScenario 01.1 - successful\n\n")

            print("\n\nScenario 02 - create band\n\n")
            band = self.create_band(manager)
            print(band)
            print("\n\nScenario 02 - successful\n\n")

            print("\n\nScenario 02.1 - list all user without band\n\n")
            all_users = self.list_all_users_without_band()
            print(all_users)
            print("\n\nScenario 02.1 - successful\n\n")

            print("\n\nScenario 02.2 - create invitation\n\n")
            invitation = self.create_invitation(all_users[0], band)
            print(invitation)
            print("\n\nScenario 02.2 - successful\n\n")

            print("\n\nScenario 02.3 - update invitation\n\n")
            invitation["status"] = 1
            invitation_updated = self.update_invitation(invitation["id"], invitation)
            print(invitation_updated)
            print("\n\nScenario 02.3 - successful\n\n")

            print("\n\nScenario 02.4 - update band member\n\n")
            band["members"] = [invitation_updated["user"]]
            band = self.update_band(band)
            print(band)
            print("\n\nScenario 02.4 - successful\n\n")

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

    def get_user(self, email):
        response = self.client.get("http://core:8080/users/email/" + email,
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("get user " + str(response.status_code))
        print("get user " + str(response.headers))
        print("get user " + str(response.json()))
        if response.status_code != 200:
            raise Exception("get user " + str(response.status_code))
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

    def list_all_users_without_band(self):
        response = self.client.get("http://core:8080/users/withoutBand",
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("list all users " + str(response.status_code))
        print("list all users " + str(response.headers))
        print("list all users " + str(response.json()))
        if response.status_code != 200:
            raise Exception("list all users " + str(response.status_code))
        return response.json()

    def create_invitation(self, user, band):
        response = self.client.post("http://core:8080/invitations",
                                    json={"message": "Join us!", "status": 2, "dateReceived": "2023-05-20",
                                          "band": band, "user": user},
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("create invitation " + str(response.status_code))
        print("create invitation " + str(response.headers))
        print("create invitation " + str(response.json()))
        if response.status_code != 200:
            raise Exception("create invitation " + str(response.status_code))
        return response.json()

    def update_invitation(self, id, invitation):
        response = self.client.put("http://core:8080/invitations/" + str(id),
                                    json=invitation,
                                    headers={"Authorization": ("Bearer " + self.token),
                                             "Content-Type": "application/json"})
        print("update_invitation " + str(response.status_code))
        print("update_invitation " + str(response.headers))
        print("update_invitation " + str(response.json()))
        if response.status_code != 200:
            raise Exception("update_invitation " + str(response.status_code))
        return response.json()

    def update_band(self, band):
        response = self.client.put("http://core:8080/bands/" + str(band["id"]),
                                   json=band,
                                   headers={"Authorization": ("Bearer " + self.token),
                                            "Content-Type": "application/json"})
        print("update band " + str(response.status_code))
        print("update band " + str(response.headers))
        print("update band " + str(response.json()))
        if response.status_code != 200:
            raise Exception("update band " + str(response.status_code))
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
