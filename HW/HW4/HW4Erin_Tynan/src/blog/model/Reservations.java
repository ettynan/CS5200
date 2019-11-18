package blog.model;

import java.util.Date;

/**
 * Reservations class users make reservations for sit down restaurants
 */
public class Reservations {
    protected int ReservationId;
    protected Date Start;
    protected Date End;
    protected int Size;
    protected String UserName;
    protected int RestaurantId;

    public Reservations(int reservationId, Date start, Date end, int size, String userName,
                        int restaurantId) {
        this.ReservationId = reservationId;
        this.Start = start;
        this.End = end;
        this.Size = size;
        this.UserName = userName;
        this.RestaurantId = restaurantId;
    }

    public int getReservationId() {
        return ReservationId;
    }

    public void setReservationId(int reservationId) {
        this.ReservationId = reservationId;
    }

    public Date getStart() {
        return Start;
    }

    public void setStart(Date start) {
        this.Start = start;
    }

    public Date getEnd() {
        return End;
    }

    public void setEnd(Date end) {
        this.End = end;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        this.Size = size;
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
