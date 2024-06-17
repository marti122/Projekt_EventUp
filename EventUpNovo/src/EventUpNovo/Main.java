package EventUpNovo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Font;

public class Main {

    private JFrame frmEventup;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frmEventup.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmEventup = new JFrame();
        frmEventup.setTitle("EventUp");
        frmEventup.setBounds(100, 100, 481, 334);
        frmEventup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEventup.getContentPane().setLayout(null);

        JButton btnNoviOrganizator = new JButton("Novi organizator");
        btnNoviOrganizator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgNoviOrganizator dialog = new DlgNoviOrganizator();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnNoviOrganizator.setForeground(Color.BLUE);
        btnNoviOrganizator.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNoviOrganizator.setBackground(Color.LIGHT_GRAY);
        btnNoviOrganizator.setBounds(14, 55, 164, 38);
        frmEventup.getContentPane().add(btnNoviOrganizator);

        JButton btnNoviIzvođač = new JButton("Novi izvođač");
        btnNoviIzvođač.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgNoviIzvodac dialog = new DlgNoviIzvodac();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnNoviIzvođač.setForeground(Color.BLUE);
        btnNoviIzvođač.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNoviIzvođač.setBackground(Color.LIGHT_GRAY);
        btnNoviIzvođač.setBounds(14, 110, 164, 38);
        frmEventup.getContentPane().add(btnNoviIzvođač);

        JButton btnNoviNastup = new JButton("Novi nastup");
        btnNoviNastup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgNoviNastup dialog = new DlgNoviNastup();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnNoviNastup.setBackground(Color.LIGHT_GRAY);
        btnNoviNastup.setForeground(Color.BLUE);
        btnNoviNastup.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNoviNastup.setBounds(14, 168, 164, 38);
        frmEventup.getContentPane().add(btnNoviNastup);

        JButton btnNoviRacun = new JButton("Novi račun");
        btnNoviRacun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgNoviRacun dialog = new DlgNoviRacun();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnNoviRacun.setForeground(Color.BLUE);
        btnNoviRacun.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNoviRacun.setBackground(Color.LIGHT_GRAY);
        btnNoviRacun.setBounds(14, 217, 164, 38);
        frmEventup.getContentPane().add(btnNoviRacun);

        JButton btnPrikazOrganizatora = new JButton("Prikaz organizatora");
        btnPrikazOrganizatora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgPrikazOrganizatora dialog = new DlgPrikazOrganizatora();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnPrikazOrganizatora.setForeground(Color.BLUE);
        btnPrikazOrganizatora.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPrikazOrganizatora.setBackground(Color.LIGHT_GRAY);
        btnPrikazOrganizatora.setBounds(218, 55, 177, 38);
        frmEventup.getContentPane().add(btnPrikazOrganizatora);

        JButton btnPrikazIzvodaca = new JButton("Prikaz izvođača");
        btnPrikazIzvodaca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgPrikazIzvodaca dialog = new DlgPrikazIzvodaca();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnPrikazIzvodaca.setForeground(Color.BLUE);
        btnPrikazIzvodaca.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPrikazIzvodaca.setBackground(Color.LIGHT_GRAY);
        btnPrikazIzvodaca.setBounds(218, 110, 177, 38);
        frmEventup.getContentPane().add(btnPrikazIzvodaca);

        JButton btnPrikazNastupa = new JButton("Prikaz nastupa");
        btnPrikazNastupa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgPrikazNastupa dialog = new DlgPrikazNastupa();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnPrikazNastupa.setForeground(Color.BLUE);
        btnPrikazNastupa.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPrikazNastupa.setBackground(Color.LIGHT_GRAY);
        btnPrikazNastupa.setBounds(218, 168, 177, 38);
        frmEventup.getContentPane().add(btnPrikazNastupa);

        JButton btnPrikazRacuna = new JButton("Prikaz računa");
        btnPrikazRacuna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgPrikazRacuna dialog = new DlgPrikazRacuna();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        btnPrikazRacuna.setForeground(Color.BLUE);
        btnPrikazRacuna.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPrikazRacuna.setBackground(Color.LIGHT_GRAY);
        btnPrikazRacuna.setBounds(218, 217, 177, 38);
        frmEventup.getContentPane().add(btnPrikazRacuna);

        JMenuBar menuBar = new JMenuBar();
        frmEventup.setJMenuBar(menuBar);

        JMenu mnIzvodac = new JMenu("Izvođač");
        menuBar.add(mnIzvodac);

        JMenuItem mntmNoviIzvodac = new JMenuItem("Novi izvođač");
        mntmNoviIzvodac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgNoviIzvodac dialog = new DlgNoviIzvodac();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        mnIzvodac.add(mntmNoviIzvodac);

        JMenuItem mntmPrikazIzvodaca = new JMenuItem("Prikaz izvođača");
        mntmPrikazIzvodaca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgPrikazIzvodaca dialog = new DlgPrikazIzvodaca();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        mnIzvodac.add(mntmPrikazIzvodaca);

        JMenu mnOrganizator = new JMenu("Organizator");
        menuBar.add(mnOrganizator);

        JMenuItem mntmNoviOrganizator = new JMenuItem("Novi organizator");
        mntmNoviOrganizator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgNoviOrganizator dialog = new DlgNoviOrganizator();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        mnOrganizator.add(mntmNoviOrganizator);

        JMenuItem mntmPrikazOrganizatora = new JMenuItem("Prikaz organizatora");
        mntmPrikazOrganizatora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgPrikazOrganizatora dialog = new DlgPrikazOrganizatora();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
        mnOrganizator.add(mntmPrikazOrganizatora);
		
		JMenu mnNastupi = new JMenu("Nastupi");
		menuBar.add(mnNastupi);
		
		JMenuItem mntmNoviNastup = new JMenuItem("Novi nastup");
		mntmNoviNastup.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DlgNoviNastup dialog = new DlgNoviNastup();
		        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    }
		});
		mnNastupi.add(mntmNoviNastup);

		JMenuItem mntmPrikazNastupa = new JMenuItem("Prikaz nastupa");
		mntmPrikazNastupa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DlgPrikazNastupa dialog = new DlgPrikazNastupa();
		        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    }
		});
		mnNastupi.add(mntmPrikazNastupa);

		JMenu mnRacun = new JMenu("Račun");
		menuBar.add(mnRacun);

		JMenuItem mntmNoviRacun = new JMenuItem("Novi račun");
		mntmNoviRacun.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DlgNoviRacun dialog = new DlgNoviRacun();
		        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    }
		});
		mnRacun.add(mntmNoviRacun);

		JMenuItem mntmPrikazRacuna = new JMenuItem("Prikaz računa");
		mntmPrikazRacuna.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DlgPrikazRacuna dialog = new DlgPrikazRacuna();
		        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    }
		});
		mnRacun.add(mntmPrikazRacuna);

		JMenu mnPomoc = new JMenu("Pomoć");
		menuBar.add(mnPomoc);

		JMenuItem mntmPomoc = new JMenuItem("Pomoć");
		mntmPomoc.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DlgPomoc dialog = new DlgPomoc();
		        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    }
		});
		mnPomoc.add(mntmPomoc);
    }
}