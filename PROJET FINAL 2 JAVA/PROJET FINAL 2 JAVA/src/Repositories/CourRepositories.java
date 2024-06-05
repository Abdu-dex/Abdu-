package repositories;

import entities.Cours;
import entities.Module;
import entities.Professeur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CoursRepository {
    private static final String INSERT_COURS_QUERY = "INSERT INTO cours (date, heure_debut, heure_fin, professeur_id, module_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_COURS_QUERY = "SELECT c.id, c.date, c.heure_debut, c.heure_fin, p.id AS p_id, p.nom AS p_nom, p.prenom AS p_prenom, p.telephone AS p_telephone, m.id AS m_id, m.nom AS m_nom FROM cours c " +
                                                         "JOIN professeurs p ON c.professeur_id = p.id " +
                                                         "JOIN modules m ON c.module_id = m.id";
    private static final String SELECT_COURS_BY_MODULE_AND_PROFESSEUR_QUERY = "SELECT c.id, c.date, c.heure_debut, c.heure_fin, p.id AS p_id, p.nom AS p_nom, p.prenom AS p_prenom, p.telephone AS p_telephone, m.id AS m_id, m.nom AS m_nom " +
                                                                                "FROM cours c " +
                                                                                "JOIN professeurs p ON c.professeur_id = p.id " +
                                                                                "JOIN modules m ON c.module_id = m.id " +
                                                                                "WHERE m.id = ? AND p.id = ?";

    public void creerCours(Cours cours) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_COURS_QUERY)) {
            statement.setDate(1, java.sql.Date.valueOf(cours.getDate()));
            statement.setTime(2, java.sql.Time.valueOf(cours.getHeureDebut()));
            statement.setTime(3, java.sql.Time.valueOf(cours.getHeureFin()));
            statement.setInt(4, cours.getProfesseur().getId());
            statement.setInt(5, cours.getModule().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cours> listerTousCours() {
        List<Cours> cours = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COURS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                LocalTime heureDebut = resultSet.getTime("heure_debut").toLocalTime();
                LocalTime heureFin = resultSet.getTime("heure_fin").toLocalTime();
                int professeurId = resultSet.getInt