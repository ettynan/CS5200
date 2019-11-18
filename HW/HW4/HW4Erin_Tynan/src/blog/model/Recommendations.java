package blog.model;

/**
 * Recommendations class users make recommendations for restaurants
 */
public class Recommendations {
    protected int RecommendationId;
    protected String UserName;
    protected int RestaurantId;

    public Recommendations(int recommendationId, String userName, int restaurantId) {
        this.RecommendationId = recommendationId;
        this.UserName = userName;
        this.RestaurantId = restaurantId;
    }

    public int getRecommendationId() {
        return RecommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.RecommendationId = recommendationId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUser(String userName) {
        this.UserName = userName;
    }

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurants(int restaurantId) {
        this.RestaurantId = restaurantId;
    }
}
