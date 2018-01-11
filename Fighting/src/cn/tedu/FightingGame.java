package cn.tedu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.tedu.soldier.Soldier;
import cn.tedu.soldier.SoldierStatus;
import cn.tedu.soldier.impl.BoySoldier;
import cn.tedu.soldier.impl.GirlSoldier;
import cn.tedu.util.ConfigUtil;



public class FightingGame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5529676381500441937L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 336;
	public static BufferedImage background;
	public static int k=0,x=0,y=0;
	public static final int INTERVEL = 10;
	
	public static Soldier boySoldier;
	public static Soldier girlSoldier;
	
	static {
		try {
			background = ImageIO.read(FightingGame.class.getResource("image/背景.gif"));
			boySoldier = new BoySoldier();
			girlSoldier = new GirlSoldier();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Shootgame");//创建窗口对象
		FightingGame game = new FightingGame();//创建面板对象
		frame.add(game);//添加游戏
		frame.setSize(WIDTH, HEIGHT);//设置窗口大小
		frame.setAlwaysOnTop(true);//设置窗口置顶
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭窗口就是关闭程序
		frame.setLocationRelativeTo(null);//设置居中显示
		frame.setVisible(true);//设置窗口可见，尽快去调用paint（画）的方法
		frame.addKeyListener(new MyKeyAdapter());//处理键盘操作事件
		game.action();//启动程序执行
	}

	public void action(){
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){//定时器做的事
				repaint();
			}
		},INTERVEL,50);
				
	}
	@Override
	public void paint(Graphics g){
		g.drawImage(background, 0, 0, null);
		boySoldier.draw(g);
		
		
	}

	
	
	private static class MyKeyAdapter implements KeyListener{

		/**
		 * 按下某个键时调用此方法。
		 */
		@Override
		public void keyTyped(KeyEvent e) {
		}
		/**
		 * 释放某个键时调用此方法。
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == Control.BOY_MOVE_LEFT){
				boySoldier.setStatus(SoldierStatus.WARK_LEFT);
				boySoldier.setDirection(0);
			}else if(keyCode == Control.BOY_MOVE_RIGHT){
				boySoldier.setStatus(SoldierStatus.WARK_RIGHT);
				boySoldier.setDirection(1);
			}else if(keyCode == Control.BOY_ATTACK_1){
				boySoldier.setStatus(SoldierStatus.ATTACK_1);
			}else if(keyCode == Control.BOY_ATTACK_2){
				boySoldier.setStatus(SoldierStatus.ATTACK_2);
			}else if(keyCode == Control.BOY_ATTACK_3){
				boySoldier.setStatus(SoldierStatus.ATTACK_3);
			}else if(keyCode == Control.BOY_JUMP){
				boySoldier.setStatus(SoldierStatus.JUMP);
			}
			
		}

		/**
		 * 键入某个键时调用此方法。
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == Control.BOY_MOVE_LEFT || keyCode == Control.BOY_MOVE_RIGHT){
				boySoldier.setStatus(SoldierStatus.STAND);
			}
		}
		
	}
	public static class Control {
		
		public static final int BOY_JUMP = ConfigUtil.getVKValue("boy.move.jump");
		public static final int BOY_MOVE_LEFT = ConfigUtil.getVKValue("boy.move.left");
		public static final int BOY_MOVE_RIGHT = ConfigUtil.getVKValue("boy.move.right");
		public static final int BOY_ATTACK_1 = ConfigUtil.getVKValue("boy.attack.1");
		public static final int BOY_ATTACK_2 = ConfigUtil.getVKValue("boy.attack.2");
		public static final int BOY_ATTACK_3 = ConfigUtil.getVKValue("boy.attack.3");
		
	}
	
	
}
