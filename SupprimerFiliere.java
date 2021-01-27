import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupprimerFiliere extends JFrame {
    private JPanel contentPane;
    private JTextField filiere;
    private JButton btnAdd;
    public SupprimerFiliere(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 140, 1000, 590);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Supprimer Filière");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblFiliere = new JLabel("Nom de la filiere :");
        lblFiliere.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFiliere.setBounds(108, 152, 919, 43);
        contentPane.add(lblFiliere);

        filiere = new JTextField();
        filiere.setFont(new Font("Tahoma", Font.PLAIN, 20));
        filiere.setBounds(310, 152, 138, 35);
        contentPane.add(filiere);
        filiere.setColumns(10);

        setVisible(true);

        btnAdd = new JButton("Supprimer");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnAdd.setBounds(455, 152, 123, 34);
        contentPane.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(btnAdd, "Si vous supprimer cette filliere, tout les etudiants qui sont inscrit serons supprimer");
                String nom  = filiere.getText();
                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_etudiant", "root", "");

                    String query1 = "SELECT * FROM filiere WHERE nom = '"+filiere.getText()+"' ";
                    String query2 = "DELETE from filiere WHERE nom = '"+filiere.getText()+"' ";
                    String query3 = "DELETE from etudiant WHERE filiere = '"+filiere.getText()+"' ";

                    Statement sta = connection.createStatement();
                    ResultSet res = sta.executeQuery(query1);
                    res.last(); int nbLigne=res.getRow();res.beforeFirst();
                    if (nbLigne == 0) {
                        JOptionPane.showMessageDialog(btnAdd, "Cette filiere n'existe pas dans la base de données");
                    } else {
                        connection.createStatement().executeUpdate(query2);
                        connection.createStatement().executeUpdate(query3);
                        JOptionPane.showMessageDialog(btnAdd, "La filiere "+filiere.getText()+" ainsi que tout les etudiqnt qui y sont inscrit a ete supprimer");

                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
