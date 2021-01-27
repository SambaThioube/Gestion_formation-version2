import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {
    private JPanel contentPane;
    private JTextField classe;
    private JTextField filiere;
    private JButton btnRecherce;
    private JButton btnAddEtudiant;
    private JButton btnAddFiliere;
    private JButton btnDeleteFiliere;
    private JButton btnDeleteEtudiant;
    private JLabel titreLabel;

    public Accueil(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 240, 1000, 590);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        titreLabel = new JLabel("GESTION DE FORMATION DE L'IPSL");
        titreLabel.setForeground(Color.orange);
        titreLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        titreLabel.setBounds(200, 40, 700, 50);
        contentPane.add(titreLabel);

        JLabel lblClasse = new JLabel("Niveau :");
        lblClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblClasse.setBounds(50, 252, 90, 43);
        contentPane.add(lblClasse);

        JLabel lblFiliere = new JLabel("Filiére :");
        lblFiliere.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFiliere.setBounds(380, 265, 90, 24);
        contentPane.add(lblFiliere);

        classe = new JTextField();
        classe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        classe.setBounds(170, 261, 180, 30);
        contentPane.add(classe);
        classe.setColumns(10);

        filiere = new JTextField();
        filiere.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filiere.setBounds(500, 261, 160, 30);
        contentPane.add(filiere);

        btnRecherce = new JButton("Rechercher");
        btnRecherce.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnRecherce.setBounds(700, 261, 159, 30);
        btnRecherce.setBackground(Color.ORANGE);
        contentPane.add(btnRecherce);

        btnAddEtudiant = new JButton("Ajouter Etudiant");
        btnAddEtudiant.setFont(new Font("Tahoma",Font.PLAIN,18));
        btnAddEtudiant.setBounds(50,180,200,30);
        btnAddEtudiant.setBackground(Color.ORANGE);
        contentPane.add(btnAddEtudiant);

        btnAddFiliere = new JButton("Ajouter Filière");
        btnAddFiliere.setFont(new Font("Tahoma",Font.PLAIN,18));
        btnAddFiliere.setBounds(300,180,150,30);
        btnAddFiliere.setBackground(Color.ORANGE);
        contentPane.add(btnAddFiliere);

        btnDeleteEtudiant = new JButton("Supprimer Etudiant");
        btnDeleteEtudiant.setFont(new Font("Tahoma",Font.PLAIN,18));
        btnDeleteEtudiant.setBounds(500,180,200,30);
        btnDeleteEtudiant.setBackground(Color.ORANGE);
        contentPane.add(btnDeleteEtudiant);

        btnDeleteFiliere = new JButton("Supprimer Filiere");
        btnDeleteFiliere.setFont(new Font("Tahoma",Font.PLAIN,18));
        btnDeleteFiliere.setBounds(750,180,200,30);
        btnDeleteFiliere.setBackground(Color.ORANGE);
        contentPane.add(btnDeleteFiliere);

        setVisible(true);

        btnRecherce.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                String nomClasse = classe.getText();
                String nomFiliere = filiere.getText();
                ListEtudiant listEtudiant = new ListEtudiant(nomClasse,nomFiliere);
            }
        });
        btnAddEtudiant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                CreerEtudiant formulaireEtudiant = new CreerEtudiant();
                formulaireEtudiant.setVisible(true);
            }
        });
        btnAddFiliere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new CreerFiliere();
            }
        });
        btnDeleteFiliere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                SupprimerFiliere supprimerFil = new SupprimerFiliere();
                supprimerFil.setVisible(true);
            }
        });
        btnDeleteEtudiant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                SupprimerEtudiant supprimerEtu = new SupprimerEtudiant();
                supprimerEtu.setVisible(true);
            }
        });


    }
}
