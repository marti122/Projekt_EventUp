package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DlgNoviOrganizator extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_SifraOrganizatora;
	private JTextField textField_NazivOrganizatora;
	private JTextField textField_KontaktOrganizatora;
	private JTextField textField_LokacijaOrganizatora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgNoviOrganizator dialog = new DlgNoviOrganizator();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgNoviOrganizator() {
		setTitle("Novi organizator");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Molimo vas da ispunite sva polja!");
			lblNewLabel.setBounds(10, 21, 159, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblSifraOrganizatora = new JLabel("Šifra organizatora:");
			lblSifraOrganizatora.setBounds(10, 57, 94, 13);
			contentPanel.add(lblSifraOrganizatora);
		}
		{
			textField_SifraOrganizatora = new JTextField();
			textField_SifraOrganizatora.setBounds(145, 54, 138, 19);
			contentPanel.add(textField_SifraOrganizatora);
			textField_SifraOrganizatora.setColumns(10);
		}
		{
			JLabel lblNazivOrganizatora = new JLabel("Naziv organizatora:");
			lblNazivOrganizatora.setBounds(10, 95, 94, 13);
			contentPanel.add(lblNazivOrganizatora);
		}
		{
			textField_NazivOrganizatora = new JTextField();
			textField_NazivOrganizatora.setBounds(145, 92, 138, 19);
			contentPanel.add(textField_NazivOrganizatora);
			textField_NazivOrganizatora.setColumns(10);
		}
		{
			JLabel lblKontaktOrganizatora = new JLabel("Kontakt organizatora:");
			lblKontaktOrganizatora.setBounds(10, 136, 113, 13);
			contentPanel.add(lblKontaktOrganizatora);
		}
		{
			textField_KontaktOrganizatora = new JTextField();
			textField_KontaktOrganizatora.setBounds(146, 133, 137, 19);
			contentPanel.add(textField_KontaktOrganizatora);
			textField_KontaktOrganizatora.setColumns(10);
		}
		{
			JLabel lblLokacijaOrganizatora = new JLabel("Lokacija organizatora:");
			lblLokacijaOrganizatora.setBounds(10, 181, 113, 13);
			contentPanel.add(lblLokacijaOrganizatora);
		}
		{
			textField_LokacijaOrganizatora = new JTextField();
			textField_LokacijaOrganizatora.setBounds(145, 178, 138, 19);
			contentPanel.add(textField_LokacijaOrganizatora);
			textField_LokacijaOrganizatora.setColumns(10);
		}
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
                        
                        String sql = "INSERT INTO Organizator (Sifra_organizatora, Naziv_organizatora, Kontakt_organizatora, Lokacija_organizatora) VALUES (?, ?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, textField_SifraOrganizatora.getText());
                        stmt.setString(2, textField_NazivOrganizatora.getText());
                        stmt.setString(3, textField_KontaktOrganizatora.getText());
                        stmt.setString(4, textField_LokacijaOrganizatora.getText());
                   
                        
                        stmt.execute();
                        
                        conn.close();
                        
                        textField_SifraOrganizatora.setText("");
                        textField_NazivOrganizatora.setText("");
                        textField_KontaktOrganizatora.setText("");
                        textField_LokacijaOrganizatora.setText("");
                   
                        
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



