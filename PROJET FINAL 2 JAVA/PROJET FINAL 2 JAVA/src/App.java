public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    public class Menu {
        private static final int EXIT_OPTION = 0;
        private static final int CREATE_MODULE_OPTION = 1;
        private static final int LIST_MODULES_OPTION = 2;
        private static final int CREATE_COURSE_OPTION = 3;
        private static final int UPDATE_COURSE_OPTION = 4;
        private static final int DELETE_COURSE_OPTION = 5;
        private static final int LIST_COURSES_OPTION = 6;
        private static final int LIST_COURSES_BY_MODULE_AND_PROFESSOR_OPTION = 7;
    
        private static final Scanner scanner = new Scanner(System.in);
    
        private final ModuleService moduleService;
        private final CourseService courseService;
        private final ProfessorService professorService;
    
        public Menu(ModuleService moduleService, CourseService courseService, ProfessorService professorService) {
            this.moduleService = moduleService;
            this.courseService = courseService;
            this.professorService = professorService;
        }
    
        public void start() {
            int choice;
            do {
                printMenu();
                choice = getUserChoice();
                handleChoice(choice);
            } while (choice != EXIT_OPTION);
        }
    
        private void printMenu() {
            System.out.println("\n--- Menu ---");
            System.out.println("0. Quitter");
            System.out.println("1. Créer un module");
            System.out.println("2. Lister les modules");
            System.out.println("3. Créer un cours");
            System.out.println("4. Mettre à jour un cours");
            System.out.println("5. Supprimer un cours");
            System.out.println("6. Lister tous les cours");
            System.out.println("7. Lister les cours d'un module et d'un professeur");
        }
    
        private int getUserChoice() {
            System.out.print("Entrez votre choix : ");
            return scanner.nextInt();
        }
    
        private void handleChoice(int choice) {
            switch (choice) {
                case EXIT_OPTION:
                    System.out.println("Aurevoir !");
                    break;
                case CREATE_MODULE_OPTION:
                    createModule();
                    break;
                case LIST_MODULES_OPTION:
                    listModules();
                    break;
                case CREATE_COURSE_OPTION:
                    createCourse();
                    break;
                case UPDATE_COURSE_OPTION:
                    updateCourse();
                    break;
                case DELETE_COURSE_OPTION:
                    deleteCourse();
                    break;
                case LIST_COURSES_OPTION:
                    listCourses();
                    break;
                case LIST_COURSES_BY_MODULE_AND_PROFESSOR_OPTION:
                    listCoursesByModuleAndProfessor();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    
        private void createModule() {
            System.out.print("Entrez le nom du module : ");
            String nom = scanner.next();
            Module module = moduleService.createModule(nom);
            System.out.println("Module créé : " + module);
        }
    
        private void listModules() {
            List<Module> modules = moduleService.getAllModules();
            System.out.println("Modules :");
            modules.forEach(System.out::println);
        }
    
        private void createCourse() {
            System.out.print("Entrez l'ID du module : ");
            int moduleId = scanner.nextInt();
            System.out.print("Entrez l'ID du professeur : ");
            int professorId = scanner.nextInt();
            System.out.print("Entrez la date du cours (AAAA-MM-JJ) : ");
            LocalDate date = LocalDate.parse(scanner.next());
            System.out.print("Entrez l'heure de début (HH:mm) : ");
            LocalTime heureDebut = LocalTime.parse(scanner.next());
            System.out.print("Entrez l'heure de fin (HH:mm) : ");
            LocalTime heureFin = LocalTime.parse(scanner.next());
            Course course = courseService.createCourse(moduleId, professorId, date, heureDebut, heureFin);
            System.out.println("Cours créé : " + course);
        }
    
        private void updateCourse() {
            System.out.print("Entrez l'ID du cours à mettre à jour : ");
            int courseId = scanner.nextInt();
            System.out.print("Entrez l'ID du nouveau module : ");
            int moduleId = scanner.nextInt();
            System.out.print("Entrez l'ID du nouveau professeur : ");
            int professorId = scanner.nextInt();
            System.out.print("Entrez la nouvelle date du cours (AAAA-MM-JJ) : ");
            LocalDate date = LocalDate.parse(scanner.next());
            System.out.print("Entrez la nouvelle heure de début (HH:mm) : ");
            LocalTime heureDebut = LocalTime.parse(scanner.next());
            System.out.print("Entrez la nouvelle heure de fin (HH:mm) : ");
            LocalTime heureFin = LocalTime.parse(scanner.next());
            Course updatedCourse = courseService.updateCourse(courseId, moduleId, professorId, date, heureDebut, heureFin);
            System.out.println("Cours mis à jour : " + updatedCourse);
        }
    
        private void deleteCourse() {
            System.out.print("Entrez l'ID du cours à supprimer : ");
            int courseId = scanner.nextInt();
            courseService.deleteCourse(courseId);
            System.out.println("Cours supprimé");
        }
    
        private void listCourses() {
            List<Course> courses = courseService.getAllCourses();
            System.out.println("Tous les cours :");
            courses.forEach(System.out::println);
        }
    
        private void listCoursesByModuleAndProfessor() {
            System.out.print("Entrez l'ID du module : ");
            int moduleId = scanner.nextInt();
            System.out.print("Entrez l'ID du professeur : ");
            int professorId = scanner.nextInt();
            List<Course> courses = courseService.getCoursesByModuleAndProfessor(moduleId, professorId);
            System.out.println("Cours du module " + moduleId + " et du professeur " + professorId + " :");
            courses.forEach(System.out::println);
        }
    }
}
