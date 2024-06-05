package repositories;

import entities.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleRepository {
    private static final String INSERT_MODULE_QUERY = "INSERT INTO modules (nom) VALUES (?)";
    private static final String SELECT_ALL_MODULES_QUERY = "SELECT * FROM modules";

    public void ajouterModule(Module module) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_MODULE_QUERY)) {
            statement.setString(1, module.getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Module> listerModules() {
        List<Module> modules = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MODULES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Module module = new Module(id, nom);
                modules.add(module);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modules;
    }
}