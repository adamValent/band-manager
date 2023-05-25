from locust import HttpUser, task, between

class test(HttpUser):

    @task
    def test(self):
        wait_time = between(1, 2)
        # First you need to get oauth2 token
        # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
        # and sign in to muni page and select scopes, then copy token, email, first name and last name and past then here
        token = "eyJraWQiOiJyc2ExIiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiI3ZTAyYTBhOS00NDZhLTQxMmQtYWQyYi05MGFkZDQ3YjBmZGQiLCJzdWIiOiI0OTMxODJAbXVuaS5jeiIsImFjciI6Imh0dHBzOi8vcmVmZWRzLm9yZy9wcm9maWxlL3NmYSIsInNjb3BlIjoidGVzdF8yIHRlc3RfMSBvcGVuaWQgZW1haWwgcHJvZmlsZSIsImF1dGhfdGltZSI6MTY4NTA0MDA5NiwiaXNzIjoiaHR0cHM6Ly9vaWRjLm11bmkuY3ovb2lkYy8iLCJleHAiOjE2ODUwNDY0MDgsImlhdCI6MTY4NTA0MjgwOCwiY2xpZW50X2lkIjoiN2UwMmEwYTktNDQ2YS00MTJkLWFkMmItOTBhZGQ0N2IwZmRkIiwianRpIjoiZTc0OWQ5MWYtNzcwOC00OGVmLTgwZTEtNmExNGY4ZTA4ODFiIn0.OUhn8Rr75rWaYVV0xu8KY13lC136CRmBycChu6oDHL4_xmsH-_F5jNtLpQOobImPM81KsoMsfjofwX9fPdTWuZdjbDfT3jKH4A6wbh69zZMhuv7jipcLq3_yvaWbv7ZKjwRgFwm4fg5fZyFa4Y_7fQ0ksl1cCCdNhjnyu2U844nvitnknOUI4tg-FBmrzFhlGhqFAWwfNDy6iTBvg1HwFNYwZK21SP0DLbZBNEXmPAAYz6VZ1_Ny1w3ctISljO1Vz_bYfR-CNITy9-lamjbKkmsOJusiTCbwUXUzf7L_5xIUeQ64aKZ44v68y5g93f_F_0VeWYk6NBzdE1t88ItIsA"
        email = "EMAIL"
        first_name = "NAME"
        second_name = "NAME"

        try:
            manager = self.create_user(token, email, first_name, second_name)
            print(manager)
            band = self.create_band(manager, token)
            print(band)
            tour = self.create_tour(band, token)
            print(tour)
            tour_date1 = create_tour_date("Prague", "2023-07-09", "O2 arena", tour, token)
            print(tour_date1)
            tour_date2 = create_tour_date("Budapest", "2023-07-12", "Budapest park", tour, token)
            print(tour_date2)
            change_tour_dates(tour, [tour_date1, tour_date2], token)


        except:
            exit()


    def create_user(self, token, email, first_name, second_name):
        response = self.client.post("http://users:8080/users-auth",
                                    json={"email": email, "userType": "MANAGER",
                                          "firstName": first_name, "lastName": second_name},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("create user " + str(response.status_code))
        print("create user " + str(response.headers))
        print("create user " + str(response.json()))
        return response.json()

    def create_band(self, user, token):
        response = self.client.post("http://core:8080/bands",
                                    json={"name": "My band", "genre": "ROCK",
                                          "image": ["67","7","89"], "manager": user},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("create band " + str(response.status_code))
        print("create band " + str(response.headers))
        print("create band " + str(response.json()))
        return response.json()

    def create_tour(self, band, token):
        response = self.client.post("http://core:8080/tours",
                                    json={"name": "World Tour", bands: [band], "tourDates": []},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("create tour " + str(response.status_code))
        print("create tour " + str(response.headers))
        print("create tour " + str(response.json()))
        return response.json()

    def create_tour_date(self, city, date, venue, tour, token):
        response = self.client.post("http://core:8080/tourDates",
                                    json={"city": city, "date": date, "venue": venue, "tour": tour},
                                    headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("create tour date " + str(response.status_code))
        print("create tour date " + str(response.headers))
        print("create tour date " + str(response.json()))
        return response

    def change_tour_dates(self, tour, tour_dates, token):
        tour["tourDates"] = tour_dates
        response = self.client.put("http://core:8080/tours/" + tour["id"],
                                   json=tour,
                                   headers={"Authorization": ("Bearer " + token), "Content-Type": "application/json"})
        print("update tour " + str(response.status_code))
        print("update tour " + str(response.headers))
        print("update tour " + str(response.json()))
