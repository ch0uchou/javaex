import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;


public class Main extends JFrame implements ActionListener{

    /**
     * Data
     */ 
    DataConnect dataConnect = new DataConnect();
    /**
     * Data
     */ 


    JPanel panelmain, panelleft, panelright, panelrighthome, panelrightsetting, panelrightsell, createPanel, readPanel, updatePanel, deletePanel, crudPanel;
    JButton homeButton, settingButton, sellButton, searchButton, createButton, readButton, updateButton, deleteButton, submitButton ;
    CardLayout rightLayout = new CardLayout();
    TextField searchArea, settingArea;
    JTextField name_Field, author_Field, category_Field, price_Field, amount_Field;
    String path="";
    File selectedimageFile;

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
                searchButton = new JButton(imageIcon);
                searchButton.setBorderPainted(false);
                searchButton.setBackground(new Color(240, 238, 227));
                searchbar.add(searchButton);
                
                // search result
                JPanel resultPanel = new JPanel(new FlowLayout());
                resultPanel.setBackground(new Color(240, 238, 227));
                JScrollPane scrollPane = new JScrollPane(resultPanel);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setBounds(100, 50, 110, 650);
                scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                panelrighthome.add(scrollPane);
            
                // for (int i = 0; i < 50; i++) {
                //     add_one_book(resultPanel, new Book(i,"Drama nuôi tôi lớn Loài người dạy tôi khôn","Pương Pương", "Tiểu Thuyết", "image/book.png", 97000, 2));
                // }
                // add_one_book(resultPanel, new Book(50,"Drama nuôi tôi lớn Loài người dạy tôi khôn","Pương Pương", "Tiểu Thuyết", "image/book.png", 97000, 2));
                // resultPanel.setPreferredSize(new Dimension(1100,105*resultPanel.getComponentCount()));
                add_all_bookui(resultPanel);
                ////       
                

                     /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////   
            ////setting right panel
                panelrightsetting = new JPanel(new BorderLayout());

                ////setting right panel
                panelrightsetting = new JPanel(new BorderLayout());
                
                // option panel

                CardLayout optLayout = new CardLayout();
                JPanel optPanel = new JPanel(optLayout);
                panelrightsetting.add(optPanel);
                crudPanel = new JPanel(new BorderLayout());
                crudPanel.setBackground(new Color(240, 238, 227));
                createPanel = new JPanel(new BorderLayout());
                createPanel.setBackground(new Color(240, 238, 227));
                // readPanel = new JPanel(new FlowLayout());
                // updatePanel = new JPanel(new FlowLayout());
                // deletePanel = new JPanel(new FlowLayout());
                optPanel.add(crudPanel, "crudPanel");
                optPanel.add(createPanel, "createPanel");
                // optPanel.add(readPanel, "readPanel");
                // optPanel.add(updatePanel, "updatePanel");
                // optPanel.add(deletePanel,"deletePanel");
                optLayout.show(optPanel, "crudPanel");
                
                //create Panel

                JPanel createbar = new JPanel(new FlowLayout());
                createbar.setBackground(new Color(240, 238, 227));
                createbar.setPreferredSize(new Dimension(1000, 50));
                createPanel.add(createbar, BorderLayout.NORTH);

                JLabel create_Label = new JLabel("Nhập sách");
                create_Label.setFont(new Font("Semibold", Font.BOLD, 30));
                createbar.add(create_Label);

                JPanel create_detail_Panel = new JPanel(new FlowLayout());
                create_detail_Panel.setPreferredSize(new Dimension(1000, 1150));
                create_detail_Panel.setBackground(new Color(240, 238, 227));

                JScrollPane createScrollPane = new JScrollPane(create_detail_Panel);
                createScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                createScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                createScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                createScrollPane.setBounds(100, 50, 110, 650);
                createPanel.add(createScrollPane);
                createScrollPane.setPreferredSize(new Dimension(1000, 650));

