# day1 作业   

#### 简单购物结算

#### 一、作业要求

![1563238215154](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1563238215154.png)

#### 二、功能分析

- ​	购买商品

  判断用户输入的商品编号是否在集合中（是否合法）

  判断购买数量是否超过库存

  购买成功后加入已购买集合

- 查看所有商品

  读取商品集合并打印

- 打印小票

  总价等于数量*金额

  按照格式打印已购买集合内的商品信息和金额

- 退出

- 操作说明：用户进入程序，选择功能，输入购买商品编号、数量，选择结算打印，退出。

  

  #### 三、主要功能实现

  ```java
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
  ```



​		

```java
case 2:
				System.out.println("商品编号\t\t商品名称\t\t价格\t\t库存");
				// 输出goodsList集合的商品
				for (Goods goods : goodsList) {
					System.out.println(goods.goodsNum + "\t\t" + goods.goodsName + "\t\t"
							+ goods.price + "\t\t" + goods.amount);
				} 
				break
```



```java
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
```



```java
			case 4:
				System.out.println("谢谢惠顾！欢迎下次光临！");
				System.exit(0);
				break;
```



#### 四、细节

- 定义Goods商品类时顺便把get、set方法调出来，快捷键ctrl+shift+s
- 集合的定义，保存所有商品和保存已购买商品
- next和nextline方法

