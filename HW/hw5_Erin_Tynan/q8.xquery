(: Version :)
xquery version "1.0";
(: Variable declarations. You can also declare functions, namespaces, etc. :)
declare variable $document as xs:string := "review.xml";

(: To run, just remove the comments characters for each FLWOR statement. :)

(: q8 For each Review of “restaurantid1”, display the Review’s RestaurantId, UserName, and Rating :)

<p name="multiple">
    <table border="1">
        <tr>
            <th>RestaurantId</th>
            <th>UserName</th>
            <th>Rating</th>
        </tr>
        {
        for $review in doc($document)/ReviewApplication/Reviews/Review
        let $restaurantid := $review/RestaurantId
        let $username := $review/UserName
        let $rating := $review/Rating
        where $review/RestaurantId/text() = "restaurantid1"
        return 
        <tr>
            <td>{$restaurantid/text()}</td>
            <td>{$username/text()}</td>
            <td>{$rating/text()}</td>
        </tr>
        }
    </table>
</p>