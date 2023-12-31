package cz.muni.fi.pa165.moduleuser.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_auth")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "email", length = 60)
    private String email;
    @NotNull
    @Column(name = "oauth_id", unique = true)
    private String oauthId;

    public User() {
    }

    public User(Long id, String email, String oauthId) {
        this.id = id;
        this.email = email;
        this.oauthId = oauthId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        User user = (User) o;

        return Objects.equals(id, user.id) &&
               Objects.equals(email, user.email) &&
               Objects.equals(oauthId, user.oauthId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, oauthId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", oauthId='" + oauthId + '\'' +
                '}';
    }
}
