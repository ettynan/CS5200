USE INSPECTIONS;
-- Are there more licensed food or liquor establishments in Boston?
SELECT * FROM (
SELECT Count(FoodEstablishmentKey) AS food_count
FROM FoodEstablishments fe
JOIN Establishments es
ON fe.EstablishmentFK = es.EstablishmentKey
JOIN Licenses l
ON l.LicenseKey = es.LicenseFK
WHERE l.LicenseStatus = 'Active'
) food,
(
SELECT Count(LiquorEstablishmentKey) AS liquor_count
FROM LiquorEstablishments le
JOIN Establishments es
ON le.EstablishmentFK = es.EstablishmentKey
JOIN Licenses l
ON l.LicenseKey = es.LicenseFK
WHERE l.LicenseStatus = 'Active'
) liquor;
-- What percentage of food establishments didn't pass inspection in a given year?
SELECT IF(Failures IS NULL, 0, Failures)/Total * 100 AS Percent, ty FROM (
SELECT Count(InspectionResult) AS Failures, YEAR(InspectionDate) AS fy
FROM InspectionHistory
WHERE InspectionResult != 'HE_Pass' -- the wheres make these two very differnt tables
GROUP BY fy
ORDER BY fy
) failure_table
RIGHT JOIN ( -- joins whether or not there are failures that year
SELECT COUNT(InspectionResult) AS Total, YEAR(InspectionDate) AS ty
FROM InspectionHistory
GROUP BY ty
ORDER BY ty) total_table
ON failure_table.fy = total_table.ty;
-- What percentage of food establishments have a violation of any type in a given year?
SELECT IF(Failures IS NULL, 0, Failures)/Total * 100 AS Percent, ty FROM (
SELECT Count(ViolationHistory.ViolationStatus) AS Failures,
YEAR(ViolationHistory.ViolationDate) as fy
FROM ViolationHistory
JOIN FoodEstablishments
ON ViolationHistory.EstablishmentName = FoodEstablishments.EstablishmentName
WHERE ViolationHistory.ViolationStatus = 'Fail'
GROUP BY fy
) failure_table
RIGHT JOIN (
SELECT Count(ViolationHistory.ViolationStatus) AS Total,
YEAR(ViolationHistory.ViolationDate) as ty
FROM ViolationHistory
JOIN FoodEstablishments
ON ViolationHistory.EstablishmentName = FoodEstablishments.EstablishmentName
GROUP BY ty
ORDER BY ty) total_table
ON failure_table.fy = total_table.ty;
-- What Food Establishments are also Liquor Establishments?
SELECT FoodEstablishments.EstablishmentName
FROM FoodEstablishments,
(
SELECT * FROM LiquorEstablishments
JOIN Establishments ON EstablishmentFK = EstablishmentKey) table1
WHERE FoodEstablishments.EstablishmentName = table1.EstablishmentName;
# Business insight: Of establishments with inactive licenses, how many had failed any inspection?
SELECT (
(SELECT COUNT(*) # 2690 inactive with fails
FROM (
SELECT InspectionHistory.EstablishmentName
FROM Licenses
INNER JOIN Establishments
ON Licenses.LicenseKey = Establishments.LicenseFK
INNER JOIN FoodEstablishments
ON Establishments.EstablishmentName = FoodEstablishments.EstablishmentName
INNER JOIN InspectionHistory
ON FoodEstablishments.EstablishmentName = InspectionHistory.EstablishmentName
WHERE LicenseStatus LIKE 'Inactive'
AND (InspectionResult LIKE 'HE_Fail'
OR InspectionResult LIKE 'HE_FailExt')
GROUP BY InspectionHistory.EstablishmentName
LIMIT 600000
) AS CNT_INACTIVE_W_FAILS) / # divided by
(SELECT COUNT(*) # 3792 total inactive
FROM (
SELECT FoodEstablishments.EstablishmentName
FROM Licenses
INNER JOIN Establishments
ON Licenses.LicenseKey = Establishments.LicenseFK
INNER JOIN FoodEstablishments
ON Establishments.EstablishmentName = FoodEstablishments.EstablishmentName
WHERE LicenseStatus LIKE 'Inactive'
GROUP BY FoodEstablishments.EstablishmentName
LIMIT 600000
) AS CNT_INACTIVE)
) * 100 AS INACTIVE_W_FAILS_PERCENTAGE;
# Business insight: Are there more inspections during a certain week of the month?
SELECT COUNT(*) AS CNT_WEEK,
CASE
WHEN INSPECTION_DAY <= 7 THEN 'Week 1 - 7 days'
WHEN INSPECTION_DAY BETWEEN 8 AND 14 THEN 'Week 2 - 7 days'
WHEN INSPECTION_DAY BETWEEN 15 AND 21 THEN 'Week 3 - 7 days'
WHEN INSPECTION_DAY BETWEEN 22 AND 28 THEN 'Week 4 - 7 days'
WHEN INSPECTION_DAY > 28 THEN 'Week 5 - 3 days'
ELSE 'NA'
END AS INSPECTION_WEEK
FROM (SELECT DAYOFMONTH(InspectionDate) AS INSPECTION_DAY FROM InspectionHistory) AS TMP_TABLE_FOR_CASE_ALIAS
GROUP BY INSPECTION_WEEK
ORDER BY CNT_WEEK DESC;
# Business Insight: And are there more or fewer failed inspections during high volume or low volume inspection weeks of the month?
SELECT COUNT(*) CNT_RESULTS,
CASE
WHEN INSPECTION_DAY <= 7
AND InspectionResult LIKE 'HE_Pass' THEN 'Week 1 PASS'
WHEN INSPECTION_DAY <= 7
AND (InspectionResult LIKE 'HE_FailExt'
OR InspectionResult LIKE 'HE_FAIL') THEN 'Week 1 FAIL'
WHEN INSPECTION_DAY BETWEEN 8 AND 14
AND InspectionResult LIKE 'HE_Pass' THEN 'Week 2 PASS'
WHEN INSPECTION_DAY BETWEEN 8 AND 14
AND (InspectionResult LIKE 'HE_FailExt'
OR InspectionResult LIKE 'HE_FAIL') THEN 'Week 2 FAIL'
WHEN INSPECTION_DAY BETWEEN 15 AND 21
AND InspectionResult LIKE 'HE_Pass' THEN 'Week 3 PASS'
WHEN INSPECTION_DAY BETWEEN 15 AND 21
AND (InspectionResult LIKE 'HE_FailExt'
OR InspectionResult LIKE 'HE_FAIL') THEN 'Week 3 FAIL'
WHEN INSPECTION_DAY BETWEEN 22 AND 28
AND InspectionResult LIKE 'HE_Pass' THEN 'Week 4 PASS'
WHEN INSPECTION_DAY BETWEEN 22 AND 28
AND (InspectionResult LIKE 'HE_FailExt'
OR InspectionResult LIKE 'HE_FAIL') THEN 'Week 4 FAIL'
WHEN INSPECTION_DAY > 28
AND InspectionResult LIKE 'HE_Pass' THEN 'Week 5 PASS'
WHEN INSPECTION_DAY > 28
AND (InspectionResult LIKE 'HE_FailExt'
OR InspectionResult LIKE 'HE_FAIL') THEN 'Week 5 FAIL'
ELSE 'NA'
END AS INSPECTION_WEEK
FROM (SELECT DAYOFMONTH(InspectionDate) AS INSPECTION_DAY, InspectionResult FROM
InspectionHistory) AS WEEK_RESULT_TABLES
GROUP BY INSPECTION_WEEK
ORDER BY CNT_RESULTS DESC;
# How many inspections occurred for each restaurant on average between 2013 and 2018?
SELECT EstablishmentName, COUNT(*)/6 as AverageInspections FROM inspectionhistory
WHERE Year(InspectionDate)>2012 AND Year(InspectionDate)<2019
GROUP BY EstablishmentName
;
#########Monthly evaluation of inspections#########
## Classify inspections by month: What is the inspections failure percentage for each month?
SELECT * FROM (
SELECT 1TC, 1FC, ROUND(1FC/1TC*100) as 1FP FROM (
SELECT COUNT(*) as 1TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=1) total, (
SELECT COUNT(*) as 1FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=1 AND InspectionResult != 'HE_Pass') fail) JAN, (
SELECT 2TC, 2FC, ROUND(2FC/2TC*100) as 2FP FROM (
SELECT COUNT(*) as 2TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=2) total, (
SELECT COUNT(*) as 2FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=2 AND InspectionResult != 'HE_Pass') fail) FEB, (
SELECT 3TC, 3FC, ROUND(3FC/3TC*100) as 3FP FROM (
SELECT COUNT(*) as 3TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=3) total, (
SELECT COUNT(*) as 3FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=3 AND InspectionResult != 'HE_Pass') fail) MAR, (
SELECT 4TC, 4FC, ROUND(4FC/4TC*100) as 4FP FROM (
SELECT COUNT(*) as 4TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=4) total, (
SELECT COUNT(*) as 4FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=4 AND InspectionResult != 'HE_Pass') fail) APR, (
SELECT 5TC, 5FC, ROUND(5FC/5TC*100) as 5FP FROM (
SELECT COUNT(*) as 5TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=5) total, (
SELECT COUNT(*) as 5FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=5 AND InspectionResult != 'HE_Pass') fail) MAY, (
SELECT 6TC, 6FC, ROUND(6FC/6TC*100) as 6FP FROM (
SELECT COUNT(*) as 6TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=6) total, (
SELECT COUNT(*) as 6FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=6 AND InspectionResult != 'HE_Pass') fail) JUN, (
SELECT 7TC, 7FC, ROUND(7FC/7TC*100) as 7FP FROM (
SELECT COUNT(*) as 7TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=7) total, (
SELECT COUNT(*) as 7FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=7 AND InspectionResult != 'HE_Pass') fail) JUL, (
SELECT 8TC, 8FC, ROUND(8FC/8TC*100) as 8FP FROM (
SELECT COUNT(*) as 8TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=8) total, (
SELECT COUNT(*) as 8FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=8 AND InspectionResult != 'HE_Pass') fail) AUG, (
SELECT 9TC, 9FC, ROUND(9FC/9TC*100) as 9FP FROM (
SELECT COUNT(*) as 9TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=9) total, (
SELECT COUNT(*) as 9FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=9 AND InspectionResult != 'HE_Pass') fail) SEP, (
SELECT 10TC, 10FC, ROUND(10FC/10TC*100) as 10FP FROM (
SELECT COUNT(*) as 10TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=10) total, (
SELECT COUNT(*) as 10FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=10 AND InspectionResult != 'HE_Pass') fail) OCT, (
SELECT 11TC, 11FC, ROUND(11FC/11TC*100) as 11FP FROM (
SELECT COUNT(*) as 11TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=11) total, (
SELECT COUNT(*) as 11FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=11 AND InspectionResult != 'HE_Pass') fail) NOV, (
SELECT 12TC, 12FC, ROUND(12FC/12TC*100) as 12FP FROM (
SELECT COUNT(*) as 12TC FROM inspectionhistory
WHERE MONTH(InspectionDate)=12) total, (
SELECT COUNT(*) as 12FC FROM inspectionhistory
WHERE MONTH(InspectionDate)=12 AND InspectionResult != 'HE_Pass') fail) DECEM
;
######### Followup #########
## Follow-up: For the months with the two highest inspection failure percentages (December and January),
## what were the average weather conditions for the days that inspections failed and in how many instances
## was it snowing or raining or both (so not “None” weather event)?
SELECT AVG(prep1.TempHighInF), AVG(prep1.TempLowInF), AVG(prep1.HumidityHighPercentage),
AVG(prep1.HumidityLowPercentage), AVG(prep1.WindHighInMpf),
AVG(prep1.WindgustHighInMpf), prep2.CNT as PositiveEventCount FROM (
SELECT DISTINCT WeatherDate, TempHighInF, TempAverageInF, TempLowInF, HumidityHighPercentage,
HumidityAveragePercentage, HumidityLowPercentage,
WindHighInMpf, WindAverageInMpf, WindGustHighInMpf, SnowFallInInches, PrecipitationInInches,
Events
FROM weather INNER JOIN inspectionhistory on WeatherDate=InspectionDate
WHERE InspectionResult != 'HE_Pass' AND (MONTH(InspectionDate)=1 OR
MONTH(InspectionDate)=12)) prep1, (
SELECT COUNT(*) as CNT
FROM weather INNER JOIN inspectionhistory on WeatherDate=InspectionDate
WHERE InspectionResult != 'HE_Pass' AND (MONTH(InspectionDate)=1 OR MONTH(InspectionDate)=12)
AND Events != 'None') prep2
;
# What is the passing percentage on average for all restaurants?
SELECT passtable.PassCount/totaltable.TotalCount *100 as TotalPassPercentage FROM (
SELECT COUNT(*) as PassCount FROM InspectionHistory
WHERE InspectionResult = 'HE_Pass') passtable, (
SELECT COUNT(*) as TotalCount FROM InspectionHistory
WHERE InspectionResult != 'NULL'
) totaltable
;
# And which restaurants have the top 100 inspections passing percentage?
SELECT passtable.EstablishmentName, passtable.PassCount/totaltable.TotalCount *100 as PassPercentage FROM (
SELECT EstablishmentName, COUNT(*) as PassCount FROM InspectionHistory
WHERE InspectionResult = 'HE_Pass'
GROUP BY EstablishmentName) passtable JOIN (
SELECT EstablishmentName, COUNT(*) as TotalCount FROM InspectionHistory
GROUP BY EstablishmentName
HAVING COUNT(*)>5 #Because a lot of establishments have only 1 inspection and that gives them a
# 100% pass in some cases, we need to consider establishments that had a good amount of inspections
) totaltable on passtable.EstablishmentName=totaltable.EstablishmentName
ORDER BY PassPercentage DESC
LIMIT 100
;
# For each inspection, how many violations happen on average and what is the average violation status
#(*,**,***)?
SELECT AVG(VPItable.VPI) as AverageViolationsPerInspection, AVL.AverageViolationLevel FROM (
SELECT EstablishmentName, InspectionHistoryFK, COUNT(*) as VPI FROM violationhistory
##VPI stands for ViolationsPerInspection
GROUP BY InspectionHistoryFK) VPItable, (
SELECT AVG(CHAR_LENGTH(ViolationLevel)) as AverageViolationLevel FROM ViolationHistory
WHERE ViolationLevel='*' OR ViolationLevel='**' OR ViolationLevel='***') AVL