import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



public class test extends JFrame implements ActionListener{
    JPanel panelmain, panelleft, panelrighthome, panelrightsetting, panelrightsell;
    JButton homeButton, settingButton, sellButton, searchButton, deleteButton, createButton, updateButton;
    CardLayout rightLayout = new CardLayout();

    public void GUI() {
        panelmain = new JPanel(new BorderLayout());

        panelrighthome = new JPanel(new FlowLayout());
        panelrighthome.setBackground(new Color(255,255,255));
    
        // left panel
        panelleft = new JPanel(new FlowLayout());
        panelleft.setBackground(new Color(255,255,255));
        panelleft.setPreferredSize(new Dimension(200, 1000));
        
        ImageIcon imageIcon = new ImageIcon("image/home.png");
        Image image = imageIcon.getImage();
        
        image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageIcon.setImage(image);
        homeButton = new JButton(imageIcon);
        homeButton.setPreferredSize(new Dimension(200, 50));
        homeButton.setBorderPainted(false);
        homeButton.setBackground(new Color(255,255,255));
        panelleft.add(homeButton);  

        imageIcon = new ImageIcon("image/settings.png");
        image = imageIcon.getImage();
        image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageIcon.setImage(image);
        settingButton = new JButton(imageIcon);
        settingButton.setPreferredSize(new Dimension(200, 50));
        settingButton.setBorderPainted(false);
        settingButton.setBackground(new Color(255,255,255));
        panelleft.add(settingButton);  

        imageIcon = new ImageIcon("image/selling.png");
        image = imageIcon.getImage();
        image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        imageIcon.setImage(image);
        sellButton = new JButton(imageIcon);
        sellButton.setPreferredSize(new Dimension(200, 50));
        sellButton.setBorderPainted(false);
        sellButton.setBackground(new Color(255,255,255));
        panelleft.add(sellButton);


        panelmain.add(panelleft, BorderLayout.WEST);
        panelmain.add(panelrighthome, BorderLayout.CENTER);
        add(panelmain);
        Dimension prop = new Dimension(1200, 700);
        setMaximumSize(prop);
        setMinimumSize(prop);
        setPreferredSize(prop);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public test(String s){
        super(s);
        GUI();
    }


    public static void main(String[] args) {
        new test("bai tap java");
    }
    
}