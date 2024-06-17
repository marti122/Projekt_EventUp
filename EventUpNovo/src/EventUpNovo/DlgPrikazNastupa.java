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
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgPrikazNastupa extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextArea textAreaPrikazNastupa; // Correctly declared as a class attribute

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgPrikazNastupa dialog = new DlgPrikazNastupa();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgPrikazNastupa() {
        setTitle("Prikaz nastupa");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

<<<<<<< HEAD
       
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 25, 384, 170); 
        contentPanel.add(scrollPane);

        
        textAreaPrikazNastupa = new JTextArea();
        scrollPane.setViewportView(textAreaPrikazNastupa);

    
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

       
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        buttonPane.add(cancelButton);

        
        selectPrikazNastupa();
    }

    private void selectPrikazNastupa() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/mroncevic?user=mroncevic&password=11");

           
            String sql = "SELECT * FROM Nastup ORDER BY `Datum_nastupa` ASC;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

           
            StringBuilder tekst = new StringBuilder();
            while (rs.next()) {
                tekst.append("Datum nastupa: ").append(rs.getString("Datum_nastupa")).append("\t");
                tekst.append("Mjesto nastupa: ").append(rs.getString("Mjesto_nastupa")).append("\t");
                tekst.append("Šifra izvođača: ").append(rs.getString("Sifra_izvodaca")).append("\n");
            }

          
            textAreaPrikazNastupa.setText(tekst.toString());

            
=======
        // Create JScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 25, 384, 170); // Adjusted bounds to fit within the dialog
        contentPanel.add(scrollPane);

        // Create JTextArea and add it to JScrollPane
        textAreaPrikazNastupa = new JTextArea(); // Initialize the class attribute
        scrollPane.setViewportView(textAreaPrikazNastupa);

        // Create button panel
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // OK button
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });
        buttonPane.add(cancelButton);

        // Call method to select and display performances
        selectPrikazNastupa();
    }

    private void selectPrikazNastupa() {
        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/mroncevic?user=mroncevic&password=11");

            // Execute query
            String sql = "SELECT * FROM Nastup";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Prepare text for JTextArea
            StringBuilder tekst = new StringBuilder();
            while (rs.next()) {
                tekst.append("Datum nastupa: ").append(rs.getString("Datum_nastupa")).append("\t");
                tekst.append("Mjesto nastupa: ").append(rs.getString("Mjesto_nastupa")).append("\t");
                tekst.append("Šifra izvođača: ").append(rs.getString("Sifra_izvodaca")).append("\n");
            }

            // Set text in JTextArea
            textAreaPrikazNastupa.setText(tekst.toString());

            // Close database connection
>>>>>>> branch 'Main' of https://github.com/marti122/Projekt_EventUp.git
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
