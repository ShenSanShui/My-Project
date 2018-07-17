package frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import db.Contact;
import db.ContactDao;

public class InsertContactFrame extends JFrame 
		implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("���� *");
	JLabel l2 = new JLabel("��   ��");
	JLabel l3 = new JLabel("��   ��");
	JLabel l4 = new JLabel("��   ��");
	JLabel l5 = new JLabel(" EMail ");
	JTextField tf1 = new JTextField(15);
	JTextField tf2 = new JTextField(15);
	JTextField tf3 = new JTextField(15);
	JTextField tf4 = new JTextField(15);
	JTextField tf5 = new JTextField(15);
	JLabel msg1 = new JLabel("  ע�⣺��*�ı��");
	JLabel msg2 = new JLabel();
	JButton btnIns = new JButton("�� ��");
	JButton btnRes = new JButton("�� ��");
	
	// ��ʼ������
	public InsertContactFrame() {
		super("�����ϵ��");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p1 = new JPanel(new GridLayout(1, 2));
		JPanel p2 = new JPanel
			(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JPanel p3 = new JPanel
			(new FlowLayout(FlowLayout.CENTER, 5, 15));

		p1.add(msg1);
		p1.add(msg2);

		p2.add(l1);
		p2.add(tf1);
		p2.add(l2);
		p2.add(tf2);
		p2.add(l3);
		p2.add(tf3);
		p2.add(l4);
		p2.add(tf4);
		p2.add(l5);
		p2.add(tf5);

		p3.add(btnIns);
		p3.add(btnRes);
		
		c.add(p1);
		c.add(p2);
		c.add(p3);

		p1.setBounds(0, 0, 360, 30);
		p2.setBounds(0, 30, 240, 170);
		p3.setBounds(240, 30, 100, 150);

		msg2.setForeground(Color.BLUE);

		this.setBounds(350, 200, 360, 210);		
		this.setResizable(false);
		this.setVisible(true);		

		//�޸�Ĭ�Ϲرղ���Ϊ DISPOSE_ON_CLOSE
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// ��ťע�������
		btnIns.addActionListener(this);
		btnRes.addActionListener(this);
	}

	//��ť�¼�����
	public void actionPerformed(ActionEvent arg0) {

		JButton src = (JButton) arg0.getSource();
		
		// ���
		if (src == btnIns) {
			msg2.setText(""); // �����һ����ӵķ�����Ϣ
			/*
			 * ȡ��ϵ����Ϣ����װ��Contact���� 
			 * ��Contact������뵽���ݿ�
			 */
			Contact aFriend = new Contact();

			// ��������Ϊ��			
			String name = null;			
			if (tf1.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "�� * �Ĳ���Ϊ�գ�", "����",
						JOptionPane.ERROR_MESSAGE);
				return;			
			}else{				
				name =  tf1.getText().trim();
			}			

			// �Ա�ֻ���� �С�Ů��M��F ��ʾ
			String sex = "";
			if (!tf2.getText().equals("")
					&& !tf2.getText().matches("[��|Ů|M|F]")) {
				JOptionPane.showMessageDialog(this, "�Ա�����ǣ��л�Ů��M��F������������",
						"����", JOptionPane.ERROR_MESSAGE);
				tf2.setText("");
				return;
			} else {
				sex = tf2.getText();
			}

			// �������������
			int age = 0;
			if (!tf3.getText().equals("")
					&& !tf3.getText().matches("[0-9]{1,3}")) {
				JOptionPane.showMessageDialog
				(this, "�����������ֵ������������", "����",
						JOptionPane.ERROR_MESSAGE);
				tf3.setText("");
				return;
			} else {
				if (tf3.getText().equals("")) {
					age = 0;
				} else {
					age = Integer.parseInt(tf3.getText());
				}
			}

			// �绰������������֣��ҹ� 8~11λ
			String phone = "";
			if (!tf4.getText().equals("")
					&& !tf4.getText().matches("[0-9]{8,11}")) {
				JOptionPane.showMessageDialog(this, "�绰���������8~11λ���֣�����������",
						"����", JOptionPane.ERROR_MESSAGE);
				tf4.setText("");
				return;
			} else {
				phone = tf4.getText();
			}

			String email = tf5.getText();

			// �����û��������ϵ����Ϣ���õ�1���������ϵ��ʵ��
			aFriend.setName(name);
			aFriend.setSex(sex);
			aFriend.setAge(age);
			aFriend.setPhone(phone);
			aFriend.setEmail(email);

			// ׼�� insert ���
			//����ContactDao��insertContact()����ʵ��¼����ϵ��
			String sql = "insert into contact" +
					"(name,sex,age,phone,email) " +
					"values(?,?,?,?,?)";
			int result = ContactDao.insertContact(sql, aFriend);
			
			// ����insertContact()������ִ�н�����ڽ�������ʾ��Ӧ�Ľ����Ϣ
			if (result == 1) {
				msg2.setText("�����ϵ�˳ɹ���");
				tf1.setText("���������ϵ����");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
			} else {
				msg2.setText("�����ϵ��ʧ�ܣ�����");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
			}
		}

		// ����
		if (src == btnRes) {
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
			msg2.setText("");
		}	
	}
}
