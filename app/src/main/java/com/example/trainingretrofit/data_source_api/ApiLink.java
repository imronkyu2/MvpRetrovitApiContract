package com.example.trainingretrofit.data_source_api;

public class ApiLink {
    public static String RESENT_CODE_CONTENT = "SIGN_UP";
    public static String STATUS_GRO_BASE_URL = " ";

    public static final class Api {

        public static final String BASE_URL = "http://10.10.0.60:8080/marketplace/";
        public static final String LIST_URL = "localhost:8080/marketplace/api/yogi/user/list?showAll=true&orderBy=none&filter=none&pageIndex=1&pageSize=2";
        public static final String CREATE_URL = "localhost:8080/marketplace/api/yogi/user/create?userName=yogi&from=yogi";
        public static final String UPDATE_URL = "localhost:8080/marketplace/api/yogi/user?userName=yogi&from=yogi";
        public static final String DELETE_URL = "localhost:8080/marketplace/api/yogi/user?userName=yogi&from=yogi";

    }

    public static final class PAGE_SIZE {
        public static final Integer USER_LIST = 10;
        public static final String FAILED = "FAILED";
    }
}
