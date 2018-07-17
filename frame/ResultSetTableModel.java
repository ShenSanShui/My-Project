package frame;
import javax.swing.table.*;
import java.sql.*;

public class ResultSetTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public ResultSetTableModel(ResultSet rs) {
		this.rs = rs;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ������д
	public int getRowCount() {
		int rowCount = 0;
		try {
			rs.last();
			rowCount = rs.getRow();
			return rowCount;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	// ������д
	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}

	}

	// ������д
	public Object getValueAt(int row, int column) {
		try {
			rs.absolute(row + 1);
			return rs.getObject(column + 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// ��ѡ��������д�������������ģ�Ͱ���Ĭ�ϵ� A B C D ... �������������ֶ�
	public String getColumnName(int column) {
		try {
			return rsmd.getColumnName(column + 1);
		} catch (SQLException e) {
			return String.valueOf(column + 1);
		}
	}
}