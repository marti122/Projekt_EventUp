package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgPrikazOrganizatora extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgPrikazOrganizatora dialog = new DlgPrikazOrganizatora();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgPrikazOrganizatora() {
        setTitle("Prikaz organizatora");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Create and add JScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 414, 200);  // Adjusted the bounds to be within the panel
        contentPanel.add(scrollPane);

        // Create JTextArea and add it to the JScrollPane
        JTextArea textArea_PrikazOrganizatora = new JTextArea();
        scrollPane.setViewportView(textArea_PrikazOrganizatora);

        // Create button panel
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // OK button
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(e -> dispose());
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        // Load data
        prikaziOrganizatore(textArea_PrikazOrganizatora);
    }

    private void prikaziOrganizatore(JTextArea textArea) {
        try {
            // Uspostava veze s bazom podataka
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/mroncevic?user=mroncevic&password=11");

            // Izvršavanje upita
            String sql = "SELECT * FROM Organizator";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Priprema za prikaz u JTextArea
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                int sifra = rs.getInt("Sifra_organizatora");
                String naziv = rs.getString("Naziv_organizatora");
                String kontakt = rs.getString("Kontakt_organizatora");
                String lokacija = rs.getString("Lokacija_organizatora");

                sb.append("Šifra organizatora: ").append(sifra).append("\n");
                sb.append("Naziv organizatora: ").append(naziv).append("\n");
                sb.append("Kontakt organizatora: ").append(kontakt).append("\n");
                sb.append("Lokacija organizatora: ").append(lokacija).append("\n\n");
            }

            // Postavljanje teksta u JTextArea
            textArea.setText(sb.toString());

            // Zatvaranje veze s bazom podataka
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


