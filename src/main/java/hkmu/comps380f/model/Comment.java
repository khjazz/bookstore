package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    @ColumnDefault("random_uuid()")
    private UUID id;

    private String content;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // getters and setters of all properties
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