                JPanel name_panel = new JPanel(new FlowLayout());
                name_panel.setPreferredSize(new Dimension(1000, 50));
                name_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(name_panel);
                JLabel name_Label = new JLabel("Nhập tên sách: ");
                name_Label.setPreferredSize(new Dimension(200, 50));
                name_Label.setBackground(new Color(240, 238, 227));
                name_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                name_panel.add(name_Label);
                name_Field = new JTextField();
                name_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                name_Field.setColumns(50);
                name_panel.add(name_Field);

                JPanel author_panel = new JPanel(new FlowLayout());
                author_panel.setPreferredSize(new Dimension(1000, 50));
                author_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(author_panel);
                JLabel author_Label = new JLabel("Nhập tên tác giả: ");
                author_Label.setPreferredSize(new Dimension(200, 50));
                author_Label.setBackground(new Color(240, 238, 227));
                author_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                author_panel.add(author_Label);
                author_Field = new JTextField();
                author_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                author_Field.setColumns(50);
                author_panel.add(author_Field);

                JPanel category_panel = new JPanel(new FlowLayout());
                category_panel.setPreferredSize(new Dimension(1000, 50));
                category_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(category_panel);
                JLabel category_Label = new JLabel("Nhập thể loại: ");
                category_Label.setPreferredSize(new Dimension(200, 50));
                category_Label.setBackground(new Color(240, 238, 227));
                category_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                category_panel.add(category_Label);
                category_Field = new JTextField();
                category_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                category_Field.setColumns(50);
                category_panel.add(category_Field);

                JPanel price_panel = new JPanel(new FlowLayout());
                price_panel.setPreferredSize(new Dimension(1000, 50));
                price_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(price_panel);
                JLabel price_Label = new JLabel("Nhập giá tiền: ");
                price_Label.setPreferredSize(new Dimension(200, 50));
                price_Label.setBackground(new Color(240, 238, 227));
                price_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                price_panel.add(price_Label);
                price_Field = new JTextField();
                price_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                price_Field.setColumns(50);
                price_panel.add(price_Field);

                JPanel amount_panel = new JPanel(new FlowLayout());
                amount_panel.setPreferredSize(new Dimension(1000, 50));
                amount_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(amount_panel);
                JLabel amount_Label = new JLabel("Nhập số lượng: ");
                amount_Label.setPreferredSize(new Dimension(200, 50));
                amount_Label.setBackground(new Color(240, 238, 227));
                amount_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                amount_panel.add(amount_Label);
                amount_Field = new JTextField();
                amount_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                amount_Field.setColumns(50);
                amount_panel.add(amount_Field);

