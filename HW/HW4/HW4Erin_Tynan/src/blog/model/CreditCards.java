package blog.model;


import java.util.Date;


/**
 * CreditCard concrete class depicting user's creditcards
 */
public class CreditCards {
    protected long CardNumber;
    protected Date Expiration;
    protected String UserName;

    // This constructor can be used for reading records from MySQL, where we have all fields.
    public CreditCards(long cardNumber, Date expiration, String userName) {
        this.CardNumber = cardNumber;
        this.Expiration = expiration;
        this.UserName = userName;
    }

    // Given CardNumber, we can fetch the full CreditCard record.
    public CreditCards(long cardNumber) {
        this.CardNumber = cardNumber;
    }

    public long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.CardNumber = cardNumber;
    }

    public Date getExpiration() {
        return Expiration;
    }

    public void setExpiration(Date expiration) {
        this.Expiration = expiration;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUser(String userName) {
        this.UserName = userName;
    }
}
