package com.spring.bocompay.domain.merchant;

/**
 * @description: 帐号信息
 * @author: Katherine
 * @create: 2018/4/26 18:17
 */
public class MerAccInfo {
    private String Purpose;//账户用途
    private String Account;//账号
    private String CurrType;//币种
    private String AccName;//账号户名
    private String AccType;//账户类型
    private String BankId;//开户银行
    private String BranchId;//所属分行
    private String BranchName;//所属分行名称
    private String DeptNo;//开户网点
    private String DeptName;//开户网点名称
    private String AccMemo;//账号备注

    public MerAccInfo() {
    }

    public MerAccInfo(
            String purpose, String account, String currType, String accName, String accType,
            String bankId, String branchId, String branchName, String deptNo, String deptName, String accMemo) {
        Purpose = purpose;
        Account = account;
        CurrType = currType;
        AccName = accName;
        AccType = accType;
        BankId = bankId;
        BranchId = branchId;
        BranchName = branchName;
        DeptNo = deptNo;
        DeptName = deptName;
        AccMemo = accMemo;
    }
}
