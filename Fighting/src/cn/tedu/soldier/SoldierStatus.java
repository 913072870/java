package cn.tedu.soldier;

/**
 * 记录战士当前的状态
 * @author ZHLM
 *
 */
public enum SoldierStatus {
	STAND,	//站立
	WARK_RIGHT,	//向右行走
	WARK_LEFT,	//向左行走
	RUN_LEFT,	//向左冲刺
	RUN_RIGHT,	//向右冲刺
	ATTACK_1,	//普通攻击
	ATTACK_2,
	ATTACK_3,
	HURT, 	//受伤
	JUMP,	//跳跃
	
}
