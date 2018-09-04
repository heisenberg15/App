package com.example.fergie.application.models;

public class PostsModel
{
    private String name, message, img;

    public PostsModel(String name, String message, String img)
    {
        this.name = name;
        this.message = message;
        this.img = img;
    }

    public String getName()
    {
        return name;
    }

    public String getMessage()
    {
        return message;
    }

    public String getImg()
    {
        return img;
    }
}
