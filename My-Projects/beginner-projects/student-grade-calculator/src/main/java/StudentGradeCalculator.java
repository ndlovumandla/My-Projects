
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define weight percentages
        final double ASSIGNMENT_WEIGHT = 0.4;
        final double EXAM_WEIGHT = 0.4;
        final double PROJECT_WEIGHT = 0.2;

        // Input for assignment grades
        System.out.println("Enter your average assignment grade (0 - 100): ");
        double assignmentGrade = getValidGrade(scanner);

        // Input for exam grades
        System.out.println("Enter your average exam grade (0 - 100): ");
        double examGrade = getValidGrade(scanner);

        // Input for project grades
        System.out.println("Enter your project grade (0 - 100): ");
        double projectGrade = getValidGrade(scanner);

        // Calculate the final grade
        double finalGrade = (assignmentGrade * ASSIGNMENT_WEIGHT)
                + (examGrade * EXAM_WEIGHT)
                + (projectGrade * PROJECT_WEIGHT);

        // Output the final grade
        System.out.printf("Your final grade is: %.2f\n", finalGrade);

        // Provide performance feedback
        provideFeedback(finalGrade);

        scanner.close();
    }

    // Method to ensure valid grade input
    private static double getValidGrade(Scanner scanner) {
        double grade = scanner.nextDouble();
        while (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
            grade = scanner.nextDouble();
        }
        return grade;
    }

    // Method to provide feedback based on the final grade
    private static void provideFeedback(double finalGrade) {
        if (finalGrade >= 90) {
            System.out.println("Excellent work! You achieved an A.");
        } else if (finalGrade >= 80) {
            System.out.println("Great job! You achieved a B.");
        } else if (finalGrade >= 70) {
            System.out.println("Good effort! You achieved a C.");
        } else if (finalGrade >= 60) {
            System.out.println("You passed with a D. Consider working harder next time.");
        } else {
            System.out.println("You failed. Better luck next time!");
        }
    }
}
