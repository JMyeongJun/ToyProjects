package model;

public class SalesVo {
   private int salesId, orderId, usePoint, sales;
   private String orderDate, salesDate, paymentMethod;

   public SalesVo(int salesId, int orderId, int usePoint, int sales, String orderDate, String salesDate,
         String paymentMethod) {
      super();
      this.salesId = salesId;
      this.orderId = orderId;
      this.usePoint = usePoint;
      this.sales = sales;
      this.orderDate = orderDate;
      this.salesDate = salesDate;
      this.paymentMethod = paymentMethod;
   }

   public int getSalesId() {
      return salesId;
   }

   public void setSalesId(int salesId) {
      this.salesId = salesId;
   }

   public int getOrderId() {
      return orderId;
   }

   public void setOrderId(int orderId) {
      this.orderId = orderId;
   }

   public int getUsePoint() {
      return usePoint;
   }

   public void setUsePoint(int usePoint) {
      this.usePoint = usePoint;
   }

   public int getSales() {
      return sales;
   }

   public void setSales(int sales) {
      this.sales = sales;
   }

   public String getOrderDate() {
      return orderDate;
   }

   public void setOrderDate(String orderDate) {
      this.orderDate = orderDate;
   }

   public String getSalesDate() {
      return salesDate;
   }

   public void setSalesDate(String salesDate) {
      this.salesDate = salesDate;
   }

   public String getPaymentMethod() {
      return paymentMethod;
   }

   public void setPaymentMethod(String paymentMethod) {
      this.paymentMethod = paymentMethod;
   }

   @Override
   public String toString() {
      return "SalesVo [salesId=" + salesId + ", orderId=" + orderId + ", usePoint=" + usePoint + ", sales=" + sales
            + ", orderDate=" + orderDate + ", salesDate=" + salesDate + ", paymentMethod=" + paymentMethod + "]";
   }
   
   public String toString2() {
      
      return orderId + "," + orderDate + "," + paymentMethod + "," + Integer.toString(sales - usePoint) + "," + usePoint + "," + sales;
      
   }
 
}