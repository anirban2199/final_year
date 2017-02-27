package project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected JButton cashW = new JButton("Cash Withdrawal");
	protected JButton cashD = new JButton("Cash Deposit");
	protected JButton balEN = new JButton("Balance Enquiry");
	static JFrame jf;
	static String[] str=new String[4];
	
	public Menu(String[] args) {
		setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
		setLayout(new BorderLayout());

		JPanel jpNorth = new JPanel();
		JPanel jpEast = new JPanel();
		JPanel jpWest = new JPanel();
		
		add(jpNorth, BorderLayout.NORTH);
		add(jpEast, BorderLayout.EAST);
		add(jpWest, BorderLayout.WEST);
		
		//jpNorth.setLayout(new GridLayout(1, 1, 10, 10));
		jpEast.setLayout(new GridLayout(5, 1, 10, 10));
		//jpWest.setLayout(new GridLayout(2, 1, 0, 10));
		
		jpEast.add(new JLabel("Welcome "+args[2]));
		jpEast.add(new JLabel("Please select the type of transaction"));
		jpEast.add(cashW);
		jpEast.add(cashD);
		jpEast.add(balEN);
		
		balEN.addActionListener(this);
		cashD.addActionListener(this);
		cashW.addActionListener(this);
	}
	public static void main(String[] args) {
		JPanel jp = new Menu(args);
		jf = new JFrame("Transaction Menu");
		jf.setContentPane(jp);
		jf.setResizable(false);
		jf.pack();	

		Dimension d1 = jf.getSize();
		
		for(int i=0;i<args.length;i++)
		str[i]=args[i];
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d2 = t.getScreenSize();
		
		int x = (d2.width - d1.width) / 2;
		int y = (d2.height - d1.height) / 2;
		
		jf.setLocation(x, y);
		jf.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		if (source == balEN) {
			JOptionPane.showMessageDialog(this, "Current balance is Rs "+str[3]);
			jf.dispose();
			FirstScreen.main(null);
		}
		else if (source == cashW) {
			Withdrawal w=new Withdrawal();
			w.getBalance(str[3],str[0]);
			Withdrawal.main(null);
			jf.dispose();
		}
		else if(source == cashD) {
			Deposit d=new Deposit();
			d.getBalance(str[3],str[0]);
			Deposit.main(null);
			jf.dispose();
		}
		
	}
}