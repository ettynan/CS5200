package food.dal;

import food.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interacts with {@link Weather} model for CRUD operations.
 * @author Clara Mae Wells
 */
public class WeatherDao {
	// Singleton pattern to limit instantiation to one object.
	protected ConnectionManager connectionManager;
	
	private static WeatherDao instance = null;
	
	protected WeatherDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static WeatherDao getInstance() {
		if(instance == null) {
			instance = new WeatherDao();
		}
		return instance;
	}
	
	/**
	 * Create a new Weather instance. 
	 * Runs an INSERT statement.
	 */
	public Weather create(Weather weather) throws SQLException {
		String insertWeather =
			"INSERT INTO Weather(WeatherDate,TempHighInF,TempAverageInF,TempLowInF,HumidityHighPercentage,HumidityAveragePercentage,HumidityLowPercentage,WindHighInMpf,WindAverageInMpf,"
			+ "WindGustHighInMpf,SnowFallInInches,PrecipitationInInches,Events) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Retrieve auto-generated key.
			insertStmt = connection.prepareStatement(insertWeather,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, new Timestamp(weather.getWeatherDate().getTime()));
			insertStmt.setInt(2, weather.getTempHighInF());
			insertStmt.setInt(3, weather.getTempAverageInF());
			insertStmt.setInt(4, weather.getTempLowInF());
			insertStmt.setInt(5, weather.getHumidityHighPercentage());
			insertStmt.setInt(6, weather.getHumidityAveragePercentage());
			insertStmt.setInt(7, weather.getHumidityLowPercentage());
			insertStmt.setInt(8, weather.getWindHighInMph());
			insertStmt.setInt(9, weather.getWindAverageInMph());
			insertStmt.setInt(10, weather.getWindGustHighInMph());
			insertStmt.setDouble(11, weather.getSnowFallInInches());
			insertStmt.setDouble(12, weather.getPrecipitationInInches());
			insertStmt.setString(13, weather.getEvents().name());
			insertStmt.executeUpdate();
			
			// Retrieve and set auto-generated key so it can be used by caller.
			resultKey = insertStmt.getGeneratedKeys();
			int weatherKey = -1;
			if(resultKey.next()) {
				weatherKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			weather.setWeatherKey(weatherKey);
			return weather;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	/**
	 * Get Weather record by weatherKey.
	 * Runs a SELECT statement and returns a single Weather instance.
	 */
	public Weather getWeatherByWeatherKey(int resultWeatherKey) throws SQLException {
		String selectWeather = "SELECT WeatherKey,WeatherDate,TempHighInF,TempAverageInF,TempLowInF,HumidityHighPercentage,HumidityAveragePercentage,HumidityLowPercentage,WindHighInMpf,WindAverageInMpf," + 
			"WindGustHighInMpf,SnowFallInInches,PrecipitationInInches,Events "
				+ "FROM Weather "
				+ "WHERE WeatherKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeather);
			selectStmt.setInt(1, resultWeatherKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Date weatherDate = new Date(results.getTimestamp("WeatherDate").getTime());
				int tempHighInF = results.getInt("TempHighInF");
				int tempAverageInF = results.getInt("TempAverageInF");
				int tempLowInF = results.getInt("TempLowInF");
				int humidityHighPercentage = results.getInt("HumidityHighPercentage");
				int humidityAveragePercentage = results.getInt("HumidityAveragePercentage");
				int humidityLowPercentage = results.getInt("HumidityLowPercentage");
				int windHighInMph = results.getInt("WindHighInMpf");
				int windAverageInMph = results.getInt("WindAverageInMpf");
				int windGustHighInMph = results.getInt("WindGustHighInMpf");
				double snowFallInInches = results.getDouble("SnowFallInInches");
				double precipitationInInches = results.getDouble("PrecipitationInInches");
				Weather.Events events = Weather.Events.valueOf(results.getString("Events"));
				
				Weather weather = new Weather(resultWeatherKey, weatherDate, tempHighInF, tempAverageInF, tempLowInF, humidityHighPercentage, humidityAveragePercentage, humidityLowPercentage,
						windHighInMph, windAverageInMph, windGustHighInMph, snowFallInInches, precipitationInInches, events);
				return weather;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	/**
	 * Get all Weather records by weatherDate.
	 * Runs a SELECT statement and returns a list of Weather records occurred on the specified weatherDate.
	 */
	public List<Weather> getWeatherByWeatherDate(Date weatherDate) throws SQLException {
		List<Weather> weatherList = new ArrayList<Weather>();
		String selectWeather = "SELECT WeatherKey,WeatherDate,TempHighInF,TempAverageInF,TempLowInF,HumidityHighPercentage,HumidityAveragePercentage,HumidityLowPercentage,WindHighInMpf,WindAverageInMpf," + 
				"WindGustHighInMpf,SnowFallInInches,PrecipitationInInches,Events "
					+ "FROM Weather "
					+ "WHERE WeatherDate=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeather);
			selectStmt.setTimestamp(1, new Timestamp(weatherDate.getTime()));
			results = selectStmt.executeQuery();
			while(results.next()) {				
				int resultWeatherKey = results.getInt("WeatherKey");
				int tempHighInF = results.getInt("TempHighInF");
				int tempAverageInF = results.getInt("TempAverageInF");
				int tempLowInF = results.getInt("TempLowInF");
				int humidityHighPercentage = results.getInt("HumidityHighPercentage");
				int humidityAveragePercentage = results.getInt("HumidityAveragePercentage");
				int humidityLowPercentage = results.getInt("HumidityLowPercentage");
				int windHighInMph = results.getInt("WindHighInMpf");
				int windAverageInMph = results.getInt("WindAverageInMpf");
				int windGustHighInMph = results.getInt("WindGustHighInMpf");
				double snowFallInInches = results.getDouble("SnowFallInInches");
				double precipitationInInches = results.getDouble("PrecipitationInInches");
				Weather.Events events = Weather.Events.valueOf(results.getString("Events"));
				
				Weather weather = new Weather(resultWeatherKey, weatherDate, tempHighInF, tempAverageInF, tempLowInF, humidityHighPercentage, humidityAveragePercentage, humidityLowPercentage,
						windHighInMph, windAverageInMph, windGustHighInMph, snowFallInInches, precipitationInInches, events);
				weatherList.add(weather);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return weatherList;
	}
	
	/**
	 * Get all Weather records by Events.
	 * Runs a SELECT statement and returns a list of Weather records that had the specified Event occur.
	 */
	public List<Weather> getWeatherByWeatherEvents(Weather.Events events) throws SQLException {
		List<Weather> weatherList = new ArrayList<Weather>();
		String selectWeather = "SELECT WeatherKey,WeatherDate,TempHighInF,TempAverageInF,TempLowInF,HumidityHighPercentage,HumidityAveragePercentage,HumidityLowPercentage,WindHighInMpf,WindAverageInMpf," + 
				"WindGustHighInMpf,SnowFallInInches,PrecipitationInInches,Events "
					+ "FROM Weather "
					+ "WHERE Events=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeather);
			selectStmt.setString(1, events.name());
			results = selectStmt.executeQuery();
			while(results.next()) {				
				int resultWeatherKey = results.getInt("WeatherKey");
				Date weatherDate = new Date(results.getTimestamp("WeatherDate").getTime());
				int tempHighInF = results.getInt("TempHighInF");
				int tempAverageInF = results.getInt("TempAverageInF");
				int tempLowInF = results.getInt("TempLowInF");
				int humidityHighPercentage = results.getInt("HumidityHighPercentage");
				int humidityAveragePercentage = results.getInt("HumidityAveragePercentage");
				int humidityLowPercentage = results.getInt("HumidityLowPercentage");
				int windHighInMph = results.getInt("WindHighInMpf");
				int windAverageInMph = results.getInt("WindAverageInMpf");
				int windGustHighInMph = results.getInt("WindGustHighInMpf");
				double snowFallInInches = results.getDouble("SnowFallInInches");
				double precipitationInInches = results.getDouble("PrecipitationInInches");
				
				Weather weather = new Weather(resultWeatherKey, weatherDate, tempHighInF, tempAverageInF, tempLowInF, humidityHighPercentage, humidityAveragePercentage, humidityLowPercentage,
						windHighInMph, windAverageInMph, windGustHighInMph, snowFallInInches, precipitationInInches, events);
				weatherList.add(weather);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return weatherList;
	}
	
	/**
	 * Delete a Weather instance.
	 * Runs a DELETE statement.
	 */
	public Weather delete(Weather weather) throws SQLException {
		String deleteWeather = "DELETE FROM Weather WHERE WeatherKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWeather);
			deleteStmt.setInt(1, weather.getWeatherKey());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}