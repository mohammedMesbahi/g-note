package estm.dsic.jee.beans;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "note")
public class Note {
    @Id
    private Long id;
    private Date date_time;
    private String subject;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
    private Integer id_user;

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Integer getId_user() {
        return id_user;
    }

}
