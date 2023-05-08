import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;


public class Main extends JFrame implements ActionListener{

    /**
     * Data
     */ 
    DataConnect dataConnect = new DataConnect();
    /**
     * Data
     */ 


    
    JPanel panelmain, panelleft, panelright, panelrighthome, panelrightsetting, panelrightsell, createPanel, readPanel, updatePanel, deletePanel, crudPanel, delete_detail_Panel, optPanel, panelrightlogin;
    JButton homeButton, settingButton, sellButton, searchButton, createButton, readButton, updateButton, deleteButton, submitButton, search_setting_Button, search_update_Button, submit_update_Button, upload_update_Button, search_delete_Button, submit_sell_Button, uploadButton, loginButton, logoutButton, submit_login_Button ;
    JRadioButton id, name, author, category, price, amount;
    CardLayout rightLayout = new CardLayout(),optLayout;
    TextField searchArea, settingArea;
    JTextField search_setting_Area, name_Field, author_Field, category_Field, price_Field, amount_Field, name_update_Field, id_update_Field, author_update_Field, category_update_Field, price_update_Field, amount_update_Field, id_delete_Field, userField;
    JPasswordField passwordField;
    String path="";
    File selectedimageFile;
    JLabel update_status, delete_status, status_sell_Lable, login_status;
    DefaultTableModel model_create =new DefaultTableModel(new Object[][] {},new String[] {}) 
                    {
                        Class[] columnTypes = new Class[] {
                            Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class
                        };
                        public Class getColumnClass(int columnIndex) {
                            return columnTypes[columnIndex];
                        }
                    };
    
