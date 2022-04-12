package test.humantask;

import com.alibaba.fastjson.JSONObject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 1/7/2021
 */
public class HumanTaskUtil {

    private static HumanTaskUtil humanTaskUtil = new HumanTaskUtil();

    public static HumanTaskUtil getInstance(){
        return humanTaskUtil;
    }

    public boolean insertExceptionToHM(HumantaskExceptionInfo hmException){
        CallableStatement callStmt = null;
        Connection dbConnection = OracleConnection.getConnection();

        try {
            System.out.println(dbConnection);
            callStmt = dbConnection.prepareCall("{?=call EXCEPTION_API_PKG.Insert_Exception(?)}");
            callStmt.registerOutParameter(1, Types.NUMERIC);
            callStmt.setString(2, JSONObject.toJSONString(hmException));
            callStmt.execute();

            int result = callStmt.getInt(1);
            if (result == 2){
                return false;
            }
            dbConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
