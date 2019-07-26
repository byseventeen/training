package com.chinasofti.day10jsp.beans;

/*
	保存商品的信息
*/
public class Goods {
	private Integer goodsId; //	商品编号、
	private String goodsName; //	商品名称、
	private String goodsDesc; //	商品简介、
	private Integer price; //	商品价格
	
	public Goods(Integer goodsId, String goodsName, String goodsDesc,
			Integer price) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsDesc = goodsDesc;
		this.price = price;
	}
	
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsDesc=" + goodsDesc + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsId == null) ? 0 : goodsId.hashCode());
		return result;
	}

	
	// 对象之间的比较是通过equals方法进行比较
	// 如果equals方法返回true，代表两个对象相等，否则就不相等
	@Override
	public boolean equals(Object obj) {
		// 第一步：比较两个两个对象的内存地址。如果内存地址相同就代表是同一个对象，直接return true。
		if (this == obj) return true;
		// 第二步：如果两个对象的内存地址不相等，那么判断obj的类型是否是Goods类型
		// 如果不是，就返回false。
		// 如果是，先把obj强转换成Goods，然后在比较他们的goodsId是否相等。
		if (obj instanceof Goods) {
			Goods goods = (Goods) obj;
			return goods.getGoodsId() == this.goodsId;
		}
		return false;
	}
	
	
	
}
