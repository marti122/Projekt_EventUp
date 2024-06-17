package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgPrikazRacuna extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextArea textAreaPrikazRacuna;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgPrikazRacuna dialog = new DlgPrikazRacuna();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgPrikazRacuna() {
        setTitle("Prikaz računa");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        // Create JScrollPane
        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Create JTextArea and add it to JScrollPane
        textAreaPrikazRacuna = new JTextArea(); // Correctly initialize the class attribute
        scrollPane.setViewportView(textAreaPrikazRacuna);

        // Create button panel
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // OK button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        // Call method to select and display invoices
        selectPrikazRacuna();
    }

    private void selectPrikazRacuna() {
        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/mroncevic?user=mroncevic&password=11");

            // Execute query
            String sql = "SELECT * FROM Racun";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Prepare text for JTextArea
            StringBuilder tekst = new StringBuilder();
            while (rs.next()) {
                tekst.append("Datum računa: ").append(rs.getString("Datum_racuna")).append("\t");
                tekst.append("Iznos računa: ").append(rs.getString("Iznos_racuna")).append("\t");
                tekst.append("Šifra organizatora: ").append(rs.getString("Sifra_organizatora")).append("\t");
                tekst.append("Šifra nastupa: ").append(rs.getString("Sifra_nastupa")).append("\t");
                tekst.append("Šifra računa: ").append(rs.getString("Sifra_racuna")).append("\n");
            }

            // Set text in JTextArea
            textAreaPrikazRacuna.setText(tekst.toString());

            // Close database connection
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
