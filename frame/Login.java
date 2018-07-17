package frame;
import javax.swing.*;
import db.ContactDao;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame 
			implements ActionListener {
	private static final long serialVersionUID = 1L;

	JLabel lName = new JLabel("��½�˺ţ�");
	JLabel lPw = new JLabel("��½���룺");
	JTextField tName = new JTextField(15);
	JPasswordField tPw = new JPasswordField(15);
	JButton btnOk = new JButton("��  ½");
	JButton btnExit = new JButton("��  ��");

	//��ʼ������
	public Login() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 8));

		c.add(lName);
		c.add(tName);
		c.add(lPw);
		c.add(tPw);
		c.add(btnOk);
		c.add(btnExit);

		// 2����ťע�������
		btnOk.addActionListener(this);
		btnExit.addActionListener(this);

		this.setTitle("�����֤");
		this.setBounds(400, 300, 280, 140);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	//��ť�¼�����
	public void actionPerformed(ActionEvent e) {
		//�˳���ť
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
		//��½��ť
		if (e.getSource() == btnOk) {
			// �Ȼ�ȡ�û�������ʻ���������
			String name = tName.getText();
			char[] pwTmp = tPw.getPassword();
			String pw = new String(pwTmp);
			
			boolean flag = ContactDao.checkUser(name, pw);

			if (flag == true) {				
				this.dispose();
				new MainFrame();	//��֤�ɹ�������������
			} else {
				JOptionPane.showMessageDialog(this, 
						"���ź����˺Ż��������", "������ʾ",
						JOptionPane.ERROR_MESSAGE);
			}
			// ����û�֮ǰ������
			tName.setText("");
			tPw.setText("");
			tName.grabFocus();
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
