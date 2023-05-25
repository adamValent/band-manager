from locust import HttpUser, task, between

class test(HttpUser):

    @task
    def test(self):
        wait_time = between(1, 2)
        # First you need to get oauth2 token
        # You get this token by running spring boot app test-client(in root directory), then go to localhost:8089
        # and sign in to muni page and select scopes, then copy token, email, first name and last name and past then here
        token = "TOKEN"
        email = "EMAIL"
        first_name = "NAME"
        second_name = "NAME"

        try:
            manager = self.create_user()
            print(user)
            band = self.create_band(manager)
            print(band)
            tour = self.create_tour(band)
            print(tour)
            tour_date1 = create_tour_date("Prague", "2023-07-09", "O2 arena", tour)
            print(tour_date1)
            tour_date2 = create_tour_date("Budapest", "2023-07-12", "Budapest park", tour)
            print(tour_date2)
            change_tour_dates(tour, [tour_date1, tour_date2])


        except:
            exit()

    @task
    def create_user(self):
        response = self.client.post("http://users:8084/users-auth",
                                    json={"email": self.email, "userType": "MANAGER",
                                          "firstName": self.first_name, "lastName": self.second_name},
                                    headers={"Authorization": ("Bearer " + self.token), "Content-Type": "application/json"})
#         print("create user " + str(response.status_code))
#         print("create user " + str(response.headers))
#         print("create user " + str(response.json()))
        return response.json()

    @task
    def create_band(self, user):
        response = self.client.post("http://core:8080/bands",
                                    json={"name": "My band", "genre": "ROCK",
                                          "image": ["67","7","89"], "manager": user},
                                    headers={"Authorization": ("Bearer " + self.token), "Content-Type": "application/json"})
#         print("create band " + str(response.status_code))
#         print("create band " + str(response.headers))
#         print("create band " + str(response.json()))
        return response.json()

    @task
    def create_tour(self, band):
        response = self.client.post("http://core:8080/tours",
                                    json={"name": "World Tour", bands: [band], "tourDates": []},
                                    headers={"Authorization": ("Bearer " + self.token), "Content-Type": "application/json"})
        print("create tour " + str(response.status_code))
        print("create tour " + str(response.headers))
        print("create tour " + str(response.json())
        return response.json()

    def create_tour_date(self, city, date, venue, tour):
        response = self.client.post("http://core:8080/tourDates",
                                    json={"city": city, "date": date, "venue": venue, "tour": tour},
                                    headers={"Authorization": ("Bearer " + self.token), "Content-Type": "application/json"})
        print("create tour date " + str(response.status_code))
        print("create tour date " + str(response.headers))
        print("create tour date " + str(response.json())
        return response

    def change_tour_dates(self, tour, tour_dates):
        tour["tourDates"] = tour_dates
        response = self.client.put("http://core:8080/tours/" + tour["id"],
                                   json=tour)
#         print("update tour " + str(response.status_code))
#         print("update tour " + str(response.headers))
#         print("update tour " + str(response.json())