    DefaultTableModel model_read =new DefaultTableModel(new Object[][] {},new String[] {}) 
    {
        Class[] columnTypes = new Class[] {
            Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class
        };
        public Class getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }
    };
    DefaultTableModel model_sell =new DefaultTableModel(new Object[][] {},new String[] {}) 
    {
        Class[] columnTypes = new Class[] {
            Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, Integer.class, String.class
        };
        public boolean isCellEditable(int row, int column) {
            return column == 8 ? true : false;
        }
        public Class getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }
    };
    JFrame message = new JFrame("message");
    JTable table_sell;
    int selling=0;
    boolean check_login = false;

    public void GUI() {
        System.out.println(dataConnect.login("staff1", "111"));
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

            imageIcon = new ImageIcon("image/login.png");
            image = imageIcon.getImage();
            image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            imageIcon.setImage(image);
            loginButton = new JButton(imageIcon);
            loginButton.setPreferredSize(new Dimension(100, 50));
            loginButton.setBackground(new Color(240, 238, 227));
            loginButton.setBorderPainted(false);
            panelleft.add(loginButton);

            imageIcon = new ImageIcon("image/logout.png");
            image = imageIcon.getImage();
            image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            imageIcon.setImage(image);
            logoutButton = new JButton(imageIcon);
            logoutButton.setPreferredSize(new Dimension(100, 50));
            logoutButton.setBackground(new Color(240, 238, 227));
            logoutButton.setBorderPainted(false);
            


        // right panel
        
        
            //right home panel 
            panelrighthome = new JPanel(new BorderLayout());

                // search bar
                JPanel searchbar = new JPanel(new FlowLayout());
                searchbar.setBackground(new Color(240, 238, 227));
                panelrighthome.add(searchbar, BorderLayout.NORTH);

                JLabel searchLabel = new JLabel("Trang chủ ");
                searchLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                searchbar.add(searchLabel);
                
                // search result
                JPanel resultPanel = new JPanel(new FlowLayout());
                resultPanel.setBackground(new Color(240, 238, 227));
                JScrollPane scrollPane = new JScrollPane(resultPanel);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setBounds(100, 50, 110, 650);
                scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                panelrighthome.add(scrollPane);

                add_all_bookui(resultPanel);
                

                     /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////   
            ////setting right panel
                panelrightsetting = new JPanel(new BorderLayout());

                ////setting right panel
                panelrightsetting = new JPanel(new BorderLayout());
                
                // option panel

                optLayout = new CardLayout();
                optPanel = new JPanel(optLayout);
                panelrightsetting.add(optPanel);
                crudPanel = new JPanel(new BorderLayout());
                crudPanel.setBackground(new Color(240, 238, 227));
                createPanel = new JPanel(new BorderLayout());
                createPanel.setBackground(new Color(240, 238, 227));
                readPanel = new JPanel(new BorderLayout());
                readPanel.setBackground(new Color(240, 238, 227));
                updatePanel = new JPanel(new BorderLayout());
                updatePanel.setBackground(new Color(240, 238, 227));
                deletePanel = new JPanel(new BorderLayout());
                deletePanel.setBackground(new Color(240, 238, 227));
                optPanel.add(crudPanel, "crudPanel");
                optPanel.add(createPanel, "createPanel");
                optPanel.add(readPanel, "readPanel");
                optPanel.add(updatePanel, "updatePanel");
                optPanel.add(deletePanel,"deletePanel");
                optLayout.show(optPanel, "crudPanel");


                //// delete panel

                
                JPanel deletebar = new JPanel(new FlowLayout());
                deletebar.setBackground(new Color(240, 238, 227));
                deletebar.setPreferredSize(new Dimension(1000, 50));
                deletePanel.add(deletebar, BorderLayout.NORTH);

                JLabel delete_Label = new JLabel("Xoá sách");
                delete_Label.setFont(new Font("Semibold", Font.BOLD, 20));
                deletebar.add(delete_Label);

                delete_detail_Panel = new JPanel(new FlowLayout());
                delete_detail_Panel.setPreferredSize(new Dimension(1000, 1150));
                delete_detail_Panel.setBackground(new Color(240, 238, 227));
                deletePanel.add(delete_detail_Panel);


                JPanel id_delete_panel = new JPanel(new FlowLayout());
                id_delete_panel.setPreferredSize(new Dimension(1000, 50));
                id_delete_panel.setBackground(new Color(240, 238, 227));
                delete_detail_Panel.add(id_delete_panel);
                JLabel id_delete_Label = new JLabel("Nhập id: ");
                id_delete_Label.setPreferredSize(new Dimension(150, 50));
                id_delete_Label.setBackground(new Color(240, 238, 227));
                id_delete_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                id_delete_panel.add(id_delete_Label);
                id_delete_Field = new JTextField();
                id_delete_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                id_delete_Field.setColumns(25);
                id_delete_panel.add(id_delete_Field);
                imageIcon = new ImageIcon("image/search.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                search_delete_Button = new JButton(imageIcon);
                search_delete_Button.setBorderPainted(false);
                search_delete_Button.setBackground(new Color(240, 238, 227));
                id_delete_panel.add(search_delete_Button);
                
                JPanel submit_delete_panel = new JPanel(new FlowLayout());
                submit_delete_panel.setPreferredSize(new Dimension(1200, 50));
                submit_delete_panel.setBackground(new Color(240, 238, 227));
                delete_status = new JLabel("");
                delete_status.setFont(new Font("Semibold", Font.PLAIN, 15));
                submit_delete_panel.add(delete_status);
                delete_detail_Panel.add(submit_delete_panel);
                
                search_delete_Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        delete_status.setText("");
                        Book book = new Book();
                        book = dataConnect.getBook(Integer.parseInt(id_delete_Field.getText()));
                        if (book != null){
                            JDialog messagedDialog = new JDialog(message, "Xác nhận xoá");
                            messagedDialog.setVisible(true);
                            Dimension a = new Dimension(550,270);
                            messagedDialog.setPreferredSize(a);
                            messagedDialog.setMaximumSize(a);
                            messagedDialog.setMinimumSize(a);
                            JPanel delete_book = new JPanel(new FlowLayout());
                            delete_book.setPreferredSize(a);
                            delete_book.setBackground(new Color(240, 238, 227));
                            add_one_book(delete_book, book);
                            JButton submit_deleteButton = new JButton("Xoá");
                            submit_deleteButton.setFont(new Font("Semibold", Font.PLAIN, 15));
                            delete_book.add(submit_deleteButton);
                            JButton cancel_deleteButton = new JButton("Huỷ");
                            cancel_deleteButton.setFont(new Font("Semibold", Font.PLAIN, 15));
                            delete_book.add(cancel_deleteButton);
                            ActionListener deleteEvent = new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    messagedDialog.removeAll();
                                    messagedDialog.setVisible(false);
                                    setEnabled(true);
                                    if (e.getSource() == submit_deleteButton){
                                        Boolean checkdelete = dataConnect.deleteBook((Integer.parseInt(id_delete_Field.getText())));
                                        File file = new File("image/b"+id_delete_Field.getText()+".png");
                                        if (file.delete()) System.out.println("xoa r ne");
                                        else System.out.println("chua xoa duoc ");
                                        if (checkdelete == true){
                                            delete_status.setText("Xoá thành công");
                                            List<Book> bookList = dataConnect.getBookList();
                                            selling_update(model_sell, bookList);
                                            add_book_table(model_read, bookList);
                                            add_book_table(model_create, bookList);
                                        }
                                        else delete_status.setText("Xoá không thành công");
                                    }
                                }
                                
                            };
                            submit_deleteButton.addActionListener(deleteEvent);
                            cancel_deleteButton.addActionListener(deleteEvent);
                            messagedDialog.add(delete_book);
                            setEnabled(false);
                    }
                    }
                });

                ///update panel
                JPanel updatebar = new JPanel(new FlowLayout());
                updatebar.setBackground(new Color(240, 238, 227));
                updatebar.setPreferredSize(new Dimension(1000, 50));
                updatePanel.add(updatebar, BorderLayout.NORTH);

                JLabel update_Label = new JLabel("Cập nhật sách");
                update_Label.setFont(new Font("Semibold", Font.BOLD, 20));
                updatebar.add(update_Label);

                JPanel update_detail_Panel = new JPanel(new FlowLayout());
                update_detail_Panel.setPreferredSize(new Dimension(1000, 1150));
                update_detail_Panel.setBackground(new Color(240, 238, 227));
                updatePanel.add(update_detail_Panel);


                JPanel id_update_panel = new JPanel(new FlowLayout());
                id_update_panel.setPreferredSize(new Dimension(1000, 50));
                id_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(id_update_panel);
                JLabel id_update_Label = new JLabel("Nhập id: ");
                id_update_Label.setPreferredSize(new Dimension(150, 50));
                id_update_Label.setBackground(new Color(240, 238, 227));
                id_update_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                id_update_panel.add(id_update_Label);
                id_update_Field = new JTextField();
                id_update_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                id_update_Field.setColumns(25);
                id_update_panel.add(id_update_Field);
                imageIcon = new ImageIcon("image/search.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                search_update_Button = new JButton(imageIcon);
                search_update_Button.setBorderPainted(false);
                search_update_Button.setBackground(new Color(240, 238, 227));
                search_update_Button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        active_update();
                    }
                    
                });
                id_update_panel.add(search_update_Button);

                JPanel name_update_panel = new JPanel(new FlowLayout());
                name_update_panel.setPreferredSize(new Dimension(1000, 50));
                name_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(name_update_panel);
                JLabel name_update_Label = new JLabel("Tên sách: ");
                name_update_Label.setPreferredSize(new Dimension(200, 50));
                name_update_Label.setBackground(new Color(240, 238, 227));
                name_update_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                name_update_panel.add(name_update_Label);
                name_update_Field = new JTextField();
                name_update_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                name_update_Field.setColumns(50);
                name_update_Field.setEditable(false);
                name_update_panel.add(name_update_Field);

                JPanel author_update_panel = new JPanel(new FlowLayout());
                author_update_panel.setPreferredSize(new Dimension(1000, 50));
                author_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(author_update_panel);
                JLabel author_update_Label = new JLabel("Tác giả: ");
                author_update_Label.setPreferredSize(new Dimension(200, 50));
                author_update_Label.setBackground(new Color(240, 238, 227));
                author_update_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                author_update_panel.add(author_update_Label);
                author_update_Field = new JTextField();
                author_update_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                author_update_Field.setColumns(50);
                author_update_Field.setEditable(false);
                author_update_panel.add(author_update_Field);

                JPanel category_update_panel = new JPanel(new FlowLayout());
                category_update_panel.setPreferredSize(new Dimension(1000, 50));
                category_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(category_update_panel);
                JLabel category_update_Label = new JLabel("Thể loại: ");
                category_update_Label.setPreferredSize(new Dimension(200, 50));
                category_update_Label.setBackground(new Color(240, 238, 227));
                category_update_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                category_update_panel.add(category_update_Label);
                category_update_Field = new JTextField();
                category_update_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                category_update_Field.setColumns(50);
                category_update_Field.setEditable(false);
                category_update_panel.add(category_update_Field);

                JPanel price_update_panel = new JPanel(new FlowLayout());
                price_update_panel.setPreferredSize(new Dimension(1000, 50));
                price_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(price_update_panel);
                JLabel price_update_Label = new JLabel("Giá: ");
                price_update_Label.setPreferredSize(new Dimension(200, 50));
                price_update_Label.setBackground(new Color(240, 238, 227));
                price_update_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                price_update_panel.add(price_update_Label);
                price_update_Field = new JTextField();
                price_update_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                price_update_Field.setColumns(50);
                price_update_Field.setEditable(false);
                price_update_panel.add(price_update_Field);

                JPanel amount_update_panel = new JPanel(new FlowLayout());
                amount_update_panel.setPreferredSize(new Dimension(1000, 50));
                amount_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(amount_update_panel);
                JLabel amount_update_Label = new JLabel("Số lượng: ");
                amount_update_Label.setPreferredSize(new Dimension(200, 50));
                amount_update_Label.setBackground(new Color(240, 238, 227));
                amount_update_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                amount_update_panel.add(amount_update_Label);
                amount_update_Field = new JTextField();
                amount_update_Field.setFont(new Font("Semibold", Font.PLAIN, 15));
                amount_update_Field.setColumns(50);
                amount_update_Field.setEditable(false);
                amount_update_panel.add(amount_update_Field);


                JPanel url_update_panel = new JPanel(new FlowLayout());
                url_update_panel.setPreferredSize(new Dimension(1200, 160));
                url_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(url_update_panel);
                upload_update_Button = new JButton("Upload");
                upload_update_Button.setPreferredSize(new Dimension(150, 150));
                upload_update_Button.setCursor(new Cursor(12));
                upload_update_Button.setFont(new Font("Semibold", Font.PLAIN, 15));
                upload_update_Button.setEnabled(false);
                upload_update_Button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedimageFile = null;
                        JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
                        file.setMultiSelectionEnabled(false);
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png");
                        file.addChoosableFileFilter(filter);
                        int result = file.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION){
                            selectedimageFile = file.getSelectedFile();
                            path = selectedimageFile.getAbsolutePath();
                            ImageIcon imageIcon = new ImageIcon(path);
                            Image image = imageIcon.getImage();
                            image = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                            imageIcon.setImage(image);
                            upload_update_Button.setIcon(imageIcon);
                        }
                    }
                });
                url_update_panel.add(upload_update_Button);
                JLabel upload_update_Label = new JLabel("Tải ảnh từ máy tính");
                upload_update_Label.setFont(new Font("Semibold", Font.PLAIN, 15));
                url_update_panel.add(upload_update_Label);

                JPanel submit_update_panel = new JPanel(new FlowLayout());
                submit_update_panel.setPreferredSize(new Dimension(1200, 50));
                submit_update_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(submit_update_panel); 
                submit_update_Button = new JButton("Cập nhật");
                submit_update_Button.setFont(new Font("Semibold", Font.PLAIN, 15));
                submit_update_Button.addActionListener(this);
                submit_update_panel.add(submit_update_Button);

                JPanel submit_updatestatus_panel = new JPanel(new FlowLayout());
                submit_updatestatus_panel.setPreferredSize(new Dimension(1200, 160));
                submit_updatestatus_panel.setBackground(new Color(240, 238, 227));
                update_detail_Panel.add(submit_updatestatus_panel); 
                update_status = new JLabel();
                update_status.setFont(new Font("Semibold", Font.PLAIN, 15));
                submit_updatestatus_panel.add(update_status);
                
                //readpanel

                JPanel search_setting_bar = new JPanel(new FlowLayout());
                search_setting_bar.setBackground(new Color(240, 238, 227));
                search_setting_bar.setPreferredSize(new Dimension(1000, 100));
                readPanel.add(search_setting_bar, BorderLayout.NORTH);

                JLabel search_setting_Label = new JLabel("Tìm kiếm :");
                search_setting_Label.setFont(new Font("Semibold", Font.BOLD, 15));
                search_setting_bar.add(search_setting_Label);

                search_setting_Area = new JTextField("Tìm kiếm id, tên sách, tác giả, ...");
                search_setting_Area.setColumns(25);
                search_setting_Area.setFont(new Font("Semibold", Font.PLAIN, 15));
                search_setting_Area.setBackground(new Color(240, 238, 227));
                search_setting_bar.add(search_setting_Area);

                imageIcon = new ImageIcon("image/search.png");
                image = imageIcon.getImage();
                image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                imageIcon.setImage(image);
                search_setting_Button = new JButton(imageIcon);
                search_setting_Button.setBorderPainted(false);
                search_setting_Button.setBackground(new Color(240, 238, 227));
                search_setting_Button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        findby();
                    }
                    
                });
                search_setting_bar.add(search_setting_Button);

                JPanel optional = new JPanel(new GridLayout(1,7));
                optional.setPreferredSize(new Dimension(1000, 50));
                optional.setBackground(new Color(240, 238, 227));
                search_setting_bar.add(optional);

                JLabel optionalLabel = new JLabel("Lọc theo: ");
                optionalLabel.setFont(new Font("Semibold", Font.PLAIN, 15));
                optionalLabel.setBackground(new Color(240, 238, 227));
                optional.add(optionalLabel);

                
                id = new JRadioButton("Id");
                id.setFont(new Font("Semibold", Font.PLAIN, 15));
                optional.add(id);
                name = new JRadioButton("Tên sách");
                name.setFont(new Font("Semibold", Font.PLAIN, 15));
                optional.add(name);
                author = new JRadioButton("Tên tác giả");
                author.setFont(new Font("Semibold", Font.PLAIN, 15));
                optional.add(author);
                category = new JRadioButton("Thể loại");
                category.setFont(new Font("Semibold", Font.PLAIN, 15));
                optional.add(category);
                price = new JRadioButton("Giá tiền");
                price.setFont(new Font("Semibold", Font.PLAIN, 15));
                optional.add(price);
                amount = new JRadioButton("Số lượng");
                amount.setFont(new Font("Semibold", Font.PLAIN, 15));
                optional.add(amount);

                ButtonGroup group = new ButtonGroup();
                group.add(id);
                group.add(name);
                group.add(author);
                group.add(category);
                group.add(price);
                group.add(amount);


                JPanel table_search_Panel = new JPanel(new BorderLayout());
                table_search_Panel.setPreferredSize(new Dimension(1050, 500));
                readPanel.add(table_search_Panel, BorderLayout.CENTER);
                
                JTable table_search_ = new JTable();
                table_search_.setModel(model_read);
                table_search_.setEnabled(false);
                table_search_.setFont(new Font("Semibold", Font.PLAIN, 15));
                table_search_.getTableHeader().setFont(new Font("Semibold", Font.BOLD, 18));
                table_search_.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                table_search_.getTableHeader().setBackground(new Color(240, 238, 227));
                table_search_.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                
                JScrollPane s_p = new JScrollPane(table_search_);
                s_p.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                
                table_search_Panel.add(s_p,BorderLayout.CENTER);
                String[] colname = {
                    "Id",
                    "Tên sách",
                    "Tên tác giả",
                    "Thể loại",
                    "Đường dẫn hình ảnh",
                    "Giá",
                    "Số lượng"
                };
                model_read.setColumnIdentifiers(colname);
                List<Book> bookList = new ArrayList<Book>();
                bookList=dataConnect.getBookList();
                add_book_table(model_read, bookList);
                table_search_.setPreferredScrollableViewportSize(new Dimension(800, 500));
                table_search_.setRowHeight(25);

                //create Panel

                JPanel createbar = new JPanel(new FlowLayout());
                createbar.setBackground(new Color(240, 238, 227));
                createbar.setPreferredSize(new Dimension(1000, 50));
                createPanel.add(createbar, BorderLayout.NORTH);

                JLabel create_Label = new JLabel("Nhập sách");
                create_Label.setFont(new Font("Semibold", Font.BOLD, 20));
                createbar.add(create_Label);

                JPanel create_detail_Panel = new JPanel(new FlowLayout());
                create_detail_Panel.setPreferredSize(new Dimension(1000, 1050));
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
                uploadButton = new JButton("Upload");
                uploadButton.setPreferredSize(new Dimension(150, 150));
                uploadButton.setCursor(new Cursor(12));
                uploadButton.setFont(new Font("Semibold", Font.PLAIN, 15));
                uploadButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedimageFile = null;
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
                submit_panel.setPreferredSize(new Dimension(1200, 60));
                submit_panel.setBackground(new Color(240, 238, 227));
                create_detail_Panel.add(submit_panel); 
                submitButton = new JButton("Xác nhận");
                submitButton.setFont(new Font("Semibold", Font.PLAIN, 15));
                submitButton.addActionListener(this);
                submit_panel.add(submitButton);

                JPanel tablePanel = new JPanel(new BorderLayout());
                tablePanel.setPreferredSize(new Dimension(1050, 500));
                create_detail_Panel.add(tablePanel);
                
                JTable table = new JTable();
                table.setModel(model_create);
                table.setEnabled(false);
                table.setFont(new Font("Semibold", Font.PLAIN, 15));
                table.getTableHeader().setFont(new Font("Semibold", Font.BOLD, 18));
                table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                table.getTableHeader().setBackground(new Color(240, 238, 227));
                table.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                
                JScrollPane sp = new JScrollPane(table);
                sp.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                
                tablePanel.add(sp,BorderLayout.CENTER);
                model_create.setColumnIdentifiers(colname);
                bookList=dataConnect.getBookList();
                add_book_table(model_create, bookList);
                table.setPreferredScrollableViewportSize(new Dimension(800, 500));
                table.setRowHeight(25);
                


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
                readButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        optLayout.show(optPanel, "readPanel");
                    }
                    
                });
                readPanel.add(readButton);  
                JLabel readLabel = new JLabel("Xem");
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
                updateButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        optLayout.show(optPanel, "updatePanel");
                    }
                    
                });
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
                deleteButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        optLayout.show(optPanel, "deletePanel");
                    }
                    
                });
                deletePanel.add(deleteButton); 
                JLabel deleteLabel = new JLabel("Xoá");
                deleteLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                deleteLabel.setBackground(new Color(240, 238, 227));
                deletePanel.add(deleteLabel);
                


                

               /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////        /////////////////////////

                
                
            ///Selling 
                panelrightsell = new JPanel(new BorderLayout());
                panelrightsell.setBackground(new Color(0, 238, 227));

                
                JPanel sellingbar = new JPanel(new FlowLayout());
                sellingbar.setBackground(new Color(240, 238, 227));
                sellingbar.setPreferredSize(new Dimension(1000, 100));
                panelrightsell.add(sellingbar, BorderLayout.NORTH);

                JLabel selling_Label = new JLabel("Đặt sách");
                selling_Label.setFont(new Font("Semibold", Font.BOLD, 20));
                sellingbar.add(selling_Label);

                JPanel submit_sellPanel = new JPanel(new FlowLayout());
                submit_sellPanel.setBackground(new Color(240, 238, 227));
                submit_sellPanel.setPreferredSize(new Dimension(1500, 30));
                sellingbar.add(submit_sellPanel);
                submit_sell_Button = new JButton("Xác nhận đặt");
                submit_sell_Button.setFont(new Font("Semibold", Font.BOLD, 15));
                submit_sell_Button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selling > 0){
                        boolean check_update = false;
                        for (int i = 0;  i < table_sell.getRowCount() - 1; i++) {
                            check_update = dataConnect.updateBookSoldAmount(Integer.parseInt(table_sell.getValueAt(i, 0).toString()), Integer.parseInt(table_sell.getValueAt(i, 8).toString()));
                            if (check_update == false) {
                                status_sell_Lable.setText("Đặt thất bại");
                                break;
                            }
                        }
                        List<Book> listBooks = dataConnect.getBookList();
                        if (check_update == true) {
                            status_sell_Lable.setText("Đặt thành công");
                            selling_update(model_sell, listBooks);
                            add_book_table(model_read, listBooks);
                            add_book_table(model_create, listBooks);
                        }
                    }}
                    
                });
                submit_sellPanel.add(submit_sell_Button);

                JPanel status_sellPanel = new JPanel(new FlowLayout());
                status_sellPanel.setBackground(new Color(240, 238, 227));
                status_sellPanel.setPreferredSize(new Dimension(1500, 30));
                sellingbar.add(status_sellPanel);
                status_sell_Lable = new JLabel("");
                status_sell_Lable.setFont(new Font("Semibold", Font.BOLD, 15));
                status_sellPanel.add(status_sell_Lable);


                JPanel table_sellPanel = new JPanel(new BorderLayout());
                table_sellPanel.setPreferredSize(new Dimension(1050, 500));
                panelrightsell.add(table_sellPanel, BorderLayout.CENTER);
                
                table_sell = new JTable(){};
                
                table_sell.setModel(model_sell);
                table_sell.setCellSelectionEnabled(true);
                
                TableModelListener tableModelListener = new TableModelListener() {

                    @Override
                    public void tableChanged(TableModelEvent e) {

                        if (e.getType() == TableModelEvent.UPDATE) {
                            
                            int row = e.getFirstRow();
                            int col = e.getColumn();
                            if (col == 8 && row < table_sell.getRowCount()-1){
                                if (Integer.parseInt(table_sell.getValueAt(row, col).toString()) < 0 )
                                {    
                                    table_sell.setValueAt(0, row, col);
                                    // table_sell.setValueAt(0, table_sell.getRowCount() -1, 8);
                                }
                                if (Integer.parseInt(table_sell.getValueAt(row, col).toString()) > Integer.parseInt(table_sell.getValueAt(row, 6).toString()))
                                {    
                                    int x = Integer.parseInt(table_sell.getValueAt(row, 6).toString());
                                    table_sell.setValueAt(x, row, col);
                                    // table_sell.setValueAt(0, table_sell.getRowCount() -1, 8);
                                }
                                if (table_sell.getRowCount() - 1 == dataConnect.Countid()){
                                    selling = 0;
                                    for (int i = 0; i < table_sell.getRowCount() - 1; i++) {
                                        selling += Integer.parseInt(table_sell.getValueAt(i, 5).toString())* Integer.parseInt(table_sell.getValueAt(i, 8).toString());
                                    }
                                    // System.out.println(selling);
                                }
                            }   
                            if (table_sell.getRowCount() - 1 == dataConnect.Countid() && Integer.parseInt(table_sell.getValueAt(dataConnect.Countid(), 8).toString()) != selling)
                            {
                                table_sell.setValueAt(selling, dataConnect.Countid(), 8);
                            }
                        }
                    }
                };
            
                table_sell.getModel().addTableModelListener(tableModelListener);
                
                table_sell.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JTable target = (JTable)e.getSource();
                        int row = target.getSelectedRow();
                        int column = target.getSelectedColumn();
                        if (e.getClickCount() == 2) {
                            if (column == 7 && row < table_sell.getRowCount() -1 )
                                table_sell.setValueAt(Integer.parseInt(table_sell.getValueAt(row, 8).toString()) + 1, row, 8);
                            // System.out.println(target.getValueAt(row, column));
                            if (column == 9 && row < table_sell.getRowCount() -1)
                                table_sell.setValueAt(Integer.parseInt(table_sell.getValueAt(row, 8).toString()) - 1, row, 8);
                        }
                    }
                });

                
                table_sell.setFont(new Font("Semibold", Font.PLAIN, 15));
                table_sell.getTableHeader().setFont(new Font("Semibold", Font.BOLD, 18));
                table_sell.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                table_sell.getTableHeader().setBackground(new Color(240, 238, 227));
                table_sell.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                
                JScrollPane sp_sell = new JScrollPane(table_sell);
                sp_sell.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
                
                table_sellPanel.add(sp_sell,BorderLayout.CENTER);
                model_sell.setColumnIdentifiers(new String[]{
                    "Id",
                    "Tên sách",
                    "Tác giả",
                    "Thể loại",
                    "Đường link hình ảnh",
                    "Đơn giá",
                    "Số lượng trong kho",
                    "",
                    "Số lượng mua",
                    ""
                });
                bookList = dataConnect.getBookList();
                selling_update(model_sell, bookList);
                System.out.println(table_sell.isCellEditable(bookList.size(), 8));
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                table_sell.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
                table_sell.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
                table_sell.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
                table_sell.setPreferredScrollableViewportSize(new Dimension(800, 500));
                table_sell.setRowHeight(25);
                
                //////////////////// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                panelrightlogin = new JPanel(new FlowLayout());
                panelrightlogin.setBackground(new Color(240, 238, 227));
                
                JPanel loginPanel = new JPanel();
                loginPanel.setPreferredSize(new Dimension(350, 400));
                loginPanel.setBackground(new Color(201, 195, 159));
                loginPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
                panelrightlogin.add(loginPanel, BorderLayout.CENTER);

                JLabel loginLabel = new JLabel("Đăng nhập");
                loginLabel.setPreferredSize(new Dimension(170, 100));
                loginLabel.setBackground(new Color(201, 195, 159));
                loginLabel.setFont(new Font("Semibold", Font.BOLD, 30));
                loginPanel.add(loginLabel);

                JPanel detail_login = new JPanel(new GridLayout(2, 2));
                detail_login.setBackground(new Color(201, 195, 159));
                detail_login.setPreferredSize(new Dimension(345, 80));
                detail_login.setBorder(null);
                loginPanel.add(detail_login);

                JLabel user = new JLabel("Username: ");
                user.setBackground(new Color(201, 195, 159));
                detail_login.add(user);
                userField = new JTextField();
                userField.setFont(new Font("Semibold", Font.PLAIN, 18));
                detail_login.add(userField);
                JLabel password = new JLabel("Password: ");
                password.setBackground(new Color(201, 195, 159));
                detail_login.add(password);
                passwordField = new JPasswordField();
                passwordField.setFont(new Font("Semibold", Font.PLAIN, 18));
                detail_login.add(passwordField);

                JPanel submit_login_Panel = new JPanel(new FlowLayout());
                submit_login_Panel.setPreferredSize(new Dimension(345, 50));
                submit_login_Panel.setBackground(new Color(201, 195, 159));
                loginPanel.add(submit_login_Panel);
                submit_login_Button = new JButton("Login");
                submit_login_Button.setFont(new Font("Semibold", Font.PLAIN, 18));
                submit_login_Panel.add(submit_login_Button);
                submit_login_Button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        check_login = dataConnect.login(userField.getText(), String.copyValueOf(passwordField.getPassword()));
                        if (check_login == false) 
                        {
                            login_status.setText("Thông tin đăng nhập không đúng");
                        }
                        else {
                            login_status.setText("");
                            passwordField.setText("");
                            userField.setText("");
                            rightLayout.show(panelright, "panelrighthome");
                            panelleft.add(logoutButton);
                            panelleft.remove(loginButton);
                        }
                    }
                    
                });
                    
                login_status = new JLabel("");
                login_status.setBackground(new Color(201, 195, 159));
                login_status.setFont(new Font("Semibold", Font.ITALIC, 15));
                loginPanel.add(login_status);

            ////////////////////// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            panelright = new JPanel();
            panelright.setLayout(rightLayout);
            panelright.add(panelrighthome, "panelrighthome");
            panelright.add(panelrightsetting, "panelrightsetting");
            panelright.add(panelrightsell,"panelrightsell");
            panelright.add(panelrightlogin,"panelrightlogin");
            
            rightLayout.show(panelright, "panelrighthome");

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "panelrighthome");
                add_all_bookui(resultPanel);
            }
            
        });
        settingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (check_login == true){
                    rightLayout.show(panelright, "panelrightsetting");
                    optLayout.show(optPanel, "crudPanel");
                }
                else rightLayout.show(panelright, "panelrightlogin");
        }
        });
        sellButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (check_login == true){
                    rightLayout.show(panelright, "panelrightsell");
                }
                else rightLayout.show(panelright, "panelrightlogin");
            }
        });
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rightLayout.show(panelright, "panelrightlogin");
            }
            
        });
        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                check_login = false;
                rightLayout.show(panelright, "panelrighthome");
                panelleft.add(loginButton);
                panelleft.remove(logoutButton);
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
                    if (check_login == true){
                    rightLayout.show(panelright, "panelrightsetting");
                    optLayout.show(optPanel, "updatePanel");
                    id_update_Field.setText(label.getLabel());
                    active_update();
                }
                else rightLayout.show(panelright, "panelrightlogin");
            }
            
        });
        newbook.add(label, BorderLayout.WEST);

        JPanel discription = new JPanel(new BorderLayout());
        discription.setPreferredSize(new Dimension(300, 200));
        discription.setBackground(new Color(201, 195, 159));
        newbook.add(discription);

        JTextArea textArea = new JTextArea(book.get_name());
        textArea.setEditable(false);
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
        resultPanel.setPreferredSize(new Dimension(1100,105*(resultPanel.getComponentCount()+1)));
        //// lay du lieu tu database add_one_book
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            add_new_book();
        }
        if (e.getSource() == submit_update_Button){
            update_book();
        }
    }

    public void add_new_book(){
        Book newbook = new Book();
        newbook.set_name(name_Field.getText());
        newbook.set_author(author_Field.getText());
        newbook.set_category(category_Field.getText());
        newbook.set_amount(Integer.parseInt(amount_Field.getText()));
        newbook.set_price(Integer.parseInt(price_Field.getText()));
        Boolean checkcreate = dataConnect.createBook(newbook);
        if (checkcreate){
                BufferedImage img;
                if (selectedimageFile != null)
                try {
                    img = ImageIO.read(selectedimageFile);
                    File newfile = new File("image/b"+Integer.toString(dataConnect.FindBookid())+".png");
                    ImageIO.write(img, "png", newfile);
                    System.out.println(newfile.getAbsolutePath());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                name_Field.setText("");
                author_Field.setText("");
                category_Field.setText("");
                amount_Field.setText("");
                price_Field.setText("");
                uploadButton.setIcon(null);
                List<Book> bookList = dataConnect.getBookList();
                selling_update(model_sell, bookList);
                add_book_table(model_read, bookList);
                add_book_table(model_create, bookList);
        }
        List<Book> bookList = new ArrayList<Book>();
        bookList=dataConnect.getBookList();
        add_book_table(model_create, bookList);
        System.out.println(dataConnect.FindBookid());
    }

    public void add_book_table(DefaultTableModel model, List<Book> bookList) {
        while(model.getRowCount() != 0) {
            model.removeRow(0);
        }
        System.out.println(model.getRowCount());
        for (Book book : bookList) {
            String[] row = {
                Integer.toString(book.get_id()),
                book.get_name(),
                book.get_author(),
                book.get_category(),
                book.get_url(),
                Integer.toString(book.get_price()),
                Integer.toString(book.get_amount())
            };
            model.addRow(row);
        }
    }

    public void findby() {
        List<Book> bookList = new ArrayList<Book>();
        if (id.isSelected()){
            bookList = dataConnect.findby("id",search_setting_Area.getText());
        }
        if (name.isSelected()){
            bookList = dataConnect.findby("name",search_setting_Area.getText());
        }
        if (author.isSelected()){
            bookList = dataConnect.findby("author",search_setting_Area.getText());
        }
        if (category.isSelected()){
            bookList = dataConnect.findby("category",search_setting_Area.getText());
        }
        if (price.isSelected()){
            bookList = dataConnect.findby("price",search_setting_Area.getText());
        }
        if (amount.isSelected()){
            bookList = dataConnect.findby("amount",search_setting_Area.getText());
        }
        add_book_table(model_read, bookList);
    }

    public void active_update(){
        update_status.setText("");
        Book book = new Book();
        book = dataConnect.getBook(Integer.parseInt(id_update_Field.getText()));
        if (book != null){
            name_update_Field.setText(book.get_name());
            author_update_Field.setText(book.get_author());
            category_update_Field.setText(book.get_category());
            price_update_Field.setText(Integer.toString(book.get_price()));
            amount_update_Field.setText(Integer.toString(book.get_amount()));
            ImageIcon imageIcon = new ImageIcon(book.get_url());
            Image image = imageIcon.getImage();
            image = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imageIcon.setImage(image);
            upload_update_Button.setIcon(imageIcon);
            name_update_Field.setEditable(true);
            author_update_Field.setEditable(true);
            category_update_Field.setEditable(true);
            price_update_Field.setEditable(true);
            amount_update_Field.setEditable(true);
            upload_update_Button.setEnabled(true);
        }
    }

    public void update_book() {
        Book book;
            book = new Book(
                Integer.parseInt(id_update_Field.getText()),
                name_update_Field.getText(),
                author_update_Field.getText(),
                category_update_Field.getText(),
                "image/b"+id_update_Field.getText()+".png",
                Integer.parseInt(price_update_Field.getText()),
                Integer.parseInt(amount_update_Field.getText())
            );
        boolean checkupdate = dataConnect.updateBook(book);
        if (checkupdate == true){
            update_status.setText("Cập nhật thành công");
            id_update_Field.setText("");
            name_update_Field.setText("");
            author_update_Field.setText("");
            category_update_Field.setText("");
            price_update_Field.setText("");
            amount_update_Field.setText("");
            upload_update_Button.setIcon(null);
            BufferedImage img;
                try {
                    img = ImageIO.read(selectedimageFile);
                    File newfile = new File("image/b"+Integer.toString(book.get_id())+".png");
                    ImageIO.write(img, "png", newfile);
                    System.out.println(newfile.getAbsolutePath());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            List<Book> bookList = dataConnect.getBookList();
            selling_update(model_sell, bookList);
            add_book_table(model_read, bookList);
            add_book_table(model_create, bookList);
        }
        else update_status.setText("Cập nhật thất bại");
        
    }

    public int getTotal(List<Book> books,List<Integer> sl) {
        int s=0;
        for (int i = 0; i < books.size(); i++) {
            s+=books.get(i).get_price()*sl.get(i);
        }
        return s;
    }
    public void selling_update(DefaultTableModel model_sell, List<Book> bookList) {
        add_book_table(model_sell, bookList);
        for (int i = 0; i < bookList.size(); i++) {
            table_sell.setValueAt("+", i, 7);
            table_sell.setValueAt(0, i, 8);
            table_sell.setValueAt("-", i, 9);
        }
        model_sell.addRow(new String[]{
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "Tổng cộng",
            "0",
        });
    }


    public Main(String s){
        super(s);
        GUI();
    }


    public static void main(String[] args) {
        new Main("bai tap java");
    }
    
}