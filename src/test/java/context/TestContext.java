package context;

import models.UserData;
import models.ListingData;

public class TestContext {

    private UserData user;
    private ListingData listing;
    private String listingName;

    public TestContext() {
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public ListingData getListing() {
        return listing;
    }

    public void setListing(ListingData listing) {
        this.listing = listing;
        if (listing != null) {
            this.listingName = listing.getName();
        }
    }

    public void clear() {
        user = null;
        listing = null;
        listingName = null;
    }
}