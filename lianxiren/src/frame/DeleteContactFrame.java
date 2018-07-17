package frame;
import java.awt.*;
import javax.swing.*;
import db.ContactDao;
import java.awt.event.*;
import java.sql.*;

public class DeleteContactFrame extends JFrame 
	implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("��  ����", JLabel.CENTER);
	JTextField tf = new JTextField(15);
	JScrollPane sp = new JScrollPane();
	JLabel msg = new JLabel("", JLabel.CENTER);
	
	JButton b_qry = new JButton("��    ѯ");
	JButton b_del = new JButton("ɾ    ��");
	
	//��ʼ������
	public DeleteContactFrame() {
		super("ɾ����ϵ��");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p = new JPanel
		(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p.add(l1);
		p.add(tf);
		p.add(b_qry);
		p.add(b_del);

		sp.setBorder(BorderFactory.
				createTitledBorder("��ɾ������ϵ��"));

		msg.setForeground(Color.RED);

		c.add(p);
		c.add(sp);
		c.add(msg);

		p.setBounds(0, 0, 500, 35);
		sp.setBounds(5, 40, 480, 60);
		msg.setBounds(5, 110, 480, 50);

		this.setBounds(350, 200, 500, 300);
		this.setResizable(false);
		this.setVisible(true);

		// �رղ��������������ڣ������˳�Ӧ�ó���
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ע�������
		b_qry.addActionListener(this);
		b_del.addActionListener(this);
	}

	//��ť �¼�����
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// ��ѯ
		if (src == b_qry) {
			//�����һ�ֵķ�����Ϣ
			msg.setText("");
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

		// ɾ��
		if (src == b_del) {
			//��ȡ��ɾ����ϵ�˵�����
			String name = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "��������Ϊ��", "����",
				JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				name = tf.getText().trim();
			}

			//ȷ�϶Ի���
			int option = JOptionPane.
			showConfirmDialog(null, "ȷ��Ҫɾ������ϵ����",
			"ȷ��ɾ��",JOptionPane.OK_CANCEL_OPTION);

			//��ȷ�϶Ի���ѡ���ˡ��ǡ�����ִ��ɾ��������ִ��ɾ��
			if (option == JOptionPane.OK_OPTION) { // ȷ��ɾ��
				String sql = "delete from contact" +
						" where name='" + name + "'";
				int result = ContactDao.updateContact(sql);
				if (result == 1) {
					msg.setText("ɾ���ɹ���");
					sp.setViewportView(null);					
				} else {
					msg.setText("ɾ��ʧ��");
				}
			} else { // ȡ��ɾ��
				return;
			}
		}
	}
}
