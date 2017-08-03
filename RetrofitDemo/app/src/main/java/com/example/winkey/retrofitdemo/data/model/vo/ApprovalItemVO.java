package com.example.winkey.retrofitdemo.data.model.vo;

import java.util.List;

/**
 * Created by Winkey on 2017/7/26.
 */

public class ApprovalItemVO {
    public List<AttachmentsBean> Attachments;

    public static class AttachmentsBean {
        /**
         * ID : 24fe6784-3328-477f-b13e-262e197c2203
         * Description : 2
         * Url : http://flm158.oss-cn-shanghai.aliyuncs.com/image/Attachment/d707b1bd-23cd-4679-9d48-78465744d8de.jpg
         * UploadUserID : 30000000-0000-0000-0000-000000000005
         * UploadUserName : 总经理A
         * UploadUserType : Employee
         * CreatedTime : 2017-07-26T15:56:59.0515143+08:00
         */

        public String ID;
        public String Description;
        public String Url;
        public String UploadUserID;
        public String UploadUserName;
        public String UploadUserType;
        public String CreatedTime;
    }
}
