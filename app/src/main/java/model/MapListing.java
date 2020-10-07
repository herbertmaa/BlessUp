package model;

public class MapListing {


    private String address;
    public static MapListing[] testListings = {new MapListing("ADDRESS1"), new MapListing("ADDRESS2"), new MapListing("ADDRESS3")};


    public MapListing(String name){
        address = name;
    }

    public String getListing(){return address;}
}
