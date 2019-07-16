package shopping;

import java.util.ArrayList;
import java.util.Scanner;




class Goods {
	String goodsNum; // 商品编号
	String goodsName; // 商品名字
	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	int price; // 售价
	int amount; // 库存
	
	public Goods(String goodsNum, String goodsName, int price, int amount) {
		this.goodsNum = goodsNum;
		this.goodsName = goodsName;
		this.price = price;
		this.amount = amount;
	}	
}

public class logit {
	// 保存所有商品
	static ArrayList<Goods> goodsList = new ArrayList<Goods>();
	// 用户购买过的商品
	static ArrayList<Goods> myGoodsList = new ArrayList<Goods>();
	
	public static void main(String[] args) {
		SetGoods(); 	//初始化商品
		
		System.out.println("欢迎光临超市自助购物");
		while(true) {
			System.out.println("请选择： 1.购买商品 2.查看商品 3.打印小票 4.退出");
			Scanner sin = new Scanner(System.in);
			int opt= sin.nextInt();
			sin.nextLine();
			
			switch (opt) {
			case 1:
				System.out.println("请输入购买商品编号");
				String num= sin.nextLine();
				
				//判断编号是否存在
				boolean isExist=false;
				for(int i=0;i<goodsList.size();i++) {
					Goods gi=(Goods) goodsList.get(i);
					if(gi.getGoodsNum().equals(num)) {
						isExist=true;
						break;
						}
					}
				if (!isExist) {
					System.out.println("该编号不存在！");
				}
				else {
					System.out.println("请输入购买数量");
					int amount=sin.nextInt();
					//判断购买数量是否达到购买条件
					for(int i=0;i<goodsList.size();i++) {
						Goods gi=(Goods) goodsList.get(i);
						if(gi.getGoodsNum().equals(num)) {
							int amt=gi.getAmount();
							if(amount>amt) {
								System.out.println("库存不足，请再次输入！");
							}
							else {
								System.out.println("购买成功！");
								gi.setAmount(amt-amount);
								myGoodsList.add(new Goods(gi.getGoodsNum(), gi.getGoodsName(), gi.getPrice(), amount));
							}
							break;
						}
					}
				}
				break;
			case 2:
				System.out.println("商品编号\t\t商品名称\t\t价格\t\t库存");
				// 输出goodsList集合的商品
				for (Goods goods : goodsList) {
					System.out.println(goods.goodsNum + "\t\t" + goods.goodsName + "\t\t"
							+ goods.price + "\t\t" + goods.amount);
				} 
				
//				for(int i=0;i<goodsList.size();i++) {
//					
//				}
				break;
			case 3:
				System.out.println("                      欢迎光临！      ");
				System.out.println("商品名称\t\t售价\t\t数量\t\t金额");
				System.out.println("----------------------------------------------------");
				int total=0;  //总数量
				double totalMoney=0.0;//总金额
				for(int i=0;i<myGoodsList.size();i++) {
					Goods gi=(Goods) myGoodsList.get(i);
					double money=gi.getPrice()*gi.getAmount();
					System.out.println(gi.getGoodsName()+"\t\t"+gi.getPrice()+"\t\t"+gi.getAmount()+"\t\t"+money);
					total+=gi.getAmount();
					totalMoney+=money;
				}
				System.out.println("----------------------------------------------------");
				System.out.println("    "+myGoodsList.size()+"项商品  共计"+total+"件");
				System.out.println("总计："+totalMoney);
				break;
			case 4:
				System.out.println("谢谢惠顾！欢迎下次光临！");
				System.exit(0);
				break;
			
			default:
				System.out.println("不在输入正确输入范围内，请重新输入！");
			}
		}
	}
	
	//初始化商品
	private static void SetGoods() {
		goodsList.add(new Goods("001","乐事薯片",8,100));
		goodsList.add(new Goods("002", "可爱多", 6, 100));
		goodsList.add(new Goods("003", "可口可乐", 3, 100));
		goodsList.add(new Goods("004", "养乐多", 3, 100));
	}
}


