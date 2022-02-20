package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class ReportEg extends JFrame {

	private static String Url = "jdbc:mysql://localhost:3306/kbtcdatabase?useSSL=false";
	private static String User = "root";
	private static String Pass = "root";
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportEg frame = new ReportEg();
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
	public ReportEg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Open Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection(Url,User,Pass);
	String sql = "select name,roll,gender from student";					
	JasperDesign jdesign = JRXmlLoader.load("C:\\NewEclipse\\ReportProject2\\src\\Test\\StudentReport.jrxml");
	JRDesignQuery updateQuery = new JRDesignQuery();
	updateQuery.setText(sql);
	jdesign.setQuery(updateQuery);
	JasperReport Jreport = JasperCompileManager.compileReport(jdesign);
	JasperPrint JasperPrint = JasperFillManager.fillReport(Jreport, null,conn);
	JasperViewer.viewReport(JasperPrint,false);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(38, 11, 364, 35);
		contentPane.add(btnNewButton);
	}
}
