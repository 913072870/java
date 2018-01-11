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
	protected int x;								//����λ��:������
	protected int y;								//����λ��:������
	protected int life = 100;						//��������ֵ
	protected int energy = 0;	
	protected int width;							//�������ռ��С:��
	protected int height;							//�������ռ��С:��
	
	protected int index=0;							//���ŵڼ���
	protected static final int INTERVEL = 60;		//���������ٶ�
	

	protected static List<SoldierStatus> attackList = Arrays.asList(new SoldierStatus[]{SoldierStatus.ATTACK_1,SoldierStatus.ATTACK_2,SoldierStatus.ATTACK_3});

	protected int direction; // ������0��1��
	protected SoldierStatus status;
	protected SoldierStatus status_old;
	protected static BufferedImage[][] hurtImage; // ����ʱ��ͼƬ
	protected static BufferedImage[][] attackImage; // ��ͨ����
	protected static BufferedImage[][] standImage; // վ��
	protected static BufferedImage[][] warkImage; // ���ߵ�ͼƬ
	protected static BufferedImage[][] runImage; // ���
	protected static BufferedImage[][] jumpImage; // ��Ծ
	public Soldier() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){//��ʱ��������
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
	 * �ƶ���ָ��λ��
	 * @param x
	 * @param y
	 */
	public void moveTo(int x,int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * ���õ�ǰ�����״̬
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
	 * ����
	 * @param injure �յ����˺�
	 */
	public abstract void getHurt(int injure);
	
	/**
	 * ����ǰ״̬���Ƶ�����
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
