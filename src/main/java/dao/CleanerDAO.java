package dao;

import model.Cleaner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CleanerDAO {
    public boolean addCleaner(Cleaner cleaner){
        String sql = """
                INSERT INTO cleaners
                (first_name, last_name, phone, department)
                VALUES (?, ?, ?, ?)
                """;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, cleaner.getFirstName());
            statement.setString(2, cleaner.getLastName());
            statement.setString(3, cleaner.getPhone());
            statement.setString(4, cleaner.getDepartment());

            return statement.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //Get all cleaners
    public List<Cleaner> getAllCleaners(){
        List<Cleaner> cleaners = new ArrayList<>();

        String sql = """
                SELECT *
                FROM cleaners
                ORDER BY first_name
                """;
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while(resultSet.next()) {
                Cleaner cleaner = new Cleaner();

                cleaner.setCleanerId(resultSet.getInt("cleaner_id"));
                cleaner.setFirstName(resultSet.getString("first_name"));
                cleaner.setLastName(resultSet.getString("last_name"));
                cleaner.setPhone(resultSet.getString("phone"));
                cleaner.setDepartment(resultSet.getString("department"));

                cleaners.add(cleaner);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return cleaners;
    }

    //search by id
    public Cleaner findCleaner(int cleanerId){
        String sql = "SELECT * FROM cleaners WHERE cleaner_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cleanerId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                return new Cleaner(
                        resultSet.getInt("cleaner_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("department")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //update cleaner
    public boolean updateCleaner(Cleaner cleaner){

        String sql = """
                UPDATE cleaners
                SET first_name = ?,
                last_name = ?,
                phone = ?,
                department = ?
                WHERE cleaner_id = ?
                """;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cleaner.getFirstName());
            statement.setString(2, cleaner.getLastName());
            statement.setString(3, cleaner.getPhone());
            statement.setString(4, cleaner.getDepartment());
            statement.setInt(5, cleaner.getCleanerId());

            return statement.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //delete cleaner
    public boolean deleteCleaner(int cleanerId) {
        String sql = "DELETE FROM cleaners WHERE cleaner_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cleanerId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getCleanerCount() {

        String sql = "SELECT COUNT(*) FROM cleaners";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
