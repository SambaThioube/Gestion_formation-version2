import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupprimerEtudiant extends JFrame {
    private JPanel contentPane;
    private JTextField etudiant;
    private JButton btnAdd;
    public SupprimerEtudiant(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 140, 1000, 590);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Supprimer Etudiant");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblFiliere = new JLabel("Numero carte Etudiant :");
        lblFiliere.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFiliere.setBounds(108, 152, 919, 43);
        contentPane.add(lblFiliere);

        etudiant = new JTextField();
        etudiant.setFont(new Font("Tahoma", Font.PLAIN, 20));
        etudiant.setBounds(310, 152, 138, 35);
        contentPane.add(etudiant);
        etudiant.setColumns(10);

        setVisible(true);

        btnAdd = new JButton("Supprimer");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnAdd.setBounds(455, 152, 123, 34);
        contentPane.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(btnAdd, "Etes vous sur de vouloir supprimer cet etudiant");
                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_etudiant", "root", "");

                    String query1 = "SELECT * FROM etudiant WHERE carte = '"+etudiant.getText()+"' ";
                    String query2 = "DELETE from etudiant WHERE carte = '"+etudiant.getText()+"' ";

                    Statement sta = connection.createStatement();
                    ResultSet res = sta.executeQuery(query1);
                    res.last(); int nbLigne=res.getRow();res.beforeFirst();
                    if (nbLigne == 0) {
                        JOptionPane.showMessageDialog(btnAdd, "Cet etudiant n'existe pas dans la base de données");
                    } else {
                        connection.createStatement().executeUpdate(query2);
                        JOptionPane.showMessageDialog(btnAdd, "L'etudiant "+etudiant.getText()+" a ete supprimer");

                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
