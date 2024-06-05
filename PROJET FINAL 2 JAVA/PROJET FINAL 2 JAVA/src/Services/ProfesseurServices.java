public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor createProfessor(String nom, String prenom) {
        Professor professor = new Professor(nom, prenom);
        return professorRepository.save(professor);
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }
}