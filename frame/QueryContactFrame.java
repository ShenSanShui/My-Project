package frame;
import javax.swing.*;
import db.ContactDao;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class QueryContactFrame extends JFrame
			implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("��  ��");
	JTextField tf = new JTextField(15);
	JButton b_qry = new JButton("����");
	JButton b_all = new JButton("������");
	JScrollPane sp = new JScrollPane();

	// ��ʼ������
	public QueryContactFrame() {
		super("������ϵ��");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p.add(l1);
		p.add(tf);
		p.add(b_qry);
		p.add(b_all);

		c.add(p);
		c.add(sp);

		p.setBounds(0, 0, 600, 35);
		sp.setBounds(5, 40, 580, 320);
		sp.setBorder(BorderFactory.createTitledBorder("��ϵ���б�"));

		this.setBounds(350, 200, 600, 400);
		this.setResizable(false);
		this.setVisible(true);

		// �رղ��������������ڣ������˳�Ӧ�ó���
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ע�������
		b_all.addActionListener(this);
		b_qry.addActionListener(this);
	}

	// ��ť�¼�����
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// ������ϵ��
		if (src == b_all) {
			tf.setText(""); // �����һ�ֵļ����ؼ���
			ResultSet rt = ContactDao.getAllContacts();
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}

		// ����������
		if (src == b_qry) {
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "��ѯ�ؼ��ֲ���Ϊ��", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			ResultSet rt = ContactDao.getContact(tf.getText().trim());
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}
	}
}
