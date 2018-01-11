package cn.tedu.skill;

import java.awt.Graphics;

/**
 * 通用技能
 * @author ZHLM
 *
 */
public interface BaseSkill {
	/**
	 * 受伤
	 */
	public abstract void hurt(Graphics g);
	/**
	 * 攻击
	 */
	public abstract void attack(Graphics g);
	/**
	 * 站立
	 */
	public abstract void stand(Graphics g);
	/**
	 * 行走
	 */
	public abstract void wark(Graphics g);
	/**
	 * 冲刺
	 */
	public abstract void run(Graphics g);
	
}
