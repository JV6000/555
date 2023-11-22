import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Mokytojo extends JFrame {
    private JComboBox<String> StudentoCB;
    private JComboBox<String> PamokosCB;
    private JTextField PazLauk;
    private JTextArea textArea;

    public Mokytojo() {
        setTitle("Mokytojo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel studentLabel = new JLabel("Pasirinkti studenta:");
        JLabel subjectLabel = new JLabel("Pasirinkti Pamoka:");
        JLabel gradeLabel = new JLabel("Veskite pazymi:");

        StudentoCB = new JComboBox<>();
        PamokosCB = new JComboBox<>();
        PazLauk = new JTextField();
        textArea = new JTextArea();
        textArea.setEditable(false);

        JButton VestiGradeButton = new JButton("Issaugoti");
        JButton EditintiGreidaButt = new JButton("Redaguoti");

        VestiGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vestiGreida();
            }
        });

        EditintiGreidaButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditintiGreida();
            }
        });

        panel.add(studentLabel);
        panel.add(StudentoCB);
        panel.add(subjectLabel);
        panel.add(PamokosCB);
        panel.add(gradeLabel);
        panel.add(PazLauk);
        panel.add(EditintiGreidaButt);
        panel.add(VestiGradeButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        IkelimasStudento();
        IkelimasPamokuCB();
    }

    private void IkelimasStudento() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split("\\|");
                StudentoCB.addItem(studentData[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void IkelimasPamokuCB() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Subjects.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                PamokosCB.addItem(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void vestiGreida() {
        String PasirinktasStudentas = (String) StudentoCB.getSelectedItem();
        String PasirinktaPamoka = (String) PamokosCB.getSelectedItem();
        String grade = PazLauk.getText();

        if (PasirinktasStudentas != null && PasirinktaPamoka != null && !grade.isEmpty()) {
            String gradeInfo = PasirinktasStudentas + "|" + PasirinktaPamoka + "|" + grade;
            writeToFile("Grades.txt", gradeInfo);
            updateOutput("Pakeisti/Issaugoti: " + gradeInfo);
        } else {
            updateOutput("Pasirinkite studenta.");
        }
    }

    private void EditintiGreida() {
        String PasirinktasStudentas = (String) StudentoCB.getSelectedItem();
        String PasirinktaPamoka = (String) PamokosCB.getSelectedItem();

        if (PasirinktasStudentas != null && PasirinktaPamoka != null) {
            String existingGrade = SurastiPaz(PasirinktasStudentas, PasirinktaPamoka);

            if (existingGrade != null) {
                String editedGrade = JOptionPane.showInputDialog(this, "Įveskite naują pažymį:");
                if (editedGrade != null && !editedGrade.isEmpty()) {
                    String newGradeInfo = PasirinktasStudentas + "|" + PasirinktaPamoka + "|" + editedGrade;
                    if (AtnaujintiPaz("Grades.txt", existingGrade, newGradeInfo)) {
                        updateOutput("Pakeistas pažymys: " + newGradeInfo);
                    } else {
                        updateOutput("Nepavyko pakeisti pažymio.");
                    }
                }
            } else {
                updateOutput("Pažymys nerastas.");
            }
        } else {
            updateOutput("Pasirinkite studentą ir pamoką.");
        }
    }

    private String SurastiPaz(String student, String subject) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Grades.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] gradeInfo = line.split("\\|");
                if (gradeInfo.length >= 2 && gradeInfo[0].equals(student) && gradeInfo[1].equals(subject)) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean AtnaujintiPaz(String fileName, String oldGrade, String newGrade) {
        java.util.List<String> grades = new java.util.ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                grades.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).equals(oldGrade)) {
                grades.set(i, newGrade);
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String grade : grades) {
                writer.write(grade + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void writeToFile(String fileName, String data) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateOutput(String message) {
        textArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Mokytojo().setVisible(true);
            }
        });
    }
}
