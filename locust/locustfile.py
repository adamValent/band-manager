from locust import HttpUser, task


class HelloWorldUser(HttpUser):
    @task
    def hello_world(self):
        # Post request - data je predavani parametru v url
        # Headers funguje uplne stejne u obou, GET a dalsi requesty maji podobne parametry
        response = self.client.post("/posts", data=
        {
            "title": "Silence of the Lambs",
            "body": "Thriller Book",
            "userId": 1
        }, headers={"Content-type": "application/x-www-form-urlencoded"})
        # Vytahnuti status codu a dat
        print("Response status code:", response.status_code)
        print("Response text:", response.json())
        # Post request - json je predavani dat v body
        response = self.client.post("/posts", json=
        {
            "title": "Silence of the Lambs",
            "body": "Thriller Book",
            "userId": 1
        }, headers={"Content-type": "application/json"})
        print("Response status code:", response.status_code)
        print("Response text:", response.json())
