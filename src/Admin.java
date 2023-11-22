import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin extends JFrame {
    private JTextArea outputTextArea;

    public Admin() {
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JButton SukuriamosGrupesMygtukas = new JButton("Sukurti grupe");
        JButton IstrintiGrupesMygtukas = new JButton("Istrinti grupe");
        JButton SukurtidalykaMygtukas = new JButton("Sukurti dalyka ");
        JButton IstrintiDalykaMygtukas = new JButton("Istrinti dalyka");
        JButton SukurtiMokytojaMygtukas = new JButton("Sukurti mokytoja");
        JButton IstrintiMokytojaMygtukas = new JButton("Istrinti mokytoja");
        JButton SukurtiStudentaMygtukas = new JButton("Sukurti studenta ");
        JButton IstrintiStudentaMygtukas = new JButton("Istrinti studenta");
        JButton priskirtiGrupeMokytojuiMygtukas = new JButton("Priskirti grupe mokytojui");
        JButton pridetiStudentaPrieDalykoMygtukas = new JButton("Pridėti studentą prie dalyko");




        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        buttonPanel.add(SukuriamosGrupesMygtukas);
        buttonPanel.add(IstrintiGrupesMygtukas);
        buttonPanel.add(SukurtidalykaMygtukas);
        buttonPanel.add(IstrintiDalykaMygtukas);
        buttonPanel.add(SukurtiMokytojaMygtukas);
        buttonPanel.add(IstrintiMokytojaMygtukas);
        buttonPanel.add(SukurtiStudentaMygtukas);
        buttonPanel.add(IstrintiStudentaMygtukas);

        buttonPanel.add(priskirtiGrupeMokytojuiMygtukas);
        buttonPanel.add(pridetiStudentaPrieDalykoMygtukas);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        SukuriamosGrupesMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SukurtiGrupe();
            }
        });

        IstrintiGrupesMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IstrintiGrupe();
            }
        });

        SukurtidalykaMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SukurtiDalyka();
            }
        });

        IstrintiDalykaMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IstrintiDalyka();
            }
        });

        SukurtiMokytojaMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SukurtiMokytoja();
            }
        });

        IstrintiMokytojaMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IstrintiMokytoja();
            }
        });

        SukurtiStudentaMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SukurtiStudenta();
            }
        });


        IstrintiStudentaMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IstrintiStudenta();
            }
        });
        priskirtiGrupeMokytojuiMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                priskirtiGrupeMokytojui();
            }
        });
        pridetiStudentaPrieDalykoMygtukas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pridetiStudentaPrieDalyko();
            }
        });
    }


    private void priskirtiGrupeMokytojui() {
        String MokytojoVard = JOptionPane.showInputDialog(this, "Veskite mokytojo varda:");
        String GrupesVardas = JOptionPane.showInputDialog(this, "Veskite grupes pavadinima:");

        if (MokytojoVard != null && !MokytojoVard.isEmpty()
                && GrupesVardas != null && !GrupesVardas.isEmpty()) {
            if (priskirtiGrupeMokytojuiFailuose(MokytojoVard, GrupesVardas)) {
                AtnaujintiOut("Grupe priskirta mokytojui: " + MokytojoVard + ", Grupes pavadinimas: " + GrupesVardas);
            } else {
                AtnaujintiOut("Mokytojas arba grupe nerasta: " + MokytojoVard + ", " + GrupesVardas);
            }
        }
    }


    private void SukurtiGrupe() {
        String GrupesVardas = JOptionPane.showInputDialog(this, "Veskite grupes pavadinima:");
        if (GrupesVardas != null && !GrupesVardas.isEmpty()) {
            RasytiFaile("Groups.txt", GrupesVardas);
            AtnaujintiOut("Grupe sukurta: " + GrupesVardas);
        }
    }

    private void IstrintiGrupe() {
        String GrupesVardas = JOptionPane.showInputDialog(this, "Veskite grupes pavadinima kuria norite istrinti:");
        if (GrupesVardas != null && !GrupesVardas.isEmpty()) {
            if (istrintiFaile("Groups.txt", GrupesVardas)) {
                AtnaujintiOut("Grupe istrinta: " + GrupesVardas);
            } else {
                AtnaujintiOut("Grupe nerasta: " + GrupesVardas);
            }
        }
    }

    private void SukurtiDalyka() {
        String DalykoPavadinimas = JOptionPane.showInputDialog(this, "Vekite dalyko pavadinima:");
        if (DalykoPavadinimas != null && !DalykoPavadinimas.isEmpty()) {
            RasytiFaile("Subjects.txt", DalykoPavadinimas);
            AtnaujintiOut("dalykas sukurtas: " + DalykoPavadinimas);
        }
    }

    private void IstrintiDalyka() {
        String DalykoPavadinimas = JOptionPane.showInputDialog(this, "Vekite dalyko pavadinima istrynimui:");
        if (DalykoPavadinimas != null && !DalykoPavadinimas.isEmpty()) {
            if (istrintiFaile("Subjects.txt", DalykoPavadinimas)) {
                AtnaujintiOut("dalykas istrintas: " + DalykoPavadinimas);
            } else {
                AtnaujintiOut("dalykas nerastas: " + DalykoPavadinimas);
            }
        }
    }

    private void SukurtiMokytoja() {
        String MokytojoVard = JOptionPane.showInputDialog(this, "Veskite varda:");
        String MokytojoPavard = JOptionPane.showInputDialog(this, "Veskite pavarde:");


        if (MokytojoVard != null && !MokytojoVard.isEmpty()
                && MokytojoPavard != null && !MokytojoPavard.isEmpty()) {

            String MokINfo = MokytojoVard + "|" + MokytojoPavard + "|mokytoj";
            String MokINfo1 = MokytojoVard + "|" + MokytojoPavard;
            RasytiFaile("Teachers.txt", MokINfo1);
            RasytiFaile("users.txt", MokINfo);
            AtnaujintiOut("Mokyojas sukurtas: " + MokytojoVard + " " + MokytojoPavard);
        }
    }

    private void IstrintiMokytoja() {
        String MokytojoVard = JOptionPane.showInputDialog(this, "Veskite mokytoja kuri norite istrinti:");
        if (MokytojoVard != null && !MokytojoVard.isEmpty()) {
            boolean MokTrn = istrintiFaile("Teachers.txt", MokytojoVard);
            boolean naudotojoTrn = istrintiFaile("users.txt", MokytojoVard);

            if (MokTrn && naudotojoTrn) {
                AtnaujintiOut("istrintas: " + MokytojoVard);
            } else {
                AtnaujintiOut("nerastas: " + MokytojoVard);
            }
        }
    }


    private void SukurtiStudenta() {
        String StudentoVardas = JOptionPane.showInputDialog(this, "Veskite studento varda:");
        String StudentoPavarde = JOptionPane.showInputDialog(this, "Veskite studento pavarde:");


        if (StudentoVardas != null && !StudentoVardas.isEmpty()
                && StudentoPavarde != null && !StudentoPavarde.isEmpty()) {

            String StudentInfo = StudentoVardas + "|" + StudentoPavarde + "|studentas";
            RasytiFaile("Students.txt", StudentInfo);
            RasytiFaile("users.txt", StudentInfo);
            AtnaujintiOut("Studentas sukurtas: " + StudentoVardas + " " + StudentoPavarde);
        }
    }

    private void IstrintiStudenta() {
        String StudentoVardas = JOptionPane.showInputDialog(this, "Studentas kuri norite istrinti:");
        if (StudentoVardas != null && !StudentoVardas.isEmpty()) {
            boolean StudentoTrnt = istrintiFaile("Students.txt", StudentoVardas);
            boolean naudotojoTrn = istrintiFaile("users.txt", StudentoVardas);

            if (StudentoTrnt && naudotojoTrn) {
                AtnaujintiOut("Studentas istrintas: " + StudentoVardas);
            } else {
                AtnaujintiOut("Studentas nerastas: " + StudentoVardas);
            }
        }
    }
    private void pridetiStudentaPrieDalyko() {
        String studentoVardas = JOptionPane.showInputDialog(this, "Veskite studento varda:");
        String studentoPavarde = JOptionPane.showInputDialog(this, "Veskite studento pavarde:");
        String dalykoPavadinimas = JOptionPane.showInputDialog(this, "Veskite dalyko pavadinima:");

        if (studentoVardas != null && !studentoVardas.isEmpty()
                && studentoPavarde != null && !studentoPavarde.isEmpty()
                && dalykoPavadinimas != null && !dalykoPavadinimas.isEmpty()) {
            if (pridetiStudentaPrieDalykoFailuose(studentoVardas, studentoPavarde, dalykoPavadinimas)) {
                AtnaujintiOut("Studentas pridėtas prie dalyko: " + studentoVardas + " " + studentoPavarde +
                        ", Dalyko pavadinimas: " + dalykoPavadinimas);
            } else {
                AtnaujintiOut("Studentas, dalykas arba abu nerasti: " + studentoVardas + " " +
                        studentoPavarde + ", " + dalykoPavadinimas);
            }
        }
    }


    private boolean priskirtiGrupeMokytojuiFailuose(String mokytojoVardas, String grupesVardas) {
        List<String> teachers = skaitytiFaila("Teachers.txt");
        List<String> groups = skaitytiFaila("Groups.txt");

        boolean mokytojasRastas = false;
        boolean grupeRasta = false;

        for (int i = 0; i < teachers.size(); i++) {
            String[] mokytojoInfo = teachers.get(i).split("\\|");
            if (mokytojoInfo.length >= 2 && mokytojoInfo[0].equals(mokytojoVardas)) {
                mokytojasRastas = true;
                break;
            }
        }

        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).equals(grupesVardas)) {
                grupeRasta = true;
                break;
            }
        }

        if (mokytojasRastas && grupeRasta) {
            RasytiFaile("AssignedGroups.txt", mokytojoVardas + "|" + grupesVardas);
            return true;
        } else {
            return false;
        }
    }
    private boolean pridetiStudentaPrieDalykoFailuose(String studentoVardas, String studentoPavarde, String dalykoPavadinimas) {
        List<String> students = skaitytiFaila("Students.txt");
        List<String> subjects = skaitytiFaila("Subjects.txt");

        boolean studentasRastas = false;
        boolean dalykasRastas = false;

        for (int i = 0; i < students.size(); i++) {
            String[] studentoInfo = students.get(i).split("\\|");
            if (studentoInfo.length >= 2 && studentoInfo[0].equals(studentoVardas) && studentoInfo[1].equals(studentoPavarde)) {
                studentasRastas = true;
                break;
            }
        }

        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).equals(dalykoPavadinimas)) {
                dalykasRastas = true;
                break;
            }
        }

        if (studentasRastas && dalykasRastas) {
            RasytiFaile("Enrollments.txt", studentoVardas + "|" + studentoPavarde + "|" + dalykoPavadinimas);
            return true;
        } else {
            return false;
        }
    }

    private List<String> skaitytiFaila(String failoVardas) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(failoVardas))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void RasytiFaile(String FailoVardas, String data) {
        try (FileWriter writer = new FileWriter(FailoVardas, true)) {
            writer.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean istrintiFaile(String failoVardas, String data) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(failoVardas))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(data)) {
                    lines.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(failoVardas))) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return found;
    }

    private void AtnaujintiOut(String message) {
        outputTextArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }
}
