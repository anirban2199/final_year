package project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oracle.jdbc.OracleDriver;

public class Deposit extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected JTextField amount = new JTextField(10);
	protected JButton dep = new JButton("Deposit");
	static JFrame jf;
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="anband";
	private static final String password="1234";
	static String bal,pin;
	public Deposit() {
		setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
		setLayout(new BorderLayout(10, 10));
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel();
		add(jpCenter, BorderLayout.CENTER);
		add(jpSouth, BorderLayout.SOUTH);
		jpCenter.setLayout(new GridLayout(2,1,10,10));
		jpSouth.setLayout(new FlowLayout());
		jpCenter.add(new JLabel("Enter deposit amount"));
		jpCenter.add(amount);
		jpSouth.add(dep);
		dep.addActionListener(this);
	}
	public static void main(String[] args) {
		JPanel jp = new Deposit();
		jf = new JFrame("Welcome");
		jf.setContentPane(jp);
		jf.setResizable(false);
		jf.pack();	
		
		Dimension d1 = jf.getSize();
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d2 = t.getScreenSize();
		
		int x = (d2.width - d1.width) / 2;
		int y = (d2.height - d1.height) / 2;
		
		jf.setLocation(x, y);
		jf.setVisible(true);
	}
	public void getBalance(String string,String string2) {
		// TODO Auto-generated method stub
		bal=string;
		pin=string2;
	}
	/*public void getBalance(String string) {
		bal=string;
	}*/
	
	public void actionPerformed(ActionEvent e) {
		int balance=Integer.parseInt(bal);
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs;
		String sql="update Bank set balance=? where pin=?";
		Driver driver=new OracleDriver();
		if(validate(Integer.parseInt(amount.getText()))) {
		try {
			DriverManager.registerDriver(driver);
			con=DriverManager.getConnection(url, user, password);
			ps=con.prepareStatement(sql);
			ps.setInt(1, (Integer.parseInt(amount.getText())+balance));
			ps.setInt(2, Integer.parseInt(pin));
			rs=ps.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(this, "Cash deposited successfully");
				jf.dispose();
				FirstScreen.main(null);
				jf.dispose();
			}
		} catch(SQLException s) {
			s.printStackTrace();
		}
		} else {
			JOptionPane.showMessageDialog(this, "Invalid amount, Enter in multiples of 100, 500 or 1000");
			jf.dispose();
			FirstScreen.main(null);
			jf.dispose();
		}
	}
	private boolean validate(int money) {
		if(money%100==0 || money%500==0 || money%1000==0) {
			return true;
		}
		return false;
	}
	
}