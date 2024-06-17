package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class DlgPrikazIzvodaca extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextArea textArea_PrikazIzvodaca;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgPrikazIzvodaca dialog = new DlgPrikazIzvodaca();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgPrikazIzvodaca() {
        setTitle("Prikaz izvođača");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);

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

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Text area for displaying data
        textArea_PrikazIzvodaca = new JTextArea();
        scrollPane.setViewportView(textArea_PrikazIzvodaca);

        // Load data into text area
        selectPrikazIzvodaca();
    }

    private void selectPrikazIzvodaca() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://ucka.veleri.hr/mroncevic?user=mroncevic&password=11"
            );

            String sql = "SELECT * FROM Izvodac";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            StringBuilder tekst = new StringBuilder();
            while (rs.next()) {
                tekst.append("Šifra izvođača: ").append(rs.getString("sifra_izvodaca")).append("\n");
                tekst.append("Ime izvođača: ").append(rs.getString("ime_izvodaca")).append("\n");
                tekst.append("Prezime izvođača: ").append(rs.getString("prezime_izvodaca")).append("\n");
                tekst.append("Umjetničko ime: ").append(rs.getString("umjetnickoIme_izvodaca")).append("\n");
                tekst.append("Kontakt izvođača: ").append(rs.getString("kontakt_izvodaca")).append("\n\n");
            }

            textArea_PrikazIzvodaca.setText(tekst.toString());

            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
