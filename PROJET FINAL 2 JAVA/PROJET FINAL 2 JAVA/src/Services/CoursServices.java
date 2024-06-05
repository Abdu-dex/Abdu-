public class CourseService {
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final ProfessorRepository professorRepository;

    public CourseService(CourseRepository courseRepository, ModuleRepository moduleRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.professorRepository = professorRepository;
    }

    public Course createCourse(int moduleId, int professorId, LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
        Module module = moduleRepository.findById(moduleId);
        Professor professor = professorRepository.findById(professorId);
        Course course = new Course(module, professor, date, heureDebut, heureFin);
        return courseRepository.save(course);
    }

    public Course updateCourse(int courseId, int moduleId, int professorId, LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
        Course course = courseRepository.findById(courseId);
        Module module = moduleRepository.findById(moduleId);
        Professor professor = professorRepository.findById(professorId);
        course.setModule(module);
        course.setProfessor(professor);
        course.setDate(date);
        course.setHeureDebut(heureDebut);
        course.setHeureFin(heureFin);
        return courseRepository.save(course);
    }

    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByModuleAndProfessor(int moduleId, int professorId) {
        return courseRepository.findByModuleIdAndProfessorId(moduleId, professorId);
    }
}
