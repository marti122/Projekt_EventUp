package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DlgNoviNastup extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textField_DatumNastupa;
    private JTextField textField_MjestoNastupa;
    private JComboBox<String> comboBox_UmjetnickoImeIzvodaca;
    private JTextField textField_SifraNastupa;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgNoviNastup dialog = new DlgNoviNastup();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgNoviNastup() {
        setTitle("Novi nastup");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblIzvodac = new JLabel("Izvođač:");
        lblIzvodac.setBounds(10, 32, 60, 13);
        contentPanel.add(lblIzvodac);

        comboBox_UmjetnickoImeIzvodaca = new JComboBox<>();
        comboBox_UmjetnickoImeIzvodaca.setBounds(132, 28, 161, 21);
        contentPanel.add(comboBox_UmjetnickoImeIzvodaca);

        JLabel lblDatumNastupa = new JLabel("Datum nastupa:");
        lblDatumNastupa.setBounds(10, 76, 82, 13);
        contentPanel.add(lblDatumNastupa);

        textField_DatumNastupa = new JTextField();
        textField_DatumNastupa.setBounds(132, 73, 161, 19);
        contentPanel.add(textField_DatumNastupa);
        textField_DatumNastupa.setColumns(10);

        JLabel lblMjestoNastupa = new JLabel("Mjesto nastupa:");
        lblMjestoNastupa.setBounds(10, 123, 82, 13);
        contentPanel.add(lblMjestoNastupa);

        textField_MjestoNastupa = new JTextField();
        textField_MjestoNastupa.setBounds(132, 120, 161, 19);
        contentPanel.add(textField_MjestoNastupa);
        textField_MjestoNastupa.setColumns(10);

        JLabel lblSifraNastupa = new JLabel("Šifra nastupa:");
        lblSifraNastupa.setBounds(10, 172, 82, 13);
        contentPanel.add(lblSifraNastupa);

        textField_SifraNastupa = new JTextField();
        textField_SifraNastupa.setBounds(132, 169, 161, 19);
        contentPanel.add(textField_SifraNastupa);
        textField_SifraNastupa.setColumns(10);

        loadIzvodaci(); // Učitaj izvođače u comboBox

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String UmjetnickoImeIzvodaca = (String) comboBox_UmjetnickoImeIzvodaca.getSelectedItem();
                String DatumNastupa = textField_DatumNastupa.getText();
                String MjestoNastupa = textField_MjestoNastupa.getText();
                String SifraNastupa = textField_SifraNastupa.getText();

                if (UmjetnickoImeIzvodaca.equals("") || DatumNastupa.equals("") || MjestoNastupa.equals("") || SifraNastupa.equals("")) {
                    JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                    Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://ucka.veleri.hr/mroncevic?" +
                            "user=mroncevic&password=11");

                    // Pronađi šifru izvođača na temelju umjetničkog imena
                    String sqlFindIzvodac = "SELECT Sifra_izvodaca FROM Izvodac WHERE UmjetnickoIme_izvodaca = ?";
                    PreparedStatement stmtFindIzvodac = conn.prepareStatement(sqlFindIzvodac);
                    stmtFindIzvodac.setString(1, UmjetnickoImeIzvodaca);
                    ResultSet rs = stmtFindIzvodac.executeQuery();
                    int SifraIzvodaca = 0;
                    if (rs.next()) {
                        SifraIzvodaca = rs.getInt("Sifra_izvodaca");
                    } else {
                        JOptionPane.showMessageDialog(null, "Izvođač nije pronađen", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String sql = "INSERT INTO Nastup (Sifra_nastupa, Datum_nastupa, Mjesto_nastupa, Sifra_izvodaca) VALUES(?, ?, ?, ?);";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, SifraNastupa);
                    stmt.setString(2, DatumNastupa);
                    stmt.setString(3, MjestoNastupa);
                    stmt.setInt(4, SifraIzvodaca);

                    stmt.execute();

                    conn.close();

                    textField_SifraNastupa.setText("");
                    textField_DatumNastupa.setText("");
                    textField_MjestoNastupa.setText("");
                    comboBox_UmjetnickoImeIzvodaca.setSelectedIndex(-1);

                    JOptionPane.showMessageDialog(null, "Podaci su uspješno uneseni.", "Informacija", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    // Metoda za učitavanje izvođača u JComboBox
    private void loadIzvodaci() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://ucka.veleri.hr/mroncevic?" +
                    "user=mroncevic&password=11");

            String sql = "SELECT UmjetnickoIme_izvodaca FROM Izvodac";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comboBox_UmjetnickoImeIzvodaca.addItem(rs.getString("UmjetnickoIme_izvodaca"));
            }

            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
