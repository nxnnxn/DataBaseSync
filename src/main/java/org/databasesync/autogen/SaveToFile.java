package org.databasesync.autogen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.databasesync.util.SqlSessionFactoryUtil;
import org.dom4j.DocumentException;

/**
 * 
 * @ClassName: SaveToFile
 * @Description:TODO
 * 
 * @author nxn on 2013-3-13
 */
public class SaveToFile {

	public String saveAsOutputStreamWriter(String fileName, String saveContent,
			String savePath) throws IOException, DocumentException {

		String filePathAndName = "";
		BufferedWriter bwriter = null;
		try {
			savePath = savePath.replace(".", "\\");
			// 创建输出文件
			File fo = new File(savePath);
			// 文件不存在,就创建该文件
			// fo.delete();
			if (!fo.exists()) {
				// 先创建文件的目录
				File pFile = new File(savePath);
				pFile.mkdirs();
			}
			filePathAndName = savePath + "\\" + fileName;
			bwriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePathAndName), "GBK"));
			if (saveContent != null) {
				bwriter.write(saveContent);
			}

		} catch (IOException e) {

			throw e;
		} finally {
			try {
				bwriter.flush();
			} catch (IOException e) {

				throw e;
			} finally {
				try {
					bwriter.close();
				} catch (IOException e) {

				} finally {
					bwriter = null;
				}
			}
		}
		return filePathAndName;
	}

	public List<Metas> selectMetasByTable(Map map) {
		List<Metas> metasList = new ArrayList<Metas>();
		SqlSession session;

		session = SqlSessionFactoryUtil.getDefalultInstance().openSession();
		metasList = session
				.selectList("com.databasesync.autogen.MetasMapper.selectTableMetas");
		session.close();

		return metasList;
	}

}
