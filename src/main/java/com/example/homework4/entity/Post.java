package com.example.homework4.entity;


import com.example.homework4.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
@Entity
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    private List<Comment> comments;


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.nickname = requestDto.getNickname();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.nickname = requestDto.getNickname();
    }

//    public Long getId() {
//        return this.id;
//    }
//
//    public String getContents() {
//        return this.contents;
//    }
//
//    public String getTitle() {
//        return this.title;
//    }


}
