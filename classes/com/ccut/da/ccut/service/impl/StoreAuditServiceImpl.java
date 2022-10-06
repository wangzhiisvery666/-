package ccut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ccut.Exception.CustomizeException;
import ccut.common.BaseThreadLocal;
import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;
import ccut.mapper.StoreAuditMapper;
import ccut.mapper.StoreMapper;
import ccut.model.VO.StoreApplicationVO;
import ccut.model.pojo.Store;
import ccut.model.pojo.StoreAudit;
import ccut.model.request.StoreAuditRequest;
import ccut.service.StoreAuditService;
import java.util.List;
import ccut.utils.CommonUtils;
import ccut.utils.FileUploadUtils;


@Service
public class StoreAuditServiceImpl
        extends ServiceImpl<StoreAuditMapper, StoreAudit>
        implements StoreAuditService {


    @Autowired
    StoreAuditMapper storeAuditMapper;


    @Autowired
    StoreMapper storeMapper;


    public CommonResponse<Boolean> addStoreAudit(StoreAuditRequest storeAuditRequest) {
        String auditName = storeAuditRequest.getAuditName();
        String creditCode = storeAuditRequest.getCreditCode();
        String contactName = storeAuditRequest.getContactName();
        String contactPhone = storeAuditRequest.getContactPhone();
        String companyAddress = storeAuditRequest.getCompanyAddress();

        String storeDescription = storeAuditRequest.getStoreDescription();

        if (StringUtils.isAllBlank(new CharSequence[]{auditName, contactName, companyAddress, storeDescription})) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }

        if (this.storeAuditMapper.storeName(auditName) > 0) {
            throw new CustomizeException(ErrorEnum.STORE_ALREADY_EXISTS);
        }


        CommonUtils.checkCreditCode(creditCode);
        CommonUtils.checkPhone(contactPhone);


        Integer currentId = BaseThreadLocal.getCurrentId();


        StoreAudit storeAudit = new StoreAudit();
        storeAudit.setUserId(currentId);
        storeAudit.setAuditName(auditName);
        storeAudit.setCreditCode(creditCode);
        storeAudit.setContactName(contactName);
        storeAudit.setContactPhone(contactPhone);
        storeAudit.setCompanyAddress(companyAddress);
        storeAudit.setStoreDescription(storeDescription);

        storeAudit.setBusinessLicense("");


        int insert = this.storeAuditMapper.insert(storeAudit);
        if (insert < 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }

        return new CommonResponse("申请表已提交", "200", Boolean.valueOf(true));
    }


    public CommonResponse<Boolean> addPicture(MultipartFile file) {
        if ("".equals(file.getOriginalFilename())) {
            throw new CustomizeException(ErrorEnum.WRONG_FILE_TYPE);
        }


        String storagePath = "img/Store/";

        Integer currentId1 = BaseThreadLocal.getCurrentId();
        String md5Id = CommonUtils.md5(currentId1 + "");

        String businessLicensePicture = md5Id + "businessLicense";


        Integer status = this.storeAuditMapper.getStatus(currentId1);
        if (status == null || status.intValue() == 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }


        String url = FileUploadUtils.upLoadImg(file, storagePath, businessLicensePicture);


        Integer currentId = BaseThreadLocal.getCurrentId();


        int insert = this.storeAuditMapper.updatePictrueByDate(currentId, url);
        if (insert < 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }

        return new CommonResponse("图片上传成功", "200", Boolean.valueOf(true));
    }


    public CommonResponse<List<StoreAudit>> getStoreAudit() {
        List<StoreAudit> allOrder = storeAuditMapper.getAllOrder();
        return new CommonResponse("获取审核表成功", "200", allOrder);
    }


    @Transactional(rollbackFor = {Exception.class})
    public CommonResponse<Boolean> approval(int Id, String approval_status, String audit_results) {
        if (StringUtils.isAllBlank(new CharSequence[]{Id + "", approval_status, audit_results})) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }
        int i = this.storeAuditMapper.updateApproval(Id, approval_status, audit_results);


        if (i < 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }

        if (approval_status.equals("2")) {
            StoreAudit storeAudit = (StoreAudit) this.storeAuditMapper.selectById(Integer.valueOf(Id));
            Store store = new Store();

            store.setStatus(Integer.valueOf(1));
            String auditName = storeAudit.getAuditName();
            store.setStoreName(auditName);
            store.setCreateDate(null);
            BeanUtils.copyProperties(storeAudit, store);
            this.storeMapper.insert(store);
        }

        return new CommonResponse("审批成功", "200", Boolean.valueOf(true));
    }


    public CommonResponse<Integer> getStatus() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        Integer status = this.storeAuditMapper.getStatus(currentId);
        if (status == null || status.intValue() < 0) {
            throw new CustomizeException(ErrorEnum.NOT_APPLIED);
        }
        return new CommonResponse("状态获取成功", "200", status);
    }


    public CommonResponse<StoreApplicationVO> putApplication() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        StoreAudit application = this.storeAuditMapper.getApplication(currentId);
        if (application == null) {
            throw new CustomizeException(ErrorEnum.NOT_APPLIED);
        }

        StoreApplicationVO storeApplicationVO = new StoreApplicationVO();
        BeanUtils.copyProperties(application, storeApplicationVO);
        return new CommonResponse("查看成功", "200", storeApplicationVO);
    }


    public CommonResponse<StoreApplicationVO> deleteApplication() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        StoreAudit application = this.storeAuditMapper.getApplication(currentId);
        Long auditId = application.getAuditId();
        this.storeAuditMapper.deleteById(auditId);
        StoreApplicationVO storeApplicationVO = new StoreApplicationVO();
        BeanUtils.copyProperties(application, storeApplicationVO);

        return new CommonResponse("查看成功", "200", storeApplicationVO);
    }
}
