SELECT * FROM (
SELECT COUNT(*) AS WeatherCount FROM Weather) SubW, (
SELECT COUNT(*) AS LicenseCategoriesCount FROM LicenseCategories) SubLC, (
SELECT COUNT(*) AS LicenseCount FROM Licenses) SubL, (
SELECT COUNT(*) AS FoodLicenseCount FROM FoodLicenses) SubFL, (
SELECT COUNT(*) AS LiquorLicenseCount FROM LiquorLicenses) SubLL, (
SELECT COUNT(*) AS EstablishmentsCount FROM Establishments) SubE, (
SELECT COUNT(*) AS AddressesCount FROM Addresses) SubA, (
SELECT COUNT(*) AS LiquorEstablishmentsCount FROM LiquorEstablishments) SubLE, (
SELECT COUNT(*) AS EstablishmentsCount FROM FoodEstablishments) SubFE, (
SELECT COUNT(*) AS InspectionHistoryCount FROM InspectionHistory) SubIH, (
SELECT COUNT(*) AS ViolationHistoryCount FROM ViolationHistory) SubVH