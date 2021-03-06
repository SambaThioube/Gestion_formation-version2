import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreerEtudiant extends JFrame {
    private JPanel contentPane;
    private JTextField numeroCarte;
    private JTextField nom;
    private JTextField prenom;
    private JTextField classe;
    private JTextField filiere;
    private JButton btnNewButton;

    public CreerEtudiant(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 140, 1000, 590);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Nouvel Etudiant");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblCarteEtudiant = new JLabel("N° Carte");
        lblCarteEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCarteEtudiant.setBounds(58, 152, 99, 43);
        contentPane.add(lblCarteEtudiant);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNom.setBounds(58, 243, 110, 29);
        contentPane.add(lblNom);

        JLabel lblPrenom = new JLabel("Prénom");
        lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrenom.setBounds(58, 324, 124, 36);
        contentPane.add(lblPrenom);

        numeroCarte = new JTextField();
        numeroCarte.setFont(new Font("Tahoma", Font.PLAIN, 32));
        numeroCarte.setBounds(214, 151, 228, 50);
        contentPane.add(numeroCarte);
        numeroCarte.setColumns(10);

        nom = new JTextField();
        nom.setFont(new Font("Tahoma", Font.PLAIN, 32));
        nom.setBounds(214, 235, 228, 50);
        contentPane.add(nom);
        nom.setColumns(10);

        prenom = new JTextField();
        prenom.setFont(new Font("Tahoma", Font.PLAIN, 32));
        prenom.setBounds(214, 320, 228, 50);
        contentPane.add(prenom);
        prenom.setColumns(10);

        JLabel lblClasse = new JLabel("Niveau Etude");
        lblClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblClasse.setBounds(542, 159, 99, 29);
        contentPane.add(lblClasse);

        JLabel lblFiliere = new JLabel("Filiére");
        lblFiliere.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFiliere.setBounds(542, 245, 99, 24);
        contentPane.add(lblFiliere);

        classe = new JTextField();
        classe.setFont(new Font("Tahoma", Font.PLAIN, 32));
        classe.setBounds(707, 151, 228, 50);
        contentPane.add(classe);
        classe.setColumns(10);

        filiere = new JTextField();
        filiere.setFont(new Font("Tahoma", Font.PLAIN, 32));
        filiere.setBounds(707, 235, 228, 50);
        contentPane.add(filiere);

        btnNewButton = new JButton("Ajouter");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(399, 447, 259, 74);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String carteEtudiant = numeroCarte.getText();
                String nomEtudiant = nom.getText();
                String prenomEtudiant = prenom.getText();
                String niveauEtudiant = classe.getText();
                String filiereEtudiant = filiere.getText();
                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_etudiant", "root", "");

                    String query1 = "SELECT * FROM filiere where nom='"+filiereEtudiant+"'";
                    String query3 = "SELECT * FROM etudiant where carte='"+carteEtudiant+"'";
                    String query2 = "INSERT INTO etudiant values(0,'" + carteEtudiant + "','" + nomEtudiant + "','" + prenomEtudiant + "','" +
                            niveauEtudiant + "','" + filiereEtudiant + "')";

                    Statement sta = connection.createStatement();
                    ResultSet res = sta.executeQuery(query1);
                    res.last();
                    int nbLigne1 = res.getRow();res.beforeFirst();
                    ResultSet res1 = connection.createStatement().executeQuery(query3);
                    res1.last();
                    int nbLigne2 = res1.getRow(); res1.beforeFirst();
                    if (nbLigne1 == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "L'IPSL ne propose pas cette filiere");
                    }else if (nbLigne2 > 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "Cette etudiant existe deja");
                    } else {
                        connection.createStatement().executeUpdate(query2);
                        JOptionPane.showMessageDialog(btnNewButton, "L'etudiant " + carteEtudiant + " a bien ete ajouté");

                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            });
    }
}
