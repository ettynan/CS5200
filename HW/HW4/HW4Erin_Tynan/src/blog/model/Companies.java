package blog.model;

/**
 * Company class depicting Companies that may or may not own Restaurants
 */
public class Companies {
    protected String CompanyName;
    protected String About;

    public Companies(String companyName, String about) {
        this.CompanyName = companyName;
        this.About = about;
    }


    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        this.CompanyName = companyName;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        this.About = about;
    }
}
