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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import oracle.jdbc.OracleDriver;


public class PinNumber extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected JButton next = new JButton("Next");
	protected JPasswordField pin = new JPasswordField(4);
	static JFrame jf;
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="anband";
	private static final String password="1234";
	int p;
	public PinNumber() {
		setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		setLayout(new BorderLayout(10, 10));
		
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel();
		JPanel jpNorth=new JPanel();
		
		add(jpNorth, BorderLayout.NORTH);
		add(jpCenter, BorderLayout.CENTER);
		add(jpSouth, BorderLayout.SOUTH);
		
		jpNorth.setLayout(new FlowLayout());
		jpCenter.setLayout(new FlowLayout());
		jpSouth.setLayout(new FlowLayout());
		
		jpNorth.add(new JLabel("Please enter your PIN number"));
		jpCenter.add(pin);
		jpSouth.add(next);
		
		next.addActionListener(this);
	}
	public static void main(String[] args) {
		JPanel jp = new PinNumber();
		jf = new JFrame("Pin Number");
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

	public void actionPerformed(ActionEvent e) {
		p=Integer.parseInt(pin.getText()); 
		//System.out.println(p);
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs;
		String sql="select * from Bank where pin=?";
		Driver driver=new OracleDriver();
		try {
			DriverManager.registerDriver(driver);
			con=DriverManager.getConnection(url, user, password);
			ps=con.prepareStatement(sql);
			ps.setInt(1, p);
			rs=ps.executeQuery();
			if(rs.next()) {
				String[] dtls={pin.getText(),Integer.toString(rs.getInt(2)),
						rs.getString(3),rs.getString(4)};
				Menu.main(dtls);
				jf.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid pin, Enter your card again");
				jf.dispose();
				FirstScreen.main(null);
			}
		} catch(SQLException s) {
			s.printStackTrace();
		}
	}
}