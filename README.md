# YouBand Manager

## About project

-   **Name**: Music Band Manager
-   **Technologies**: Java 17, Maven, Spring Boot
-   **Developers**:
    -   Oskar Adam Válent @xvalent _Project Leader_
    -   Jakub Šušlík @xsuslik
    -   Patrik Čangel @xcangel
    -   Tomáš Koscielniak @xkosciel
-   **Assignment**: - The web application allows one music band to manage their activities. Managers can create a new band and hire team members from a catalog. They can set for the band the musical style (e.g., rock, pop, etc.), a name, and a logo. Band managers can add new albums and songs to a band. Each Song has a name and a duration. Managers can also schedule tours for the bands regarding dates and cities visited.
    Team members can log in to the system and see the offers made by the band managers. They can accept or reject being part of a band. After they are part of a band, they can see all the planned activities and the profiles of the other band members.

# Project Description

Music band manager is a simple system for managing music bands.

## Roles

The system has two authorization roles - **Manager** and **Band Member**.

-   Manager is a role for managing bands, their members, albums, songs, and band activities.
-   Band Member role is for accepting band offers and viewing band information after accepting an offer.

## Entities

-   **BaseEntity** - an abstract entity that is extended by every entity, has an ID property
-   **User** - an abstract entity that is extended by Manager and BandMember entities
-   **Manager** - entity representing a manager
-   **BandMemeber** - entity representing a band member
-   **Band** - entity representing a band
-   **Album** - entity representing an album
-   **Song** - entity representing a song
-   **Activity** - entity representing a band activity such as a tour

## Use case diagram

![alt text](diagrams/use-case-diagram.png "Use case diagram")

## Class diagram

![alt text](diagrams/class-diagram.png "Use case diagram")
