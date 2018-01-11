package cn.tedu.soldier.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import cn.tedu.soldier.Soldier;
import cn.tedu.soldier.SoldierStatus;
import cn.tedu.util.ImageUtil;

public class BoySoldier extends Soldier {

	private static List<BufferedImage[][]> attackImages = new LinkedList<BufferedImage[][]>();
	
	static {
		try {
			attackImages.add(ImageUtil.getBufferedImages("男战士出招1.jpg", 5, 3, 12));
			attackImages.add(ImageUtil.getBufferedImages("男战士出招2.jpg", 5, 3, 12));
			attackImages.add(ImageUtil.getBufferedImages("男战士出招3.jpg", 5, 3, 12));
			
			standImage = ImageUtil.getBufferedImages("男战士站立.jpg", 5, 3, 12);
			warkImage = ImageUtil.getBufferedImages("男战士前进.jpg", 5, 3, 12);
			runImage = ImageUtil.getBufferedImages("男战士冲刺.jpg", 5, 3, 12);
			jumpImage = ImageUtil.getBufferedImages("男战士跳跃.jpg", 5, 4, 19);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BoySoldier() {
		super();
	}

	@Override
	public void hurt(Graphics g) {
		g.drawImage(hurtImage[direction][index % hurtImage[direction].length], x, y, null);
	}

	@Override
	public void attack(Graphics g) {
		int x = 0;
		if(this.status == SoldierStatus.ATTACK_1){
			x = this.x - (this.direction == 0 ? 1 : -1) * width / 3;
			attackImage = attackImages.get(0);
		}else if(this.status == SoldierStatus.ATTACK_2){
			x = this.x - (this.direction == 0 ? 1 : -1) * width / 6;
			attackImage = attackImages.get(1);
		}else {
			x = this.x - (this.direction == 0 ? 1 : -1) * width / 10;
			attackImage = attackImages.get(2);
		}
		g.drawImage(attackImage[direction][index % attackImage[direction].length], x, y, null);
		if (index >= attackImage[direction].length - 1) {
			status = status_old;
		}
	}

	@Override
	public void stand(Graphics g) {
		g.drawImage(standImage[direction][index % standImage[direction].length], x, y, null);
	}

	@Override
	public void wark(Graphics g) {
		if(status == SoldierStatus.WARK_LEFT){
			g.drawImage(warkImage[direction][index % warkImage[direction].length], x, y, null);
		}else{
			g.drawImage(warkImage[direction][(warkImage[direction].length - 1) - index % warkImage[direction].length],
					x, y, null);
		}
	}

	@Override
	public void run(Graphics g) {
		if(status == SoldierStatus.RUN_LEFT){
			g.drawImage(runImage[direction][index % runImage[direction].length], x, y, null);
		}else{
			g.drawImage(runImage[direction][runImage[direction].length - 1 - index % runImage[direction].length], x, y,
					null);
		}
	}

	@Override
	public void useSkill() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getHurt(int injure) {
		this.life -= injure;
	}

	@Override
	public void draw(Graphics g) {
		switch (status) {
		case STAND:
			stand(g);
			break;
		case WARK_LEFT: case WARK_RIGHT:
			wark(g);
			break;
		case RUN_LEFT: case RUN_RIGHT:
			run(g);
			break;
		case ATTACK_1:case ATTACK_2:case ATTACK_3:
			attack(g);
			break;
		case HURT:
			hurt(g);
			break;
		case JUMP:
			jump(g);
			break;
		}

	}

}