                JPanel url_panel = new JPanel(new FlowLayout());
                url_panel.setPreferredSize(new Dimension(1200, 160));
                url_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(url_panel);
                JButton uploadButton = new JButton("Upload");
                uploadButton.setPreferredSize(new Dimension(150, 150));
                uploadButton.setCursor(new Cursor(12));
                uploadButton.setFont(new Font("Semibold", Font.PLAIN, 15));
                uploadButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
                        file.setMultiSelectionEnabled(false);
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png");
                        file.addChoosableFileFilter(filter);
                        int result = file.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION){
                            selectedimageFile = file.getSelectedFile();
                            path = selectedimageFile.getAbsolutePath();
                            
                        }
                        ImageIcon imageIcon = new ImageIcon(path);
                        Image image = imageIcon.getImage();
                        image = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        imageIcon.setImage(image);
                        uploadButton.setIcon(imageIcon);
                    }
                });
                url_panel.add(uploadButton);
                JLabel uploadLabel = new JLabel("Tải ảnh từ máy tính");
                uploadLabel.setFont(new Font("Semibold", Font.PLAIN, 15));
                url_panel.add(uploadLabel);

                JPanel submit_panel = new JPanel(new FlowLayout());
                submit_panel.setPreferredSize(new Dimension(1200, 160));
                submit_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(submit_panel); 
                submitButton = new JButton("Submit");
                submitButton.setFont(new Font("Semibold", Font.PLAIN, 15));
                submitButton.addActionListener(this);
                submit_panel.add(submitButton);

                JPanel tablePanel = new JPanel(new BorderLayout());
                tablePanel.setPreferredSize(new Dimension(1050, 500));
                create_detail_Panel.add(tablePanel);
                DefaultTableModel model =new DefaultTableModel(new Object[][] {},new String[] {}) 
                    {
                        Class[] columnTypes = new Class[] {
                            Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class
                        };
                        public Class getColumnClass(int columnIndex) {
                            return columnTypes[columnIndex];
                        }
                    };
                JTable table = new JTable();
                table.setModel(model);
                table.setEnabled(false);
                table.setFont(new Font("Semibold", Font.PLAIN, 15));
                table.getTableHeader().setFont(new Font("Semibold", Font.BOLD, 18));
                table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                table.getTableHeader().setBackground(new Color(240, 238, 227));
                table.setBackground(new Color(240, 238, 227));
                table.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                JScrollPane sp = new JScrollPane(table);
                sp.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                sp.setPreferredSize(new Dimension(800, 500));
                tablePanel.add(sp,BorderLayout.CENTER);
                String[] colname = {
                    "id",
                    "name",
                    "author",
                    "category",
                    "url",
                    "price",
                    "amount"
                };
                model.setColumnIdentifiers(colname);
                for (int i = 0; i < 100; i++) {
                    String[] colname1 = {
                        Integer.toString(i),
                        "name",
                        "author",
                        "category",
                        "url",
                        "price",
                        "amount"
                    };
                    model.addRow(colname1);
                }
                table.setPreferredScrollableViewportSize(new Dimension(800, 500));


                


                ///crud Panel
                JPanel optionbar = new JPanel(new FlowLayout());
                optionbar.setBackground(new Color(240, 238, 227));
                crudPanel.add(optionbar, BorderLayout.NORTH);

                JLabel inputLabel = new JLabel("Chọn một mục để tiếp tục");
                inputLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                optionbar.add(inputLabel);

                JPanel crud_Panel = new JPanel(new GridLayout(1,4));
                crudPanel.add(crud_Panel);

                JPanel createPanel = new JPanel(new FlowLayout());
                createPanel.setBackground(new Color(240, 238, 227));
                crud_Panel.add(createPanel, BorderLayout.CENTER);
                imageIcon = new ImageIcon("image/create.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                createButton = new JButton(imageIcon);
                createButton.setPreferredSize(new Dimension(250, 500));
                createButton.setBackground(new Color(240, 238, 227));
                createButton.setBorderPainted(false);
                createButton.setAlignmentY(200);
                createButton.setCursor(new Cursor(12));
                createButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        optLayout.show(optPanel, "createPanel");
                    }
                    
                });
                createPanel.add(createButton);  
                JLabel createLabel = new JLabel("Tạo");
                createLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                createLabel.setBackground(new Color(240, 238, 227));
                createPanel.add(createLabel);
                
                JPanel readPanel = new JPanel(new FlowLayout());
                readPanel.setBackground(new Color(240, 238, 227));
                crud_Panel.add(readPanel);
                imageIcon = new ImageIcon("image/read.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                readButton = new JButton(imageIcon);
                readButton.setPreferredSize(new Dimension(250, 500));
                readButton.setBackground(new Color(240, 238, 227));
                readButton.setBorderPainted(false);
                readButton.setCursor(new Cursor(12));
                readPanel.add(readButton);  
                JLabel readLabel = new JLabel("Đọc");
                readLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                readLabel.setBackground(new Color(240, 238, 227));
                readPanel.add(readLabel);
                

                JPanel updatePanel = new JPanel(new FlowLayout());
                updatePanel.setBackground(new Color(240, 238, 227));
                crud_Panel.add(updatePanel);
                imageIcon = new ImageIcon("image/update.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                updateButton = new JButton(imageIcon);
                updateButton.setPreferredSize(new Dimension(250, 500));
                updateButton.setBackground(new Color(240, 238, 227));
                updateButton.setBorderPainted(false);
                updateButton.setCursor(new Cursor(12));
                updatePanel.add(updateButton);  
                JLabel updateLabel = new JLabel("Cập nhật");
                updateLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                updateLabel.setBackground(new Color(240, 238, 227));
                updatePanel.add(updateLabel);


                JPanel deletePanel = new JPanel(new FlowLayout());
                deletePanel.setBackground(new Color(240, 238, 227));
                crud_Panel.add(deletePanel);
                imageIcon = new ImageIcon("image/delete.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                deleteButton = new JButton(imageIcon);
                deleteButton.setPreferredSize(new Dimension(250, 500));
                deleteButton.setBackground(new Color(240, 238, 227));
                deleteButton.setBorderPainted(false);
                deleteButton.setCursor(new Cursor(12));
                deletePanel.add(deleteButton); 
                JLabel deleteLabel = new JLabel("Xoá");
                deleteLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                deleteLabel.setBackground(new Color(240, 238, 227));
                deletePanel.add(deleteLabel);
                

               /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////

                
                
            ///Selling 
                panelrightsell = new JPanel(new FlowLayout());
                JLabel a = new JLabel("chưa làm chẳng có moẹ gì cả");
                panelrightsell.add(a);
               
            
            panelright = new JPanel();
            
            panelright.setLayout(rightLayout);
            
            
            panelright.add(panelrighthome, "panelrighthome");
            panelright.add(panelrightsetting, "panelrightsetting");
            panelright.add(panelrightsell,"panelrightsell");
            
            rightLayout.show(panelright, "panelrighthome");

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "panelrighthome");
                
                
                
                // resultPanel.removeAll();
                // for (int i = 0; i < 50; i++) {
                //     add_one_book(resultPanel, new Book(i,"Drama nuôi tôi lớn Loài người dạy tôi khôn","Pương Pương", "Tiểu Thuyết", "image/b/ook.png", 97000, 2));
                // }
                // resultPanel.setPreferredSize(new Dimension(1100,105*resultPanel.getComponentCount()));
                add_all_bookui(resultPanel);
            }
            
        });
        settingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "panelrightsetting");
            }
            
        });
        sellButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "panelrightsell");
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
        label.setPreferredSize(new Dimension(180, 300));
        label.setBackground(Color.WHITE);
        label.setCursor(new Cursor(12));
        label.setLabel(Integer.toString(book.get_id()));
        label.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(label.getLabel());
            }
            
        });
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
        List<Book> bookList = new ArrayList<Book>();
        bookList=dataConnect.getBookList();
        for (Book book : bookList) {
            add_one_book(resultPanel, book);
        }
        resultPanel.setPreferredSize(new Dimension(1100,105*resultPanel.getComponentCount()));
        //// lay du lieu tu database add_one_book
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            Book newbook = new Book();
            newbook.set_name(name_Field.getText());
            newbook.set_author(author_Field.getText());
            newbook.set_category(category_Field.getText());
            newbook.set_amount(Integer.parseInt(amount_Field.getText()));
            newbook.set_price(Integer.parseInt(price_Field.getText()));
            Boolean checkcreate = dataConnect.createBook(newbook);
            if (checkcreate == true){
                BufferedImage img;
                try {
                    img = ImageIO.read(selectedimageFile);
                    File newfile = new File("image/book.png");
                    ImageIO.write(img, "png", newfile);
                    System.out.println(newfile.getAbsolutePath());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        }
    }



    public Main(String s){
        super(s);
        GUI();
    }


    public static void main(String[] args) {
        new Main("bai tap java");
    }
    
}