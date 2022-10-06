package ccut.service;

import com.baomidou.mybatisplus.extension.service.IService;


import ccut.common.CommonResponse;
import ccut.model.pojo.Address;
import ccut.model.request.RequestAddress;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface AddressService extends IService<Address> {
  CommonResponse<Boolean> addAdress(RequestAddress paramRequestAddress, HttpServletRequest paramHttpServletRequest);
  
  CommonResponse<Boolean> updateAdress(Address paramAddress);
  
  CommonResponse<Boolean> deleteAdress(Address paramAddress);
  
  CommonResponse<List<Address>> getAdress(HttpServletRequest paramHttpServletRequest);
  
  Address getDefaultAddress(int paramInt);
  
  CommonResponse<Boolean> getAddressSum(HttpServletRequest paramHttpServletRequest);
}

