package shopper;

import com.mysql.cj.Query;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.RemoveStatement;
import com.mysql.cj.xdevapi.Result;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ShopApp extends JFrame {
    private static String Url = "jdbc:mysql://127.0.0.1:3306/shop?useSSL=false";
    private static String User = "root";
    private static String Pass = "1234";

    private JPanel contentPane;
    private JTextField txtWelcome;
    private JTextField txtfieldSearch;
    private JTextField txtCategories;
    private JTextField User_txt;
    private JTextField Pass_txt;
    private JTextField shopcartField;
    private JTextField removeitem;
    private JTable table_products;
    private JTable table_buyer;
    private JTextField textField;
    private JTextField textField_1;
    private JTable admintable;
    private JTextField Itemname_txt;
    private JTextField Stock_txt;
    private JTextField Price_txt;
    private JTextField Itemcode_txt;
    private JTextField Category_txt;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShopApp frame = new ShopApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ShopApp() {
        setForeground(Color.BLUE);
        setTitle("PC parts picker");
        setResizable(false);

        //JTextArea products;


        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java\\Shopping\\Items\\icons8-shop-28.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1175, 697);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        panel.setBounds(10, 11, 1126, 630);
        contentPane.add(panel);
        panel.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabbedPane.setBackground(new Color(0, 0, 0));
        tabbedPane.setBounds(0, 0, 1126, 652);
        panel.add(tabbedPane);

        JPanel HomeLogin_Panel = new JPanel();
        HomeLogin_Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        HomeLogin_Panel.setBackground(Color.DARK_GRAY);
        tabbedPane.addTab("Home", new ImageIcon("D:\\Java\\Shopping\\Items\\icons8-home-16.png"), HomeLogin_Panel, null);
        tabbedPane.setForegroundAt(0, Color.BLACK);
        tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
        HomeLogin_Panel.setLayout(null);

        txtWelcome = new JTextField();
        txtWelcome.setBackground(new Color(255, 222, 173));
        txtWelcome.setFont(new Font("Segoe Script", Font.BOLD, 26));
        txtWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        txtWelcome.setText("Welcome");
        txtWelcome.setBounds(0, 11, 1124, 39);
        HomeLogin_Panel.add(txtWelcome);
        txtWelcome.setColumns(10);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 222, 173));
        panel_1.setBounds(217, 127, 671, 304);
        HomeLogin_Panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("User Name");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(183, 83, 90, 19);
        panel_1.add(lblNewLabel_2);

        User_txt = new JTextField();
        User_txt.setBounds(285, 83, 192, 20);
        panel_1.add(User_txt);
        User_txt.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Password");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2_1.setBounds(183, 141, 90, 19);
        panel_1.add(lblNewLabel_2_1);

        Pass_txt = new JPasswordField();
        Pass_txt.setColumns(10);
        Pass_txt.setBounds(285, 141, 192, 20);
        panel_1.add(Pass_txt);

        JLabel txtBuyer_Name = new JLabel("");
        txtBuyer_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBuyer_Name.setBounds(123, 11, 179, 41);

        JPanel Shop_panel = new JPanel();
        JPanel Admin_panel = new JPanel();
        JPanel BuyerView_1 = new JPanel();
        JLabel txtUser_Name = new JLabel("");
        txtUser_Name.setHorizontalAlignment(SwingConstants.LEFT);
        txtUser_Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtUser_Name.setBounds(130, 14, 154, 22);

        JButton btnNewButton_4 = new JButton("Log In");
        btnNewButton_4.setBackground(Color.WHITE);
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*Select Name from Sql*/

                String User_Name = User_txt.getText();
                String User_Pass = Pass_txt.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    String usersql="select username from user where username = '" + User_Name + "'";
                    ResultSet userset =statement.executeQuery(usersql);
                    String user =null;
                    while(userset.next())
                    {
                        user=userset.getString("username");
                    }
                    if ( User_Name.equals(user))
                    {
                        String passsql = "select userpass from user where username = '" + User_Name + "'";

                        ResultSet resultSet = statement.executeQuery(passsql);
                        String password = null;
                        while (resultSet.next())
                        {
                            password = resultSet.getString("userpass");
                        }


                        /*
                         * Need to solve
                         * if
                         * condition
                         *password check not working
                         */
                        System.out.println(User_Pass);
                        System.out.println(password);
                        if (password.equals(User_Pass)) {

                            txtUser_Name.setText(User_Name);
                            txtBuyer_Name.setText(User_Name);
                            User_txt.setText("");
                            Pass_txt.setText("");
                            String rolesql = "select role from user where username = '" + User_Name + "'";
                            ResultSet roleselect =statement.executeQuery(rolesql);
                            String role=null;
                            while(roleselect.next())
                            {
                                role = roleselect.getString("role");
                            }
                            if (role.equals("admin")){

                                String showitemsql ="SELECT * FROM itemtable";
                                ResultSet showitemset =statement.executeQuery(showitemsql);
                                DefaultTableModel modelitem =(DefaultTableModel) table_products.getModel();
                                modelitem.setRowCount(0);
                                while(showitemset.next())
                                {
                                    String showname = showitemset.getString(2);
                                    int showstock = showitemset.getInt(3);
                                    int showprice = showitemset.getInt(4);
                                    int showitemcode = showitemset.getInt(5);
                                    String showitemcate = showitemset.getString(6);

                                    Object[] row ={showname, showstock, showprice, showitemcode,showitemcate};
                                    modelitem.addRow(row);
                                }
                                String showadminsql ="SELECT * FROM itemtable";
                                ResultSet showadmin =statement.executeQuery(showadminsql);
                                DefaultTableModel modeladmin =(DefaultTableModel) admintable.getModel();
                                modeladmin.setRowCount(0);
                                while(showadmin.next())
                                {
                                    String showname = showadmin.getString(2);
                                    int showstock = showadmin.getInt(3);
                                    int showprice = showadmin.getInt(4);
                                    int showitemcode = showadmin.getInt(5);


                                    Object[] row ={showname, showstock, showprice, showitemcode};
                                    modeladmin.addRow(row);
                                }
                                tabbedPane.setEnabledAt(3, true);
                                tabbedPane.setSelectedIndex(3);
                                tabbedPane.setEnabledAt(2, true);
                                tabbedPane.setEnabledAt(1, true);
                            }
                            else
                            {
                                tabbedPane.remove(3);
                                tabbedPane.setSelectedIndex(1);
                                tabbedPane.remove(0);
                                String showitemsql ="SELECT * FROM itemtable";
                                ResultSet showitemset =statement.executeQuery(showitemsql);
                                DefaultTableModel modelitem =(DefaultTableModel) table_products.getModel();
                                modelitem.setRowCount(0);
                                while(showitemset.next())
                                {
                                    String showname = showitemset.getString(2);
                                    int showstock = showitemset.getInt(3);
                                    int showprice = showitemset.getInt(4);
                                    int showitemcode = showitemset.getInt(5);


                                    Object[] row ={showname, showstock, showprice, showitemcode};
                                    modelitem.addRow(row);

                                }
                                tabbedPane.setEnabledAt(1, true);tabbedPane.setEnabledAt(0, true);
                            }




                        } else {
                            JOptionPane.showMessageDialog(contentPane, "No Michael No No that was so not right", "Wrong Password", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(contentPane, "Try Again Username not right", "Wrong", JOptionPane.INFORMATION_MESSAGE);
                    }

                    //	statement.executeUpdate(sql);
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
        btnNewButton_4.setBounds(390, 172, 90, 19);
        panel_1.add(btnNewButton_4);

        JButton btnSign = new JButton("Sign Up");
        btnSign.setBackground(Color.WHITE);
        btnSign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String User_Name = User_txt.getText();
                String password = Pass_txt.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    // try catch for Already existed usernames
                    // main funtion is to insert user into the database
                    try{
                        String sql = "insert into user (username, userpass)values ('" + User_Name + "','" + password + "')";
                        statement.executeUpdate(sql);
                    }catch (Exception handled)
                    {
                        JOptionPane.showMessageDialog(contentPane, "Try Again username already existed", "Wrong", JOptionPane.INFORMATION_MESSAGE);
                        User_txt.setText("");
                        Pass_txt.setText("");
                    }
                    User_txt.setText("");
                    Pass_txt.setText("");





                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
        btnSign.setBounds(285, 172, 89, 19);
        panel_1.add(btnSign);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 127, 194, 304);
        HomeLogin_Panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_14 = new JLabel("Tips");
        lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_14.setBounds(10, 11, 73, 23);
        panel_2.add(lblNewLabel_14);

        JTextArea txtrUsernameMustBe = new JTextArea();
        txtrUsernameMustBe.setText("Username must be \r\n5 characters \r\nor below");
        txtrUsernameMustBe.setEditable(false);
        txtrUsernameMustBe.setBounds(10, 45, 174, 57);
        panel_2.add(txtrUsernameMustBe);

        JTextArea txtrPasswordMustBe = new JTextArea();
        txtrPasswordMustBe.setEditable(false);
        txtrPasswordMustBe.setText("Password must be \r\n8 words or higher");
        txtrPasswordMustBe.setBounds(10, 123, 174, 66);
        panel_2.add(txtrPasswordMustBe);

        JTextArea txtrIfYouDont = new JTextArea();
        txtrIfYouDont.setBounds(10, 200, 174, 100);
        panel_2.add(txtrIfYouDont);
        txtrIfYouDont.setText("If you don't have \r\nan account yet \r\nu can create one \r\nby pressing \r\nSign up button");
        txtrIfYouDont.setEditable(false);


        Shop_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        Shop_panel.setBackground(new Color(127, 255, 212));
        tabbedPane.addTab("Shop", new ImageIcon("D:\\Java\\Shopping\\Items\\icons8-shop-16 (1).png"), Shop_panel, null);
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setBackgroundAt(1, new Color(255, 255, 255));
        Shop_panel.setLayout(null);

        txtfieldSearch = new JTextField();
        txtfieldSearch.setBounds(507, 14, 503, 35);
        Shop_panel.add(txtfieldSearch);
        txtfieldSearch.setColumns(10);

        JLabel lblNewLabel = new JLabel("Enter your keyword");
        lblNewLabel.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(363, 18, 134, 23);
        Shop_panel.add(lblNewLabel);

        table_products = new JTable();
        table_products.setEnabled(false);
        table_products.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                },
                new String[] {
                        "ItemName", "Stock", "Price int ($)", "#Itemcode"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, Integer.class, Integer.class, Integer.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        table_products.getColumnModel().getColumn(0).setPreferredWidth(161);
        table_products.getColumnModel().getColumn(1).setResizable(false);
        table_products.getColumnModel().getColumn(2).setResizable(false);
        table_products.getColumnModel().getColumn(3).setResizable(false);
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url,User,Pass);
                    Statement statement = connection.createStatement();
                    String finder = txtfieldSearch.getText();

                    //to select from database itemtable with keywords
                    String sql ="SELECT * FROM itemtable WHERE itemname LIKE '%"+finder+"%';";
                    ResultSet found = statement.executeQuery(sql);

                    //to display on Jtable
                    DefaultTableModel model =(DefaultTableModel) table_products.getModel();
                    model.setRowCount(0);

                    while(found.next())
                    {
                        String name = found.getString(2);
                        int  stock= found.getInt(3);
                        int  price= found.getInt(4);
                        int  code= found.getInt(5);
                        Object[] row ={name, stock, price, code};

                        model.addRow(row);




                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSearch.setBackground(new Color(255, 255, 255));
        btnSearch.setBounds(1020, 20, 89, 23);
        Shop_panel.add(btnSearch);

        JPanel UserDisplay = new JPanel();
        UserDisplay.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        UserDisplay.setForeground(Color.YELLOW);
        UserDisplay.setBounds(0, 0, 360, 51);
        Shop_panel.add(UserDisplay);
        UserDisplay.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("UserName");
        lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 13));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setBounds(56, 11, 78, 29);
        UserDisplay.add(lblNewLabel_1);


        UserDisplay.add(txtUser_Name);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setBounds(0, 62, 179, 542);
        Shop_panel.add(categoryPanel);
        categoryPanel.setLayout(null);

        txtCategories = new JTextField();
        txtCategories.setBackground(new Color(255, 140, 0));
        txtCategories.setForeground(new Color(0, 0, 0));
        txtCategories.setEditable(false);
        txtCategories.setFont(new Font("Segoe UI Variable", Font.BOLD, 13));
        txtCategories.setHorizontalAlignment(SwingConstants.CENTER);
        txtCategories.setText("Categories");
        txtCategories.setBounds(0, 0, 179, 20);
        categoryPanel.add(txtCategories);
        txtCategories.setColumns(10);

        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setBounds(0, 21, 179, 521);
        categoryPanel.add(categoriesPanel);
        categoriesPanel.setLayout(new GridLayout(5, 0, 0, 0));



        JButton btnNewButton = new JButton("CPU");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =CPU
                    String sql = "Select * from itemtable where category = 'CPU'";
                    ResultSet cpu = statement.executeQuery(sql);
                    System.out.println("query working");
                    DefaultTableModel model =(DefaultTableModel) table_products.getModel();
                    model.setRowCount(0);


                    while (cpu.next()) {
                        String name = cpu.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = cpu.getInt(3);
                        int price = cpu.getInt(4);
                        int itemcode = cpu.getInt(5);

                        Object[] row ={name, stock, price, itemcode};

                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setForeground(Color.BLACK);
        categoriesPanel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("RAM");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =RAM

                    String sql = "Select * from itemtable where category = 'RAM'";
                    ResultSet ram = statement.executeQuery(sql);
                    System.out.println("query working");

                    DefaultTableModel model =(DefaultTableModel) table_products.getModel();
                    model.setRowCount(0);


                    while (ram.next()) {
                        String name = ram.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = ram.getInt(3);
                        int price = ram.getInt(4);
                        int itemcode = ram.getInt(5);

                        Object[] row ={name, stock, price, itemcode};

                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_1.setBackground(Color.WHITE);
        btnNewButton_1.setForeground(Color.BLACK);
        categoriesPanel.add(btnNewButton_1);

        JButton btnStorage = new JButton("Storage");
        btnStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =Storage

                    String sql = "Select * from itemtable where category = 'Storage'";
                    ResultSet storage = statement.executeQuery(sql);
                    System.out.println("query working");


                    DefaultTableModel model =(DefaultTableModel) table_products.getModel();
                    model.setRowCount(0);


                    while (storage.next()) {
                        String name = storage.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = storage.getInt(3);
                        int price = storage.getInt(4);
                        int itemcode = storage.getInt(5);

                        Object[] row ={name, stock, price, itemcode};
                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });
        btnStorage.setBackground(Color.WHITE);
        btnStorage.setForeground(Color.BLACK);
        categoriesPanel.add(btnStorage);

        JButton btnNewButton_2 = new JButton("GPU");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =GPU

                    String sql = "Select * from itemtable where category = 'GPU'";
                    ResultSet gpu = statement.executeQuery(sql);
                    System.out.println("query working");
                    DefaultTableModel model =(DefaultTableModel) table_products.getModel();
                    model.setRowCount(0);


                    while (gpu.next()) {
                        String name = gpu.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = gpu.getInt(3);
                        int price = gpu.getInt(4);
                        int itemcode = gpu.getInt(5);

                        Object[] row ={name, stock, price, itemcode};
                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnNewButton_2.setBackground(Color.WHITE);
        btnNewButton_2.setForeground(Color.BLACK);
        categoriesPanel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Accessories");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();

                    //to select from database itemtable with category =Accessories
                    String sql = "Select * from itemtable where category = 'Accessories'";
                    ResultSet accs = statement.executeQuery(sql);
                    System.out.println("query working");

                    DefaultTableModel model =(DefaultTableModel) table_products.getModel();
                    model.setRowCount(0);


                    while (accs.next()) {
                        String name = accs.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = accs.getInt(3);
                        int price = accs.getInt(4);
                        int itemcode = accs.getInt(5);

                        Object[] row ={name, stock, price, itemcode};
                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_3.setBackground(Color.WHITE);
        btnNewButton_3.setForeground(Color.BLACK);
        categoriesPanel.add(btnNewButton_3);

        JPanel ItemTable = new JPanel();
        ItemTable.setBounds(182, 62, 942, 440);
        Shop_panel.add(ItemTable);
        ItemTable.setLayout(null);

        JPanel ItemSelect = new JPanel();
        ItemSelect.setBounds(0, 0, 942, 440);
        ItemTable.add(ItemSelect);
        ItemSelect.setLayout(null);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(0, 0, 942, 440);
        ItemSelect.add(scrollPane_2);


        scrollPane_2.setViewportView(table_products);

        JPanel BuyerPanel = new JPanel();
        BuyerPanel.setBounds(182, 502, 942, 102);
        Shop_panel.add(BuyerPanel);
        BuyerPanel.setLayout(null);

        JLabel lblBuy = new JLabel("Add Things to cart");
        lblBuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblBuy.setBounds(0, 0, 137, 28);
        BuyerPanel.add(lblBuy);

        shopcartField = new JTextField();
        shopcartField.setBounds(117, 39, 282, 28);
        BuyerPanel.add(shopcartField);
        shopcartField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Enter ItemCode");
        lblNewLabel_3.setBounds(117, 24, 233, 14);
        BuyerPanel.add(lblNewLabel_3);

        JSpinner spinner = new JSpinner();
        spinner.setBounds(398, 39, 97, 28);
        BuyerPanel.add(spinner);

        JLabel lblNewLabel_4 = new JLabel("Amount");
        lblNewLabel_4.setBounds(398, 24, 56, 14);
        BuyerPanel.add(lblNewLabel_4);

        JLabel lblNewLabel_8 = new JLabel("Total Items :");
        JLabel lblNewLabel_8_1 = new JLabel("Total Price :");

        JButton btnNewButton_5 = new JButton("Add to Cart");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url,User,Pass);
                    Statement statement = connection.createStatement();
                    String finder = shopcartField.getText();
                    shopcartField.setText("");//clear out the text from textbox

                    //select items with itemcode
                    String sql ="SELECT * FROM itemtable WHERE itemcode = '"+finder+"'";
                    //get number to add to cart
                    int buystock = (int) spinner.getValue();
                    spinner.setValue(0);//clear the spinner

                    ResultSet found = statement.executeQuery(sql);
                    String username = txtUser_Name.getText();
                    int stock = 0;
                    String name = null;
                    int price = 0;
                    while(found.next())
                    {
                        name = found.getString(2);
                        stock = found.getInt(3);
                        price = found.getInt(4);

                    }
                    //condition to check if the buy amount is greater than in-stock
                    if (stock<buystock)
                    {
                        JOptionPane.showMessageDialog(contentPane,"Extras","Re-adjust the buy amount",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        //calculate to update the reduced stocks for itemtable
                        int reducing = stock - buystock;
                        //i hate this mfker lol
                        String sql_1 = "UPDATE itemtable SET stock = "+reducing+ " WHERE itemcode = "+finder+";";
                        statement.executeUpdate(sql_1);
                        //calculate total price for cart table
                        int total_price = buystock * price;

                        //select the item from cart table to check
                        String findcart ="SELECT * FROM cart WHERE itemname = '"+name+"' AND username ='"+username+"'";

                        ResultSet foundcart=statement.executeQuery(findcart);
                        String cartname = null;
                        int cartstock=0,cartprice =0;
                        while(foundcart.next())
                        {
                            cartname = foundcart.getString(1);
                            cartstock = foundcart.getInt(2);
                            cartprice = foundcart.getInt(3);
                        }
                        int carttotalprice= total_price+cartprice;// if the item already existed the price in the cart must increase with the amount of the item (to be add) to cart
                        int carttotalstock= buystock+cartstock;// if the item already existed the stock in the cart must increase with the amount of the item (to be add) to cart


                        //if condition to control below
                        //if the item already existed just update stock and price
                        //if the item doesnt existed insert the item to cart
                        if (name.equals(cartname))
                        {
                            String sqlstockupdate ="update cart set stock ="+carttotalstock+" WHERE itemname = '"+name+"' AND username ='"+username+"'";

                            System.out.println(sqlstockupdate);	statement.executeUpdate(sqlstockupdate);
                            String sqlpriceupdate ="update cart set price ="+carttotalprice+" WHERE itemname = '"+name+"' AND username ='"+username+"'";

                            System.out.println(sqlpriceupdate);statement.executeUpdate(sqlpriceupdate);
                            System.out.println("w8");
                        }
                        else
                        {
                            String sql_2 ="insert into cart(itemname, stock, price,username) values ('"+name+"', "+buystock+", "+total_price+",'"+username+"'); ";
                            System.out.println(sql_2);
                            statement.executeUpdate(sql_2);

                        }

                        //select the itemtable again to show the changed amounts of the stocks
                        String reshow ="SELECT * FROM itemtable ";
                        ResultSet resultSet = statement.executeQuery(reshow);
                        DefaultTableModel model =(DefaultTableModel) table_products.getModel();
                        model.setRowCount(0);
                        while(resultSet.next())
                        {
                            String rename = resultSet.getString(2);
                            int  restock= resultSet.getInt(3);
                            int  reprice= resultSet.getInt(4);
                            int  recode= resultSet.getInt(5);

                            Object[] row ={rename, restock,reprice,recode};
                            model.addRow(row);
                        }

                    }

                    String sql_ = "Select * from cart where username = '"+username+"';";

                    ResultSet view = statement.executeQuery(sql_);
                    //System.out.println("query working");
                    DefaultTableModel model =(DefaultTableModel) table_buyer.getModel();
                    model.setRowCount(0);

                    while (view.next()) {
                        String name_ = view.getString(1);
                        //System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock_ = view.getInt(2);
                        int price_ = view.getInt(3);
                        Object[] row={name_, stock_, price_};
                        model.addRow(row);
                    }





                    // to the sum of all items quantity from cart and show it in total view
                    String stocksql="Select sum(stock) from cart where username = '"+username+"';";
                    System.out.println(stocksql);

                    ResultSet result=statement.executeQuery(stocksql);
                    while (result.next())
                    {
                        int stockshow=result.getInt("sum(stock)");
                        lblNewLabel_8.setText("Total Items :   "+stockshow);

                    }
                    // to the sum of all items price from cart and show it in total view
                    String pricesql="Select sum(price) from cart where username = '"+username+"';";
                    System.out.println(pricesql);
                    System.out.println(stocksql);

                    ResultSet resultprice=statement.executeQuery(pricesql);
                    while (resultprice.next())
                    {
                        int priceshow=resultprice.getInt("sum(price)");
                        lblNewLabel_8_1.setText("Total price :   "+priceshow);

                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_5.setBackground(new Color(255, 255, 255));
        btnNewButton_5.setBounds(533, 42, 124, 23);
        BuyerPanel.add(btnNewButton_5);


        /*this is just the dumb idea (skipped)
        Query to select name from Database
        //String to add in ComboBox
        String[] itemnames = {"select items from here", " "};*/


        BuyerView_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        BuyerView_1.setBackground(new Color(152, 251, 152));
        tabbedPane.addTab("Cart", new ImageIcon("D:\\Java\\Shopping\\Items\\icons8-shopping-cart-16.png"), BuyerView_1, null);
        tabbedPane.setEnabledAt(2, false);
        tabbedPane.setBackgroundAt(2, new Color(255, 255, 255));
        BuyerView_1.setLayout(null);

        JLabel lblNewLabel_4_1 = new JLabel("Buyer Account :");
        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1.setBounds(10, 11, 103, 41);
        BuyerView_1.add(lblNewLabel_4_1);


        BuyerView_1.add(txtBuyer_Name);

        JLabel lblNewLabel_5 = new JLabel("Items In Cart");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_5.setBounds(0, 63, 1124, 34);
        BuyerView_1.add(lblNewLabel_5);

        JPanel RemoveView = new JPanel();
        RemoveView.setBackground(new Color(240, 128, 128));
        RemoveView.setBounds(0, 467, 434, 137);
        BuyerView_1.add(RemoveView);
        RemoveView.setLayout(null);

        JLabel lblNewLabel_6 = new JLabel("Items to Remove");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_6.setBounds(0, 0, 113, 23);
        RemoveView.add(lblNewLabel_6);

        removeitem = new JTextField();
        removeitem.setColumns(10);
        removeitem.setBounds(29, 48, 262, 23);
        RemoveView.add(removeitem);

        JSpinner spinner_1 = new JSpinner();
        spinner_1.setBounds(29, 99, 113, 20);
        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url,User,Pass);
                    Statement statement = connection.createStatement();
                    String finder = removeitem.getText();
                    removeitem.setText("");//clear textbox
                    String username = txtBuyer_Name.getText();
                    //String sql ="SELECT * FROM itemtable WHERE itemcode = '"+finder+"'";
                    // To select from cart with item name and user to remove
                    String sql ="SELECT * FROM cart WHERE itemname = '"+finder+"' AND username = '"+username+"';";
                    int removestocks = (int) spinner_1.getValue();//to remove value
                    spinner_1.setValue(0);//clear spinner

                    ResultSet found = statement.executeQuery(sql);

                    int stock = 0;
                    String name = null;
                    int price = 0;
                    while(found.next())
                    {

                        name = found.getString(1);
                        stock = found.getInt(2);
                        price = found.getInt(3);

                        System.out.println(name+stock+price);
                    }


                    if (stock<removestocks)
                    {
                        JOptionPane.showMessageDialog(contentPane,"Invalid Stock","Invalid Stock",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        int reducing = stock - removestocks;// to update stocks to cart
                        int total_price = (price - ((price / stock) * removestocks));// to update changed price to the cart
                        String sql_1 = "UPDATE cart SET stock = " + reducing + " WHERE itemname = '" + finder + "';";

                        String sql_2 = "UPDATE cart SET price = " + total_price + " WHERE itemname = '" + finder + "';";
                        statement.executeUpdate(sql_1);
                        statement.executeUpdate(sql_2);

                        //select the cart again to delete out the stock 0 item from database
                        String deleter = "Select * from cart where username = '" + username + "';";
                        ResultSet deleting = statement.executeQuery(deleter);
                        try {
                            while (deleting.next()) {
                                String itemname = deleting.getString(1);
                                int itemstock = deleting.getInt(2);
                                if (itemstock == 0) {
                                    String delete = "Delete from cart where itemname ='" + itemname + "'";
                                    statement.executeUpdate(delete);
                                }
                            }
                        } catch (Exception exception) {
                        }


                        // select the cart to show the changed amount from the database
                        String showsql = "Select * from cart where username = '" + username + "';";
                        ResultSet view = statement.executeQuery(showsql);
                        //System.out.println("query working");

                        DefaultTableModel modelcart = (DefaultTableModel) table_buyer.getModel();
                        modelcart.setRowCount(0);
                        while (view.next()) {
                            String itemname = view.getString(1);
                            //System.out.println(name);
                            //String pd_desc = cpu.getString(2);
                            int itemstock = view.getInt(2);
                            int itemprice = view.getInt(3);

                            Object[] row = {itemname, itemstock, itemprice};
                            modelcart.addRow(row);

                        }

                        //restock the items from
                        String sql_3 = "SELECT * FROM itemtable WHERE itemname = '" + finder + "'";
                        ResultSet found_1 = statement.executeQuery(sql_3);
                        int stockfromitem = 0;
                        while (found_1.next()) {
                            stockfromitem = found_1.getInt(3);
                        }
                        int totalstock = removestocks + stockfromitem;
                        String sql_4 = "UPDATE itemtable SET stock = " + totalstock + " WHERE itemname = '" + finder + "';";
                        statement.executeUpdate(sql_4);

                        //select itemtable from database to show the changed amounts after removing items from cart table
                        String showitemsql = "SELECT * FROM itemtable";
                        ResultSet showitemset = statement.executeQuery(showitemsql);
                        DefaultTableModel modelitem = (DefaultTableModel) table_products.getModel();
                        modelitem.setRowCount(0);
                        while (showitemset.next()) {
                            String showname = showitemset.getString(2);
                            System.out.println(name);
                            //String pd_desc = cpu.getString(2);
                            int showstock = showitemset.getInt(3);
                            int showprice = showitemset.getInt(4);
                            int showitemcode = showitemset.getInt(5);

                            Object[] row = {showname, showstock, showprice, showitemcode};
                            modelitem.addRow(row);
                        }


                        // to the sum of all items quantity from cart and show it in total view
                        String stocksql = "Select sum(stock) from cart where username = '" + username + "';";
                        System.out.println(stocksql);

                        ResultSet result = statement.executeQuery(stocksql);
                        while (result.next()) {
                            int stockshow = result.getInt("sum(stock)");
                            lblNewLabel_8.setText("Total Items :   " + stockshow);

                        }
                        // to the sum of all items price from cart and show it in total view
                        String pricesql = "Select sum(price) from cart where username = '" + username + "';";
                        System.out.println(pricesql);
                        System.out.println(stocksql);

                        ResultSet resultprice = statement.executeQuery(pricesql);
                        while (resultprice.next()) {
                            int priceshow = resultprice.getInt("sum(price)");
                            lblNewLabel_8_1.setText("Total price :   " + priceshow);

                        }
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnRemove.setBackground(Color.WHITE);
        btnRemove.setBounds(303, 98, 89, 23);
        RemoveView.add(btnRemove);

        JLabel lblNewLabel_7 = new JLabel("Name Items to remove");
        lblNewLabel_7.setBounds(29, 34, 150, 14);
        RemoveView.add(lblNewLabel_7);


        RemoveView.add(spinner_1);

        JLabel lblNewLabel_9 = new JLabel("Item Number");
        lblNewLabel_9.setBounds(29, 82, 96, 14);
        RemoveView.add(lblNewLabel_9);

        JPanel BuyNow = new JPanel();
        BuyNow.setBackground(new Color(255, 192, 203));
        BuyNow.setBounds(433, 467, 512, 137);
        BuyerView_1.add(BuyNow);
        BuyNow.setLayout(null);


        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_8.setBounds(241, 27, 191, 14);
        BuyNow.add(lblNewLabel_8);


        lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_8_1.setBounds(241, 65, 191, 14);
        BuyNow.add(lblNewLabel_8_1);


        JButton btnBuy = new JButton("Buy Now");
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    String username = txtBuyer_Name.getText();
                    // to delete bought item from cart with the username //Project form to be added
                    String sql = "delete from cart where username = '"+username+"';";
                    statement.executeUpdate(sql);

                    DefaultTableModel model =(DefaultTableModel) table_buyer.getModel();
                    model.setRowCount(0);



                    // to the sum of all items quantity from cart and show it in total view
                    String stocksql="Select sum(stock) from cart where username = '"+username+"';";
                    System.out.println(stocksql);

                    ResultSet result=statement.executeQuery(stocksql);
                    while (result.next())
                    {
                        int stockshow=result.getInt("sum(stock)");
                        lblNewLabel_8.setText("Total Items :   "+stockshow);

                    }
                    // to the sum of all items price from cart and show it in total view
                    String pricesql="Select sum(price) from cart where username = '"+username+"';";
                    System.out.println(pricesql);
                    System.out.println(stocksql);

                    ResultSet resultprice=statement.executeQuery(pricesql);
                    while (resultprice.next())
                    {
                        int priceshow=resultprice.getInt("sum(price)");
                        lblNewLabel_8_1.setText("Total price :   "+priceshow);

                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnBuy.setBackground(Color.WHITE);
        btnBuy.setBounds(355, 103, 145, 23);
        BuyNow.add(btnBuy);

        JLabel payment_method = new JLabel("Paying with ");
        payment_method.setBounds(10, 11, 221, 31);
        BuyNow.add(payment_method);


        JButton btnNewButton_6 = new JButton("View Cart");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    String username = txtBuyer_Name.getText();
                    // select from cart with username
                    String sql = "Select * from cart where username = '"+username+"';";

                    ResultSet view = statement.executeQuery(sql);
                    //System.out.println("query working");
                    DefaultTableModel model =(DefaultTableModel) table_buyer.getModel();
                    model.setRowCount(0);

                    while (view.next()) {
                        String name = view.getString(1);
                        //System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = view.getInt(2);
                        int price = view.getInt(3);
                        Object[] row={name, stock, price};
                        model.addRow(row);
                    }
                    // to the sum of all items quantity from cart and show it in total view
                    String stocksql="Select sum(stock) from cart where username = '"+username+"';";
                    System.out.println(stocksql);

                    ResultSet result=statement.executeQuery(stocksql);
                    while (result.next())
                    {
                        int stockshow=result.getInt("sum(stock)");
                        lblNewLabel_8.setText("Total Items :   "+stockshow);

                    }
                    // to the sum of all items price from cart and show it in total view
                    String pricesql="Select sum(price) from cart where username = '"+username+"';";
                    System.out.println(pricesql);
                    System.out.println(stocksql);

                    ResultSet resultprice=statement.executeQuery(pricesql);
                    while (resultprice.next())
                    {
                        int priceshow=resultprice.getInt("sum(price)");
                        lblNewLabel_8_1.setText("Total price :   "+priceshow);

                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_6.setBounds(315, 22, 89, 23);
        BuyerView_1.add(btnNewButton_6);
        btnNewButton_6.setBackground(new Color(224, 255, 255));

        JPanel Pay_Method = new JPanel();
        Pay_Method.setBackground(new Color(175, 238, 238));
        Pay_Method.setBounds(955, 108, 154, 278);
        BuyerView_1.add(Pay_Method);
        Pay_Method.setLayout(null);

        JLabel lblNewLabel_10 = new JLabel("Payment Methods");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_10.setBounds(0, 0, 154, 25);
        Pay_Method.add(lblNewLabel_10);

        JButton btnKbz = new JButton("KbzPay");
        JButton Ayapay = new JButton("Ayapay");
        JButton Kbz_Bank = new JButton("Kbz Bank");
        JButton CB_bank = new JButton("CB bank");
        JButton Visa = new JButton("Visa");
        btnKbz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btnKbz = (JButton) e.getSource();
                btnKbz.setBackground(Color.RED);
                Ayapay.setBackground(Color.WHITE);
                Kbz_Bank.setBackground(Color.WHITE);
                CB_bank.setBackground(Color.WHITE);
                Visa.setBackground(Color.WHITE);
                payment_method.setText("Paying with KBZPay");


            }
        });
        btnKbz.setBackground(new Color(240, 255, 255));
        btnKbz.setBounds(0, 48, 154, 35);
        Pay_Method.add(btnKbz);


        Ayapay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton Ayapay = (JButton) e.getSource();
                btnKbz.setBackground(Color.WHITE);
                Ayapay.setBackground(Color.RED);
                Kbz_Bank.setBackground(Color.WHITE);
                CB_bank.setBackground(Color.WHITE);
                Visa.setBackground(Color.WHITE);
                payment_method.setText("Paying with Aya Pay");
            }
        });
        Ayapay.setBackground(new Color(240, 255, 255));
        Ayapay.setBounds(0, 92, 154, 35);
        Pay_Method.add(Ayapay);


        Kbz_Bank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton Kbz_Bank = (JButton) e.getSource();
                btnKbz.setBackground(Color.WHITE);
                Ayapay.setBackground(Color.WHITE);
                Kbz_Bank.setBackground(Color.RED);
                CB_bank.setBackground(Color.WHITE);
                Visa.setBackground(Color.WHITE);
                payment_method.setText("Paying with Kbz Bank");
            }
        });
        Kbz_Bank.setBackground(new Color(240, 255, 255));
        Kbz_Bank.setBounds(0, 138, 154, 35);
        Pay_Method.add(Kbz_Bank);


        CB_bank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton CB_bank = (JButton) e.getSource();
                btnKbz.setBackground(Color.WHITE);
                Ayapay.setBackground(Color.WHITE);
                Kbz_Bank.setBackground(Color.WHITE);
                CB_bank.setBackground(Color.RED);
                Visa.setBackground(Color.WHITE);
                payment_method.setText("Paying with CB Bank");


            }
        });
        CB_bank.setBackground(new Color(240, 255, 255));
        CB_bank.setBounds(0, 184, 154, 35);
        Pay_Method.add(CB_bank);


        Visa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton Visa  = (JButton) e.getSource();
                btnKbz.setBackground(Color.WHITE);
                Ayapay.setBackground(Color.WHITE);
                Kbz_Bank.setBackground(Color.WHITE);
                CB_bank.setBackground(Color.WHITE);
                Visa.setBackground(Color.RED);
                payment_method.setText("Paying with Visa");
            }
        });
        Visa.setBackground(new Color(240, 255, 255));
        Visa.setBounds(0, 230, 154, 35);
        Pay_Method.add(Visa);

        JPanel Rating_Panel = new JPanel();
        Rating_Panel.setBackground(new Color(144, 238, 144));
        Rating_Panel.setBounds(955, 397, 154, 192);
        BuyerView_1.add(Rating_Panel);
        Rating_Panel.setLayout(null);

        JLabel lblNewLabel_12 = new JLabel("Rate the Store");
        lblNewLabel_12.setBounds(0, 0, 154, 26);
        Rating_Panel.add(lblNewLabel_12);

        JButton btnNewButton_8 = new JButton("Add Comment");
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnNewButton_8.setBounds(10, 98, 134, 23);
        Rating_Panel.add(btnNewButton_8);

        JSlider slider = new JSlider();
        slider.setMaximum(5);
        slider.setValue(0);
        slider.setBounds(10, 45, 134, 26);
        Rating_Panel.add(slider);

        JLabel lblNewLabel_13 = new JLabel("1        2        3         4        5");
        lblNewLabel_13.setBounds(10, 26, 154, 14);
        Rating_Panel.add(lblNewLabel_13);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 108, 945, 358);
        BuyerView_1.add(scrollPane);

        table_buyer = new JTable();
        table_buyer.setColumnSelectionAllowed(true);
        table_buyer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_buyer.setCellSelectionEnabled(true);
        table_buyer.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null},
                },
                new String[] {
                        "Item Name", "Stock", "Price in($)"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, Integer.class, Integer.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                    false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table_buyer.getColumnModel().getColumn(0).setResizable(false);
        table_buyer.getColumnModel().getColumn(0).setPreferredWidth(241);
        table_buyer.getColumnModel().getColumn(1).setResizable(false);
        table_buyer.getColumnModel().getColumn(2).setResizable(false);
        scrollPane.setViewportView(table_buyer);




        Admin_panel.setLayout(null);
        Admin_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        Admin_panel.setBackground(new Color(127, 255, 212));
        tabbedPane.addTab("Admin", null, Admin_panel, null);
        tabbedPane.setEnabledAt(3, false);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(507, 14, 503, 35);
        Admin_panel.add(textField);

        JLabel lblNewLabel_11 = new JLabel("Enter your keyword");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
        lblNewLabel_11.setBounds(363, 18, 134, 23);
        Admin_panel.add(lblNewLabel_11);

        JButton btnSearch_1 = new JButton("Search");
        btnSearch_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url,User,Pass);
                    Statement statement = connection.createStatement();
                    String finder = txtfieldSearch.getText();

                    //to select from database itemtable with keywords
                    String sql ="SELECT * FROM itemtable WHERE itemname LIKE '%"+finder+"%';";
                    ResultSet found = statement.executeQuery(sql);

                    //to display on Jtable
                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);

                    while(found.next())
                    {
                        String name = found.getString(2);
                        int  stock= found.getInt(3);
                        int  price= found.getInt(4);
                        int  code= found.getInt(5);
                        String categ=found.getString(6);
                        Object[] row ={name, stock, price, code,categ};

                        model.addRow(row);

                        Itemname_txt.setEnabled(true);
                        Itemname_txt.setEditable(true);
                        Price_txt.setEnabled(true);
                        Price_txt.setEditable(true);
                        Stock_txt.setEnabled(true);
                        Stock_txt.setEditable(true);
                        Category_txt.setEditable(true);
                        Category_txt.setEnabled(true);



                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSearch_1.setBackground(Color.WHITE);
        btnSearch_1.setBounds(1020, 20, 89, 23);
        Admin_panel.add(btnSearch_1);

        JPanel UserDisplay_1 = new JPanel();
        UserDisplay_1.setLayout(null);
        UserDisplay_1.setForeground(Color.YELLOW);
        UserDisplay_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        UserDisplay_1.setBounds(0, 0, 360, 51);
        Admin_panel.add(UserDisplay_1);

        JLabel admin_name = new JLabel("AdminName");
        admin_name.setHorizontalAlignment(SwingConstants.LEFT);
        admin_name.setFont(new Font("Segoe UI Variable", Font.PLAIN, 13));
        admin_name.setBounds(56, 11, 78, 29);
        UserDisplay_1.add(admin_name);

        JLabel txtAdmin = new JLabel("");
        txtAdmin.setHorizontalAlignment(SwingConstants.LEFT);
        txtAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtAdmin.setBounds(130, 14, 154, 22);
        UserDisplay_1.add(txtAdmin);

        JPanel categoryPanel_1 = new JPanel();
        categoryPanel_1.setLayout(null);
        categoryPanel_1.setBounds(0, 62, 179, 542);
        Admin_panel.add(categoryPanel_1);

        textField_1 = new JTextField();
        textField_1.setText("Categories");
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setForeground(Color.BLACK);
        textField_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 13));
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBackground(new Color(255, 140, 0));
        textField_1.setBounds(0, 0, 179, 20);
        categoryPanel_1.add(textField_1);

        JPanel categoriesPanel_1 = new JPanel();
        categoriesPanel_1.setBounds(0, 21, 179, 521);
        categoryPanel_1.add(categoriesPanel_1);
        categoriesPanel_1.setLayout(new GridLayout(5, 0, 0, 0));

        JButton btnNewButton_7 = new JButton("CPU");
        btnNewButton_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =CPU
                    String sql = "Select * from itemtable where category = 'CPU'";
                    ResultSet cpu = statement.executeQuery(sql);
                    System.out.println("query working");
                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);


                    while (cpu.next()) {
                        String name = cpu.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = cpu.getInt(3);
                        int price = cpu.getInt(4);
                        int itemcode = cpu.getInt(5);
                        String categ=cpu.getString(6);
                        Object[] row ={name, stock, price, itemcode,categ};

                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnNewButton_7.setForeground(Color.BLACK);
        btnNewButton_7.setBackground(Color.WHITE);
        categoriesPanel_1.add(btnNewButton_7);

        JButton btnNewButton_1_1 = new JButton("RAM");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =RAM

                    String sql = "Select * from itemtable where category = 'RAM'";
                    ResultSet ram = statement.executeQuery(sql);
                    System.out.println("query working");

                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);


                    while (ram.next()) {
                        String name = ram.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = ram.getInt(3);
                        int price = ram.getInt(4);
                        int itemcode = ram.getInt(5);
                        String categ=ram.getString(6);
                        Object[] row ={name, stock, price, itemcode, categ};

                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_1_1.setForeground(Color.BLACK);
        btnNewButton_1_1.setBackground(Color.WHITE);
        categoriesPanel_1.add(btnNewButton_1_1);

        JButton btnStorage_1 = new JButton("Storage");
        btnStorage_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =Storage

                    String sql = "Select * from itemtable where category = 'Storage'";
                    ResultSet storage = statement.executeQuery(sql);
                    System.out.println("query working");


                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);


                    while (storage.next()) {
                        String name = storage.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = storage.getInt(3);
                        int price = storage.getInt(4);
                        int itemcode = storage.getInt(5);
                        String categ=storage.getString(6);
                        Object[] row ={name, stock, price, itemcode, categ};
                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnStorage_1.setForeground(Color.BLACK);
        btnStorage_1.setBackground(Color.WHITE);
        categoriesPanel_1.add(btnStorage_1);

        JButton btnNewButton_2_1 = new JButton("GPU");
        btnNewButton_2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();
                    //to select from database itemtable with category =GPU

                    String sql = "Select * from itemtable where category = 'GPU'";
                    ResultSet gpu = statement.executeQuery(sql);
                    System.out.println("query working");
                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);


                    while (gpu.next()) {
                        String name = gpu.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = gpu.getInt(3);
                        int price = gpu.getInt(4);
                        int itemcode = gpu.getInt(5);
                        String categ=gpu.getString(6);
                        Object[] row ={name, stock, price, itemcode,categ};
                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnNewButton_2_1.setForeground(Color.BLACK);
        btnNewButton_2_1.setBackground(Color.WHITE);
        categoriesPanel_1.add(btnNewButton_2_1);

        JButton btnNewButton_3_1 = new JButton("Accessories");
        btnNewButton_3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();

                    //to select from database itemtable with category =Accessories
                    String sql = "Select * from itemtable where category = 'Accessories'";
                    ResultSet accs = statement.executeQuery(sql);
                    System.out.println("query working");

                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);


                    while (accs.next()) {
                        String name = accs.getString(2);
                        System.out.println(name);
                        //String pd_desc = cpu.getString(2);
                        int stock = accs.getInt(3);
                        int price = accs.getInt(4);
                        int itemcode = accs.getInt(5);
                        String categ=accs.getString(6);

                        Object[] row ={name, stock, price, itemcode, categ};
                        model.addRow(row);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton_3_1.setForeground(Color.BLACK);
        btnNewButton_3_1.setBackground(Color.WHITE);
        categoriesPanel_1.add(btnNewButton_3_1);

        JPanel ItemTable_1 = new JPanel();
        ItemTable_1.setLayout(null);
        ItemTable_1.setBounds(182, 62, 942, 355);
        Admin_panel.add(ItemTable_1);

        JPanel ItemSelect_1 = new JPanel();
        ItemSelect_1.setLayout(null);
        ItemSelect_1.setBounds(0, 0, 942, 357);
        ItemTable_1.add(ItemSelect_1);

        JScrollPane scrollPane_2_1 = new JScrollPane();
        scrollPane_2_1.setBounds(0, 0, 942, 356);
        ItemSelect_1.add(scrollPane_2_1);

        admintable = new JTable();
        admintable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null},
                },
                new String[] {
                        "Itemname", "Stock", "Item Price", "Itemcode", "Category"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, Integer.class, Integer.class, Integer.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        scrollPane_2_1.setViewportView(admintable);

        JPanel updater = new JPanel();
        updater.setBounds(182, 415, 942, 200);
        Admin_panel.add(updater);
        updater.setLayout(null);

        JLabel lblNewLabel_15 = new JLabel("Itemname");
        lblNewLabel_15.setBounds(10, 53, 103, 22);
        updater.add(lblNewLabel_15);

        JLabel lblNewLabel_15_1 = new JLabel("Stock");
        lblNewLabel_15_1.setBounds(10, 86, 103, 22);
        updater.add(lblNewLabel_15_1);

        JLabel lblNewLabel_15_2 = new JLabel("Price");
        lblNewLabel_15_2.setBounds(10, 119, 103, 22);
        updater.add(lblNewLabel_15_2);

        JLabel lblNewLabel_15_3 = new JLabel("Itemcode");
        lblNewLabel_15_3.setBounds(10, 11, 103, 22);
        updater.add(lblNewLabel_15_3);

        JLabel lblNewLabel_15_4 = new JLabel("Category");
        lblNewLabel_15_4.setBounds(10, 152, 103, 22);
        updater.add(lblNewLabel_15_4);

        Itemname_txt = new JTextField();
        Itemname_txt.setEnabled(false);
        Itemname_txt.setEditable(false);
        Itemname_txt.setBounds(120, 54, 151, 20);
        updater.add(Itemname_txt);
        Itemname_txt.setColumns(10);

        Stock_txt = new JTextField();
        Stock_txt.setEnabled(false);
        Stock_txt.setEditable(false);
        Stock_txt.setColumns(10);
        Stock_txt.setBounds(120, 87, 151, 20);
        updater.add(Stock_txt);

        Price_txt = new JTextField();
        Price_txt.setEnabled(false);
        Price_txt.setEditable(false);
        Price_txt.setColumns(10);
        Price_txt.setBounds(120, 120, 151, 20);
        updater.add(Price_txt);

        Itemcode_txt = new JTextField();
        Itemcode_txt.setColumns(10);
        Itemcode_txt.setBounds(120, 12, 151, 20);
        updater.add(Itemcode_txt);

        Category_txt = new JTextField();
        Category_txt.setEnabled(false);
        Category_txt.setEditable(false);
        Category_txt.setColumns(10);
        Category_txt.setBounds(120, 153, 151, 20);
        updater.add(Category_txt);

        JButton admin_add = new JButton("Add");
        admin_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String code = Itemcode_txt.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();

                    //to select from database itemtable with category =Accessories
                    String sql = "Insert into itemtable (itemcode)values ("+code+")";
                    System.out.println(sql);
                    statement.executeUpdate(sql);

                    String newname = Itemname_txt.getText();
                    String newstock = Stock_txt.getText();
                    String newprice = Price_txt.getText();
                    String newcateg = Category_txt.getText();


                    String editname="update itemtable set itemname ='"+newname+"' WHERE itemcode = "+code+"";
                    String editstock="update itemtable set stock ="+newstock+" WHERE itemcode ="+code+"";
                    String editprice="update itemtable set price ="+newprice+" WHERE itemcode = "+code+"";
                    String editcateg="update itemtable set category ='"+newcateg+"' WHERE itemcode = "+code+"";
                    statement.executeUpdate(editname);
                    statement.executeUpdate(editstock);
                    statement.executeUpdate(editprice);
                    statement.executeUpdate(editcateg);





                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(contentPane,"Invalid itemcode","Invalid itemcode",JOptionPane.INFORMATION_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        admin_add.setBounds(331, 53, 89, 23);
        updater.add(admin_add);

        JButton admin_search = new JButton("Search");
        admin_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String code = Itemcode_txt.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();

                    //to select from database itemtable with category =Accessories
                    String sql = "Select * from itemtable where itemcode = "+code+";";
                    System.out.println(sql);
                    ResultSet accs = statement.executeQuery(sql);




                    while (accs.next()) {
                        String name = accs.getString(2);
                        //String pd_desc = cpu.getString(2);
                        int stock = accs.getInt(3);
                        int price = accs.getInt(4);
                        String categ=accs.getString(6);


                        Itemname_txt.setText(name);
                        Stock_txt.setText(String.valueOf(stock));
                        Price_txt.setText(String.valueOf(price));
                        Category_txt.setText(categ);

                    }
                    Itemcode_txt.setEnabled(false);
                    Itemcode_txt.setEditable(false);


                    Itemname_txt.setEnabled(true);
                    Itemname_txt.setEditable(true);
                    Price_txt.setEnabled(true);
                    Price_txt.setEditable(true);
                    Stock_txt.setEnabled(true);
                    Stock_txt.setEditable(true);
                    Category_txt.setEditable(true);
                    Category_txt.setEnabled(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(contentPane,"Invalid itemcode","Invalid itemcode",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        admin_search.setBounds(331, 11, 89, 23);
        updater.add(admin_search);

        JButton admin_edit = new JButton("Edit");
        admin_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String code = Itemcode_txt.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();

                    //to select from database itemtable with category =Accessories


                    String newname = Itemname_txt.getText();
                    String newstock = Stock_txt.getText();
                    String newprice = Price_txt.getText();
                    String newcateg = Category_txt.getText();


                    String editname="update itemtable set itemname ='"+newname+"' WHERE itemcode = "+code+"";
                    String editstock="update itemtable set stock ="+newstock+" WHERE itemcode ="+code+"";
                    String editprice="update itemtable set price ="+newprice+" WHERE itemcode = "+code+"";
                    String editcateg="update itemtable set category ='"+newcateg+"' WHERE itemcode = "+code+"";
                    statement.executeUpdate(editname);
                    statement.executeUpdate(editstock);
                    statement.executeUpdate(editprice);
                    statement.executeUpdate(editcateg);







                    Itemcode_txt.setText("");
                    Itemname_txt.setText("");
                    Stock_txt.setText("");
                    Price_txt.setText("");
                    Category_txt.setText("");
                    Itemcode_txt.setEnabled(true);
                    Itemcode_txt.setEditable(true);

                    Itemname_txt.setEnabled(false);
                    Itemname_txt.setEditable(false);

                    Price_txt.setEnabled(false);
                    Price_txt.setEditable(false);

                    Stock_txt.setEnabled(false);
                    Stock_txt.setEditable(false);

                    Category_txt.setEditable(false);
                    Category_txt.setEnabled(false);
                    JOptionPane.showMessageDialog(contentPane,"Update Successful","Update Success",JOptionPane.INFORMATION_MESSAGE);

                    String sql = "Select * from itemtable";
                    ResultSet accs = statement.executeQuery(sql);

                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);
                    while (accs.next()) {
                        String name = accs.getString(2);

                        //String pd_desc = cpu.getString(2);
                        int stock = accs.getInt(3);
                        int price = accs.getInt(4);
                        int itemcode = accs.getInt(5);
                        String categ=accs.getString(6);

                        Object[] row ={name, stock, price, itemcode, categ};
                        model.addRow(row);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        admin_edit.setBounds(331, 104, 89, 23);
        updater.add(admin_edit);

        JButton admin_remove = new JButton("Remove");
        admin_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String code = Itemcode_txt.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(Url, User, Pass);
                    Statement statement = connection.createStatement();

                    //to select from database itemtable with category =Accessories
                    String sql = "Delete from itemtable where itemcode = "+code+";";

                    System.out.println(sql);
                    statement.executeUpdate(sql);
                    Itemcode_txt.setEnabled(false);
                    Itemcode_txt.setEditable(false);

                    String showagain = "Select * from itemtable";
                    ResultSet gpu = statement.executeQuery(showagain);
                    DefaultTableModel model =(DefaultTableModel) admintable.getModel();
                    model.setRowCount(0);

                    Itemcode_txt.setEnabled(true);
                    Itemcode_txt.setEditable(true);

                    while (gpu.next()) {
                        String name = gpu.getString(2);
                        //String pd_desc = cpu.getString(2);
                        int stock = gpu.getInt(3);
                        int price = gpu.getInt(4);
                        int itemcode = gpu.getInt(5);
                        String categ=gpu.getString(6);
                        Object[] row ={name, stock, price, itemcode,categ};
                        model.addRow(row);
                    }
                    JOptionPane.showMessageDialog(contentPane,"Item Deleted Successfully","Delete",JOptionPane.INFORMATION_MESSAGE);


                    Itemcode_txt.setText("");
                    Itemname_txt.setText("");

                    Price_txt.setText("");
                    Stock_txt.setText("");
                    Category_txt.setText("");



                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(contentPane,"Invalid itemcode","Invalid itemcode",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        admin_remove.setBounds(331, 152, 89, 23);
        updater.add(admin_remove);
    }
}