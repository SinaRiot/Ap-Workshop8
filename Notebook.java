import java.io.*;
import java.util.*;

public class Notebook {
    private Map<String, Note> notes;

    public Notebook() {
        notes = new HashMap<>();
    }

    public void addNote() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter note title (unique): ");
        String title = scanner.nextLine();

        if (notes.containsKey(title)) {
            System.out.println("A note with this title already exists. Please use a different title.");
            return;
        }

        System.out.println("Enter note text: ");
        String text = scanner.nextLine();

        Note note = new Note(title, text);
        notes.put(title, note);
        System.out.println("Note added successfully.");
    }

    public void removeNote() {
        Scanner scanner = new Scanner(System.in);

        if (notes.isEmpty()) {
            System.out.println("No notes available.");
            return;
        }

        System.out.println("Available notes:");
        int index = 1;
        List<String> titles = new ArrayList<>(notes.keySet());
        for (String title : titles) {
            System.out.println(index++ + ". " + title);
        }

        System.out.println("Enter the number of the note you want to remove: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice > 0 && choice <= titles.size()) {
            String title = titles.get(choice - 1);
            notes.remove(title);
            System.out.println("Note removed successfully.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void viewNoteText() {
        Scanner scanner = new Scanner(System.in);

        if (notes.isEmpty()) {
            System.out.println("No notes available.");
            return;
        }

        System.out.println("Available notes:");
        int index = 1;
        List<String> titles = new ArrayList<>(notes.keySet());
        for (String title : titles) {
            System.out.println(index++ + ". " + title);
        }

        System.out.println("Enter the number of the note you want to view: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice > 0 && choice <= titles.size()) {
            String title = titles.get(choice - 1);
            Note note = notes.get(title);
            System.out.println("Title: " + note.getTitle());
            System.out.println("Text: " + note.getText());
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void saveNotesToFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter filename to save notes: ");
        String filename = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Note note : notes.values()) {
                writer.write("Title: " + note.getTitle() + "\n");
                writer.write("Text: " + note.getText() + "\n");
                writer.write("------------\n");
            }
            System.out.println("Notes saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving notes: " + e.getMessage());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add Note");
            System.out.println("2. Remove Note");
            System.out.println("3. View Note Text");
            System.out.println("4. Save Notes to File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    removeNote();
                    break;
                case 3:
                    viewNoteText();
                    break;
                case 4:
                    saveNotesToFile();
                    break;
                case 0:
                    System.out.println("Exiting the notebook.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void main(String[] args) {
        Notebook notebook = new Notebook();
        notebook.run();
    }
}
