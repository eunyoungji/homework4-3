package com.example.homework4.dto;


import lombok.Getter;

@Getter
public class PostRequestDto {
    private String contents;
    private String title;

    public String getTitle() {
        return this.title;
    }
    public String getContents() {
        return this.contents;
    }



}
