package com.mycompany.project;
import java.io.*;

public class TravelDeal implements Serializable
{
	private String title;
	private String description;
	private String id;
	private String price;
	private String imageUrl;
    private String ImageName;
	public TravelDeal(){
		
	}

	public TravelDeal(String title, String description,String price, String imageUrl)
	{
		this.setTitle(title);
		this.setDescription(description);;
		this.setId(id);
		this.setPrice(price);
		this.setImageUrl(imageUrl);;
		this.setImageName(ImageName);
		
	}

	public void setImageName(String imageName)
	{
		ImageName = imageName;
	}

	public String getImageName()
	{
		return ImageName;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getPrice()
	{
		return price;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}
}
