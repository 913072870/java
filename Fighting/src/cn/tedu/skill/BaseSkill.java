package cn.tedu.skill;

import java.awt.Graphics;

/**
 * ͨ�ü���
 * @author ZHLM
 *
 */
public interface BaseSkill {
	/**
	 * ����
	 */
	public abstract void hurt(Graphics g);
	/**
	 * ����
	 */
	public abstract void attack(Graphics g);
	/**
	 * վ��
	 */
	public abstract void stand(Graphics g);
	/**
	 * ����
	 */
	public abstract void wark(Graphics g);
	/**
	 * ���
	 */
	public abstract void run(Graphics g);
	
}
