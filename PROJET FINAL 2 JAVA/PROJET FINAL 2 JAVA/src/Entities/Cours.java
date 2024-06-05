package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cours {
    private int id;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Professeur professeur;
    private Module module;

    public Cours(int id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, Professeur professeur, Module module) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.professeur = professeur;
        this.module = module;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public Module getModule() {
        return module;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", date=" + date +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", professeur=" + professeur +
                ", module=" + module +
                '}';
    }
}