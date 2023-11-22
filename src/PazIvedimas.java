import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PazIvedimas extends JFrame {

    private Map<String, Integer> StudentoPaz;

    public PazIvedimas() {
        StudentoPaz = new HashMap<>();

        setTitle("Pazymio Ivedimas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        JLabel PamokosLabel = new JLabel("Pasirinkti pamoka:");
        JLabel PazLabel = new JLabel("Įveskite / redaguokite pažymį:");

        JComboBox<String> PamokosCB = new JComboBox<>();
        JTextField pazTexField = new JTextField();
        JButton SaugotiPazButt = new JButton("issaugoti");

        mainPanel.add(PamokosLabel);
        mainPanel.add(PamokosCB);
        mainPanel.add(PazLabel);
        mainPanel.add(pazTexField);
        mainPanel.add(SaugotiPazButt);

        add(mainPanel);

        // parodyti dabartinius
        loadStudentoPaz();


        for (String subject : StudentoPaz.keySet()) {
            PamokosCB.addItem(subject);
        }

        SaugotiPazButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String PasirinktasDalykas = (String) PamokosCB.getSelectedItem();
                String PazmText = pazTexField.getText();

                if (PasirinktasDalykas != null && !PazmText.isEmpty()) {
                    int Paz = Integer.parseInt(PazmText);

   
                    StudentoPaz.put(PasirinktasDalykas, Paz);


                    SaugotiPaz();

                    JOptionPane.showMessageDialog(PazIvedimas.this, "Issaugoti sekmingai.");
                } else {
                    JOptionPane.showMessageDialog(PazIvedimas.this, "Pasirinkite dalyą ir įveskite pažymį.", "Klaida", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadStudentoPaz() {
        try (BufferedReader reader = new BufferedReader(new FileReader("1_Pazs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String subject = parts[0].trim();
                int Paz = Integer.parseInt(parts[1].trim());

                StudentoPaz.put(subject, Paz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SaugotiPaz() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("1_Pazs_Pazs.txt"))) {
            for (Map.Entry<String, Integer> entry : StudentoPaz.entrySet()) {
                String subject = entry.getKey();
                int Paz = entry.getValue();
                writer.write(subject + "," + Paz + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PazIvedimas().setVisible(true);
            }
        });
    }
}