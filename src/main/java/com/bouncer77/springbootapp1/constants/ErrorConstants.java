package com.bouncer77.springbootapp1.constants;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 18.09.2020
 */

public class ErrorConstants {

    public static final String SERVICE_INSTANCE_TRACKING = "SY_SRV_INST";
    public static final String INCORRECT_INPUT_PARAMETERS = "Некорректные входные параметры";
    public static final String INCORRECT_DEPTH_TYPE = "Недопустимый входной служебный параметр depthType";
    public static final String INTERNAL_ERROR = "Внутренняя ошибка";
    public static final String STATUS_CHANGE_ERROR = "Ошибка смены статуса";
    public static final String SERVICE_INSTANCE_RESPONSE_ERROR =
            "ServiceInstanceResponse или/и ServiceInstance, полученный(-ые) в ответе от Cloud Inventory DB, является пустым(-и) (null)";
    public static final String SERVICE_INSTANCE_LIST_RESPONSE_ERROR =
            "ServiceInstanceListResponse или/и один или более одного ServiceInstance, полученный(-ые) в ответе от Cloud Inventory DB, является пустым(-и) (null)";
    public static final String LONG_ID_RESPONSE_ERROR =
            "LongIdResponse или/и id, полученный(-ые) в ответе является пустым(-и) (null)";
    public static final String SERVICE_INSTANCE_PARAMETER_ALREADY_EXISTS = "Параметер с таким именем уже существует";

    public static final String ERROR_001 = "-001";
    public static final String ERROR_002 = "-002";
    public static final String ERROR_004 = "-004";
    public static final String ERROR_005 = "-005";
    public static final String ERROR_007 = "-007";
}
