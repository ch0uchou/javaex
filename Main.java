import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;



public class Main extends JFrame implements ActionListener{

    /**
     * Book
     */ 

    JPanel panelmain, panelleft, panelright, panelrighthome, panelrightsetting, panelrightsell;
    JButton homeButton, settingButton, sellButton, searchButton, crudButton;
    CardLayout rightLayout = new CardLayout();
    TextField searchArea, settingArea;

    public void GUI() {
        panelmain = new JPanel(new BorderLayout());
        
        
        // left panel
        panelleft = new JPanel(new FlowLayout());
        panelleft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
        panelleft.setBackground(new Color(240, 238, 227));
        panelleft.setPreferredSize(new Dimension(100, 1000));
        
            ImageIcon imageIcon = new ImageIcon("image/home.png");
            Image image = imageIcon.getImage();
            
            image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            imageIcon.setImage(image);
            homeButton = new JButton(imageIcon);
            homeButton.setPreferredSize(new Dimension(100, 50));
            homeButton.setBackground(new Color(240, 238, 227));
            homeButton.setBorderPainted(false);
            panelleft.add(homeButton);    

            imageIcon = new ImageIcon("image/settings.png");
            image = imageIcon.getImage();
            image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            imageIcon.setImage(image);
            settingButton = new JButton(imageIcon);
            settingButton.setPreferredSize(new Dimension(100, 50));
            settingButton.setBackground(new Color(240, 238, 227));
            settingButton.setBorderPainted(false);
            panelleft.add(settingButton);  

            imageIcon = new ImageIcon("image/selling.png");
            image = imageIcon.getImage();
            image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            imageIcon.setImage(image);
            sellButton = new JButton(imageIcon);
            sellButton.setPreferredSize(new Dimension(100, 50));
            sellButton.setBackground(new Color(240, 238, 227));
            sellButton.setBorderPainted(false);
            panelleft.add(sellButton);


        // right panel
        
        
            //right home panel 
            panelrighthome = new JPanel(new BorderLayout());

                // search bar
                JPanel searchbar = new JPanel(new FlowLayout());
                searchbar.setBackground(new Color(240, 238, 227));
                panelrighthome.add(searchbar, BorderLayout.NORTH);

                JLabel searchLabel = new JLabel("Tìm kiếm :");
                searchLabel.setFont(new Font("Semibold", Font.BOLD, 15));
                searchbar.add(searchLabel);

                searchArea = new TextField("Tìm kiếm id, tên sách, tác giả, ...");
                searchArea.setFont(new Font("Semibold", Font.PLAIN, 15));
                searchArea.setBackground(new Color(240, 238, 227));
                searchbar.add(searchArea);

                imageIcon = new ImageIcon("image/search.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                sellButton = new JButton(imageIcon);
                sellButton.setBorderPainted(false);
                sellButton.setBackground(new Color(240, 238, 227));
                searchbar.add(sellButton);
                
                // search result
                JPanel resulPanel = new JPanel(new FlowLayout());
                resulPanel.setBackground(new Color(240, 238, 227));
                // panelrighthome.add(resulPanel, BorderLayout.CENTER);
                
                for (int i = 0; i < 50; i++) {
                    add_one_book(resulPanel, new Book(1,"Drama nuôi tôi lớn Loài người dạy tôi khôn","Pương Pương", "Tiểu Thuyết", "image/book.png", 97000, 2));
                }
                resulPanel.setPreferredSize(new Dimension(1100,105*resulPanel.getComponentCount()));
                
                JScrollPane scrollPane = new JScrollPane(resulPanel);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setBounds(100, 50, 110, 650);
                panelrighthome.add(scrollPane);
                
                
                ////       
                

                
            ////setting right panel
                panelrightsetting = new JPanel(new BorderLayout());


                // option bar
                JPanel optionbar = new JPanel(new FlowLayout());
                optionbar.setBackground(new Color(240, 238, 227));
                panelrightsetting.add(optionbar, BorderLayout.NORTH);

                JLabel inputLabel = new JLabel("Nhập sách mới");
                inputLabel.setFont(new Font("Semibold", Font.BOLD, 15));
                optionbar.add(inputLabel);

                // settingArea = new TextField();
                // settingArea.setFont(new Font("Semibold", Font.PLAIN, 15));
                // settingArea.setColumns(50);
                // settingArea.setBackground(new Color(240, 238, 227));
                // optionbar.add(settingArea);

                // crudButton = new JButton("Submit");
                // crudButton.setFont(new Font("Semibold", Font.BOLD, 15));
                // crudButton.setBackground(new Color(240, 238, 227));
                // optionbar.add(crudButton);

                
                
            ///setting 
                panelrightsell = new JPanel();
               
            
            panelright = new JPanel();
            panelright.setLayout(rightLayout);
            panelmain.add(panelright);
            panelright.add(panelrighthome, "1");
            panelright.add(panelrightsetting, "2");
            panelright.add(panelrightsell,"3");
            rightLayout.show(panelright, "1");

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "1");
            }
            
        });
        settingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "2");
            }
            
        });
        sellButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "3");
            }
            
        });

        panelmain.add(panelleft, BorderLayout.WEST);
        panelmain.add(panelright, BorderLayout.CENTER);
        add(panelmain);
        Dimension prop = new Dimension(1200, 700);
        setMaximumSize(prop);
        setMinimumSize(prop);
        setPreferredSize(prop);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void add_one_book(JPanel resulPanel, Book book) {
        JPanel newbook = new JPanel(new BorderLayout());
        newbook.setPreferredSize(new Dimension(500, 200));
        newbook.setBackground(new Color(201, 195, 159));
        resulPanel.add(newbook);

        
        ImageIcon imageIcon = new ImageIcon(book.get_url());
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(190, 200, Image.SCALE_SMOOTH);
        imageIcon.setImage(image);
        JButton label = new JButton(imageIcon);
        label.setPreferredSize(new Dimension(190, 300));
        label.setBackground(Color.WHITE);
        label.setCursor(new Cursor(12));
        newbook.add(label, BorderLayout.WEST);

        JPanel discription = new JPanel(new BorderLayout());
        discription.setPreferredSize(new Dimension(300, 200));
        discription.setBackground(new Color(201, 195, 159));
        newbook.add(discription);

        JTextArea textArea = new JTextArea(book.get_name());
        textArea.setEditable(true);
        textArea.setPreferredSize(new Dimension(300, 62));
        textArea.setFont(new Font("Semibold", Font.BOLD, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(201, 195, 159));
        discription.add(textArea,BorderLayout.NORTH);

        JTextArea textArea1 = new JTextArea("Tác giả: " + book.get_author() + "\nThể loại: " + book.get_category());
        textArea1.setEditable(false);
        textArea1.setPreferredSize(new Dimension(300, 45));
        textArea1.setFont(new Font("Semibold", Font.PLAIN, 18));
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea1.setBackground(new Color(201, 195, 159));
        discription.add(textArea1,BorderLayout.CENTER);


        JTextArea textArea2 = new JTextArea("Giá tiền: " + book.get_price() + "\nSố lượng: " + book.get_amount());
        textArea2.setEditable(false);
        textArea2.setPreferredSize(new Dimension(300, 45));
        textArea2.setFont(new Font("Semibold", Font.PLAIN, 18));
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setBackground(new Color(201, 195, 159));
        discription.add(textArea2,BorderLayout.SOUTH);


    }


    public void add_all_bookui(JPanel resultPanel){
        resultPanel.removeAll();
        //// lay du lieu tu database add_one_book
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public Main(String s){
        super(s);
        GUI();
    }


    public static void main(String[] args) {
        new Main("bai tap java");
    }
    
}