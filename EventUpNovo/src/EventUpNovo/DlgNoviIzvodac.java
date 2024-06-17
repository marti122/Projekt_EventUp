package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DlgNoviIzvodac extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField_SifraIzvodaca;
    private JTextField textField_ImeIzvodaca;
    private JTextField textField_PrezimeIzvodaac;
    private JTextField textField_UmjetnickoImeIzvodaca;
    private JTextField textField_KontakIzvodaca;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgNoviIzvodac dialog = new DlgNoviIzvodac();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgNoviIzvodac() {
        setTitle("Novi izvođač");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        {
            JLabel lblNewLabel = new JLabel("Molimo vas da ispunite sva polja!");
            lblNewLabel.setBounds(10, 21, 163, 13);
            contentPanel.add(lblNewLabel);
        }
        {
            JLabel lblSifraIzvodaca = new JLabel("Šifra izvođača:");
            lblSifraIzvodaca.setBounds(10, 57, 76, 13);
            contentPanel.add(lblSifraIzvodaca);
        }
        
        textField_SifraIzvodaca = new JTextField();
        textField_SifraIzvodaca.setBounds(135, 54, 136, 19);
        contentPanel.add(textField_SifraIzvodaca);
        textField_SifraIzvodaca.setColumns(10);
        
        JLabel lblImeIzvodaca = new JLabel("Ime izvođača:");
        lblImeIzvodaca.setBounds(10, 93, 76, 13);
        contentPanel.add(lblImeIzvodaca);
        
        textField_ImeIzvodaca = new JTextField();
        textField_ImeIzvodaca.setBounds(135, 90, 136, 19);
        contentPanel.add(textField_ImeIzvodaca);
        textField_ImeIzvodaca.setColumns(10);
        
        JLabel lblPrezimeIzvodaca = new JLabel("Prezime izvođača:");
        lblPrezimeIzvodaca.setBounds(10, 131, 89, 13);
        contentPanel.add(lblPrezimeIzvodaca);
        
        textField_PrezimeIzvodaac = new JTextField();
        textField_PrezimeIzvodaac.setBounds(135, 128, 136, 19);
        contentPanel.add(textField_PrezimeIzvodaac);
        textField_PrezimeIzvodaac.setColumns(10);
        
        JLabel lblUmjetnickoImeIzvodaca = new JLabel("Umjetničko ime izvođača");
        lblUmjetnickoImeIzvodaca.setBounds(10, 168, 113, 13);
        contentPanel.add(lblUmjetnickoImeIzvodaca);
        
        textField_UmjetnickoImeIzvodaca = new JTextField();
        textField_UmjetnickoImeIzvodaca.setBounds(135, 165, 136, 19);
        contentPanel.add(textField_UmjetnickoImeIzvodaca);
        textField_UmjetnickoImeIzvodaca.setColumns(10);
        
        JLabel lblKontakIzvodaca = new JLabel("Kontakt izvođača");
        lblKontakIzvodaca.setBounds(10, 209, 113, 13);
        contentPanel.add(lblKontakIzvodaca);
        
        textField_KontakIzvodaca = new JTextField();
        textField_KontakIzvodaca.setBounds(137, 206, 134, 19);
        contentPanel.add(textField_KontakIzvodaca);
        textField_KontakIzvodaca.setColumns(10);
        
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                okButton.addActionListener(e -> {
                    try {                        
                        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                        Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://ucka.veleri.hr/mroncevic?" +
                            "user=mroncevic&password=11");
                        
                        String sifraIzvodaca = textField_SifraIzvodaca.getText();
                        String imeIzvodaca = textField_ImeIzvodaca.getText();
                        String umjetnickoImeIzvodaca = textField_UmjetnickoImeIzvodaca.getText();
                       
                        
                        if (sifraIzvodaca.isEmpty() || imeIzvodaca.isEmpty() || umjetnickoImeIzvodaca.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nisu sva obavezna polja popunjena", "Greška", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        
                        String sql = "INSERT INTO Izvodac (Sifra_izvodaca, Ime_izvodaca, Prezime_izvodaca, UmjetnickoIme_izvodaca, Kontakt_izvodaca) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, textField_SifraIzvodaca.getText());
                        stmt.setString(2, textField_ImeIzvodaca.getText());
                        stmt.setString(3, textField_PrezimeIzvodaac.getText());
                        stmt.setString(4, textField_UmjetnickoImeIzvodaca.getText());
                        stmt.setString(5, textField_KontakIzvodaca.getText());
                        
                   
                        
                        stmt.execute();
                        
                        conn.close();
                        
                        textField_SifraIzvodaca.setText("");
                        textField_ImeIzvodaca.setText("");
                        textField_PrezimeIzvodaac.setText("");
                        textField_UmjetnickoImeIzvodaca.setText("");
                        textField_KontakIzvodaca.setText("");
                        
                        JOptionPane.showMessageDialog(null, "Podaci su uspješno uneseni.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(e -> dispose());
                buttonPane.add(cancelButton);
            }
        }
    }
}


