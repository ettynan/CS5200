(: Version :)
xquery version "1.0";
(: Variable declarations. You can also declare functions, namespaces, etc. :)
declare variable $document as xs:string := "review.xml";

(: To run, just remove the comments characters for each FLWOR statement. :)

(: q9  For each Review, display the Review’s RestaurantId, UserName, Rating, and the count of the UserName’s Recommendations for the RestaurantId :)

<p name="multiple">
    <table border="1">
        <tr>
            <th>RestaurantId</th>
            <th>UserName</th>
            <th>Rating</th>
            <th>Recommendation Count</th>
        </tr>
        {
        for $review in doc($document)/ReviewApplication/Reviews/Review
        let $restaurantid := $review/RestaurantId
        let $username := $review/UserName
        let $rating := $review/Rating
        let $recommendation := doc($document)/ReviewApplication/Recommendations
        where $recommendation/RestaurantId/text() = "restaurantid1"
        return 
        <tr>
            <td>{$restaurantid/text()}</td>
            <td>{$username/text()}</td>
            <td>{$rating/text()}</td>
            <td>{$recommendation/text()}</td>
        </tr>
        }
    </table>
</p>