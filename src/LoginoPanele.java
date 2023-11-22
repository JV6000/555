import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginoPanele extends JFrame {
    private JTextField naudotojovardas;
    private JPasswordField slaptazodziolaukas;
    private JLabel statusas;

    public LoginoPanele() {
        setTitle("Prisijungimas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel NVLabel = new JLabel("Naudotojo vardas:");
        JLabel passLB = new JLabel("Slaptazodis:");
        naudotojovardas = new JTextField();
        slaptazodziolaukas = new JPasswordField();
        statusas = new JLabel();

        JButton prbut = new JButton("Prisijungti");
        prbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prisijungti();
            }
        });

        panel.add(NVLabel);
        panel.add(naudotojovardas);
        panel.add(passLB);
        panel.add(slaptazodziolaukas);
        panel.add(new JLabel());
        panel.add(prbut);

        add(panel, BorderLayout.CENTER);
        add(statusas, BorderLayout.SOUTH);
    }

    private void prisijungti() {
        String ndvard = naudotojovardas.getText();
        char[] slaptazodioCh = slaptazodziolaukas.getPassword();
        String slaptazodis = new String(slaptazodioCh);

        if (autentifikavimas(ndvard, slaptazodis)) {
            // prisijungmas sekmingas
            statusas.setText("Prisijungta sekmingai");

            // chekina roles
            String role = getUserRole(ndvard);
            roles(role);
        } else {

            statusas.setText("nepavyko prisijungti.");
        }
    }

    private boolean autentifikavimas(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");
                String storedUsername = userData[0];
                String storedPassword = userData[1];

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true; // sekmingas
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // nesekmingas
    }

    private String getUserRole(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");
                String storedUsername = userData[0];
                String role = userData[2];

                if (username.equals(storedUsername)) {
                    return role;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void roles(String role) {
        if ("admin".equals(role)) {

            System.out.println("atidaroma admino...");
            new Admin().setVisible(true);
            setVisible(false);

        } else if ("mokytoj".equals(role)) {

            System.out.println("atidaroma mokytojo...");
            new Mokytojo().setVisible(true);
            setVisible(false);
        } else if ("studentas".equals(role)) {
            System.out.println("atidaroma mokytojo...");
            new Studento().setVisible(true);
            setVisible(false);

        } else {
            System.out.println("nezinoma role.");

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginoPanele().setVisible(true);
            }
        });
    }
}
