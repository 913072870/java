package cn.tedu.soldier;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.tedu.FightingGame;
import cn.tedu.skill.BaseSkill;
import cn.tedu.skill.SoldierSkill;

public abstract class Soldier implements BaseSkill,SoldierSkill {
	protected int x;								//人物位置:横坐标
	protected int y;								//人物位置:纵坐标
	protected int life = 100;						//人物生命值
	protected int energy = 0;	
	protected int width;							//人物的所占大小:宽
	protected int height;							//人物的所占大小:高
	
	protected int index=0;							//播放第几张
	protected static final int INTERVEL = 60;		//动画播放速度
	

	protected static List<SoldierStatus> attackList = Arrays.asList(new SoldierStatus[]{SoldierStatus.ATTACK_1,SoldierStatus.ATTACK_2,SoldierStatus.ATTACK_3});

	protected int direction; // 面向方向，0左，1右
	protected SoldierStatus status;
	protected SoldierStatus status_old;
	protected static BufferedImage[][] hurtImage; // 受伤时的图片
	protected static BufferedImage[][] attackImage; // 普通攻击
	protected static BufferedImage[][] standImage; // 站立
	protected static BufferedImage[][] warkImage; // 行走的图片
	protected static BufferedImage[][] runImage; // 冲刺
	protected static BufferedImage[][] jumpImage; // 跳跃
	public Soldier() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){//定时器做的事
				index++;
				move();
			}
		},0,INTERVEL);
		status = SoldierStatus.STAND;
		status_old = SoldierStatus.STAND;
		x=FightingGame.WIDTH/2;
		y=FightingGame.HEIGHT/2;
		direction = 0;
		width = standImage[direction][0].getWidth();
		height = standImage[direction][0].getHeight();
	}
	
	
	/**
	 * 移动到指定位置
	 * @param x
	 * @param y
	 */
	public void moveTo(int x,int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * 设置当前人物的状态
	 * @param status
	 */
	public void setStatus(SoldierStatus status){
		if(attackList.contains(this.status) || this.status==SoldierStatus.JUMP){
			return;
		}
		
		if(attackList.contains(status)){
			status_old = this.status;
		}
		if(this.status != status){
			index=0;
			this.status = status;
		}
	}
	
	public void setDirection(int direction) {
		if (attackList.contains(status)) {
			return;
		}
		this.direction = direction;
	}

	/**
	 * 受伤
	 * @param injure 收到的伤害
	 */
	public abstract void getHurt(int injure);
	
	/**
	 * 将当前状态绘制到画板
	 * @param g
	 */
	public abstract void draw(Graphics g);
	
	public void jump(Graphics g){
		int len = jumpImage[direction].length;
		int y2 = -4*height/len/len *(index-len/2)*(index-len/2) + height;
		g.drawImage(jumpImage[direction][index % jumpImage[direction].length], x, y - y2, null);
		if (index >= jumpImage[direction].length - 1) {
			this.status = SoldierStatus.STAND;
		}
	}
	
	
	@SuppressWarnings("incomplete-switch")
	public void move(){
		switch (status) {
		case STAND:
			break;
		case WARK_LEFT:
			x-=2;
			break;
		case WARK_RIGHT:
			x+=2;
			break;
		case RUN_LEFT:
			x-=5;
			break;
		case RUN_RIGHT:
			x+=5;
			break;
		}
	};
	
}
