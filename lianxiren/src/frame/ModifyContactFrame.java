package frame;
import javax.swing.*;
import db.ContactDao;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ModifyContactFrame extends JFrame 
		implements ActionListener {	

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("��  ����", JLabel.CENTER);
	JLabel l2 = new JLabel("�����Ա�  ", JLabel.RIGHT);
	JLabel l3 = new JLabel("��������  ", JLabel.RIGHT);
	JLabel l4 = new JLabel("���µ绰  ", JLabel.RIGHT);
	JLabel l5 = new JLabel("����Email ", JLabel.RIGHT);
	JTextField tf = new JTextField(15);
	JTextField tf_sex = new JTextField(15);
	JTextField tf_age = new JTextField(15);
	JTextField tf_phone = new JTextField(15);
	JTextField tf_email = new JTextField(15);
	JScrollPane sp = new JScrollPane();
	JButton b_qry = new JButton("��    ѯ");
	JButton b_upd = new JButton("��    ��");
	JButton b_res = new JButton("��    ��");

	//��ʼ������
	ModifyContactFrame() {
		super("������ϵ��");
		Container c = this.getContentPane();
		c.setLayout(null);
		
		JPanel p1 = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		p1.add(l1);
		p1.add(tf);
		p1.add(b_qry);

		JPanel p2 = new JPanel(new GridLayout(4, 2, 10, 3));
		p2.add(l2);
		p2.add(tf_sex);
		p2.add(l3);
		p2.add(tf_age);
		p2.add(l4);
		p2.add(tf_phone);
		p2.add(l5);
		p2.add(tf_email);

		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p3.add(b_upd);
		p3.add(b_res);
		
		sp.setBorder(BorderFactory.createTitledBorder("�����µ���ϵ��"));
		
		c.add(p1);
		c.add(p2);
		c.add(sp);
		c.add(p3);

		p1.setBounds(0, 0, 500, 35);
		sp.setBounds(5, 40, 480, 60);
		p2.setBounds(50, 110, 300, 120);
		p3.setBounds(150, 230, 200, 40);

		this.setBounds(300, 200, 500, 400);
		this.setResizable(false);
		this.setVisible(true);

		// �رղ��������������ڣ������˳�Ӧ�ó���
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ע�������
		b_qry.addActionListener(this);
		b_upd.addActionListener(this);

		// ���ð�ť�¼�����
		b_res.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				tf_sex.setText("");
				tf_age.setText("");
				tf_phone.setText("");
				tf_email.setText("");
				sp.setViewportView(null);
			}
		});

	}

	//��ѯ �����°�ť �¼�����
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// ��ѯ
		if (src == b_qry) {
			// �����һ�ָ����ı����е�����
			tf_sex.setText("");
			tf_age.setText("");
			tf_phone.setText("");
			tf_email.setText("");

			// ��ȡ��ѯ����ϵ������
			String name = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "��������Ϊ��", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				name = tf.getText().trim();
			}

			// ��ѯ�����µ���ϵ����Ϣ
			ResultSet rt = ContactDao.getContactByName(name);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}

		// ����
		if (src == b_upd) {
			String name = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "��������Ϊ��", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				name = tf.getText().trim();
			}
			String sql = "";
			String newSex = "";
			int newAge = 0;
			String newPhone = "";
			String newEmail = "";

			// ���Ա�ǿգ�������Ա�
			if (!tf_sex.getText().equals("")) {

				if (!tf_sex.getText().matches("[��|Ů|M|F]")) {
					JOptionPane.showMessageDialog
					(this, "�Ա�����ǣ��л�Ů��M��F������������",
							"����", JOptionPane.ERROR_MESSAGE);
					tf_sex.setText("");
				} else {
					newSex = tf_sex.getText();
					sql = "update contact set sex='" + newSex
							+ "' where name='" + name + "'";
					ContactDao.updateContact(sql);
				}
			}

			// ������ǿգ����������
			if (!tf_age.getText().equals("")) {
				if (!tf_age.getText().matches("[0-9]{1,3}")) {
					JOptionPane.showMessageDialog
					(this, "�����������ֵ������������", "����",
							JOptionPane.ERROR_MESSAGE);
					tf_age.setText("");
				} else {
					if (tf_age.getText().equals("")) {
						newAge = 0;
					} else {
						newAge = Integer.parseInt(tf_age.getText());
					}
					sql = "update contact set age=" 
						+ newAge + " where name='"
							+ name + "'";
					ContactDao.updateContact(sql);
				}

			}

			// ���绰�ǿգ�����µ绰
			if (!tf_phone.getText().equals("")) {

				if (!tf_phone.getText().matches("[0-9]{8,11}")) {
					JOptionPane.showMessageDialog
					(this, "�绰���������8~11λ���֣�����������",
							"����", JOptionPane.ERROR_MESSAGE);
					tf_phone.setText("");

				} else {
					newPhone = tf_phone.getText();
					sql = "update contact set phone='" + newPhone
							+ "' where name='" + name + "'";
					ContactDao.updateContact(sql);
				}
			}

			// ��email�ǿգ������email
			if (!tf_email.getText().equals("")) {

				newEmail = tf_email.getText();
				sql = "update contact set email='" + newEmail
						+ "' where name='" + name + "'";
				ContactDao.updateContact(sql);
			}

			// ���½��������¼�������ϵ�ˣ���ʾ���º����Ϣ
			ResultSet rt = ContactDao.getContactByName(name);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
			
			tf_sex.setText("");
			tf_age.setText("");
			tf_phone.setText("");
			tf_email.setText("");
		}
	}
}
