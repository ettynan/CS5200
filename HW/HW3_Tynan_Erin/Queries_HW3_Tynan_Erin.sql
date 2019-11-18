
USE  Restaurant_Reviews;
SELECT * FROM Reviews;

# 1) What is the average review rating for each of the top 10 ranked restaurants? Include the
# restaurant name in the result set.
SELECT Restaurants.Name, Reviews.RestaurantID, AVG(rating) avg_rating
FROM Reviews
JOIN Restaurants
	ON Reviews.RestaurantID = Restaurants.RestaurantID
GROUP BY Reviews.RestaurantID, Restaurants.Name
ORDER BY avg_rating DESC
LIMIT 3;

#2) How many users have created more than one review?
SELECT UserName, COUNT(DISTINCT ReviewID) AS count
FROM Reviews
GROUP BY UserName
HAVING COUNT(DISTINCT ReviewID) > 1;

#3 What day of the week is most popular for making a reservation? Use the DAYOFWEEK()
# function on the ‘Start’ column
SELECT 
	DAYOFWEEK(Start) AS wd,
    COUNT(*) AS count
FROM Reservations
GROUP BY 
	wd
ORDER BY
	count DESC;

#4) Which UserNames have made multiple reservations at the same SitDownRestaurant?
SELECT Reservations.RestaurantID, Users.UserName
FROM Reservations
	JOIN Users
	ON Users.UserName = Reservations.UserName
GROUP BY Users.UserName, Reservations.RestaurantID
HAVING COUNT(*) > 1;

#5) Identify the number of credit cards per provider. The credit card provider is determined by the
# leading digit(s) of the CardNumber. 
SELECT 
	(SELECT Count(*)
    FROM CreditCards
    WHERE CardNumber LIKE '34%'
    OR CardNumber LIKE '37%') AS AmericanExpress,
    (SELECT Count(*)
    FROM CreditCards
    WHERE LEFT(CardNumber, 3) BETWEEN 644 AND 649
    OR CardNumber LIKE '6011%'
    OR CardNumber LIKE '65%') AS Discover,
	(SELECT Count(*)
	FROM CreditCards
	WHERE LEFT(CardNumber, 2) BETWEEN 51 AND 55) AS MasterCard,
    (SELECT Count(*)
	FROM CreditCards
	WHERE CardNumber LIKE '4%') AS Visa,
    (SELECT Count(*)
	FROM CreditCards
	WHERE CardNumber NOT LIKE '4%'
    AND CardNumber NOT LIKE '37%'
    AND CardNumber NOT LIKE '34%'
    AND CardNumber NOT LIKE '6011%'
    AND CardNumber NOT LIKE '65%'
    AND LEFT(CardNumber, 3) NOT BETWEEN 644 AND 649 
    AND LEFT(CardNumber, 2) NOT BETWEEN 51 AND 55) AS NA
    ;

#6) What is the total number of active restaurants for each type of restaurant
# (SitDownRestaurant, TakeOutRestaurant, FoodCartRestaurant)?
SELECT  
	(SELECT Count(*) 
	FROM SitDownRestaurants 
		JOIN Restaurants
		ON SitDownRestaurants.RestaurantId = Restaurants.RestaurantId
	WHERE Active = TRUE) as SitDown,
	(SELECT Count(*) 
	FROM TakeOutRestaurants 
		JOIN Restaurants
		ON TakeOutRestaurants.RestaurantId = Restaurants.RestaurantId
	WHERE Active = TRUE) as TakeOut,
	(SELECT Count(*) 
	FROM FoodCartRestaurants 
		JOIN Restaurants
		ON FoodCartRestaurants.RestaurantId = Restaurants.RestaurantId
	WHERE Active = TRUE) as FoodCart;

#7) Which UserNames have not created a review, nor created a recommendation, nor created a
# reservation? In other words, users that have not created any of the following: reviews,
# recommendations, reservations.

SELECT UserName
FROM Users
WHERE NOT EXISTS
	(
	SELECT Users.UserName 
	FROM Reviews, Recommendations, Reservations 
	WHERE Users.UserName = Reviews.UserName 
	OR Users.UserName = Recommendations.UserName
	OR Users.UserName = Reservations.UserName
	);

#8) What is the ratio of the total number of recommendations to the total number of reviews?
SELECT
	(
	SELECT Count(*) 
		FROM Recommendations
	)/(
	SELECT Count(*) 
		FROM Reviews);
    
#9) Of the users that have created a reservation at a SitDownRestaurant, what is the percentage
#that the user has recommended that SitDownRestaurant?
SELECT
	(
    SELECT COUNT(DISTINCT UserName)
	FROM Recommendations
    WHERE Recommendation = TRUE
    )/(
    SELECT COUNT(DISTINCT UserName)
	FROM Reservations
    ) *100;

#10) Which UserNames have created more than twice the number of recommendations than
#number of reviews? Also take into account users that have not created recommendations or
#reviews -- if a user has create one recommendation but zero reviews, then that user should be
#included in the result set. 
SELECT Users.UserName,
	IF(RECS.UserName IS NULL, 0, RECS.REC_Count) AS REC_Count_Subtotal,
	IF(REVIEWS.UserName IS NULL, 0, REVIEWS.REVIEW_Count) AS REVIEW_Count_Subtotal
FROM Users 
	LEFT OUTER JOIN (
		SELECT UserName, COUNT(*) AS REC_Count
		FROM Recommendations
		GROUP BY UserName) AS RECS
	ON Users.UserName = RECS.UserName
	LEFT OUTER JOIN (
		SELECT UserName, COUNT(*) AS REVIEW_Count
		FROM Reviews
		GROUP BY UserName) AS REVIEWS
	ON Users.UserName = REVIEWS.UserName
WHERE 
	IF(RECS.UserName IS NULL,0, RECS.REC_Count) >= 2 * IF(REVIEWS.UserName IS NULL, 0, REVIEWS.REVIEW_Count);

	


    


