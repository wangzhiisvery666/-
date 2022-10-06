package ccut.Exception;

import ccut.common.ErrorEnum;

 public class CustomizeException
   extends RuntimeException
 {
   private ErrorEnum error;

   public CustomizeException(ErrorEnum error) {
     this.error = error;
   }

   public ErrorEnum getError() {
     return this.error;
   }
 }

