package cn.tedu.util;

import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.Properties;

import cn.tedu.FightingGame;

public class ConfigUtil {
	private static Properties pro;
	private static Class<KeyEvent> KeyEventClass;
	static{
		try {
			pro = new Properties();
			InputStream in = FightingGame.class.getResourceAsStream("resources/control.properties");
			pro.load(in);
			in.close();
			KeyEventClass = KeyEvent.class;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * �������ļ��л�ȡ����ֵ
	 * @param key
	 * @return
	 */
	
	public static String getValue (String key){
		return pro.getProperty(key).trim().toUpperCase();
	}
	/**
	 * ͨ�������ļ���ȡ������Ӧ�� KeyCode
	 * @param key
	 * @return
	 */
	public static int getVKValue (String key) {
		try {
			return KeyEventClass.getField("VK_"+getValue(key)).getInt(KeyEventClass);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
}
