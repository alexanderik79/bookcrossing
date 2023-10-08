package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "jenre")
    private String jenre;
    @ManyToOne()
    private User user;

    @Transient
    public List<String> listOfGenres() {
        var listOfGenres = new ArrayList<String>();
        listOfGenres.add("Роман");
        listOfGenres.add("Дитяча література");
        listOfGenres.add("Фантастика");
        listOfGenres.add("Детектив і трилер");
        listOfGenres.add("Жахи");
        listOfGenres.add("Наукова література");
        listOfGenres.add("Поезія");
        listOfGenres.add("Класика");
        listOfGenres.add("Подорожі і пригоди");
        listOfGenres.add("Кулінарія");
        listOfGenres.add("Наукова фантастика");
        listOfGenres.add("Саморозвиток і психологія");
        listOfGenres.add("Історична література");
        listOfGenres.add("Біографії і мемуари");

        return listOfGenres;
    }

}
