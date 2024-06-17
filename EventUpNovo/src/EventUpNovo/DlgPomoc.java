package EventUpNovo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class DlgPomoc extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DlgPomoc dialog = new DlgPomoc();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgPomoc() {
        setTitle("Pomoć");
        setBounds(100, 100, 495, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JTextArea txtrto = new JTextArea();
        txtrto.setFont(new Font("Arial", Font.PLAIN, 12));
        txtrto.setText("Što omogućava EventUp?\r\n\r\nAplikacijom EventUp možete unijeti nove izvođače, organizatore, nastupe te izraditi \r\nnove račune za određene nastupe. Osim unosa, EventUp vam omogućava prikaz svih \r\nizvođača, organizatora i nastupa iz baze podataka kao i prikaz svih izrađenih računa za odrađene nastupe.\r\n\r\nKome je namijenjen EventUp?\r\n\r\nAdministratorima koji unose podatke o organizatorima i izvođačima na različitim\r\n glazbenim festivalima diljem Hrvatske.");
        txtrto.setBackground(new Color(192, 192, 192));
        txtrto.setBounds(27, 21, 420, 180);
        contentPanel.add(txtrto);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // OK button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose()); // Lambda expression for ActionListener
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose()); // Lambda expression for ActionListener
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
}

