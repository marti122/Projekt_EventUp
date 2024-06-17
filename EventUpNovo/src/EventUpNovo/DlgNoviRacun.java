package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DlgNoviRacun extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField_SifraRacuna;
    private JTextField textField_Iznos;
    private JTextField textField_DatumIzradeRacuna;
    private JComboBox<String> comboBox_Organizator;
    private JComboBox<String> comboBox_SifraNastupa;

    public static void main(String[] args) {
        try {
            DlgNoviRacun dialog = new DlgNoviRacun();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgNoviRacun() {
        setTitle("Novi račun");
        setBounds(100, 100, 574, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblOrganizator = new JLabel("Organizator:");
        lblOrganizator.setBounds(10, 30, 92, 13);
        contentPanel.add(lblOrganizator);

        comboBox_Organizator = new JComboBox<>();
        comboBox_Organizator.setBounds(112, 26, 100, 21);
        contentPanel.add(comboBox_Organizator);

        JLabel lblSifraRacuna = new JLabel("Šifra računa:");
        lblSifraRacuna.setBounds(10, 73, 81, 13);
        contentPanel.add(lblSifraRacuna);

        textField_SifraRacuna = new JTextField();
        textField_SifraRacuna.setBounds(112, 70, 100, 19);
        contentPanel.add(textField_SifraRacuna);
        textField_SifraRacuna.setColumns(10);

        JLabel lblIznos = new JLabel("Iznos:");
        lblIznos.setBounds(10, 119, 45, 13);
        contentPanel.add(lblIznos);

        textField_Iznos = new JTextField();
        textField_Iznos.setBounds(112, 116, 100, 19);
        contentPanel.add(textField_Iznos);
        textField_Iznos.setColumns(10);

        JLabel lblSifraNastupa = new JLabel("Šifra nastupa:");
        lblSifraNastupa.setBounds(259, 30, 71, 13);
        contentPanel.add(lblSifraNastupa);

        comboBox_SifraNastupa = new JComboBox<>();
        comboBox_SifraNastupa.setBounds(378, 26, 108, 21);
        contentPanel.add(comboBox_SifraNastupa);

        JLabel lblDatumIzradeRacuna = new JLabel("Datum izrade računa:");
        lblDatumIzradeRacuna.setBounds(259, 73, 100, 13);
        contentPanel.add(lblDatumIzradeRacuna);

        textField_DatumIzradeRacuna = new JTextField();
        textField_DatumIzradeRacuna.setBounds(378, 70, 108, 19);
        contentPanel.add(textField_DatumIzradeRacuna);
        textField_DatumIzradeRacuna.setColumns(10);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveRacun();
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

        loadOrganizatori();
        loadSifraNastupa();
    }

    private void loadOrganizatori() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://ucka.veleri.hr/mroncevic?" +
                "user=mroncevic&password=11");

            String sql = "SELECT Naziv_organizatora FROM Organizator";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comboBox_Organizator.addItem(rs.getString("Naziv_organizatora"));
            }

            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSifraNastupa() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://ucka.veleri.hr/mroncevic?" +
                "user=mroncevic&password=11");

            String sql = "SELECT Nastup.Sifra_nastupa, Izvodac.UmjetnickoIme_izvodaca FROM Nastup INNER JOIN Izvodac ON Nastup.Sifra_izvodaca = Izvodac.Sifra_izvodaca";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comboBox_SifraNastupa.addItem(rs.getString("Sifra_nastupa") + " - " + rs.getString("UmjetnickoIme_izvodaca"));
            }

            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveRacun() {
        try {
            String sifraRacuna = textField_SifraRacuna.getText();
            String iznos = textField_Iznos.getText();
            String datum = textField_DatumIzradeRacuna.getText();
            String organizator = (String) comboBox_Organizator.getSelectedItem();
            String sifraNastupa = (String) comboBox_SifraNastupa.getSelectedItem();

            if (sifraRacuna.isEmpty() || iznos.isEmpty() || datum.isEmpty() || organizator.isEmpty() || sifraNastupa.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena", "Greška", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (isSifraRacunaAlreadyExists(sifraRacuna)) {
                JOptionPane.showMessageDialog(null, "Šifra računa već postoji u bazi!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://ucka.veleri.hr/mroncevic?" +
                "user=mroncevic&password=11");

            String sqlFindOrganizator = "SELECT Sifra_organizatora FROM Organizator WHERE Naziv_organizatora = ?";
            PreparedStatement stmtFindOrganizator = conn.prepareStatement(sqlFindOrganizator);
            stmtFindOrganizator.setString(1, organizator);
            ResultSet rs = stmtFindOrganizator.executeQuery();
            int sifraOrganizatora = 0;
            if (rs.next()) {
                sifraOrganizatora = rs.getInt("Sifra_organizatora");
            } else {
                JOptionPane.showMessageDialog(null, "Organizator nije pronađen", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sifraNastupaClean = sifraNastupa.split(" - ")[0];
			String sql = "INSERT INTO Racun (Sifra_racuna, Datum_racuna, Iznos_racuna, Sifra_organizatora, Sifra_nastupa) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sifraRacuna);
			stmt.setString(2, datum);
			stmt.setString(3, iznos);
			stmt.setInt(4, sifraOrganizatora);
			stmt.setString(5, sifraNastupaClean);

			stmt.execute();

			conn.close();

			// Reset fields
			textField_SifraRacuna.setText("");
			textField_Iznos.setText("");
			textField_DatumIzradeRacuna.setText("");
			comboBox_Organizator.setSelectedIndex(-1);
			comboBox_SifraNastupa.setSelectedIndex(-1);

			JOptionPane.showMessageDialog(null, "Račun je uspješno spremljen!", "Informacija", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean isSifraRacunaAlreadyExists(String sifraRacuna) {
		boolean exists = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://ucka.veleri.hr/mroncevic?" +
							"user=mroncevic&password=11");

			String sql = "SELECT COUNT(*) FROM Racun WHERE Sifra_racuna = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sifraRacuna);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				exists = rs.getInt(1) > 0;
			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
		}

		return exists;
	}
}
