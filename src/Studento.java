import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Studento extends JFrame {
    private JComboBox<String> PamokosCB;
    private JTextArea pazTextLk;

    public Studento() {
        setTitle("Studento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel PamokosLabel = new JLabel("Pamoka:");
        PamokosCB = new JComboBox<>();
        pazTextLk = new JTextArea();
        pazTextLk.setEditable(false);

        JButton ZiuretPazButt = new JButton("Ziureti pazymi");
        ZiuretPazButt.addActionListener(e -> ZiuretPaz());

        panel.add(PamokosLabel);
        panel.add(PamokosCB);
        panel.add(new JLabel());
        panel.add(ZiuretPazButt);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(pazTextLk), BorderLayout.CENTER);

        loadPamokosCB();
    }

    private void loadPamokosCB() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Subjects.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                PamokosCB.addItem(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ZiuretPaz() {
        String selectedSubject = (String) PamokosCB.getSelectedItem();

        if (selectedSubject != null) {
            pazTextLk.setText("");

            try (BufferedReader reader = new BufferedReader(new FileReader("Grades.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] gradeData = line.split("\\|");
                    String studentName = gradeData[0];
                    String subject = gradeData[1];
                    String grade = gradeData[2];

                    if (studentName.equals("student_name_placeholder") || subject.equals(selectedSubject)) {
                        pazTextLk.append("Pamoka: " + subject + "\tPažymys: " + grade + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            pazTextLk.setText("Pasirinkti Pamoka.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Studento().setVisible(true));
    }
}
