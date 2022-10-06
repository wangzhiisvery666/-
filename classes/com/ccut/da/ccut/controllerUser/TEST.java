///*    */ package java-INF.classes.com.ccut.dachuang.ccut.controllerUser;
///*    */
///*    */ @ApiModel("测试")
///*    */ public class TEST {
///*    */   @ApiModelProperty("测试用1")
///*    */   private int a;
///*    */
///*  8 */   public void setA(int a) { this.a = a; } @ApiModelProperty("测试用2") private int b; public void setB(int b) { this.b = b; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof com.ccut.dachuang.ccut.controllerUser.TEST)) return false;  com.ccut.dachuang.ccut.controllerUser.TEST other = (com.ccut.dachuang.ccut.controllerUser.TEST)o; return !other.canEqual(this) ? false : ((getA() != other.getA()) ? false : (!(getB() != other.getB()))); } protected boolean canEqual(Object other) { return other instanceof com.ccut.dachuang.ccut.controllerUser.TEST; } public int hashCode() { int PRIME = 59; result = 1; result = result * 59 + getA(); return result * 59 + getB(); } public String toString() { return "TEST(a=" + getA() + ", b=" + getB() + ")"; }
///*    */
///*    */
///*    */   public int getA() {
///* 12 */     return this.a;
///*    */   } public int getB() {
///* 14 */     return this.b;
///*    */   }
///*    */ }
//
//
///* Location:              D:\java代码\dachaung\target\dc-3.0.jar!\BOOT-INF\classes\com\ccut\dachuang\ccut.controllerUser\TEST.class
// * Java compiler version: 8 (52.0)
// * JD-Core Version:       1.1.3
// */