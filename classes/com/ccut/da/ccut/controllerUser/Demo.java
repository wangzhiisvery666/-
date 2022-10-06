///*    */ package java
//
//-INF.classes.com.ccut.dachuang.ccut.controllerUser;
///*    */
///*    */ import com.aliyun.oss.ClientException;
///*    */ import com.aliyun.oss.OSS;
///*    */ import com.aliyun.oss.OSSClientBuilder;
///*    */ import com.aliyun.oss.OSSException;
///*    */ import com.aliyun.oss.ccut.model.PutObjectRequest;
///*    */ import com.aliyun.oss.ccut.model.PutObjectResult;
///*    */ import java.io.File;
///*    */
///*    */
///*    */
///*    */ public class Demo
///*    */ {
///*    */   public static void main(String[] args) throws ccut.Exception {
///* 16 */     String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
///*    */
///* 18 */     String accessKeyId = "LTAI5tFVZUUkSEz4Fro8LF1P";
///* 19 */     String accessKeySecret = "tdOoaFU036DJ8PC1IIHl9f48vQ6Dkr";
///*    */
///* 21 */     String bucketName = "wzpicture";
///*    */
///* 23 */     String objectName = "img/exampleobject.jpg";
///*    */
///*    */
///* 26 */     String filePath = "D:\\picture.jpg";
///*    */
///*    */
///* 29 */     OSS ossClient = (new OSSClientBuilder()).build(endpoint, accessKeyId, accessKeySecret);
///*    */
///*    */
///*    */     try {
///* 33 */       PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///* 41 */       PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
///*    */     }
///* 43 */     catch (OSSException oe) {
///* 44 */       System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
///*    */
///* 46 */       System.out.println("Error Message:" + oe.getErrorMessage());
///* 47 */       System.out.println("Error Code:" + oe.getErrorCode());
///* 48 */       System.out.println("Request ID:" + oe.getRequestId());
///* 49 */       System.out.println("Host ID:" + oe.getHostId());
///* 50 */     } catch (ClientException ce) {
///* 51 */       System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
///*    */
///*    */
///* 54 */       System.out.println("Error Message:" + ce.getMessage());
///*    */     } finally {
///* 56 */       if (ossClient != null)
///* 57 */         ossClient.shutdown();
///*    */     }
///*    */   }
///*    */ }
//
